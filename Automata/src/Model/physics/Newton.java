package Model.physics;

import java.awt.geom.AffineTransform;
import java.util.ArrayDeque;
import java.util.ArrayList;

import java.util.Map.Entry;

import Utils.SafeMap;
import Utils.SafeMapElement;
import Utils.Vector2;

public class Newton {
	private SafeGrid[] bodies;
	private static final int l = ColliderType.values().length;
	// 0: no collision, 1: detect collision, 2: detect and block
	private static int collisionMatrix[][] = {
			/* Wall Projectile Character Area */
			/* Wall */ { 0, 0, 0, 0 }, /* Projectile */ { 1, 0, 1, 0 }, /* Character */ { 2, 0, 2, 1 },
			/* Area */ { 0, 0, 0, 0 } };

	public Newton() {
		bodies = new SafeGrid[l];
		for(int i=0; i < l; i++) {
			bodies[i] = new SafeGrid();
		}
	}

	public void add(PhysicsBody body) {
		bodies[body.getType().ordinal()].add(new PhysicsBodyProxy(body));
	}

	public void remove(PhysicsBody body) {
		bodies[body.getType().ordinal()].remove(body.getProxy());
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
		b.transform = saved;

		Vector2 n = normal.scale(0.05f);
		AffineTransform tr = AffineTransform.getTranslateInstance(n.x, n.y);
		tr.concatenate(b.transform);
		b.setTransform(tr);
	}

	private boolean circleTest(PhysicsBody b1, PhysicsBody b2) {
		float dx = (float) b1.getTransform().getTranslateX() - (float) b2.getTransform().getTranslateX();
		float dy = (float) b1.getTransform().getTranslateY() - (float) b2.getTransform().getTranslateY();
		Vector2 d = new Vector2(dx, dy);

		float min_d = b1.getHitBox().extRadius() + b2.getHitBox().extRadius();
		return d.norm() <= min_d;
	}
	
	private void singleCollision(long elapsed, PhysicsBodyProxy b1, PhysicsBodyProxy b2, long idx, int b_idx, int bt_idx, int col, int i, int j) {
		//if(b_idx != i || j > bt_idx) {
			Vector2 normal = new Vector2(0, 0);
			PhysicsBody bo1 = b1.getBody();
			PhysicsBody bo2 = b2.getBody();
			if(circleTest(bo1, bo2)) {

				if(collide(bo1, bo2, normal)){
					bo1.getEntity().colisionHappened(bo2.getEntity(), bo2.getType());
					bo2.getEntity().colisionHappened(bo1.getEntity(), bo1.getType());
					if(col == 2) {
						normal = normal.normalize();//.scale(elapsed/1000.0f*10f);
						b1.setCollision(bo2);
						b1.setNormal(normal.invert());
						b2.setNormal(normal);
					}
				}
			}
		//}
	}
	private void handleCollisions(long elapsed, PhysicsBodyProxy b1, long idx, int b_idx, int bt_idx) {
		AffineTransform save = simulateTranslation(elapsed, b1.getBody());
		for(int i=0; i < l; i++) {
			int col = collisionMatrix[b_idx][i];
			if(col > 0) {
				int j=0;
				long pos = b1.getPos();
				int x = (int) ((pos >> 32) & 0xFFFFFFFFL);
				int y = (int) (pos & 0xFFFFFFFFL);
				
				for(int k=-1; k <= 1; k++) {
					for(int m=-1; m <= 1; m++) {
						long tx = x+m;
						long ty = y+k;
						long np = (tx << 32) | (ty & 0xffffffffL);
						SafeGridCell sgc = bodies[i].get(np);
						if(sgc == null) {
							continue;
						}
						for(Entry<Long, SafeMapElement> b2_e: sgc) {
							singleCollision(elapsed, b1, (PhysicsBodyProxy)b2_e.getValue(), idx, b_idx, bt_idx, col, i, j);
							j++;
						}
					}
				}
			}
		}
		if(b1.getCollision() != null)
			resetPosition(elapsed, b1.getBody(), save, b1.getNormal());
	}

	public void tick(long elapsed) {
		update();
		
		for(int i=0; i < l; i++) {
			int j=0;
			for(SafeGridCell c : bodies[i]) {
				for(Entry<Long, SafeMapElement> body_e: c) {
					PhysicsBodyProxy proxy = (PhysicsBodyProxy) body_e.getValue();
					proxy.setCollision(null);
					proxy.setNormal(null);
					proxy.getBody().tick(elapsed);
					j++;
				}
			}
			//bodies[i].update();
		}
		
		for(int i=0; i < l; i++) {
			int j=0;
			for(SafeGridCell c : bodies[i]) {
				for(Entry<Long, SafeMapElement> body_e: c) {
					PhysicsBodyProxy body = (PhysicsBodyProxy) body_e.getValue();
					handleCollisions(elapsed, body, body_e.getKey(), i, j);
					j++;
				}
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
				if(GJK.collide(p1.prim, p2.prim, t1, t2)) {
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
