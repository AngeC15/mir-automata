package Model;

import Model.automata.Automaton;

public class Entity {
	protected int state;
	protected Automaton automaton;
	int id;
	
	public Entity(int id, Automaton a) {
		this.id = id;
		automaton = a;
	}
	public int getID() {
		return id;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public boolean step(GameState gs) {
		return automaton.step(this, gs);
	}
}
