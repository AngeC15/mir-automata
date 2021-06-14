package Model.automata.actions;

import Model.entities.Entity;

public class Print extends Action{
	private String msg;
	public Print(String str) {
		msg = str;
	}
	@Override
	public boolean apply(Entity e) {
		System.out.println("" + e.getID() + " : " + msg);
		return true;
	}

}
