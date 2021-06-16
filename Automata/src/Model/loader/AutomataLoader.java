package Model.loader;

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

	private AutomataLoader() {
		automata = new HashMap<String, Automaton>();
	}

	public static void load(String filename) {
		instance.specific_load(filename);
	}

	private void specific_load(String filename) {
		try {
			AST ast = (AST) AutomataParser.from_file(filename);
			AstToObject visiteur = new AstToObject();
			List<Automaton> li = ((List<Automaton>) ast.accept(visiteur));
		} catch (Exception ex) {
			System.out.println(ex.getCause());
			System.out.println(ex.getClass());
			System.out.println(Arrays.toString(ex.getStackTrace()).replace(',', '\n'));
		}
	}

}
