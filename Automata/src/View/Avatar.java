package View;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.entities.Entity;
import View.AnimNode.Condition;

public class Avatar {
	private static final AffineTransform identity = new AffineTransform();
	private AnimNode state;
	Entity entity;
	AffineTransform transform;
	BufferedImage[] m_images;
	
	public Avatar(Entity e, Template tmp) throws IOException{
		state = tmp.getStartNode();
		entity = e;
		transform = e.getTransform();
		e.setAvatar(this);
	}
	void paint(Graphics2D g) {;
		BufferedImage sprite = state.getSprite();
		g.drawRenderedImage(sprite, identity);
	}
	public void step(Condition condition) throws Exception {
		state = state.nextNode(condition);
	}
	public int getSpriteW() {
		return state.getSprite().getWidth();
	}
	public int getSpriteH() {
		return state.getSprite().getHeight();
	}
}
