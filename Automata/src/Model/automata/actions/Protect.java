package Model.automata.actions;

import Model.automata.creation.DirectionExtension;
import Model.entities.Entity;

public class Protect extends Action {

	DirectionExtension dir;

	public Protect(DirectionExtension directionToRet) {
		this.dir = directionToRet;
	}
	
	@Override
	public boolean apply(Entity e) {
		e.Protect(dir);
		return false;
	}
}
