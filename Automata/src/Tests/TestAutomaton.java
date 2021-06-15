package Tests;

import Model.automata.Automaton;
import Model.automata.AutomatonState;
import Model.automata.actions.Print;
import Model.automata.conditions.True;
import Model.entities.Entity;
import Utils.Vector2;

public class TestAutomaton {

	public static void main(String[] args) {
		Automaton a = new Automaton();
		AutomatonState s = new AutomatonState("default");
		s.addTransition(s, s, new True(), new Print("a", 0.0f));
		s.addTransition(s, s, new True(), new Print("b", 0.0f));
		a.addState(s);
		
		
		Entity e = new Entity(0, a, null, new Vector2(0, 0));
		e.step();
		e.step();
		e.step();
		e.step();
	}

}
