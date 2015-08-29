package world.objects;

import java.util.ArrayList;

import world.Point;
import world.World;
import world.World.Color;

public class Gate extends PhysObject{
 
	Color color;
	
	public Gate(World world, Point position, Color color) {
		super(world, position);
		this.color=color;
	}

	@Override
	public Character getChar() {
		return 'w';
	}

	@Override
	public Boolean isSolid() {
		ArrayList<Button> buttons= world.getButtons(color);
		
		boolean result=false;
		for (Button button : buttons) {
			if(world.isClear(button.position)){
				result= true;
				break;
			}
		}
		return result;
	}
}
