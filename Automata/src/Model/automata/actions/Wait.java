package Model.automata.actions;

import Model.entities.Entity;

public class Wait extends Action {

	public Wait(float weight) {
		super(weight);
	}

	@Override
	public boolean apply(Entity e) {
		e.addAction(EnumAction.WAIT);
		e.Wait();
		return true;
	}

}
