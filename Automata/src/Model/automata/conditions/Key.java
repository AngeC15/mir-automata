package Model.automata.conditions;

import Model.GameState;
import Model.entities.Entity;

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
