package Model.entities;

import Model.World;
import Model.automata.Automaton;
import Model.loader.AutomataLoader;
import Utils.Vector2;

public class Bullet extends Entity {
	
	Vector2 vectBalle;
	
	
	public Bullet(Entity e, Vector2 vect) {
		super(AutomataLoader.get("Bullet"), e.getWorld());
		// TODO Auto-generated constructor stub
	}




}
