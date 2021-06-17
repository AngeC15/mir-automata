package Tests;

import java.io.IOException;
import java.util.HashMap;

import Model.automata.Automaton;
import Model.loader.AutomataLoader;

public class Test_AutomataLoader {
	
	public static Automaton loader(String filename) throws IOException {
		AutomataLoader.load_all(filename);
		Automaton automata = AutomataLoader.get("Mine");
		return automata;
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("Test Start");
		loader("src/Tests/automataloader_ressources.txt");
		System.out.println("Test End");
	}
	
}
