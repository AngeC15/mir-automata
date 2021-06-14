package Model.automata.actions;

import Model.automata.creation.DirectionExtension;
import Model.entities.Entity;

public class Hit extends Action{

	DirectionExtension dir;

	public Hit(DirectionExtension directionToRet) {
		this.dir = directionToRet;
	}

	@Override
	public boolean apply(Entity e) {
		e.Hit(dir);
		return false;
	}
}
