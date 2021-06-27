package Model.path;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import Model.map.Map;

public class Grid {
	
	private Node[][] grid;
	private float dimension;
	
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
			tmp = currentNode.nextNeighbor();
			if (!tmp.containWall() && openSet.contains(tmp)) {
				tmp.SethCost(tmp.distance(goal)+currentNode.GethCost());
				openSet.add(tmp);
			}
		}
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
			neighbors(currentNode.getX(), currentNode.getY(), goal);
			
			path.add(currentNode);
		}
		openSet.clear();
		return null;
	}
	

}
