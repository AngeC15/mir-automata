package View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Template {
	private SpriteSheet spriteSheet;
	private AnimNode startNode;
	private ArrayList<AnimNode> allNodes;

	public Template(String fileNameSpriteSheet, String fileNameAutomata) throws IOException {
		spriteSheet = new SpriteSheet(fileNameSpriteSheet, 4, 6, 24);
		allNodes = new ArrayList<AnimNode>();
		readFile(fileNameAutomata);
		startNode = allNodes.get(0);
	}

	/**
	 * Read the file at the given file name and constructs the animation automaton .
	 * The automaton is given in this way :
	 * 
	 * <pre>
	 * {@code 0 MOVE 3 JUMP 2}
	 * </pre>
	 * 
	 * 0 is the index of the actual state, MOVE is the condition to go on the state
	 * 3, JUMP is the condition to go on the state 2
	 * 
	 * @param fileNameAutomata
	 */
	private void readFile(String fileNameAutomata) {
		File file = new File(fileNameAutomata);
		try {
			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] line = data.split(" ");

				int index = Integer.parseInt(line[0]); // Recover the actual state
				AnimNode node = containsNode(index);
				if (node == null) {
					node = new AnimNode(spriteSheet.getSprite(index), index);
					allNodes.add(node);
				}
				for (int i = 1; i + 1 < line.length; i += 2) {
					String condition = line[i]; // Recover the condition to change state
					index = Integer.parseInt(line[i + 1]); // Recover the index of the next state

					AnimNode nextNode = containsNode(index);
					if (nextNode == null) {
						nextNode = new AnimNode(spriteSheet.getSprite(index), index);
						allNodes.add(nextNode); // add the node to the global list of nodes
					}
					node.addNode(condition, nextNode); // add the node to the list of the next nodes

				}
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	/***
	 * Verify if an index exist in the list of nodes
	 * 
	 * @param index index of the sprite
	 * @return the node if find , null otherwise
	 */
	private AnimNode containsNode(int index) {
		for (AnimNode node : allNodes) {
			if (node.getIndex() == index) {
				return node;
			}
		}
		return null;
	}
	
	public void displayAllNodes() {
		for(AnimNode node : allNodes) {
			System.out.println(node);
		}
	}

}
