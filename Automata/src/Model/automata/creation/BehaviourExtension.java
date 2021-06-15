package Model.automata.creation;

import java.util.List;

import Model.automata.Transition;


public class BehaviourExtension {
	//un comportement est défini par une suite de transitions
	List<Transition> transitions;

	public BehaviourExtension(List<Transition> transitions) {
		super();
		this.transitions = transitions;
	}
	
	

}
