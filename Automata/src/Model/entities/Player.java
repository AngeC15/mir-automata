package Model.entities;

import Controller.VirtualInput;
import java.awt.geom.AffineTransform;
import Model.World;

import Model.automata.creation.DirectionExtension;
import Model.entities.enemies.Tank;
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


public class Player extends Entity{
	public Weapon armeCac;
	public Weapon armeDist;

	
	public Player(World w) {
		super(AutomataLoader.get("Player"));
		this.acceleration = 80.0f;
		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(3.1f, 5.2f)));
		this.body = new PhysicsBody(h, ColliderType.Character,15.0f, 40.0f, this);
		
		armeCac = new Dagger(); //to change please
		armeDist = new Gun("Bullet");
	}

	
	public void setArmeCac(Weapon armeCac) {
		this.armeCac = armeCac;
	}

	public void setArmeDist(Weapon armeDist) {
		this.armeDist = armeDist;
	}

	@Override
	public boolean step() {
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
			System.out.println(e.getMessage());
		}
	}
	

	@Override
	public void Hit(DirectionExtension dir) {
		// attaque corp à corps
		System.out.println("Hit");
		super.Hit(dir);
		armeCac.attack(this, new Vector2(0, -1)); // attack in front
	}


	@Override
	public void Pop(DirectionExtension dir) {
		// tir arme distance
		super.Pop(dir);
		armeDist.attack(this, new Vector2(0, -1)); // attack in front
	}
	
	
	

}
