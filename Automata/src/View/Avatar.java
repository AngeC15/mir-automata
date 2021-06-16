package View;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import Model.automata.actions.Enum_Action;
import Model.entities.Entity;

public class Avatar {
	private static final AffineTransform identity = new AffineTransform();
	private AnimNode state;
	Entity entity;
	AffineTransform transform;

	int compteur;

	public Avatar(Entity e, Template tmp) throws IOException {
		state = tmp.getStartNode();
		entity = e;
		transform = e.getTransform();
		e.setAvatar(this);
		compteur = 0;
	}

	void paint(Graphics2D g) {
		if (compteur == 3) { // limite la vitesse des mouvements
			this.step();
			compteur = 0;
		}
		compteur++;
		BufferedImage sprite = state.getSprite();
		g.drawRenderedImage(sprite, identity);
	}

	public void step(Enum_Action action) {
		//Enum_Action action = entity.getAction();
		state = state.nextNode(action);
		

	}
}
