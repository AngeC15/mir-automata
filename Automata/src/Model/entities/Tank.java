package Model.entities;

import Model.World;
import Model.automata.creation.CategoryExtension;
import Model.automata.creation.DirectionExtension;
import Model.loader.AutomataLoader;

public class Tank extends Entity {

	public Tank(World w) {
		super(AutomataLoader.get("Tank"), w);
		velocity = 20;
	}
	
	@Override
	public boolean Closest(DirectionExtension direction, CategoryExtension categorie) {
		Entity closestEntity;
		double startAngle;
		double percentage = 12.5;
		if (categorie == CategoryExtension.A) {
			closestEntity = super.world.getPlayer();
		}
		else
		{
			System.out.println("Not supported Entity type");
			return false;
		}
		switch (direction) {
		case E:
			startAngle = 337.5;
			break;

		case NE:
			startAngle = 22.5;
			break;

		case N:
			startAngle = 67.5;
			break;

		case NW:
			startAngle = 112.5;
			break;

		case W:
			startAngle = 157.5;
			break;

		case SW:
			startAngle = 202.5;
			break;

		case S:
			startAngle = 247.5;
			break;

		case SE:
			startAngle = 292.5;
			break;
			
		default:
			System.out.println("Non-existing direction");
			return false;
		}
		
		double endAngle = 360/percentage+startAngle;
		double relativeX = closestEntity.transform.getTranslateX() - transform.getTranslateX();
		double relativeY = closestEntity.transform.getTranslateY() - transform.getTranslateY();
		
		// Marouflage
		if(relativeX == 0)
			relativeX = 1;
		double relativeAngle = Math.atan(relativeY/relativeX);
		return (relativeAngle>=startAngle && relativeAngle <= endAngle);
	}
}
