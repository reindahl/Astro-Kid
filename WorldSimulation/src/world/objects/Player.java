package world.objects;

import java.util.ArrayList;

import world.Point;
import world.World;
import world.World.Color;

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

		if((world.getLocation(relativTo(Direction.down)) instanceof Ground)){
			Ground ground =(Ground) world.getLocation(relativTo(Direction.down));
			if(Color.green!=ground.color){
				moving=false;
				return false;
			}
		}
		return true;
	}


	@Override
	protected Boolean moveTo() {
		Point to = relativTo(facing);

		if(facing==Direction.up && world.isLadder(position) && world.isClear(to)){
			world.Move(position, to);
			this.position=to;
			keepmoving();
			return true;
		}

		if((facing==Direction.down) && world.isClear(to)){
			world.Move(position, to);
			this.position=to;
			keepmoving();
			return true;
		}

		if((facing==Direction.left ||facing==Direction.right) && world.isClear(to)){

			world.Move(position, to);
			this.position=to;
			keepmoving();
			return true;
		}


		if((facing==Direction.left ||facing==Direction.right) && !world.isClear(to) && 
				world.getLocation(to) instanceof MovableObject && world.isClear(to.relativTo(facing))){

			MovableObject obj=((MovableObject) world.getLocation(to));
			obj.facing=facing;
			obj.moveTo();
			world.Move(position, to);
			this.position=to;
			keepmoving();
			return true;
		}
		moving=false;
		return false;


	}

	/**
	 * player given a command
	 * @param direction
	 */
	public Boolean move(Direction direction) {
		if(moving){
			return false;
		}

		moving=true;
		facing=direction;
		return true;

	}

	public Boolean push(Direction direction) {
		if(direction==Direction.down || Direction.up==direction){
			return false;
		}
		if(!(world.getLocation(relativTo(direction)) instanceof MovableObject)){
			return false;
		}

		if(moving){
			return false;
		}
		((MovableObject) world.getLocation(relativTo(direction))).facing=direction;
		((MovableObject) world.getLocation(relativTo(direction))).moving=true;
		return true;
	}

	@Override
	public String toString(){
		return "Player "+position;
	}

	@Override
	public Character getChar() {
		return 'p';
	}
}
