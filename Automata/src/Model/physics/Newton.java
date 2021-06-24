package Model.physics;

import java.awt.geom.AffineTransform;
import java.util.Map.Entry;

import Utils.SafeMap;
import Utils.SafeMapElement;
import Utils.Vector2;

public class Newton {
	private SafeMap[] bodies;
	private static final int l = ColliderType.values().length;
	// 0: no collision, 1: detect collision, 2: detect and block
	private static int collisionMatrix[][] = {
			/* Wall Projectile Character Area */
			/* Wall */ { 0, 0, 0, 0 }, /* Projectile */ { 1, 0, 1, 0 }, /* Character */ { 2, 0, 2, 1 },
			/* Area */ { 0, 0, 0, 0 } };

	public Newton() {
		bodies = new SafeMap[l];
		for (int i = 0; i < l; i++) {
			bodies[i] = new SafeMap();
		}
	}

	public void add(PhysicsBody body) {
		long id = bodies[body.getType().ordinal()].add(body);
		body.setID(id);
	}

	public void remove(PhysicsBody body) {
		bodies[body.getType().ordinal()].remove(body.getID());
	}

	public void update() {
		for (int i = 0; i < l; i++) {
			bodies[i].update();
		}
	}

	private AffineTransform simulateTranslation(long elapsed, PhysicsBody b) {
		Vector2 velo = b.getVelocity();
		AffineTransform t = b.getTransform();
		if (velo.x == 0.0f && velo.y == 0.0f)
			return t;

		AffineTransform save = new AffineTransform(t);

		Vector2 delta = velo.scale(elapsed / 1000.0f);
		AffineTransform trans = AffineTransform.getTranslateInstance(delta.x, delta.y);
		trans.concatenate(t);
		b.setTransform(trans);
		return save;
	}

	private void resetPosition(long elapsed, PhysicsBody b, AffineTransform saved, Vector2 normal) {
		// float dot = b.getVelocity().dot(normal);
		b.transform = saved;
		// b.velocity = b.getVelocity().sub(normal.scale(dot));
		// b.transform.translate(normal.x, normal.y);
		// Vector2 dir = new Vector2(-normal.y, -normal.x);
		// Vector2 v = b.getVelocity();
		// Vector2 delta = v.scale(elapsed/1000.0f);
		// Vector2 nv = dir.normalize().scale(delta.norm());
		/*
		 * System.out.println("----------"); System.out.println("normal is " + normal.x
		 * + " " + normal.y); System.out.println("dir is " + dir.x + " " + dir.y);
		 * System.out.println("v is " + v.x + " " + v.y); System.out.println("nv is " +
		 * nv.x + " " + nv.y);
		 */
		// v.x = nv.x;
		// v.y = nv.y;
		Vector2 n = normal.scale(0.05f);
		b.transform.translate(n.x, n.y);

	}

	private boolean circleTest(PhysicsBody b1, PhysicsBody b2) {
		float dx = (float) b1.getTransform().getTranslateX() - (float) b2.getTransform().getTranslateX();
		float dy = (float) b1.getTransform().getTranslateY() - (float) b2.getTransform().getTranslateY();
		Vector2 d = new Vector2(dx, dy);

		float min_d = b1.getHitBox().extRadius() + b2.getHitBox().extRadius();
		return d.norm() <= min_d;
	}

	private void handleCollision(long elapsed, PhysicsBody b1, long idx, int b_idx, int bt_idx,
			PhysicsBody[][] collisions, Vector2[][] normals) {
		AffineTransform save = simulateTranslation(elapsed, b1);
		for (int i = 0; i < l; i++) {
			int col = collisionMatrix[b_idx][i];
			if (col > 0) {
				int j = 0;
				for (Entry<Long, SafeMapElement> b2_e : bodies[i]) {
					if (b_idx != i || j > bt_idx) {
						PhysicsBody b2 = (PhysicsBody) b2_e.getValue();

						Vector2 normal = new Vector2(0, 0);
						if (circleTest(b1, b2)) {
							if (collide(b1, b2, normal)) {
								b1.getEntity().colisionHappened(b2.getEntity(), b2.getType());
								b2.getEntity().colisionHappened(b1.getEntity(), b1.getType());
								if (col == 2) {
									normal = normal.normalize();// .scale(elapsed/1000.0f*10f);
									collisions[b_idx][bt_idx] = b2;
									collisions[i][j] = b1;
									normals[b_idx][bt_idx] = normal.invert();
									normals[i][j] = normal;
								}
							}
						}
					}
					j++;
				}
			}
		}
		if (collisions[b_idx][bt_idx] != null)
			resetPosition(elapsed, b1, save, normals[b_idx][bt_idx]);
	}

	public void tick(long elapsed) {
		PhysicsBody[][] collisions = new PhysicsBody[l][];
		Vector2[][] normals = new Vector2[l][];
		for (int i = 0; i < l; i++) {
			collisions[i] = new PhysicsBody[bodies[i].size()];
			normals[i] = new Vector2[bodies[i].size()];
		}

		for (int i = 0; i < l; i++) {
			int j = 0;
			for (Entry<Long, SafeMapElement> body_e : bodies[i]) {
				PhysicsBody body = (PhysicsBody) body_e.getValue();
				body.tick(elapsed);
				collisions[i][j] = null;
				normals[i][j] = null;
				j++;
			}
		}
		for (int i = 0; i < l; i++) {
			int j = 0;
			for (Entry<Long, SafeMapElement> body_e : bodies[i]) {
				PhysicsBody body = (PhysicsBody) body_e.getValue();
				handleCollision(elapsed, body, body_e.getKey(), i, j, collisions, normals);
				j++;

			}
		}
	}

	public boolean collide(PhysicsBody b1, PhysicsBody b2, Vector2 normal) {
		HitBox h1 = b1.getHitBox();
		HitBox h2 = b2.getHitBox();
		for (PrimitiveInstance p1 : h1.shapes) {
			for (PrimitiveInstance p2 : h2.shapes) {
				AffineTransform t1 = new AffineTransform(b1.transform);
				AffineTransform t2 = new AffineTransform(b2.transform);
				t1.concatenate(p1.transform);
				t2.concatenate(p2.transform);
				if (GJK.collide(p1.prim, p2.prim, t1, t2)) {
					normal.x = GJK.get_normal().x;
					normal.y = GJK.get_normal().y;
					// System.out.println("Colision");
					return true;
				}
			}
		}
		return false;
	}
}
