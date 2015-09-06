package world.objects;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import world.Point;
import world.World;
import world.World.Type;

public class Teleport extends PhysObject {

	Point exit;
	public Teleport(World world, Point position, Point exit) {
		super(world, position);
		this.exit=exit;
	}

	public Point getExit(){
		return new Point(exit.getX(), exit.getY());
	}
	
	@Override
	public Character getChar() {
		return 't';
	}

	@Override
	public Boolean isSolid() {
		return false;
	}

	@Override
	public Type getType() {
		return Type.teleport;
	}
	@Override
	public Element toXml(Document doc) {
		Element boot = doc.createElement("Teleport");
		boot.appendChild(position.toXml(doc));
		return boot;
	}
}
