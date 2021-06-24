package Model.entities.weapon;

import Model.entities.Entity;
import Utils.Vector2;

public abstract class Weapon {

	boolean cac;
	float damage;
	double shot_frequency;

	
	
	public Weapon(boolean cac) {
		super();
		this.cac = cac;
	}


	public abstract Entity attack(Entity e, Vector2 vect);


	public boolean isCac() {
		return cac;
	}


	public void setCac(boolean cac) {
		this.cac = cac;
	}


	public float getDamage() {
		return damage;
	}


	public void setDamage(float damage) {
		this.damage = damage;
	}


	public double getShot_frequency() {
		return shot_frequency;
	}


	public void setShot_frequency(double shot_frequency) {
		this.shot_frequency = shot_frequency;
	}
	
	
	@Override
	public String toString() {
		return "Weapon";
	}

}
