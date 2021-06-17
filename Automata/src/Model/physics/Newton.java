package Model.physics;

import java.awt.geom.AffineTransform;

import Model.physics.primitives.Primitive;
import Utils.Vector2;

public class Newton {
	
	protected Vector2 dobble_support(Primitive s1, Primitive s2, Vector2 d) {
		return s1.support(d) - s2.support(d.invert());
	}
	
	protected boolean GJK(Primitive s1, Primitive s2, AffineTransform A1, AffineTransform A2) {
		Vector2 vect1 = new Vector2((float)A1.getTranslateX(), (float)A1.getTranslateX());
		Vector2 vect2 = new Vector2((float)A2.getTranslateX(), (float)A2.getTranslateX());
		
		Vector2 d = vect1.sub(vect2);
		
		Vector2[] Triangle = {dobble_support(s1, s2, d)};
		
		
		
		
		return true;	
	}
	
	
}
