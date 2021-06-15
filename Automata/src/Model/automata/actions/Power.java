package Model.automata.actions;

import Model.entities.Entity;

public class Power extends Action{

	@Override
	public boolean apply(Entity e) {
		e.Power();
		return false;
	}
}
