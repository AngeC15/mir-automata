package Model.ground;

public class Map {
	
	private int[][] map;

	public Map(int n, int p) {
		map = new int[n][p];
		for (int i = 0 ; i < n ; i++) {
			for (int j = 0 ; j < p ; j ++) {
				map[i][j] = 0;
			}
		}
	}
	
	public int[][] get() {
		return map;
	}
	
}
