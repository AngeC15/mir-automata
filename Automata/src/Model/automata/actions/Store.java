package Model.automata.actions;

import Model.entities.Entity;

public class Store extends Action {

	public Store(float weight) {
		super(weight);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean apply(Entity e) {
		e.addAction(EnumAction.STORE);
		e.Store();
		return false;
	}
}
