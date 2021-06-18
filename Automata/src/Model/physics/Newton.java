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
			AffineTransform t = body.getTransform();
			Vector2 velocity = body.getVelocity();
			Vector2 delta = velocity.scale(elapsed/1000.0f);
			t.concatenate(AffineTransform.getTranslateInstance(delta.x, delta.y));
		}
	}
}
