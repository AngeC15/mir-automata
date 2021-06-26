package Model.pathfinding;

public class Node implements Comparable<Node> {
	
	private int x;
	private int y;
	public int cost;
	public int heuristic;
	private Node previousNode;
	
	public Node(int x, int y, Node previousNode) {
		this.x = x;
		this.y = y;
		this.previousNode = previousNode;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Node getPreviousNode() {
		return previousNode;
	}

	/**
	 * Returns the distance between this and the given node 
	 * @param distantNode
	 * @return double
	 */
	public double distance(Node distantNode) {
		double tmpX = Math.pow(distantNode.getX() - x, 2);
		double tmpY = Math.pow(distantNode.getY() - y, 2);
		
		return Math.sqrt(tmpX + tmpY);
	}
	
	/**
	 * Returns true if the other node has close enough coordinates (with the given margin)
	 * @param otherNode
	 * @param margin
	 * @return boolean
	 */
	public boolean isSameCoordinates(Node otherNode, int margin) {
		boolean sameX = otherNode.getX() - margin < x + margin || otherNode.getX() + margin > x - margin;
		boolean sameY = otherNode.getY() - margin < y + margin || otherNode.getY() + margin > y - margin;
		
		return sameX || sameY;
	}

	@Override
	public int compareTo(Node otherNode) {
		if (heuristic < otherNode.heuristic) {
			return 1;
			
		} else if (heuristic == otherNode.heuristic) {
			return 0;
			
		} else {
			return -1;
		}
	}
}
