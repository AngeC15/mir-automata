package View;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map.Entry;

import Model.World;
import Model.automata.actions.EnumAction;
import Model.entities.Entity;
import Model.loader.TemplatesLoader;
import Utils.SafeMapElement;

public class Season {
	public enum EnumSeason {
		SUMMER, WINTER
	}

	private Template groundTemplate;
	private World w;
	private EnumSeason current;

	public Season(World w) {
		this.w = w;
		current = EnumSeason.SUMMER;
		groundTemplate = TemplatesLoader.get("Ground");
	}
	
	public BufferedImage getGround() {
		return groundTemplate.getDefaultNode().getSprite();
	}

	/**
	 * Changes the current season by changing the templates associated with the entities.
	 * @throws Exception
	 */
	public void nextSeason() throws Exception {
		EnumSeason[] tabSeason = EnumSeason.values();
		if (current.ordinal() + 1 < tabSeason.length) {
			current = tabSeason[current.ordinal() + 1];
		}
		groundTemplate = TemplatesLoader.get("Ground", current);
		for (Entry<Long, SafeMapElement> entries : w.getEntities()) {
			Entity et = (Entity) entries.getValue();
			Avatar avatar = et.getAvatar();
			avatar.template = TemplatesLoader.get(et.toString(), current);

		}
	}

}
