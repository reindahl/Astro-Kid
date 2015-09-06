import java.util.LinkedList;

import gui.editor.Gui;
import world.World;
import world.World.Color;
import world.commands.Command;
import world.commands.Move;
import world.commands.NoOp;
import world.objects.PhysObject.Direction;



public class driver {
	
	static World world;
	
	static LinkedList<Command> commands =new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
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
		
		new Gui(world, true);
		world.update();
		
		
		commands.add(new Move(Direction.right));
		commands.add(new NoOp());
		commands.add(new Move(Direction.left));
		commands.add(new NoOp());
		commands.add(new Move(Direction.left));
		
	}

	private static void gameloop() throws Exception {
		while(world.getPlayer()!=null && !world.isGoal()){
			playerinput();
			world.update();

		}
		System.out.println("Done");
	}



	private static void playerinput() throws Exception {
//		System.out.println("input command:");
//		try {
//			System.out.println("command "+System.in.read());
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
		try {
		    Thread.sleep(2000);                 //2000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		if(!commands.isEmpty()){
			if(!commands.poll().Do(world)){
				throw new Exception("illigal action");
			}
		}
	}

}
