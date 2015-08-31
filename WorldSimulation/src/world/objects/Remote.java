package world.objects;

import world.Point;
import world.World;

public class Remote extends PhysObject{

	public Remote(World world, Point position) {
		super(world, position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Character getChar() {
		// TODO Auto-generated method stub
		return 'n';
	}

	@Override
	public Boolean isSolid() {
		// TODO Auto-generated method stub
		return false;
	}

}
