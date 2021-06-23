package Model.physics.primitives;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import Utils.Vector2;

public abstract class Primitive {
	public abstract Vector2 support(Vector2 d);
	public abstract float extRadius();
	public abstract void debug(Graphics2D g, AffineTransform transform);
}
