package Model.automata;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import Model.Entity;
import Model.GameState;
import Model.automata.actions.Action;
import Model.automata.conditions.Condition;

/**
 * An automaton is a list of states. Every state has an action, a condition and
 * a destination. Every state will had its own list.
 * 
 * @author Cyprien, Julian, Samuel
 *
 */
public class Automaton {
	private ArrayList<ArrayList<Transition>> states;

	/**
	 * Creates a new automaton with an empty transition list.
	 */
	public Automaton() {
		states = new ArrayList<ArrayList<Transition>>();
	}

	/**
	 * Adds a new list of transitions to a state.
	 * 
	 * @return the number of transitions
	 */
	public int addState() {
		states.add(new ArrayList<Transition>());
		return states.size();
	}

	/**
	 * Adds a new transition to the src state, with a destination, a condition and
	 * an action.
	 * 
	 * @param src  The starting state.
	 * @param dst  The final state.
	 * @param cond The linked condition.
	 * @param act  The linked action.
	 * @return The number of transitions of src.
	 */
	public int addTransition(int src, int dst, Condition cond, Action act) {
		/*
		 * TODO: Check if it is pertinent to make this security check.
		 * if(states.get(src) == null) return -1;
		 */
		states.get(src).add(new Transition(dst, cond, act));
		return states.get(src).size();
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
	public boolean step(Entity entity, GameState gs) {
		ArrayList<Transition> transitions = states.get(entity.getState());
		ArrayList<Transition> valid = new ArrayList<Transition>();
		for (int i = 0; i < transitions.size(); i++) {
			Transition t = transitions.get(i);
			if (t.condition.eval(entity, gs))
				valid.add(t);
		}
		if (valid.size() > 0) {
			int randIdx = (valid.size() == 1) ? 1 : ThreadLocalRandom.current().nextInt(0, valid.size());
			boolean r = valid.get(randIdx).action.apply(entity, gs);
			entity.setState(valid.get(randIdx).destination);
			return r;
		}
		return false;
	}
}
