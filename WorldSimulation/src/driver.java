import world.World;



public class driver {
	
	static World world;
	
	public static void main(String[] args) {
		loadWorld();
		gameloop();
	}

	private static void loadWorld() {
		// TODO Auto-generated method stub
		world=new World(1,1);
	}

	private static void gameloop() {
		while(!world.isGoal()){
			playerinput();
			world.update();

		}
		
	}



	private static void playerinput() {
		
		
	}

}