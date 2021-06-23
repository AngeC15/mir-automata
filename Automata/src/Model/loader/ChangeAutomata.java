package Model.loader;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

import Model.automata.Automaton;

/**
 * This class allows an entity to be assigned an automaton
 * 
 * @author Camille
 *
 */
public class ChangeAutomata {

	private HashMap<String, Automaton> entityAutomaton;

	/**
	 * Constructor of the class.
	 * 
	 * @param fileEntityAutomata - File in which an entity is assigned an automaton.
	 * 
	 */
	public ChangeAutomata(String fileEntityAutomata) {
		this.entityAutomaton = new HashMap<String, Automaton>();
		readFile(fileEntityAutomata);
	}

	/**
	 * Reads the file passed in parameters. Assigns to each entity its automaton in
	 * the following way : <br/>
	 * <code>NomEntite NomAutomate</code><br/>
	 * <code>NomEntite2 NomAutomate2</code>
	 * 
	 * @param fileNameAutomata filepath to the file
	 */
	private void readFile(String fileNameAutomata) {
		File file = new File(fileNameAutomata);
		try {
			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] line = data.split(" ");

				if (line.length > 2) {
					myReader.close();
					throw new Exception("Too much arguments.");
				}
				if (line.length > 0) {
					String readEntity = line[0];
					String readAutomata = line[1];
					Automaton aut = AutomataLoader.getAutomaton(line[1]);
					if (aut != null) {
						entityAutomaton.put(line[0], aut);
					} else {
						System.err.println("The automaton " + line[1] + "does not exist");
					}
				}

			}
			myReader.close();
		} catch (Exception e) {
			System.err.println("An error occurred.");
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves the automaton associated with the name
	 * 
	 * @param name Name of the entity from which we wish to retrieve the automaton
	 * @return the automaton
	 */
	public Automaton getAutomaton(String name) {
		return entityAutomaton.get(name);

	}
}
