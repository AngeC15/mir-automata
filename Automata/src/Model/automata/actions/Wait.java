package Model.automata.actions;

import Model.GameState;
import Model.entities.Entity;

public class Wait extends Action{

	@Override
	public boolean apply(Entity e, GameState s) {
		
		return true;
	}

}
