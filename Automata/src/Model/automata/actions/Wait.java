package Model.automata.actions;

import Model.Entity;
import Model.GameState;

public class Wait extends Action{

	@Override
	public boolean apply(Entity e, GameState s) {
		
		return true;
	}

}
