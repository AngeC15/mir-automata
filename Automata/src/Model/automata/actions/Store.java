package Model.automata.actions;

import Model.entities.Entity;

public class Store extends Action {

	
	
	public Store(float weight) {
		super(weight);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean apply(Entity e) {
		e.setAction(Enum_Action.STORE);
		e.Store();
		return false;
	}
}