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
			for (int k = 0 ; k < li.size() ; k ++) {
				name = li.get(k).getName();
				automata.put(name, li.get(k));
			}
		} catch (Exception ex) {
			System.out.println(ex.getCause());
			System.out.println(ex.getClass());
			System.out.println(Arrays.toString(ex.getStackTrace()).replace(',', '\n'));
		}
	}
	
	public static void load_all(String filename) throws IOException {
		instance.load_all_(filename);
	}
	
	private void load_all_(String filename) throws IOException {
		FileReader file = new FileReader(filename);
		BufferedReader br = new BufferedReader(file);
		String line;
		while((line = br.readLine()) != null) {
			load_(line);
		}
	}
	
	private Automaton get_(String name) {
		return automata.get(name);
	}
	
	public static Automaton get(String name){
		return instance.get_(name);
	}

}
