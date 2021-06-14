package Tests;

import Model.automata.Automaton;
import Model.automata.actions.Print;
import Model.automata.conditions.True;
import Model.entities.Entity;

public class TestAutomaton {

	public static void main(String[] args) {
		Automaton a = new Automaton();
		a.addState();
		a.addTransition(0, 0, new True(), new Print("a"));
		a.addTransition(0, 0, new True(), new Print("b"));
		
		Entity e = new Entity(0, a);
		e.step(null);
		e.step(null);
		e.step(null);
		e.step(null);
	}

}
