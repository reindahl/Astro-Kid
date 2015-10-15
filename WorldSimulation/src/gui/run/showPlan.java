package gui.run;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import downward.Down;
import gui.editor.Gui;
import world.Converter;
import world.Converter.PDDL;
import world.Plan;
import world.World;
import world.commands.Command;

public class ShowPlan implements Runnable {
	Path domain = Down.domain;
	World world;
	
	PDDL pddl = PDDL.ManualGate;
	public ShowPlan(World world) {
		Path path= Paths.get("tmp.xml");
		try {
			world.toXml(path.toString());
			this.world=new World(path);
			Files.deleteIfExists(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public ShowPlan(World world, Path domain) {
		this.domain=domain;
		if(domain.equals(Down.domainNoUpdate)){
			pddl=PDDL.AxiomGate;
		}
		Path path= Paths.get("tmp.xml");
		try {
			world.toXml(path.toString());
			this.world=new World(path);
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

			Files.write(Paths.get("/home/reindahl/downward/pddl/run.pddl"), Converter.toPDDL(world, "run", pddl));
			double totalTime;
			long startTime;
			startTime = System.currentTimeMillis();
			plan=downward.Down.run(domain,Paths.get("/home/reindahl/downward/pddl/run.pddl"),true);
			totalTime = (System.currentTimeMillis() - startTime) / 1000.;
			System.out.println(totalTime);
			
			if(plan.getCommands().isEmpty()){
				JOptionPane.showMessageDialog(null, "No Solution Found", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			System.out.println(plan.getTime());
			System.out.println(plan);
			new Gui(world, false,false);

			ArrayList<Command> commands= plan.getCommands();
			for (Command command : commands) {

				Thread.sleep(1000);


				if(!command.Do(world)){
					throw new Exception("illigal action "+command);
				}
				world.update();

			}
			Thread.sleep(2000);
			world.update();


		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
