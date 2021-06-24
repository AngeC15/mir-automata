package Model.entities;

import Controller.VirtualInput;

import java.awt.Color;
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
import Utils.Vector2;

public class Player extends LivingEntity {
	public Weapon armeCac;
	public Weapon armeDist;
	private double lastAttack;
	private double lastAttackFrequency;
	private Entity daggerStrick;

	public Player(World w) {
		super(AutomataLoader.get("Player"), 1);
		this.acceleration = 80.0f;
		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(3.1f, 5.2f)));
		this.body = new PhysicsBody(h, ColliderType.Character, 15.0f, 40.0f, this);

		armeCac = new Dagger(); // to change please
		armeDist = new Gun("Bullet");

		lastAttack = System.currentTimeMillis();

		this.life = 750;
	}

	public void setArmeCac(Weapon armeCac) {
		this.armeCac = armeCac;
	}

	public void setArmeDist(Weapon armeDist) {
		this.armeDist = armeDist;
	}

	@Override
	public boolean step() {
		//Check to destroy dagger strick
		double now = System.currentTimeMillis();
		if(daggerStrick != null && now - lastAttack >125) {
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

	@Override
	public void Hit(DirectionExtension dir) {
		// Meelee attack
		lastAttack = System.currentTimeMillis();
		lastAttackFrequency = armeCac.getShot_frequency();

		this.daggerStrick = armeCac.attack(this, new Vector2(0, -1));
		
	}

	@Override
	public void Pop(DirectionExtension dir) {
		// Distance attack
		lastAttack = System.currentTimeMillis();
		lastAttackFrequency = armeDist.getShot_frequency();

		armeDist.attack(this, new Vector2(0, -1));
	}

	@Override
	public boolean GotPower() {
		double now = System.currentTimeMillis();
		if (now - lastAttack > lastAttackFrequency)
			return true;
		return false;
	}

	@Override
	public String toString() {
		return "Player";
	}

	public Color getColor() {
		return Color.green;
	}

	
	@Override
	public void Egg(DirectionExtension dir) {
		new DeadEntity(this, AutomataLoader.get("Dead"), team, 350, "DeadExplosion");
		this.getWorld().removeEntity(getID());
	}

}
