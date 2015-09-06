package world.objects;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import world.Point;
import world.World;
import world.World.Type;

public class Robot extends MovableObject{


	int lastUpdated=-1;
	public Robot(World world, Direction facing, Point position) {
		super(world,position);
		this.facing=facing;
	}

	@Override
	public boolean keepmoving() {
		return true;
	}

	@Override
	protected Boolean moveTo() {
		if(lastUpdated==world.getSteps()){
			return true;
		}
		Point to = relativTo(facing);


		if(facing==Direction.down && world.isClear(to)){
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
		
		return false;
	}

	@Override
	public Character getChar() {
		if(facing==Direction.left){
			return'l';
		}else{
			return'r';
			
		}
		
	}
	@Override
	public String toString(){
		return "Player "+position;
	}

	public Direction getFacing() {
		return facing;
	}
	@Override
	public Boolean isSolid() {
		return true;
	}

	public void activate() {
		moving=true;
		
	}

	@Override
	public Type getType() {
		if(facing==Direction.right){
			return Type.robotRight;
		}else{
			return Type.robotLeft;
		}
		
	}
	
	@Override
	public Element toXml(Document doc) {
		Element boot = doc.createElement("Robot");
		boot.appendChild(position.toXml(doc));
		Element color = doc.createElement("Facing");
		color.appendChild(doc.createTextNode(facing.toString()));
		boot.appendChild(color);
		return boot;
	}
}
