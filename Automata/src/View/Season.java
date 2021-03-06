package View;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Map.Entry;

import Model.World;
import Model.entities.Decor;
import Model.entities.Entity;
import Model.entities.Player;
import Model.loader.TemplatesLoader;
import Utils.RandomUtil;
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
	 * Changes the current season by changing the templates associated with the
	 * entities.
	 * 
	 * @throws Exception
	 */
	public void nextSeason() throws Exception {
		EnumSeason[] tabSeason = EnumSeason.values();
		int index = Math.floorMod(current.ordinal() + 1, tabSeason.length);
		current = tabSeason[index];
		groundTemplate = TemplatesLoader.get("Ground", current);
		for (Entry<Long, SafeMapElement> entries : w.getEntities()) {
			Entity et = (Entity) entries.getValue();
			Avatar avatar = et.getAvatar();

			if (et instanceof Player && current == EnumSeason.WINTER) {
				Player p = (Player) et;
				p.getBody().setFriction(1); // normal 15
			} else if (et instanceof Player) {
				Player p = (Player) et;
				p.getBody().setFriction(15); // normal 15
				p.getBody().setmaxSpeed(40); // normal 40
			}
			if (et instanceof Decor && ((Decor) et).toString() != "Wall") {
				avatar.setTemplate(TemplatesLoader.get(et.toString(), current));
			}
		}

	}

	public EnumSeason getCurrentSeason() {
		return current;
	}

	/**
	 * Make a transition from summer to winter by simulating snow and changing the
	 * scenery.
	 * 
	 * @param g
	 * @param intensitySnow - the maxiumum intensity of the snow
	 * @param cmpIntensity  - a counter to increase the intensity of the snow
	 * @return the value of the counter (need to be place on the cmpIntensity
	 *         attribute )
	 */
	public int transitionSummerWinter(Graphics2D g, int intensitySnow, int cmpIntensity) {
		int playerPosX = (int) w.getPlayer().getTransform().getTranslateX();
		int playerPosY = (int) w.getPlayer().getTransform().getTranslateY();
		if (cmpIntensity < intensitySnow && current == EnumSeason.SUMMER) {
			cmpIntensity += 5;
			g.setColor(Color.white);
			for (int i = 0; i < intensitySnow; i++) {
				int size = RandomUtil.genererInt(0, 3);

				g.fillOval(RandomUtil.genererInt(playerPosX - 100, playerPosX + 100),
						RandomUtil.genererInt(playerPosY - 100, playerPosY + 100), size, size);
			}
		} else if (cmpIntensity > 0 && current == EnumSeason.WINTER) {
			cmpIntensity -= 8;
			g.setColor(new Color(230, 230, 230));
			for (int i = 0; i < intensitySnow; i++) {
				int size = RandomUtil.genererInt(0, 3);
				g.fillOval(RandomUtil.genererInt(playerPosX - 100, playerPosX + 100),
						RandomUtil.genererInt(playerPosY - 100, playerPosY + 100), size, size);
			}
		} else if ((cmpIntensity >= intensitySnow && current == EnumSeason.SUMMER) || (current != EnumSeason.SUMMER)) {
			try {
				this.nextSeason();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			intensitySnow = 0;
		}
		return cmpIntensity;
	}

}
