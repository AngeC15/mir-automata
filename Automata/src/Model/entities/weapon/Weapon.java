package Model.entities.weapon;

import Model.entities.Entity;
import Model.entities.Player;

public abstract class Weapon {

	boolean cac;
	
	
	public Weapon(boolean cac) {
		super();
		this.cac = cac;
	}


	public abstract void attack(Entity e, int mouseX, int mouseY);
	

}
