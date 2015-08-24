package world.objects;

import java.awt.Color;

import javax.swing.text.Position;

import world.Point;
import world.World;

public class Ground extends PhysObject{

	Color color;
	public Ground(World world, Point position) {
		super(world,position);
		// TODO Auto-generated constructor stub
	}

	public Ground(World world, Point position, Color color) {
		super(world, position);
		this.color=color;
		// TODO Auto-generated constructor stub
	}
	
	public Color getColor(){
		return color;
	}
}
