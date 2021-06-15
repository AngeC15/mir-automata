package Model.entities;

import Model.World;
import Model.automata.Automaton;
import Model.automata.AutomatonState;
import Model.automata.creation.DirectionExtension;
import Utils.Vector2;
import Utils.Functions;

public class Entity {
	protected AutomatonState state;
	protected Automaton automaton;
	protected World world;
	protected int id;
	protected Vector2 pos;
	protected Vector2 currentdir;
	protected float velocity = 3;
	
	public Entity(int id, Automaton a, World w, Vector2 p) {
		this.id = id;
		automaton = a;
		world = w;
		pos = p;
	}
	
	public int getID() {
		return id;
	}
	
	public AutomatonState getState() {
		return state;
	}
	
	public void setState(AutomatonState state) {
		this.state = state;
	}
	
	public boolean step() {
		return automaton.step(this);
	}
	
	public void move(DirectionExtension dir) {
		float coeff = world.getElapsed()*velocity/1000.0f;
		if (dir.ordinal() < 4) {
			pos.add(Functions.getRelativeDir(dir, currentdir).scale(coeff));
		}
		else {
			pos.add(Functions.getAbsoluteDir(dir).scale(coeff));
		}
	}
	
}
