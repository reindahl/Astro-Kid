package problemGenerator;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Level {
	String name;
	
	Character[][] map;
	Point goal;
	Point player;
	HashSet<Point> brown = new HashSet<>();
	HashSet<Point> green = new HashSet<>();
	HashSet<Point> blue = new HashSet<>();
	HashSet<Point> purple = new HashSet<>();
	HashSet<Point> ladders = new HashSet<>();
	HashSet<Point> stones = new HashSet<>();
	HashSet<Point> robots = new HashSet<>();
	
	
	public Level(List<String> readAllLines, String name) {
		System.out.println(name);
		this.name=name;
		int height=0, width=0;
		for (String string : readAllLines) {
			width=Math.max(string.length(), width);
			height++;
		}
		map=new Character[width+2][height+1];
		for (Character[] row: map){
			Arrays.fill(row, ' ');
		}
		int i=0;
		for (String string : readAllLines) {

			
			for (int j = 0; j < string.length(); j++) {
				map[j+1][i]=string.charAt(j);
				switch (string.charAt(j)) {
				case '¤':
					brown.add(new Point(j+1,i));
					
					break;
				case 'c':
					green.add(new Point(j+1,i));
					
					break;
				case 'z':
					purple.add(new Point(j+1,i));
					
					break;
				case 'x':
					blue.add(new Point(j+1,i));
					
					break;
				case 'p':
					player=new Point(j+1,i);
					
					break;
				case 'g':
					goal=new Point(j+1,i);
					
					break;
				case 's':
					stones.add(new Point(j+1,i));
					
					break;
				case '#':
					ladders.add(new Point(j+1,i));
					
					break;
				case 'r':
				case 'l':
					robots.add(new Point(j+1,i));
					break;
				case ' ':
					
					break;
				case '\t':
					break;
				default:
					System.out.println("unknown symbol "+ ((int)map[j+1][i]));
					break;
				}
			}
			i++;
		}
	}

	public ArrayList<String> getText() {
		ArrayList<String> lines= new ArrayList<>();
		StringBuilder builder= new StringBuilder();
		for (int j = 0; j < map[0].length; j++) {
			builder.append(";");
			for (int i = 0; i < map.length;i++) {
				builder.append(map[i][j]);
			}
			lines.add(builder.toString());
			builder.setLength(0);
		}
		lines.add("\n");
		lines.add("(define (problem "+name+"-Astro)");
		lines.add(" (:domain Astro)");
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
		for (@SuppressWarnings("unused") Point s : robots) {
			lines.add("  robot"+tmp+" - robot");
			tmp++;
		}
		
		
		lines.add(" )");

		lines.add(" (:init");
		
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
		for (Point point : robots) {
			lines.add("  (at robot"+tmp+" pos-"+(point.x<10?"0":"")+point.x+"-"+(point.y<10?"0":"")+point.y+")");
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
		
		
		//relative positions
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[0].length; y++) {

				if(map[x][y]==' '||map[x][y]=='g'||map[x][y]=='#'){

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
		
		
		lines.add(" (:goal (and");
		
		lines.add("   (at player-01 "+"pos-"+(goal.x<10?"0":"")+goal.x+"-"+(goal.y<10?"0":"")+goal.y+")");
		lines.add("   (alive player-01)");
		lines.add("  )");
		lines.add(" )");
		lines.add(" (:metric minimize (total-cost))");
		lines.add(")");
		return lines;
	}

}
