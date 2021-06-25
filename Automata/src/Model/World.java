package Model;

import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.Map.Entry;

import Controller.VirtualInput;
import Model.automata.creation.KeyExtension;
import Model.entities.Entity;
import Model.entities.Player;
import Model.entities.enemies.Tank;
import Model.loader.TemplatesLoader;
import Model.map.Map;
import Model.physics.Newton;
import Utils.SafeMap;
import Utils.SafeMapElement;
import View.Avatar;
import View.Template;

public class World {

	private SafeMap entities;

	private Entity player;
	private VirtualInput inputs;
	private long elapsed;
	private Newton newton;
	private Map map;

	public World(VirtualInput vi) {
		inputs = vi;
		entities = new SafeMap();
		elapsed = 0;
		newton = new Newton();

	}

	public void tick(long elapsed) {
		this.elapsed = elapsed;

		entities.update();
		newton.update();

		for (Entry<Long, SafeMapElement> e : entities) {
			((Entity) e.getValue()).step();
		}
		newton.tick(elapsed);
		if (map != null)
			try {
				map.tick(elapsed);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	}

	public SafeMap getEntities() {
		return entities;
	}

	public long getElapsed() {
		return elapsed;
	}

	public boolean getKey(KeyExtension k) {
		return inputs.getKey(k);
	}

	public void addEntity(Entity entity) {

		long id = entities.add(entity);
		entity.setID(id);
		entity.setWorld(this);
		newton.add(entity.getBody());
	}

	public void removeEntity(long id) {
		Entity pb = ((Entity) entities.get(id));
		newton.remove(pb.getBody());
		entities.remove(id);
	}

	public void setPlayer(Entity p) {
		player = p;
	}

	public Entity getPlayer() {
		return player;
	}

	public void setMap(Map m) {
		map = m;
	}

	public VirtualInput getInputs() {
		return inputs;
	}

	public void setInputs(VirtualInput inputs) {
		this.inputs = inputs;
	}

	public void generationDone() throws IOException {
		Player player = new Player();
		Template tmp = TemplatesLoader.get("Player");
		new Avatar(player, tmp);
		this.addEntity(player);
		this.setPlayer(player);
		
		Tank tank = new Tank("Tank");
		Template tmpTank = TemplatesLoader.get("Tank");
		new Avatar(tank, tmpTank);
		tank.getTransform().concatenate(AffineTransform.getTranslateInstance(0, 100));
		this.addEntity(tank);
		

	}
}
