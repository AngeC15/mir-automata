package Model.automata.conditions;

import Model.Entity;
import Model.GameState;

public class True extends Condition{

	@Override
	public boolean eval(Entity e, GameState s) {
		return true;
	}
	
	

}
