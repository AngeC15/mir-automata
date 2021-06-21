package Model.entities;

import Controller.VirtualInput;
import java.awt.geom.AffineTransform;
import Model.World;

import Model.automata.creation.DirectionExtension;
import Model.entities.weapon.Dagger;
import Model.entities.weapon.Gun;
import Model.entities.weapon.Weapon;

import Model.loader.AutomataLoader;
import Model.physics.ColliderType;
import Model.physics.HitBox;
import Model.physics.PhysicsBody;
import Model.physics.PrimitiveInstance;
import Model.physics.primitives.Circle;

/**
 * A Java class for having a enemy with life
 * @author cyprien
 *
 */
public class EnemyPlayer extends LivingEntity{
	public Weapon armeCac;
	public Weapon armeDist;
	public Weapon currentWeapon;
	private double waitingSwitch;
	
	public EnemyPlayer(World w) {
		super(AutomataLoader.get("Wall"), 2);
		this.acceleration = 80.0f;
		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(3.1f, 5.2f)));
		//this.body = new PhysicsBody(h, 15.0f, 40.0f);
		this.body = new PhysicsBody(h, ColliderType.Character, 15.0f, 40.0f, this);
		
		armeCac = new Dagger(); //to change please
		armeDist = new Gun();
		currentWeapon = armeDist;
		waitingSwitch = System.currentTimeMillis();
		this.life = 100;
		this.damage = 10;
	}

	public void switchWeapon() {
		//you need to wait 1s between 2 switch of weapon
		double now = System.currentTimeMillis();
		if( now - waitingSwitch > 1000) {
			waitingSwitch = now;
			if(currentWeapon == armeDist) {
				currentWeapon = armeCac;
			}else {
				currentWeapon = armeDist;
			}
			System.out.println("Weapon switched");
		}
	}
	
	public void setArmeCac(Weapon armeCac) {
		this.armeCac = armeCac;
	}

	public void setArmeDist(Weapon armeDist) {
		this.armeDist = armeDist;
	}

	
	

	@Override
	public void Hit(DirectionExtension dir) {
		// attaque corp à corps
		System.out.println("Hit with " + currentWeapon.getClass().toString());
		super.Hit(dir);
		VirtualInput christianClavier = this.world.getInputs();
		
		//armeCac.attack(this, christianClavier.getMouseX(), christianClavier.getMouseY());
		currentWeapon.attack(this, christianClavier.getMousePlayer());
		
	}


	@Override
	public void Pop(DirectionExtension dir) {
		//changement d'arme
		this.switchWeapon();
		
	}
	
	
	

}
