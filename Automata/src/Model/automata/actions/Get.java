package Model.automata.actions;


import Model.entities.Entity;

public class Get extends Action{
	
	@Override
	public boolean apply(Entity e) {
		e.Get();
		return false;
	}
		
}
