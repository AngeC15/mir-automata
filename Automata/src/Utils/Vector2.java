package Utils;

public class Vector2 {
	public float x;
	public float y;
	
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
		r.x /= n;
		r.y /= n;
		return r;
	}
	
	public Vector2 scale(float s) {
		x *= s;
		y *= s;
		return this;
	}
	
	public Vector2 add(Vector2 vect) {
		return new Vector2(x + vect.x, y + vect.y);
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
	
	public Vector2 tripleCross(Vector2 vec) {
		float z = x*vec.y - y*vec.x;
		return new Vector2(y*z, -x*z);
	}
}
