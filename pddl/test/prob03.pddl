;         
;     p   
;         
;         
;     g   
;     ¤   
;         


(define (problem prob03-Astro)
 (:domain Astro)
 (:objects
  player-01 - player
  pos-00-00 - location
  pos-01-00 - location
  pos-02-00 - location
  pos-03-00 - location
  pos-04-00 - location
  pos-05-00 - location
  pos-06-00 - location
  pos-07-00 - location
  pos-08-00 - location
  pos-00-01 - location
  pos-01-01 - location
  pos-02-01 - location
  pos-03-01 - location
  pos-04-01 - location
  pos-05-01 - location
  pos-06-01 - location
  pos-07-01 - location
  pos-08-01 - location
  pos-00-02 - location
  pos-01-02 - location
  pos-02-02 - location
  pos-03-02 - location
  pos-04-02 - location
  pos-05-02 - location
  pos-06-02 - location
  pos-07-02 - location
  pos-08-02 - location
  pos-00-03 - location
  pos-01-03 - location
  pos-02-03 - location
  pos-03-03 - location
  pos-04-03 - location
  pos-05-03 - location
  pos-06-03 - location
  pos-07-03 - location
  pos-08-03 - location
  pos-00-04 - location
  pos-01-04 - location
  pos-02-04 - location
  pos-03-04 - location
  pos-04-04 - location
  pos-05-04 - location
  pos-06-04 - location
  pos-07-04 - location
  pos-08-04 - location
  pos-00-05 - location
  pos-01-05 - location
  pos-02-05 - location
  pos-03-05 - location
  pos-04-05 - location
  pos-05-05 - location
  pos-06-05 - location
  pos-07-05 - location
  pos-08-05 - location
  pos-00-06 - location
  pos-01-06 - location
  pos-02-06 - location
  pos-03-06 - location
  pos-04-06 - location
  pos-05-06 - location
  pos-06-06 - location
  pos-07-06 - location
  pos-08-06 - location
 )
 (:init
  (= (total-cost) 0)
  (at player-01 pos-05-01)
  (alive player-01)
  (clear pos-00-00)
  (relativ-dir pos-00-00 pos-01-00 right)
  (relativ-dir pos-00-00 pos-00-01 down)
  (clear pos-00-01)
  (relativ-dir pos-00-01 pos-01-01 right)
  (relativ-dir pos-00-01 pos-00-00 up)
  (relativ-dir pos-00-01 pos-00-02 down)
  (clear pos-00-02)
  (relativ-dir pos-00-02 pos-01-02 right)
  (relativ-dir pos-00-02 pos-00-01 up)
  (relativ-dir pos-00-02 pos-00-03 down)
  (clear pos-00-03)
  (relativ-dir pos-00-03 pos-01-03 right)
  (relativ-dir pos-00-03 pos-00-02 up)
  (relativ-dir pos-00-03 pos-00-04 down)
  (clear pos-00-04)
  (relativ-dir pos-00-04 pos-01-04 right)
  (relativ-dir pos-00-04 pos-00-03 up)
  (relativ-dir pos-00-04 pos-00-05 down)
  (clear pos-00-05)
  (relativ-dir pos-00-05 pos-01-05 right)
  (relativ-dir pos-00-05 pos-00-04 up)
  (relativ-dir pos-00-05 pos-00-06 down)
  (clear pos-00-06)
  (relativ-dir pos-00-06 pos-01-06 right)
  (relativ-dir pos-00-06 pos-00-05 up)
  (boarder pos-00-06)
  (clear pos-01-00)
  (relativ-dir pos-01-00 pos-00-00 left)
  (relativ-dir pos-01-00 pos-02-00 right)
  (relativ-dir pos-01-00 pos-01-01 down)
  (clear pos-01-01)
  (relativ-dir pos-01-01 pos-00-01 left)
  (relativ-dir pos-01-01 pos-02-01 right)
  (relativ-dir pos-01-01 pos-01-00 up)
  (relativ-dir pos-01-01 pos-01-02 down)
  (clear pos-01-02)
  (relativ-dir pos-01-02 pos-00-02 left)
  (relativ-dir pos-01-02 pos-02-02 right)
  (relativ-dir pos-01-02 pos-01-01 up)
  (relativ-dir pos-01-02 pos-01-03 down)
  (clear pos-01-03)
  (relativ-dir pos-01-03 pos-00-03 left)
  (relativ-dir pos-01-03 pos-02-03 right)
  (relativ-dir pos-01-03 pos-01-02 up)
  (relativ-dir pos-01-03 pos-01-04 down)
  (clear pos-01-04)
  (relativ-dir pos-01-04 pos-00-04 left)
  (relativ-dir pos-01-04 pos-02-04 right)
  (relativ-dir pos-01-04 pos-01-03 up)
  (relativ-dir pos-01-04 pos-01-05 down)
  (clear pos-01-05)
  (relativ-dir pos-01-05 pos-00-05 left)
  (relativ-dir pos-01-05 pos-02-05 right)
  (relativ-dir pos-01-05 pos-01-04 up)
  (relativ-dir pos-01-05 pos-01-06 down)
  (clear pos-01-06)
  (relativ-dir pos-01-06 pos-00-06 left)
  (relativ-dir pos-01-06 pos-02-06 right)
  (relativ-dir pos-01-06 pos-01-05 up)
  (boarder pos-01-06)
  (clear pos-02-00)
  (relativ-dir pos-02-00 pos-01-00 left)
  (relativ-dir pos-02-00 pos-03-00 right)
  (relativ-dir pos-02-00 pos-02-01 down)
  (clear pos-02-01)
  (relativ-dir pos-02-01 pos-01-01 left)
  (relativ-dir pos-02-01 pos-03-01 right)
  (relativ-dir pos-02-01 pos-02-00 up)
  (relativ-dir pos-02-01 pos-02-02 down)
  (clear pos-02-02)
  (relativ-dir pos-02-02 pos-01-02 left)
  (relativ-dir pos-02-02 pos-03-02 right)
  (relativ-dir pos-02-02 pos-02-01 up)
  (relativ-dir pos-02-02 pos-02-03 down)
  (clear pos-02-03)
  (relativ-dir pos-02-03 pos-01-03 left)
  (relativ-dir pos-02-03 pos-03-03 right)
  (relativ-dir pos-02-03 pos-02-02 up)
  (relativ-dir pos-02-03 pos-02-04 down)
  (clear pos-02-04)
  (relativ-dir pos-02-04 pos-01-04 left)
  (relativ-dir pos-02-04 pos-03-04 right)
  (relativ-dir pos-02-04 pos-02-03 up)
  (relativ-dir pos-02-04 pos-02-05 down)
  (clear pos-02-05)
  (relativ-dir pos-02-05 pos-01-05 left)
  (relativ-dir pos-02-05 pos-03-05 right)
  (relativ-dir pos-02-05 pos-02-04 up)
  (relativ-dir pos-02-05 pos-02-06 down)
  (clear pos-02-06)
  (relativ-dir pos-02-06 pos-01-06 left)
  (relativ-dir pos-02-06 pos-03-06 right)
  (relativ-dir pos-02-06 pos-02-05 up)
  (boarder pos-02-06)
  (clear pos-03-00)
  (relativ-dir pos-03-00 pos-02-00 left)
  (relativ-dir pos-03-00 pos-04-00 right)
  (relativ-dir pos-03-00 pos-03-01 down)
  (clear pos-03-01)
  (relativ-dir pos-03-01 pos-02-01 left)
  (relativ-dir pos-03-01 pos-04-01 right)
  (relativ-dir pos-03-01 pos-03-00 up)
  (relativ-dir pos-03-01 pos-03-02 down)
  (clear pos-03-02)
  (relativ-dir pos-03-02 pos-02-02 left)
  (relativ-dir pos-03-02 pos-04-02 right)
  (relativ-dir pos-03-02 pos-03-01 up)
  (relativ-dir pos-03-02 pos-03-03 down)
  (clear pos-03-03)
  (relativ-dir pos-03-03 pos-02-03 left)
  (relativ-dir pos-03-03 pos-04-03 right)
  (relativ-dir pos-03-03 pos-03-02 up)
  (relativ-dir pos-03-03 pos-03-04 down)
  (clear pos-03-04)
  (relativ-dir pos-03-04 pos-02-04 left)
  (relativ-dir pos-03-04 pos-04-04 right)
  (relativ-dir pos-03-04 pos-03-03 up)
  (relativ-dir pos-03-04 pos-03-05 down)
  (clear pos-03-05)
  (relativ-dir pos-03-05 pos-02-05 left)
  (relativ-dir pos-03-05 pos-04-05 right)
  (relativ-dir pos-03-05 pos-03-04 up)
  (relativ-dir pos-03-05 pos-03-06 down)
  (clear pos-03-06)
  (relativ-dir pos-03-06 pos-02-06 left)
  (relativ-dir pos-03-06 pos-04-06 right)
  (relativ-dir pos-03-06 pos-03-05 up)
  (boarder pos-03-06)
  (clear pos-04-00)
  (relativ-dir pos-04-00 pos-03-00 left)
  (relativ-dir pos-04-00 pos-05-00 right)
  (relativ-dir pos-04-00 pos-04-01 down)
  (clear pos-04-01)
  (relativ-dir pos-04-01 pos-03-01 left)
  (relativ-dir pos-04-01 pos-05-01 right)
  (relativ-dir pos-04-01 pos-04-00 up)
  (relativ-dir pos-04-01 pos-04-02 down)
  (clear pos-04-02)
  (relativ-dir pos-04-02 pos-03-02 left)
  (relativ-dir pos-04-02 pos-05-02 right)
  (relativ-dir pos-04-02 pos-04-01 up)
  (relativ-dir pos-04-02 pos-04-03 down)
  (clear pos-04-03)
  (relativ-dir pos-04-03 pos-03-03 left)
  (relativ-dir pos-04-03 pos-05-03 right)
  (relativ-dir pos-04-03 pos-04-02 up)
  (relativ-dir pos-04-03 pos-04-04 down)
  (clear pos-04-04)
  (relativ-dir pos-04-04 pos-03-04 left)
  (relativ-dir pos-04-04 pos-05-04 right)
  (relativ-dir pos-04-04 pos-04-03 up)
  (relativ-dir pos-04-04 pos-04-05 down)
  (clear pos-04-05)
  (relativ-dir pos-04-05 pos-03-05 left)
  (relativ-dir pos-04-05 pos-05-05 right)
  (relativ-dir pos-04-05 pos-04-04 up)
  (relativ-dir pos-04-05 pos-04-06 down)
  (clear pos-04-06)
  (relativ-dir pos-04-06 pos-03-06 left)
  (relativ-dir pos-04-06 pos-05-06 right)
  (relativ-dir pos-04-06 pos-04-05 up)
  (boarder pos-04-06)
  (clear pos-05-00)
  (relativ-dir pos-05-00 pos-04-00 left)
  (relativ-dir pos-05-00 pos-06-00 right)
  (relativ-dir pos-05-00 pos-05-01 down)
  (relativ-dir pos-05-01 pos-04-01 left)
  (relativ-dir pos-05-01 pos-06-01 right)
  (relativ-dir pos-05-01 pos-05-00 up)
  (relativ-dir pos-05-01 pos-05-02 down)
  (clear pos-05-02)
  (relativ-dir pos-05-02 pos-04-02 left)
  (relativ-dir pos-05-02 pos-06-02 right)
  (relativ-dir pos-05-02 pos-05-01 up)
  (relativ-dir pos-05-02 pos-05-03 down)
  (clear pos-05-03)
  (relativ-dir pos-05-03 pos-04-03 left)
  (relativ-dir pos-05-03 pos-06-03 right)
  (relativ-dir pos-05-03 pos-05-02 up)
  (relativ-dir pos-05-03 pos-05-04 down)
  (clear pos-05-04)
  (relativ-dir pos-05-04 pos-04-04 left)
  (relativ-dir pos-05-04 pos-06-04 right)
  (relativ-dir pos-05-04 pos-05-03 up)
  (relativ-dir pos-05-04 pos-05-05 down)
  (relativ-dir pos-05-05 pos-04-05 left)
  (relativ-dir pos-05-05 pos-06-05 right)
  (relativ-dir pos-05-05 pos-05-04 up)
  (relativ-dir pos-05-05 pos-05-06 down)
  (clear pos-05-06)
  (relativ-dir pos-05-06 pos-04-06 left)
  (relativ-dir pos-05-06 pos-06-06 right)
  (relativ-dir pos-05-06 pos-05-05 up)
  (boarder pos-05-06)
  (clear pos-06-00)
  (relativ-dir pos-06-00 pos-05-00 left)
  (relativ-dir pos-06-00 pos-07-00 right)
  (relativ-dir pos-06-00 pos-06-01 down)
  (clear pos-06-01)
  (relativ-dir pos-06-01 pos-05-01 left)
  (relativ-dir pos-06-01 pos-07-01 right)
  (relativ-dir pos-06-01 pos-06-00 up)
  (relativ-dir pos-06-01 pos-06-02 down)
  (clear pos-06-02)
  (relativ-dir pos-06-02 pos-05-02 left)
  (relativ-dir pos-06-02 pos-07-02 right)
  (relativ-dir pos-06-02 pos-06-01 up)
  (relativ-dir pos-06-02 pos-06-03 down)
  (clear pos-06-03)
  (relativ-dir pos-06-03 pos-05-03 left)
  (relativ-dir pos-06-03 pos-07-03 right)
  (relativ-dir pos-06-03 pos-06-02 up)
  (relativ-dir pos-06-03 pos-06-04 down)
  (clear pos-06-04)
  (relativ-dir pos-06-04 pos-05-04 left)
  (relativ-dir pos-06-04 pos-07-04 right)
  (relativ-dir pos-06-04 pos-06-03 up)
  (relativ-dir pos-06-04 pos-06-05 down)
  (clear pos-06-05)
  (relativ-dir pos-06-05 pos-05-05 left)
  (relativ-dir pos-06-05 pos-07-05 right)
  (relativ-dir pos-06-05 pos-06-04 up)
  (relativ-dir pos-06-05 pos-06-06 down)
  (clear pos-06-06)
  (relativ-dir pos-06-06 pos-05-06 left)
  (relativ-dir pos-06-06 pos-07-06 right)
  (relativ-dir pos-06-06 pos-06-05 up)
  (boarder pos-06-06)
  (clear pos-07-00)
  (relativ-dir pos-07-00 pos-06-00 left)
  (relativ-dir pos-07-00 pos-08-00 right)
  (relativ-dir pos-07-00 pos-07-01 down)
  (clear pos-07-01)
  (relativ-dir pos-07-01 pos-06-01 left)
  (relativ-dir pos-07-01 pos-08-01 right)
  (relativ-dir pos-07-01 pos-07-00 up)
  (relativ-dir pos-07-01 pos-07-02 down)
  (clear pos-07-02)
  (relativ-dir pos-07-02 pos-06-02 left)
  (relativ-dir pos-07-02 pos-08-02 right)
  (relativ-dir pos-07-02 pos-07-01 up)
  (relativ-dir pos-07-02 pos-07-03 down)
  (clear pos-07-03)
  (relativ-dir pos-07-03 pos-06-03 left)
  (relativ-dir pos-07-03 pos-08-03 right)
  (relativ-dir pos-07-03 pos-07-02 up)
  (relativ-dir pos-07-03 pos-07-04 down)
  (clear pos-07-04)
  (relativ-dir pos-07-04 pos-06-04 left)
  (relativ-dir pos-07-04 pos-08-04 right)
  (relativ-dir pos-07-04 pos-07-03 up)
  (relativ-dir pos-07-04 pos-07-05 down)
  (clear pos-07-05)
  (relativ-dir pos-07-05 pos-06-05 left)
  (relativ-dir pos-07-05 pos-08-05 right)
  (relativ-dir pos-07-05 pos-07-04 up)
  (relativ-dir pos-07-05 pos-07-06 down)
  (clear pos-07-06)
  (relativ-dir pos-07-06 pos-06-06 left)
  (relativ-dir pos-07-06 pos-08-06 right)
  (relativ-dir pos-07-06 pos-07-05 up)
  (boarder pos-07-06)
  (clear pos-08-00)
  (relativ-dir pos-08-00 pos-07-00 left)
  (relativ-dir pos-08-00 pos-08-01 down)
  (clear pos-08-01)
  (relativ-dir pos-08-01 pos-07-01 left)
  (relativ-dir pos-08-01 pos-08-00 up)
  (relativ-dir pos-08-01 pos-08-02 down)
  (clear pos-08-02)
  (relativ-dir pos-08-02 pos-07-02 left)
  (relativ-dir pos-08-02 pos-08-01 up)
  (relativ-dir pos-08-02 pos-08-03 down)
  (clear pos-08-03)
  (relativ-dir pos-08-03 pos-07-03 left)
  (relativ-dir pos-08-03 pos-08-02 up)
  (relativ-dir pos-08-03 pos-08-04 down)
  (clear pos-08-04)
  (relativ-dir pos-08-04 pos-07-04 left)
  (relativ-dir pos-08-04 pos-08-03 up)
  (relativ-dir pos-08-04 pos-08-05 down)
  (clear pos-08-05)
  (relativ-dir pos-08-05 pos-07-05 left)
  (relativ-dir pos-08-05 pos-08-04 up)
  (relativ-dir pos-08-05 pos-08-06 down)
  (clear pos-08-06)
  (relativ-dir pos-08-06 pos-07-06 left)
  (relativ-dir pos-08-06 pos-08-05 up)
  (boarder pos-08-06)
 )
 (:goal (and
   (at player-01 pos-05-04)
   (alive player-01)
  )
 )
 (:metric minimize (total-cost))
)
