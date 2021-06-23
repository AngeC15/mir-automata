package Model.entities.enemies;

import java.awt.geom.AffineTransform;

import Model.entities.weapon.Dagger;
import Model.physics.ColliderType;
import Model.physics.HitBox;
import Model.physics.PhysicsBody;
import Model.physics.PrimitiveInstance;
import Model.physics.primitives.Circle;

public class Duck extends Enemy {
	
	public Duck(String automaton) {
		super(automaton);
		weapon = new Dagger();
		cooldown = 100;
		shootDistance = 3;
		acceleration = 90;
		
		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(10, 10)));
		this.body = new PhysicsBody(h, ColliderType.Character, 0, 35, this);
	}

}
