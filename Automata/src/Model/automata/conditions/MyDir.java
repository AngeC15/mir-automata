package Model.automata.conditions;

import Model.Entity;
import Model.GameState;

public class MyDir extends Condition{
	
	private Object direction;

	
	
	public MyDir(Object direction) {
		this.direction = direction;
		System.out.println("Direction vaut" + this.direction);

	}



	@Override
	public boolean eval(Entity e, GameState s) {
		System.out.println("Pas encore implémenté, merci de repasser");
		return false;
	}
}
