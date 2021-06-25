package Model.automata.actions;

import Model.automata.creation.DirectionExtension;
import Model.entities.Entity;

public class Protect extends Action {

	DirectionExtension dir;

	public Protect(DirectionExtension directionToRet, float weight) {
		super(weight);
		this.dir = directionToRet;
	}

	@Override
	public boolean apply(Entity e) {
		e.addAction(EnumAction.PROTECT);
		e.Protect(dir);
		return false;
	}
}
