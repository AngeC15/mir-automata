package Model.automata.actions;

import Model.automata.creation.DirectionExtension;
import Model.entities.Entity;

/**
 */
public class Turn extends Action{

	DirectionExtension dir;

	public Turn(DirectionExtension directionToRet, float weight) {
		super(weight);
		this.dir = directionToRet;
	}

	@Override
	public boolean apply(Entity e) {
		e.Turn(dir);
		return false;
	}
}
