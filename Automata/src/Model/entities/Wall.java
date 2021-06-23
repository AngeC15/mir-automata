package Model.entities;

import java.awt.geom.AffineTransform;


import Model.automata.creation.DirectionExtension;
import Model.loader.AutomataLoader;
import Model.loader.TemplatesLoader;
import Model.map.Map;
import Model.physics.ColliderType;
import Model.physics.HitBox;
import Model.physics.PhysicsBody;
import Model.physics.PrimitiveInstance;
import Model.physics.primitives.Circle;
import View.Template;


public class Wall extends Entity{
	
	private boolean alive;
	private int x;
	private int y;
	private Map map;
	private double time;
	private final int max_step = 12;
	private int step_counter;
	private Template[] templates;
	
	public Wall(Map m, int px, int py) {
		super(AutomataLoader.get("Wall"));
		templates = new Template[2]; 
		templates[0] = TemplatesLoader.get("GenCell");
		templates[1] = TemplatesLoader.get("Dead");
		map = m;
		x = px;
		y = py;
		alive = true;
		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Circle(), AffineTransform.getScaleInstance(3.0f, 5.2f)));
		this.body = new PhysicsBody(h, ColliderType.Wall, 0.0f, 0.0f, this);
		time = System.currentTimeMillis();
		}
	
	public boolean getAlive() {
		return alive;
	}
	
	public boolean GotPower() {
		int cmpt = 0;
		int tmpx = (int)Math.floor((double)map.getX()/2);
		int tmpy = (int)Math.floor((double)map.getY()/2);
		try {
		for (int k = -1 ; k <= 1 ; k++) {
			for (int l = -1 ; l <= 1 ; l ++) {
				if (x == tmpx+k && y == tmpy+l)
					return false;
				if (!(k==0 && l==0) && ((Wall)map.get(x+k, y+l)).getAlive()) {
					cmpt++;
				}
			}
		}
		
		if(alive) 
			return cmpt >= 3;
		else 
			return cmpt >= 5;
		
		}
		catch(Exception e){
			System.exit(0);
		}
		return false;
	}
	
	public boolean generationOver() {
		return step_counter >= max_step;
	}
	
	@Override
	public boolean GotStuff() {
		return !generationOver();
	}
	
	@Override
	public void Throw(DirectionExtension dir) {
		if (dir == DirectionExtension.F) {
			this.avatar.setTemplate(templates[0]);
			setAlive(true);
		}
		else {
			this.avatar.setTemplate(templates[1]);
			setAlive(false);
		}
	}
	
	public void setAlive(boolean state) {
		alive = state;
	}
	
	public void Explode() {
		if(!alive)
			map.remove(x, y);
	}
	
	public boolean step() {
		if (System.currentTimeMillis()-time > 500) {
			time = System.currentTimeMillis();
			step_counter ++;
			return automaton.step(this);
		}
		return true;
	}
	
}
