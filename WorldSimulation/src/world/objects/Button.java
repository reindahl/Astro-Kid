package world.objects;

import world.Point;
import world.World;
import world.World.Color;

public class Button extends PhysObject{

	Color color;
	
	public Button(World world, Point position, Color color) {
		super(world, position);
		this.color=color;
	}

	@Override
	public Character getChar() {
		return 'b';
	}

	@Override
	public Boolean isSolid() {
		return false;
	}
}
