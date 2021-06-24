package Model.entities;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.Random;

import Model.automata.creation.DirectionExtension;
import Model.loader.AutomataLoader;
import Model.loader.TemplatesLoader;
import Model.map.Map;
import Model.physics.ColliderType;
import Model.physics.HitBox;
import Model.physics.PhysicsBody;
import Model.physics.PrimitiveInstance;
import Model.physics.primitives.Square;
import View.Template;

public class Decor extends Entity {

	private int state; // 0 = dead, 1 == alive, 3 == tree
	private int x;
	private int y;
	private Map map;
	private Template[] templates;
	private int random;

	public Decor(Map m, int px, int py) {
		super(AutomataLoader.get("Wall"), 3);
		templates = new Template[3];
		templates[0] = TemplatesLoader.get("Wall");
		templates[1] = TemplatesLoader.get("Dead");
		templates[2] = TemplatesLoader.get("Tree");
		map = m;
		x = px;
		y = py;
		state = 1;

		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Square(), AffineTransform.getScaleInstance(4f, 4f)));
		this.body = new PhysicsBody(h, ColliderType.Wall, 0.0f, 0.0f, this);
	}

	public int getAlive() {
		return state;
	}

	public boolean generation() {
		int cmpt = 0;
		try {
			for (int k = -1; k <= 1; k++) {
				for (int l = -1; l <= 1; l++) {
					if (!(k == 0 && l == 0) && ((Decor) map.get(x + k, y + l)).getAlive() == 1) {
						cmpt++;
					}
				}
			}

			if (state == 1)
				return cmpt >= 3;
			else
				return cmpt >= 5;

		} catch (Exception e) {
			System.exit(0);
		}

		return false;
	}

	@Override
	public boolean GotPower() {
		int tmpx = (int) Math.floor((double) map.getX() / 2);
		int tmpy = (int) Math.floor((double) map.getY() / 2);
		for (int k = -1; k <= 1; k++) {
			for (int l = -1; l <= 1; l++) {
				if (x == tmpx + k && y == tmpy + l)
					return false;
			}
		}
		if (GotStuff()) {
			return generation();
		} else {
			if (random == 1 && state == 0)
				return true;
			return false;
		}
	}

	public boolean generationOver() {
		return map.stepCounter() >= map.getMaxStep();
	}

	@Override
	public boolean GotStuff() {
		return !generationOver();
	}

	@Override
	public void Throw(DirectionExtension dir) {
		if (dir == DirectionExtension.F) {
			this.avatar.setTemplate(templates[0]);
			setState(1);
		} else if (dir == DirectionExtension.B) {
			this.avatar.setTemplate(templates[1]);
			setState(0);
		} else if (dir == DirectionExtension.H) {
			this.avatar.setTemplate(templates[2]);
			setState(3);
		} else {
			Random rn = new Random();
			random = rn.nextInt(30);
		}
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public void Explode() {
		if (state == 0)
			map.remove(x, y);
	}

	@Override
	public boolean step() {
		if (map.step()) {
			return automaton.step(this);
		}
		return true;
	}

	@Override
	public Color getColor() {
		if (state == 0)
			return Color.getHSBColor(60 / 360.0f, 20 / 100.0f, 90 / 100.0f);
		else if (state == 1)
			return Color.gray;
		else
			return Color.getHSBColor(118 / 360.0f, 60 / 100.0f, 50 / 100.0f);
	}

	@Override
	public String toString() {
		if (state == 0)
			return "Dead";
		else if (state == 1)
			return "Wall";
		else
			return "Tree";
	}

}
