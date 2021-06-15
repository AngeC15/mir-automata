package Model.automata.actions;

import Model.automata.creation.DirectionExtension;
import Model.entities.Entity;

public class Wizz extends Action{
	
	DirectionExtension dir;

	public Wizz(DirectionExtension dir) {
		super();
		this.dir = dir;
	}

	@Override
	public boolean apply(Entity e) {
		e.Wizz(dir);
		return false;
	}
}
