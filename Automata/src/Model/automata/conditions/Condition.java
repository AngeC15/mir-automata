package Model.automata.conditions;

import Model.World;
import Model.entities.Entity;

public abstract class Condition {
	public abstract boolean eval(Entity e);
}
