package Model.automata.actions;

import Model.Entity;
import Model.GameState;

public class Print extends Action{
	private String msg;
	public Print(String str) {
		msg = str;
	}
	@Override
	public boolean apply(Entity e, GameState s) {
		System.out.println("" + e.getID() + " : " + msg);
		return true;
	}

}
