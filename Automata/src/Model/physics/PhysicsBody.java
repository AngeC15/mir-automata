package Model.physics;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import Model.entities.Entity;
import Utils.SafeMapElement;
import Utils.Vector2;

//Hitbox + other properties
public class PhysicsBody {

	private HitBox hitbox;
	private float friction;
	private float max_speed;
	Vector2 velocity;
	AffineTransform transform;
	private boolean accelerating;
	private ColliderType type;
	private Entity entity;
	private PhysicsBodyProxy proxy;

	public PhysicsBody(HitBox hb, ColliderType t, float friction, float max_speed, Entity e) {
		this.hitbox = hb;
		this.type = t;
		this.friction = friction;
		this.max_speed = max_speed;
		transform = new AffineTransform();
		velocity = new Vector2(0, 0);
		accelerating = false;
		this.entity = e;
	}

	public void accelerate(long elapsed, Vector2 a) {
		accelerating = true;
		float dot = a.normalize().dot(velocity.normalize());
		float coeff = (dot + 1.0f) / 2.0f;

		velocity = velocity.lerp(a.normalize().scale(velocity.norm()), 1 - coeff);
		a = a.scale(elapsed / 1000.0f);
		velocity = velocity.add(a);
		if (velocity.norm() > max_speed) {
			velocity = velocity.normalize().scale(max_speed);
		}
	}

	public void tick(long elapsed) {
		if (!accelerating)
			velocity = velocity.scale(1 - (elapsed / 1000.0f * friction));
		accelerating = false;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public HitBox getHitBox() {
		return hitbox;
	}

	public AffineTransform getTransform() {
		return transform;
	}

	public void setTransform(AffineTransform t) {
		transform = t;
	}

	public void debug(Graphics2D g) {
		AffineTransform save = g.getTransform();
		hitbox.debug(g);
		g.setTransform(save);
	}

	public ColliderType getType() {
		return type;
	}


	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public void setProxy(PhysicsBodyProxy p) {
		proxy = p;
	}
	public PhysicsBodyProxy getProxy() {
		return proxy;
	}
	public void setFriction(int friction) {
		this.friction = friction;
	}

	public void setmaxSpeed(int speed) {
		this.max_speed = speed;
	}
}
