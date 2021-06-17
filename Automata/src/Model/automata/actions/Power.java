package Model.automata.actions;

import Model.entities.Entity;

public class Power extends Action{

	public Power(float weight) {
		super(weight);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean apply(Entity e) {
		e.setAction(EnumAction.POWER);
		e.Power();
		return false;
	}

	
}
