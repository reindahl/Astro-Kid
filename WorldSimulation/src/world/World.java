package world;

import java.util.ArrayList;
import java.util.Iterator;

import world.objects.Goal;
import world.objects.MovableObject;
import world.objects.PhysObject;
import world.objects.Player;

public class World {

	PhysObject fixedMap[][];
	PhysObject movingMap[][];
	
	ArrayList<MovableObject> movable= new ArrayList<>();
	
	Player player;
	
	Goal goal;
	public static int width;
	public static int height;
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

		    if (!obj.isLegal())
		        iter.remove();
		}
		
	}


	private void moveObjects() {
		for (MovableObject physObjects : movable) {
			physObjects.updatePosition();
		}
	}
	
	public boolean isClear(int x, int y){
		if(movingMap[x][y]!=null || fixedMap[x][y]!=null){
			return false;
		}
		return true;
	}


	public PhysObject getLocation(Point point) {
		// TODO Auto-generated method stub
		return null;
	}


	public PhysObject getLocation(int i, int y) {
		// TODO Auto-generated method stub
		return null;
	}
}
