package Model.entities.enemies;

import java.awt.geom.AffineTransform;

import Model.entities.weapon.Dagger;
import Model.physics.ColliderType;
import Model.physics.HitBox;
import Model.physics.PhysicsBody;
import Model.physics.PrimitiveInstance;
import Model.physics.primitives.Circle;

public class Mecha extends Enemy {

	public Mecha(String automaton) {
		super(automaton);
		weapon = new Dagger();
		cooldown = 900;
		shootDistance = 3;

		life = 250;

		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(10, 10)));
		this.body = new PhysicsBody(h, ColliderType.Character, 4, 30, this);
	}

	@Override
	public String toString() {
		return "Mecha";
	}

}
