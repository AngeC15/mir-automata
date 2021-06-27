package Model.entities.Bullet;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import Model.automata.creation.DirectionExtension;
import Model.entities.Entity;
import Model.entities.LivingEntity;
import Model.loader.AutomataLoader;
import Model.loader.TemplatesLoader;
import Model.physics.ColliderType;
import Model.physics.HitBox;
import Model.physics.PhysicsBody;
import Model.physics.PrimitiveInstance;
import Model.physics.primitives.Circle;
import Utils.Vector2;
import View.Avatar;

/**
 * 
 * @author Cyprien, Camille, Samuel, Joan
 *
 */
public class Bullet extends LivingEntity {

	Entity e;
	String bulletSkin;
	Vector2 vect;

	/**
	 * 
	 * @param e          = entity who launch the bullet (not the weapon)
	 * @param vect       = vector of direction of the bullet
	 * @param bulletSkin = aspect of the Bullet
	 * @param damage
	 */
	public Bullet(Entity e, Vector2 vect, String bulletSkin, int damage) {
		super(AutomataLoader.get("Bullet"), e.getEquipe());

		// on créer tout le nécessaire pour gerer les physics body
		this.e = e;
		this.vect = vect;
		this.bulletSkin = bulletSkin;
		this.damage = damage;
		this.life = 1000;
		this.acceleration = 2000.0f;
		this.team = e.team;

		initBullet();

	}

	/**
	 * 
	 * @param e             = entity who launch the bullet (not the weapon)
	 * @param vect          = vector of direction of the bullet
	 * @param bulletSkin    = aspect of the Bullet
	 * @param automatonName = name of the automaton
	 */
	public Bullet(Entity e, Vector2 vect, String bulletSkin, String automatonName, int damage) {
		super(AutomataLoader.get(automatonName), e.getEquipe());

		// on créer tout le nécessaire pour gerer les physics body
		this.e = e;
		this.vect = vect;
		this.bulletSkin = bulletSkin;
		this.damage = damage;
		this.life = 1000;
		this.acceleration = 2000.0f;
		this.team = e.team;
		initBullet();

	}

	private void initBullet() {
		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(3f, 3f)));
		this.body = new PhysicsBody(h, ColliderType.Projectile, 0.0f, 60.0f, this);

		vect = vect.normalize();

		float angle = (float) Math.atan2(-vect.x, vect.y);

		this.getBody().setTransform(new AffineTransform(e.getTransform()));

		this.getTransform().rotate(angle);
		this.getTransform().translate(0, 5);
		try {
			Avatar avatarBullet = new Avatar(this, TemplatesLoader.get(bulletSkin));
			this.setAvatar(avatarBullet);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public Color getColor() {
		return null;
	}

	/**
	 * Bullet movement
	 */
	@Override
	public void Pop(DirectionExtension dir) {
		super.Move(dir);

	}

	@Override
	public void Wizz(DirectionExtension dir) {


	}

}
