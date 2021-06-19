package Model;

import java.util.ArrayDeque;
import java.util.Map.Entry;
import java.util.Queue;

import Model.automata.creation.KeyExtension;
import Model.entities.Entity;
import Model.map.Map;
import Model.physics.Newton;

import java.util.TreeMap;

import Controller.VirtualInput;

public class World {
	private TreeMap<Long, Entity> entities;
	private ArrayDeque<Entity> addQueue;
	private ArrayDeque<Long> rmQueue;
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
		addQueue = new ArrayDeque<Entity>();
		rmQueue = new ArrayDeque<Long>();
	}
	
	public void tick(long elapsed) {
		this.elapsed = elapsed;
		manageEntity();
	
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
		addQueue.push(entity);
	}
	public void removeEntity(long id) {
		rmQueue.push(id);
	}
	private void manageEntity() {
		long id;
		while(addQueue.size() > 0) {
			id = nextIntanceIdx++;
			Entity entity = addQueue.pop();
			entity.setWorld(this);
			entity.setID(id);
			entities.put(id, entity);
			newton.add(entity.getBody());
		}/*
		while(rmQueue.size() > 0) {
			id = (long)rmQueue.pop();
			entities.remove(id);
			newton.remove(entity.getBody());
		}*/
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
