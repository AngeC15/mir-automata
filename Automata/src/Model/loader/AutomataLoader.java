package Model.loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import Model.automata.Automaton;
import Model.automata.ast.AST;
import Model.automata.creation.AstToObject;
import Model.automata.parser.AutomataParser;

public class AutomataLoader {

	private static AutomataLoader instance = new AutomataLoader();
	private HashMap<String, Automaton> automata;
	private AstToObject visiteur;
	private ChangeAutomata entityAutomata;

	private AutomataLoader() {
		automata = new HashMap<String, Automaton>();
		visiteur = new AstToObject();
	}

	public static void load(String filename) {
		instance.load_(filename);
	}

	private void load_(String filename) {
		try {
			AST ast = (AST) AutomataParser.from_file(filename);
			List<Automaton> li = ((List<Automaton>) ast.accept(visiteur));
			String name;
			for (int k = 0; k < li.size(); k++) {
				name = li.get(k).getName();
				automata.put(name, li.get(k));
			}

		} catch (Exception ex) {
			System.out.println(ex.getCause());
			System.out.println(ex.getClass());
			System.out.println(Arrays.toString(ex.getStackTrace()).replace(',', '\n'));
		}

	}

	public static void load_all(String filename, String fileEntityAutomata) throws IOException {
		instance.load_all_(filename, fileEntityAutomata);
		
	}

	private void load_all_(String filename, String fileEntityAutomata) throws IOException {
		FileReader file = new FileReader(filename);
		BufferedReader br = new BufferedReader(file);
		String line;
		
		while ((line = br.readLine()) != null) {
			load_(line);
		}
		entityAutomata = new ChangeAutomata(fileEntityAutomata);
		
	}

	private Automaton getAutomaton_(String name) {
		return automata.get(name);

	}

	public static Automaton getAutomaton(String name) {
		return instance.getAutomaton_(name);
	}

	private Automaton get_(String name) {
		Automaton aut = entityAutomata.getAutomaton(name);
		if (aut != null) {
			return aut;
		}
		return automata.get(name);
	}

	/**
	 * Retrieves the automaton associated with the name. If the entity does not
	 * appear in the file it will default to the automaton bearing its name.
	 * 
	 * @param name - Name of the entity from which we wish to retrieve the automaton
	 * @return the associated automaton if the name is present in the file
	 *         entityAutomata.txt, the automaton with the same name otherwise.
	 */
	public static Automaton get(String name) {
		return instance.get_(name);
	}

}

