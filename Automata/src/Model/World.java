package Model;

import java.util.Map.Entry;

import Model.automata.creation.KeyExtension;
import Model.entities.Entity;

import java.util.TreeMap;

import Controller.VirtualInput;

public class World {
	private TreeMap<Long, Entity> entities;
	private long nextIntanceIdx;
	private VirtualInput inputs;
	
	public World(VirtualInput vi) {
		inputs = vi;
		entities = new TreeMap<Long, Entity>();
		nextIntanceIdx = 0;
	}
	
	public void tick(long elapsed) {
		
		for(Entry<Long, Entity> entries : entities.entrySet()) {
			entries.getValue().step();
		}
	}
	public TreeMap<Long, Entity> getEntities(){
		return entities;
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
}
