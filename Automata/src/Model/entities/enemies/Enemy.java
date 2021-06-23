package Model.entities.enemies;

import java.awt.Color;

import Model.automata.creation.CategoryExtension;
import Model.automata.creation.DirectionExtension;
import Model.entities.Entity;
import Model.entities.LivingEntity;
import Model.entities.weapon.Weapon;
import Model.loader.AutomataLoader;
import Utils.Vector2;
import Model.entities.DeadEntity;

public abstract class Enemy extends LivingEntity {

	protected Weapon weapon;
	protected int cooldown;
	protected double shootDistance;
	protected float friction;
	protected float maxSpeed;
	protected double lastAttack;

	public Enemy(String automaton) {
		super(AutomataLoader.get(automaton), 2);
	}

	protected void rotate() {
		Entity player = world.getPlayer();

		double relativeX = player.getTransform().getTranslateX() - getTransform().getTranslateX();
		double relativeY = player.getTransform().getTranslateY() - getTransform().getTranslateY();
		double relativeAngle = Math.atan2(relativeY, relativeX);

		relativeAngle -= Math.atan2(getTransform().getShearY(), getTransform().getScaleY());
		getTransform().rotate(relativeAngle - Math.toRadians(90));
	}

	/**
	 * Takes a category and a direction and returns true if the closest entity of
	 * said category is in said direction. Only implemented to detect player
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

		// entity coordinates relative to this
		double relativeX = closestEntity.getTransform().getTranslateX() - getTransform().getTranslateX();
		double relativeY = closestEntity.getTransform().getTranslateY() - getTransform().getTranslateY();
		double distance = Math.sqrt(relativeX * relativeX + relativeY * relativeY);

		if (distance < shootDistance)
			return false;

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
		double relativeAngle = Math.toDegrees((Math.atan2(relativeY, relativeX)));

		if (direction == DirectionExtension.W) {
			// Need special treatment for West as we can't loop between 180° and -180°
			return (relativeAngle >= startAngle && relativeAngle <= 180)
					|| (relativeAngle <= -157.5 && relativeAngle >= -180);

		} else {
			return (relativeAngle >= startAngle && relativeAngle <= endAngle);
		}
	}

	@Override
	public void Pop(DirectionExtension dir) {
		lastAttack = System.currentTimeMillis();
		Vector2 vector = new Vector2(0, 1);
		weapon.attack(this, vector);
	}

	@Override
	public void Hit(DirectionExtension dir) {
		lastAttack = System.currentTimeMillis();
		Vector2 vector = new Vector2(0, 1);
		weapon.attack(this, vector);
	}

	@Override
	public boolean GotPower() {
		double now = System.currentTimeMillis();
		if (now - lastAttack > cooldown)
			return true;
		return false;
	}
	
	public Color getColor() {
		return Color.red;
	}
}
