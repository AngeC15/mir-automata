package Model.entities.enemies;

import java.awt.geom.AffineTransform;

import Model.automata.creation.DirectionExtension;
import Model.entities.weapon.Dagger;
import Model.physics.ColliderType;
import Model.physics.HitBox;
import Model.physics.PhysicsBody;
import Model.physics.PrimitiveInstance;
import Model.physics.primitives.Circle;
import Utils.Vector2;

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

	@Override
	public void Pop(DirectionExtension dir) {
		// TODO Auto-generated method stub
		super.Pop(dir);
		 lastAttack = System.currentTimeMillis();
		Vector2 vector = new Vector2(0, 1);
		this.daggerStrike = weapon.attack(this, vector);
	}

	@Override
	public void Hit(DirectionExtension dir) {
		// TODO Auto-generated method stub
		super.Hit(dir);
		 lastAttack = System.currentTimeMillis();
		Vector2 vector = new Vector2(0, 1);
		this.daggerStrike = weapon.attack(this, vector);
	}
	
	

}
