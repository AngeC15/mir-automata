package Model.entities;

import Controller.VirtualInput;
import Model.World;

import Model.automata.creation.DirectionExtension;
import Model.entities.weapon.Weapon;
import Model.entities.weapon.dagger;
import Model.entities.weapon.gun;
import Model.loader.AutomataLoader;


public class Player extends Entity{
	public Weapon armeCac;
	public Weapon armeDist;

	
	public Player(World w) {
		super(AutomataLoader.get("Player"), w);
		armeCac = new dagger(500000); //to change please
		armeDist = new gun(5000);

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
		armeCac.attack(this, christianClavier.getMouseX(), christianClavier.getMouseY());
		
	}


	@Override
	public void Pop(DirectionExtension dir) {
		// tir arme distance
		System.out.println("Pop" + dir);
		super.Pop(dir);
		VirtualInput christianClavier = this.world.getInputs();
		armeDist.attack(this, christianClavier.getMouseX(), christianClavier.getMouseY());
	}
	
	
	

}
