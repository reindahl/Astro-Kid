package world.objects;



import org.w3c.dom.Document;
import org.w3c.dom.Element;

import world.Point;
import world.World;
import world.World.Color;
import world.World.Type;

public class Stone extends MovableObject{

	
	public Stone(World world, Point position) {
		super(world,position);
	}

	@Override
	public boolean keepmoving() {
		PhysObject above = world.getLocation(relativTo(Direction.up));
		if(above instanceof MovableObject){
			((MovableObject) above).moving=true;
		}
		if(!isLegal()){
			moving=false;
			return false;
		}
		PhysObject under =world.getLocation(relativTo(Direction.down));
		if(under instanceof Ground && ((Ground) under).getColor()==Color.green){
			return true;
		}
		
		moving=false;
		return false;
		
//		if((world.getLocation(relativTo(Direction.down)) instanceof Ground)){
//			Ground ground =(Ground) world.getLocation(relativTo(Direction.down));
//			if(Color.green!=ground.color){
//				moving=false;
//				return false;
//			}
//		}
//		return true;
	}
	
	@Override
	public boolean isLegal() {
		if(!super.isLegal()){
			return false;
		}
		PhysObject under= world.getLocation(relativTo(Direction.down));
		if(under instanceof Ground && (((Ground) under).getColor() == Color.blue || ((Ground) under).getColor() == Color.purple)){
			return false;
			
		}
		return true;
	}

	@Override
	protected Boolean moveTo() {
		if(lastUpdated==world.getSteps()){
			return true;
		}else{
			lastUpdated=world.getSteps();
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
	public String toString(){
		return "Stone "+position;
	}

	@Override
	public Character getChar() {
		return 's';
	}
	
	@Override
	public Boolean isSolid() {
		return true;
	}

	@Override
	public Type getType() {
		return Type.stone;
	}
	@Override
	public Element toXml(Document doc) {
		Element boot = doc.createElement("Stone");
		boot.appendChild(position.toXml(doc));
		return boot;
	}
}
