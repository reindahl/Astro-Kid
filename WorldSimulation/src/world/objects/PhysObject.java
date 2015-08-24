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
	
	public PhysObject(World world, Point position){
		this.position=position;
		this.world=world;
	}
	

	
	public Point relativTo(Direction dir){
		switch (dir) {
		case left:	
			return new Point(position.getX()-1,position.getY());
		case right:
			return new Point(position.getX()+1,position.getY());
		case up:
			return new Point(position.getX(),position.getY()-1);
		case down:
			return new Point(position.getX(),position.getY()+1);
		default:
			return null;
		}
	}

	
	public Point getPosition(){
		return position;
	}
}
