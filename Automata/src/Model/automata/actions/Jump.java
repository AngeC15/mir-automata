package Model.automata.actions;

import Model.automata.creation.DirectionExtension;
import Model.entities.Entity;

public class Jump extends Action {
	
	DirectionExtension dir;

	
	public Jump(DirectionExtension f) {
		this.dir = f;
	}

	@Override
	public boolean apply(Entity e) {
		e.Jump(dir);
		return false;
	}
}
