package Model.automata.actions;

import Model.automata.creation.DirectionExtension;
import Model.entities.Entity;

public class Move extends Action {

	DirectionExtension dir;

	public Move(DirectionExtension f, float weight) {
		super(weight);
		this.dir = f;
	}

	@Override
	public boolean apply(Entity e) {
		e.addAction(EnumAction.MOVE);
		e.Move(dir);
		return false;
	}
}
