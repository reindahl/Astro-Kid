package world.objects;

import world.Point;
import world.World;

public class Teleport extends PhysObject {

	Point exit;
	public Teleport(World world, Point position, Point exit) {
		super(world, position);
		this.exit=exit;
	}

	public Point getExit(){
		return new Point(exit.getX(), exit.getY());
	}
	
	@Override
	public Character getChar() {
		return 't';
	}

	@Override
	public Boolean isSolid() {
		return false;
	}

}
