package downward;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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

	public static Path downwardPath = Paths.get("/home/reindahl/downward/src/fast-downward.py");

	public static Path simpleDomain = Paths.get("levels/domain-simple.pddl");

	public static Path domain = Paths.get("pddl/domain.pddl");
	public static Path domain2 = Paths.get("pddl/domain2.pddl");
	
	public static void main(String[] args) throws InterruptedException, IOException {
//		generatePDDL(Paths.get("levels"));
		
		runAllProblems(domain2);
//		Path problemPath =Paths.get("pddl/level09.pddl");
//		System.out.println(problemPath);
//
//		double totalTime;
//		long startTime;
//
//		System.out.println("translate+preprocess");
//		startTime = System.currentTimeMillis();
//		generateSas(domain, problemPath);
//		totalTime = (System.currentTimeMillis() - startTime) / 1000.;
//		System.out.println(totalTime);
//		
//		System.out.println("search");
//		startTime = System.currentTimeMillis();
//		runSas(Paths.get("output"), true);
//		totalTime = (System.currentTimeMillis() - startTime) / 1000.;
//		System.out.println(totalTime);
	}
	
	private static ArrayList<String> filterList(ArrayList<String> out) {
		ArrayList<String> filtered = new ArrayList<>();
		boolean cut = true;
		for (String string : out) {
			if (string.contains("Solution")) {
				cut = false;
			}
			if (!cut) {
				filtered.add(string);
			}
		}
		return filtered;
	}

	public static void generatePDDL(Path path) throws FileNotFoundException {
		if (Files.notExists(path)) {
			throw new FileNotFoundException();
		}
		if (Files.isDirectory(path)) {

			try {
				Files.newDirectoryStream(path).forEach(e -> {
					try {
						generatePDDL(e);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (path.toString().endsWith(".xml")) {

			World world = new World(path);
			String name = path.getFileName().toString();
			name = name.substring(0, name.length() - 4);
			name += ".pddl";
			try {
				Converter.toPDDL(world, Paths.get("pddl", name));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public static Path generateSas(Path domainPath, Path problemPath) throws IOException, InterruptedException {
		// --translate --preprocess
		ProcessBuilder pb;
		if (domainPath != null) {
			pb = new ProcessBuilder(downwardPath.toString(), "--translate", "--preprocess", domainPath.toString(),
					problemPath.toString());
		} else {
			pb = new ProcessBuilder(downwardPath.toString(), "--translate", "--preprocess", problemPath.toString());
		}
		long startTime = System.currentTimeMillis();
		Process process = pb.start();
		int errCode = process.waitFor();
		double totalTime = (System.currentTimeMillis() - startTime) / 1000.;
		// System.out.println("downward executed, any errors? " + (errCode == 0
		// ? "No" : "Yes"));

		if (errCode == 0) {
			ArrayList<String> out = outputToList(process.getInputStream());
			out.add("TotalTime: " + totalTime + "s");
			String outputName = problemPath.toString();
			outputName = outputName.substring(0, outputName.length() - 4) + "outputSas";
			Files.write(Paths.get(outputName), out, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING,
					StandardOpenOption.CREATE);

			return Paths.get("output");
		} else {

			System.out.println("downward eroor Output:\n" + output(process.getErrorStream()));
			return null;
		}
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

	public static Plan run(Path problemPath) throws IOException, InterruptedException {
		return run(null, problemPath, false);
	}

	public static Plan run(Path problemPath, boolean output) throws IOException, InterruptedException {
		return run(null, problemPath, output);
	}

	public static Plan run(Path domain, Path problemPath) throws IOException, InterruptedException {
		return run(domain, problemPath, false);
	}

	public static Plan run(Path domainPath, Path problemPath, Boolean output) throws IOException, InterruptedException {
		// ./fast-downward.py ../pddl/prob01.pddl --search "astar(blind())"
		// ./fast-downward.py ../pddl/level4v2.pddl --search "astar(ff())"
		ProcessBuilder pb;
		if (domainPath != null) {
			pb = new ProcessBuilder(downwardPath.toString(), domainPath.toString(), problemPath.toString(), "--search",
					"astar(ff())");
		} else {
			pb = new ProcessBuilder(downwardPath.toString(), problemPath.toString(), "--search", "astar(ff())");
		}
		long startTime = System.currentTimeMillis();
		Process process = pb.start();
		int errCode = process.waitFor();
		double totalTime = (System.currentTimeMillis() - startTime) / 1000.;
		// System.out.println("downward executed, any errors? " + (errCode == 0
		// ? "No" : "Yes"));

		if (errCode == 0) {
			ArrayList<String> out = outputToList(process.getInputStream());
			out.add("TotalTime: " + totalTime + "s");
			if (output) {
				String outputName = problemPath.toString();
				outputName = outputName.substring(0, outputName.length() - 4) + "output";
				Files.write(Paths.get(outputName), out, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING,
						StandardOpenOption.CREATE);
			}
			out = filterList(out);

			return new Plan(out, totalTime);
		} else {

			System.out.println("downward eroor Output:\n" + output(process.getErrorStream()));
			return new Plan(totalTime);
		}
	}

	/**
	 * @param name
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static Plan run(String name) throws IOException, InterruptedException {
		Path path = Paths.get(name);
		if (Files.exists(path)) {
			return run(path);
		} else {
			return run(Paths.get("/home/reindahl/downward/pddl/" + name));

		}
	}
	
	public static void runAllProblems() {

		runAllProblems(null, false);
	}

	public static void runAllProblems(Boolean output) {

		runAllProblems(null, output);
	}	
	public static void runAllProblems(Path domain) {

		runAllProblems(domain, false);
	}
	public static void runAllProblems(Path domain, Boolean output) {
		Path levels = Paths.get("levels/");

		try {
			for (Path path : Files.newDirectoryStream(levels)) {
				if (path.getFileName().toString().matches("^prob\\d{2}(v\\d)?\\.xml$")) {
					try {
						System.out.println(path);
						World world = new World(path);
						ArrayList<String> pddl = Converter.toPDDL(world,
								path.getFileName().toString().substring(0, path.getFileName().toString().length() - 4));
						String outputName = path.toString();
						outputName = outputName.substring(0, outputName.length() - 3) + "pddl";
						Files.write(Paths.get(outputName), pddl, StandardCharsets.UTF_8,
								StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);

						Plan plan;
						if (domain != null && Files.exists(domain)) {
							plan = run(domain, Paths.get(outputName), output);
						} else {
							plan = run(Paths.get(outputName), output);
						}
						if (plan == null) {
							System.out.println("downward errors");
						} else if (plan.getCommands().isEmpty()) {
							System.out.println("failed to find solution");
						} else {
							System.out.println("found solution in " + plan.getTotalTime());
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

	public static Plan runSas(Path sasPath, Boolean output) throws IOException, InterruptedException {

		ProcessBuilder pb;

		pb = new ProcessBuilder(downwardPath.toString(),
				"/media/reindahl/Backup/GitHub/Astro-Kid/WorldSimulation/" + sasPath.toString(), "--search",
				"astar(ff())");
		long startTime = System.currentTimeMillis();
		Process process = pb.start();
		int errCode = process.waitFor();
		double totalTime = (System.currentTimeMillis() - startTime) / 1000.;
		// System.out.println("downward executed, any errors? " + (errCode == 0
		// ? "No" : "Yes"));

		if (errCode == 0) {
			ArrayList<String> out = outputToList(process.getInputStream());
			out.add("TotalTime: " + totalTime + "s");
			if (output) {
				String outputName = sasPath.toString();
				outputName = outputName.substring(0, outputName.length()) + ".Search";
				Files.write(Paths.get(outputName), out, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING,
						StandardOpenOption.CREATE);
			}
			out = filterList(out);

			return new Plan(out, totalTime);
		} else {
			outputToList(process.getInputStream()).forEach(e -> System.out.println(e.toString()));
			System.out.println("downward eroor Output:\n" + output(process.getErrorStream()));
			return null;
		}
	}

	public static Path translate(Path domainPath, Path problemPath) throws IOException, InterruptedException {
		// --translate --preprocess
		ProcessBuilder pb;
		if (domainPath != null) {
			pb = new ProcessBuilder(downwardPath.toString(), "--translate", domainPath.toString(),
					problemPath.toString());
		} else {
			pb = new ProcessBuilder(downwardPath.toString(), "--translate", problemPath.toString());
		}
		long startTime = System.currentTimeMillis();
		Process process = pb.start();
		int errCode = process.waitFor();
		double totalTime = (System.currentTimeMillis() - startTime) / 1000.;
		// System.out.println("downward executed, any errors? " + (errCode == 0
		// ? "No" : "Yes"));

		if (errCode == 0) {
			ArrayList<String> out = outputToList(process.getInputStream());
			out.add("TotalTime: " + totalTime + "s");
			String outputName = problemPath.toString();
			outputName = outputName.substring(0, outputName.length() - 4) + "outputTranslate";
			Files.write(Paths.get(outputName), out, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING,
					StandardOpenOption.CREATE);

			return null;
		} else {

			System.out.println("downward eroor Output:\n" + output(process.getErrorStream()));
			return null;
		}
	}
}
