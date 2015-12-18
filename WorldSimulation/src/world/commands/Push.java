package world.commands;

import java.util.Arrays;

import world.World;
import world.objects.PhysObject.Direction;

public class Push extends Command {
	Direction dir;
	public Push(Direction dir) {
		this.dir=dir;
	}
	public Push(Direction dir, String[] strings) {
		this.dir=dir;
		parameters=strings;
	}
	@Override
	public boolean Do(World world) {
		if(parameters!=null && !Arrays.equals(parameters, getSuccessParameters(world))){
			System.out.println("Do failed: "+this);
			return false;
		}
		return world.playerAction(dir);
	}
	
	@Override
	public String toString(){
		return "Push "+dir;
	}

	@Override
	public String[] getSuccessParameters(World world) {
		// TODO Auto-generated method stub
		return null;
	}
}
