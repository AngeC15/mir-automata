package Model.physics;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import Utils.SafeMap;

public class SafeGrid implements Iterable<Entry<Long,SafeGridElement>>{
	long idx;
	TreeMap<Long, SafeMap> grid;
	ArrayDeque<SafeGridElement> addQueue;
	ArrayDeque<Long> rmQueue;
	
	public SafeGrid() {
		idx = 0;
		grid = new TreeMap<Long, SafeMap>();
		addQueue = new ArrayDeque<SafeGridElement>();
		rmQueue = new ArrayDeque<Long>();
	}
	
	public void update() {
		long id = idx - addQueue.size();
		
		int s = addQueue.size();
		for(int i=0; i < s; i++) {
			contents.put(id, addQueue.poll());
			id++;
		}
		s = rmQueue.size();
		for(int i=0; i < s; i++) {
			contents.remove(rmQueue.poll());
		}
	}
	public long add(SafeMapElement elem) {
		addQueue.addLast(elem);
		return idx++;
	}
	public void remove(long id) {
		rmQueue.addLast(id);
	}
	public SafeMapElement get(Long id) {
		return contents.get(id);
	}
	public Iterator<Entry<Long,SafeMapElement>> iterator() {
		return (Iterator<Entry<Long,SafeMapElement>>) contents.entrySet().iterator();
	}
	public int size() {
		return contents.size();
	}
}

}
