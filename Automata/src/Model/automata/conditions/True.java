package Model.automata.conditions;

import Model.entities.Entity;

public class True extends Condition {
	@Override
	public boolean eval(Entity e) {
		return true;
	}

}
