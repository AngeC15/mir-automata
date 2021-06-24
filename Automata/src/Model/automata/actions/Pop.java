package Model.automata.actions;

import Model.automata.creation.DirectionExtension;
import Model.entities.Entity;

public class Pop extends Action {

	DirectionExtension dir;

	public Pop(DirectionExtension f, float weight) {
		super(weight);
		this.dir = f;

	}

	@Override
	public boolean apply(Entity e) {
		e.addAction(EnumAction.POP);
		e.Apply(dir);
		e.Pop(dir);
		return false;
	}
}
