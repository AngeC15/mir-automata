package Model.automata.actions;

import Model.automata.creation.DirectionExtension;
import Model.entities.Entity;

public class Wait extends Action{

	
	public Wait() {
	}

	@Override
	public boolean apply(Entity e) {
		e.Wait();
		return true;
	}

}
