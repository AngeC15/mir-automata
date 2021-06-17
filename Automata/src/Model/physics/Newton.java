package Model.physics;

import java.awt.geom.AffineTransform;

import Model.physics.primitives.Primitive;
import Utils.Vector2;

public class Newton {
	
	private int idx = 0;
	private static Vector2 origin = new Vector2(0, 0);
	
	protected Vector2 double_support(Primitive s1, Primitive s2, AffineTransform A1, AffineTransform A2, Vector2 d) {
		Vector2 vect1 = s1.support(d);
		Vector2 vect2 = s2.support(d.invert());
		
	}
	
	protected boolean lineCase(Vector2[] Triangle, Vector2 d) {
		Vector2 vect1 = new Vector2(Triangle[0].x-Triangle[1].x,
				Triangle[0].y-Triangle[1].y);
		Vector2 vect2 = Triangle[1].invert();
		Vector2 vectn = vect1.tripleCross(vect2);
		d = vectn;
		return false;
	}
	
	protected boolean triangleCase(Vector2[] Triangle, Vector2 d) {
		Vector2 vect1 = new Vector2(Triangle[1].x-Triangle[2].x,
				Triangle[1].y-Triangle[2].y);
		Vector2 vect2 = new Vector2(Triangle[0].x-Triangle[2].x,
				Triangle[0].y-Triangle[2].y);
		Vector2 vect3 = Triangle[2].invert();
		return true;
	}
	
	protected boolean contain(Vector2[] Triangle, Vector2 d) {
		if (idx+1 == 2) {
			return lineCase(Triangle, d);
		}
		return triangleCase(Triangle, d);
	}
	
	protected boolean GJK(Primitive s1, Primitive s2, AffineTransform A1, AffineTransform A2) {
		Vector2 vect1 = new Vector2((float)A1.getTranslateX(), (float)A1.getTranslateX());
		Vector2 vect2 = new Vector2((float)A2.getTranslateX(), (float)A2.getTranslateX());
		
		Vector2 d = vect1.sub(vect2);
		
		Vector2[] Triangle = new Vector2[3];
		Triangle[idx ++] = double_support(s1, s2, A1, A2, d);
		d = origin.sub(Triangle[0]);
		
		Vector2 S;
		
		while (true){
			S = double_support(s1, s2, d);
			if (S.dot(d) < 0)
				return false;
			Triangle[idx ++] = S;
			if (contain(Triangle, d))
				return true;
		}
	}
	
	
}
