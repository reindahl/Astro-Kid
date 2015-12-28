(define (domain Astro)
	(:requirements :typing :strips :equality :adl)
	(:types colour remote thing location direction goal flag - object
			player stone robot - thing 
			
	)
	(:constants up down left right - direction
			brown green purple blue red yellow - colour
			updating updateStage1 updateStage2 updateStage3 updateStage4 updateStage5 updateStage6 - flag
			
	)
	
	(:functions (total-cost) - number)
	
	(:predicates 
		(clear ?l - location)
		(at ?t - thing ?l - location)
		(ladder ?l - location)
		(relativ-dir ?from ?to - location ?dir - direction)
		(moving ?s - thing ?dir - direction)
		(moved ?s - thing ?dir - direction)
		(update ?f - flag)
		(boarder ?l - location)
		(ground ?c - colour ?l - location)
		(button ?c - colour ?l - location)
		(gate ?c - colour ?l - location)
		(closed ?at - loaction)
		(facing ?t - thing ?dir - direction)
		(teleport ?l - location)
		(has ?r - remote)
		(wearing ?col - colour)
		(boot ?col - colour ?at - location)
		(controller ?r - remote ?at - location)
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
	
	
	(:action activateRobot
       :parameters  	(?at - location ?r - robot ?rem - remote ?dir - direction)
       :precondition 	(and 
							(not (moving ?r left))(not (moving ?r down))(not (moving ?r right))
							(not (update updating))
							(at ?r ?at) 
							(facing ?r ?dir)
							(has ?rem)
						)
       :effect 			(and
							(not (has ?rem))
							(moving ?r ?dir)
							(update updating)
							(update updateStage1)
							(increase (total-cost) 1)
						)
	)
	
	
	(:action walk
       :parameters  	(?p - player ?from ?to ?underTo - location ?dir - direction)
       :precondition 	(and 
							(not (moving ?p left))(not (moving ?p down))(not (moving ?p right))
							(not (closed ?to))
							(not (update updating))
							(at ?p ?from) 
							(clear ?to)
							(relativ-dir ?from ?to ?dir)
							(or (= ?dir left) (= ?dir right)) 
							
							(relativ-dir ?to ?underTo down)
						)
       :effect 			(and
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
						(not (closed ?to))
						(not (update updating))
						(at ?p ?from) 
						(or 
							(clear ?to) 
							(and
								(exists (?col - colour) (ground ?col ?to))
								(ladder ?to)
							)
						)
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
						(not (closed ?to))
						(not (update updating))
						(at ?p ?from) 
						(or 
							(clear ?to) 
							(and
								(exists (?col - colour) (ground ?col ?to))
								(ladder ?to)
							)
						)
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
						(not (closed ?to))
						(not (update updating))
						(at ?p ?at) (at ?t ?from) (clear ?to) 
						(relativ-dir ?at ?from ?dir) 
						(relativ-dir ?from ?to ?dir) 
						(or (= ?dir left) (= ?dir right))
					  )
		:effect (and  	
						(moving ?t ?dir)

						(facing ?t ?dir)
						(when (= ?dir left) (not (facing ?t right)))
						(when (= ?dir right) (not (facing ?t left)))

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
										(and (ground blue ?under) (not (wearing blue)))
										(and (ground purple ?under) (not (wearing purple)))
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
											(not (closed ?under))
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
							(update updateStage3)
							(at ?t ?from)
							(relativ-dir ?from ?under down)
							(relativ-dir ?from ?to ?dir)
							(or (= ?dir left) (= ?dir right)) 
							
						)
						
		:effect (and
					
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
					(update updateStage5)
				)
	)
	
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	; stage 5 - gates
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;	
	
	(:action updateGate
		:parameters  ()
		:precondition 	(and
							(update updateStage5)
							
						)
		:effect	(and
					(forall (?gate ?b - location ?col - colour)
						(when
							(and
								(gate ?col ?gate)
								(button ?col ?b)
							)
								(and
									(when (clear ?b)(closed ?gate))
									(when (not (clear ?b))(not (closed ?gate)))
								)
						)
							
					)
					(not (update updateStage5))
					(update updateStage6)
				)
	)
	
	
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	; stage 6 - pickup
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;	
	
	(:action updatePickupRemote
		:parameters  (?p - player ?at - location ?r - remote)
		:precondition 	(and
							(update updateStage6)
							(at ?p ?at)
							(controller ?r ?at)
						)
		:effect	(and
					
					(not (update updateStage6))
					(not (update updating))
					(has ?r)
					(not (controller ?r ?at))
				)
	)
	
	(:action updatePickupBoots
		:parameters  (?p - player ?at - location ?col - colour)
		:precondition 	(and
							(update updateStage6)
							(at ?p ?at)
							(boot ?col ?at)
						)
		:effect	(and
					
					(not (update updateStage6))
					(not (update updating))
					(wearing ?col)
					(when (= ?col green) (and (not (wearing purple)) (not (wearing blue))))
					(when (= ?col blue) (and (not (wearing purple)) (not (wearing green))))
					(when (= ?col purple) (and (not (wearing blue)) (not (wearing green))))
					(not (boot ?col ?at))
					
				)
	)
	
	(:action updatePickupEnd
		:parameters  (?p - player ?at - location)
		:precondition 	(and
							(update updateStage6)
							(at ?p ?at)
							(not (exists (?col - colour) (boot ?col ?at)))
							(not (exists (?r - remote) (controller ?r ?at)))
						)
		:effect	(and
					(not (update updateStage6))
					(not (update updating))
				)
	)
	
)
