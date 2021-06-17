package Model.physics;

import Utils.Vector2;

//Hitbox + other properties
public class PhysicsObject {
	private HitBox hitbox;
	private float friction;
	private float max_speed;
	Vector2 velocity;
	
	public PhysicsObject(HitBox hb, float friction, float max_speed) {
		this.hitbox = hb;
		this.friction = friction;
		this.max_speed = max_speed;
	}
	
	void accelerate(long elapsed, Vector2 a) {
		a = a.scale(elapsed/1000.0f);
		velocity.add(a);
		if(velocity.norm() > max_speed) {
			velocity = velocity.normalize().scale(max_speed);
		}
	}
	
	void tick(long elapsed) {
		velocity.scale(1 - (elapsed/1000.0f*friction));
	}
	
	Vector2 getVelocity() {
		return velocity;
	}
	
	HitBox getHitBox() {
		return hitbox;
	}
}
