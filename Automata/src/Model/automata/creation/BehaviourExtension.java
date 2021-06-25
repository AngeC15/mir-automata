package Model.automata.creation;

import java.util.List;

import Model.automata.Transition;

/**
 * 
 * @author cyprien
 *
 */

public class BehaviourExtension {
	// A behaviour is defined by a list of transitions.
	List<Transition> transitions;

	public BehaviourExtension(List<Transition> transitions) {
		super();
		this.transitions = transitions;
	}

}
