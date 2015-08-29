package world;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import world.objects.Goal;
import world.objects.Ground;
import world.objects.Ladder;
import world.objects.MovableObject;
import world.objects.PhysObject;
import world.objects.PhysObject.Direction;
import world.objects.Player;
import world.objects.Robot;
import world.objects.Stone;

public class World {

	public enum Type{
		ground, groundGreen, groundPurple, groundBlue,
		bootGreen, bootPurple, bootBlue,
		player, goal,
		buttonRed, gateRed,
		stone, 
		robotLeft, robotRight,
		ladder, teleport, 
		remote,
		nothing
	}
	
	public enum Color{red, brown, green, blue, purple}
	
	PhysObject fixedMap[][];
	MovableObject movingMap[][];
	HashSet<Point> ladders;

	ArrayList<MovableObject> moveable= new ArrayList<>();

	Player player;
	Goal goal;
	public int width;
	public int height;

	private int steps=0;

	public World(int width, int height) {
		this.height=height;
		this.width=width;
		fixedMap=new PhysObject[width][height];
		movingMap=new MovableObject[width][height];
	}


	public void addButton(Point position, Color color){

	}


	public void addGate(Point position, Color color){

	}


	public void addGoal(int x, int y) {
		
		addGoal( new Point(x, y));
	}


	public void addGoal(Point position) {
		goal=new Goal(this,position);
		
	}
	public void addGround(int x, int y) {
		addGround(new Point(x, y));
		
	}
	public void addGround(int x, int y, Color color) {
		addGate(new Point(x, y), color);

	}

	public void addGround(Point position) {
		fixedMap[position.getX()][position.getY()]= new Ground(this, position);
		
	}

	public void addGround(Point position, Color color) {
		fixedMap[position.getX()][position.getY()]= new Ground(this, position,color);
		
	}


	public void addLadder(Point position) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}


	public void addPlayer(int x, int y) {
		addPlayer(new Point(x, y));


	}

	public void addPlayer(Point position){
		player=new Player(this, position);
		movingMap[position.getX()][position.getY()]=player;
		moveable.add(player);
	}
	public void addRobot(int x, int y, Direction direction) {
		Robot robot=new Robot(this, direction, new Point(x, y));
		movingMap[x][y]=robot;
		moveable.add(robot);
		
	}

	public void addRobot(Point position, Direction facing){

	}
	public void addStone(int x, int y) {

		Stone stone=new Stone(this, new Point(x, y));
		movingMap[x][y]=stone;
		moveable.add(stone);

	}


	public void addStone(Point position){
		addStone(position.getX(), position.getY());
	}


	private void destroyObjects() {
		Iterator<MovableObject> iter = moveable.iterator();

		while (iter.hasNext()) {
			MovableObject obj = iter.next();

			if (!obj.isLegal()){
				movingMap[obj.getPosition().getX()][obj.getPosition().getY()]=null;
				iter.remove();
			}
		}

	}


	public Goal getGoal() {
		return goal;
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

	public PhysObject getLocation(Point point) {
		return getLocation(point.getX(), point.getY());
	}


	public Player getPlayer() {
		return player;
	}


	public int getSteps() {
		return steps;
	}

	public boolean isClear(int x, int y){
		if(movingMap[x][y]!=null || fixedMap[x][y]!=null){
			return false;
		}
		return true;
	}

	public boolean isClear(Point to) {
		return isClear(to.getX(), to.getY());
	}


	public boolean isGoal(){
		if(goal.getPosition().equals(player.getPosition())){
			return true;
		}else{
			return false;
		}
	}


	public boolean isLadder(Point p){
		return ladders.contains(p);
	}


	public void Move(Point from, Point to) {
		MovableObject tmp=movingMap[from.getX()][from.getY()];
		movingMap[from.getX()][from.getY()]=null;

		movingMap[to.getX()][to.getY()]=tmp;
	}


	private void moveObjects() {

		for (MovableObject physObjects : moveable) {
			physObjects.updatePosition();

		}
	}


	public Boolean playerAction(Direction direction) {
		//TODO: distinguis between push, move, activate
		if(isClear(player.relativTo(direction))){
			return player.move(direction);
		}
		return player.push(direction);

	}


	public void setSteps(int steps) {
		this.steps = steps;
	}


	public Character[][] toArray(){
		Character[][] result= new Character[width][height];
		for (int i = 0; i <width; i++) {
			for (int j = 0; j < height; j++) {
				if(fixedMap[i][j]!=null){
					result[i][j]=fixedMap[i][j].getChar();
				}else{
					result[i][j]=' ';
				}
			}
		}

		for (MovableObject obj : moveable) {
			result[obj.getPosition().getX()][obj.getPosition().getY()]=obj.getChar();
		}
		return result;
	}


	@Override
	public String toString(){
		Character[][] map=toArray();
		StringBuilder s = new StringBuilder();
		s.append("\n");

		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				s.append(map[i][j]);
			}
			s.append("\n");
		}
		return s.toString();
	}


	public void update() {
		setSteps(getSteps() + 1);
		moveObjects();
		destroyObjects();
	}


	public ArrayList<Stone> getStones() {
		ArrayList<Stone> result = new ArrayList<>();

		for (MovableObject obj : moveable) {
			if(obj instanceof Stone)
				result.add((Stone) obj);
		}
		return result;
	}
	public ArrayList<Robot> getRobots() {
		ArrayList<Robot> result = new ArrayList<>();

		for (MovableObject obj : moveable) {
			if(obj instanceof Robot)
				result.add((Robot) obj);
		}
		return result;
	}


	public ArrayList<Ground> getGround() {
		ArrayList<Ground> result = new ArrayList<>();
		
		for (int i = 0; i < fixedMap.length; i++) {
			for (int j = 0; j < fixedMap[0].length; j++) {
				PhysObject obj = fixedMap[i][j];
				if(obj instanceof Ground)
					result.add((Ground) obj);
			}
		}

		return result;
	}


	public ArrayList<Ladder> getLadders() {
		ArrayList<Ladder> result = new ArrayList<>();
		
		for (int i = 0; i < fixedMap.length; i++) {
			for (int j = 0; j < fixedMap[0].length; j++) {
				PhysObject obj = fixedMap[i][j];
				if(obj instanceof Ladder)
					result.add((Ladder) obj);
			}
		}

		return result;
	}


	public ArrayList<PhysObject> getTeleports() {
		// TODO Auto-generated method stub
		return null;
	}


	public ArrayList<PhysObject> getGates() {
		// TODO Auto-generated method stub
		return null;
	}


	public ArrayList<PhysObject> getButtons() {
		// TODO Auto-generated method stub
		return null;
	}

}
