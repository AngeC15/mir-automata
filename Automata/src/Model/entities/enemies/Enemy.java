package Model.entities.enemies;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Vector;

import Model.automata.creation.CategoryExtension;
import Model.automata.creation.DirectionExtension;
import Model.entities.DeadEntity;
import Model.entities.Entity;
import Model.entities.LivingEntity;
import Model.entities.weapon.Weapon;
import Model.loader.AutomataLoader;
import Model.path.Grid;
import Model.path.Node;
import Utils.Functions;
import Utils.Vector2;

public abstract class Enemy extends LivingEntity {

	protected Weapon weapon;
	protected int cooldown;
	protected double shootDistance;
	protected float friction;
	protected float maxSpeed;
	protected double lastAttack;
	private ArrayDeque<Node> path;
	private double start;
	private Grid grid;
	private Vector2 last_direction;

	public Enemy(String automaton) {
		super(AutomataLoader.get(automaton), 2);
		start = System.currentTimeMillis();
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
	
	public boolean addLifeBar() {
		return true;
	}
	
	private void update_path() {
		if (grid == null) grid = world.getGrid();
//		double gridw = grid.getDim()*grid.getP();
//		double gridh = grid.getDim()*grid.getN();
		double cx = body.getTransform().getTranslateX()/grid.getDim()+grid.getP()/2.0f + 0.5f;
		double cy = body.getTransform().getTranslateY()/grid.getDim()+grid.getN()/2.0f + 0.5f;
		int x = ((int)(cx)%grid.getP());
		int y = ((int)(cy)%grid.getN());
		Node start = new Node(x, y, world.getMap());
		
		cx = world.getPlayer().getTransform().getTranslateX()/grid.getDim()+grid.getP()/2.0f + 0.5f;
		cy = world.getPlayer().getTransform().getTranslateY()/grid.getDim()+grid.getN()/2.0f + 0.5f;
		x = ((int)(cx)%grid.getP());
		y = ((int)(cy)%grid.getN());
		Node goal = new Node(x, y, world.getMap());
		path = grid.starPath(start, goal);
	}
	
	private double distance() {
		double tmpx = Math.pow(world.getPlayer().getTransform().getTranslateX() - body.getTransform().getTranslateX(), 2);
		double tmpy = Math.pow(world.getPlayer().getTransform().getTranslateY() - body.getTransform().getTranslateY(), 2);
		return Math.sqrt(tmpx + tmpy);
	}
	
	@Override
	public void Move(DirectionExtension dir) {
//		Vector2 vect;
//		// DirectionExtension dir2 =
//		// DirectionExtension.RelToAbsolute(this.directionEntite, dir);
//		if (dir.ordinal() < 4) {
//			Vector2 direction = new Vector2((float) body.getTransform().getShearX(),
//					(float) body.getTransform().getScaleY());
//			vect = Functions.getRelativeDir(dir, direction);
//		} else {
//			vect = Functions.getAbsoluteDir(dir);
//		}
		double tmp = System.currentTimeMillis();
		if (tmp - start > 500 && distance() < 30) {
			start = tmp;
			update_path();
		}
		if (path == null || path.size() < 2)
			update_path();
		Vector2 target = path.peek().getWorldCoordinate();
		
		Vector2 pos = new Vector2((float)body.getTransform().getTranslateX(), (float)body.getTransform().getTranslateY());
		Vector2 diff = target.sub(pos);
//		float tmp2 = diff.y;
//		diff.y = diff.x;
//		diff.y = tmp2;
		if (diff.norm() < 6.0f) {
			path.pop();
		}
		else {
			body.accelerate(world.getElapsed(), diff.normalize().scale(acceleration));
		}
		
		
		// 2 next lines commented during the merge phase
		// vect.scale(world.getElapsed()*velocity/1000.0f);
		// transform.concatenate(AffineTransform.getTranslateInstance(vect.x, vect.y));
		// System.out.println("BOuge vers " + dir2);
		
//		body.accelerate(world.getElapsed(), vect.scale(acceleration));
	}
}
