package Model.map;

import Model.World;
import Model.entities.Entity;
import Model.entities.Wall;

public class Map {
	
	private Entity[][] map;
	private float dimension;

	public Map(World world, int n, int p, float dimension) {
		map = new Entity[n][p];
		for (int i = 0 ; i < n ; i++) {
			for (int j = 0 ; j < p ; j ++) {
					map[i][j] = new Wall(world);
			}
		}
		this.dimension = dimension;
	}
	
	public Entity get(int i, int j) {
		i = i % map.length;
		j = j % map[0].length;
		return map[i][j];
	}
	
	

}
