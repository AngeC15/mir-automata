package Model.entities.weapon;

import Model.World;
import Model.entities.Entity;
import Model.entities.Bullet.Bullet;
import Utils.Vector2;

public class Gun extends Weapon {

	String bulletSkin;

	public Gun(String bulletSkin) {
		super(false);
		this.bulletSkin = bulletSkin;
		this.shot_frequency = 500;
	}

	@Override
	public Entity attack(Entity e, Vector2 vect) {

		Bullet bul = new Bullet(e, vect, bulletSkin, "BulletZigZag", 20);

		World w = e.getWorld();
		w.addEntity(bul);
		Vector2 direction = new Vector2((float) bul.getTransform().getShearX(), (float) bul.getTransform().getScaleY());
		return bul;

	}

	@Override
	public String toString() {
		return "Gun";
	}

}
