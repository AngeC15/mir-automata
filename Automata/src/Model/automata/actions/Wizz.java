package Model.automata.actions;

import Model.automata.creation.DirectionExtension;
import Model.entities.Entity;

public class Wizz extends Action{
	
	DirectionExtension dir;

	public Wizz(DirectionExtension dir, float weight) {
		super(weight);
		this.dir = dir;
	}

	@Override
	public boolean apply(Entity e) {
		e.setAction(Enum_Action.WIZZ);
		e.Wizz(dir);
		return false;
	}
}
