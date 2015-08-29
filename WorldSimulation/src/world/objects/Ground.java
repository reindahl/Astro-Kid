package world.objects;


import world.Point;
import world.World;
import world.World.Color;

public class Ground extends PhysObject{

	Color color;
	public Ground(World world, Point position) {
		super(world,position);
		color=Color.brown;
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
	
	@Override
	public String toString(){
		return "Ground: "+color+" "+position;
	}

	@Override
	public Character getChar() {
		if(color==Color.green){
			return 'c';
		}
		return '¤';
	}
	
	@Override
	public Boolean isSolid() {
		return true;
	}
}
