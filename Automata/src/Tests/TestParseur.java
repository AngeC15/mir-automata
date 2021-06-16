package Tests;

import java.util.Arrays;
import java.util.List;

import Model.automata.Automaton;
import Model.automata.AutomatonState;
import Model.automata.Transition;
import Model.automata.ast.AST;
import Model.automata.creation.AstToObject;
import Model.automata.parser.AutomataParser;
/**
 * 
 * @author cyprien, Julian
 *
 */
public class TestParseur {
	
	public static List<Automaton> loadAutomata(String filename) {
	    try {
	      AST ast = (AST) AutomataParser.from_file(filename);
	      AstToObject visiteur = new AstToObject();
	      List<Automaton> li =  ((List<Automaton>) ast.accept(visiteur));
	      return li;
	    
	    } catch (Exception ex) {
	    	System.out.println(ex.getCause());
	    	System.out.println(ex.getClass());
	    	System.out.println(Arrays.toString(ex.getStackTrace()).replace(',', '\n'));
	      return null;
	    }
	  }
	
	static void testDeAutomaton(List<Automaton> li) throws Exception{
		//verification du nombre d'automate differents
		if(li.size() != 3) {
			throw new Exception("Il n'y a pas le bon nombre d'automate retourné ");
		}
		for(Automaton auto : li) {
			String affichageAuto = auto.toString();
			System.out.println(affichageAuto);
			//pour chaque automatonState, on affiche ses transisitions
			for(AutomatonState etat : auto.getStates()) {
				System.out.println("Etat : " + etat.name);
				System.out.println("Les transitions sont :");
				if(etat.length() > 0) {
					for(Transition tr : etat.getTransitions()) {
						System.out.println(tr.toString());
					}
				}
			}
			
		}
		
		
		
	}

	public static void main(String[] args) throws Exception {
		List<Automaton> li = loadAutomata("src/Tests/GalAutomaton/philosophe.gal");
		testDeAutomaton(li);
		System.out.println("Test fini");

	}

}
