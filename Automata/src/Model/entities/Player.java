package Model.entities;

import Model.World;
import Model.loader.AutomataLoader;

public class Player extends Entity{

	public Player(World w) {
		super(AutomataLoader.get("Player"), w);
	}

}
