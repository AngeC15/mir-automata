package Model.map;

import java.io.IOException;

import Model.World;
import Model.entities.Entity;
import Model.entities.Wall;
import Model.loader.TemplatesLoader;
import View.Avatar;

public class Map {
	
	private Entity[][] map;
	private float dimension;
	private final int max_tick = 20;
	private int tick_counter;
	
	public Map(int n, int p, float dimension) throws IOException {
		tick_counter = 0;
		map = new Entity[n][p];
		for (int i = 0 ; i < n ; i++) {
			for (int j = 0 ; j < p ; j ++) {
				Wall w = new Wall(this, i, j);
				map[i][j] = w;
				new Avatar(w, TemplatesLoader.get("GenCell"));
			}
		}
		this.dimension = dimension;
	}
	
	public Entity get(int i, int j) {
		i = i % map.length;
		j = j % map[0].length;
		return map[i][j];
	}
	public void tick(long elapsed) {
		tick_counter++;
	}
	public boolean generationOver() {
		return tick_counter >= max_tick;
	}
	

}
