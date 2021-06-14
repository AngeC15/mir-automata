package View;

import java.io.IOException;

public class Template {
	private SpriteSheet spriteSheet;
	private AutoAnim automata;
	private AnimNode startNode;
	
	Template(String fileNameSpriteSheet, String fileNameAutomata) throws IOException{
		spriteSheet = new SpriteSheet("/Resources/winchester-4x6.png",4,6,24);
		//automata = new AutoAnim();
		startNode = new AnimNode(spriteSheet.getSprite(0));
	}
	
	
	
}
