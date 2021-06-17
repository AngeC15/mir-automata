package View;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Model.automata.actions.EnumAction;

public class AnimNode {

	private BufferedImage sprite;
	private AnimNode nextNode;
	private int time;

	public AnimNode(BufferedImage sprite, int time) {
		this.sprite = sprite;
		this.time = time;
	}

	public void addNode(AnimNode node) {
		nextNode = node;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public AnimNode nextNode(EnumAction condition) {
		/* TODO
		 * for (int i = 0; i < actions.size(); i++) {
			if (actions.get(i) == condition) {
				return nextNode.get(i);
			}
		}*/
		// throw new Exception("State non valid");
		// Pas de changement de comportement si l'action demand�e n'est pas pertinente

		return this;

	}
}
