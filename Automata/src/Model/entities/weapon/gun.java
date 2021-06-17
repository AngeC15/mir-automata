package Model.entities.weapon;

import java.util.Vector;

import Model.World;
import Model.automata.creation.DirectionExtension;
import Model.entities.Bullet;
import Model.entities.Entity;
import Utils.Vector2;

public class gun extends Weapon{

	final int VITESSEBALLE = 1;
	long id = 0;
	
	public gun(int damage) {
		super(damage, false);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack(Entity e, int x, int y) {
		System.out.println("x= "+ x + " y = "+y);
		Vector2 vecteurBalle = new Vector2(x, y);
		Bullet bul = new Bullet(e, damage, VITESSEBALLE, vecteurBalle);
		DirectionExtension di = vectToDir(vecteurBalle);
		World w = e.getWorld();
		w.addEntity(e, id++);
		
		
		
		
	}

	private DirectionExtension vectToDir(Vector2 vecteurBalle) {
		
		double arg = Vector2.argument(vecteurBalle);
		System.out.println("argument = " + arg);
		if((arg > 0 && arg < 22.5) || (arg < 360 && arg >= 337.5)){
			return DirectionExtension.E;
		}else if(arg >= 22.5 && arg < 67.5) {
			return DirectionExtension.NE;
		}else if(arg >= 67.5 && arg < 112.5) {
			return DirectionExtension.N;
		}else if(arg >= 112.5 && arg < 157.5) {
			return DirectionExtension.NW;
		}else if(arg >= 157.5 && arg < 202.5) {
			return DirectionExtension.W;
		}else if(arg >= 202.5 && arg < 247.5) {
			return DirectionExtension.SW;
		}else if(arg >= 247.5 && arg < 292.5) {
			return DirectionExtension.S;
		}else if(arg >= 292.5 && arg < 337.5) {
			return DirectionExtension.SE;
		}
		System.out.println("Dans gun.java, utilisation d'une valeur pas défaut, anormal");
		return DirectionExtension.S;
	}

	
	
	
	

}
