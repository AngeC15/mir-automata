package Tests;

import java.io.IOException;

import Model.World;
import Model.automata.Automaton;
import Model.automata.AutomatonState;
import Model.automata.actions.Print;
import Model.automata.conditions.True;
import Model.entities.Entity;
import Utils.Vector2;
import View.Avatar;

public class TestAutomaton {

	public static void main(String[] args) {
		Automaton a = new Automaton("test");
		AutomatonState s = new AutomatonState("default");
		s.addTransition(s, s, new True(), new Print("a", 0.0f));
		s.addTransition(s, s, new True(), new Print("b", 0.0f));
		a.addState(s);
		
		
		Entity e;
		World w = new World(null);
		e = new Entity(a,3);
		e.step();
		e.step();
		e.step();
		e.step();
	}

}
