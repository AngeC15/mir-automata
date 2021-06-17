package Model.automata.actions;


import Model.entities.Entity;

public class Get extends Action{
	
	
	
	public Get(float weight) {
		super(weight);
	}

	@Override
	public boolean apply(Entity e) {
		e.setAction(EnumAction.GET);
		e.Get();
		return false;
	}
		
}
