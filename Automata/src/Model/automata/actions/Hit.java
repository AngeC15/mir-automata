package Model.automata.actions;

import Model.automata.creation.DirectionExtension;
import Model.entities.Entity;

public class Hit extends Action{

	DirectionExtension dir;

	public Hit(DirectionExtension directionToRet, float weight) {
		super(weight);
		this.dir = directionToRet;
	}

	@Override
	public boolean apply(Entity e) {
		e.setAction(Enum_Action.HIT);
		e.Hit(dir);
		return false;
	}
}
