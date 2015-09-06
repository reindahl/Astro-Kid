package world.commands;

import world.World;

public class NoOp implements Command{

	public NoOp() {
	}

	@Override
	public boolean Do(World world) {
		return true;
	}
	@Override
	public String toString(){
		return "NoOp";
	}
}
