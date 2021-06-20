package Model.entities;

import Controller.VirtualInput;
import java.awt.geom.AffineTransform;
import Model.World;

import Model.automata.creation.DirectionExtension;
import Model.entities.weapon.Dagger;
import Model.entities.weapon.Gun;
import Model.entities.weapon.Weapon;

import Model.loader.AutomataLoader;
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
		this.body = new PhysicsBody(h, 15.0f, 40.0f);
//		body.getTransform().rotate(Math.PI/2);
		
		armeCac = new Dagger(); //to change please
		armeDist = new Gun();
//		AffineTransform t1 = new AffineTransform(body.getTransform());
//		t1.concatenate(body.getHitBox().get(0).get_transform());
//		for (int k = 0 ; k < 8 ; k ++) {
//			Vector2 vect = new Vector2((float)Math.cos(k*Math.PI/4.0f), (float)Math.sin(k*Math.PI/4.0f));
////			System.out.println("vect "+ vect.x + " "+vect.y);
//			vect = (vect).transform(t1);
//			System.out.println("vect "+ vect.x + " "+vect.y);
//			
//		}
		
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
		System.out.println("Hit");
		super.Hit(dir);
		VirtualInput christianClavier = this.world.getInputs();
		
		//armeCac.attack(this, christianClavier.getMouseX(), christianClavier.getMouseY());
		armeCac.attack(this, christianClavier.getMousePlayer());
		
	}


	@Override
	public void Pop(DirectionExtension dir) {
		// tir arme distance
		super.Pop(dir);
		VirtualInput christianClavier = this.world.getInputs();
		//armeDist.attack(this, christianClavier.getMouseX(), christianClavier.getMouseY());
		armeDist.attack(this, christianClavier.getMousePlayer());
	}
	
	
	

}
