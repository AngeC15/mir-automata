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
	private Template template;
	private int x;
	private int y;

	public Avatar(Template template, int x, int y) {
		this.template = template;
		state = template.getStartNode();
		this.x = x;
		this.y = y;
	}

	public void paint(Graphics g, int angleInDegrees , int width, int height) {
		BufferedImage sprite = state.getSprite();
		
		AffineTransform transform = new AffineTransform();
		transform.translate(x, y);
		transform.rotate(Math.toRadians(angleInDegrees));
		
		((Graphics2D) g).drawImage(sprite, transform, null) ;
	}

	public void moveAvatar(Vector2 movement) {
		x += movement.x;
		y += movement.y;
	}
	
	
	
	public void step(Condition condition) throws Exception {
		state = state.nextNode(condition);
	}

}
