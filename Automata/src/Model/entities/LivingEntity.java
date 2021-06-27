package Model.entities;

import java.awt.Color;

import Model.automata.Automaton;
import Model.automata.actions.EnumAction;
import Model.entities.Bullet.Bullet;

/**
 * add life for entities,
 * 
 * @author cyprien
 *
 */
public class LivingEntity extends Entity {
	protected float life;
	protected float damage;

	public LivingEntity(Automaton a, int equipe) {
		super(a, equipe);

	}

	public float getLife() {
		return life;
	}

	public void setLife(float life) {
		this.life = life;
	}

	/**
	 * applied the damage to entity life
	 * 
	 * @param damage
	 */
	public void damage(float damage) {
		// negative damage will heal
		// because 2 colisions are detected
		addAction(EnumAction.HURT);

		this.life -= damage / 2;
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	public void checkDeath() {
		// if entity is dead, we delete it
		// System.out.println("Santé de l'entité " + this + " à " + this.life);
		if (this.life <= 0 || this instanceof Bullet) {
			// deletion
			// System.out.println("Entité supprimé");
			this.death();
		}

	}

	public void death() {
		this.getWorld().removeEntity(getID());
	}

	@Override
	public boolean GotStuff() {
		return life > 0;
	}

	@Override
	public Color getColor() {
		return null;
	}

	public boolean hasLifeBar() {
		return false;
	}

}
