package Model.automata.conditions;

import Model.automata.creation.CategoryExtension;
import Model.automata.creation.DirectionExtension;
import Model.entities.Entity;

public class Cell extends Condition{
	
	private DirectionExtension direction;
	private CategoryExtension categorie;
	
	
	
	public Cell(DirectionExtension direction, CategoryExtension categorie) {
		this.direction = direction;
		this.categorie = categorie;
		System.out.println("La Direction vaut " + this.direction + " La categorie vaut " + this.categorie);
	}



	@Override
	public boolean eval(Entity e) {
		e.Cell(direction, categorie);
		return false;
	}
}
