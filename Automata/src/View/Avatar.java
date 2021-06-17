package View;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import Model.automata.actions.EnumAction;
import Model.entities.Entity;

/**
 * @author Camille, Gergely, Samuel
 *
 * 
 */
public class Avatar {
	private static final AffineTransform identity = new AffineTransform();
	private AnimNode state;
	Entity entity;
	AffineTransform transform;
	EnumAction currentAction;
	// ms
	int compteur;

	public Avatar(Entity e, Template tmp) throws IOException {
		state = tmp.getDefaultNode();
		entity = e;
		transform = e.getTransform();
		e.setAvatar(this);
		compteur = 0;
		currentAction = tmp.getDefaultAction();
	}

	void paint(Graphics2D g) {
		BufferedImage sprite = state.getSprite();
		g.drawRenderedImage(sprite, identity);
	}
	
	/**
	 * Checks if the avatar must change its animation, 
	 */
	public void step() {
		
	}

	public void changeCurrentAction(EnumAction action) {
		state = state.nextNode(action);

	}
}