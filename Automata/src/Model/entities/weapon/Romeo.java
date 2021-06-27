package Model.entities.weapon;

import Model.World;
import Model.entities.Entity;
import Model.entities.Bullet.SwordStrick;
import Model.physics.PhysicsBody;
import Utils.Vector2;

public class Romeo extends Weapon{
	protected PhysicsBody body;
	int equipe;

	public Romeo() {
		super(true);
		this.shot_frequency = 100;
		this.damage = 500;
	}
	
	@Override
	public Entity attack(Entity e, Vector2 vect) {
		this.equipe = e.getEquipe();
		// création d'une hitbox qui fait un mouvement circulaire
		int team = e.getEquipe();
		SwordStrick swordStrick = new SwordStrick(e, vect, this.damage);
		World w = e.getWorld();
		w.addEntity(swordStrick);
		Vector2 direction = new Vector2((float) swordStrick.getTransform().getShearX(),
				(float) swordStrick.getTransform().getScaleY());
		return swordStrick;
	}
}
