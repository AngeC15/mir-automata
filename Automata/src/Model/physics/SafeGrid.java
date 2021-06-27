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
	float cellSize;
	float cellOffset = 0;
	TreeMap<Long, SafeGridCell> grid;
	TreeMap<Long, SafeGridCell> addQueue;
	ArrayDeque<Long> rmQueue;
	private double emptyCellLife = 5000;

	public SafeGrid(float block_size) {
		idx = 0;
		grid = new TreeMap<Long, SafeGridCell>();
		addQueue = new TreeMap<Long, SafeGridCell>();
		rmQueue = new ArrayDeque<Long>();
		cellSize = 5*block_size;
	}
	
	public void update() {
		
		for(SafeGridCell m : this) {
			m.update();
			if(m.empty() && (System.currentTimeMillis() - m.getTime()) > emptyCellLife) {
				//System.out.println("timeout " + m.getPos());
				removeCell(m.getPos());
			}
			for(Entry<Long, SafeMapElement> e : m) {
				SafeGridElement em = (SafeGridElement)e.getValue();
				long prev_pos = em.getPos();
				long pos = getPos(em.getPosX_f(), em.getPosY_f());
				if(prev_pos != pos) {
					//System.out.println("moved (" + em.getPos() + " - " + em.getID() + ") from " + prev_pos + " to " + pos);
					remove(em);
					addSafe(em);
				}
			}
		}
		
		int s = addQueue.size();
		for(SafeGridCell c: addQueue.values()) {
			grid.put(c.getPos(), c);
		}
		addQueue.clear();
		
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
		addQueue.put(c.getPos(), c);
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
		e.setPos(pos);
		if(!grid.containsKey(pos) && !addQueue.containsKey(pos)) {
			SafeGridCell c = new SafeGridCell(pos);
			//System.out.println("Cell added, add (" + e.getPos() + " - " + e.getID() + ") to " + c);
			id = c.add(e);
			c.setTime(System.currentTimeMillis());
			addCell(c);
		}
		else {
			SafeGridCell c = grid.get(pos);
			if(c == null)
				c = addQueue.get(pos);
 			id = c.add(e);
			c.setTime(System.currentTimeMillis());
			//System.out.println("Cell exists, add (" + e.getPos() + " - " + e.getID() + ") to " + grid.get(pos));
		}
		e.setID(id);
	}
	public void add(SafeGridElement e) {
		long pos = getPos(e.getPosX_f(), e.getPosY_f());
		long id;
		e.setPos(pos);
		if(!grid.containsKey(pos) && !addQueue.containsKey(pos)) {
			SafeGridCell c = new SafeGridCell(pos);
			//System.out.println("Cell added, add (" + e.getPos() + " - " + e.getID() + ") to " + c);
			id = c.add(e);
			c.setTime(System.currentTimeMillis());
			addCellUnsafe(c);
		}
		else {
			SafeGridCell c = grid.get(pos);
			if(c == null)
				c = addQueue.get(pos);
 			id = c.add(e);
			c.setTime(System.currentTimeMillis());
			//System.out.println("Cell exists, add (" + e.getPos() + " - " + e.getID() + ") to " + grid.get(pos));
		}
		e.setID(id);
	}
	public void remove(SafeGridElement e) {
		SafeGridCell c = grid.get(e.getPos());
		//System.out.println("(rm) Remove (" + e.getPos() + " - " + e.getID() + ") from " + grid.get(e.getPos()));
		
		c.remove(e.getID());
		c.setTime(System.currentTimeMillis());
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
