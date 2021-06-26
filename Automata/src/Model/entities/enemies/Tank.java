package Model.entities.enemies;

import java.awt.geom.AffineTransform;

import Model.automata.creation.DirectionExtension;
import Model.entities.weapon.Gun;
import Model.physics.ColliderType;
import Model.physics.HitBox;
import Model.physics.PhysicsBody;
import Model.physics.PrimitiveInstance;
import Model.physics.primitives.Circle;

public class Tank extends Enemy {

	public Tank(String automaton) {
		super(automaton);
		weapon = new Gun("EnemyBullet");
		cooldown = 1900;
		shootDistance = 40;
		acceleration = 18;

		life = 100;

		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(10, 10)));
		this.body = new PhysicsBody(h, ColliderType.Character, 10, 20, this);
	}

	@Override
	public String toString() {
		return "Tank";
	}
	
	@Override
	public void Wizz(DirectionExtension dir) {
		super.Move(dir);
	}
}
