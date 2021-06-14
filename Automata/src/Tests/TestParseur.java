package Tests;

import java.util.List;

import Model.automata.ast.AST;
import Model.automata.ast.Automaton;
import Model.automata.creation.AstToObject;
import Model.automata.parser.AutomataParser;

public class TestParseur {

	List<Automaton> loadAutomata(String filename) {
	    try {
	      AST ast = (AST) AutomataParser.from_file(filename);
	      //...
	      // TODO à vous de constuire les automates à partir de l'AST
	      //...
	      Automaton automata = ast.accept(new AstToObject());
	      return automata ;
	    } catch (Exception ex) {
	      return null;
	    }
	  }

	public static void main() {
		loadAutomata("");
	}

}
