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
	private int x;
	private int y;
	private Map map;
	public Wall(Map m, int px, int py) {
		super(AutomataLoader.get("Wall"));
		map = m;
		x = px;
		y = py;
		alive = true;
		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(3.1f, 5.2f)));
		this.body = new PhysicsBody(h, 0.0f, 0.0f);
//		body.getTransform().rotate(Math.PI);
	}
	
	public boolean getAlive() {
		return alive;
	}
	
	public boolean GotPower() {
		int cmpt = 0;
		try {
		for (int k = -1 ; k <= 1 ; k++) {
			for (int l = -1 ; l <= 1 ; l ++) {
				if (!(k==0 && l==0) && ((Wall)map.get(x+k, y+l)).getAlive()) {
					if (++cmpt == 4) 
						return true;
				}
			}
		}
		return false;
		}
		catch(Exception e){
			System.exit(0);
		}
		return false;
	}
	
	public boolean GotStuff() {
		return !map.generationOver();
	}
	
	@Override
	public void Throw(DirectionExtension dir) {
		if (dir == DirectionExtension.F) {
			setAlive(true);
		}
		else setAlive(false);
	}
	
	public void setAlive(boolean state) {
		alive = state;
	}
	
	public void Explode() {
		if(!alive)
			map.remove(x, y);
	}
	
}
