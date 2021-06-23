package View;

import java.util.ArrayList;
import java.util.Map.Entry;

import Model.World;
import Model.automata.actions.EnumAction;
import Model.entities.Entity;
import Utils.SafeMapElement;

public class Season {

	Ground ground;
	World w;

	public Season(Ground g, World w) {
		ground = g;
		this.w = w;
	}

	public void nextSeason() throws Exception {
		ground.step();
		for (Entry<Long, SafeMapElement> entries : w.getEntities()) {
			Entity et = (Entity) entries.getValue();
			Avatar avatar = et.getAvatar();
			if (et.team == 3) {
				et.getAvatar().step();
				
			}
		}
	}

}
