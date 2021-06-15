package Model.automata.actions;

import Model.automata.creation.DirectionExtension;
import Model.entities.Entity;

public class Move extends Action {
	
	DirectionExtension dir;

	
	public Move(DirectionExtension f) {
		this.dir = f;
	}

	@Override
	public boolean apply(Entity e) {
		e.Move(dir);
		return false;
	}
}
