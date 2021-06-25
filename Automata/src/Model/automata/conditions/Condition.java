package Model.automata.conditions;

import Model.entities.Entity;

public abstract class Condition {

	/**
	 * Checks the state of the condition.
	 * 
	 * @param e The Entity to test.
	 * @return true if the condition is true, false otherwise.
	 */
	public abstract boolean eval(Entity e);

	@Override
	public String toString() {
		return "Condition [" + getClass() + "]";
	}

}
