package Model.entities;

import java.awt.geom.AffineTransform;

import Model.World;
import Model.automata.Automaton;
import Model.automata.creation.CategoryExtension;
import Model.automata.creation.DirectionExtension;
import Model.loader.AutomataLoader;
import Model.map.Map;
import Model.physics.HitBox;
import Model.physics.PhysicsBody;
import Model.physics.PrimitiveInstance;
import Model.physics.primitives.Circle;

public class Wall extends Entity{
	
	private boolean alive;

	public Wall(World w) {
		super(AutomataLoader.get("Wall"), w, w.getNextId());
		this.acceleration = 80.0f;
		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(2, 2)));
		this.body = new PhysicsBody(h, 0.0f, 0.0f);
	}
	
	public Wall(World w, boolean state) {
		super(AutomataLoader.get("Wall"), w, w.getNextId());
		this.acceleration = 80.0f;
		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(2, 2)));
		this.body = new PhysicsBody(h, 0.0f, 0.0f);
		this.alive = state;
	}
	
	public boolean getAlive() {
		return alive;
	}
	
	public boolean GotPower(Map map, int i, int j) {
		int cmpt = 0;
		if (((Wall) map.get(i, j+1)).getAlive()) {
			cmpt ++;
		}
		if (((Wall) map.get(i+1, j+1)).getAlive()) {
			cmpt ++;
		}
		if (((Wall) map.get(i+1, j)).getAlive()) {
			cmpt ++;
		}
		if (((Wall) map.get(i+1, j-1)).getAlive()) {
			cmpt ++;
			if (cmpt == 4) return true;
		}
		if (((Wall) map.get(i, j-1)).getAlive()) {
			cmpt ++;
			if (cmpt == 4) return true;
		}
		if (((Wall) map.get(i-1, j-1)).getAlive()) {
			cmpt ++;
			if (cmpt == 4) return true;
		}
		if (((Wall) map.get(i-1, j)).getAlive()) {
			cmpt ++;
			if (cmpt == 4) return true;
		}
		if (((Wall) map.get(i-1, j+1)).getAlive()) {
			cmpt ++;
			if (cmpt == 4) return true;
		}
		return false;
	}
	
	public void Throw(DirectionExtension dir, CategoryExtension categorie) {
		if (categorie == CategoryExtension.O) {
			setAlive(true);
		}
		else setAlive(false);
	}
	
	public void setAlive(boolean state) {
		alive = state;
	}
	
	
}
