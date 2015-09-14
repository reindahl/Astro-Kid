package downward;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import world.Converter;
import world.Plan;
import world.World;

public class Down {

	public static Path downwardPath= Paths.get("/home/reindahl/downward/src/fast-downward.py");
	
	public static void main(String[] args) throws InterruptedException,	IOException {
		//		Plan plan =run("prob04.pddl");
		//		System.out.println(plan);

//		runAllProblems(Paths.get("levels/domain-simple.pddl"),false);
		runAllProblems(Paths.get("levels/domain.pddl"),false);
	}

	private static ArrayList<String> filterList(ArrayList<String> out) {
		ArrayList<String> filtered= new ArrayList<>();
		boolean cut= true;
		for (String string : out) {
			if(string.contains("Solution")){
				cut=false;
			}
			if(!cut){
				filtered.add(string);
			}
		}
		return filtered;
	}


	private static String output(InputStream inputStream) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + System.getProperty("line.separator"));
			}
		} finally {
			br.close();
		}
		return sb.toString();
	}


	private static ArrayList<String> outputToList(InputStream inputStream) throws IOException {
		ArrayList<String> list = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
		} finally {
			br.close();
		}
		return list;
	}

	private static void printListe(ArrayList<String> list){
		for (String string : list) {
			System.out.println(string);
		}
	}

	public static Plan run(Path problemPath) throws IOException, InterruptedException{
		return run(null, problemPath, false);
	}

	public static Plan run(Path problemPath, boolean output) throws IOException, InterruptedException {
		return run(null, problemPath, output);
	}
	public static Plan run(Path domainPath, Path problemPath, Boolean output) throws IOException, InterruptedException{
		//./fast-downward.py ../pddl/prob01.pddl --search "astar(blind())"
		//./fast-downward.py ../pddl/level4v2.pddl --search "astar(ff())"
		ProcessBuilder pb;
		if(domainPath!= null){
			pb = new ProcessBuilder(downwardPath.toString(), domainPath.toString(), problemPath.toString(), "--search", "astar(blind())");
		}else{
			pb = new ProcessBuilder(downwardPath.toString(), problemPath.toString(), "--search", "astar(blind())");
		}
		long startTime = System.currentTimeMillis();
		Process process = pb.start();
		int errCode = process.waitFor();
		double totalTime =(System.currentTimeMillis()-startTime)/1000.;
		//		System.out.println("downward executed, any errors? " + (errCode == 0 ? "No" : "Yes"));


		if(errCode==0){
			ArrayList<String> out =outputToList(process.getInputStream());
			out.add("TotalTime: "+totalTime+"s");
			if(output){
				String outputName = problemPath.toString();
				outputName=outputName.substring(0, outputName.length()-4)+"output";
				Files.write(Paths.get(outputName), out, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
			}
			out=filterList(out);
			return new Plan(out, totalTime);
		}else{
			
			System.out.println("downward eroor Output:\n" + output(process.getErrorStream()));
			return null;
		}
	}

	/**
	 * @deprecated
	 * @param name
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static Plan run(String name) throws IOException, InterruptedException{
		Path path = Paths.get(name);
		if(Files.exists(path)){
			return run(path);
		}
		///home/reindahl/downward
		//./fast-downward.py ../pddl/prob01.pddl --search "astar(blind())"
		//./fast-downward.py ../pddl/level4v2.pddl --search "astar(ff())"
		ProcessBuilder pb = new ProcessBuilder("/home/reindahl/downward/src/fast-downward.py", "/home/reindahl/downward/pddl/"+name, "--search", "astar(blind())");
		System.out.println("Run downward command");

		Process process = pb.start();
		int errCode = process.waitFor();
		System.out.println("downward executed, any errors? " + (errCode == 0 ? "No" : "Yes"));
		ArrayList<String> out =outputToList(process.getInputStream());
		out=filterList(out);
		System.out.println("downward Output:\n");
		printListe(out);
		System.out.println("downward eroor Output:\n" + output(process.getErrorStream()));

		return new Plan(out);
	}

	public static void runAllProblems(Path domain, Boolean output){
		Path levels= Paths.get("levels/");

		try {
			for (Path path : Files.newDirectoryStream(levels)) {
				if(path.getFileName().toString().matches("^prob\\d{2}(v\\d)?\\.xml$")){
					try {
						System.out.println(path);
						World world=new World(path);
						ArrayList<String> pddl = Converter.toPDDL(world, path.getFileName().toString().substring(0, path.getFileName().toString().length()));
						String outputName = path.toString();
						outputName=outputName.substring(0, outputName.length()-3)+"pddl";
						Files.write(Paths.get(outputName), pddl, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);

						Plan plan=run(Paths.get(outputName), output);
						if(domain!= null && Files.exists(domain)){
							plan=run(domain,Paths.get(outputName), output);
						}else{
							plan=run(Paths.get(outputName), output);
						}
						if(plan==null){
							System.out.println("downward errors");
						}else if(plan.getCommands().isEmpty()){
							System.out.println("failed to find solution");
						}else{
							System.out.println("found solution in "+plan.getTotalTime());
						}
						System.out.println();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void runAllProblems(Boolean output){
//		Path levels= Paths.get("levels/");
//
//		try {
//			for (Path path : Files.newDirectoryStream(levels)) {
//				if(path.getFileName().toString().matches("^prob\\d{2}(v\\d)?\\.xml$")){
//					try {
//						System.out.println(path);
//						World world=new World(path);
//						ArrayList<String> pddl = Converter.toPDDL(world, path.getFileName().toString().substring(0, path.getFileName().toString().length()));
//						String outputName = path.toString();
//						outputName=outputName.substring(0, outputName.length()-3)+"pddl";
//						Files.write(Paths.get(outputName), pddl, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
//
//						Plan plan=run(Paths.get(outputName), output);
//						
//						if(plan==null){
//							System.out.println("downward errors");
//						}else if(plan.getCommands().isEmpty()){
//							System.out.println("failed to find solution");
//						}else{
//							System.out.println("found solution in "+plan.getTotalTime());
//						}
//						System.out.println();
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//
//
//				}
//
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		runAllProblems(null, output);
	}

}
