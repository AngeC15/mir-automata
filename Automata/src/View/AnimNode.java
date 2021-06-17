package View;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Model.automata.actions.EnumAction;

public class AnimNode {

	private BufferedImage sprite;
	private AnimNode nextNode;
	private int time;
	private EnumAction action;

	public AnimNode(BufferedImage sprite, int time, EnumAction action) {
		this.sprite = sprite;
		this.time = time;
		this.action = action;
	}

	public void addNode(AnimNode node) {
		nextNode = node;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public AnimNode nextNode() {
		return nextNode;
	}

	public EnumAction getAction() {
		return action;
	}

	public int getTime() {
		return time;
	}
}
