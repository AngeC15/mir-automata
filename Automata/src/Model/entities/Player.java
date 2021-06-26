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
import Utils.Vector2;

public class Player extends LivingEntity {
	public Weapon armeCac;
	public Weapon armeDist;
	private double lastAttack;
	private double lastAttackFrequency;

	public Player() {
		super(AutomataLoader.get("Player"), 1);
		this.acceleration = 80.0f;
		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(3.1f, 5.2f)));
		this.body = new PhysicsBody(h, ColliderType.Character, 15.0f, 40.0f, this);

		armeCac = new Dagger(); // to change please
		armeDist = new Gun("Bullet");

		lastAttack = System.currentTimeMillis();

		this.life = 100;
	}

	public void setArmeCac(Weapon armeCac) {
		this.armeCac = armeCac;
	}

	public void setArmeDist(Weapon armeDist) {
		this.armeDist = armeDist;
	}

	@Override
	public boolean step() {
		// Check to destroy dagger strick
		double now = System.currentTimeMillis();
		if (this.daggerStrike != null && now - lastAttack > 125) {
			//System.out.println("Remove daggerStrick from player: " );
			this.world.removeEntity(daggerStrike.getID());
			daggerStrike = null;
		}
		if (this.ShotStrike != null && now - lastAttack > 8000) {
			//System.out.println("Remove ShotStrick from player: " );
			this.world.removeEntity(ShotStrike.getID());
			ShotStrike = null;
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

	@Override
	public void Hit(DirectionExtension dir) {
		// Meelee attack
		double now = System.currentTimeMillis();
		
		if(now - lastAttack> armeCac.getShot_frequency()) {
			lastAttack = now;
			this.daggerStrike = armeCac.attack(this, new Vector2(0, -1));
		}
	}

	@Override
	public void Pop(DirectionExtension dir) {
		// Distance attack
		double now = System.currentTimeMillis();
		
		if(now - lastAttack> armeDist.getShot_frequency()) {
			lastAttack = now;
			this.ShotStrike = armeDist.attack(this, new Vector2(0, -1));
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

}
