package Model.automata.actions;

import Model.automata.creation.DirectionExtension;
import Model.entities.Entity;

public class Pick extends Action {

	DirectionExtension dir;

	public Pick(DirectionExtension directionToRet) {
		this.dir = directionToRet;
	}

	@Override
	public boolean apply(Entity e) {
		e.Pick(dir);
		return false;
	}
}
