package Model.automata.creation;

import Model.automata.AutomatonState;

/**
 * A Mode is a state with a behaviour, and a behaviour is a list of transition
 * 
 * @author cyprien
 *
 */

public class ModeExtension {
	AutomatonState state;
	BehaviourExtension behaviour;

	public ModeExtension(AutomatonState state, BehaviourExtension behaviour) {
		super();
		this.state = state; // Etat ou tu es
		this.behaviour = behaviour;
	}

}
