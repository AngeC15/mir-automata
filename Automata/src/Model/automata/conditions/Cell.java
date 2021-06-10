package Model.automata.conditions;

import Model.Entity;
import Model.GameState;

public class Cell extends Condition{
	
	private Object direction;
	private Object categorie;
	
	
	
	public Cell(Object direction, Object categorie) {
		this.direction = direction;
		this.categorie = categorie;
		System.out.println("La Direction vaut " + this.direction + " La categorie vaut " + this.categorie);
	}



	@Override
	public boolean eval(Entity e, GameState s) {
		System.out.println("Pas encore implémenté, merci de repasser");
		return false;
	}
}
