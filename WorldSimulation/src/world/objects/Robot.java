package world.objects;

import world.Point;
import world.World;

public class Robot extends MovableObject{

	Direction facing;
	
	public Robot(World world, Direction facing, Point position) {
		super(world,position);
		this.facing=facing;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean keepmoving() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void moveTo() {
		// TODO Auto-generated method stub

	}

}
