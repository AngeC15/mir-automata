package Model.path;

public class Grid {
	
	private Node[][] grid;
	
	public Grid(int n, int p, float dimension) {
		grid = new Node[n][p];
		for (int i = 0 ; i < n ; i ++) {
			for (int j = 0 ; j < p ; j ++) {
				grid[i][j] = new Node(i, j);
			}
		}
	}
	
	

}
