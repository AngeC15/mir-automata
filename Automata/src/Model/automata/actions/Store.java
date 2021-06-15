package Model.automata.actions;

import Model.entities.Entity;

public class Store extends Action {

	@Override
	public boolean apply(Entity e) {
		e.Store();
		return false;
	}
}
