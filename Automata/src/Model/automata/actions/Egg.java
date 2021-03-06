package Model.automata.actions;

import Model.automata.creation.DirectionExtension;
import Model.entities.Entity;

public class Egg extends Action {

	DirectionExtension dir;

	public Egg(DirectionExtension directionToRet, float weight) {
		super(weight);
		this.dir = directionToRet;
	}

	@Override
	public boolean apply(Entity e) {
		e.addAction(EnumAction.EGG);
		e.Egg(dir);
		return true;
	}

}
