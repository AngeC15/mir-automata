package View.Avatar;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Utils.Vector2;
import View.AnimNode;
import View.AnimNode.Condition;
import View.Template;

public class Avatar {
	private AnimNode state;
	AffineTransform af;

	public Avatar(Template template, AffineTransform af) {
		state = template.getStartNode();
		this.af = af;
	}

	/**
	 * Draws this avatar on the given Graphics.
	 * AffineTransform af must be modified before calling this method.
	 * @param g
	 */
	public void paint(Graphics g) {
		BufferedImage sprite = state.getSprite();
		
		((Graphics2D) g).drawImage(sprite, af, null);
	}
	
	public void step(Condition condition) throws Exception {
		state = state.nextNode(condition);
	}

}
