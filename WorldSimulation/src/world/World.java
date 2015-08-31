package world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import world.objects.Button;
import world.objects.Gate;
import world.objects.Goal;
import world.objects.Ground;
import world.objects.Ladder;
import world.objects.MovableObject;
import world.objects.PhysObject;
import world.objects.Remote;
import world.objects.Teleport;
import world.objects.PhysObject.Direction;
import world.objects.Player;
import world.objects.Robot;
import world.objects.Stone;

public class World {

	public enum Color{red, brown, green, blue, purple}
	
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
	
	PhysObject fixedMap[][];
	MovableObject movingMap[][];
	HashSet<Point> ladders = new HashSet<>();
	HashMap<Point, PhysObject> pickups = new HashMap<>();
	ArrayList<Ladder> laddersList= new ArrayList<>();
	
	HashMap<Color, ArrayList<Button>> buttons= new HashMap<>();
	ArrayList<Gate> gates= new ArrayList<>();
	
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


	public void addButton(int x, int y, Color color) {
		
		addButton(new Point(x, y), color);
	}


	public void addButton(Point position, Color color){
		Button button=new Button(this, position, color);
		ArrayList<Button> list=buttons.get(color);
		if(list==null){
			list= new ArrayList<>();
			buttons.put(color, list);
		}
		list.add(button);
		fixedMap[position.getX()][position.getY()]=button;
		
	}


	public void addGate(int x, int y, Color color) {
		addGate(new Point(x, y), color);
		
	}


	public void addGate(Point position, Color color){
		Gate gate = new Gate(this, position, color);
		gates.add(gate);
		fixedMap[position.getX()][position.getY()]=gate;
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
		addGround(new Point(x, y), color);

	}

	public void addGround(Point position) {
		fixedMap[position.getX()][position.getY()]= new Ground(this, position);
		
	}


	public void addGround(Point position, Color color) {
		fixedMap[position.getX()][position.getY()]= new Ground(this, position,color);
		
	}

	public void addLadder(int x, int y) {
		addLadder(new Point(x, y));
		
	}
	public void addLadder(Point position) {
		ladders.add(position);
		laddersList.add(new Ladder(this, position));

	}

	public void addPlayer(int x, int y) {
		addPlayer(new Point(x, y));


	}
	public void addPlayer(Point position){
		player=new Player(this, position);
		movingMap[position.getX()][position.getY()]=player;
		moveable.add(player);
	}


	public void addRemote(int x, int y) {
		// TODO Auto-generated method stub
		Remote remote =new Remote(this, new Point(x, y));
		pickups.put(remote.getPosition(), remote);
		
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
	public void addTeleport(int x1, int y1, int x2, int y2) {

		addTeleport(new Point(x1, y1), new Point(x2, y2));
	}


	public void addTeleport(Point pos1, Point pos2) {
		Teleport tele1= new Teleport(this, pos1, pos2);
		Teleport tele2= new Teleport(this, pos2, pos1);
		
		fixedMap[pos1.getX()][pos1.getY()]=tele1;
		fixedMap[pos2.getX()][pos2.getY()]=tele2;

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

	public ArrayList<Button> getButtons() {
		ArrayList<Button> result = new ArrayList<>();
		Collection<ArrayList<Button>> col = buttons.values();
		col.forEach(list-> result.addAll(list));
		return result;
	}

	public ArrayList<Button> getButtons(Color color) {
		ArrayList<Button> result=buttons.get(color);
		if(result==null){
			result=new ArrayList<>();
		}
		return result;
	}


	public ArrayList<Gate> getGates() {
		return gates;
	}


	public Goal getGoal() {
		return goal;
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

		return laddersList;
	}


	public PhysObject getLocation(int x, int y) {

		if(x<0 || x>=width || y<0 || y>=height){
			return null;
		}
		if(movingMap[x][y]!=null){
			return movingMap[x][y];
		}
		if(fixedMap[x][y]!=null){
			return fixedMap[x][y];
		}
		


		return null;
	}


	public PhysObject getLocation(Point point) {
		return getLocation(point.getX(), point.getY());
	}


	public Player getPlayer() {
		return player;
	}


	public ArrayList<Robot> getRobots() {
		ArrayList<Robot> result = new ArrayList<>();

		for (MovableObject obj : moveable) {
			if(obj instanceof Robot)
				result.add((Robot) obj);
		}
		return result;
	}


	public int getSteps() {
		return steps;
	}


	public ArrayList<Stone> getStones() {
		ArrayList<Stone> result = new ArrayList<>();

		for (MovableObject obj : moveable) {
			if(obj instanceof Stone)
				result.add((Stone) obj);
		}
		return result;
	}
	
	public ArrayList<PhysObject> getTeleports() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
	
	public boolean isClear(int x, int y){
		if(movingMap[x][y]!=null && movingMap[x][y].isSolid()){
			return false;
		}
		if(fixedMap[x][y]!=null && fixedMap[x][y].isSolid()){
			return false;
		}

		return true;
		
	}

	
	public boolean isClear(Point to) {
		return isClear(to.getX(), to.getY());
	}


	public boolean isClearMoveable(int x, int y){
		if(movingMap[x][y]!=null && movingMap[x][y].isSolid()){
			return false;
		}

		return true;
		
	}


	public boolean isClearMoveable(Point to) {
		return isClearMoveable(to.getX(), to.getY());
	}


	public boolean isGoal(){
		if(goal.getPosition().equals(player.getPosition())){
			return true;
		}else{
			return false;
		}
	}


	public Boolean isLadder(Point point){
		return ladders.contains(point);
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
		
		//teleport
		if(fixedMap[player.getX()][player.getY()] instanceof Teleport){
			Teleport tele=(Teleport) fixedMap[player.getX()][player.getY()];
			Move(player.getPosition(), tele.getExit());
			player.setPosition(tele.getExit());
		}
		
		//pickup
		PhysObject pickup =pickups.get(player.getPosition());
		if(pickup!=null){
			player.pickup(pickup);
			pickups.remove(player.getPosition());
		}
	}

	public Boolean playerAction(Direction direction) {
		//TODO: distinguish between push, move, activate
		
		
		if(player.isClimb(direction) || player.isWalk(direction)){
			return player.move(direction);
		}
		
		if(player.isPush(direction)){
			return player.push(direction);
		}
		
		
		
		return false;

	}


	public boolean playerAction(int x, int y) {
		return player.useRemote(x, y);
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
		for (Ladder ladder : getLadders()) {
			result[ladder.getPosition().getX()][ladder.getPosition().getY()]=ladder.getChar();
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

}
