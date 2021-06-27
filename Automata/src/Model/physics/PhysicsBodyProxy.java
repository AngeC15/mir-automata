package Model.physics;

import Utils.Vector2;

public class PhysicsBodyProxy implements SafeGridElement {
	private PhysicsBody body;
	private long id;
	private long pos;
	private PhysicsBody collision;
	private Vector2 normal;
	
	public PhysicsBodyProxy(PhysicsBody b) {
		body = b;
		body.setProxy(this);
	}
	
	public PhysicsBody getCollision() {
		return collision;
	}
	public void setCollision(PhysicsBody collision) {
		this.collision = collision;
	}
	public Vector2 getNormal() {
		return normal;
	}
	public void setNormal(Vector2 normal) {
		this.normal = normal;
	}

	public PhysicsBody getBody() {
		return body;
	}
	@Override
	public void setPos(long i) {
		pos = i;
	}

	@Override
	public long getPos() {
		return pos;
	}

	@Override
	public float getPosX_f() {
		return (float) body.getTransform().getTranslateX();
	}

	@Override
	public float getPosY_f() {
		return (float) body.getTransform().getTranslateY();
	}
	
	@Override
	public void setID(long id) {
		this.id = id;

	}

	@Override
	public long getID() {
		return id;
	}
}
