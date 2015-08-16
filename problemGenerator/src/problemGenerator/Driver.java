package problemGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Driver {
	
	
	static Path path;
	
	public static void main(String[] args) {
		path= Paths.get("E:\\Dropbox\\Kandidat\\pddl\\test");

		
		createProblems(path);
		path= Paths.get("E:\\Dropbox\\Kandidat\\pddl\\AstroKid");
		createProblems(path);
		System.out.println("Done");
	}
	
	public static void createProblems(Path path){
		
		if(Files.isDirectory(path)){
			try {
				Files.newDirectoryStream(path, "*.lvl").forEach(p -> createProblem(p));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			createProblem(path);
		}
		
	}
	
	
	public static void createProblem(Path path){
		String filename=path.getFileName().toString().substring(0, path.getFileName().toString().length()-4).toString();
		Level level = null;
		try {
			level= new Level(Files.readAllLines(path), filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		Path pddl=path.getParent().resolve(filename+".pddl");
		
		try {
			Files.write(pddl,  level.getText());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
