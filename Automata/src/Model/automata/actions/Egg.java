package Model.automata.actions;

import Model.automata.creation.DirectionExtension;
import Model.entities.Entity;

public class Egg extends Action{
	
	DirectionExtension dir;

	public Egg(DirectionExtension directionToRet) {
		this.dir = directionToRet;
	}

	@Override
	public boolean apply(Entity e) {
		e.Egg(dir);
		return true;
	}

}
