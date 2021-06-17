package Model.automata.actions;

import Model.automata.creation.DirectionExtension;
import Model.entities.Entity;

public class Pick extends Action {

	DirectionExtension dir;

	public Pick(DirectionExtension directionToRet, float weight) {
		super(weight);
		this.dir = directionToRet;
	}

	@Override
	public boolean apply(Entity e) {
		e.setAction(EnumAction.PICK);
		e.Pick(dir);
		return false;
	}
}
