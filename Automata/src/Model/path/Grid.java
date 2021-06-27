package Model.path;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import Model.map.Map;

public class Grid {
	
	private Node[][] grid;
	private float dimension;
	
	private Node[] neighbors = new Node[4];
	private int idx;
	
	PriorityQueue<Node> openSet = new PriorityQueue<Node>(1, new NodeComparator());
	ArrayDeque<Node> path = new ArrayDeque();
	Node currentNode;
	Node previousNode;
	
	//erreur peut potentiellement venir d'ici
	private class NodeComparator implements Comparator<Node>{

		@Override
		public int compare(Node node1, Node node2) {
			if (node1.GetfCost() > node2.GetfCost())
				return 1;
			if (node1.GetfCost() < node2.GetfCost())
				return -1;
			return 0;
		}
		
	}
	
	public Grid(int n, int p, float dimension, Map map) {
		grid = new Node[n][p];
		this.dimension = dimension;
		for (int i = 0 ; i < n ; i ++) {
			for (int j = 0 ; j < p ; j ++) {
				grid[i][j] = new Node(i, j, map);
			}
		}
	}
	
	public float getDim() {
		return dimension;
	}
	
	public void neighbors(int x, int y, Node goal) {
		Node tmp;
		
		for (int k = 0 ; k < 4 ; k ++) {
			tmp = nextNeighbor();
			if (!tmp.containWall() && !openSet.contains(tmp)) {
				tmp.SethCost(tmp.distance(goal)+currentNode.GethCost());
				openSet.add(tmp);
			}
		}
	}
	
	public Node nextNeighbor() {
		idx = idx % 4;
		return neighbors[idx ++];
	}
	
	public void InitNeighbors() {
		int x = currentNode.getX();
		int y = currentNode.getY();
		neighbors[0] = grid[Math.floorMod(x+1,getP())][y];
		neighbors[1] = grid[x][Math.floorMod(y+1, getN())];
		neighbors[2] = grid[Math.floorMod(x-1,getP())][y];
		neighbors[3] = grid[x][Math.floorMod(y-1,getN())];
	}
	
	public ArrayDeque<Node> starPath(Node start, Node goal) {
		currentNode = start;
		previousNode = null;
		openSet.add(start);
		
		start.SethCost(start.distance(goal));
		
		while (!openSet.isEmpty()) {
			currentNode = openSet.peek();
			
			if (currentNode == goal) {
				openSet.clear();
				return path;
			}
			openSet.remove();
			
			InitNeighbors();
			neighbors(currentNode.getX(), currentNode.getY(), goal);
			
		}
		openSet.clear();
		return null;
	}
	
	public int getN() {
		return grid.length;
	}
	
	public int getP() {
		return grid[0].length;
	}
	

}
