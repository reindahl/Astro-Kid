(define (domain BLOCKS)
  (:requirements :strips :typing)
	(:predicates 
		(on ?x ?y - object)
		(clear ?x)
		(handempty)
		(holding ?x - box)
	)
	(:types box table - object)
	(:constants t - table)
	(:action pick-up
	    :parameters (?x - box ?under - object)
	    :precondition (and 
			(clear ?x)  
			(handempty)
			(on ?x ?under)
		)
	    :effect (and 
			(not (on ?x ?under))
			(not (clear ?x))
			(clear ?under)
			(not (handempty))
			(holding ?x)
		)
	)
	(:action put-down
	    :parameters (?x - box ?under - object)
	    :precondition (holding ?x)
	    :effect(and 
			(not (holding ?x))
			(clear ?x)
			(not (clear ?under))
			(handempty)
			(on ?x ?under)
		)
	)
)