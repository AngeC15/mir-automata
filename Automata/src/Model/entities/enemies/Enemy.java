package Model.entities.enemies;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import Model.automata.creation.CategoryExtension;
import Model.automata.creation.DirectionExtension;
import Model.entities.DeadEntity;
import Model.entities.Decor;
import Model.entities.Entity;
import Model.entities.LivingEntity;
import Model.entities.weapon.Weapon;
import Model.loader.AutomataLoader;
import Model.pathfinding.Node;
import Model.physics.ColliderType;
import Utils.Vector2;

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
		rotate();
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
		double relativeX = world.getPlayer().getTransform().getTranslateX() - getTransform().getTranslateX();
		double relativeY = world.getPlayer().getTransform().getTranslateY() - getTransform().getTranslateY();
		double distance = Math.sqrt(relativeX * relativeX + relativeY * relativeY);
		if (distance >= shootDistance)
			return false;
		double now = System.currentTimeMillis();
		if (now - lastAttack > cooldown)
			return true;
		return false;
	}

	@Override
	public void Egg(DirectionExtension dir) {
		new DeadEntity(this, AutomataLoader.get("Dead"), team, 350, "DeadExplosion");
		this.getWorld().removeEntity(getID());
	}

	@Override
	public Color getColor() {
		return Color.red;
	}

	@Override
	public boolean Cell(DirectionExtension direction, CategoryExtension categorie) {
		return false;
	}
	
	private void aStarPlayer(int margin) {
		// create the two queues
		Queue<Node> closedList = new LinkedList<Node>();
		PriorityQueue<Node> openList = new PriorityQueue<Node>();
		
		// create and insert the start node
		openList.add(new Node((int) getTransform().getTranslateX(), 
				(int) getTransform().getTranslateY(), 0, 0, null));
		
		// create a player Node for coordinate comparison
		Entity player = world.getPlayer();
		Node playerNode = new Node((int) player.getTransform().getTranslateX(), 
				(int) player.getTransform().getTranslateY(), 0, 0, null);
		
		while (!openList.isEmpty()) {
			// extract highest priority node
			Node currentNode = openList.remove();
			
			if (currentNode.isSameCoordinates(playerNode, margin)) {
				// TODO : reconstruct the path
				return; // end of A* algorithm
				
			} else {
				// create and store the 4 neighbors
				ArrayList<Node> neighbors = new ArrayList<Node>();
				
			}
		}
	}

}
