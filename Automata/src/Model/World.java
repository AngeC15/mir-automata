package Model;

import java.util.Map.Entry;

import Model.automata.creation.KeyExtension;
import Model.entities.Entity;

import java.util.TreeMap;

import Controller.VirtualInput;

public class World {
	private TreeMap<Long, Entity> entities;
	private Entity player;
	private long nextIntanceIdx;
	private VirtualInput inputs;
	private long elapsed;
	
	public World(VirtualInput vi) {
		inputs = vi;
		entities = new TreeMap<Long, Entity>();
		nextIntanceIdx = 0;
		elapsed = 0;
	}
	
	public void tick(long elapsed) {
		this.elapsed = elapsed;
	
		for(Entry<Long, Entity> entries : entities.entrySet()) {
			entries.getValue().step();
		}
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
	public void addEntity(Entity entity, long id) {
		entities.put(id, entity);
		nextIntanceIdx++;
	}
	public long getNextId() {
		return nextIntanceIdx;
	}
	public void setPlayer(Entity p) {
		player = p;
	}
	public Entity getPlayer() {
		return player;
	}
}
