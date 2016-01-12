(define (domain Astro)
	(:requirements :typing :strips :equality :adl)
	(:types colour remote thing location direction goal flag - object
			player stone robot - thing 
			
	)
	(:constants up down left right - direction
			brown green purple blue red yellow - colour
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
		(falling ?t)
		;(somethingsMoving)
	)
	(:derived (closed ?at)
		(exists (?c - colour ?b - location)
			(and
				(gate ?c ?at) 
				(button ?c ?b)
				(clear ?b)
			)
		)
	)
	;(:derived (somethingsMoving)
	;	(exists (?s - thing ?dir - direction)
	;		(and
	;			(moving ?s ?dir)
	;		)
	;	)
	;)
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	; player action
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	(:action Walk
		:parameters  	(
			?p0 - player
			?p1 - location
			?p2 - location
			?p3 - location
			?p4 - location
			?p5 - direction
		)
		:precondition 	(and
			(relativ-dir ?p3 ?p1 up)
			(not (ground blue ?p3))
			(not (= ?p2 ?p4))
			(not (= ?p2 ?p3))
			(not (= ?p1 ?p3))
			(not (= ?p1 ?p4))
			(not (= down ?p5))
			(not (at ?p0 ?p3))
			(not (ladder ?p4))
			(not (= ?p1 ?p2))
			(not (at ?p0 ?p2))
			(relativ-dir ?p4 ?p2 up)
			(not (at ?p0 ?p4))
			(relativ-dir ?p2 ?p4 down)
			(not (ladder ?p1))
			(not (ground green ?p3))
			(clear ?p2)
			(at ?p0 ?p1)
			(not (moving ?p0 right))
			(not (= ?p5 up))
			(not (= ?p3 ?p4))
			(relativ-dir ?p1 ?p3 down)
			(relativ-dir ?p1 ?p2 ?p5)
			(not (clear ?p1))
			(not (moving ?p0 ?p5))
			(not (clear ?p3))
			(relativ-dir ?p3 ?p4 ?p5)
		)
		:effect 	(and
			(when (and
					(not (ground blue ?p4))
					(relativ-dir ?p4 ?p3 left)
					(relativ-dir ?p2 ?p1 left)
					(ground brown ?p3)
					(not (ladder ?p2))
					(not (= left ?p5))
					(not (ground brown ?p4))
					(relativ-dir ?p3 ?p4 right)
					(not (relativ-dir ?p3 ?p4 left))
					(not (relativ-dir ?p2 ?p1 right))
					(not (relativ-dir ?p1 ?p2 left))
					(not (relativ-dir ?p4 ?p3 right))
					(not (clear ?p4))
					(relativ-dir ?p1 ?p2 right)
				)
				(moving ?p0 ?p5)
			)
			(when (and
					(not (ground blue ?p4))
					(relativ-dir ?p4 ?p3 left)
					(relativ-dir ?p2 ?p1 left)
					(ground brown ?p3)
					(not (ladder ?p2))
					(not (= left ?p5))
					(not (ground brown ?p4))
					(relativ-dir ?p3 ?p4 right)
					(not (relativ-dir ?p3 ?p4 left))
					(not (relativ-dir ?p2 ?p1 right))
					(not (relativ-dir ?p1 ?p2 left))
					(not (relativ-dir ?p4 ?p3 right))
					(not (clear ?p4))
					(relativ-dir ?p1 ?p2 right)
				)
				(moving ?p0 right)
			)
			(when (and
					(not (ground blue ?p4))
				)
				(at ?p0 ?p2)
			)
			(when (and
					(not (ground blue ?p4))
				)
				(not (clear ?p2))
			)
			(not (at ?p0 ?p1))
			(clear ?p1)
			(increase (total-cost) 1)
		)
	)
	(:action ClimbUp
		:parameters  	(
			?p0 - player
			?p1 - location
			?p2 - location
		)
		:precondition 	(and
			(not (clear ?p1))
			(at ?p0 ?p1)
			(not (moving ?p0 right))
			(relativ-dir ?p1 ?p2 up)
			(not (= ?p1 ?p2))
			(not (at ?p0 ?p2))
			(relativ-dir ?p2 ?p1 down)
			(ladder ?p1)
		)
		:effect 	(and
			(at ?p0 ?p2)
			(when (and
					(ground brown ?p1)
					(clear ?p2)
				)
				(not (clear ?p2))
			)
			(not (at ?p0 ?p1))
			(when (and
					(not (clear ?p2))
					(ground brown ?p2)
					(not (ground brown ?p1))
					(ladder ?p2)
				)
				(clear ?p1)
			)
			(increase (total-cost) 1)
		)
	)
	(:action Push
		:parameters  	(
			?p0 - player
			?p1 - object
			?p2 - location
			?p3 - location
			?p4 - location
			?p5 - location
			?p6 - location
			?p7 - location
			?p8 - direction
		)
		:precondition 	(and
			(= ?p8 right)
			(not (clear ?p7))
			(relativ-dir ?p6 ?p7 ?p8)
			(ground brown ?p6)
			(not (= ?p2 ?p5))
			(relativ-dir ?p3 ?p2 up)
			(not (= ?p6 ?p7))
			(not (= ?p2 ?p3))
			(relativ-dir ?p6 ?p7 right)
			(not (= down ?p8))
			(not (= ?p2 ?p7))
			(not (at ?p0 ?p3))
			(not (ladder ?p5))
			(not (at ?p0 ?p5))
			(relativ-dir ?p4 ?p5 ?p8)
			(not (ladder ?p3))
			(not (ground brown ?p7))
			(not (ground green ?p3))
			(not (= ?p4 ?p6))
			(relativ-dir ?p3 ?p6 right)
			(not (moving ?p1 right))
			(not (= ?p5 ?p6))
			(at ?p1 ?p4)
			(not (= ?p8 up))
			(not (clear ?p2))
			(not (clear ?p4))
			(not (= ?p3 ?p5))
			(relativ-dir ?p2 ?p3 down)
			(not (= ?p3 ?p7))
			(not (at ?p1 ?p5))
			(relativ-dir ?p6 ?p4 up)
			(at ?p0 ?p2)
			(relativ-dir ?p7 ?p5 up)
			(relativ-dir ?p4 ?p5 right)
			(relativ-dir ?p5 ?p7 down)
			(not (= ?p2 ?p4))
			(not (= ?p2 ?p6))
			(ground brown ?p3)
			(not (ladder ?p2))
			(not (= left ?p8))
			(not (at ?p0 ?p4))
			(not (= ?p4 ?p7))
			(not (= ?p4 ?p5))
			(not (moving ?p1 ?p8))
			(not (= ?p5 ?p7))
			(not (ground green ?p6))
			(not (moving ?p0 right))
			(not (= ?p3 ?p4))
			(relativ-dir ?p2 ?p4 ?p8)
			(relativ-dir ?p4 ?p2 left)
			(relativ-dir ?p7 ?p6 left)
			(relativ-dir ?p2 ?p4 right)
			(not (at ?p1 ?p2))
			(relativ-dir ?p3 ?p6 ?p8)
			(not (moving ?p0 ?p8))
			(not (= ?p3 ?p6))
			(relativ-dir ?p6 ?p3 left)
			(relativ-dir ?p5 ?p4 left)
			(relativ-dir ?p4 ?p6 down)
			(clear ?p5)
		)
		:effect 	(and
			(when (and
					(not (ground blue ?p7))
				)
				(not (clear ?p5))
			)
			(when (and
					(ground green ?p7)
					(not (ground blue ?p7))
				)
				(moving ?p1 ?p8)
			)
			(when (and
					(not (ground blue ?p7))
				)
				(at ?p1 ?p5)
			)
			(when (and
					(ground green ?p7)
					(not (ground blue ?p7))
				)
				(moving ?p1 right)
			)
			(not (at ?p1 ?p4))
			(clear ?p4)
			(increase (total-cost) 1)
		)
	)
	(:action teleport
		:parameters  (?p - player ?at ?tele - location)
		:precondition 	(and
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
				)
	)
	(:action fall
	
			:parameters  (?t - thing ?at ?under - location)
			:precondition	(and
								(relativ-dir ?at ?under down)
								(at ?t ?at)
								(clear ?under)
								(not (closed ?under))
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
								(not (closed ?under))
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
	
	(:action fallStop 
	
			:parameters  (?t - thing ?at ?under - location)
			:precondition	(and
								(relativ-dir ?at ?under down)
								(at ?t ?at)
								(or 
									(not (clear ?under))
									(closed ?under)
								)
								;(not (ladder ?under))
								;(not (ladder ?at))
							)
			:effect			(and
								(not (falling ?t))
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
								(not (closed ?to))
								(moving ?p ?dir)
								(or (= ?dir left) (= ?dir right))
								
								(ground green ?under)
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
	;;;;;;
	; Slide hit
	;;;;;;
	(:action slide 
	
			:parameters  (?p - player ?t - thing ?at ?to ?under ?underTo ?toTo - location ?dir - direction)
			:precondition	(and
								(relativ-dir ?at ?under down)
								(relativ-dir ?at ?to ?dir)
								(relativ-dir ?to ?toTo ?dir)
								(relativ-dir ?toTo ?underTo down)
								(at ?p ?at)
								
								(clear ?toTO)
								(at ?t ?to)
								(not (closed ?to))
								(moving ?p ?dir)
								(or (= ?dir left) (= ?dir right))
								
								(ground green ?under)
							)
			:effect			(and
								(not (at ?p ?at))
								(clear ?at)
								(at ?p ?to)
								(not (at ?t ?to))
								(at ?t ?toTo)
								(not (clear ?toTo))
								(and
									(not (moving ?p left))
									(not (moving ?p right))
								)
								;;start falling
								(when (and 
										(clear ?underTo)
										(not (closed ?underTo))
									)
									(falling ?t)
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
								(not (clear ?under))
								(clear ?to)
								(not (closed ?to))
								(not (exists (?p - player) (= ?p ?t)))
								
								(moving ?t ?dir)
								(or (= ?dir left) (= ?dir right))
								
								(or 
									(ground green ?under)
									(exists (?r - robot) (= ?r ?t))
								)
								
								
							)
			:effect			(and
								(not (at ?t ?at))
								(clear ?at)
								(at ?t ?to)
								(not (clear ?to))
								
								;;start falling
								(when (and 
										(clear ?underTo)
										(not (closed ?underTo))
									)
									(falling ?t)
								)
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
								 	(closed ?to)
								)
								
								;check for slide hit player
								(not (exists (?p - player ?toTo - location)
										(and
											(= ?p ?t)
											(relativ-dir ?to ?toTo ?dir)
											(clear ?toTO)
										)
									)			
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
								(not (falling ?t))
							)
	)

	
	(:action destroyStone 
	
		:parameters  (?t - stone ?at ?under - location)
		:precondition	(and 
							(relativ-dir ?at ?under down)
							(at ?t ?at)
							(or 
								(ground purple ?under)
								(ground blue ?under)
							)
						)

		:effect			(and
							(not (moving ?t left))
							(not (moving ?t right))
							(not (at ?t ?at))
							(clear ?at)
						)
	)
	
	(:action destroyPlayer
	
		:parameters  (?t - player ?at ?under - location)
		:precondition	(and 
							(relativ-dir ?at ?under down)
							(at ?t ?at)
							(or 
								(and (ground blue ?under) (not (wearing blue)))
								(and (ground purple ?under) (not (wearing purple)))
							)
							
						)

		:effect			(and
							(not (moving ?t left))
							(not (moving ?t right))
							(not (at ?t ?at))
							(clear ?at)
						)
	)
	

	(:action destroy
	
		:parameters  (?t - thing ?at ?under - location)
		:precondition	(and 
							(relativ-dir ?at ?under down)
							(at ?t ?at)
							(or
								(exists (?p - player) 
									(and 
										(= ?p ?t)
										(or 
											(and (ground blue ?under) (not (wearing blue)))
											(and (ground purple ?under) (not (wearing purple)))
										)
									)
								)
								(exists (?s - stone) 
									(and 
										(= ?s ?t)
										(or 
											(ground purple ?under)
											(ground blue ?under)
										)
									)
								)
							)
						)

		:effect			(and
							(not (moving ?t left))
							(not (moving ?t right))
							(not (at ?t ?at))
							(clear ?at)
						)
	)
	
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	; pickup
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;	
	
	(:action pickupRemote
		:parameters  (?p - player ?at - location ?r - remote)
		:precondition 	(and
							(at ?p ?at)
							(controller ?r ?at)
						)
		:effect	(and
					(has ?r)
					(not (controller ?r ?at))
				)
	)
	
	(:action pickupBoots
		:parameters  (?p - player ?at - location ?col - colour)
		:precondition 	(and
							(at ?p ?at)
							(boot ?col ?at)
						)
		:effect	(and

					(wearing ?col)
					(when (= ?col green) (and (not (wearing purple)) (not (wearing blue))))
					(when (= ?col blue) (and (not (wearing purple)) (not (wearing green))))
					(when (= ?col purple) (and (not (wearing blue)) (not (wearing green))))
					(not (boot ?col ?at))
					
				)
	)		
)
