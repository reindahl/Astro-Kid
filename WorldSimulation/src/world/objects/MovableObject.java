package world.objects;

import world.World;

public abstract class MovableObject extends PhysObject{

	public MovableObject(World world) {
		super(world);
		// TODO Auto-generated constructor stub
	}

	public void updatePosition() {
		// TODO Auto-generated method stub
		
		if(world.isClear(position.getX(),position.getY()-1)){
			
			//TODO: start falling
			
			return;
		}
		
		
		if(moving==null){
			//it is not suppose to move
			return;
		}
		
		//update position
		
		//should it keep moving
		keepmoving();
	}
	
	public boolean isLegal() {
		if(position.getX()==0 || position.getX()== World.width-1 || position.getY()==World.height-1){
			return false;
		}
		return true;
	}
	
	//should it keep moving
	public abstract boolean keepmoving();
}
