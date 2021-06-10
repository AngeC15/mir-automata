package Model.automata.actions;

import Model.Entity;
import Model.GameState;

public class Print extends Action{

	@Override
	public boolean apply(Entity e, GameState s) {
		System.out.println("" + e.getID() + "says hello");
		return false;
	}

}
