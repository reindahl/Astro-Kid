package world.objects;

import world.Point;
import world.World;

public abstract class MovableObject extends PhysObject{

	Direction moving;
	
	public MovableObject(World world, Point position) {
		super(world, position);
		// TODO Auto-generated constructor stub
	}

	public void updatePosition() {
		if(world.isClear(position.relativTo(Direction.down))){
			
			//TODO: start falling
			moving=Direction.down;
		}
		
		
		if(moving==null){
			//it is not suppose to move
			return;
		}
		moveTo();
	}
	


	/**
	 * moves the object to the new position if it is legal
	 * @param to
	 */
	protected abstract Boolean moveTo();

	public boolean isLegal() {
		if(position.getX()==0 || position.getX()== world.width-1 || position.getY()==world.height-1){
			return false;
		}
		return true;
	}
	
	//should it keep moving
	protected abstract boolean keepmoving();
}
