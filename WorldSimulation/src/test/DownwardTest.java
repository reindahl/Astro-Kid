package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Test;

import world.Plan;

public class DownwardTest {

	@Test
	public void filterTest(){
		ArrayList<String> lines = readExample();
		
		Plan plan = new Plan(lines);
		System.out.println(plan.getActions());
		assertEquals(5, plan.getActions().size());
	}
	
	
	
	public ArrayList<String> readExample(){
		try {
			return (ArrayList<String>) Files.readAllLines(Paths.get("output example.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
