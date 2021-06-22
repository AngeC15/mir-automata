package Model.entities;

import java.awt.geom.AffineTransform;
import java.io.IOException;

import Model.World;
import Model.automata.Automaton;
import Model.loader.AutomataLoader;
import Model.physics.ColliderType;
import Model.physics.HitBox;
import Model.physics.PhysicsBody;
import Model.physics.PrimitiveInstance;
import Model.physics.primitives.Circle;
import Utils.Vector2;
import View.Avatar;
import View.Template;

/**
 * 
 * @author Cyprien, Camille, Samuel, Joan
 *
 */
public class Bullet extends LivingEntity {
	
	/**
	 * 
	 * @param e = entity who launch the bullet (not the weapon)
	 * @param vect  = vector of direction of the bullet
	 */
	public Bullet(Entity e, Vector2 vect) {
		super(AutomataLoader.get("Bullet"), e.getEquipe()); //on mets la balle dans la même équipe que le joueur
		//on créer tout le nécessaire pour gerer les physics body
		this.damage = 20;
		this.life = 1000;
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
			Template BulletTemplate = new View.Template("Resources/winchester-4x6.png", "Resources/example.ani");
			Avatar avatarBullet = new Avatar(this, BulletTemplate);
			this.setAvatar(avatarBullet);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
	}




}
