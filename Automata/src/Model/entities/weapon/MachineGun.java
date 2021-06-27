package Model.entities.weapon;

import Model.World;
import Model.entities.Bullet.Bullet;
import Model.entities.Entity;
import Utils.Vector2;

public class MachineGun extends Weapon {

	String bulletSkin;


	public MachineGun(String bulletSkin) {
		super(false);
		super.damage = 10;
		this.bulletSkin = bulletSkin;
		this.shot_frequency = 75;
	}
	
	@Override
	public Entity attack(Entity e, Vector2 vect) {
		// on crée la bullet
		Bullet bul = new Bullet(e, vect, bulletSkin, damage);
		World w = e.getWorld();
		w.addEntity(bul);
		Vector2 direction = new Vector2((float) bul.getTransform().getShearX(), (float) bul.getTransform().getScaleY());
		return bul;
	}

}
