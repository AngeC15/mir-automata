package Model.entities;

import java.awt.geom.AffineTransform;

import Model.World;
import Model.loader.AutomataLoader;
import Model.physics.HitBox;
import Model.physics.PhysicsBody;
import Model.physics.PrimitiveInstance;
import Model.physics.primitives.Circle;

public class Player extends Entity{

	public Player(World w) {
		super(AutomataLoader.get("Player"), w, w.getNextId());
		this.acceleration = 80.0f;
		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(3.1f, 5.2f)));
		this.body = new PhysicsBody(h, 15.0f, 40.0f);
	}

}
