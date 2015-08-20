package world;

public class Point {
	int x,y;
	
	
	
	
	public Point(int x, int y) {
		this.x=x;
		this.y=y;
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
