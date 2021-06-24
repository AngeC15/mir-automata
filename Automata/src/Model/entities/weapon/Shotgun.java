package Model.entities.weapon;

import Model.World;
import Model.entities.Bullet;
import Model.entities.Entity;
import Utils.Vector2;

public class Shotgun extends Weapon {

	String bulletSkin;
	

	public Shotgun(String bulletSkin) {
		super(false);
		this.bulletSkin = bulletSkin;
		this.shot_frequency = 1000;
	}

	@Override
	public Entity attack(Entity e, Vector2 vect) {
		// on crée la bullet
		Bullet bul = new Bullet(e, vect, bulletSkin, 300);
		World w = e.getWorld();
		w.addEntity(bul);
		Vector2 direction = new Vector2((float) bul.getTransform().getShearX(), (float) bul.getTransform().getScaleY());
		return bul;
	}
}
