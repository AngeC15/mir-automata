package Model.entities.weapon;

import Model.World;
import Model.entities.Bullet;
import Model.entities.Entity;
import Utils.Vector2;

public class Sniper extends Weapon {
	String bulletSkin;
	

	public Sniper(String bulletSkin) {
		super(false);
		this.bulletSkin = bulletSkin;
		this.shot_frequency = 2000;
	}

	@Override
	public Entity attack(Entity e, Vector2 vect) {
		// on crée la bullet
		Bullet bul = new Bullet(e, vect, bulletSkin, 3000);
		World w = e.getWorld();
		w.addEntity(bul);
		Vector2 direction = new Vector2((float) bul.getTransform().getShearX(), (float) bul.getTransform().getScaleY());
		return bul;
	}

}
