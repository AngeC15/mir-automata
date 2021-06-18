package Model.entities;

import java.awt.geom.AffineTransform;
import java.io.IOException;

import Model.World;
import Model.automata.Automaton;
import Model.loader.AutomataLoader;
import Model.physics.HitBox;
import Model.physics.PhysicsBody;
import Model.physics.PrimitiveInstance;
import Model.physics.primitives.Circle;
import Utils.Vector2;
import View.Avatar;
import View.Template;

public class Bullet extends Entity {
	
	
	
	public Bullet(Entity e, Vector2 vect) {
		super(AutomataLoader.get("Bullet"), e.getWorld());
		//on créer tout le nécessaire pour gerer les physics body
		this.acceleration = 80.0f;
		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(3, 3)));
		this.body = new PhysicsBody(h, 0.0f, 40.0f);
		
		
		//on récupère les coordonnées de celui qui tire:
		double xOwner = e.getTransform().getTranslateX();
		double yOwner = e.getTransform().getTranslateY();
		this.directionEntite = vect;
		vect = vect.normalize();
		
		this.getTransform().concatenate(AffineTransform.getTranslateInstance(xOwner+ vect.x, yOwner +4+vect.y));
		this.getTransform().rotate(Math.atan(vect.y/vect.x));
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
