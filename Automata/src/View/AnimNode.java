package View;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import View.AnimNode.Condition;

public class AnimNode {

	public enum Condition {
		MOVE, FIRE, HURT, POP, WIZZ
	}

	private BufferedImage sprite;
	private int index;
	private ArrayList<Condition> conditions;
	private ArrayList<AnimNode> nextNodes;

	public AnimNode(BufferedImage sprite, int index) {
		this.sprite = sprite;
		conditions = new ArrayList<Condition>();
		nextNodes = new ArrayList<AnimNode>();
		this.index = index;
	}

	public void addNode(String condition, AnimNode node) {
		Condition cond = Condition.valueOf(condition);
		conditions.add(cond);
		nextNodes.add(node);

	}

	public int getIndex() {
		return this.index;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public AnimNode nextNode(Condition condition) throws Exception {
		for (int i = 0; i < conditions.size(); i++) {
			if (conditions.get(i) == condition) {
				return nextNodes.get(i);
			}
		}
		throw new Exception("State non valid");
	}

	@Override
	public String toString() {
		String chaine = "Etat " + index + " : \n";
		for (int i = 0; i < conditions.size(); i++) {
			chaine += " " + conditions.get(i) + " -> " + nextNodes.get(i).getIndex() + "\n";
		}
		return chaine;

	}
}
