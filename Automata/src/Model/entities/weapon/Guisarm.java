package Model.entities.weapon;

import Model.World;
import Model.entities.Entity;
import Model.entities.Bullet.SwordStrick;
import Model.physics.PhysicsBody;
import Utils.Vector2;

public class Guisarm extends Weapon {
	protected PhysicsBody body;
	int equipe;
	int etat;

	public Guisarm() {
		super(true);
		this.shot_frequency = 2000;
		this.damage = 20;
		etat = 1;
	}
	
	@Override
	public Entity attack(Entity e, Vector2 vect) {
		this.equipe = e.getEquipe();
		// création d'une hitbox qui fait un mouvement circulaire
		int team = e.getEquipe();
		SwordStrick swordStrick = new SwordStrick(e, vect, this.damage);
		changeDamage();
		World w = e.getWorld();
		w.addEntity(swordStrick);
		Vector2 direction = new Vector2((float) swordStrick.getTransform().getShearX(),
				(float) swordStrick.getTransform().getScaleY());
		return swordStrick;
	}
	
	void changeDamage() {
		if(etat ==1) {
			this.damage = 400;
			etat++;
		}else if (etat == 2) {
			this.damage = 20;
			etat =1;
		}
	}
	

}
