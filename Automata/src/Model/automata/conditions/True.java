package Model.automata.conditions;

import Model.World;
import Model.entities.Entity;

public class True extends Condition{
	public boolean eval(Entity e) {
		return true;
	}

}
