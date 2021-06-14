package Model.entities;

import Model.GameState;
import Model.automata.Automaton;
import Model.automata.creation.StateExtension;

public class Entity {
	protected StateExtension state;
	protected Automaton automaton;
	int id;
	
	public Entity(int id, Automaton a) {
		this.id = id;
		automaton = a;
	}
	public int getID() {
		return id;
	}
	public StateExtension getState() {
		return state;
	}
	public void setState(StateExtension state) {
		this.state = state;
	}
	public boolean step(GameState gs) {
		return automaton.step(this, gs);
	}
}
