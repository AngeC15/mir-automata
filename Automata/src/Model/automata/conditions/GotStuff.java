package Model.automata.conditions;

import Model.entities.Entity;

public class GotStuff extends Condition{

	@Override
	public boolean eval(Entity e) {
		e.GotStuff();
		return false;
	}
}
