package Model.automata.actions;

import Model.entities.Entity;

/**
 * Defines what an action is
 * 
 * @author Cyprien, Julian, Samuel
 *
 */
public abstract class Action {
	public abstract boolean apply(Entity e);
}
