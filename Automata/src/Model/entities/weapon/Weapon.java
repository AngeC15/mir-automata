package Model.entities.weapon;

import Model.entities.Entity;
import Utils.Vector2;

public abstract class Weapon {

	boolean cac;
	float damage;
	
	
	public Weapon(boolean cac) {
		super();
		this.cac = cac;
	}


	public abstract void attack(Entity e, Vector2 vect);
	

}
