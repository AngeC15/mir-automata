package Model.entities;

import java.awt.geom.AffineTransform;
import java.io.IOException;

import Controller.VirtualInput;
import Model.automata.Automaton;
import Model.automata.creation.CategoryExtension;
import Model.automata.creation.DirectionExtension;
import Model.loader.AutomataLoader;
import Model.loader.TemplatesLoader;
import Model.physics.ColliderType;
import Model.physics.HitBox;
import Model.physics.PhysicsBody;
import Model.physics.PrimitiveInstance;
import Model.physics.primitives.Circle;
import Utils.Vector2;
import View.Avatar;

public class SwordStrick extends LivingEntity{

	public SwordStrick(Entity e, Vector2 vector) {
		super(AutomataLoader.get("Strick"), e.getEquipe());
		this.damage = 20;
		this.life = 1000;
		this.acceleration = 2000.0f;
		this.team = e.team;
		
		HitBox h = new HitBox();
		PrimitiveInstance pI = new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(8.2f, 3.1f));
		// VirtualInput keyboard = e.getWorld().getInputs();
		
		
		// Vector2 vectDir = keyboard.getMousePlayer().normalize();
		
		//pI.transform.translate(e.getTransform().getTranslateX()+vectDir.x, e.getTransform().getTranslateY()+vectDir.y);
		//pI.transform.scale(1, -1);
		//System.out.println("hitbox faite: " + pI.transform.toString());
		h.add(pI);
		//System.out.println("rr " + pI.toString());
		this.body = new PhysicsBody(h, ColliderType.Projectile, 0.0f, 60.0f, this);
		
		
		vector = vector.normalize();
		
		float angle = (float) Math.atan2(-vector.x, vector.y);

		this.getBody().setTransform(new AffineTransform(e.getTransform()));
		
		this.getTransform().rotate(angle);
		this.getTransform().translate(0, 5);

		try {
			Avatar avatarBullet = new Avatar(this, TemplatesLoader.get("Strick"));
			this.setAvatar(avatarBullet);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}


	
	
	
	
	
	
}
