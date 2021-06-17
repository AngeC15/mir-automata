package Model.entities.weapon;

import Model.entities.Entity;

public abstract class Weapon {
	public int damage;
	public boolean CaC;
	//add range when other weapons will be added
	
	
	public Weapon(int damage, boolean caC) {
		super();
		this.damage = damage;
		CaC = caC;
	}
	
	public abstract void attack(Entity e, int x, int y);


	
	
}
