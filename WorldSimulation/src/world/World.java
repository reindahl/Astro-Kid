package world;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import world.objects.Goal;
import world.objects.Ground;
import world.objects.MovableObject;
import world.objects.PhysObject;
import world.objects.PhysObject.Direction;
import world.objects.Player;
import world.objects.Stone;

public class World {

	PhysObject fixedMap[][];
	HashSet<Point> ladders;
	MovableObject movingMap[][];
	
	ArrayList<MovableObject> movable= new ArrayList<>();
	
	Player player;
	
	Goal goal;
	public int width;
	public int height;
	
	
	public World(int width, int height) {
		this.height=height;
		this.width=width;
		fixedMap=new PhysObject[width][height];
		movingMap=new MovableObject[width][height];
	}


	public boolean isGoal(){
		if(goal.getPosition().equals(player.getPosition())){
			return true;
		}else{
			return false;
		}
	}


	public void update() {
		moveObjects();
		destroyObjects();
	}


	private void destroyObjects() {
		Iterator<MovableObject> iter = movable.iterator();

		while (iter.hasNext()) {
			MovableObject obj = iter.next();
			
		    if (!obj.isLegal()){
		    	movingMap[obj.getPosition().getX()][obj.getPosition().getY()]=null;
		        iter.remove();
		    }
		}
		
	}


	private void moveObjects() {
		for (MovableObject physObjects : movable) {
			physObjects.updatePosition();
		}
	}
	public boolean isClear(Point to) {
		return isClear(to.getX(), to.getY());
	}
	public boolean isClear(int x, int y){
		if(movingMap[x][y]!=null || fixedMap[x][y]!=null){
			return false;
		}
		return true;
	}

	public boolean isLadder(Point p){
		return ladders.contains(p);
	}

	public PhysObject getLocation(Point point) {
		return getLocation(point.getX(), point.getY());
	}


	public PhysObject getLocation(int x, int y) {
		
		if(x<0 || x>=width || y<0 || y>=height){
			return null;
		}
		
		if(fixedMap[x][y]!=null){
			return fixedMap[x][y];
		}
		if(movingMap[x][y]!=null){
			return movingMap[x][y];
		}
		
		
		return null;
	}

	
	
	public void addRobot(Point position, Direction facing){
		
	}
	public void addStone(Point position){
		
	}
	public void addButton(Point position, Color color){
		
	}
	
	public void addGate(Point position, Color color){
		
	}
	public void addPlayer(Point position){
		player=new Player(this, position);
		movingMap[position.getX()][position.getY()]=player;
		movable.add(player);
	}


	public void addPlayer(int x, int y) {
		addPlayer(new Point(x, y));
		
		
	}


	public void addGoal(int x, int y) {
		goal=new Goal(this, new Point(x, y));
		
	}


	public void addGround(int x, int y) {
		// TODO Auto-generated method stub
		fixedMap[x][y]= new Ground(this, new Point(x, y));
	}
	public void addGround(int x, int y, Color green) {
		fixedMap[x][y]= new Ground(this, new Point(x, y),green);
		
	}

	public Boolean playerAction(Direction direction) {
		return player.move(direction);
		
	}


	public void Move(Point from, Point to) {
		MovableObject tmp=movingMap[from.getX()][from.getY()];
		movingMap[from.getX()][from.getY()]=null;
		
		movingMap[to.getX()][to.getY()]=tmp;
	}


	public void addStone(int x, int y) {
		
		Stone stone=new Stone(this, new Point(x, y));
		movingMap[x][y]=stone;
		movable.add(stone);

	}



}
