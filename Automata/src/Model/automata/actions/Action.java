package Model.automata.actions;

import Model.entities.Entity;

/**
 * Defines what an action is
 * 
 * @author Cyprien, Julian, Samuel
 *
 */
public abstract class Action {
	
	float weight; //must be more than 0 ad less than 1
	
	public abstract boolean apply(Entity e);

	public float getWeigth() {
		return this.weight;
	}

	public Action(float weight) {
		this.weight = weight;
	}
}
