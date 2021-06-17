package Model.automata.actions;

import Model.automata.creation.DirectionExtension;
import Model.entities.Entity;

public class Wait extends Action{

	
	public Wait(float weight) {
		super(weight);
	}

	@Override
	public boolean apply(Entity e) {
		e.setAction(EnumAction.WAIT);
		e.Wait();
		return true;
	}

}
