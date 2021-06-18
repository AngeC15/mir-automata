package Model.physics;

import java.util.ArrayList;

import Model.physics.primitives.Primitive;

//group of primitives
public class HitBox {
	
	ArrayList<PrimitiveInstance> shapes;
	
	public HitBox() {
		shapes = new ArrayList<PrimitiveInstance>();
	}
	
	public void add(PrimitiveInstance s) {
		shapes.add(s);
	}
	
	public PrimitiveInstance get(int k) {
		return shapes.get(k); 
	}
	
	
	
}
