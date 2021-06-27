package Model;

import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.Map.Entry;

import Controller.VirtualInput;
import Model.automata.creation.KeyExtension;
import Model.entities.Entity;
import Model.entities.Player;
import Model.entities.enemies.Enemy;
import Model.entities.enemies.Tank;
import Model.loader.TemplatesLoader;
import Model.map.Map;
import Model.monster_generator.Generator;
import Model.physics.Newton;
import Utils.SafeMap;
import Utils.SafeMapElement;
import View.Avatar;
import View.Template;
import Model.Level;

public class World {

	private SafeMap entities;

	private Entity player;
	private VirtualInput inputs;
	private long elapsed;
	private Newton newton;
	private Map map;
	public Level niveau;
	public boolean playerDead;
	public int deathEnnemies;

	private float block_size;
	private float game_w;
	private float game_h;
	private static final AffineTransform identity = new AffineTransform();

	public World(VirtualInput vi, float block_size, float game_w, float game_h) {
		deathEnnemies = 0;
		inputs = vi;
		entities = new SafeMap();
		elapsed = 0;
		this.niveau = null;
		playerDead = false;

		newton = new Newton(block_size);
		this.block_size = block_size;
		this.game_w = game_w;
		this.game_h = game_h;

	}

	public void tick(long elapsed) {
		this.elapsed = elapsed;

		for (Entry<Long, SafeMapElement> e : entities) {
			Entity en = (Entity) e.getValue();

			AffineTransform player_tranform;
			if (player == null)
				player_tranform = identity;
			else
				player_tranform = player.getTransform();

			double fX = (player_tranform.getTranslateX() - en.getTransform().getTranslateX()) * 2;
			double fY = (player_tranform.getTranslateY() - en.getTransform().getTranslateY()) * 2;

			double dX = Math.abs(fX) - block_size;
			double dY = Math.abs(fY) - block_size;

			if (dX > game_w) {
				AffineTransform t;
				if (fX < 0.0f)
					t = AffineTransform.getTranslateInstance(-game_w - block_size, 0);
				else
					t = AffineTransform.getTranslateInstance(game_w + block_size, 0);
				t.concatenate(en.getTransform());
				en.getBody().setTransform(t);
			}
			if (dY > game_h) {
				AffineTransform t;
				if (fY < 0.0f)
					t = AffineTransform.getTranslateInstance(0, -game_h - block_size);
				else
					t = AffineTransform.getTranslateInstance(0, game_h + block_size);

				t.concatenate(en.getTransform());
				en.getBody().setTransform(t);
			}
		}
		for (Entry<Long, SafeMapElement> e : entities) {
			Entity en = (Entity) e.getValue();
			en.step();

		}
		entities.update();
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

	public float getBlockSize() {
		return block_size;
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
		if (pb instanceof Player) {
			this.playerDead = true;
		} else if (pb instanceof Enemy) {
			deathEnnemies++;
		}
		if (pb != null) {
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

		// uncomment if you want enemies
		/*
		 * Tank tank = new Tank("Tank"); Template tmpTank = TemplatesLoader.get("Tank");
		 * new Avatar(tank, tmpTank);
		 * tank.getTransform().concatenate(AffineTransform.getTranslateInstance(0,
		 * 100)); this.addEntity(tank);
		 */
		/*
		 * 
		 * new Avatar(tank, tmpTank);
		 * tank.getTransform().concatenate(AffineTransform.getTranslateInstance(0,
		 * 100)); this.addEntity(tank);
		 */

		Generator g = new Generator(this, 100, 1);
		// g.spawn_cover();
		niveau = new Level(2000, 0, this, g);

		/*
		 * Mecha mecha = new Mecha("Mecha"); Template tmpMecha =
		 * TemplatesLoader.get("Mecha"); new Avatar(mecha, tmpMecha);
		 * mecha.getTransform().concatenate(AffineTransform.getTranslateInstance(0,
		 * 40)); this.addEntity(mecha);
		 */
		/*
		 * Flamethrower flamethrower = new Flamethrower("Flamethrower"); Template
		 * tmpFlamethrower = TemplatesLoader.get("Flamethrower"); new
		 * Avatar(flamethrower, tmpFlamethrower);
		 * flamethrower.getTransform().concatenate(AffineTransform.getTranslateInstance(
		 * 0, -100)); this.addEntity(flamethrower);
		 * 
		 * Plane plane = new Plane("Plane"); Template tmpPlane =
		 * TemplatesLoader.get("Plane"); new Avatar(plane, tmpPlane);
		 * plane.getTransform().concatenate(AffineTransform.getTranslateInstance(0,
		 * 20)); this.addEntity(plane);
		 */

	}

	public void tryLevel() throws IOException {
		if (niveau != null) {
			niveau.generate();
		}
	}

	public double getGame_w() {
		// TODO Auto-generated method stub
		return this.game_w;
	}

	public double getGame_h() {
		// TODO Auto-generated method stub
		return this.game_h;
	}

}
