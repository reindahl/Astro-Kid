import gui.editor.gui;
import world.World;
import world.World.Color;



public class driver {
	
	static World world;
	
	public static void main(String[] args) {
		loadWorld();
		gameloop();
	}

	private static void loadWorld() {
		
		/**
		 * p  g
		 * ¤c¤¤
		 * @return
		 */
		world =new World(10,5);
		world.addPlayer(2,2);
		world.addGround(2,3);
		world.addGround(3,3,Color.green);
		world.addGround(4,3);
		world.addGround(5,3);
		world.addGoal(5,2);
		
		new gui(world);
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
