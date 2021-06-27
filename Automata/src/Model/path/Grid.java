package Model.path;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import Model.map.Map;

public class Grid {
	
	private Node[][] grid;
	private float dimension;
	
	private Node[] neighbors = new Node[4];
	private int idx;
	
	PriorityQueue<Node> openSet = new PriorityQueue<Node>(1, new NodeComparator());
	LinkedList<Node> closedlist = new LinkedList();
	Node currentNode;
	
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
			if (!tmp.containWall() && !openSet.contains(tmp) && !closedlist.contains(tmp)) {
				tmp.SethCost(tmp.distance(goal));
				tmp.setPrevious(currentNode);
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
		neighbors[0] = grid[(x+1+getP())%getP()][y];
		neighbors[1] = grid[x][(y+1+getN())%getN()];
		neighbors[2] = grid[(x-1+getP())%getP()][y];
		neighbors[3] = grid[x][(y-1+getN())%getN()];
//		for (int k = 0 ; k < 4 ; k ++) {
//			neighbors[k].setPrevious(currentNode);
//		}
	}
	
	public ArrayDeque<Node> construct_path(Node finalnode){
		Node tmp = finalnode;
		ArrayDeque<Node> path = new ArrayDeque<Node>();
		while (tmp != null) {
			path.push(tmp);
			tmp = tmp.getPrevious();
		}
		return path;
	}
	
	public ArrayDeque<Node> starPath(Node start, Node goal) {
		currentNode = start;
		start.setPrevious(null);

		start.SethCost(start.distance(goal));

		openSet.add(start);
		

		
		while (!openSet.isEmpty()) {
			currentNode = openSet.peek();
			closedlist.add(currentNode);
			
			if (currentNode.equals(goal)) {
				openSet.clear();
				closedlist.clear();
				return construct_path(currentNode);
			}
			openSet.remove();
			
			InitNeighbors();
			neighbors(currentNode.getX(), currentNode.getY(), goal);
			
		}
		openSet.clear();
		closedlist.clear();
		return null;
	}
	
	public int getN() {
		return grid.length;
	}
	
	public int getP() {
		return grid[0].length;
	}
	

}
