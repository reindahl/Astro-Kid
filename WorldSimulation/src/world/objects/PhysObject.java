package world.objects;


import world.Point;
import world.World;

public class PhysObject {
	public enum Direction{
		left, right, up, down
	}
	
	World world;
	
	Point position;
	Direction moving;
	
	public PhysObject(World world){
		this.world=world;
	}
	

	
	public PhysObject relativTo(Direction dir){
		switch (dir) {
		case left:	
			return world.getLocation(position.getX()-1,position.getY());
		case right:
			return world.getLocation(position.getX()+1,position.getY());
		case up:
			return world.getLocation(position.getX(),position.getY()+1);
		case down:
			return world.getLocation(position.getX(),position.getY()-1);
		default:
			return null;
		}
	}

	
	public Point getPosition(){
		return position;
	}
}
