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
	protected ArrayList<Node> pathToPlayer;

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
		
		pathToPlayer = new ArrayList<Node>();
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
		
		double relativeX = closestEntity.getTransform().getTranslateX() - getTransform().getTranslateX();
		double relativeY = closestEntity.getTransform().getTranslateY() - getTransform().getTranslateY();
		double distance = Math.sqrt(relativeX * relativeX + relativeY * relativeY);
		
		if (distance < shootDistance)
			return false;
		
		try {
			aStarPlayer(1);
			
			Node node = new Node((int) getTransform().getTranslateX(), (int) getTransform().getTranslateY(), null);
			
			while (pathToPlayer.get(0).isSameCoordinates(node, 1)) {
				pathToPlayer.remove(0);
			}
			
			relativeX = pathToPlayer.get(0).getX() - getTransform().getTranslateX();
			relativeY = pathToPlayer.get(0).getY() - getTransform().getTranslateY();
			
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
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
	
	private void aStarPlayer(int margin) throws Exception {
		// create the two queues
		Queue<Node> closedList = new LinkedList<Node>();
		PriorityQueue<Node> openList = new PriorityQueue<Node>();
		
		int firstX = (int) getTransform().getTranslateX();
		int firstY = (int) getTransform().getTranslateY();
		
		// create and insert the start node
		Node firstNode = new Node(firstX, firstY, null);
		openList.add(firstNode);
		
		// create a player Node for coordinate comparison
		Entity player = world.getPlayer();
		Node playerNode = new Node((int) player.getTransform().getTranslateX(), 
				(int) player.getTransform().getTranslateY(), null);
		
		while (!openList.isEmpty()) {
			// extract highest priority node
			Node currentNode = openList.remove();
			
			if (currentNode.isSameCoordinates(playerNode, margin)) {
				
				while (currentNode != null) {
					pathToPlayer.add(0, currentNode);
					currentNode = currentNode.getPreviousNode();
				}
				
				return; // end of A* algorithm
				
			} else {
				System.out.println("loop");
				
				// create and store the 4 neighbors
				ArrayList<Node> neighbors = new ArrayList<Node>();
				neighbors.add(new Node(currentNode.getX() + 2 * margin, currentNode.getY(), currentNode));
				neighbors.add(new Node(currentNode.getX() - 2 * margin, currentNode.getY(), currentNode));
				neighbors.add(new Node(currentNode.getX(), currentNode.getY() + 2 * margin, currentNode));
				neighbors.add(new Node(currentNode.getX(), currentNode.getY() - 2 * margin, currentNode));

				// remove neighbors if they are a wall
				for (int i = 0; i < neighbors.size(); i++) {
					Node neighbor = neighbors.get(i);
					int x = (int) (neighbor.getX() / 5.3f + 15);
					int y = (int) (neighbor.getY() / 5.3f + 15);
					if (world.getMap().hasWall(x, y, margin)) {
						neighbors.remove(i);
						i--;
					}
				}
				
				// remove duplicate neighbors (for later contains)
				for (Node neighbor : neighbors) {
					
					Node replaceNode = null;
					
					for (Node node : closedList) {
						if (neighbor.isSameCoordinates(node, margin)) {
							replaceNode = node;
							break;
						}
					}
					
					if (replaceNode == null) {
						for (Node node : openList) {
							if (neighbor.isSameCoordinates(node, margin)) {
								replaceNode = node;
								break;
							}
						}
					}
					
					if (replaceNode != null) {
						neighbor = replaceNode;
					}
				}
				
				// check each neighbor
				for (Node neighbor : neighbors) {
					if (! (closedList.contains(neighbor) || (openList.contains(neighbor) && neighbor.cost < currentNode.cost + 1))) {
						neighbor.cost = currentNode.cost + 1;
						neighbor.heuristic = neighbor.cost + (int) neighbor.distance(playerNode);
						// neighbor.heuristic *= 5; weighted A* 
						openList.add(neighbor);
					}
				}
				closedList.add(currentNode);
			}
		}
		
		throw new Exception("A* algorithm error");
	}

}
