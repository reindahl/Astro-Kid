package world.objects;

import world.Point;
import world.World;
import world.World.Type;

public class Ladder extends PhysObject{

	public Ladder(World world, Point position) {
		super(world, position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Character getChar() {
		return '#';
	}
	@Override
	public Boolean isSolid() {
		return false;
	}

	@Override
	public Type getType() {
		return Type.ladder;
	}
}
