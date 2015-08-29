package world.objects;

import world.Point;
import world.World;

public class Robot extends MovableObject{


	int lastUpdated=-1;
	public Robot(World world, Direction facing, Point position) {
		super(world,position);
		this.facing=facing;
	}

	@Override
	public boolean keepmoving() {
		return true;
	}

	@Override
	protected Boolean moveTo() {
		if(lastUpdated==world.getSteps()){
			return true;
		}
		Point to = relativTo(facing);


		if(facing==Direction.down && world.isClear(to)){
			world.Move(position, to);
			this.position=to;
			keepmoving();
			return true;
		}

		if((facing==Direction.left ||facing==Direction.right) && world.isClear(to)){
			
			world.Move(position, to);
			this.position=to;
			keepmoving();
			return true;
		}
		
		return false;
	}

	@Override
	public Character getChar() {
		if(facing==Direction.left){
			return'l';
		}else{
			return'r';
			
		}
		
	}
	@Override
	public String toString(){
		return "Player "+position;
	}

	public Direction getFacing() {
		return facing;
	}
	@Override
	public Boolean isSolid() {
		return true;
	}
}
