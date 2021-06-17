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
	
	public Bullet(World w, int damage, int vitesse, Vector2 vecteurBalle) {
		super(AutomataLoader.get("Bullet"), w);
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

	

}
