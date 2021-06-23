package Model.entities.enemies;

import java.awt.geom.AffineTransform;

import Model.automata.creation.CategoryExtension;
import Model.automata.creation.DirectionExtension;
import Model.entities.DeadEntity;
import Model.entities.weapon.Gun;
import Model.loader.AutomataLoader;
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
		shootDistance = 60;
		acceleration = 18;
		life = 100;
		damage = 50;
		
		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(10, 10)));
		this.body = new PhysicsBody(h, ColliderType.Character, 10, 20, this);
	}
	
	@Override
	public boolean Closest(DirectionExtension direction, CategoryExtension categorie) {
		rotate();
		return super.Closest(direction,categorie);
	}
	
	@Override
	public void Egg(DirectionExtension dir) {
		new DeadEntity(this, AutomataLoader.get("Dead"), team, 350, "DeadExplosion");
		this.getWorld().removeEntity(getID());
	}
}
