package Utils;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

public class SafeMap implements Iterable<Entry<Long, SafeMapElement>> {
	long idx;
	TreeMap<Long, SafeMapElement> contents;
	ArrayDeque<SafeMapElement> addQueue;
	ArrayDeque<Long> rmQueue;

	public SafeMap() {
		idx = 0;
		contents = new TreeMap<Long, SafeMapElement>();
		addQueue = new ArrayDeque<SafeMapElement>();
		rmQueue = new ArrayDeque<Long>();
	}

	public void update() {
		long id = idx - addQueue.size();

		int s = addQueue.size();
		for (int i = 0; i < s; i++) {
			SafeMapElement elem = addQueue.poll();
			//System.out.println("added (" + elem.getID() + ") to " + this);
			contents.put(id, elem);
			id++;
		}
		s = rmQueue.size();
		for (int i = 0; i < s; i++) {
			long idr = rmQueue.poll();
			SafeMapElement elem = contents.get(idr);
			if(elem == null)
				continue;
			//System.out.println("removed (" + elem.getID() + ") from " + this);
			contents.remove(idr);
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

	@Override
	public Iterator<Entry<Long, SafeMapElement>> iterator() {
		return contents.entrySet().iterator();
	}

	public int size() {
		return contents.size();
	}
	public boolean empty() {
		return (contents.size() + addQueue.size() + rmQueue.size()) == 0;
	}
}
