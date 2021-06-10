package Utils;

public class Vector2 {
	public float x;
	public float y;
	
	public float norm() {
		return (float) Math.sqrt(x*x + y*y);
	}
	public void normalize() {
		float n = norm();
		x /= n;
		y /= n;
	}
	
	public void scale(float s) {
		s /= norm();
		x *= s;
		y *= s;
	}
}
