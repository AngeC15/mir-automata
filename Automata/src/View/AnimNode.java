package View;

import java.awt.image.BufferedImage;

import Model.automata.actions.EnumAction;

/**
 * @author Gergely, camille, Samuel
 *
 * Represents one step in an animation. Contains a sprite and the following step
 */
public class AnimNode {

	private BufferedImage sprite;
	private AnimNode nextNode;
	private int time;
	private EnumAction action;

	/**
	 * Creates a node with a sprite, a duration and a linked action
	 * 
	 * @param sprite
	 * @param time
	 * @param action
	 */
	public AnimNode(BufferedImage sprite, int time, EnumAction action) {
		this.sprite = sprite;
		this.time = time;
		this.action = action;
	}

	/**
	 * Gives to this node a following node
	 * 
	 * @param node
	 */
	public void addNode(AnimNode node) {
		nextNode = node;
	}

	/**
	 * @return the sprite linked to this node
	 */
	public BufferedImage getSprite() {
		return sprite;
	}

	/**
	 * @return the following node
	 */
	public AnimNode nextNode() {
		return nextNode;
	}

	/**
	 * @return the linked action of this node
	 */
	public EnumAction getAction() {
		return action;
	}

	/**
	 * @return the duration the avatar should show the sprite of this node
	 */
	public int getTime() {
		return time;
	}
}
