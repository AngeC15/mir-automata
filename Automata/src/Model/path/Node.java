package Model.path;

import Model.entities.Decor;
import Model.map.Map;
import Utils.Vector2;

public class Node {

	private double hCost;
	private Node previousNode;
	
	private Map map;
	
	private int x;
	private int y;
	
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
	
	public double min(double a, double b) {
		if (a <= b) return a;
		return b;
	}
	
	public double distance(Node goal) {
		double tmpx = Math.pow(min(goal.getX() - this.x, 30-goal.getX() + this.x), 2);
		double tmpy = Math.pow(min(goal.getY() - this.y, 30-goal.getY() + this.y), 2);
		return Math.sqrt(tmpx + tmpy);
	}
	
	public int getX() {
		return x;
	}
		
	public int getY() {
		return y;
	}
	
	public boolean containWall() {
		if (	map.get(y+1, x) != null && map.get(y+1, x).getAlive() == 1
			&&  map.get(y, x+1) != null && map.get(y, x+1).getAlive() == 1
			&&  map.get(y-1, x) != null && map.get(y-1, x).getAlive() == 1
			&&  map.get(y, x-1) != null && map.get(y, x-1).getAlive() == 1
			&&  map.get(y+1, x+1) != null && map.get(y+1, x+1).getAlive() == 1
			&&  map.get(y-1, x-1) != null && map.get(y-1, x-1).getAlive() == 1
			&&  map.get(y+1, x-1) != null && map.get(y+1, x-1).getAlive() == 1
			&&  map.get(y-1, x+1) != null && map.get(y-1, x+1).getAlive() == 1
			&&  map.get(y, x) != null && map.get(y, x).getAlive() == 1
				) {
			return true;
		}
		return false;
	}
	
	public boolean equals(Node node) {
		if (node.x == x && node.y == y)
			return true;
		return false;
	}
	
	public void setPrevious(Node previous) {
		previousNode = previous;
	}
	
	public Node getPrevious() {
		return previousNode;
	}
	
	public Vector2 getWorldCoordinate() {
		return new Vector2((x - (map.getY()/2.0f))*map.getDim(), (y - (map.getX()/2.0f))*map.getDim());
	}
	
	
	
}
