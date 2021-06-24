package Model.automata.conditions;

import Model.automata.creation.KeyExtension;
import Model.entities.Entity;

public class Key extends Condition {

	private KeyExtension key;

	public Key(KeyExtension key) {
		this.key = key;
	}

	@Override
	public boolean eval(Entity e) {
		return e.getKey(key);
	}

}
