package Model.physics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import Model.physics.primitives.Primitive;
import Utils.Vector2;

//group of primitives
public class HitBox {
	float radius = 0.0f;
	ArrayList<PrimitiveInstance> shapes;
	
	public HitBox() {
		shapes = new ArrayList<PrimitiveInstance>();
	}
	
	public void add(PrimitiveInstance s) {
		float x = (float) s.get_transform().getTranslateX();
		float y = (float) s.get_transform().getTranslateY();
		float r = (new Vector2(x, y)).norm();
		r += s.get_prim().extRadius()*Math.max(s.get_transform().getScaleX(), s.get_transform().getScaleY());
		if(r > radius)
			radius = r;
		shapes.add(s);
		//System.out.println(radius);
	}
	
	public PrimitiveInstance get(int k) {
		return shapes.get(k); 
	}
	
	public float extRadius() {
		return radius;
	}
	public void debug(Graphics2D g) {
		for(PrimitiveInstance p : shapes) {
			p.debug(g);
		}
		
	}
	
	
	
	
}
