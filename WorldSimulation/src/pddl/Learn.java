package pddl;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import downward.Down;
import pddl.Hypothesis.PlanType;
import world.Plan;
import world.World;
import world.commands.Command;
import world.commands.Move;
import world.objects.PhysObject.Direction;

public class Learn {
	static Path pProblem = Paths.get("learning/problem.pddl");
	static Path pDomain = Paths.get("learning/domain.pddl");
	static Hypothesis hypo = null;
	public static void main(String[] args) throws Exception {

		tutorial1();
		learnGui();
		hypo.toPDDL(pDomain);
	}
	

	public static void learnGui() throws Exception{
		System.out.println("--------------------------------------------------");
		do{
			JFileChooser chooser = new JFileChooser("levels");
			chooser.showOpenDialog(null);
			if(chooser.getSelectedFile()==null){
				System.out.println("no file selected");
				return;
			}
			Path path = Paths.get(chooser.getSelectedFile().getPath());
			World world = new World(path);

//			Converter.toPDDL(world, pProblem);

			PddlProblem prob = new PddlProblem(world);
			prob.toPDDL(pProblem);
			
			if(hypo==null){
				hypo = new Hypothesis(prob,PlanType.pessimistic);
			}else{
				hypo.setProblem(prob);
			}

			System.out.println("testing");
			i=0;
			while (!testHyoptehsis(hypo, world) && i!=0){
				System.out.println("iteration "+i);
				System.out.println("________________________________");
				i++;
//				break;
			};
			PddlProblem.clear();
		}while(JOptionPane.OK_OPTION==JOptionPane.showConfirmDialog(
				null,
				"Would you like continue to learn",
				"An Inane Question",
				JOptionPane.YES_NO_OPTION));
		
		hypo.toPDDL(pDomain);

	}

	static int i=0;
	public static boolean testHyoptehsis(Hypothesis hypo, World world) throws IOException, InterruptedException{
		
		hypo.toPDDL(pDomain,i);
		Plan plan=Down.run(pDomain, pProblem,true);
		System.out.println("plan "+ plan);
		if(plan.getCommands().isEmpty()){
			return false;
		}
		i=0;
		return executePlan(hypo, world, plan.getCommands());
	}
	
	public static Boolean executePlan(Hypothesis hypo, World world, Collection<Command> commands){
		boolean result =true;
		PddlProblem before;
		PddlProblem after;
		hypo.setProblem(new PddlProblem(world));
		for (Command command : commands) {
			before = new PddlProblem(world);
			if(command.parameters==null){
				command.parameters=command.getSuccessParameters(world);
			}
			if(command.Do(world)){
				world.update();
				after = new PddlProblem(world);
				hypo.addKnowledge(command, before, after);
			}else{
				hypo.addKnowledge(command, before);
				result=false;
				break;
			}
			
		}
		
		return result;
	}
	
	public static void tutorial1(){
		System.out.println("tutorial 1");
		Path path = Paths.get("levels/level01intro.xml");
		World world = new World(path);

		ArrayList<Command> commands= new ArrayList<>();
		commands.add(new Move(Direction.right));
		commands.add(new Move(Direction.right));
		commands.add(new Move(Direction.right));
		commands.add(new Move(Direction.up));
		commands.add(new Move(Direction.up));
		commands.add(new Move(Direction.right));
		commands.add(new Move(Direction.right));
		commands.add(new Move(Direction.right));
		
		PddlProblem prob = new PddlProblem(world);
		if(hypo==null){
			
			hypo = new Hypothesis(prob,PlanType.pessimistic);
		}else{
			hypo.setProblem(prob);
		}

		
		executePlan(hypo, world, commands);
		
		PddlProblem.clear();

	}
}
