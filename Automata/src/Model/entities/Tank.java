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
	/**
	 * Function takes a category and a direction and returns true if the closest entity
	 * of said category is in said direction.
	 * Only implemented to detect player
	 */
	@Override
	public boolean Closest(DirectionExtension direction, CategoryExtension categorie) {
		Entity closestEntity;
		double startAngle;
		double percentage = 7; // hand calibrated
		
		if (categorie == CategoryExtension.A) {
			closestEntity = super.world.getPlayer();
		} else {
			System.out.println("Not supported Entity type");
			return false;
		}
		
		switch (direction) {
		case E:
			startAngle = -22.5;
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
			startAngle = -157.5;
			break;

		case S:
			startAngle = -112.5;
			break;

		case SE:
			startAngle = -67.5;
			break;

		default:
			System.out.println("Non-existing direction");
			return false;
		}
		
		double endAngle = 360 / percentage + startAngle;
		
		// entity coordinates relative to this
		double relativeX = closestEntity.transform.getTranslateX() - transform.getTranslateX();
		double relativeY = closestEntity.transform.getTranslateY() - transform.getTranslateY();

		// Avoiding 0 division
		if (relativeX == 0)
			relativeX = 1;
	
		double relativeAngle = Math.toDegrees((Math.atan2(relativeY , relativeX)));
		
		if (direction == DirectionExtension.W) {
			// Need special treatment for West as we can't loop between 180° and -180°
			return (relativeAngle >= startAngle && relativeAngle <= 180)
					|| (relativeAngle <= -157.5 && relativeAngle >= -180);
			
		} else {
			return (relativeAngle >= startAngle && relativeAngle <= endAngle);
		}
		
		
	}
}
