(define (domain Astro)
	(:requirements :typing :strips :equality :adl)
	(:types colour remote thing location direction goal flag - object
			player stone robot - thing 
			
	)
	(:constants up down left right - direction
			brown green purple blue red - colour
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
	
	
	(:action walk
       :parameters  	(?p - player ?from ?to ?underFrom ?underTo - location ?dir - direction)
       :precondition 	(and 
       						(not (moving ?p left))(not (moving ?p right))
							(at ?p ?from) 

							(clear ?to)
							
							(relativ-dir ?from ?to ?dir)
							(or (= ?dir left) (= ?dir right)) 
							
							
							(relativ-dir ?from ?underFrom down)
							(or
								(not (clear ?underFrom))
								(ladder ?underFrom)
							)
							
							(relativ-dir ?to ?underTo down)
							(not (and (ground blue ?underFrom) (not (wearing blue))))
							(not (and (ground purple ?underFrom) (not (wearing purple))))
							
							
							;;check nothing is falling
							
							(not (exists (?t3 - thing ?dirx - direction) (moving ?t3 ?dirx))) 
							
							(not (exists (?tx - thing ?tAt ?tUnder - location)
									(and
										(relativ-dir ?tAt ?tUnder ?tUnder)
										(not (clear ?tUnder))
										
										(not 
											(exists (?px - player) 
												(and
													(= ?px ?tx)
													(or
														(ladder ?tUnder)
														(ladder ?tAt)
													) 
												)
											)
										)
									)
								)
							
							)
						)
       :effect 			(and
							(not (at ?p ?from))
							(clear ?from)
							(at ?p ?to)
							(not (clear ?to))
							(increase (total-cost) 1)
							
							(when (ground green ?underTo)
								(moving ?p ?dir)
							)
						)
	)

	 (:action climbUp
       :parameters  (?p - player ?from ?to - location)
       :precondition (and
						(not (moving ?p left))(not (moving ?p right)) 

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
						
						;;check nothing else is moving
							
						(not (exists (?t3 - thing ?dirx - direction) (moving ?t3 ?dirx))) 
							
						(not (exists (?t - thing ?tAt ?tUnder - location)
								(and
									(relativ-dir ?tAt ?tUnder ?tUnder)
									(not (clear ?tUnder))
										
									(exists (?p - player) 
										(and
											(= ?p ?t)
											(or
												(ladder ?tUnder)
												(ladder ?tAt)
											) 
										)
									)
								)
							)
						)
					)
       :effect (and (not (at ?p ?from))
					(clear ?from)
					(not (clear ?to))
					(at ?p ?to)
					(increase (total-cost) 1)
				)
	)
	(:action climbDown
			:parameters  (?p - player ?from ?to - location)
			:precondition (and 
							(not (moving ?p left))(not (moving ?p right))
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
							
							;;check nothing else is moving
							
							(not (exists (?t3 - thing ?dirx - direction) (moving ?t3 ?dirx))) 
							
							(not (exists (?t - thing ?tAt ?tUnder - location)
									(and
										(relativ-dir ?tAt ?tUnder ?tUnder)
										(not (clear ?tUnder))
										
										(exists (?p - player) 
											(and
												(= ?p ?t)
												(or
													(ladder ?tUnder)
													(ladder ?tAt)
												) 
											)
										)
									)
								)
							
							)
						  )
			:effect 	(and 
							(not (at ?p ?from))
							(clear ?from)
							(not (clear ?to))
							(at ?p ?to)
							(increase (total-cost) 1)
						)
	)
	
	
	(:action push
		:parameters  (?p - player ?t - thing ?at ?under ?from ?to ?underFrom ?underTo - location ?dir - direction)
		:precondition (and
						(not (moving ?p left))(not (moving ?p right))
						(at ?p ?at) (at ?t ?from) (clear ?to) 
						(relativ-dir ?at ?from ?dir) 
						(relativ-dir ?from ?to ?dir) 
						
						(or (= ?dir left) (= ?dir right))
						
						(not (= ?p ?t))
						
						(relativ-dir ?from ?underfrom down)
						(relativ-dir ?to ?underTo down)
						;;check nothing else is moving
							(relativ-dir ?from ?under down)
							(or
								(not (clear ?under))
								(ladder ?under)
							)
							
							(not (exists (?t3 - thing ?dirx - direction) (moving ?t3 ?dirx))) 
							
							(not (exists (?t2 - thing ?tAt ?tUnder - location)
									(and
										(relativ-dir ?tAt ?tUnder ?tUnder)
										(not (clear ?tUnder))
										
										(not 
											(and
												(= ?p ?t2)
												(or
													(ladder ?tUnder)
													(ladder ?tAt)
												) 
											)
										)
									)
								)
							
							)
					  )
		:effect (and  	
						(not (at ?t ?from))
						(at ?t ?to)
						(not (clear ?to))
						(clear ?from)

						(facing ?t ?dir)
						(when (= ?dir left) (not (facing ?t right)))
						(when (= ?dir right) (not (facing ?t left)))

						(when 	(or
									(ground green ?underTo)
									(exists (?r - robot) (= ?r ?t))
								)
								(moving ?t ?dir)
						)

						(increase (total-cost) 1)
				)
	
	
	)
	
	
	(:action fall
	
			:parameters  (?t - thing ?at ?under - location)
			:precondition	(and
								(relativ-dir ?at ?under down)
								(at ?t ?at)
								(clear ?under)
								(not (ladder ?under))
								(not (ladder ?at))
								(not (exists (?p - player) (= ?p ?t)))
							)
			:effect			(and
								(not (at ?t ?at))
								(clear ?at)
								(at ?t ?under)
								(not (clear ?under))
							)
	)
	(:action fall 
	
			:parameters  (?p - player ?at ?under - location)
			:precondition	(and
								(relativ-dir ?at ?under down)
								(at ?p ?at)
								(clear ?under)
								(not (ladder ?under))
								(not (ladder ?at))
							)
			:effect			(and
								(not (at ?p ?at))
								(clear ?at)
								(at ?p ?under)
								(not (clear ?under))
								(not (moving ?p left))
								(not (moving ?p right))
								
								(increase (total-cost) 1)
								
							)
	)
	(:action slide 
	
			:parameters  (?p - player ?at ?to ?under ?underTo - location ?dir - direction)
			:precondition	(and
								(relativ-dir ?at ?under down)
								(relativ-dir ?at ?to ?dir)
								(relativ-dir ?to ?underTo down)
								(at ?p ?at)
								
								(clear ?to)
								
								(moving ?p ?dir)
								(or (= ?dir left) (= ?dir right))
								
								(or
									(not (clear ?under))
									(ladder ?under)
									(ladder ?at)
								)
							)
			:effect			(and
								(not (at ?p ?at))
								(clear ?at)
								(at ?p ?to)
								(not (clear ?to))
								(when (not (ground green ?underTo))
									(and
										(not (moving ?p left))
										(not (moving ?p right))
									)
								)
								(increase (total-cost) 1)
							)
	)						
		(:action slide 
	
			:parameters  (?t - thing ?at ?to ?under ?underTo - location ?dir - direction)
			:precondition	(and
								(relativ-dir ?at ?under down)
								(relativ-dir ?at ?to ?dir)
								(relativ-dir ?to ?underTo down)
								(at ?t ?at)
								
								(clear ?to)
								(not (exists (?p - player) (= ?p ?t)))
								
								(moving ?t ?dir)
								(or (= ?dir left) (= ?dir right))
								
								(or
									(not (clear ?under))
									(ladder ?under)
									(ladder ?at)
								)
							)
			:effect			(and
								(not (at ?t ?at))
								(clear ?at)
								(at ?t ?to)
								(not (clear ?to))
								
							)
	)
	(:action slideStop 
	
			:parameters  (?t - thing ?at ?under ?to - location ?dir - direction)
			:precondition	(and
								(relativ-dir ?at ?under down)
								(relativ-dir ?at ?to ?dir)
								(at ?t ?at)
		
								(moving ?t ?dir)
								(or (= ?dir left) (= ?dir right))
								(not (clear ?under))
								(or 
									(and 
										(not (ground green ?under))
										(not (exists (?r - robot) (= ?r ?t)))
									)
									(not (clear ?to))
								)
							)
			:effect			(and
								(not (moving ?t left))
								(not (moving ?t right))
							)
	)
	
	(:action boarder 
	
			:parameters  (?t - thing ?at - location)
			:precondition	(and
								(at ?t ?at)
								
								(boarder ?at)
							)
			:effect			(and
								(not (moving ?t left))
								(not (moving ?t right))
								(not (at ?t ?at))
								(clear ?at)
							)
	)			
)
