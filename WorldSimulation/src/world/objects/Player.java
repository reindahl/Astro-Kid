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
		PhysObject under=world.getLocation(relativTo(Direction.down));
		if(under instanceof Ground && ((Ground) under).getColor() != Color.green && ((Ground) under).getColor() != Color.brown && ((Ground) under).getColor() != wearing ){
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
		if((facing==Direction.down|| facing==Direction.up)&& world.isLadder(getPosition())){
			moving=false;
			return false;
		}
		
		return true;
	}


	@Override
	protected Boolean moveTo() {
		Point to = relativTo(facing);

		if(facing==Direction.up && world.isLadder(getPosition()) && (world.isClear(to) || (world.isLadder(to) && world.isClearMoveable(to)))){
			world.Move(getPosition(), to);
			this.setPosition(to);
			keepmoving();
			return true;
		}

		if((facing==Direction.down) && (world.isClear(to) || (world.isLadder(to) && world.isClearMoveable(to)))){
			world.Move(getPosition(), to);
			this.setPosition(to);
			keepmoving();
			return true;
		}

		if((facing==Direction.left ||facing==Direction.right) && world.isClear(to)){

			world.Move(getPosition(), to);
			this.setPosition(to);
			keepmoving();
			return true;
		}


		if((facing==Direction.left ||facing==Direction.right) && !world.isClear(to) && 
				world.getLocation(to) instanceof MovableObject && world.isClear(to.relativTo(facing))){

			MovableObject obj=((MovableObject) world.getLocation(to));
			obj.facing=facing;
			obj.moveTo();
			world.Move(getPosition(), to);
			this.setPosition(to);
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
	public void updatePosition() {
		Point below = relativTo(Direction.down);
		if(world.isClear(below) && !world.isLadder(getPosition())){
			//start falling
			world.Move(getPosition(), below);
			this.setPosition(below);
			keepmoving();
			return;
		}


		if(!moving){
			//it is not suppose to move
			return;
		}
		moveTo();
	}
	@Override
	public String toString(){
		return "Player "+getPosition();
	}

	@Override
	public Character getChar() {
		return 'p';
	}
	
	@Override
	public Boolean isSolid() {
		return true;
	}
	
	public Boolean isWalk(Direction direction){
		if(direction== Direction.left || direction==Direction.right){
			if(world.isClear(relativTo(direction))){
				return true;
			}
		}
		
		return false;
	}
	
	public Boolean isClimb(Direction direction){
		if(direction== Direction.up){
			if(world.isClear(relativTo(direction)) && world.isLadder(getPosition())){
				return true;
			}
			
			if(world.isClearMoveable(relativTo(direction)) && world.isLadder(getPosition()) && world.isLadder(relativTo(direction))){
				return true;
			}
		}
		if(direction== Direction.down){
			if(world.isClear(relativTo(direction)) && world.isLadder(relativTo(direction))){
				return true;
			}
			
			if(world.isClearMoveable(relativTo(direction)) && world.isLadder(relativTo(direction))){
				return true;
			}
		}
		return false;
	}
	
	public Boolean isPush(Direction direction){
		if(direction== Direction.left || direction== Direction.right){
			if(!world.isClearMoveable(relativTo(direction))){
				return true;
			}
		}

		return false;
	}

	public boolean hasRemote() {
		return !remotes.isEmpty();
	}
}