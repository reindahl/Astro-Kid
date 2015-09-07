package downward;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import world.Plan;

public class Down {




	public static void main(String[] args) throws InterruptedException,
	IOException {
		Plan plan =run("prob04.pddl");
		System.out.println(plan);
	}

	public static Plan run(String name) throws IOException, InterruptedException{
		///home/reindahl/downward
		//./fast-downward.py ../pddl/prob01.pddl --search "astar(blind())"
		
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

}
