package Model.automata.actions;

import Model.World;
import Model.entities.Entity;

public class Wait extends Action{

	@Override
	public boolean apply(Entity e) {
		
		return true;
	}

}
