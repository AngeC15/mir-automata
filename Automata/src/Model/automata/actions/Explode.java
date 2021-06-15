package Model.automata.actions;

import Model.entities.Entity;

public class Explode extends Action {

	public Explode() {
	}
	
	@Override
	public boolean apply(Entity e) {
		e.Explode();
		return false;
	}
}
