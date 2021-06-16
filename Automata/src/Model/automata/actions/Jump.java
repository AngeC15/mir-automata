package Model.automata.actions;

import Model.automata.creation.DirectionExtension;
import Model.entities.Entity;

public class Jump extends Action {
	
	DirectionExtension dir;

	
	public Jump(DirectionExtension f, float weight) {
		super(weight);
		this.dir = f;
	}

	@Override
	public boolean apply(Entity e) {
		e.Jump(dir);
		return false;
	}
}
