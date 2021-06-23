package Model.entities;

import java.awt.geom.AffineTransform;

import Model.automata.creation.CategoryExtension;
import Model.automata.creation.DirectionExtension;
import Model.loader.AutomataLoader;
import Model.physics.ColliderType;
import Model.physics.HitBox;
import Model.physics.PhysicsBody;
import Model.physics.PrimitiveInstance;
import Model.physics.primitives.Circle;

public class Tank extends LivingEntity {

	public Tank() {
		super(AutomataLoader.get("Tank"), 2);
		acceleration = 40.f;
		
		// copied from players
		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(3.1f, 5.2f)));
		this.body = new PhysicsBody(h, ColliderType.Character,15.0f, 20.0f, this);
		this.life = 100;
		this.damage = 10;
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
		double relativeX = closestEntity.getTransform().getTranslateX() - getTransform().getTranslateX();
		double relativeY = closestEntity.getTransform().getTranslateY() - getTransform().getTranslateY();

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
	@Override
	public String toString() {
		return "Tank";
	}
}
