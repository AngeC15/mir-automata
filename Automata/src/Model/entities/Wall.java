package Model.entities;

import java.awt.geom.AffineTransform;
import java.io.IOException;

import Model.World;
import Model.automata.Automaton;
import Model.automata.creation.CategoryExtension;
import Model.automata.creation.DirectionExtension;
import Model.loader.AutomataLoader;
import Model.loader.TemplatesLoader;
import Model.map.Map;
import Model.physics.HitBox;
import Model.physics.PhysicsBody;
import Model.physics.PrimitiveInstance;
import Model.physics.primitives.Circle;
import View.Avatar;
import View.Template;

public class Wall extends Entity{
	
	private boolean alive;
	private int x;
	private int y;
	private Map map;
	private long cmpt_tick;
	
	public Wall(Map m, int px, int py) {
		super(AutomataLoader.get("Wall"));
		map = m;
		x = px;
		y = py;
		alive = true;
		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(3.1f, 5.2f)));
		this.body = new PhysicsBody(h, 0.0f, 0.0f);
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
					if (++cmpt == 4 && !alive) 
						return true;
					else if (++cmpt == 4 && alive)
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
	@Override
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
	
	public boolean step() {
//		if (cmpt_tick++%100 == 0) {
//			if (!alive) {
//				Template tmp = TemplatesLoader.get("Dead");
//				try {
//					Avatar av = new Avatar(this, tmp);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
			return automaton.step(this);
//		}
//		return false;
	}
	
}
