;               
;       l p  wg 
;  ¤b   ¤¤¤¤¤¤¤ 
;  ¤¤¤¤¤        
;               
;               


(define (problem prob10-Astro)
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
  robot0 - robot
 )
 (:init
  (= (total-cost) 0)
  (at player-01 pos-09-01)
  (at robot0 pos-07-01)
  (facing robot0 left)
  (ground brown pos-02-02)
  (ground brown pos-02-03)
  (ground brown pos-03-03)
  (ground brown pos-04-03)
  (ground brown pos-05-03)
  (ground brown pos-06-03)
  (ground brown pos-07-02)
  (ground brown pos-08-02)
  (ground brown pos-09-02)
  (ground brown pos-10-02)
  (ground brown pos-11-02)
  (ground brown pos-12-02)
  (ground brown pos-13-02)
  (button red pos-03-02)
  (clear pos-03-02)
  (gate red pos-12-01)
  (closed pos-12-01)
  (clear pos-12-01)
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
  (boarder pos-00-05)
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
  (boarder pos-01-05)
  (clear pos-02-00)
  (relativ-dir pos-02-00 pos-01-00 left)
  (relativ-dir pos-02-00 pos-03-00 right)
  (relativ-dir pos-02-00 pos-02-01 down)
  (clear pos-02-01)
  (relativ-dir pos-02-01 pos-01-01 left)
  (relativ-dir pos-02-01 pos-03-01 right)
  (relativ-dir pos-02-01 pos-02-00 up)
  (relativ-dir pos-02-01 pos-02-02 down)
  (relativ-dir pos-02-02 pos-01-02 left)
  (relativ-dir pos-02-02 pos-03-02 right)
  (relativ-dir pos-02-02 pos-02-01 up)
  (relativ-dir pos-02-02 pos-02-03 down)
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
  (boarder pos-02-05)
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
  (boarder pos-03-05)
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
  (boarder pos-04-05)
  (clear pos-05-00)
  (relativ-dir pos-05-00 pos-04-00 left)
  (relativ-dir pos-05-00 pos-06-00 right)
  (relativ-dir pos-05-00 pos-05-01 down)
  (clear pos-05-01)
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
  (clear pos-05-04)
  (relativ-dir pos-05-04 pos-04-04 left)
  (relativ-dir pos-05-04 pos-06-04 right)
  (relativ-dir pos-05-04 pos-05-03 up)
  (relativ-dir pos-05-04 pos-05-05 down)
  (clear pos-05-05)
  (relativ-dir pos-05-05 pos-04-05 left)
  (relativ-dir pos-05-05 pos-06-05 right)
  (relativ-dir pos-05-05 pos-05-04 up)
  (boarder pos-05-05)
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
  (boarder pos-06-05)
  (clear pos-07-00)
  (relativ-dir pos-07-00 pos-06-00 left)
  (relativ-dir pos-07-00 pos-08-00 right)
  (relativ-dir pos-07-00 pos-07-01 down)
  (relativ-dir pos-07-01 pos-06-01 left)
  (relativ-dir pos-07-01 pos-08-01 right)
  (relativ-dir pos-07-01 pos-07-00 up)
  (relativ-dir pos-07-01 pos-07-02 down)
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
  (boarder pos-07-05)
  (clear pos-08-00)
  (relativ-dir pos-08-00 pos-07-00 left)
  (relativ-dir pos-08-00 pos-09-00 right)
  (relativ-dir pos-08-00 pos-08-01 down)
  (clear pos-08-01)
  (relativ-dir pos-08-01 pos-07-01 left)
  (relativ-dir pos-08-01 pos-09-01 right)
  (relativ-dir pos-08-01 pos-08-00 up)
  (relativ-dir pos-08-01 pos-08-02 down)
  (relativ-dir pos-08-02 pos-07-02 left)
  (relativ-dir pos-08-02 pos-09-02 right)
  (relativ-dir pos-08-02 pos-08-01 up)
  (relativ-dir pos-08-02 pos-08-03 down)
  (clear pos-08-03)
  (relativ-dir pos-08-03 pos-07-03 left)
  (relativ-dir pos-08-03 pos-09-03 right)
  (relativ-dir pos-08-03 pos-08-02 up)
  (relativ-dir pos-08-03 pos-08-04 down)
  (clear pos-08-04)
  (relativ-dir pos-08-04 pos-07-04 left)
  (relativ-dir pos-08-04 pos-09-04 right)
  (relativ-dir pos-08-04 pos-08-03 up)
  (relativ-dir pos-08-04 pos-08-05 down)
  (clear pos-08-05)
  (relativ-dir pos-08-05 pos-07-05 left)
  (relativ-dir pos-08-05 pos-09-05 right)
  (relativ-dir pos-08-05 pos-08-04 up)
  (boarder pos-08-05)
  (clear pos-09-00)
  (relativ-dir pos-09-00 pos-08-00 left)
  (relativ-dir pos-09-00 pos-10-00 right)
  (relativ-dir pos-09-00 pos-09-01 down)
  (relativ-dir pos-09-01 pos-08-01 left)
  (relativ-dir pos-09-01 pos-10-01 right)
  (relativ-dir pos-09-01 pos-09-00 up)
  (relativ-dir pos-09-01 pos-09-02 down)
  (relativ-dir pos-09-02 pos-08-02 left)
  (relativ-dir pos-09-02 pos-10-02 right)
  (relativ-dir pos-09-02 pos-09-01 up)
  (relativ-dir pos-09-02 pos-09-03 down)
  (clear pos-09-03)
  (relativ-dir pos-09-03 pos-08-03 left)
  (relativ-dir pos-09-03 pos-10-03 right)
  (relativ-dir pos-09-03 pos-09-02 up)
  (relativ-dir pos-09-03 pos-09-04 down)
  (clear pos-09-04)
  (relativ-dir pos-09-04 pos-08-04 left)
  (relativ-dir pos-09-04 pos-10-04 right)
  (relativ-dir pos-09-04 pos-09-03 up)
  (relativ-dir pos-09-04 pos-09-05 down)
  (clear pos-09-05)
  (relativ-dir pos-09-05 pos-08-05 left)
  (relativ-dir pos-09-05 pos-10-05 right)
  (relativ-dir pos-09-05 pos-09-04 up)
  (boarder pos-09-05)
  (clear pos-10-00)
  (relativ-dir pos-10-00 pos-09-00 left)
  (relativ-dir pos-10-00 pos-11-00 right)
  (relativ-dir pos-10-00 pos-10-01 down)
  (clear pos-10-01)
  (relativ-dir pos-10-01 pos-09-01 left)
  (relativ-dir pos-10-01 pos-11-01 right)
  (relativ-dir pos-10-01 pos-10-00 up)
  (relativ-dir pos-10-01 pos-10-02 down)
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
  (clear pos-10-05)
  (relativ-dir pos-10-05 pos-09-05 left)
  (relativ-dir pos-10-05 pos-11-05 right)
  (relativ-dir pos-10-05 pos-10-04 up)
  (boarder pos-10-05)
  (clear pos-11-00)
  (relativ-dir pos-11-00 pos-10-00 left)
  (relativ-dir pos-11-00 pos-12-00 right)
  (relativ-dir pos-11-00 pos-11-01 down)
  (clear pos-11-01)
  (relativ-dir pos-11-01 pos-10-01 left)
  (relativ-dir pos-11-01 pos-12-01 right)
  (relativ-dir pos-11-01 pos-11-00 up)
  (relativ-dir pos-11-01 pos-11-02 down)
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
  (boarder pos-11-05)
  (clear pos-12-00)
  (relativ-dir pos-12-00 pos-11-00 left)
  (relativ-dir pos-12-00 pos-13-00 right)
  (relativ-dir pos-12-00 pos-12-01 down)
  (relativ-dir pos-12-01 pos-11-01 left)
  (relativ-dir pos-12-01 pos-13-01 right)
  (relativ-dir pos-12-01 pos-12-00 up)
  (relativ-dir pos-12-01 pos-12-02 down)
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
  (boarder pos-12-05)
  (clear pos-13-00)
  (relativ-dir pos-13-00 pos-12-00 left)
  (relativ-dir pos-13-00 pos-14-00 right)
  (relativ-dir pos-13-00 pos-13-01 down)
  (clear pos-13-01)
  (relativ-dir pos-13-01 pos-12-01 left)
  (relativ-dir pos-13-01 pos-14-01 right)
  (relativ-dir pos-13-01 pos-13-00 up)
  (relativ-dir pos-13-01 pos-13-02 down)
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
  (boarder pos-13-05)
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
  (boarder pos-14-05)
 )
 (:goal (and
   (at player-01 pos-13-01)
  )
 )
 (:metric minimize (total-cost))
)
