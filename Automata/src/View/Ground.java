package View;

import java.awt.image.BufferedImage;

import Model.loader.TemplatesLoader;

public class Ground {
	Template template;
	private AnimNode state;
	
	public Ground() {
		this.template = TemplatesLoader.get("Ground");
		state = this.template.getDefaultNode();
	}
	
	public void step() {
		state = state.nextNode();
	}
	
	public BufferedImage getSprite() {
		return state.getSprite();
	}
	
}
