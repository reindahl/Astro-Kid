package world.objects;

import java.util.ArrayList;

import world.Point;
import world.World;

public class Player extends MovableObject {
	public Player(World world) {
		super(world);
		// TODO Auto-generated constructor stub
	}

	ArrayList<Remote> remotes = new ArrayList<>();
	
	public boolean isLegal() {
		if(!super.isLegal()){
			return false;
		}
		world.getLocation(new Point(position.getX(), position.getY()-1));
		
		return true;
	}

	@Override
	public boolean keepmoving() {
		// TODO Auto-generated method stub
		
		switch (moving) {
		case up:
			return false;
		case down:
			
		case left:
			
		case right:	
		default:
			break;
		}
		
	
		
		return false;
	}
	
	
}
