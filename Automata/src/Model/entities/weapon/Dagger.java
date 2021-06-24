package Model.entities.weapon;

import Model.World;
import Model.entities.Entity;
import Model.entities.SwordStrick;
import Model.physics.PhysicsBody;
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
	public Entity attack(Entity e, Vector2 vect) {
		this.equipe = e.getEquipe();
		// création d'une hitbox qui fait un mouvement circulaire
		int team = e.getEquipe();
		/*
		 * HitBox h = new HitBox(); PrimitiveInstance pI = new PrimitiveInstance(new
		 * Circle(), AffineTransform.getScaleInstance(3.1f, 5.2f)); VirtualInput
		 * keyboard = e.getWorld().getInputs(); Vector2 vectDir =
		 * keyboard.getMousePlayer().normalize();
		 * 
		 * 
		 * pI.transform.translate(e.getTransform().getTranslateX()+vectDir.x,
		 * e.getTransform().getTranslateY()+vectDir.y); pI.transform.scale(5, 5);
		 * System.out.println("hitbox faite: " + pI.transform.toString()); h.add(pI);
		 * System.out.println("rr " + pI.toString()); this.body = new PhysicsBody(h,
		 * ColliderType.Projectile, 15.0f, 40.0f, e); //translation
		 * 
		 * //suppression de la hitbox
		 */
		SwordStrick swordStrick = new SwordStrick(e, vect);
		World w = e.getWorld();
		w.addEntity(swordStrick);
		Vector2 direction = new Vector2((float) swordStrick.getTransform().getShearX(),
				(float) swordStrick.getTransform().getScaleY());
		return swordStrick;
	}

	@Override
	public String toString() {
		return "Dagger";
	}

}
