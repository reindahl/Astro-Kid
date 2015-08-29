package world;

import world.objects.PhysObject.Direction;

public class Point {
	int x,y;
	
	
	
	
	public Point(int x, int y) {
		this.x=x;
		this.y=y;
	}

	public Point(Point position) {
		this.x=position.x;
		this.y=position.y;
	}

	@Override
	public boolean equals(Object obj){
		if ( this == obj ) return true;
		  if ( !(obj instanceof Point) ){ 
			  return false;
		  }
		  Point that = (Point) obj;
		
		  return x==that.getX() && y==that.getY();
	}
	
	public Point relativTo(Direction dir){
		switch (dir) {
		case left:	
			return new Point(x-1,y);
		case right:
			return new Point(x+1,y);
		case up:
			return new Point(x,y-1);
		case down:
			return new Point(x,y+1);
		default:
			return null;
		}
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	@Override
	public String toString(){
		return x+":"+y;
	}
	
}
