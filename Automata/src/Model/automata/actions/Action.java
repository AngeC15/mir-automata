package Model.automata.actions;

import Model.GameState;
import Model.entities.Entity;

/**
 * Hi
 * 
 * @author Cyprien, Julian, Samuel
 *
 */
public abstract class Action {

	/**
	 * 
	 * 
	 * @param e
	 * @param s
	 * @return
	 */
	public abstract boolean apply(Entity e, GameState s);
}
