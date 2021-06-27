package Model.entities.weapon;

import Model.World;
import Model.entities.Bullet.Bullet;
import Model.entities.Bullet.MultipleBullet;
import Model.entities.Entity;
import Utils.Vector2;

public class Pistol extends Weapon {
	
	String bulletSkin;

	

	public Pistol(String bulletSkin) {
		super(false);
		super.damage = 5;
		this.bulletSkin = bulletSkin;
		this.shot_frequency = 300;
	}

	@Override
	public Entity attack(Entity e, Vector2 vect) {
		// on crée la bullet
		MultipleBullet bul = new MultipleBullet(e, vect, bulletSkin, damage);
		World w = e.getWorld();
		w.addEntity(bul);
		Vector2 direction = new Vector2((float) bul.getTransform().getShearX(), (float) bul.getTransform().getScaleY());
		return bul;
	}
	
	@Override
	public String toString() {
		return "Pistol: damage 10";
	}

}
