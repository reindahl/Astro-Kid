;     r         
;   #¤¤         
;   #           
;   # s         
;  ¤#¤¤ ¤¤¤     
;  s#p ¤¤  s    
;  ¤¤¤    #¤¤¤  
;     r   #     
;   ¤zz¤¤¤¤     
; ¤¤            
;     g     s   
;     ¤¤¤¤¤¤¤¤¤ 
;               


(define (problem level5-Astro)
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
  pos-09-00 - location
  pos-10-00 - location
  pos-11-00 - location
  pos-12-00 - location
  pos-13-00 - location
  pos-14-00 - location
  pos-00-01 - location
  pos-01-01 - location
  pos-02-01 - location
  pos-03-01 - location
  pos-04-01 - location
  pos-05-01 - location
  pos-06-01 - location
  pos-07-01 - location
  pos-08-01 - location
  pos-09-01 - location
  pos-10-01 - location
  pos-11-01 - location
  pos-12-01 - location
  pos-13-01 - location
  pos-14-01 - location
  pos-00-02 - location
  pos-01-02 - location
  pos-02-02 - location
  pos-03-02 - location
  pos-04-02 - location
  pos-05-02 - location
  pos-06-02 - location
  pos-07-02 - location
  pos-08-02 - location
  pos-09-02 - location
  pos-10-02 - location
  pos-11-02 - location
  pos-12-02 - location
  pos-13-02 - location
  pos-14-02 - location
  pos-00-03 - location
  pos-01-03 - location
  pos-02-03 - location
  pos-03-03 - location
  pos-04-03 - location
  pos-05-03 - location
  pos-06-03 - location
  pos-07-03 - location
  pos-08-03 - location
  pos-09-03 - location
  pos-10-03 - location
  pos-11-03 - location
  pos-12-03 - location
  pos-13-03 - location
  pos-14-03 - location
  pos-00-04 - location
  pos-01-04 - location
  pos-02-04 - location
  pos-03-04 - location
  pos-04-04 - location
  pos-05-04 - location
  pos-06-04 - location
  pos-07-04 - location
  pos-08-04 - location
  pos-09-04 - location
  pos-10-04 - location
  pos-11-04 - location
  pos-12-04 - location
  pos-13-04 - location
  pos-14-04 - location
  pos-00-05 - location
  pos-01-05 - location
  pos-02-05 - location
  pos-03-05 - location
  pos-04-05 - location
  pos-05-05 - location
  pos-06-05 - location
  pos-07-05 - location
  pos-08-05 - location
  pos-09-05 - location
  pos-10-05 - location
  pos-11-05 - location
  pos-12-05 - location
  pos-13-05 - location
  pos-14-05 - location
  pos-00-06 - location
  pos-01-06 - location
  pos-02-06 - location
  pos-03-06 - location
  pos-04-06 - location
  pos-05-06 - location
  pos-06-06 - location
  pos-07-06 - location
  pos-08-06 - location
  pos-09-06 - location
  pos-10-06 - location
  pos-11-06 - location
  pos-12-06 - location
  pos-13-06 - location
  pos-14-06 - location
  pos-00-07 - location
  pos-01-07 - location
  pos-02-07 - location
  pos-03-07 - location
  pos-04-07 - location
  pos-05-07 - location
  pos-06-07 - location
  pos-07-07 - location
  pos-08-07 - location
  pos-09-07 - location
  pos-10-07 - location
  pos-11-07 - location
  pos-12-07 - location
  pos-13-07 - location
  pos-14-07 - location
  pos-00-08 - location
  pos-01-08 - location
  pos-02-08 - location
  pos-03-08 - location
  pos-04-08 - location
  pos-05-08 - location
  pos-06-08 - location
  pos-07-08 - location
  pos-08-08 - location
  pos-09-08 - location
  pos-10-08 - location
  pos-11-08 - location
  pos-12-08 - location
  pos-13-08 - location
  pos-14-08 - location
  pos-00-09 - location
  pos-01-09 - location
  pos-02-09 - location
  pos-03-09 - location
  pos-04-09 - location
  pos-05-09 - location
  pos-06-09 - location
  pos-07-09 - location
  pos-08-09 - location
  pos-09-09 - location
  pos-10-09 - location
  pos-11-09 - location
  pos-12-09 - location
  pos-13-09 - location
  pos-14-09 - location
  pos-00-10 - location
  pos-01-10 - location
  pos-02-10 - location
  pos-03-10 - location
  pos-04-10 - location
  pos-05-10 - location
  pos-06-10 - location
  pos-07-10 - location
  pos-08-10 - location
  pos-09-10 - location
  pos-10-10 - location
  pos-11-10 - location
  pos-12-10 - location
  pos-13-10 - location
  pos-14-10 - location
  pos-00-11 - location
  pos-01-11 - location
  pos-02-11 - location
  pos-03-11 - location
  pos-04-11 - location
  pos-05-11 - location
  pos-06-11 - location
  pos-07-11 - location
  pos-08-11 - location
  pos-09-11 - location
  pos-10-11 - location
  pos-11-11 - location
  pos-12-11 - location
  pos-13-11 - location
  pos-14-11 - location
  pos-00-12 - location
  pos-01-12 - location
  pos-02-12 - location
  pos-03-12 - location
  pos-04-12 - location
  pos-05-12 - location
  pos-06-12 - location
  pos-07-12 - location
  pos-08-12 - location
  pos-09-12 - location
  pos-10-12 - location
  pos-11-12 - location
  pos-12-12 - location
  pos-13-12 - location
  pos-14-12 - location
  stone0 - stone
  stone1 - stone
  stone2 - stone
  stone3 - stone
  robot0 - robot
  robot1 - robot
 )
 (:init
  (= (total-cost) 0)
  (at player-01 pos-04-05)
  (alive player-01)
  (ladder pos-03-03)
  (ladder pos-03-05)
  (ladder pos-09-07)
  (ladder pos-03-01)
  (ladder pos-03-02)
  (ladder pos-03-04)
  (ladder pos-09-06)
  (at stone0 pos-10-05)
  (at stone1 pos-11-10)
  (at stone2 pos-05-03)
  (at stone3 pos-02-05)
  (at robot0 pos-05-07)
  (at robot1 pos-05-00)
  (ground purple pos-04-08)
  (ground purple pos-05-08)
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
  (relativ-dir pos-00-06 pos-00-07 down)
  (clear pos-00-07)
  (relativ-dir pos-00-07 pos-01-07 right)
  (relativ-dir pos-00-07 pos-00-06 up)
  (relativ-dir pos-00-07 pos-00-08 down)
  (clear pos-00-08)
  (relativ-dir pos-00-08 pos-01-08 right)
  (relativ-dir pos-00-08 pos-00-07 up)
  (relativ-dir pos-00-08 pos-00-09 down)
  (clear pos-00-09)
  (relativ-dir pos-00-09 pos-01-09 right)
  (relativ-dir pos-00-09 pos-00-08 up)
  (relativ-dir pos-00-09 pos-00-10 down)
  (clear pos-00-10)
  (relativ-dir pos-00-10 pos-01-10 right)
  (relativ-dir pos-00-10 pos-00-09 up)
  (relativ-dir pos-00-10 pos-00-11 down)
  (clear pos-00-11)
  (relativ-dir pos-00-11 pos-01-11 right)
  (relativ-dir pos-00-11 pos-00-10 up)
  (relativ-dir pos-00-11 pos-00-12 down)
  (clear pos-00-12)
  (relativ-dir pos-00-12 pos-01-12 right)
  (relativ-dir pos-00-12 pos-00-11 up)
  (boarder pos-00-12)
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
  (relativ-dir pos-01-06 pos-01-07 down)
  (clear pos-01-07)
  (relativ-dir pos-01-07 pos-00-07 left)
  (relativ-dir pos-01-07 pos-02-07 right)
  (relativ-dir pos-01-07 pos-01-06 up)
  (relativ-dir pos-01-07 pos-01-08 down)
  (clear pos-01-08)
  (relativ-dir pos-01-08 pos-00-08 left)
  (relativ-dir pos-01-08 pos-02-08 right)
  (relativ-dir pos-01-08 pos-01-07 up)
  (relativ-dir pos-01-08 pos-01-09 down)
  (relativ-dir pos-01-09 pos-00-09 left)
  (relativ-dir pos-01-09 pos-02-09 right)
  (relativ-dir pos-01-09 pos-01-08 up)
  (relativ-dir pos-01-09 pos-01-10 down)
  (clear pos-01-10)
  (relativ-dir pos-01-10 pos-00-10 left)
  (relativ-dir pos-01-10 pos-02-10 right)
  (relativ-dir pos-01-10 pos-01-09 up)
  (relativ-dir pos-01-10 pos-01-11 down)
  (clear pos-01-11)
  (relativ-dir pos-01-11 pos-00-11 left)
  (relativ-dir pos-01-11 pos-02-11 right)
  (relativ-dir pos-01-11 pos-01-10 up)
  (relativ-dir pos-01-11 pos-01-12 down)
  (clear pos-01-12)
  (relativ-dir pos-01-12 pos-00-12 left)
  (relativ-dir pos-01-12 pos-02-12 right)
  (relativ-dir pos-01-12 pos-01-11 up)
  (boarder pos-01-12)
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
  (relativ-dir pos-02-04 pos-01-04 left)
  (relativ-dir pos-02-04 pos-03-04 right)
  (relativ-dir pos-02-04 pos-02-03 up)
  (relativ-dir pos-02-04 pos-02-05 down)
  (relativ-dir pos-02-05 pos-01-05 left)
  (relativ-dir pos-02-05 pos-03-05 right)
  (relativ-dir pos-02-05 pos-02-04 up)
  (relativ-dir pos-02-05 pos-02-06 down)
  (relativ-dir pos-02-06 pos-01-06 left)
  (relativ-dir pos-02-06 pos-03-06 right)
  (relativ-dir pos-02-06 pos-02-05 up)
  (relativ-dir pos-02-06 pos-02-07 down)
  (clear pos-02-07)
  (relativ-dir pos-02-07 pos-01-07 left)
  (relativ-dir pos-02-07 pos-03-07 right)
  (relativ-dir pos-02-07 pos-02-06 up)
  (relativ-dir pos-02-07 pos-02-08 down)
  (clear pos-02-08)
  (relativ-dir pos-02-08 pos-01-08 left)
  (relativ-dir pos-02-08 pos-03-08 right)
  (relativ-dir pos-02-08 pos-02-07 up)
  (relativ-dir pos-02-08 pos-02-09 down)
  (relativ-dir pos-02-09 pos-01-09 left)
  (relativ-dir pos-02-09 pos-03-09 right)
  (relativ-dir pos-02-09 pos-02-08 up)
  (relativ-dir pos-02-09 pos-02-10 down)
  (clear pos-02-10)
  (relativ-dir pos-02-10 pos-01-10 left)
  (relativ-dir pos-02-10 pos-03-10 right)
  (relativ-dir pos-02-10 pos-02-09 up)
  (relativ-dir pos-02-10 pos-02-11 down)
  (clear pos-02-11)
  (relativ-dir pos-02-11 pos-01-11 left)
  (relativ-dir pos-02-11 pos-03-11 right)
  (relativ-dir pos-02-11 pos-02-10 up)
  (relativ-dir pos-02-11 pos-02-12 down)
  (clear pos-02-12)
  (relativ-dir pos-02-12 pos-01-12 left)
  (relativ-dir pos-02-12 pos-03-12 right)
  (relativ-dir pos-02-12 pos-02-11 up)
  (boarder pos-02-12)
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
  (relativ-dir pos-03-06 pos-02-06 left)
  (relativ-dir pos-03-06 pos-04-06 right)
  (relativ-dir pos-03-06 pos-03-05 up)
  (relativ-dir pos-03-06 pos-03-07 down)
  (clear pos-03-07)
  (relativ-dir pos-03-07 pos-02-07 left)
  (relativ-dir pos-03-07 pos-04-07 right)
  (relativ-dir pos-03-07 pos-03-06 up)
  (relativ-dir pos-03-07 pos-03-08 down)
  (relativ-dir pos-03-08 pos-02-08 left)
  (relativ-dir pos-03-08 pos-04-08 right)
  (relativ-dir pos-03-08 pos-03-07 up)
  (relativ-dir pos-03-08 pos-03-09 down)
  (clear pos-03-09)
  (relativ-dir pos-03-09 pos-02-09 left)
  (relativ-dir pos-03-09 pos-04-09 right)
  (relativ-dir pos-03-09 pos-03-08 up)
  (relativ-dir pos-03-09 pos-03-10 down)
  (clear pos-03-10)
  (relativ-dir pos-03-10 pos-02-10 left)
  (relativ-dir pos-03-10 pos-04-10 right)
  (relativ-dir pos-03-10 pos-03-09 up)
  (relativ-dir pos-03-10 pos-03-11 down)
  (clear pos-03-11)
  (relativ-dir pos-03-11 pos-02-11 left)
  (relativ-dir pos-03-11 pos-04-11 right)
  (relativ-dir pos-03-11 pos-03-10 up)
  (relativ-dir pos-03-11 pos-03-12 down)
  (clear pos-03-12)
  (relativ-dir pos-03-12 pos-02-12 left)
  (relativ-dir pos-03-12 pos-04-12 right)
  (relativ-dir pos-03-12 pos-03-11 up)
  (boarder pos-03-12)
  (clear pos-04-00)
  (relativ-dir pos-04-00 pos-03-00 left)
  (relativ-dir pos-04-00 pos-05-00 right)
  (relativ-dir pos-04-00 pos-04-01 down)
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
  (relativ-dir pos-04-04 pos-03-04 left)
  (relativ-dir pos-04-04 pos-05-04 right)
  (relativ-dir pos-04-04 pos-04-03 up)
  (relativ-dir pos-04-04 pos-04-05 down)
  (relativ-dir pos-04-05 pos-03-05 left)
  (relativ-dir pos-04-05 pos-05-05 right)
  (relativ-dir pos-04-05 pos-04-04 up)
  (relativ-dir pos-04-05 pos-04-06 down)
  (relativ-dir pos-04-06 pos-03-06 left)
  (relativ-dir pos-04-06 pos-05-06 right)
  (relativ-dir pos-04-06 pos-04-05 up)
  (relativ-dir pos-04-06 pos-04-07 down)
  (clear pos-04-07)
  (relativ-dir pos-04-07 pos-03-07 left)
  (relativ-dir pos-04-07 pos-05-07 right)
  (relativ-dir pos-04-07 pos-04-06 up)
  (relativ-dir pos-04-07 pos-04-08 down)
  (relativ-dir pos-04-08 pos-03-08 left)
  (relativ-dir pos-04-08 pos-05-08 right)
  (relativ-dir pos-04-08 pos-04-07 up)
  (relativ-dir pos-04-08 pos-04-09 down)
  (clear pos-04-09)
  (relativ-dir pos-04-09 pos-03-09 left)
  (relativ-dir pos-04-09 pos-05-09 right)
  (relativ-dir pos-04-09 pos-04-08 up)
  (relativ-dir pos-04-09 pos-04-10 down)
  (clear pos-04-10)
  (relativ-dir pos-04-10 pos-03-10 left)
  (relativ-dir pos-04-10 pos-05-10 right)
  (relativ-dir pos-04-10 pos-04-09 up)
  (relativ-dir pos-04-10 pos-04-11 down)
  (clear pos-04-11)
  (relativ-dir pos-04-11 pos-03-11 left)
  (relativ-dir pos-04-11 pos-05-11 right)
  (relativ-dir pos-04-11 pos-04-10 up)
  (relativ-dir pos-04-11 pos-04-12 down)
  (clear pos-04-12)
  (relativ-dir pos-04-12 pos-03-12 left)
  (relativ-dir pos-04-12 pos-05-12 right)
  (relativ-dir pos-04-12 pos-04-11 up)
  (boarder pos-04-12)
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
  (relativ-dir pos-05-03 pos-04-03 left)
  (relativ-dir pos-05-03 pos-06-03 right)
  (relativ-dir pos-05-03 pos-05-02 up)
  (relativ-dir pos-05-03 pos-05-04 down)
  (relativ-dir pos-05-04 pos-04-04 left)
  (relativ-dir pos-05-04 pos-06-04 right)
  (relativ-dir pos-05-04 pos-05-03 up)
  (relativ-dir pos-05-04 pos-05-05 down)
  (clear pos-05-05)
  (relativ-dir pos-05-05 pos-04-05 left)
  (relativ-dir pos-05-05 pos-06-05 right)
  (relativ-dir pos-05-05 pos-05-04 up)
  (relativ-dir pos-05-05 pos-05-06 down)
  (clear pos-05-06)
  (relativ-dir pos-05-06 pos-04-06 left)
  (relativ-dir pos-05-06 pos-06-06 right)
  (relativ-dir pos-05-06 pos-05-05 up)
  (relativ-dir pos-05-06 pos-05-07 down)
  (relativ-dir pos-05-07 pos-04-07 left)
  (relativ-dir pos-05-07 pos-06-07 right)
  (relativ-dir pos-05-07 pos-05-06 up)
  (relativ-dir pos-05-07 pos-05-08 down)
  (relativ-dir pos-05-08 pos-04-08 left)
  (relativ-dir pos-05-08 pos-06-08 right)
  (relativ-dir pos-05-08 pos-05-07 up)
  (relativ-dir pos-05-08 pos-05-09 down)
  (clear pos-05-09)
  (relativ-dir pos-05-09 pos-04-09 left)
  (relativ-dir pos-05-09 pos-06-09 right)
  (relativ-dir pos-05-09 pos-05-08 up)
  (relativ-dir pos-05-09 pos-05-10 down)
  (clear pos-05-10)
  (relativ-dir pos-05-10 pos-04-10 left)
  (relativ-dir pos-05-10 pos-06-10 right)
  (relativ-dir pos-05-10 pos-05-09 up)
  (relativ-dir pos-05-10 pos-05-11 down)
  (relativ-dir pos-05-11 pos-04-11 left)
  (relativ-dir pos-05-11 pos-06-11 right)
  (relativ-dir pos-05-11 pos-05-10 up)
  (relativ-dir pos-05-11 pos-05-12 down)
  (clear pos-05-12)
  (relativ-dir pos-05-12 pos-04-12 left)
  (relativ-dir pos-05-12 pos-06-12 right)
  (relativ-dir pos-05-12 pos-05-11 up)
  (boarder pos-05-12)
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
  (relativ-dir pos-06-05 pos-05-05 left)
  (relativ-dir pos-06-05 pos-07-05 right)
  (relativ-dir pos-06-05 pos-06-04 up)
  (relativ-dir pos-06-05 pos-06-06 down)
  (clear pos-06-06)
  (relativ-dir pos-06-06 pos-05-06 left)
  (relativ-dir pos-06-06 pos-07-06 right)
  (relativ-dir pos-06-06 pos-06-05 up)
  (relativ-dir pos-06-06 pos-06-07 down)
  (clear pos-06-07)
  (relativ-dir pos-06-07 pos-05-07 left)
  (relativ-dir pos-06-07 pos-07-07 right)
  (relativ-dir pos-06-07 pos-06-06 up)
  (relativ-dir pos-06-07 pos-06-08 down)
  (relativ-dir pos-06-08 pos-05-08 left)
  (relativ-dir pos-06-08 pos-07-08 right)
  (relativ-dir pos-06-08 pos-06-07 up)
  (relativ-dir pos-06-08 pos-06-09 down)
  (clear pos-06-09)
  (relativ-dir pos-06-09 pos-05-09 left)
  (relativ-dir pos-06-09 pos-07-09 right)
  (relativ-dir pos-06-09 pos-06-08 up)
  (relativ-dir pos-06-09 pos-06-10 down)
  (clear pos-06-10)
  (relativ-dir pos-06-10 pos-05-10 left)
  (relativ-dir pos-06-10 pos-07-10 right)
  (relativ-dir pos-06-10 pos-06-09 up)
  (relativ-dir pos-06-10 pos-06-11 down)
  (relativ-dir pos-06-11 pos-05-11 left)
  (relativ-dir pos-06-11 pos-07-11 right)
  (relativ-dir pos-06-11 pos-06-10 up)
  (relativ-dir pos-06-11 pos-06-12 down)
  (clear pos-06-12)
  (relativ-dir pos-06-12 pos-05-12 left)
  (relativ-dir pos-06-12 pos-07-12 right)
  (relativ-dir pos-06-12 pos-06-11 up)
  (boarder pos-06-12)
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
  (relativ-dir pos-07-04 pos-06-04 left)
  (relativ-dir pos-07-04 pos-08-04 right)
  (relativ-dir pos-07-04 pos-07-03 up)
  (relativ-dir pos-07-04 pos-07-05 down)
  (relativ-dir pos-07-05 pos-06-05 left)
  (relativ-dir pos-07-05 pos-08-05 right)
  (relativ-dir pos-07-05 pos-07-04 up)
  (relativ-dir pos-07-05 pos-07-06 down)
  (clear pos-07-06)
  (relativ-dir pos-07-06 pos-06-06 left)
  (relativ-dir pos-07-06 pos-08-06 right)
  (relativ-dir pos-07-06 pos-07-05 up)
  (relativ-dir pos-07-06 pos-07-07 down)
  (clear pos-07-07)
  (relativ-dir pos-07-07 pos-06-07 left)
  (relativ-dir pos-07-07 pos-08-07 right)
  (relativ-dir pos-07-07 pos-07-06 up)
  (relativ-dir pos-07-07 pos-07-08 down)
  (relativ-dir pos-07-08 pos-06-08 left)
  (relativ-dir pos-07-08 pos-08-08 right)
  (relativ-dir pos-07-08 pos-07-07 up)
  (relativ-dir pos-07-08 pos-07-09 down)
  (clear pos-07-09)
  (relativ-dir pos-07-09 pos-06-09 left)
  (relativ-dir pos-07-09 pos-08-09 right)
  (relativ-dir pos-07-09 pos-07-08 up)
  (relativ-dir pos-07-09 pos-07-10 down)
  (clear pos-07-10)
  (relativ-dir pos-07-10 pos-06-10 left)
  (relativ-dir pos-07-10 pos-08-10 right)
  (relativ-dir pos-07-10 pos-07-09 up)
  (relativ-dir pos-07-10 pos-07-11 down)
  (relativ-dir pos-07-11 pos-06-11 left)
  (relativ-dir pos-07-11 pos-08-11 right)
  (relativ-dir pos-07-11 pos-07-10 up)
  (relativ-dir pos-07-11 pos-07-12 down)
  (clear pos-07-12)
  (relativ-dir pos-07-12 pos-06-12 left)
  (relativ-dir pos-07-12 pos-08-12 right)
  (relativ-dir pos-07-12 pos-07-11 up)
  (boarder pos-07-12)
  (clear pos-08-00)
  (relativ-dir pos-08-00 pos-07-00 left)
  (relativ-dir pos-08-00 pos-09-00 right)
  (relativ-dir pos-08-00 pos-08-01 down)
  (clear pos-08-01)
  (relativ-dir pos-08-01 pos-07-01 left)
  (relativ-dir pos-08-01 pos-09-01 right)
  (relativ-dir pos-08-01 pos-08-00 up)
  (relativ-dir pos-08-01 pos-08-02 down)
  (clear pos-08-02)
  (relativ-dir pos-08-02 pos-07-02 left)
  (relativ-dir pos-08-02 pos-09-02 right)
  (relativ-dir pos-08-02 pos-08-01 up)
  (relativ-dir pos-08-02 pos-08-03 down)
  (clear pos-08-03)
  (relativ-dir pos-08-03 pos-07-03 left)
  (relativ-dir pos-08-03 pos-09-03 right)
  (relativ-dir pos-08-03 pos-08-02 up)
  (relativ-dir pos-08-03 pos-08-04 down)
  (relativ-dir pos-08-04 pos-07-04 left)
  (relativ-dir pos-08-04 pos-09-04 right)
  (relativ-dir pos-08-04 pos-08-03 up)
  (relativ-dir pos-08-04 pos-08-05 down)
  (clear pos-08-05)
  (relativ-dir pos-08-05 pos-07-05 left)
  (relativ-dir pos-08-05 pos-09-05 right)
  (relativ-dir pos-08-05 pos-08-04 up)
  (relativ-dir pos-08-05 pos-08-06 down)
  (clear pos-08-06)
  (relativ-dir pos-08-06 pos-07-06 left)
  (relativ-dir pos-08-06 pos-09-06 right)
  (relativ-dir pos-08-06 pos-08-05 up)
  (relativ-dir pos-08-06 pos-08-07 down)
  (clear pos-08-07)
  (relativ-dir pos-08-07 pos-07-07 left)
  (relativ-dir pos-08-07 pos-09-07 right)
  (relativ-dir pos-08-07 pos-08-06 up)
  (relativ-dir pos-08-07 pos-08-08 down)
  (relativ-dir pos-08-08 pos-07-08 left)
  (relativ-dir pos-08-08 pos-09-08 right)
  (relativ-dir pos-08-08 pos-08-07 up)
  (relativ-dir pos-08-08 pos-08-09 down)
  (clear pos-08-09)
  (relativ-dir pos-08-09 pos-07-09 left)
  (relativ-dir pos-08-09 pos-09-09 right)
  (relativ-dir pos-08-09 pos-08-08 up)
  (relativ-dir pos-08-09 pos-08-10 down)
  (clear pos-08-10)
  (relativ-dir pos-08-10 pos-07-10 left)
  (relativ-dir pos-08-10 pos-09-10 right)
  (relativ-dir pos-08-10 pos-08-09 up)
  (relativ-dir pos-08-10 pos-08-11 down)
  (relativ-dir pos-08-11 pos-07-11 left)
  (relativ-dir pos-08-11 pos-09-11 right)
  (relativ-dir pos-08-11 pos-08-10 up)
  (relativ-dir pos-08-11 pos-08-12 down)
  (clear pos-08-12)
  (relativ-dir pos-08-12 pos-07-12 left)
  (relativ-dir pos-08-12 pos-09-12 right)
  (relativ-dir pos-08-12 pos-08-11 up)
  (boarder pos-08-12)
  (clear pos-09-00)
  (relativ-dir pos-09-00 pos-08-00 left)
  (relativ-dir pos-09-00 pos-10-00 right)
  (relativ-dir pos-09-00 pos-09-01 down)
  (clear pos-09-01)
  (relativ-dir pos-09-01 pos-08-01 left)
  (relativ-dir pos-09-01 pos-10-01 right)
  (relativ-dir pos-09-01 pos-09-00 up)
  (relativ-dir pos-09-01 pos-09-02 down)
  (clear pos-09-02)
  (relativ-dir pos-09-02 pos-08-02 left)
  (relativ-dir pos-09-02 pos-10-02 right)
  (relativ-dir pos-09-02 pos-09-01 up)
  (relativ-dir pos-09-02 pos-09-03 down)
  (clear pos-09-03)
  (relativ-dir pos-09-03 pos-08-03 left)
  (relativ-dir pos-09-03 pos-10-03 right)
  (relativ-dir pos-09-03 pos-09-02 up)
  (relativ-dir pos-09-03 pos-09-04 down)
  (relativ-dir pos-09-04 pos-08-04 left)
  (relativ-dir pos-09-04 pos-10-04 right)
  (relativ-dir pos-09-04 pos-09-03 up)
  (relativ-dir pos-09-04 pos-09-05 down)
  (clear pos-09-05)
  (relativ-dir pos-09-05 pos-08-05 left)
  (relativ-dir pos-09-05 pos-10-05 right)
  (relativ-dir pos-09-05 pos-09-04 up)
  (relativ-dir pos-09-05 pos-09-06 down)
  (clear pos-09-06)
  (relativ-dir pos-09-06 pos-08-06 left)
  (relativ-dir pos-09-06 pos-10-06 right)
  (relativ-dir pos-09-06 pos-09-05 up)
  (relativ-dir pos-09-06 pos-09-07 down)
  (clear pos-09-07)
  (relativ-dir pos-09-07 pos-08-07 left)
  (relativ-dir pos-09-07 pos-10-07 right)
  (relativ-dir pos-09-07 pos-09-06 up)
  (relativ-dir pos-09-07 pos-09-08 down)
  (relativ-dir pos-09-08 pos-08-08 left)
  (relativ-dir pos-09-08 pos-10-08 right)
  (relativ-dir pos-09-08 pos-09-07 up)
  (relativ-dir pos-09-08 pos-09-09 down)
  (clear pos-09-09)
  (relativ-dir pos-09-09 pos-08-09 left)
  (relativ-dir pos-09-09 pos-10-09 right)
  (relativ-dir pos-09-09 pos-09-08 up)
  (relativ-dir pos-09-09 pos-09-10 down)
  (clear pos-09-10)
  (relativ-dir pos-09-10 pos-08-10 left)
  (relativ-dir pos-09-10 pos-10-10 right)
  (relativ-dir pos-09-10 pos-09-09 up)
  (relativ-dir pos-09-10 pos-09-11 down)
  (relativ-dir pos-09-11 pos-08-11 left)
  (relativ-dir pos-09-11 pos-10-11 right)
  (relativ-dir pos-09-11 pos-09-10 up)
  (relativ-dir pos-09-11 pos-09-12 down)
  (clear pos-09-12)
  (relativ-dir pos-09-12 pos-08-12 left)
  (relativ-dir pos-09-12 pos-10-12 right)
  (relativ-dir pos-09-12 pos-09-11 up)
  (boarder pos-09-12)
  (clear pos-10-00)
  (relativ-dir pos-10-00 pos-09-00 left)
  (relativ-dir pos-10-00 pos-11-00 right)
  (relativ-dir pos-10-00 pos-10-01 down)
  (clear pos-10-01)
  (relativ-dir pos-10-01 pos-09-01 left)
  (relativ-dir pos-10-01 pos-11-01 right)
  (relativ-dir pos-10-01 pos-10-00 up)
  (relativ-dir pos-10-01 pos-10-02 down)
  (clear pos-10-02)
  (relativ-dir pos-10-02 pos-09-02 left)
  (relativ-dir pos-10-02 pos-11-02 right)
  (relativ-dir pos-10-02 pos-10-01 up)
  (relativ-dir pos-10-02 pos-10-03 down)
  (clear pos-10-03)
  (relativ-dir pos-10-03 pos-09-03 left)
  (relativ-dir pos-10-03 pos-11-03 right)
  (relativ-dir pos-10-03 pos-10-02 up)
  (relativ-dir pos-10-03 pos-10-04 down)
  (clear pos-10-04)
  (relativ-dir pos-10-04 pos-09-04 left)
  (relativ-dir pos-10-04 pos-11-04 right)
  (relativ-dir pos-10-04 pos-10-03 up)
  (relativ-dir pos-10-04 pos-10-05 down)
  (relativ-dir pos-10-05 pos-09-05 left)
  (relativ-dir pos-10-05 pos-11-05 right)
  (relativ-dir pos-10-05 pos-10-04 up)
  (relativ-dir pos-10-05 pos-10-06 down)
  (relativ-dir pos-10-06 pos-09-06 left)
  (relativ-dir pos-10-06 pos-11-06 right)
  (relativ-dir pos-10-06 pos-10-05 up)
  (relativ-dir pos-10-06 pos-10-07 down)
  (clear pos-10-07)
  (relativ-dir pos-10-07 pos-09-07 left)
  (relativ-dir pos-10-07 pos-11-07 right)
  (relativ-dir pos-10-07 pos-10-06 up)
  (relativ-dir pos-10-07 pos-10-08 down)
  (clear pos-10-08)
  (relativ-dir pos-10-08 pos-09-08 left)
  (relativ-dir pos-10-08 pos-11-08 right)
  (relativ-dir pos-10-08 pos-10-07 up)
  (relativ-dir pos-10-08 pos-10-09 down)
  (clear pos-10-09)
  (relativ-dir pos-10-09 pos-09-09 left)
  (relativ-dir pos-10-09 pos-11-09 right)
  (relativ-dir pos-10-09 pos-10-08 up)
  (relativ-dir pos-10-09 pos-10-10 down)
  (clear pos-10-10)
  (relativ-dir pos-10-10 pos-09-10 left)
  (relativ-dir pos-10-10 pos-11-10 right)
  (relativ-dir pos-10-10 pos-10-09 up)
  (relativ-dir pos-10-10 pos-10-11 down)
  (relativ-dir pos-10-11 pos-09-11 left)
  (relativ-dir pos-10-11 pos-11-11 right)
  (relativ-dir pos-10-11 pos-10-10 up)
  (relativ-dir pos-10-11 pos-10-12 down)
  (clear pos-10-12)
  (relativ-dir pos-10-12 pos-09-12 left)
  (relativ-dir pos-10-12 pos-11-12 right)
  (relativ-dir pos-10-12 pos-10-11 up)
  (boarder pos-10-12)
  (clear pos-11-00)
  (relativ-dir pos-11-00 pos-10-00 left)
  (relativ-dir pos-11-00 pos-12-00 right)
  (relativ-dir pos-11-00 pos-11-01 down)
  (clear pos-11-01)
  (relativ-dir pos-11-01 pos-10-01 left)
  (relativ-dir pos-11-01 pos-12-01 right)
  (relativ-dir pos-11-01 pos-11-00 up)
  (relativ-dir pos-11-01 pos-11-02 down)
  (clear pos-11-02)
  (relativ-dir pos-11-02 pos-10-02 left)
  (relativ-dir pos-11-02 pos-12-02 right)
  (relativ-dir pos-11-02 pos-11-01 up)
  (relativ-dir pos-11-02 pos-11-03 down)
  (clear pos-11-03)
  (relativ-dir pos-11-03 pos-10-03 left)
  (relativ-dir pos-11-03 pos-12-03 right)
  (relativ-dir pos-11-03 pos-11-02 up)
  (relativ-dir pos-11-03 pos-11-04 down)
  (clear pos-11-04)
  (relativ-dir pos-11-04 pos-10-04 left)
  (relativ-dir pos-11-04 pos-12-04 right)
  (relativ-dir pos-11-04 pos-11-03 up)
  (relativ-dir pos-11-04 pos-11-05 down)
  (clear pos-11-05)
  (relativ-dir pos-11-05 pos-10-05 left)
  (relativ-dir pos-11-05 pos-12-05 right)
  (relativ-dir pos-11-05 pos-11-04 up)
  (relativ-dir pos-11-05 pos-11-06 down)
  (relativ-dir pos-11-06 pos-10-06 left)
  (relativ-dir pos-11-06 pos-12-06 right)
  (relativ-dir pos-11-06 pos-11-05 up)
  (relativ-dir pos-11-06 pos-11-07 down)
  (clear pos-11-07)
  (relativ-dir pos-11-07 pos-10-07 left)
  (relativ-dir pos-11-07 pos-12-07 right)
  (relativ-dir pos-11-07 pos-11-06 up)
  (relativ-dir pos-11-07 pos-11-08 down)
  (clear pos-11-08)
  (relativ-dir pos-11-08 pos-10-08 left)
  (relativ-dir pos-11-08 pos-12-08 right)
  (relativ-dir pos-11-08 pos-11-07 up)
  (relativ-dir pos-11-08 pos-11-09 down)
  (clear pos-11-09)
  (relativ-dir pos-11-09 pos-10-09 left)
  (relativ-dir pos-11-09 pos-12-09 right)
  (relativ-dir pos-11-09 pos-11-08 up)
  (relativ-dir pos-11-09 pos-11-10 down)
  (relativ-dir pos-11-10 pos-10-10 left)
  (relativ-dir pos-11-10 pos-12-10 right)
  (relativ-dir pos-11-10 pos-11-09 up)
  (relativ-dir pos-11-10 pos-11-11 down)
  (relativ-dir pos-11-11 pos-10-11 left)
  (relativ-dir pos-11-11 pos-12-11 right)
  (relativ-dir pos-11-11 pos-11-10 up)
  (relativ-dir pos-11-11 pos-11-12 down)
  (clear pos-11-12)
  (relativ-dir pos-11-12 pos-10-12 left)
  (relativ-dir pos-11-12 pos-12-12 right)
  (relativ-dir pos-11-12 pos-11-11 up)
  (boarder pos-11-12)
  (clear pos-12-00)
  (relativ-dir pos-12-00 pos-11-00 left)
  (relativ-dir pos-12-00 pos-13-00 right)
  (relativ-dir pos-12-00 pos-12-01 down)
  (clear pos-12-01)
  (relativ-dir pos-12-01 pos-11-01 left)
  (relativ-dir pos-12-01 pos-13-01 right)
  (relativ-dir pos-12-01 pos-12-00 up)
  (relativ-dir pos-12-01 pos-12-02 down)
  (clear pos-12-02)
  (relativ-dir pos-12-02 pos-11-02 left)
  (relativ-dir pos-12-02 pos-13-02 right)
  (relativ-dir pos-12-02 pos-12-01 up)
  (relativ-dir pos-12-02 pos-12-03 down)
  (clear pos-12-03)
  (relativ-dir pos-12-03 pos-11-03 left)
  (relativ-dir pos-12-03 pos-13-03 right)
  (relativ-dir pos-12-03 pos-12-02 up)
  (relativ-dir pos-12-03 pos-12-04 down)
  (clear pos-12-04)
  (relativ-dir pos-12-04 pos-11-04 left)
  (relativ-dir pos-12-04 pos-13-04 right)
  (relativ-dir pos-12-04 pos-12-03 up)
  (relativ-dir pos-12-04 pos-12-05 down)
  (clear pos-12-05)
  (relativ-dir pos-12-05 pos-11-05 left)
  (relativ-dir pos-12-05 pos-13-05 right)
  (relativ-dir pos-12-05 pos-12-04 up)
  (relativ-dir pos-12-05 pos-12-06 down)
  (relativ-dir pos-12-06 pos-11-06 left)
  (relativ-dir pos-12-06 pos-13-06 right)
  (relativ-dir pos-12-06 pos-12-05 up)
  (relativ-dir pos-12-06 pos-12-07 down)
  (clear pos-12-07)
  (relativ-dir pos-12-07 pos-11-07 left)
  (relativ-dir pos-12-07 pos-13-07 right)
  (relativ-dir pos-12-07 pos-12-06 up)
  (relativ-dir pos-12-07 pos-12-08 down)
  (clear pos-12-08)
  (relativ-dir pos-12-08 pos-11-08 left)
  (relativ-dir pos-12-08 pos-13-08 right)
  (relativ-dir pos-12-08 pos-12-07 up)
  (relativ-dir pos-12-08 pos-12-09 down)
  (clear pos-12-09)
  (relativ-dir pos-12-09 pos-11-09 left)
  (relativ-dir pos-12-09 pos-13-09 right)
  (relativ-dir pos-12-09 pos-12-08 up)
  (relativ-dir pos-12-09 pos-12-10 down)
  (clear pos-12-10)
  (relativ-dir pos-12-10 pos-11-10 left)
  (relativ-dir pos-12-10 pos-13-10 right)
  (relativ-dir pos-12-10 pos-12-09 up)
  (relativ-dir pos-12-10 pos-12-11 down)
  (relativ-dir pos-12-11 pos-11-11 left)
  (relativ-dir pos-12-11 pos-13-11 right)
  (relativ-dir pos-12-11 pos-12-10 up)
  (relativ-dir pos-12-11 pos-12-12 down)
  (clear pos-12-12)
  (relativ-dir pos-12-12 pos-11-12 left)
  (relativ-dir pos-12-12 pos-13-12 right)
  (relativ-dir pos-12-12 pos-12-11 up)
  (boarder pos-12-12)
  (clear pos-13-00)
  (relativ-dir pos-13-00 pos-12-00 left)
  (relativ-dir pos-13-00 pos-14-00 right)
  (relativ-dir pos-13-00 pos-13-01 down)
  (clear pos-13-01)
  (relativ-dir pos-13-01 pos-12-01 left)
  (relativ-dir pos-13-01 pos-14-01 right)
  (relativ-dir pos-13-01 pos-13-00 up)
  (relativ-dir pos-13-01 pos-13-02 down)
  (clear pos-13-02)
  (relativ-dir pos-13-02 pos-12-02 left)
  (relativ-dir pos-13-02 pos-14-02 right)
  (relativ-dir pos-13-02 pos-13-01 up)
  (relativ-dir pos-13-02 pos-13-03 down)
  (clear pos-13-03)
  (relativ-dir pos-13-03 pos-12-03 left)
  (relativ-dir pos-13-03 pos-14-03 right)
  (relativ-dir pos-13-03 pos-13-02 up)
  (relativ-dir pos-13-03 pos-13-04 down)
  (clear pos-13-04)
  (relativ-dir pos-13-04 pos-12-04 left)
  (relativ-dir pos-13-04 pos-14-04 right)
  (relativ-dir pos-13-04 pos-13-03 up)
  (relativ-dir pos-13-04 pos-13-05 down)
  (clear pos-13-05)
  (relativ-dir pos-13-05 pos-12-05 left)
  (relativ-dir pos-13-05 pos-14-05 right)
  (relativ-dir pos-13-05 pos-13-04 up)
  (relativ-dir pos-13-05 pos-13-06 down)
  (clear pos-13-06)
  (relativ-dir pos-13-06 pos-12-06 left)
  (relativ-dir pos-13-06 pos-14-06 right)
  (relativ-dir pos-13-06 pos-13-05 up)
  (relativ-dir pos-13-06 pos-13-07 down)
  (clear pos-13-07)
  (relativ-dir pos-13-07 pos-12-07 left)
  (relativ-dir pos-13-07 pos-14-07 right)
  (relativ-dir pos-13-07 pos-13-06 up)
  (relativ-dir pos-13-07 pos-13-08 down)
  (clear pos-13-08)
  (relativ-dir pos-13-08 pos-12-08 left)
  (relativ-dir pos-13-08 pos-14-08 right)
  (relativ-dir pos-13-08 pos-13-07 up)
  (relativ-dir pos-13-08 pos-13-09 down)
  (clear pos-13-09)
  (relativ-dir pos-13-09 pos-12-09 left)
  (relativ-dir pos-13-09 pos-14-09 right)
  (relativ-dir pos-13-09 pos-13-08 up)
  (relativ-dir pos-13-09 pos-13-10 down)
  (clear pos-13-10)
  (relativ-dir pos-13-10 pos-12-10 left)
  (relativ-dir pos-13-10 pos-14-10 right)
  (relativ-dir pos-13-10 pos-13-09 up)
  (relativ-dir pos-13-10 pos-13-11 down)
  (relativ-dir pos-13-11 pos-12-11 left)
  (relativ-dir pos-13-11 pos-14-11 right)
  (relativ-dir pos-13-11 pos-13-10 up)
  (relativ-dir pos-13-11 pos-13-12 down)
  (clear pos-13-12)
  (relativ-dir pos-13-12 pos-12-12 left)
  (relativ-dir pos-13-12 pos-14-12 right)
  (relativ-dir pos-13-12 pos-13-11 up)
  (boarder pos-13-12)
  (clear pos-14-00)
  (relativ-dir pos-14-00 pos-13-00 left)
  (relativ-dir pos-14-00 pos-14-01 down)
  (clear pos-14-01)
  (relativ-dir pos-14-01 pos-13-01 left)
  (relativ-dir pos-14-01 pos-14-00 up)
  (relativ-dir pos-14-01 pos-14-02 down)
  (clear pos-14-02)
  (relativ-dir pos-14-02 pos-13-02 left)
  (relativ-dir pos-14-02 pos-14-01 up)
  (relativ-dir pos-14-02 pos-14-03 down)
  (clear pos-14-03)
  (relativ-dir pos-14-03 pos-13-03 left)
  (relativ-dir pos-14-03 pos-14-02 up)
  (relativ-dir pos-14-03 pos-14-04 down)
  (clear pos-14-04)
  (relativ-dir pos-14-04 pos-13-04 left)
  (relativ-dir pos-14-04 pos-14-03 up)
  (relativ-dir pos-14-04 pos-14-05 down)
  (clear pos-14-05)
  (relativ-dir pos-14-05 pos-13-05 left)
  (relativ-dir pos-14-05 pos-14-04 up)
  (relativ-dir pos-14-05 pos-14-06 down)
  (clear pos-14-06)
  (relativ-dir pos-14-06 pos-13-06 left)
  (relativ-dir pos-14-06 pos-14-05 up)
  (relativ-dir pos-14-06 pos-14-07 down)
  (clear pos-14-07)
  (relativ-dir pos-14-07 pos-13-07 left)
  (relativ-dir pos-14-07 pos-14-06 up)
  (relativ-dir pos-14-07 pos-14-08 down)
  (clear pos-14-08)
  (relativ-dir pos-14-08 pos-13-08 left)
  (relativ-dir pos-14-08 pos-14-07 up)
  (relativ-dir pos-14-08 pos-14-09 down)
  (clear pos-14-09)
  (relativ-dir pos-14-09 pos-13-09 left)
  (relativ-dir pos-14-09 pos-14-08 up)
  (relativ-dir pos-14-09 pos-14-10 down)
  (clear pos-14-10)
  (relativ-dir pos-14-10 pos-13-10 left)
  (relativ-dir pos-14-10 pos-14-09 up)
  (relativ-dir pos-14-10 pos-14-11 down)
  (clear pos-14-11)
  (relativ-dir pos-14-11 pos-13-11 left)
  (relativ-dir pos-14-11 pos-14-10 up)
  (relativ-dir pos-14-11 pos-14-12 down)
  (clear pos-14-12)
  (relativ-dir pos-14-12 pos-13-12 left)
  (relativ-dir pos-14-12 pos-14-11 up)
  (boarder pos-14-12)
 )
 (:goal (and
   (at player-01 pos-05-10)
   (alive player-01)
  )
 )
 (:metric minimize (total-cost))
)
