package Model.automata.actions;

import Model.automata.creation.DirectionExtension;
import Model.entities.Entity;

public class Pop extends Action{

	DirectionExtension dir;

	
	public Pop(DirectionExtension f) {
		super();
		this.dir = f;
		
	}

	@Override
	public boolean apply(Entity e) {
		e.Apply(dir);
		return false;
	}
}
