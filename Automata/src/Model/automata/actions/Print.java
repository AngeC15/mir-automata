package Model.automata.actions;

import Model.entities.Entity;

public class Print extends Action{
	private String msg;
	
	public Print(String str, float p) {
		super(p);
		msg = str;
	}
	@Override
	public boolean apply(Entity e) {
		e.setAction(Enum_Action.PRINT);
		System.out.println("" + e.getID() + " : " + msg);
		return true;
	}

}
