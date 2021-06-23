package Model.entities;

import java.awt.geom.AffineTransform;
import java.io.IOException;

import Model.automata.Automaton;
import Model.automata.creation.DirectionExtension;
import Model.loader.TemplatesLoader;
import Model.physics.ColliderType;
import Model.physics.HitBox;
import Model.physics.PhysicsBody;
import Model.physics.PrimitiveInstance;
import Model.physics.primitives.Circle;
import View.Avatar;
import Model.World;

public class DeadEntity extends Entity {

	// Should be as long as animation death time
	private int deathTimer;
	private double creationDate;

	public DeadEntity(LivingEntity parent,Automaton a, int equipe, int deathTimer, String template) {
		super(a, equipe);
		world = parent.world;
		this.deathTimer = deathTimer;
		creationDate = System.currentTimeMillis();
		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(10, 10)));
		this.body = new PhysicsBody(h, ColliderType.Character, 4, 30, this);
		try {
			Avatar avatarCorpse = new Avatar(this, TemplatesLoader.get(template));
			this.setAvatar(avatarCorpse);
			getTransform().concatenate(AffineTransform.getTranslateInstance(parent.getTransform().getTranslateX(), parent.getTransform().getTranslateY()));
			world.addEntity(this);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

	@Override
	public void Wizz(DirectionExtension dir) {
		world.removeEntity(this.getID());
		return;
	}
	@Override
	public boolean GotStuff() {
		double now = System.currentTimeMillis();
		if (now - creationDate > deathTimer)
			return true;
		return false;
	}
}