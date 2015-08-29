package test;

import static org.junit.Assert.*;

import org.junit.Test;

import world.World;
import world.World.Color;
import world.objects.PhysObject.Direction;
import world.objects.Player;
import world.objects.Robot;
import world.objects.Stone;

public class SimTest {


	@Test
	public void testCreateSimple(){
		World world =setupWorld();
		
		
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 5; y++) {
				if((x==2 && (y==2 || y==3))|| (x==3 && y==3 )  || (x==4 &&  y==3) ){
					assertFalse(""+world.getLocation(x, y),world.isClear(x, y));
				}else{
					assertTrue(""+world.getLocation(x, y),world.isClear(x, y));
				}
			}
		}
		
	}
	
	@Test
	public void testMoveSimple(){
		World world =setupWorld();
		world.playerAction(Direction.right);
		
		world.update();
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 5; y++) {
				if((x==2 &&  y==3)|| (x==3 && y==2 ) || (x==3 && y==3 )  || (x==4 &&  y==3) ){
					assertFalse(""+world.getLocation(x, y),world.isClear(x, y));
				}else{
					assertTrue(""+world.getLocation(x, y),world.isClear(x, y));
				}
			}
		}
		
		world.update();
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 5; y++) {
				if((x==2 &&  y==3)|| (x==3 && y==2 ) || (x==3 && y==3 )  || (x==4 &&  y==3) ){
					assertFalse(""+world.getLocation(x, y),world.isClear(x, y));
				}else{
					assertTrue(""+world.getLocation(x, y),world.isClear(x, y));
				}
			}
		}
		world.playerAction(Direction.right);
		world.update();
		assertTrue(world.getLocation(4, 2)+"",world.isGoal());
		
	}
	
	
	@Test
	public void testFallSimple(){
		World world =setupWorld();
		world.playerAction(Direction.left);
		
		world.update();
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 5; y++) {
				if((x==2 &&  y==3)|| (x==1 && y==2 ) || (x==3 && y==3 )  || (x==4 &&  y==3) ){
					assertFalse(""+world.getLocation(x, y),world.isClear(x, y));
				}else{
					assertTrue(""+world.getLocation(x, y),world.isClear(x, y));
				}
			}
		}
		
		world.update();
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 5; y++) {
				if((x==2 &&  y==3)|| (x==1 && y==3 ) || (x==3 && y==3 )  || (x==4 &&  y==3) ){
					assertFalse(""+world.getLocation(x, y),world.isClear(x, y));
				}else{
					assertTrue(""+world.getLocation(x, y),world.isClear(x, y));
				}
			}
		}
		world.update();
		world.update();
		assertTrue(""+world.getLocation(1, 4),world.isClear(1, 4));
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 5; y++) {
				if((x==2 &&  y==3) || (x==3 && y==3 )  || (x==4 &&  y==3) ){
					assertFalse(""+world.getLocation(x, y),world.isClear(x, y));
				}else{
					assertTrue(""+world.getLocation(x, y),world.isClear(x, y));
				}
			}
		}
	}
	
	
	@Test
	public void testSlideSimple(){
		/**
		 * p  g
		 * ¤c¤¤
		 * @return
		 */
		World world =new World(10,5);
		world.addPlayer(2,2);
		world.addGround(2,3);
		world.addGround(3,3,Color.green);
		world.addGround(4,3);
		world.addGround(5,3);
		world.addGoal(5,2);

		//walk
		world.playerAction(Direction.right);
		world.update();
		
		assertTrue(world.isClear(2, 2));
		assertTrue(""+world.getLocation(3, 2) , world.getLocation(3, 2) instanceof Player);
		
		//slide
		assertFalse(world.playerAction(Direction.left));
		world.update();
		
		assertTrue(""+world.getLocation(2, 2),world.isClear(2, 2));
		assertTrue(""+world.getLocation(3, 2),world.isClear(3, 2));
		assertTrue(""+world.getLocation(4, 2),world.getLocation(4, 2) instanceof Player);
		
		//nothing
		world.update();
		assertTrue(""+world.getLocation(2, 2),world.isClear(2, 2));
		assertTrue(""+world.getLocation(3, 2),world.isClear(3, 2));
		assertTrue(""+world.getLocation(4, 2),world.getLocation(4, 2) instanceof Player);
		
		//walk
		assertTrue(world.playerAction(Direction.right));
		world.update();
		assertTrue(""+world.getLocation(4, 2),world.isClear(4, 2));
		assertTrue(""+world.getLocation(5, 2),world.getLocation(5, 2) instanceof Player);
		assertTrue(world.getLocation(5, 2)+"",world.isGoal());
	}
	
	@Test
	public void testSlideStoneSimple(){
		
		/**
		 *  ps   g 
		 *  ¤¤cc¤¤¤
		 */
		World world =new World(10,5);
		world.addPlayer(2,2);

		world.addGround(2,3);
		world.addGround(3,3);
		world.addStone(3,2);
		world.addGround(4,3,Color.green);
		world.addGround(5,3,Color.green);
		world.addGround(6,3);
		world.addGround(7,3);
		world.addGoal(7,2);
		world.addGround(8,3);
		
	
		//push
		assertTrue(world.playerAction(Direction.right));
		world.update();
		assertTrue(""+world.getLocation(3, 2),world.isClear(3, 2));
		assertTrue(""+world.getLocation(2, 2),world.getLocation(2, 2) instanceof Player);
		assertTrue(""+world.getLocation(4, 2),world.getLocation(4, 2) instanceof Stone);


		//nothing (slide)
		world.update();
		assertTrue(""+world.getLocation(2, 2),world.getLocation(2, 2) instanceof Player);
		assertTrue(""+world.getLocation(4, 2),world.isClear(4, 2));
		assertTrue(""+world.getLocation(5, 2),world.getLocation(5, 2) instanceof Stone);
		
		
		//nothing (stop)
		world.update();
		assertTrue(""+world.getLocation(2, 2),world.getLocation(2, 2) instanceof Player);
		assertTrue(""+world.getLocation(5, 2),world.isClear(5, 2));
		assertTrue(""+world.getLocation(6, 2),world.getLocation(6, 2) instanceof Stone);
		
		
		//nothing
		world.update();
		assertTrue(""+world.getLocation(2, 2),world.getLocation(2, 2) instanceof Player);
		assertTrue(""+world.getLocation(5, 2),world.isClear(5, 2));
		assertTrue(""+world.getLocation(7, 2),world.isClear(7, 2));
		assertTrue(""+world.getLocation(6, 2),world.getLocation(6, 2) instanceof Stone);
		
		//nothing
		world.update();
		assertTrue(""+world.getLocation(2, 2),world.getLocation(2, 2) instanceof Player);
		assertTrue(""+world.getLocation(5, 2),world.isClear(5, 2));
		assertTrue(""+world.getLocation(7, 2),world.isClear(7, 2));
		assertTrue(""+world.getLocation(6, 2),world.getLocation(6, 2) instanceof Stone);
		
	}
	
	
	@Test
	public void testSlideHitSimple(){
		
		/**
		 *   ps  g 
		 *   ¤¤c¤¤
		 */
		World world =new World(12,5);
		world.addPlayer(4,2);

		world.addGround(4,3);
		world.addGround(5,3);
		world.addStone(5,2);
		world.addGround(6,3,Color.green);
		world.addGround(7,3);
		world.addGround(8,3);
		world.addGoal(8,2);
		
	
		//push
		assertTrue(world.playerAction(Direction.right));
		world.update();
		assertTrue(""+world.getLocation(5, 2),world.isClear(5, 2));
		assertTrue(""+world.getLocation(4, 2),world.getLocation(4, 2) instanceof Player);
		assertTrue(""+world.getLocation(6, 2),world.getLocation(6, 2) instanceof Stone);

		
		
		
		//walk
		world.playerAction(Direction.right);
		world.update();
		assertTrue(""+world.getLocation(4, 2),world.isClear(4, 2));
		assertTrue(""+world.getLocation(5, 2),world.getLocation(5, 2) instanceof Player);
		assertTrue(""+world.getLocation(6, 2),world.isClear(6, 2));
		assertTrue(""+world.getLocation(7, 2),world.getLocation(7, 2) instanceof Stone);
		
		
		//walk
		world.playerAction(Direction.right);
		world.update();
		assertTrue(""+world.getLocation(5, 2),world.isClear(5, 2));
		assertTrue(""+world.getLocation(6, 2),world.getLocation(6, 2) instanceof Player);
		assertTrue(""+world.getLocation(7, 2),world.getLocation(7, 2) instanceof Stone);
		
		//slide
		world.update();
		assertTrue(""+world.getLocation(6, 2),world.isClear(6, 2));
		assertTrue(""+world.getLocation(7, 2),world.getLocation(7, 2) instanceof Player);
		assertTrue(""+world.getLocation(8, 2),world.getLocation(8, 2) instanceof Stone);
		
		//nothing
		world.update();
		assertTrue(""+world.getLocation(6, 2),world.isClear(6, 2));
		assertTrue(""+world.getLocation(7, 2),world.getLocation(7, 2) instanceof Player);
		assertTrue(""+world.getLocation(8, 2),world.getLocation(8, 2) instanceof Stone);
		
		//push
		assertTrue(world.playerAction(Direction.right));
		world.update();
		assertTrue(""+world.getLocation(7, 2),world.getLocation(7, 2) instanceof Player);
		assertTrue(""+world.getLocation(8, 2),world.isClear(8, 2));
		assertTrue(""+world.getLocation(9, 2),world.getLocation(9, 2) instanceof Stone);
		
		//walk
		world.playerAction(Direction.right);
		world.update();
		
		assertTrue(world.getLocation(8, 2)+"",world.isGoal());
	}
	
	
	@Test
	public void testRobotSimple(){
		
		/**
		 *   ps  g 
		 *   ¤¤c¤¤
		 *        ¤¤¤
		 */
		World world =new World(13,5);
		world.addPlayer(4,2);

		world.addGround(4,3);
		world.addGround(5,3);
		world.addRobot(5, 2, Direction.left);
		world.addGround(6,3,Color.green);
		world.addGround(7,3);
		world.addGround(8,3);
		world.addGoal(8,2);
		world.addGround(9,4);
		world.addGround(10,4);
		world.addGround(11,4);
		
		assertTrue(""+world.getLocation(4, 2),world.getLocation(4, 2) instanceof Player);
		assertTrue(""+world.getLocation(5, 2),world.getLocation(5, 2) instanceof Robot);
		//push
		assertTrue(world.playerAction(Direction.right));
		world.update();
		assertTrue(""+world.getLocation(5, 2),world.isClear(5, 2));
		assertTrue(""+world.getLocation(4, 2),world.getLocation(4, 2) instanceof Player);
		assertTrue(""+world.getLocation(6, 2),world.getLocation(6, 2) instanceof Robot);

		
		
		
		//nothing
		world.update();
		assertTrue(""+world.getLocation(6, 2),world.isClear(6, 2));
		assertTrue(""+world.getLocation(7, 2),world.getLocation(7, 2) instanceof Robot);
		
		
		//nothing
		world.update();
		assertTrue(""+world.getLocation(6, 2),world.isClear(6, 2));
		assertTrue(""+world.getLocation(8, 2),world.getLocation(8, 2) instanceof Robot);
		
		//nothing
		world.update();
		assertTrue(""+world.getLocation(8, 2),world.isClear(8, 2));
		assertTrue(""+world.getLocation(9, 2),world.getLocation(9, 2) instanceof Robot);
		
		//nothing
		world.update();
		assertTrue(""+world.getLocation(9, 2),world.isClear(9, 2));
		assertTrue(""+world.getLocation(9, 3),world.getLocation(9, 3) instanceof Robot);
		//nothing
		world.update();
		assertTrue(""+world.getLocation(9, 3),world.isClear(9, 3));
		assertTrue(""+world.getLocation(10, 3),world.getLocation(10, 3) instanceof Robot);

	}
	/**
	 * p g
	 * ¤¤¤
	 * @return
	 */
	public World setupWorld(){
		World world =new World(10,5);
		world.addPlayer(2,2);
		world.addGround(2,3);
		world.addGround(3,3);
		world.addGround(4,3);
		world.addGoal(4,2);
		
		return world;
	}
	

}
