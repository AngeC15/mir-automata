package Model.entities.enemies;

import java.awt.geom.AffineTransform;

import Model.automata.creation.DirectionExtension;
import Model.entities.weapon.Gun;
import Model.physics.ColliderType;
import Model.physics.HitBox;
import Model.physics.PhysicsBody;
import Model.physics.PrimitiveInstance;
import Model.physics.primitives.Circle;
import Utils.Vector2;
import Model.physics.primitives.Square;


public class Tank extends Enemy {

	public Tank(String automaton) {
		super(automaton);
		weapon = new Gun("EnemyBullet");
		cooldown = 1900;
		shootDistance = 40;
		acceleration = 18;
		life = 100;

		HitBox h = new HitBox();
		PrimitiveInstance prim = new PrimitiveInstance(new Square(), AffineTransform.getScaleInstance(11, 16));
		prim.get_transform().translate(0, -0.15);
		h.add(prim);
		this.body = new PhysicsBody(h, ColliderType.Character, 10, 20, this);
	}

	@Override
	public String toString() {
		return "Tank";
	}

	@Override
	public void Pop(DirectionExtension dir) {
		// TODO Auto-generated method stub
		super.Pop(dir);
		lastAttack = System.currentTimeMillis();
		Vector2 vector = new Vector2(0, 1);
		this.ShotStrike = weapon.attack(this, vector);
	}

	
}
