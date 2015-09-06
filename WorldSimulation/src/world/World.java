package world;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import world.objects.Button;
import world.objects.Gate;
import world.objects.Goal;
import world.objects.Ground;
import world.objects.Ladder;
import world.objects.MovableObject;
import world.objects.PhysObject;
import world.objects.PhysObject.Direction;
import world.objects.Player;
import world.objects.Remote;
import world.objects.Robot;
import world.objects.Start;
import world.objects.Stone;
import world.objects.Teleport;

public class World extends Observable{

	public enum Color{red, brown, green, blue, purple}

	public enum Type{
		ground, groundGreen, groundPurple, groundBlue,
		bootGreen, bootPurple, bootBlue,
		player, goal,
		buttonRed, gateRed,
		stone, 
		robotLeft, robotRight,
		ladder, teleport, 
		remote, start
	}

	PhysObject fixedMap[][];
	MovableObject movingMap[][];
	HashSet<Point> ladders = new HashSet<>();
	HashMap<Point, PhysObject> pickups = new HashMap<>();
	ArrayList<Ladder> laddersList= new ArrayList<>();

	HashMap<Color, ArrayList<Button>> buttons= new HashMap<>();
	ArrayList<Gate> gates= new ArrayList<>();
	ArrayList<Teleport> teleports= new ArrayList<>();
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

	public World(String path){
		try {
			File fXmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);


			doc.getDocumentElement().normalize();

			height=Integer.parseInt(doc.getDocumentElement().getAttributeNode("height").getValue());
			width=Integer.parseInt(doc.getDocumentElement().getAttributeNode("width").getValue());
			fixedMap=new PhysObject[width][height];
			movingMap=new MovableObject[width][height];


			NodeList nList = doc.getElementsByTagName("Ground");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					addGround(getPointXml(eElement), getColorXml(eElement));

				}
			}
			
			nList = doc.getElementsByTagName("Player");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					addPlayer(getPointXml(eElement));

				}
			}
			
			
			nList = doc.getElementsByTagName("Goal");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					addGoal(getPointXml(eElement));

				}
			}
			
			nList = doc.getElementsByTagName("Stone");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					addStone(getPointXml(eElement));

				}
			}
			
			
			nList = doc.getElementsByTagName("Ladder");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					addLadder(getPointXml(eElement));

				}
			}
			
			
			nList = doc.getElementsByTagName("Button");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);


				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					addButton(getPointXml(eElement), getColorXml(eElement));

				}
			}
			
			nList = doc.getElementsByTagName("Gate");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);


				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					addGate(getPointXml(eElement), getColorXml(eElement));

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
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
		fixedMap[position.getX()][position.getY()]=goal;
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
		fixedMap[position.getX()][position.getY()]=new Start(this, position);
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
		teleports.add(tele1);
		teleports.add(tele2);
	}
	public void clear(Point position) {
		fixedMap[position.getX()][position.getY()]= null;
		movingMap[position.getX()][position.getY()]= null;
		moveable.removeIf(e -> e.getPosition().equals(position));
		if(player!=null && player.getPosition()==position){
			player=null;
		}
		if(goal!=null && goal.getPosition()==position){
			goal=null;
		}
		ladders.remove(position);
		laddersList.removeIf(e -> e.getPosition().equals(position));
		teleports.removeIf(e -> e.getPosition().equals(position));
		gates.removeIf(e -> e.getPosition()==position);
		pickups.remove(position);
		setChanged();
		notifyObservers();
	}


	private void destroyObjects() {
		Iterator<MovableObject> iter = moveable.iterator();

		while (iter.hasNext()) {
			MovableObject obj = iter.next();

			if (!obj.isLegal()){
				movingMap[obj.getPosition().getX()][obj.getPosition().getY()]=null;
				if(obj instanceof Player){
					player=null;
				}
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

	private Color getColorXml(Element eElement){
		return Color.valueOf(eElement.getElementsByTagName("Color").item(0).getTextContent());
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


	public int getHeight() {
		return height;
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


	public ArrayList<Type> getLocationType(int x, int y) {
		ArrayList<Type> result= new ArrayList<>();
		if(x<0 || x>=width || y<0 || y>=height){
			return null;
		}
		if(movingMap[x][y]!=null){
			result.add(movingMap[x][y].getType());
		}
		if(fixedMap[x][y]!=null){
			result.add(fixedMap[x][y].getType());

		}
		PhysObject pick = pickups.get(new Point(x, y));
		if(pick!=null){
			result.add(pick.getType());
		}

		if(ladders.contains(new Point(x, y))){
			result.add(Type.ladder);
		}

		return result;
	}


	public Player getPlayer() {
		return player;
	}


	private Point getPointXml(Element eElement){
		int x, y;
		NamedNodeMap tmp =eElement.getElementsByTagName("Point").item(0).getAttributes(); 
		x=Integer.parseInt(tmp.getNamedItem("x").getNodeValue());
		y=Integer.parseInt(tmp.getNamedItem("y").getNodeValue());
		return new Point(x,y);
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


	public ArrayList<Teleport> getTeleports() {
		return teleports;
	}


	public int getWidth() {
		return width;
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
		if(player!=null && goal.getPosition().equals(player.getPosition())){
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
		if(player!=null){
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
	}


	/**
	 * distinguish between push, move, activate
	 * @param direction
	 * @return
	 */
	public Boolean playerAction(Direction direction) {
		if(player==null){
			return false;
		}


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


	public boolean playerAction(Point pos) {
		return playerAction(pos.getX(), pos.getY());
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
		result[goal.getPosition().getX()][goal.getPosition().getY()]=goal.getChar();
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

	public void toXml(String name) throws FileNotFoundException, XMLStreamException{
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("World");
			rootElement.setAttribute("height", ""+height);
			rootElement.setAttribute("width", ""+width);
			doc.appendChild(rootElement);
			
			for (int i = 0; i <width; i++) {
				for (int j = 0; j < height; j++) {
					if(fixedMap[i][j]!=null){
						rootElement.appendChild(fixedMap[i][j].toXml(doc));
					}
				}
			}
			for (Ladder ladder : getLadders()) {
				rootElement.appendChild(ladder.toXml(doc));
			}
			for (MovableObject obj : moveable) {
				rootElement.appendChild(obj.toXml(doc));
			}
			


			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			transformerFactory.setAttribute("indent-number", 2);
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(name));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
	public void update() {
		setSteps(getSteps() + 1);
		moveObjects();
		destroyObjects();
		setChanged();
		notifyObservers();
	}

}
