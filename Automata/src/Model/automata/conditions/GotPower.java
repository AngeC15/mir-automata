package Model.automata.conditions;

import Model.entities.Entity;

public class GotPower extends Condition {

	@Override
	public boolean eval(Entity e) {
		return e.GotPower();
		
	}
}
