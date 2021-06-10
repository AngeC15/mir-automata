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
	public void normalize() {
		float n = norm();
		x /= n;
		y /= n;
	}
	
	public void scale(float s) {
		x *= s;
		y *= s;
	}
}
