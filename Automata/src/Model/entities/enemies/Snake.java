package Model.entities.enemies;

import java.awt.geom.AffineTransform;

import Model.entities.weapon.Dagger;
import Model.physics.ColliderType;
import Model.physics.HitBox;
import Model.physics.PhysicsBody;
import Model.physics.PrimitiveInstance;
import Model.physics.primitives.Circle;

public class Snake extends Enemy {

	public Snake(String automaton) {
		super(automaton);
		weapon = new Dagger();
		cooldown = 900;
		shootDistance = 3;
		
		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(10, 10)));
		this.body = new PhysicsBody(h, ColliderType.Character, 4, 30, this);
	}

}
