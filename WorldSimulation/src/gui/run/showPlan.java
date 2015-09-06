package gui.run;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import gui.editor.Gui;
import world.Converter;
import world.Plan;
import world.World;
import world.commands.Command;

public class showPlan implements Runnable {
	
	World world;
	public showPlan(World world) {
		Path path= Paths.get("tmp.xml");
		try {
			world.toXml(path.toString());
			this.world=new World(path.toString());
			Files.deleteIfExists(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		Plan plan;
		try {

			Files.write(Paths.get("/home/reindahl/downward/pddl/run.pddl"), Converter.toPDDL(world, "run"));
			plan=downward.Down.run("run.pddl");
			System.out.println(plan);
			new Gui(world);
			ArrayList<Command> commands= plan.getCommands();
			for (Command command : commands) {
				try {
					Thread.sleep(2000);                 //2000 milliseconds is one second.
				} catch(InterruptedException ex) {
					Thread.currentThread().interrupt();
				}

				if(!command.Do(world)){
					throw new Exception("illigal action "+command);
				}
				world.update();
				
			}


		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
