package Model.entities;

import java.awt.geom.AffineTransform;
import java.io.IOException;

import Model.automata.Automaton;
import Model.loader.AutomataLoader;
import Model.loader.TemplatesLoader;
import Model.physics.ColliderType;
import Model.physics.HitBox;
import Model.physics.PhysicsBody;
import Model.physics.PrimitiveInstance;
import Model.physics.primitives.Circle;
import View.Avatar;
import Model.World;

public class PathfindingSingleton extends Entity {

	private Entity lastHittenEntity;
	private static PathfindingSingleton instance;
	
	private PathfindingSingleton(Automaton a, int equipe, World world) {
		super(a, equipe);
		
		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(10, 10)));
		this.body = new PhysicsBody(h, ColliderType.Projectile, 10, 20, this);
		try {
			avatar = new Avatar(this, TemplatesLoader.get("Strick"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		world.addEntity(this);
	}
	
	public static PathfindingSingleton getInstance(World world) {
		if(instance == null)
			instance = new PathfindingSingleton(AutomataLoader.get("Dummy"), 3, world);
		return instance;
	}
	
	public void changePosition(int x, int y) {
		getTransform().concatenate(AffineTransform.getTranslateInstance(x, y));
	}
	@Override
	public boolean step() {
		return super.step();
	}
	
	@Override
	public void colisionHappened(Entity other, ColliderType c) {
		lastHittenEntity = other;
		super.colisionHappened(other, c);
	}
	
	
	public Entity getLastHit() {
		return lastHittenEntity;
	}
	
}
