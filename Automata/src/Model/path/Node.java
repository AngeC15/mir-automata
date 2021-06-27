package Model.path;

public class Node {

	private float hCost;
	private float gCost;
	
	private boolean wakable;
	
	private int x;
	private int y;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}
