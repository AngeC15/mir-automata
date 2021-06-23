package Model.entities.weapon;

import java.awt.geom.AffineTransform;

import Model.entities.Entity;
import Model.entities.Player;
import Model.physics.ColliderType;
import Model.physics.HitBox;
import Model.physics.PhysicsBody;
import Model.physics.PrimitiveInstance;
import Model.physics.primitives.Square;
import Utils.Vector2;

public class Dagger extends Weapon {
	
	protected PhysicsBody body;
	int equipe;
	
	public Dagger() {
		super(true);
		this.shot_frequency = 500;
		this.damage = 30;
	}

	@Override
	public void attack(Entity e, Vector2 vect) {
		this.equipe = e.getEquipe();
		// création d'une hitbox qui fait un mouvement circulaire
		int team = e.getEquipe();
		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Square(), AffineTransform.getScaleInstance(1.0f, 10.0f)));
		this.body = new PhysicsBody(h, ColliderType.Projectile, 15.0f, 40.0f, e);
		//translation
		
		//suppression de la hitbox
		h = null;
		body = null;
		
	}

	@Override
	public String toString() {
		return "Dagger";
	}

}
