package View;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import Model.automata.actions.EnumAction;

public class Template {
	private SpriteSheet spriteSheet;
	private HashMap<EnumAction, AnimNode> allNodes;

	public Template(String fileNameSpriteSheet, String fileNameAutomata) throws IOException {
		spriteSheet = new SpriteSheet(fileNameSpriteSheet, 4, 6, 24);
		allNodes = new HashMap<EnumAction, AnimNode>();
		readFile(fileNameAutomata);
	}

	/**
	 * Read the file at the given file name and constructs the animation automaton .
	 * The automaton is given in this way:
	 * 
	 * <pre>
	 * {@code 0 MOVE 3 JUMP 2}
	 * </pre>
	 * 
	 * 
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

				if (line.length < 3) {
					myReader.close();
					throw new Exception("Too few arguments.");
				}

				EnumAction action = EnumAction.valueOf(line[0]); // Recovers the actual action
				int time = Integer.parseInt(line[1]);
				int dividedTime = time / line.length - 2;
				AnimNode node = new AnimNode(spriteSheet.getSprite(Integer.parseInt(line[2])), dividedTime);
				AnimNode tempNode = node;
				for (int i = 3; i < line.length; i++) {
					AnimNode nextNode = new AnimNode(spriteSheet.getSprite(Integer.parseInt(line[i])), dividedTime);
					tempNode.addNode(nextNode);
					tempNode = nextNode;
				}

				// Now, let's store that to the HashMap
				allNodes.put(action, node);
			}
			myReader.close();
		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public AnimNode getStartNode(EnumAction currentAction, EnumAction newAction) throws Exception {
		Set<EnumAction> set = allNodes.keySet();
		Iterator<EnumAction> iterator = set.iterator();
		while(iterator.hasNext())
		{
			EnumAction toCompare = iterator.next();
			if(toCompare == currentAction)
				// Reversed priority, first is last
				return allNodes.get(newAction);
			if(toCompare == newAction)
				return null;
		}
		throw new Exception("Haven't found current action");
	}

}
