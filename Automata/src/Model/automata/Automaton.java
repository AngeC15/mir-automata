package Model.automata;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import Model.entities.Entity;

/**
 * An automaton is a list of states. Every state has an action, a condition and
 * a destination. Every state will had its own list.
 * 
 * @author Cyprien, Julian, Samuel
 *
 */
public class Automaton {
	private ArrayList<AutomatonState> states;

	/**
	 * Creates a new automaton with an empty transition list.
	 */
	public Automaton() {
		states = new ArrayList<AutomatonState>();
	}

	/**
	 * Adds a new list of transitions to a state.
	 * 
	 * @return the number of transitions
	 */
	public int addState(AutomatonState s) {
		states.add(s);
		return states.size();
	}

	
	/**
	 * Takes every transition of the current state of the entity from states. Checks
	 * every condition of the current state in the automaton in order to create a
	 * list of valid states. Choose one random state among every valid state for the
	 * entity to take.
	 * 
	 * @param entity The entity using the automaton.
	 * @param gs     TODO NYI
	 * @return false if there is no valid transition or if the action was not
	 *         possible to perform (i.e. something trying to move into a wall), true
	 *         otherwise.
	 */
	public boolean step(Entity entity) {

		ArrayList<Transition> transitions = entity.getState().getTransitions();
		ArrayList<Transition> valid = new ArrayList<Transition>();
		for (int i = 0; i < transitions.size(); i++) {
			Transition t = transitions.get(i);
			if(t.condition.eval(entity))
				valid.add(t);
		}
		if(valid.size() > 0) {
			int randIdx = ThreadLocalRandom.current().nextInt(0, valid.size());
			boolean r = valid.get(randIdx).action.apply(entity);
			entity.setState(valid.get(randIdx).destination);
			return r;
		}
		return false;
	}
	public void print() {
		System.out.println("States:");
		for(int i=0; i < states.size(); i++) {
			AutomatonState as = states.get(i);
			System.out.println("	[" + i + "] " + as.name);
			
			for(int j=0; j < as.getTransitions().size(); j++) {
				Transition tr = as.getTransitions().get(j);
				System.out.println("(" + j + ") --> " + tr.destination.name);
			}
		}
	}
}
