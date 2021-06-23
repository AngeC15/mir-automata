package Tests;

import java.io.IOException;

import Controller.VirtualInput;
import Model.World;
import Model.loader.AutomataLoader;
import Model.loader.ChangeAutomata;
import Model.entities.Player;
import Model.entities.Wall;
import Model.loader.TemplatesLoader;
import Model.map.Map;
import Model.automata.Automaton;

public class Test_changeAutomata {

	public static void main(String[] args) throws IOException {
		AutomataLoader.load_all("Bots/loader.txt", "Bots/entityAutomata.txt");
		Automaton automata = AutomataLoader.get("Player");
		System.out.println(automata.getName());

	
		

	}

}
