package world;

import java.util.ArrayList;

public class Plan {

	ArrayList<String> actions = new ArrayList<>();
	
	public Plan(ArrayList<String> lines) {
		for (String string : lines) {
			if(string.endsWith("(1)")){
				actions.add(string);
			}
		}
		for (int i = 0; i < actions.size(); i++) {
			if(actions.get(i).startsWith("walk")){
				String[] split= actions.get(i).split(" ");
				actions.set(i, "walk "+split[split.length-2]);
			}else if(actions.get(i).startsWith("noOp")){
				actions.set(i, "noOp");
			}if(actions.get(i).startsWith("push")){
				String[] split= actions.get(i).split(" ");
				actions.set(i, "push "+split[split.length-2]);
			}if(actions.get(i).startsWith("climbDown")){
				String[] split= actions.get(i).split(" ");
				actions.set(i, "climbDown "+split[split.length-2]);
			}if(actions.get(i).startsWith("climbUp")){
				String[] split= actions.get(i).split(" ");
			
				actions.set(i, "climbUp "+split[split.length-2]);
			}if(actions.get(i).startsWith("activateRobot")){
				String[] split= actions.get(i).split(" ");
				int x, y;
				String pos=split[1];
				String[] p=pos.split("-");
				x=Integer.parseInt(p[1]);
				y=Integer.parseInt(p[2]);
				actions.set(i, "activateRobot "+x+","+y);
			}
		}
		
	}
	
	public ArrayList<String> getActions(){
		return actions;
	}
	

}
