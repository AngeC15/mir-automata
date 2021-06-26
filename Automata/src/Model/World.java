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
	
	public Map getMap() {
		return map;
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

		// Uncomment if you want enemies
		Tank tank = new Tank("Tank");
		Template tmpTank = TemplatesLoader.get("Tank");
		new Avatar(tank, tmpTank);
		tank.getTransform().concatenate(AffineTransform.getTranslateInstance(0, 10));
		addEntity(tank);
		/*
		Tank tank2 = new Tank("Tank");
		Template tmpTank2 = TemplatesLoader.get("Tank");
		new Avatar(tank2, tmpTank2);
		tank2.getTransform().concatenate(AffineTransform.getTranslateInstance(100, 200));
		addEntity(tank2);

		Mecha mecha = new Mecha("Mecha"); Template tmpMecha =
		TemplatesLoader.get("Mecha"); new Avatar(mecha, tmpMecha);
		mecha.getTransform().concatenate(AffineTransform.getTranslateInstance(0,
		40)); world.addEntity(mecha);
		
		Flamethrower flamethrower = new Flamethrower("Flamethrower"); Template
		tmpFlamethrower = TemplatesLoader.get("Flamethrower"); new
		Avatar(flamethrower, tmpFlamethrower);
		flamethrower.getTransform().concatenate(AffineTransform.getTranslateInstance(
		0, -100)); world.addEntity(flamethrower);
		
		Plane plane = new Plane("Plane"); Template tmpPlane =
		TemplatesLoader.get("Plane"); new Avatar(plane, tmpPlane);
		plane.getTransform().concatenate(AffineTransform.getTranslateInstance(0,
		20)); world.addEntity(plane);
		*/
	}
}
