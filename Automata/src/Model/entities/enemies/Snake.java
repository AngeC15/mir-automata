package Model.entities.enemies;

import java.awt.geom.AffineTransform;

import Model.automata.creation.DirectionExtension;
import Model.entities.DeadEntity;
import Model.entities.weapon.Dagger;
import Model.loader.AutomataLoader;
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
		life = 30;
		damage = 20;
		
		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(10, 10)));
		this.body = new PhysicsBody(h, ColliderType.Character, 4, 30, this);
	}
	
	@Override
	public void Egg(DirectionExtension dir) {
		new DeadEntity(this, AutomataLoader.get("Dead"), team, 350, "DeadDust");
		this.getWorld().removeEntity(getID());
	}

}
