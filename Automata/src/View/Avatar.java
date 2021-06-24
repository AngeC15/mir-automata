package View;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import Model.automata.actions.EnumAction;
import Model.entities.Entity;

/**
 * @author Camille, Gergely, Samuel
 *
 *         The graphical representation of an Entity. This is where the Entity
 *         paint method is.
 */
public class Avatar {
	private static final AffineTransform identity = new AffineTransform();
	private AnimNode state;
	Entity entity;
	AffineTransform transform;
	EnumAction currentAction;
	// Time in ms.
	long compteur;
	private Template template;

	/**
	 * Creates a new Avatar linked to a Template and to an unique Entity
	 * 
	 * @param e   Entity
	 * @param tmp Template
	 * @throws IOException
	 */
	public Avatar(Entity e, Template tmp) throws IOException {
		state = tmp.getDefaultNode();
		entity = e;
		transform = e.getTransform();
		e.setAvatar(this);
		compteur = System.currentTimeMillis();
		currentAction = tmp.getDefaultAction();
		template = tmp;

	}

	/**
	 * Paints the avatar to the screen using the informations from the entity and
	 * the current animation.
	 * 
	 * @param g
	 */
	void paint(Graphics2D g) {
		if (System.currentTimeMillis() - compteur >= state.getTime())
			try {
				step();
				compteur = System.currentTimeMillis();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		BufferedImage sprite = state.getSprite();
		g.drawRenderedImage(sprite, identity);

	}

	/**
	 * Checks if the avatar must change its animation. If it does, the avatar will
	 * change nodes based on the priority.
	 * 
	 * @throws Exception
	 */
	public void step() throws Exception {
		ArrayList<EnumAction> actions = entity.getActions();
		if (actions.size() == 0 && state.isInterruptable())
			return;

		AnimNode node = template.changeAnimationSequence(currentAction, actions);
		if (node == null) {
			if (state.nextNode() == null) {
				// When the current animation is finished, the avatar will take back its default
				// behaviour.
				state = template.getDefaultNode();
				currentAction = state.getAction();
			} else {
				state = state.nextNode();
			}
		} else {
			currentAction = node.getAction();
			state = node;
		}
	}

	public void setTemplate(Template tmp) {
		currentAction = tmp.getDefaultAction();
		template = tmp;
		state = tmp.getDefaultNode();
	}

	public int getSpriteW() {
		return state.getSprite().getWidth();
	}

	public int getSpriteH() {
		return state.getSprite().getHeight();
	}

	public BufferedImage getDefaultSprite() {
		return template.getDefaultNode().getSprite();
	}
}
