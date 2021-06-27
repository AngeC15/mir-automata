package Model.path;

import Model.entities.Decor;
import Model.map.Map;

public class Node {

	private double hCost;
	
	private boolean wakable;
	
	private Map map;
	
	private int x;
	private int y;
	
	private Node[] neighbors = new Node[4];
	private int idx;
	
	public Node(int x, int y, Map map) {
		this.x = x;
		this.y = y;
		this.map = map;
	}
	
	public void SethCost(double hCost) {
		this.hCost = hCost;
	}
	
	public double GethCost() {
		return hCost;
	}
	
	
	public double GetfCost() {
		return hCost;
	}
	
	public double distance(Node goal) {
		double tmpx = Math.pow(goal.getX() - this.x, 2);
		double tmpy = Math.pow(goal.getY() - this.y, 2);
		return Math.sqrt(tmpx + tmpy);
	}
	
	public int getX() {
		return x;
	}
		
	public int getY() {
		return y;
	}
	
	public boolean containWall() {
		Decor tmp = map.get(x, y);
		if (tmp != null && map.get(x, y).getAlive() == 1)
			return true;
		return false;
	}
	
	public Node nextNeighbor() {
		idx = idx % neighbors.length;
		return neighbors[idx ++];
	}
	
}
