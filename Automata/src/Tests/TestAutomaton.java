package Tests;

import Model.automata.Automaton;
import Model.automata.actions.Print;
import Model.automata.conditions.True;
import Model.entities.Entity;
import Utils.Vector2;

public class TestAutomaton {

	public static void main(String[] args) {
		Automaton a = new Automaton();
		a.addState();
		a.addTransition(0, 0, new True(), new Print("a"));
		a.addTransition(0, 0, new True(), new Print("b"));
		
		Entity e = new Entity(0, a, null, new Vector2(0, 0));
		e.step();
		e.step();
		e.step();
		e.step();
	}

}
