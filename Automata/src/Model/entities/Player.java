package Model.entities;

import Model.World;

public class Player extends Entity{

	public Player(World w) {
		super(Tests.TestParseur.loadAutomata("Bots/Player.gal").get(0), w);
	}

}
