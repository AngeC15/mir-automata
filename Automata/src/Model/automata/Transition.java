package Model.automata;

import Model.automata.actions.Action;
import Model.automata.conditions.Condition;

public class Transition {
	public AutomatonState destination;
	public Condition condition;
	public Action action;

	public Transition(AutomatonState dst, Condition cond, Action act) {
		destination = dst;
		condition = cond;
		action = act;
	}

	@Override
	public String toString() {
		return " \nTransition [destination=" + destination.toString() + "\n condition=" + condition.toString()
				+ "\n action=" + action.toString() + "]";
	}

}
