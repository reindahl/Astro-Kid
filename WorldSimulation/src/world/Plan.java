package world;

import java.util.ArrayList;

import world.commands.Activate;
import world.commands.Command;
import world.commands.Move;
import world.commands.NoOp;
import world.commands.Push;
import world.objects.PhysObject.Direction;

public class Plan {

	ArrayList<String> actions = new ArrayList<>();
	ArrayList<Command> commands = new ArrayList<>();
	
	
	public Plan(ArrayList<String> lines) {
		for (String string : lines) {
			if(string.endsWith("(1)")){
				actions.add(string);
			}
		}
		for (int i = 0; i < actions.size(); i++) {
			if(actions.get(i).startsWith("walk")){
				String[] split= actions.get(i).split(" ");
				
				Direction dir = dir(split[split.length-2]);
				actions.set(i, "walk "+dir);
				commands.add(new Move(dir));
			}else if(actions.get(i).startsWith("noop")){
				actions.set(i, "noOp");
				commands.add(new NoOp());
			}else if(actions.get(i).startsWith("push")){
				String[] split= actions.get(i).split(" ");
				Direction dir = dir(split[split.length-2]);
				actions.set(i, "push "+dir);
				commands.add(new Push(dir));
			}else if(actions.get(i).startsWith("climbdown")){;
				commands.add(new Move(Direction.down));
			}else if(actions.get(i).startsWith("climbup")){
				commands.add(new Move(Direction.up));
			}else if(actions.get(i).startsWith("activaterobot")){
				String[] split= actions.get(i).split(" ");
				int x, y;
				String pos=split[1];
				String[] p=pos.split("-");
				x=Integer.parseInt(p[1]);
				y=Integer.parseInt(p[2]);
				actions.set(i, "activateRobot "+x+","+y);
				commands.add(new Activate(x,y));
			}else{
				System.out.println(actions.get(i));
			}
		}
		
	}
	
	public Direction dir(String dir){
		switch (dir) {
		case "left":
			return Direction.left;
		case "right":
			return Direction.right;
		case "up":
			return Direction.up;
		case "down":
			return Direction.down;
		default:
			return null;
		}
	}
	
	public ArrayList<String> getActions(){
		return actions;
	}
	public ArrayList<Command> getCommands(){
		return commands;
	}
	
	public String toString(){
		return commands.toString();
	}
}
