(define (domain Astro)
	(:requirements :typing :strips :equality :adl)
	(:types colour thing location direction goal flag - object
			player stone robot - thing 
	)
	(:constants up down left right - direction
			brown green purple blue - colour
			updating updateStage1 updateStage2 updateStage3 updateStage4 - flag
			
	)
	
	(:functions (total-cost) - number)
	
	(:predicates 
		(clear ?l - location)
		(at ?t - thing ?l - location)
		(ladder ?l - location)
		(relativ-dir ?from ?to - location ?dir - direction)
		(alive ?p - player)
		(moving ?s - thing ?dir - direction)
		(moved ?s - thing ?dir - direction)
		(update ?f - flag)
		(wearing ?t - thing ?col - colour)
		(boarder ?l - location)
		(ground ?c - colour ?l - location)
		(button ?c - colour ?l - location)
		(gate ?c - colour ?l - location)
		(open ?c - colour)
		(facing ?r - robot ?dir - direction)
		(teleport ?l - location)
	)

	
	
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	; player action
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	
	(:action noOp
       :parameters  ()
		:precondition 	(and  
							(not (update updating))
						)
       :effect (and 
					(update updating)
					(update updateStage1)
					(increase (total-cost) 1)
				)
	)
	
	(:action walk
       :parameters  	(?p - player ?from ?to - location ?dir - direction)
       :precondition 	(and 
							(not (moving ?p left))(not (moving ?p down))(not (moving ?p right))
							(not (update updating))
							(at ?p ?from) 
							(clear ?to)
							(relativ-dir ?from ?to ?dir)
							(or (= ?dir left) (= ?dir right)) 
						)
       :effect 			(and
							(not (at ?p ?from))
							(clear ?from)
							(at ?p ?to)
							(not (clear ?to))
							(moving ?p ?dir)
							(update updating)
							(update updateStage1)
							(increase (total-cost) 1)
						)
	)

   (:action climbUp
       :parameters  (?p - player ?from ?to - location)
       :precondition (and
						(not (moving ?p left))(not (moving ?p down))(not (moving ?p right))  
						(not (update updating))
						(at ?p ?from) 
						(clear ?to)
						(relativ-dir ?from ?to up)
						(ladder ?from)
					 )
       :effect (and (not (at ?p ?from))
					(clear ?from)
					(not (clear ?to))
					(at ?p ?to)
					(update updating)
					(update updateStage1)
					(increase (total-cost) 1)
				)
	)
	(:action climbDown
		:parameters  (?p - player ?from ?to - location)
		:precondition (and 
						(not (moving ?p left))(not (moving ?p down))(not (moving ?p right))		
						(not (update updating))
						(at ?p ?from) 
						(clear ?to)
						(relativ-dir ?from ?to down)
						(or (ladder ?to) (ladder ?from))
					  )
		:effect 	(and 
						(not (at ?p ?from))
						(clear ?from)
						(not (clear ?to))
						(at ?p ?to)
						(update updating)
						(update updateStage1)
						(increase (total-cost) 1)
					)
	)
	(:action push
		:parameters  (?p - player ?t - thing ?at ?from ?to - location ?dir - direction)
		:precondition (and
						(not (moving ?p left))(not (moving ?p down))(not (moving ?p right))
						(not (update updating))
						(at ?p ?at) (at ?t ?from) (clear ?to) 
						(relativ-dir ?at ?from ?dir) 
						(relativ-dir ?from ?to ?dir) 
						(or (= ?dir left) (= ?dir right))
					  )
		:effect (and  	
						(not (at ?t ?from))
						(at ?t ?to)
						(not (clear ?to))
						(clear ?from)
						(moving ?t ?dir)
						(when (exists (?r - robot) (= ?t ?r))
							(facing ?r ?dir)
						)
						(update updating)
						(update updateStage1)
						(increase (total-cost) 1)
				)
	
	
	)
	
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	; update
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	; stage 1 - destroying
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;	
	; slows down the preproccessing
	;remove/kill items from boarder
	(:action updateDestroy
       :parameters  ()
       :precondition 	(and  
							(update updateStage1)
						)
       :effect (and  
					(forall (?t - thing ?at ?under - location)
						(when 
							(or 
								(and 
									(boarder ?at)
									(at ?t ?at) 	
								)
								(and 
									(relativ-dir ?at ?under down)
									(at ?t ?at)
									(or
										(and (ground blue ?under) (not (wearing ?p blue)))
										(and (ground purple ?under) (not (wearing ?p purple)))
									)
									(not (exists (?r - robot) (= ?t ?r)))
								)
								
							)
								(and
									(not (at ?t ?at))
									(not (moving ?t left))
									(not (moving ?t right))
									(not (moving ?t down))
									(clear ?at)
								)
						)
						
					)
					(not (update updateStage1))
					(update updateStage2)
				)
	)

	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	; stage 2 - start Falling
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;	
	; changes direction for things that starts to fall

	(:action updateWorldFalling
		:parameters ()
		:precondition 	(and
							(update updateStage2)
						)
		:effect			(and
							(forall (?t - thing ?at ?under - location)
									(when 
										(and
											(relativ-dir ?at ?under down)
											(at ?t ?at)
											(clear ?under)
											(not (ladder ?under))
											(not (ladder ?at))
										)
											(and
												(not (moving ?t left))
												(not (moving ?t right))
												(moving ?t down)
											)
									)
							
							)
							(update updateStage3)
							(not (update updateStage2))
							
						)
	)
	
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	; stage 3 - moving
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;	
	; moves the objects that should be moved during update
	(:action updateMove
		:parameters  (?t - thing ?from ?to ?under - location ?dir - direction)
		:precondition 	(and
							(moving ?t ?dir)
							(at ?t ?from)
							(relativ-dir ?from ?to ?dir)
							(relativ-dir ?from ?under down)
							(clear ?to)
							
							(or 
								(exists (?r - robot) (= ?t ?r))
								(ground green ?under) 
								(= ?dir down)
							)
							(update updateStage3)
						)
						
		:effect	(and
					(moved ?t ?dir)
					(not (moving ?t ?dir))
					(not (at ?t from))
					(at ?t ?to)
					(clear ?from)
					(not (clear ?to))
				)
	)
	
	(:action updateMoveStop
		:parameters  (?t - thing ?from ?to ?under - location ?dir - direction)
		:precondition 	(and
							(moving ?t ?dir)
							(at ?t ?from)
							(relativ-dir ?from ?to ?dir)
							(relativ-dir ?from ?under down)
							
							(or 
								(not (clear ?to))
								(and 
									(clear ?under)
									(not (ladder ?under))
									(not (= ?dir down))
								)
								(and 
									(not(ground green ?under))
									(not (= ?dir down))	
									(not (exists (?r - robot) (= ?t ?r)))	
									
								)
								
							)
							(update updateStage3)

						)
						
		:effect	(and
					(when 	(exists (?r - robot ?rdir - direction) 
								(and
									(= ?t ?r)
									(= ?dir down)
									(facing ?r ?rdir)
								)								
							)
								(moved ?t ?rdir)
					)
					(not (moving ?t ?dir))
				)
	)
	
	
	; ends the world update sequnce
	(:action updateMoveEnd
		:parameters  ()
		:precondition 	(and
							(update updateStage3)
							(not (exists (?t - thing ?dir - direction)(moving ?t ?dir)))
						)
						
		:effect	(and
					(forall (?t2 - thing ?dir2 - direction) 
						(when 	(moved ?t2 ?dir2) 
								(and 
									(not (moved ?t2 ?dir2))
									(moving ?t2 ?dir2)
								)	
						)
					)
					(not (update updateStage3))
					(update updateStage4)
				)
	)

	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	; stage 4 - teleport
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;	
	
	(:action updateTeleport
		:parameters  (?p - player ?at ?tele - location)
		:precondition 	(and
							(update updateStage4)
							(at ?p ?at)
							(teleport ?tele)
							(teleport ?at)
							(not (= ?at ?tele))
						)
		:effect	(and
					(at ?p ?tele)
					(not (at ?p ?at))
					(clear ?at)
					(not (clear ?tele))
					(not (update updateStage4))
					(not (update updating))
				)
	)
	
	(:action updateTeleportEnd
		:parameters  (?p - player ?at ?tele - location)
		:precondition 	(and
							(update updateStage4)
							(at ?p ?at)
							(not (teleport ?at))

						)
		:effect	(and
					(not (update updateStage4))
					(not (update updating))
				)
	)
)