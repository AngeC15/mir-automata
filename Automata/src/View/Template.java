package View;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;

import Model.automata.actions.EnumAction;
import View.Season.EnumSeason;

/**
 * @author Gergely, Camille, Samuel
 * 
 *         Specifies the animation for a single time of entity. Can be shared by
 *         multiple avatars.
 *
 */
public class Template {
	private SpriteSheet spriteSheet;
	private LinkedHashMap<EnumAction, AnimNode> allNodes;
	protected EnumSeason season;
	private double scale;

	/**
	 * Creates a template with an associated sprite sheet and an animation automata.
	 * 
	 * @param fileNameSpriteSheet
	 * @param fileNameAutomata
	 * @param rows
	 * @param lines
	 * @param totalSprites
	 * @param scale
	 * @throws Exception 
	 */
	public Template(String fileNameSpriteSheet, String fileNameAutomata, int rows, int lines, int totalSprites,
			double scale) throws Exception {
		this.scale = scale;
		spriteSheet = new SpriteSheet(fileNameSpriteSheet, rows, lines, totalSprites);
		allNodes = new LinkedHashMap<EnumAction, AnimNode>();
		this.season = EnumSeason.SUMMER;
		readFile(fileNameAutomata);

	}

	/**
	 * Creates a template with an associated sprite sheet and an animation automata.
	 * 
	 * @param fileNameSpriteSheet
	 * @param fileNameAutomata
	 * @throws Exception 
	 */
	public Template(EnumSeason season, String fileNameSpriteSheet, String fileNameAutomata, int rows, int lines,
			int totalSprites, double scale) throws Exception {
		spriteSheet = new SpriteSheet(fileNameSpriteSheet, rows, lines, totalSprites);
		allNodes = new LinkedHashMap<EnumAction, AnimNode>();
		this.season = season;
		this.scale = scale;
		readFile(fileNameAutomata);
	}

	/**
	 * Read the file at the given file name and constructs the animation automaton .
	 * The automaton is given in this way:
	 * 
	 * <pre>
	 * {@code MOVE 3000 4 2 7 1}
	 * </pre>
	 * 
	 * MOVE being the action from EnumAction 3000 being the total time the animation
	 * will take 4, 2, 7 and 1 being the states the animation will follow
	 * 
	 * 
	 * @param fileNameAutomata
	 * @throws Exception 
	 */
	private void readFile(String fileNameAutomata) throws Exception {
		File file = new File(fileNameAutomata);
		if (file.exists() && file != null) {
				Scanner myReader = new Scanner(file);
				while (myReader.hasNextLine()) {
					String data = myReader.nextLine();
					String[] line = data.split(" ");

					if (line.length < 3) {
						myReader.close();
						throw new Exception("Too few arguments.");
					}

					EnumAction action = EnumAction.valueOf(line[0]); // Recovers the actual action
					int time = Integer.parseInt(line[1]);
					int dividedTime = time / (line.length - 3);
					AnimInterrupt inter = AnimInterrupt.valueOf(line[2]);
					BufferedImage scaledImage = resize(spriteSheet.getSprite(Integer.parseInt(line[3])));
					AnimNode node = new AnimNode(scaledImage, dividedTime, action, inter);
					AnimNode tempNode = node;
					for (int i = 4; i < line.length; i++) {
						scaledImage = resize(spriteSheet.getSprite(Integer.parseInt(line[i])));
						AnimNode nextNode = new AnimNode(scaledImage, dividedTime, action, inter);
						tempNode.addNode(nextNode);
						tempNode = nextNode;
					}

					// Now, let's store that to the HashMap
					allNodes.put(action, node);
				}
				myReader.close();

		} else {
			throw new Exception("The file " + fileNameAutomata + "was not found");
		}

	}

	/**
	 * Checks what node have the most priority out of the given newAction array. If
	 * none of the elements of newAction have the most priority than currentAction,
	 * currentAction will be returned instead.
	 * 
	 * @param currentAction
	 * @param newAction
	 * @return currentAction or an element from newAction
	 * @throws Exception
	 */
	public AnimNode changeAnimationSequence(EnumAction currentAction, ArrayList<EnumAction> newAction)
			throws Exception {
		// Il y a une boucle imbriquée (minimale), la supprimer permetterait de gagner
		// en
		// performance. Bonne chance.
		Set<EnumAction> set = allNodes.keySet();
		Iterator<EnumAction> iterator = set.iterator();
		EnumAction compareAction;
		while (iterator.hasNext()) {
			compareAction = iterator.next();
			if (compareAction == currentAction)
				return null;
			for (EnumAction action : newAction)
				if (compareAction == action)
					return allNodes.get(action);
		}
		throw new Exception("Current action not found. Should not happen.");
	}

	/**
	 * Resizes the image with the template's scale variable
	 * 
	 * @param imgToScale
	 * @return the resized image
	 */
	public BufferedImage resize(BufferedImage imgToScale) {
		int w = imgToScale.getWidth();
		int h = imgToScale.getHeight();
		BufferedImage after = new BufferedImage((int) (w * scale), (int) (h * scale), BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		at.scale(scale, scale);
		AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		return scaleOp.filter(imgToScale, after);
	}

	/**
	 * @return the node with the lesser priority
	 */
	public AnimNode getDefaultNode() {
		Object[] returnNode = allNodes.values().toArray();
		return (AnimNode) returnNode[returnNode.length - 1];
	}

	/**
	 * @return the action with the lesser priority
	 */
	public EnumAction getDefaultAction() {
		Object[] returnAction = allNodes.keySet().toArray();
		return (EnumAction) returnAction[returnAction.length - 1];
	}

}
