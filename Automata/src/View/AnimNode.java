package View;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Model.automata.actions.Enum_Action;

public class AnimNode {

	private BufferedImage sprite;
	private int index;
	private ArrayList<Enum_Action> actions;
	private ArrayList<AnimNode> nextNodes;

	public AnimNode(BufferedImage sprite, int index) {
		this.sprite = sprite;
		actions = new ArrayList<Enum_Action>();
		nextNodes = new ArrayList<AnimNode>();
		this.index = index;
	}

	public void addNode(String condition, AnimNode node) {
		Enum_Action cond = Enum_Action.valueOf(condition);
		actions.add(cond);
		nextNodes.add(node);

	}

	public int getIndex() {
		return this.index;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public AnimNode nextNode(Enum_Action condition) {
		for (int i = 0; i < actions.size(); i++) {
			if (actions.get(i) == condition) {
				return nextNodes.get(i);
			}
		}
		// throw new Exception("State non valid");
		// Pas de changement de comportement si l'action demandée n'est pas pertinente

		return this;

	}

	@Override
	public String toString() {
		String chaine = "Etat " + index + " : \n";
		for (int i = 0; i < actions.size(); i++) {
			chaine += " " + actions.get(i) + " -> " + nextNodes.get(i).getIndex() + "\n";
		}
		return chaine;

	}
}
