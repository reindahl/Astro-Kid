package world.objects;



import world.Point;
import world.World;
import world.World.Color;

public class Stone extends MovableObject{

	int lastUpdated=-1;
	
	public Stone(World world, Point position) {
		super(world,position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean keepmoving() {
		if((world.getLocation(relativTo(Direction.down)) instanceof Ground)){
			Ground ground =(Ground) world.getLocation(relativTo(Direction.down));
			if(Color.green!=ground.color){
				moving = null;
				return false;
			}
		}else{
			moving = null;
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean isLegal() {
		if(!super.isLegal()){
			return false;
		}
		PhysObject under= world.getLocation(position.getX(), position.getY()-1);
		if(under instanceof Ground && ((Ground) under).getColor() != null ){
			return false;
			
		}
		return true;
	}

	@Override
	protected Boolean moveTo() {
		if(lastUpdated==world.getSteps()){
			return true;
		}
		Point to = relativTo(moving);


		if(moving==Direction.down && world.isClear(to)){

			world.Move(position, to);
			this.position=to;
			keepmoving();
			return true;
		}

		if((moving==Direction.left ||moving==Direction.right) && world.isClear(to)){
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
		// TODO Auto-generated method stub
		return 's';
	}
}
