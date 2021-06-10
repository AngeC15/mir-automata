package Model.automata.conditions;

import Model.Entity;
import Model.GameState;

public abstract class Condition {
	
	
	public abstract boolean eval(Entity e, GameState s);	


}
