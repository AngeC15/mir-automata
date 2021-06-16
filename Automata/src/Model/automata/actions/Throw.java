package Model.automata.actions;

import Model.automata.creation.DirectionExtension;
import Model.entities.Entity;

public class Throw extends Action {

	DirectionExtension dir;

	public Throw(DirectionExtension directionToRet, float weight) {
		super(weight);
		this.dir = directionToRet;
	}
	
	@Override
	public boolean apply(Entity e) {
		e.setAction(Enum_Action.THROW);
		e.Throw(dir);
		return false;
	}
}
