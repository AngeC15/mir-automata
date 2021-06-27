package Model.entities.weapon;

import Model.World;
import Model.entities.Bullet.Bullet;
import Model.entities.Bullet.MultipleBullet;
import Model.entities.Entity;
import Utils.Vector2;

public class Shotgun extends Weapon {

	String bulletSkin;

	public Shotgun(String bulletSkin) {
		super(false);
		super.damage = 200;
		this.bulletSkin = bulletSkin;
		this.shot_frequency = 1000;
	}

	@Override
	public Entity attack(Entity e, Vector2 vect) {
		// on crée la bullet
		MultipleBullet bul = new MultipleBullet(e, vect, bulletSkin,"BulletZigZag", 7);
		World w = e.getWorld();
		w.addEntity(bul);
		return bul;
	}
}
