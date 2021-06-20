package Utils;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

public class SafeMap implements Iterable<Entry<Long,SafeMapElement>>{
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
		for(int i=0; i < addQueue.size(); i++) {
			contents.put(id, addQueue.poll());
			id++;
		}
		for(int i=0; i < rmQueue.size(); i++) {
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
}
