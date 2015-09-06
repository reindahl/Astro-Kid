package world.commands;

import world.World;
import world.objects.PhysObject.Direction;

public class Push implements Command {
	Direction dir;
	public Push(Direction dir) {
		this.dir=dir;
	}

	@Override
	public boolean Do(World world) {
		
		return world.playerAction(dir);
	}
	
	@Override
	public String toString(){
		return "Push "+dir;
	}
}
