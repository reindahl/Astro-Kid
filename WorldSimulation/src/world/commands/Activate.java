package world.commands;

import world.Point;
import world.World;

public class Activate implements Command {
	Point pos;
	public Activate(Point pos) {
		this.pos=pos;
	}

	public Activate(int x, int y) {
		pos=new Point(x, y);
	}

	@Override
	public boolean Do(World world) {
		return world.playerAction(pos);
	}
	@Override
	public String toString(){
		return "Activate "+pos;
	}
}
