package Model.automata;

import java.util.ArrayList;

import Model.automata.actions.Action;
import Model.automata.conditions.Condition;

/**
 * 
 * @author Julian, Cyprien
 *
 */
public class AutomatonState {
		

		ArrayList<Transition> transitions; //potentiellement supprimable
		public String name;

		public AutomatonState(String name) {
			super();
			this.transitions = new ArrayList<Transition>();
			this.name = name;
			transitions = new ArrayList<Transition>();
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
			Transition t = new Transition(dst, cond, act);
			transitions.add(t);
			return transitions.size();
		}
		public void setTransition(ArrayList<Transition> list) {
			transitions = list;
		}
		public ArrayList<Transition> getTransitions(){
			return transitions;
		}

		public int length() {
			
			return transitions.size();
		}
		
		@Override
		public String toString() {
			return "AutomatonState [name=" + name + "]";
		}

}
