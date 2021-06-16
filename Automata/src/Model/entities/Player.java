package Model.entities;

import Model.World;
import Model.automata.Automaton;
import Model.automata.AutomatonState;
import Model.automata.conditions.Key;
import Model.automata.conditions.operators.AndOperator;
import Model.automata.conditions.operators.NotOperator;
import Model.automata.creation.DirectionExtension;
import Model.automata.creation.KeyExtension;
import Model.automata.actions.Move;

public class Player extends Entity{

	public Player(World w) {
		super(new Automaton(new AutomatonState("default")), w);
		AutomatonState def = automaton.getInit();
		try {
			def.addTransition(def, def, new AndOperator(new Key(KeyExtension.Q), new AndOperator(new NotOperator(new Key(KeyExtension.S)), new NotOperator(new Key(KeyExtension.N)))) , new Move(DirectionExtension.O, 1.0f));
			def.addTransition(def, def, new AndOperator(new Key(KeyExtension.Z), new AndOperator(new NotOperator(new Key(KeyExtension.Q)), new NotOperator(new Key(KeyExtension.D)))) , new Move(DirectionExtension.N, 1.0f));
			def.addTransition(def, def, new AndOperator(new Key(KeyExtension.D), new AndOperator(new NotOperator(new Key(KeyExtension.S)), new NotOperator(new Key(KeyExtension.N)))) , new Move(DirectionExtension.E, 1.0f));
			def.addTransition(def, def, new AndOperator(new Key(KeyExtension.S), new AndOperator(new NotOperator(new Key(KeyExtension.Q)), new NotOperator(new Key(KeyExtension.D)))) , new Move(DirectionExtension.S, 1.0f));
			
			def.addTransition(def, def, new AndOperator(new Key(KeyExtension.Z), new Key(KeyExtension.Q)) , new Move(DirectionExtension.NO, 1.0f));
			def.addTransition(def, def, new AndOperator(new Key(KeyExtension.Z), new Key(KeyExtension.D)) , new Move(DirectionExtension.NE, 1.0f));
			def.addTransition(def, def, new AndOperator(new Key(KeyExtension.S), new Key(KeyExtension.D)) , new Move(DirectionExtension.SE, 1.0f));
			def.addTransition(def, def, new AndOperator(new Key(KeyExtension.S), new Key(KeyExtension.Q)) , new Move(DirectionExtension.SO, 1.0f));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		automaton.addState(def);
		automaton.setInit(def);
		
		
		// TODO Auto-generated constructor stub
	}

}
