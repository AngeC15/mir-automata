package Model.automata.conditions;

import Model.automata.creation.KeyExtension;
import Model.entities.Entity;

public class Key extends Condition{

	private KeyExtension touche;

	public Key(KeyExtension touche) {
		this.touche = touche;
		System.out.println("Touche vaut" + this.touche);

	}
	
	@Override
	public boolean eval(Entity e) {
		e.key(touche);
		return false;
	}

	
}
