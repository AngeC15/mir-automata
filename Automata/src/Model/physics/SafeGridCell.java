package Model.physics;

import Utils.SafeMap;

public class SafeGridCell extends SafeMap{
	private long pos;
	private double empty_since;
	public SafeGridCell(long p) {
		super();
		pos = p;
		empty_since = Double.MAX_VALUE;
	}
}
