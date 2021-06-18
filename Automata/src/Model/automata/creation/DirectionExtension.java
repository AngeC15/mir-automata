/**
 * 
 */
package Model.automata.creation;

/**
 * @author cyprien
 * https://gricad-gitlab.univ-grenoble-alpes.fr/PROJET_INFO3/projet/-/blob/master/gal/SEMANTIQUE.md
 */
public enum DirectionExtension {
	/**
	 * We're going to put here every possible direction (8 absolute + 4 relatives).
	 */
	F, R, B, L, N, NE, E, SE, S, SW, W, NW;
	
	public static DirectionExtension RelToAbsolute(DirectionExtension directionCourante, DirectionExtension directionAction) {
		//System.out.println("Direction courante = " + directionCourante + "directionAction :" + directionAction);
		if(directionAction == null) {
			return E;
		}
		if(directionCourante == null) {
			return directionAction;
		}
		switch(directionAction) {
			case F:
				return directionCourante;
			case R:
				switch(directionCourante) {
					case N:
						return E;
					case NE:
						return SE;
					case E:
						return S;
					case SE:
						return SW;
					case S:
						return W;
					case SW:
						return NW;
					case W:
						return N;
					case NW:
						return NE;
				default:
					System.out.println("Default R enum Direction");
					break;
				}
			case L:
				switch(directionCourante) {
					case N:
						return W;
					case NW:
						return SW;
					case W:
						return S;
					case SW:
						return SE;
					case S:
						return E;
					case SE:
						return NE;
					case E:
						return N;
					case NE:
						return NW;
				default:
					System.out.println("Default L enum Direction");
					break;
				}
			case B:
				switch(directionCourante) {
					case N:
						return S;
					case NE:
						return SW;
					case E:
						return W;
					case SE:
						return NW;
					case S:
						return N;
					case SW:
						return NE;
					case W:
						return E;
					case NW:
						return SE;
				default:
					System.out.println("Default B enum Direction");
					break;
				}
		default:
			break;
		
		}
		return directionAction;
		
	}
}
