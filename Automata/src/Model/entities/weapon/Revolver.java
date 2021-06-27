package Model.entities.weapon;

import Model.World;
import Model.entities.Bullet.Bullet;
import Model.entities.Entity;
import Utils.Vector2;

public class Revolver extends Weapon{
	String bulletSkin;
	

	public Revolver(String bulletSkin) {
		super(false);
		this.bulletSkin = bulletSkin;
		this.shot_frequency = 750;
	}

	@Override
	public Entity attack(Entity e, Vector2 vect) {
		// on crée la bullet
		Bullet bul = new Bullet(e, vect, bulletSkin, 75);
		World w = e.getWorld();
		w.addEntity(bul);
		return bul;
	}
}
