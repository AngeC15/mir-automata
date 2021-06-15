package Model.automata;

import java.util.ArrayList;

import Model.automata.actions.Action;
import Model.automata.conditions.Condition;

public class AutomatonState {
		
		ArrayList<Transition> transitions; //potentiellement supprimable
		public String name;

		public AutomatonState(String name) {
			super();
			this.name = name;
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
		public int addTransition(AutomatonState src, AutomatonState dst, Condition cond, Action act) {
			transitions.add(new Transition(dst, cond, act));
			return transitions.size();
		}
		public void setTransition(ArrayList<Transition> list) {
			transitions = list;
		}
		public ArrayList<Transition> getTransitions(){
			return transitions;
		}

}
