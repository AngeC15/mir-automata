package Model.automata.actions;

import Model.World;
import Model.entities.Entity;

public abstract class Action {
	public abstract boolean apply(Entity e);
}
