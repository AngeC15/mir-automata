package Model.entities.Bullet;

import Model.World;
import Model.entities.Entity;
import Utils.Vector2;

/**
 * Generates multiple bullets
 *
 */
public class MultipleBullet extends Bullet {

	private int nbBullet;
	private String automatonName;

	public MultipleBullet(Entity e, Vector2 vect, String bulletSkin, int nbBullet) {
		super(e, vect, bulletSkin, 20);
		this.nbBullet = nbBullet;
		automatonName = "Bullet";

		generateMultiple();
	}

	/**
	 * Construct multiple bullets
	 * 
	 * @param e
	 * @param vect
	 * @param bulletSkin
	 * @param AutomatonName = for exemple "Bullet" or "BulletZigZag" which define
	 *                      the comportment of the bullet
	 * @param nbBullet      = the number of bullets you want generates
	 */
	public MultipleBullet(Entity e, Vector2 vect, String bulletSkin, String automatonName, int nbBullet) {
		super(e, vect, bulletSkin, automatonName, 20);
		this.nbBullet = nbBullet;
		this.automatonName = automatonName;
		generateMultiple();
	}

	/**
	 * Generate nbBullets Bullets spaced with an angle of 5 degrees
	 */
	private void generateMultiple() {
		float angle = (float) Math.atan(vect.y / vect.x);
		float angleDev;
		float x;
		float y;
		Bullet bul = new Bullet(e, vect, bulletSkin, automatonName, 20);
		World w = e.getWorld();
		w.addEntity(bul);

		for (int i = 0; i < (nbBullet - 1) / 2; i++) {
			angleDev = (float) Math.toRadians(5 * (i + 1));
			x = (float) Math.cos(angle + angleDev);
			y = (float) Math.sin(angle + angleDev);
			Bullet bul2 = new Bullet(e, new Vector2(x, y), bulletSkin, automatonName, 20);
			x = (float) Math.cos(angle - angleDev);
			y = (float) Math.sin(angle - angleDev);
			Bullet bul3 = new Bullet(e, new Vector2(x, y), bulletSkin, automatonName, 20);

			w.addEntity(bul2);
			w.addEntity(bul3);

		}
	}

}
