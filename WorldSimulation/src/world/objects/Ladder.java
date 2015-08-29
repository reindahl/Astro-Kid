package world.objects;

import world.Point;
import world.World;

public class Ladder extends PhysObject{

	public Ladder(World world, Point position) {
		super(world, position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Character getChar() {
		return '#';
	}

}
