package Model.automata;

import Model.automata.actions.Action;
import Model.automata.conditions.Condition;
import Model.automata.creation.StateExtension;

public class Transition {
	public StateExtension destination;
	public Condition condition;
	public Action action;
	
	public Transition(StateExtension dst, Condition cond, Action act){
		destination = dst;
		condition = cond;
		action = act;
	}
}
