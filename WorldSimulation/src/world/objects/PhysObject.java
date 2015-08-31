package world.objects;


import world.Point;
import world.World;

public abstract class PhysObject {
	public enum Direction{
		left, right, up, down
	}
	
	World world;
	
	protected Point position;

	
	public PhysObject(World world, Point position){
		this.position=position;
		this.world=world;
	}
	

	
	public Point relativTo(Direction dir){
		return position.relativTo(dir);

	}

	
	public Point getPosition(){
		return position;
	}
	public int getX() {
		
		return position.getX();
	}

	public int getY() {
		
		return position.getY();
	}
	public abstract Character getChar();
	
	public abstract Boolean isSolid();



	public void setPosition(Point position) {
		this.position = position;
	}
	
}
