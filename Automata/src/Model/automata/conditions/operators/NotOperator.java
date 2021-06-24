package Model.automata.conditions.operators;

import Model.automata.conditions.Condition;
import Model.entities.Entity;

public class NotOperator extends Condition {
	public Condition elm;

	public NotOperator(Condition elm) {
		this.elm = elm;
	}

	@Override
	public boolean eval(Entity entity) {
		return !elm.eval(entity);
	}

}
