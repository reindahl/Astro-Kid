package test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import world.World;
import world.objects.PhysObject.Direction;
import world.objects.Player;
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
		World world =setupWorldSlideStone();
		world.playerAction(Direction.right);
		world.update();
		
		assertTrue(world.isClear(2, 2));
		assertTrue(world.getLocation(3, 2) instanceof Player);
		
		//slide
		world.playerAction(Direction.left);
		world.update();
		
		assertTrue(""+world.getLocation(2, 2),world.isClear(2, 2));
		assertTrue(""+world.getLocation(3, 2),world.isClear(3, 2));
		assertTrue(""+world.getLocation(4, 2),world.getLocation(4, 2) instanceof Player);
		
		//push
		assertTrue(world.playerAction(Direction.right));
		world.update();
		
		System.out.println(""+world.getLocation(4, 2));
		System.out.println(""+world.getLocation(5, 2));
		System.out.println(""+world.getLocation(6, 2));
		assertTrue(""+world.getLocation(6, 2),world.getLocation(6, 2) instanceof Stone);
		assertTrue(""+world.getLocation(5, 2),world.isClear(5, 2));
		assertTrue(""+world.getLocation(4, 2),world.getLocation(4, 2) instanceof Player);
		
		
		//walk
		world.playerAction(Direction.right);
		world.update();
		
		//slide
		world.update();
		
		//push
		world.playerAction(Direction.right);
		world.update();
		
		//walk
		world.playerAction(Direction.right);
		world.update();
		
		assertTrue(world.getLocation(4, 2)+"",world.isGoal());
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
	
	/**
	 * p  s  g 
	 * ¤c¤¤c¤¤
	 * @return
	 */
	public World setupWorldSlideStone(){
		World world =new World(10,5);
		world.addPlayer(2,2);
		world.addGround(2,3);
		world.addGround(3,3,Color.GREEN);
		world.addGround(4,3);
		world.addGround(5,3);
		world.addStone(5,2);
		world.addGround(6,3,Color.GREEN);
		world.addGround(7,3);
		world.addGround(8,3);
		world.addGoal(8,2);
		
		return world;
	}
}
