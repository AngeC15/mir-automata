package Model;

import java.util.ArrayDeque;
import java.util.Map.Entry;
import java.util.Queue;

import Model.automata.creation.KeyExtension;
import Model.entities.Entity;
import Model.physics.Newton;

import java.util.TreeMap;

import Controller.VirtualInput;

public class World {
	private TreeMap<Long, Entity> entities;
	private ArrayDeque<Entity> entityQueue;
	private Entity player;
	private long nextIntanceIdx;
	private VirtualInput inputs;
	private long elapsed;
	private Newton newton;
	
	public World(VirtualInput vi) {
		inputs = vi;
		entities = new TreeMap<Long, Entity>();
		nextIntanceIdx = 0;
		elapsed = 0;
		newton = new Newton();
		entityQueue = new ArrayDeque<Entity>();
	}
	
	public void tick(long elapsed) {
		this.elapsed = elapsed;
		addEntityInternal();
	
		for(Entry<Long, Entity> entries : entities.entrySet()) {
			entries.getValue().step();
		}
		newton.tick(elapsed);
	}
	public TreeMap<Long, Entity> getEntities(){
		return entities;
	}
	public long getElapsed() {
		return elapsed;
	}
	public boolean getKey(KeyExtension k) {
		return inputs.getKey(k);
	}
	public void addEntity(Entity entity) {
		entityQueue.push(entity);
	}
	private void addEntityInternal() {
		long id;
		while(entityQueue.size() > 0) {
			id = nextIntanceIdx++;
			Entity entity = entityQueue.pop();
			entity.setWorld(this);
			entity.setID(id);
			entities.put(id, entity);
			newton.add(entity.getBody());
		}
	}
	public void setPlayer(Entity p) {
		player = p;
	}
	public Entity getPlayer() {
		return player;
	}

	public VirtualInput getInputs() {
		return inputs;
	}

	public void setInputs(VirtualInput inputs) {
		this.inputs = inputs;
	}
	
}
