package View;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AnimNode {

	public enum Condition {  // dans le fichier Civilite.java  
	    MOVE, JUMP, MONSIEUR  
	}
	
	
	private BufferedImage sprite;
	private ArrayList<Condition> conditions;
	private ArrayList<AnimNode> nextNodes;

	public AnimNode(BufferedImage sprite) {
		this.sprite = sprite;
		conditions = new ArrayList<Condition>();
		nextNodes = new ArrayList<AnimNode>();
	}
}
