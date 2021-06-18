package Model.physics.primitives;

import Utils.Vector2;

public class Circle extends Primitive{
	@Override
	public Vector2 support(Vector2 d) {
		return d.normalize().scale(0.5f);
	}
}
