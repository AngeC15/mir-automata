package Model.automata.conditions;

import Model.automata.creation.DirectionExtension;
import Model.entities.Entity;

public class MyDir extends Condition {

	private DirectionExtension direction;

	public MyDir(DirectionExtension direction) {
		this.direction = direction;
		System.out.println("Direction vaut" + this.direction);

	}

	@Override
	public boolean eval(Entity e) {
		return e.MyDir(direction);
	}
}
