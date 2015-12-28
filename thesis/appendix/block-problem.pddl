(define (problem BLOCKS-4-0)
	(:domain BLOCKS)
	(:objects 
		D B A C - box
	)
	(:INIT 
		(clear C) (clear A) (clear B) (clear D) 
		(on C t) (on A t) (on B t) (on D t) 
		(handempty))
	(:goal 
		(and 
			(on D C) (on C B) (on B A)
		)
	)
)