package world.objects;

import world.Point;
import world.World;

public class Robot extends MovableObject{

	Direction facing;
	int lastUpdated=-1;
	public Robot(World world, Direction facing, Point position) {
		super(world,position);
		this.facing=facing;
		// TODO Auto-generated constructor stub
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
		Point to = relativTo(moving);


		if(moving==Direction.down && world.isClear(to)){
			world.Move(position, to);
			this.position=to;
			keepmoving();
			moving=facing;
			return true;
		}

		if((moving==Direction.left ||moving==Direction.right) && world.isClear(to)){
			facing=moving;
			world.Move(position, to);
			this.position=to;
			keepmoving();
			return true;
		}
		
		moving=null;
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

}
