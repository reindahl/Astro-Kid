package world.objects;

import world.Point;
import world.World;

public class Goal extends PhysObject{

	public Goal(World world, Point position) {
		super(world,position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Character getChar() {
		return 'g';
	}



}
