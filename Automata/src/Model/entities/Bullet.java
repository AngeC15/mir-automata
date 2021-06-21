package Model.entities;

import java.awt.geom.AffineTransform;
import java.io.IOException;

import Model.loader.AutomataLoader;
import Model.loader.TemplatesLoader;
import Model.physics.ColliderType;
import Model.physics.HitBox;
import Model.physics.PhysicsBody;
import Model.physics.PrimitiveInstance;
import Model.physics.primitives.Circle;
import Utils.Vector2;
import View.Avatar;

public class Bullet extends Entity {
	
	
	
	public Bullet(Entity e, Vector2 vect, String bulletSkin) {
		super(AutomataLoader.get("Bullet"));
		//on créer tout le nécessaire pour gerer les physics body
		this.acceleration = 2000.0f;
		HitBox h = new HitBox();

		h.add(new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(3.1f, 5.2f)));
		this.body = new PhysicsBody(h, ColliderType.Projectile, 0.0f, 60.0f, this);
		
		vect = vect.normalize();
		
		float angle = (float) Math.atan2(-vect.x, vect.y);

		this.getBody().setTransform(new AffineTransform(e.getTransform()));
		
		this.getTransform().rotate(angle);
		this.getTransform().translate(0, 5);
		try {
			Avatar avatarBullet = new Avatar(this, TemplatesLoader.get(bulletSkin));
			this.setAvatar(avatarBullet);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
	}




}
