package Model.entities;

import Model.automata.Automaton;

public class DamagingEntity extends Entity{

	float damage;
	
	public DamagingEntity(Automaton a, int equipe) {
		super(a, equipe);
		// TODO Auto-generated constructor stub
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}
	
	
	

}
