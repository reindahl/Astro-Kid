package world.objects;

import java.awt.Color;
import java.util.ArrayList;

import world.Point;
import world.World;

public class Player extends MovableObject {
	public Player(World world, Point position) {
		super(world, position);

	}

	ArrayList<Remote> remotes = new ArrayList<>();
	Color wearing;

	@Override
	public boolean isLegal() {
		if(!super.isLegal()){
			return false;
		}
		PhysObject under= world.getLocation(new Point(position.getX(), position.getY()-1));
		if(under instanceof Ground && ((Ground) under).getColor() != wearing ){
			return false;

		}
		return true;
	}

	@Override
	public boolean keepmoving() {
		// TODO Auto-generated method stub

		switch (moving) {
		case up:
			moving=null;
			return false;
		case down:

		case left:

		case right:	
		default:
			break;
		}



		return false;
	}


	@Override
	protected void moveTo() {
		Point to = relativTo(moving);

		if(moving==Direction.up && world.isLadder(position) && world.isClear(to)){
			world.Move(position, to);
			this.position=to;

		}

		if(moving==Direction.down && world.isClear(to)){

			world.Move(position, to);
			this.position=to;

		}

		if((moving==Direction.left ||moving==Direction.right) && world.isClear(to)){

			world.Move(position, to);
			this.position=to;

		}

		if((world.getLocation(relativTo(Direction.down)) instanceof Ground)){
			Ground ground =(Ground) world.getLocation(relativTo(Direction.down));
			if(Color.GREEN!=ground.color){
				moving = null;
			}
		}else{
			moving = null;
		}


	}

	/**
	 * player given a command
	 * @param direction
	 */
	public Boolean move(Direction direction) {
		if(moving!=null){
			return false;
		}
		
		moving=direction;
		return true;

	}

	public Boolean push(Direction direction) {
		if(direction==Direction.down || Direction.up==direction){
			return false;
		}
		if(!(world.getLocation(relativTo(direction)) instanceof MovableObject)){
			return false;
		}

		if(moving!=null){
			return false;
		}
		world.getLocation(relativTo(direction)).moving=direction;
		return true;
	}

	@Override
	public String toString(){
		return "Player "+position;
	}
}
