package world.objects;

import java.awt.Color;

import world.Point;
import world.World;
import world.objects.PhysObject.Direction;

public class Stone extends MovableObject{

	public Stone(World world, Point position) {
		super(world,position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean keepmoving() {
		// TODO Auto-generated method stub
		

		
		return false;
	}
	
	@Override
	public boolean isLegal() {
		if(!super.isLegal()){
			return false;
		}
		PhysObject under= world.getLocation(new Point(position.getX(), position.getY()-1));
		if(under instanceof Ground && ((Ground) under).getColor() != null ){
			return false;
			
		}
		return true;
	}

	@Override
	protected void moveTo() {
		Point to = relativTo(moving);


		if(moving==Direction.down && world.isClear(to)){

			world.Move(position, to);
			this.position=to;

		}

		if((moving==Direction.left ||moving==Direction.right) && world.isClear(to)){

			world.Move(position, to);
			this.position=to;

		}

		if((world.getLocation(relativTo(Direction.down)) instanceof Ground)){
			Ground ground =(Ground) world.getLocation(relativTo(Direction.down));
			if(Color.GREEN!=ground.color){
				moving = null;
			}
		}else{
			moving = null;
		}


	}
	@Override
	public String toString(){
		return "Player "+position;
	}
}
