package Model.map;

import Model.World;
import Model.entities.Entity;
import Model.entities.Wall;

public class Map {
	
	private Entity[][] map;
	private int dimension;

	public Map(World world, int n, int p, int dimension) {
		map = new Entity[n][p];
		for (int i = 0 ; i < n ; i++) {
			for (int j = 0 ; j < p ; j ++) {
				if (i!=n-1 && j!=n-1) 
					map[i][j] = new Wall(world);
				else
					map[i][j] = new Wall(world, true);
			}
		}
		this.dimension = dimension;
	}
	
	public Entity get(int i, int j) {
		return map[i][j];
	}

}
