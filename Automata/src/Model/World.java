package Model;

import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.Map.Entry;

import Controller.VirtualInput;
import Model.automata.creation.KeyExtension;
import Model.entities.Entity;
import Model.entities.Player;
import Model.entities.enemies.Mecha;
import Model.entities.enemies.Tank;
import Model.loader.TemplatesLoader;
import Model.map.Map;
import Model.monster_generator.Generator;
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
	private Level niveau;
	public boolean playerDead;

	public World(VirtualInput vi) {
		inputs = vi;
		entities = new SafeMap();
		elapsed = 0;
		newton = new Newton();
		niveau = null;
		playerDead = false;

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
		if(pb instanceof Player) {
			this.playerDead = true;
		}
		if(pb != null) {
			newton.remove(pb.getBody());
			entities.remove(id);
		}
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
		
		  /*
		  
		  new Avatar(tank, tmpTank);
		  tank.getTransform().concatenate(AffineTransform.getTranslateInstance(0, 100)); 
		  this.addEntity(tank);
		   */
		

		  Generator g=new Generator(this, 100,1);
		  //g.spawn_cover();
		 niveau = new Level(100, 0, this, g);
		 
		  
		  /*
		  Mecha mecha = new Mecha("Mecha"); Template tmpMecha =
		  TemplatesLoader.get("Mecha"); new Avatar(mecha, tmpMecha);
		  mecha.getTransform().concatenate(AffineTransform.getTranslateInstance(0, 40)); 
		  this.addEntity(mecha);
		  */
		  /*
		  Flamethrower flamethrower = new Flamethrower("Flamethrower"); Template
		  tmpFlamethrower = TemplatesLoader.get("Flamethrower"); new
		  Avatar(flamethrower, tmpFlamethrower);
		  flamethrower.getTransform().concatenate(AffineTransform.getTranslateInstance(
		  0, -100)); this.addEntity(flamethrower);
		  
		  Plane plane = new Plane("Plane"); Template tmpPlane =
		  TemplatesLoader.get("Plane"); new Avatar(plane, tmpPlane);
		  plane.getTransform().concatenate(AffineTransform.getTranslateInstance(0,
		  20)); this.addEntity(plane);
		  */
		 
	}

	public void tryLevel() throws IOException {
		if(niveau != null) {
			niveau.generate();
		}
	}

}
