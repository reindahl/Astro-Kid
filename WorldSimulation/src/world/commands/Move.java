package world.commands;

import world.World;
import world.objects.PhysObject.Direction;

public class Move implements Command {
	Direction dir;
	public Move(Direction dir) {
		this.dir=dir;
	}

	@Override
	public boolean Do(World world) {
		return world.playerAction(dir);
	}
	@Override
	public String toString(){
		return "Move "+dir;
	}
}
