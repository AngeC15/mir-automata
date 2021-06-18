package Model.physics;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import Model.physics.primitives.Primitive;
import Utils.Vector2;

public class Newton {
	private ArrayList<PhysicsBody> bodies;
	
	public Newton() {
		bodies = new ArrayList<PhysicsBody>();
	}
	
	public void add(PhysicsBody body) {
		bodies.add(body);
	}
	public void tick(long elapsed) {
		for(PhysicsBody body : bodies) {
			body.tick(elapsed);
			AffineTransform save = new AffineTransform(body.getTransform());
			AffineTransform t = body.getTransform();
			Vector2 velocity = body.getVelocity();
			Vector2 delta = velocity.scale(elapsed/1000.0f);
			
			AffineTransform translate = AffineTransform.getTranslateInstance(delta.x, delta.y);
			t.concatenate(translate);
			if(collideAll(body)) {
				body.transform = save;
				System.out.println("collision");
			}
		}
	}
	public boolean collideAll(PhysicsBody b) {
		for(PhysicsBody body : bodies) {
			if(body == b)
				continue;
			if(collide(b, body))
				return true;
		}
		return false;
	}
	
	public boolean collide(PhysicsBody b1, PhysicsBody b2) {
		HitBox h1 = b1.getHitBox();
		HitBox h2 = b1.getHitBox();
		for(PrimitiveInstance p1: h1.shapes) {
			for(PrimitiveInstance p2: h2.shapes) {
				AffineTransform t1 = new AffineTransform(b1.transform);
				AffineTransform t2 = new AffineTransform(b2.transform);
				t1.concatenate(p1.transform);
				t2.concatenate(p2.transform);
				if(GJK.collide(p1.prim, p2.prim, t1, t2)) {
					return true;
				}
			}
		}
		return false;
	}
}
