package Tests;

import java.util.Arrays;
import java.util.List;

import Model.automata.Automaton;
import Model.automata.ast.AST;
import Model.automata.creation.AstToObject;
import Model.automata.parser.AutomataParser;

public class TestParseur {
	static List<Automaton> loadAutomata(String filename) {
	    try {
	      AST ast = (AST) AutomataParser.from_file(filename);
	      //...
	      // TODO à vous de constuire les automates à partir de l'AST
	      //...
	      AstToObject visiteur = new AstToObject();
	      return (List<Automaton>) ast.accept(visiteur);
	    
	    } catch (Exception ex) {
	    	System.out.println(ex.getCause());
	    	System.out.println(ex.getClass());
	    	System.out.println(Arrays.toString(ex.getStackTrace()).replace(',', '\n'));
	      return null;
	    }
	  }

	public static void main(String[] args) {
		loadAutomata("src/Tests/GalAutomaton/philosophe.gal");
		//loadAutomata("src/Tests/GalAutomaton/twoState.gal");
		loadAutomata("src/Tests/GalAutomaton/mine.gal");

	}

}
