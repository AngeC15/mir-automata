package Model.physics;

import java.awt.geom.AffineTransform;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import Utils.SafeMap;
import Utils.SafeMapElement;

public class SafeGrid implements Iterable<SafeMap>{
	long idx;
	float cellSize = 25.0f;
	float cellOffset = 25.0f/2.0f;
	TreeMap<Long, SafeMap> grid;
	ArrayDeque<Cell> addQueue;
	ArrayDeque<Long> rmQueue;
	private class Cell{
		public Cell(long p, SafeMap m) {
			pos = p;
			map = m;
		}
		public long pos;
		public SafeMap map;
	}
	public SafeGrid() {
		idx = 0;
		grid = new TreeMap<Long, SafeMap>();
		addQueue = new ArrayDeque<Cell>();
		rmQueue = new ArrayDeque<Long>();
	}
	
	public void update() {
		for(SafeMap m : this) {
			m.update();
			for(Entry<Long, SafeMapElement> e : m) {
				long id = e.getKey();
				SafeGridElement em = (SafeGridElement)e.getValue();
				long prev_pos = em.getPos();
				long pos = getPos(em.getPosX_f(), em.getPosY_f());
				if(prev_pos != pos) {
					m.remove(id);
					grid.get(pos).add(em);
				}
			}
		}
		
		int s = addQueue.size();
		for(int i=0; i < s; i++) {
			Cell c = addQueue.poll();
			grid.put(c.pos, c.map);
		}
		s = rmQueue.size();
		for(int i=0; i < s; i++) {
			grid.remove(rmQueue.poll());
		}
	}
	public long getPos(float x, float y) {
		x -= cellOffset;
		y -= cellOffset;
		x /= cellSize;
		y /= cellSize;
		
		long xi = (long)x;
		long yi = (long)y;
		
		return (xi << 32) & yi;
	}
	public void addCell(SafeMap cell, long pos) {
		Cell c = new Cell(pos, cell);
		addQueue.addLast(c);
	}
	public void remove(long id) {
		rmQueue.addLast(id);
	}
	public SafeGridElement get(long pos, long id) {
		return (SafeGridElement)grid.get(pos).get(id);
	}
	public Iterator<SafeMap> iterator() {
		return grid.values().iterator();
	}
	public int gridSize() {
		return grid.size();
	}
	public int count() {
		int c = 0;
		for(SafeMap m : this) {
			c += m.size();
		}
		return c;
	}
}
