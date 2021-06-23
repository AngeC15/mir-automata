package Tests;

import java.io.IOException;
import java.util.HashMap;

import Model.automata.Automaton;
import Model.loader.AutomataLoader;

public class Test_AutomataLoader {
	
	public static Automaton loader(String filename, String fileEntityAutomata) throws IOException {
		AutomataLoader.load_all(filename, fileEntityAutomata);
		Automaton automata = AutomataLoader.get("Mine");
		System.out.println(automata.getName());
		return automata;
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("Test Start");
		loader("src/Tests/automataloader_resources.txt", "src/Tests/entityAutomata.txt");
		System.out.println("Test End");
	}
	
}
