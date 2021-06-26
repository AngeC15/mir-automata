package Model.entities;

import java.awt.Color;
import java.awt.geom.AffineTransform;

import Controller.VirtualInput;
import Model.automata.creation.DirectionExtension;
import Model.entities.weapon.AssaultRifle;
import Model.entities.weapon.Axe;
import Model.entities.weapon.Croissant;
import Model.entities.weapon.Dagger;
import Model.entities.weapon.Guisarm;
import Model.entities.weapon.Gun;
import Model.entities.weapon.Halberd;
import Model.entities.weapon.Hammer;
import Model.entities.weapon.Juliette;
import Model.entities.weapon.Longsword;
import Model.entities.weapon.MachineGun;
import Model.entities.weapon.Pistol;
import Model.entities.weapon.Revolver;
import Model.entities.weapon.Romeo;
import Model.entities.weapon.Scythe;
import Model.entities.weapon.Shotgun;
import Model.entities.weapon.Sniper;
import Model.entities.weapon.Spear;
import Model.entities.weapon.Sword;
import Model.entities.weapon.TommyGun;
import Model.entities.weapon.Weapon;
import Model.loader.AutomataLoader;
import Model.physics.ColliderType;
import Model.physics.HitBox;
import Model.physics.PhysicsBody;
import Model.physics.PrimitiveInstance;
import Model.physics.primitives.Circle;
import Model.physics.primitives.Square;
import Utils.Vector2;

public class Player extends LivingEntity {
	public Weapon armeCac;
	public Weapon armeDist;
	private double lastAttack;
	private double lastAttackFrequency;
	private Entity daggerStrick;

	public Player() {
		super(AutomataLoader.get("Player"), 1);
		this.acceleration = 100.0f;
		HitBox h = new HitBox();
		PrimitiveInstance prim = new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(6.25f, 8.8f));
		prim.get_transform().translate(0.0f, 0.05f);
		h.add(prim);

		this.body = new PhysicsBody(h, ColliderType.Character,15.0f, 47.0f, this);
//		armeCac = new Dagger(); //to change please
//		armeDist = new Gun();


		armeCac = new Dagger(); // to change please
		armeDist = new Gun("Bullet");

		lastAttack = System.currentTimeMillis();

		this.life = 100000;
	}

	public void setArmeCac(Weapon armeCac) {
		this.armeCac = armeCac;
	}

	public void setArmeDist(Weapon armeDist) {
		this.armeDist = armeDist;
	}

	public void tick(long elapsed) {
//		this.getBody().getTransform().rotate(0.002*elapsed);
	}

	@Override
	public boolean step() {
		// Check to destroy dagger strick
		double now = System.currentTimeMillis();
		if (daggerStrick != null && now - lastAttack > 125) {
			this.world.removeEntity(daggerStrick.getID());
			daggerStrick = null;
		}
		rotate();
		return super.step();
	}

	protected void rotate() {
		VirtualInput keyboard = this.world.getInputs();

		try {
			// mouse angle relative to the player
			double relativeAngle = Math.atan2(keyboard.getMousePlayer().y, keyboard.getMousePlayer().x);

			// substract the players current angle and rotate
			relativeAngle -= Math.atan2(getTransform().getShearY(), getTransform().getScaleY());
			getTransform().rotate(relativeAngle + Math.toRadians(90));

		} catch (NullPointerException e) {
			getTransform().rotate(Math.toRadians(0));
		}
	}

	/**
	 * Hand to hand attack
	 */
	@Override
	public void Wizz(DirectionExtension dir) {
		double now = System.currentTimeMillis();
		
		if(now - lastAttack> armeCac.getShot_frequency()) {
			lastAttack = now;
		
		this.daggerStrick = armeCac.attack(this, new Vector2(0, -1));
		}
	}

	/**
	 * Distance attack
	 */
	@Override
	public void Pop(DirectionExtension dir) {
		double now = System.currentTimeMillis();
		
		if(now - lastAttack> armeDist.getShot_frequency()) {
			lastAttack = now;
		armeDist.attack(this, new Vector2(0, -1));
		}
	}

	@Override
	public boolean GotPower() {
		double now = System.currentTimeMillis();
		if (now - lastAttack > lastAttackFrequency)
			return true;
		return false;
	}

	@Override
	public Color getColor() {
		return Color.blue;
	}

	@Override
	public void Egg(DirectionExtension dir) {
		new DeadEntity(this, AutomataLoader.get("Dead"), team, 350, "DeadExplosion");
		this.getWorld().removeEntity(getID());
	}
	
	@Override
	public boolean addLifeBar() {
		return true;
	}

}
