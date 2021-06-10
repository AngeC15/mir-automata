package Model.automata;

import Model.automata.actions.Action;
import Model.automata.conditions.Condition;

public class Transition {
	public int destination;
	public Condition condition;
	public Action action;
	
	public Transition(int dst, Condition cond, Action act){
		destination = dst;
		condition = cond;
		action = act;
	}
}
