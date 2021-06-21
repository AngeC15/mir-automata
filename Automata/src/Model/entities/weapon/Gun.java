package Model.entities.weapon;

import Model.World;
import Model.entities.Bullet;
import Model.entities.Entity;
import Model.entities.Player;
import Utils.Vector2;

public class Gun extends Weapon{

	String bulletSkin;
	
	public Gun(String bulletSkin) {
		super(false);
		this.bulletSkin = bulletSkin;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack(Entity e, Vector2 vect) {
		
		//on crée la bullet
		Bullet bul = new Bullet(e, vect, bulletSkin);
		World w = e.getWorld();
		w.addEntity(bul);
		Vector2 direction = new Vector2((float)bul.getTransform().getShearX(), (float)bul.getTransform().getScaleY());
		
	}


	
}
