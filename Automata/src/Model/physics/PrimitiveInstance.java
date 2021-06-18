package Model.physics;

import java.awt.geom.AffineTransform;

import Model.physics.primitives.Primitive;

public class PrimitiveInstance {

	Primitive prim;
	AffineTransform T;
	
	public PrimitiveInstance(Primitive p, AffineTransform T) {
		prim = p;
		this.T = T;
	}
	
	public Primitive get_prim() {
		return prim;
	}
	
	public AffineTransform get_instance() {
		return T;
	}
	
}
