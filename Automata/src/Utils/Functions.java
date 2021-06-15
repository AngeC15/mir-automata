package Utils;

import Model.automata.creation.DirectionExtension;

public class Functions {
	
	private static final Vector2[] movIdx = {
			new Vector2(0,1),
			new Vector2(1,1),
			new Vector2(1,0),
			new Vector2(1,-1),
			new Vector2(0,-1),
			new Vector2(-1,-1),
			new Vector2(-1,0),
			new Vector2(-1,1)
		};
	
	public static Vector2 getAbsoluteDir(DirectionExtension dir) {
		return movIdx[dir.ordinal()-4].normalize();
	}
	
	public static Vector2 getRelativeDir(DirectionExtension dir, Vector2 relativeDir) {
		Vector2 vect = movIdx[dir.ordinal()*2+1];
		return new Vector2(vect.x * relativeDir.x,vect.y * relativeDir.y);
	}
}
