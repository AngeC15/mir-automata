package Model.entities;

import Model.World;
import Model.automata.Automaton;
import Model.automata.AutomatonState;

public class Player extends Entity{

	public Player(int id, World w) {
		super(id, new Automaton(), w);
		AutomatonState def = new AutomatonState("default");
		
		automaton.addState(def);
		automaton.setInit(def);
		
		
		// TODO Auto-generated constructor stub
	}

}
