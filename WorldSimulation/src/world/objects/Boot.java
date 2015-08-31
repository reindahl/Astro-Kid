package world.objects;

import world.Point;
import world.World;
import world.World.Color;

public class Boot extends PhysObject {
	Color color;
	public Boot(World world, Point position, Color color) {
		super(world, position);
		this.color=color;
	}

	@Override
	public Character getChar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isSolid() {
		return false;
	}
	
	public Color getColor(){
		return color;
	}

}
