package Model.physics;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import java.util.Map.Entry;

import Model.physics.primitives.Primitive;
import Utils.SafeMap;
import Utils.SafeMapElement;
import Utils.Vector2;

public class Newton {
	private SafeMap[] bodies;
	
	//0: no collision, 1: detect collision, 2: detect and block
	private static int collisionMatrix[][] = {
					/*Wall  Projectile  Character  Area*/
	/*Wall*/	    { 0,    0,          0,         0},
	/*Projectile*/  { 1,    0,          1,         0},
	/*Character*/   { 2,    0,          2,         1},
	/*Area*/	    { 0,    0,          0,         0}
	};
	
	public Newton() {
		int l = ColliderType.values().length;
		bodies = new SafeMap[l];
		for(int i=0; i < l; i++) {
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
		int l = ColliderType.values().length;
		for(int i=0; i < l; i++) {
			bodies[i].update();
		}
	}
	
	private AffineTransform simulateTranslation(long elapsed, PhysicsBody b) {
		AffineTransform t = b.getTransform();
		AffineTransform save = new AffineTransform(t);
		Vector2 velo = b.getVelocity();
		Vector2 delta = velo.scale(elapsed/1000.0f);
		AffineTransform trans = AffineTransform.getTranslateInstance(delta.x, delta.y);
		trans.concatenate(t);
		b.setTransform(trans);
		return save;
	}
	private void resetPosition(long elapsed, PhysicsBody b, AffineTransform saved, Vector2 normal) {
		float dot = b.getVelocity().dot(normal);
		b.transform = saved;
		//b.velocity = b.getVelocity().sub(normal.scale(dot));
		b.transform.translate(normal.x, normal.y);
	}
	
	private void handleCollision(long elapsed, PhysicsBody b1, long idx, int b_idx, int bt_idx, PhysicsBody[][] collisions, Vector2[][] normals) {
		int l = ColliderType.values().length;
		AffineTransform save = simulateTranslation(elapsed, b1);
		for(int i=0; i < l; i++) {
			int col = collisionMatrix[b_idx][i];
			if(col > 0) {
				int j=0;
				for(Entry<Long,SafeMapElement> b2_e : bodies[i]) {
					if(b_idx != i || j > bt_idx) {
						PhysicsBody b2 = (PhysicsBody) b2_e.getValue();
						Vector2 normal = new Vector2(0, 0);
						if(collide(b1, b2, normal)){
							b1.getEntity().colisionHappened(b2.getEntity(), b2.getType());
							b2.getEntity().colisionHappened(b1.getEntity(), b1.getType());
							if(col == 2) {
								normal = normal.normalize().scale(elapsed/1000.0f*10f);
								collisions[b_idx][bt_idx] = b2;
								collisions[i][j] = b1;
								normals[b_idx][bt_idx] = normal.invert();
								normals[i][j] = normal;
							}
						}
					}
					j++;
				}
			}
		}
		if(collisions[b_idx][bt_idx] != null)
			resetPosition(elapsed, b1, save, normals[b_idx][bt_idx]);
	}

	public void tick(long elapsed) {
		
		int l = ColliderType.values().length;
		PhysicsBody[][] collisions = new PhysicsBody[l][];
		Vector2[][] normals = new Vector2[l][];
		for(int i=0; i < l; i++) {
			collisions[i] = new PhysicsBody[bodies[i].size()];
			normals[i] = new Vector2[bodies[i].size()];
		}
		
		for(int i=0; i < l; i++) {
			int j=0;
			for(Entry<Long,SafeMapElement> body_e : bodies[i]) {
				PhysicsBody body = (PhysicsBody) body_e.getValue();
				body.tick(elapsed);
				collisions[i][j] = null;
				normals[i][j] = null;
				j++;
			}
		}
		for(int i=0; i < l; i++) {
			int j=0;
			for(Entry<Long,SafeMapElement> body_e : bodies[i]) {
				PhysicsBody body = (PhysicsBody) body_e.getValue();
				handleCollision(elapsed, body, body_e.getKey(), i, j, collisions, normals);
				j++;

			}
		}
	}
	
	public boolean collide(PhysicsBody b1, PhysicsBody b2, Vector2 normal) {
		HitBox h1 = b1.getHitBox();
		HitBox h2 = b2.getHitBox();
		for(PrimitiveInstance p1: h1.shapes) {
			for(PrimitiveInstance p2: h2.shapes) {
				AffineTransform t1 = new AffineTransform(b1.transform);
				AffineTransform t2 = new AffineTransform(b2.transform);
				t1.concatenate(p1.transform);
				t2.concatenate(p2.transform);
				if(GJK.collide(p1.prim, p2.prim, t1, t2)) {
					normal.x = GJK.get_normal().x;
					normal.y = GJK.get_normal().y;
					return true;
				}
			}
		}
		return false;
	}
}
