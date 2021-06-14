package Model.automata.conditions;

import Model.Entity;
import Model.GameState;

public class Key extends Condition{

	private Object touche;

	public Key(Object touche) {
		this.touche = touche;
		System.out.println("Touche vaut" + this.touche);

	}
	
	@Override
	public boolean eval(Entity e) {
		System.out.println("Pas encore implementé, merci de repasser");
		return false;
	}

	
}
