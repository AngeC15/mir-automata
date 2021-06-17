package Model.automata.actions;

import Model.entities.Entity;

public class Explode extends Action {

	public Explode(float weight) {
		super(weight);
	}
	
	@Override
	public boolean apply(Entity e) {
		e.setAction(EnumAction.EXPLODE);
		e.Explode();
		return false;
	}
}
