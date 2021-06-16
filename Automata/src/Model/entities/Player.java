package Model.entities;

import Model.World;
import Model.automata.Automaton;
import Model.automata.AutomatonState;
import Model.automata.conditions.Key;
import Model.automata.creation.DirectionExtension;
import Model.automata.creation.KeyExtension;
import Model.automata.actions.Move;

public class Player extends Entity{

	public Player(World w) {
		super(new Automaton(new AutomatonState("default")), w);
		AutomatonState def = automaton.getInit();
		def.addTransition(def, def, new Key(KeyExtension.Q), new Move(DirectionExtension.O, 1.0f));
		def.addTransition(def, def, new Key(KeyExtension.Z), new Move(DirectionExtension.N, 1.0f));
		def.addTransition(def, def, new Key(KeyExtension.D), new Move(DirectionExtension.E, 1.0f));
		def.addTransition(def, def, new Key(KeyExtension.S), new Move(DirectionExtension.S, 1.0f));
		automaton.addState(def);
		automaton.setInit(def);
		
		
		// TODO Auto-generated constructor stub
	}

}
