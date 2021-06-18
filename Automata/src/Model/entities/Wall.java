package Model.entities;

import java.awt.geom.AffineTransform;

import Model.World;
import Model.automata.Automaton;
import Model.loader.AutomataLoader;
import Model.physics.HitBox;
import Model.physics.PhysicsBody;
import Model.physics.PrimitiveInstance;
import Model.physics.primitives.Circle;

public class Wall extends Entity{

	public Wall(World w) {
		super(AutomataLoader.get("Wall"), w, w.getNextId());
		this.acceleration = 80.0f;
		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(3, 3)));
		this.body = new PhysicsBody(h, 0.0f, 0.0f);
	}

}
