package world.objects;

import world.World;

public class Robot extends MovableObject{

	Direction facing;
	
	public Robot(World world, Direction facing) {
		super(world);
		this.facing=facing;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean keepmoving() {
		// TODO Auto-generated method stub
		return false;
	}

}
