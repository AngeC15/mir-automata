package Model;

import java.util.Map.Entry;

import Model.entities.Entity;

import java.util.TreeMap;

public class World {
	private TreeMap<Long, Entity> entities;
	private long nextIntanceIdx;
	private long elapsed;
	
	public World() {
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
	public long addEntity(Entity entity) {
		entities.put(nextIntanceIdx, entity);
		return nextIntanceIdx++;
	}
	
	public long getElapsed() {
		return elapsed;
	}
}
