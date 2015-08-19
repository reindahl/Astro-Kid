package problemGenerator;

import gui.Tile;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Level {
	
	
	public enum Type{
		ground, groundGreen, groundPurple, groundBlue,
		start, goal,
		buttonRed, gateRed,
		stone, 
		robotLeft, robotRight,
		ladder, teleport, 
		nothing
	}
	
	String name;
	
	Type[][] map;
	Point goal;
	Point player;
	HashSet<Point> brown = new HashSet<>();
	HashSet<Point> green = new HashSet<>();
	HashSet<Point> blue = new HashSet<>();
	HashSet<Point> purple = new HashSet<>();
	HashSet<Point> ladders = new HashSet<>();
	HashSet<Point> stones = new HashSet<>();
	HashSet<Point> robotsLeft = new HashSet<>();
	HashSet<Point> robotsRight = new HashSet<>();
	HashSet<Point> teleports = new HashSet<>();
	HashMap<Point, String> gate = new HashMap<>();
	HashMap<Point, String> button = new HashMap<>();
	
	
	
	public Level(List<String> readAllLines, String name) {
		System.out.println(name);
		this.name=name;
		int height=0, width=0;
		for (String string : readAllLines) {
			width=Math.max(string.length(), width);
			height++;
		}
		map=new Type[width+2][height+1];
		for (Type[] row: map){
			Arrays.fill(row, Type.nothing);
		}
		int i=0;
		for (String string : readAllLines) {

			
			for (int j = 0; j < string.length(); j++) {
				switch (string.charAt(j)) {
				case '¤':
					map[j+1][i]=Type.ground;
					brown.add(new Point(j+1,i));
					
					break;
				case 'c':
					map[j+1][i]=Type.groundGreen;
					green.add(new Point(j+1,i));
					
					break;
				case 'z':
					map[j+1][i]=Type.groundPurple;
					purple.add(new Point(j+1,i));
					
					break;
				case 'x':
					map[j+1][i]=Type.groundBlue;
					blue.add(new Point(j+1,i));
					
					break;
				case 'p':
					map[j+1][i]=Type.start;
					player=new Point(j+1,i);
					
					break;
				case 'g':
					map[j+1][i]=Type.goal;
					goal=new Point(j+1,i);
					
					break;
				case 's':
					map[j+1][i]=Type.stone;
					stones.add(new Point(j+1,i));
					
					break;
				case '#':
					map[j+1][i]=Type.ladder;
					ladders.add(new Point(j+1,i));
					
					break;
				case 'r':
					map[j+1][i]=Type.robotRight;
					robotsRight.add(new Point(j+1,i));
					break;
				case 'l':
					map[j+1][i]=Type.robotLeft;
					robotsLeft.add(new Point(j+1,i));
					break;
				case 't':
					map[j+1][i]=Type.teleport;
					teleports.add(new Point(j+1,i));
					break;
				case ' ':
					map[j+1][i]=Type.nothing;
					break;
				case 'b':
					map[j+1][i]=Type.buttonRed;
					button.put(new Point(j+1,i), "red");
					break;
				case 'w':
					map[j+1][i]=Type.gateRed;
					gate.put(new Point(j+1,i), "red");
					break;
				case '\t':
					break;
				default:
					System.out.println("unknown symbol "+ ((int)string.charAt(j)) +" : "+string.charAt(j));
					break;
				}
			}
			i++;
		}
	}

	public Level(Tile[][] tmap, String name) {
		System.out.println(name);
		this.name=name;
		int height=tmap.length, width=tmap[0].length;

		map=new Type[width+2][height+1];
		for (Type[] row: map){
			Arrays.fill(row, Type.nothing);
		}
		for (int i = 0; i < height; i++) {
			
			for (int j = 0; j < width; j++) {
				map[j+1][i]=tmap[j][i].getType();
				switch (tmap[j][i].getType()) {
				case ground:
					brown.add(new Point(j+1,i));
					
					break;
				case groundGreen:
					green.add(new Point(j+1,i));
					
					break;
				case groundPurple:
					purple.add(new Point(j+1,i));
					
					break;
				case groundBlue:
					blue.add(new Point(j+1,i));
					
					break;
				case start:
					player=new Point(j+1,i);
					
					break;
				case goal:
					goal=new Point(j+1,i);
					
					break;
				case stone:
					stones.add(new Point(j+1,i));
					
					break;
				case ladder:
					ladders.add(new Point(j+1,i));
					
					break;
				case robotLeft:
					robotsLeft.add(new Point(j+1,i));
					break;
				case robotRight:
					robotsRight.add(new Point(j+1,i));
					break;
				case teleport:
					teleports.add(new Point(j+1,i));
					break;
				case nothing:
					
					break;
				case buttonRed:
					button.put(new Point(j+1,i), "red");
					break;
				case gateRed:
					gate.put(new Point(j+1,i), "red");
					break;

				default:
					System.out.println("unknown type "+ tmap[j][i].getName());
					break;
				}
			}
			i++;
		}
	}

	public ArrayList<String> getText() {
		if(player==null || goal==null){
			System.err.println("ERROR: player "+player+" : goal "+goal);
			System.exit(-1);
		}
		
		ArrayList<String> lines= new ArrayList<>();
		StringBuilder builder= new StringBuilder();
		for (int j = 0; j < map[0].length; j++) {
			builder.append(";");
			for (int i = 0; i < map.length;i++) {
				builder.append(TypeToCharacter(map[i][j]));
			}
			lines.add(builder.toString());
			builder.setLength(0);
		}
		lines.add("\n");
		lines.add("(define (problem "+name+"-Astro)");
		lines.add(" (:domain Astro)");
		
		lines.addAll(objs());
	
	
		lines.addAll(init());

		lines.addAll(goals());
		
		lines.add(" (:metric minimize (total-cost))");
		lines.add(")");
		return lines;
	}

	
	public ArrayList<String> objs(){
		ArrayList<String> lines= new ArrayList<>();
		lines.add(" (:objects");
		
		//objects
		
		lines.add("  player-01 - player");
		
		
		
		for (int j = 0; j < map[0].length; j++) {
			for (int i = 0; i < map.length; i++) {
				lines.add("  pos-"+(i<10?"0":"")+i+"-"+(j<10?"0":"")+j+" - location");
			}
		}
		
		int tmp =0;
		for (@SuppressWarnings("unused") Point s : stones) {
			lines.add("  stone"+tmp+" - stone");
			tmp++;
		}
		
		tmp =0;
		for (@SuppressWarnings("unused") Point s : robotsLeft) {
			lines.add("  robot"+tmp+" - robot");
			tmp++;
		}
		for (@SuppressWarnings("unused") Point s : robotsRight) {
			lines.add("  robot"+tmp+" - robot");
			tmp++;
		}
		
		lines.add(" )");
		return lines;
	}
	
	public ArrayList<String> init(){
		ArrayList<String> lines= new ArrayList<>();
		lines.add(" (:init");
		int tmp=0;
		lines.add("  (= (total-cost) 0)");
		lines.add("  (at player-01 "+"pos-"+(player.x<10?"0":"")+player.x+"-"+(player.y<10?"0":"")+player.y+")");
		lines.add("  (alive player-01)");
		
		//ladders
		for (Point point : ladders) {
			lines.add("  (ladder pos-"+(point.x<10?"0":"")+point.x+"-"+(point.y<10?"0":"")+point.y+")");
		}
		
		//stones
		tmp=0;
		for (Point point : stones) {
			lines.add("  (at stone"+tmp+" pos-"+(point.x<10?"0":"")+point.x+"-"+(point.y<10?"0":"")+point.y+")");
			tmp++;
		}
		//robots
		tmp=0;
		for (Point point : robotsLeft) {
			lines.add("  (at robot"+tmp+" pos-"+(point.x<10?"0":"")+point.x+"-"+(point.y<10?"0":"")+point.y+")");
			lines.add("  (facing robot"+tmp+" left)");
			tmp++;
		}
		for (Point point : robotsRight) {
			lines.add("  (at robot"+tmp+" pos-"+(point.x<10?"0":"")+point.x+"-"+(point.y<10?"0":"")+point.y+")");
			lines.add("  (facing robot"+tmp+" right)");
			tmp++;
		}
		
		//ground
		for (Point point : green) {
			lines.add("  (ground green pos-"+(point.x<10?"0":"")+point.x+"-"+(point.y<10?"0":"")+point.y+")");

		}
		for (Point point : purple) {
			lines.add("  (ground purple pos-"+(point.x<10?"0":"")+point.x+"-"+(point.y<10?"0":"")+point.y+")");

		}
		for (Point point : blue) {
			lines.add("  (ground blue pos-"+(point.x<10?"0":"")+point.x+"-"+(point.y<10?"0":"")+point.y+")");

		}
		//teleports
		for (Point point : teleports) {
			lines.add("  (teleport pos-"+(point.x<10?"0":"")+point.x+"-"+(point.y<10?"0":"")+point.y+")");
		}
		
		//gates
		for (Point point : button.keySet()) {
			lines.add("  (button red pos-"+(point.x<10?"0":"")+point.x+"-"+(point.y<10?"0":"")+point.y+")");
			lines.add("  (clear pos-"+(point.x<10?"0":"")+point.x+"-"+(point.y<10?"0":"")+point.y+")");
		}
		
		//buttons
		for (Point point : gate.keySet()) {
			lines.add("  (gate red pos-"+(point.x<10?"0":"")+point.x+"-"+(point.y<10?"0":"")+point.y+")");
			lines.add("  (closed pos-"+(point.x<10?"0":"")+point.x+"-"+(point.y<10?"0":"")+point.y+")");
			lines.add("  (clear pos-"+(point.x<10?"0":"")+point.x+"-"+(point.y<10?"0":"")+point.y+")");
		}
		
		
		//relative positions
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[0].length; y++) {

				if(map[x][y]==Type.nothing||map[x][y]==Type.goal||map[x][y]==Type.ladder||map[x][y]==Type.teleport){

					lines.add("  (clear pos-"+(x<10?"0":"")+x+"-"+(y<10?"0":"")+y+")");
				}
				if(x!=0){
					lines.add("  (relativ-dir pos-"+(x<10?"0":"")+x+"-"+(y<10?"0":"")+y+" pos-"+(x-1<10?"0":"")+(x-1)+"-"+(y<10?"0":"")+y+" left)");
				}
				if(x!=map.length-1){
					lines.add("  (relativ-dir pos-"+(x<10?"0":"")+x+"-"+(y<10?"0":"")+y+" pos-"+(x+1<10?"0":"")+(x+1)+"-"+(y<10?"0":"")+y+" right)");
				}
				
				if(y!=0){
					lines.add("  (relativ-dir pos-"+(x<10?"0":"")+x+"-"+(y<10?"0":"")+y+" pos-"+(x<10?"0":"")+x+"-"+(y-1<10?"0":"")+(y-1)+" up)");
				}
				if(y!=map[0].length-1){
					lines.add("  (relativ-dir pos-"+(x<10?"0":"")+x+"-"+(y<10?"0":"")+y+" pos-"+(x<10?"0":"")+x+"-"+(y+1<10?"0":"")+(y+1)+" down)");
				}else{
					lines.add("  (boarder pos-"+(x<10?"0":"")+x+"-"+(y<10?"0":"")+y+")");
				}
			}
		}
		
		
		lines.add(" )");
		return lines;
	}
	
	public ArrayList<String> goals(){
		ArrayList<String> lines= new ArrayList<>();
		lines.add(" (:goal (and");
		
		lines.add("   (at player-01 "+"pos-"+(goal.x<10?"0":"")+goal.x+"-"+(goal.y<10?"0":"")+goal.y+")");
		lines.add("   (alive player-01)");
		lines.add("  )");
		lines.add(" )");
		return lines;
	}
	
	
	public Character TypeToCharacter(Type type){
		switch (type) {
		case ground:
			return '¤';
		case groundGreen:
			return 'c';
		case groundPurple:
			return 'z';
		case groundBlue:
			return 'x';
		case start:
			return 'p';
		case goal:
			return 'g';
		case stone:
			return 's';
		case ladder:
			return '#';
		case robotRight:
			return 'r';
		case robotLeft:
			return 'l';
		case teleport:
			return 't';
		case buttonRed:
			return 'b';
		case gateRed:
			return 'w';
		case nothing:
			return ' ';
		default:
			System.out.println("unknown symbol "+ type);
			break;
		}
		return null;
	}
}
