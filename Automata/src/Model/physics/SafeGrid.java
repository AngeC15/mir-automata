package Model.physics;

import java.awt.geom.AffineTransform;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import Utils.SafeMap;
import Utils.SafeMapElement;

public class SafeGrid implements Iterable<SafeGridCell>{
	long idx;
	float cellSize = 5*5.3f;
	float cellOffset = 0;
	TreeMap<Long, SafeGridCell> grid;
	ArrayDeque<SafeGridCell> addQueue;
	ArrayDeque<Long> rmQueue;
	private double emptyCellLife = 5000;

	public SafeGrid() {
		idx = 0;
		grid = new TreeMap<Long, SafeGridCell>();
		addQueue = new ArrayDeque<SafeGridCell>();
		rmQueue = new ArrayDeque<Long>();
	}
	
	public void update() {
		for(SafeGridCell m : this) {
			m.update();
			if(m.size() == 0 && (m.getTime() - System.currentTimeMillis()) > emptyCellLife) {
				removeCell(m.getPos());
			}
			for(Entry<Long, SafeMapElement> e : m) {
				SafeGridElement em = (SafeGridElement)e.getValue();
				long prev_pos = em.getPos();
				long pos = getPos(em.getPosX_f(), em.getPosY_f());
				if(prev_pos != pos) {
					remove(em);
					addSafe(em);
				}
			}
		}
		
		int s = addQueue.size();
		for(int i=0; i < s; i++) {
			SafeGridCell c = addQueue.poll();
			grid.put(c.getPos(), c);
		}
		s = rmQueue.size();
		for(int i=0; i < s; i++) {
			long trm = rmQueue.poll();
			grid.remove(trm);
		}
	}
	
	public long getPos(float x, float y) {
		//System.out.println("x " + x + " y " + y);
		x -= cellOffset;
		y -= cellOffset;
		x /= cellSize;
		y /= cellSize;
		
		long xi = (long)Math.round(x);
		long yi = (long)Math.round(y);
		
		//System.out.println("xi " + xi + " yi " + yi);
		long p = ((xi << 32) | (yi & 0xFFFFFFFFL));
		//System.out.println("p " + p);
		return p;
	}
	public void addCell(SafeGridCell c) {
		addQueue.addLast(c);
	}
	public void addCellUnsafe(SafeGridCell c) {
		grid.put(c.getPos(), c);
	}
	public void removeCell(long id) {
		rmQueue.addLast(id);
	}
	public void addSafe(SafeGridElement e) {
		long pos = getPos(e.getPosX_f(), e.getPosY_f());
		long id;
		if(!grid.containsKey(pos)) {
			SafeGridCell c = new SafeGridCell(pos);
			id = c.add(e);
			addCell(c);
		}
		else {
			id = grid.get(pos).add(e);
		}
		e.setID(id);
		e.setPos(pos);
		
	}
	public void add(SafeGridElement e) {
		long pos = getPos(e.getPosX_f(), e.getPosY_f());
		long id;
		e.setPos(pos);
		if(!grid.containsKey(pos)) {
			SafeGridCell c = new SafeGridCell(pos);
			id = c.add(e);
			addCellUnsafe(c);
		}
		else {
			id = grid.get(pos).add(e);
		}
		e.setID(id);
		
	}
	public void remove(SafeGridElement e) {
		grid.get(e.getPos()).remove(e.getID());
	}
	public SafeGridCell get(long pos) {
		if(!grid.containsKey(pos))
			return null;
		return grid.get(pos);
	}
	public Iterator<SafeGridCell> iterator() {
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
