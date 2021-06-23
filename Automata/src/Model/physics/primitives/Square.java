package Model.physics.primitives;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import Utils.Vector2;

public class Square extends Primitive{
	private static final Vector2[] points = {
		new Vector2(1,1).scale(0.5f),
		new Vector2(1,-1).scale(0.5f),
		new Vector2(-1,1).scale(0.5f),
		new Vector2(-1,-1).scale(0.5f)
	};
	
	@Override
	public Vector2 support(Vector2 d) {
		d = d.normalize();
		int x = (int)(1.999f - (d.x + 1)); // 1:0 -> -1:2
		int y = (int)(1.999f - (d.y + 1));
		return points[2*x + y];
		
	}

	@Override
	public float extRadius() {
		return 0.7072f;
	}

	@Override
	public void debug(Graphics2D g, AffineTransform transform) {
		g.transform(transform);
		g.setColor(Color.green);
		g.draw(new Rectangle.Float(-0.5f, -0.5f, 1, 1));
		
	}

}
