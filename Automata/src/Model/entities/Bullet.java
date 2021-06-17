package Model.entities;

import java.awt.geom.AffineTransform;
import java.io.IOException;

import Model.World;
import Model.automata.creation.DirectionExtension;
import Model.loader.AutomataLoader;
import Utils.Functions;
import Utils.Vector2;
import View.Avatar;
import View.Template;

public class Bullet extends Entity{
	
	public int damage;
	public int vitesse;
	Vector2 vectorName;
	
	public Bullet(Entity e2, int damage, int vitesse, Vector2 vecteurBalle) {
		super(AutomataLoader.get("Bullet"), e2.getWorld(), e2.getWorld().getNextId());
		this.damage = damage;
		this.vitesse = vitesse;
		this.vectorName = vecteurBalle;
		
		//graphic parts
		Template BulletTemplate;
		try {
			BulletTemplate = new Template("Resources/winchester-4x6.png", "Resources/example.ani");
			Avatar av = new Avatar(this, BulletTemplate);
			this.setAvatar(av);
			//this.Move(DirectionExtension.F);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void Move(DirectionExtension dir) {
		// TODO Auto-generated method stub
		this.deplacement(this.vectorName, dir);
	}

	private void deplacement(Vector2 vect, DirectionExtension dir) {
		if (dir.ordinal() < 4) {
			//Vector2 direction = new Vector2((float)transform.getShearX(), (float)transform.getScaleY());
			Vector2 direction = new Vector2(vect.x, vect.y);
			vect = Functions.getRelativeDir(dir, direction);
		}
		else {
			this.directionEntite = dir;
			vect = Functions.getAbsoluteDir(dir);
		}
		vect.scale(world.getElapsed()*velocity/1000.0f);
		transform.concatenate(AffineTransform.getTranslateInstance(vect.x, vect.y));		
	}
	

	

}
