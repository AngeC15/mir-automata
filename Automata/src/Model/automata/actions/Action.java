package Model.automata.actions;

import Model.Entity;
import Model.GameState;

public abstract class Action {
	public abstract boolean apply(Entity e, GameState s);
}
