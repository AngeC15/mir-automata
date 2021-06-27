package Model.physics.primitives;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import Utils.Vector2;

public class Circle extends Primitive {
	@Override
	public Vector2 support(Vector2 d) {
		return d.normalize().scale(0.5f);
	}

	@Override
	public void debug(Graphics2D g, AffineTransform transform) {
		g.transform(transform);
		g.setColor(Color.green);
		g.setStroke(new BasicStroke(0.1f));
		g.draw(new Ellipse2D.Float(-0.5f, -0.5f, 1, 1));

	}

	@Override
	public float extRadius() {
		return 0.5f;
	}
	
	public String toString() {
		return "Circle";
	}
}
