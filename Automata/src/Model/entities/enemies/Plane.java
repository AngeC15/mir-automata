package Model.entities.enemies;

import java.awt.geom.AffineTransform;

import Model.automata.creation.DirectionExtension;
import Model.entities.weapon.Gun;
import Model.physics.ColliderType;
import Model.physics.HitBox;
import Model.physics.PhysicsBody;
import Model.physics.PrimitiveInstance;
import Model.physics.primitives.Circle;

public class Plane extends Enemy {

	public Plane(String automaton) {
		super(automaton);
		weapon = new Gun("Missile");
		cooldown = 1900;
		shootDistance = 40;
		acceleration = 18;

		life = 400;

		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(10, 10)));
		this.body = new PhysicsBody(h, ColliderType.Character, 0, 25, this);
	}

	@Override
	public String toString() {
		return "Plane";
	}
	
}
