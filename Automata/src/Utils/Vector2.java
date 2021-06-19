package Utils;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Vector2 extends Point2D.Float{
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float norm() {
		return (float) Math.sqrt(x*x + y*y);
	}
	
	public Vector2 normalize() {
		Vector2 r = new Vector2(x, y);
		float n = r.norm();
		if(n == 0.0f)
			return r;
		r.x /= n;
		r.y /= n;
		return r;
	}
	
	public Vector2 scale(float s) {
		return new Vector2(x*s, y*s);
	}
	
	public Vector2 add(Vector2 vect) {
		return new Vector2(x + vect.x, y + vect.y);
	}
	
	static public double argument(Vector2 vect) {
		return (400-vect.y)/(510-vect.x);
	}
	public Vector2 sub(Vector2 vect) {
		return new Vector2(x - vect.x, y - vect.y);
	}

	public Vector2 invert() {
		return new Vector2(-x, -y);
	}
	
	public float dot(Vector2 vec) {
		return x*vec.x + y*vec.y;
	}
	
	public Vector2 tripleCross(Vector2 a, Vector2 b) {
		float z = x*a.y - y*a.x;
		return new Vector2(b.y*z, -b.x*z);
	}
	
	public Vector2 transform(AffineTransform t) {
		Vector2 r = new Vector2(0, 0);
		t.transform(this, r);
		return r;
	}
	public Vector2 lerp(Vector2 vec, float val) {
		return new Vector2(x*(1-val) + vec.x*val, y*(1-val) + vec.y*val);
	}
}
