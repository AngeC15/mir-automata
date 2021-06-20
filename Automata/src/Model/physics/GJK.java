package Model.physics;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

import Model.physics.primitives.Primitive;
import Utils.Vector2;

public class GJK {
	private static int idx = 0;
	private static Vector2 origin = new Vector2(0, 0);
	private static Vector2 n = new Vector2(0, 0);
	
	protected static Vector2 double_support(Primitive s1, Primitive s2, AffineTransform A1, AffineTransform A2, Vector2 d) {
		Vector2 d1 = new Vector2(0,0);
		Vector2 d2 = new Vector2(0,0);
		float angle1 = (float) Math.atan2(A1.getShearY(), A1.getScaleY());
		float angle2 = (float) Math.atan2(A2.getShearY(), A2.getScaleY());
		d1 = d.transform(AffineTransform.getRotateInstance(-angle1));
		d2 = d.transform(AffineTransform.getRotateInstance(-angle2));

		Vector2 vect1 = (s1.support(d1)).transform(A1);
		Vector2 vect2 = (s2.support(d2.invert())).transform(A2);
		return vect1.sub(vect2);
	}

	protected static boolean lineCase(Vector2[] triangle, Vector2 d) {
		Vector2 vect1 = new Vector2(triangle[0].x - triangle[1].x, triangle[0].y - triangle[1].y);
		Vector2 vect2 = triangle[1].invert();
		Vector2 vectn = vect1.tripleCross(vect2, vect1);
		d = vectn;
		return false;
	}

	protected static boolean triangleCase(Vector2[] triangle, Vector2 d) {
		Vector2 vect1 = new Vector2(triangle[1].x - triangle[2].x, triangle[1].y - triangle[2].y);
		Vector2 vect2 = new Vector2(triangle[0].x - triangle[2].x, triangle[0].y - triangle[2].y);
		Vector2 vect3 = triangle[2].invert();
		Vector2 vectn1 = vect2.tripleCross(vect1, vect1);
		Vector2 vectn2 = vect1.tripleCross(vect2, vect2);
		System.out.println(idx);
		if (vectn1.dot(vect3) > 0) {
			idx--;
			triangle[idx - 2] = triangle[idx - 1];
			triangle[idx - 1] = triangle[idx];
			d = vectn1;
			return false;
		} else if (vectn2.dot(vect3) > 0) {
			triangle[idx-2] = triangle[idx-1];
			idx--;
			d = vectn2;
			return false;
		}
		n = d;
		return true;
	}

	protected static boolean contain(Vector2[] triangle, Vector2 d) {
		if (idx == 2) {
			return lineCase(triangle, d);
		}
		return triangleCase(triangle, d);
	}

	public static boolean collide(Primitive s1, Primitive s2, AffineTransform A1, AffineTransform A2) {
		idx = 0;
		n = new Vector2(0, 0);
		Vector2 vect1 = new Vector2((float)A1.getTranslateX(), (float)A1.getTranslateY());
		Vector2 vect2 = new Vector2((float)A2.getTranslateX(), (float)A2.getTranslateY());
		
		Vector2 d = (vect1.sub(vect2)).normalize();
		
		Vector2[] triangle = new Vector2[3];
		triangle[idx++] = double_support(s1, s2, A1, A2, d);
		d = origin.sub(triangle[0]);

		Vector2 S;

		while (true) {
			S = double_support(s1, s2, A1, A2, d);
			if (S.dot(d) < 0)
				return false;
			triangle[idx ++] = S;
			if (contain(triangle, d)) {
				n = d;
				return true;
			}
		}
	}
	public static Vector2 get_normal() {
		return n;
	}
}
