package Model.map;

import java.awt.geom.AffineTransform;
import java.io.IOException;

import Model.World;
import Model.entities.Entity;
import Model.entities.Wall;
import Model.loader.TemplatesLoader;
import View.Avatar;

public class Map {
	
	private Entity[][] map;
	private float dimension;
	private World world;
	private double start;
	private int max_step = 12;
	private boolean player_generated;
	
	public Map(int n, int p, float dimension, World world) throws IOException {
//		tick_counter = 0;
		this.world = world;
		map = new Entity[n][p];
		
		AffineTransform xt = AffineTransform.getTranslateInstance(dimension, 0);
		AffineTransform yt = AffineTransform.getTranslateInstance(0, dimension);
		AffineTransform lineCurrent = new AffineTransform();
		lineCurrent.translate(-n/2*dimension, -p/2*dimension);
		
		for (int i = 0 ; i < n ; i++) { 
			AffineTransform cellCurrent = new AffineTransform(lineCurrent);
			for (int j = 0 ; j < p ; j ++) {
				Wall w = new Wall(this, i, j);
				map[i][j] = w; 
				new Avatar(w, TemplatesLoader.get("GenCell"));
				w.getBody().getTransform().concatenate(cellCurrent);
				world.addEntity(w);
				cellCurrent.concatenate(xt);
			}
			lineCurrent.concatenate(yt);
		}
		this.dimension = dimension;
		this.start = System.currentTimeMillis();
		this.player_generated = false;
	}
	
	public Entity get(int i, int j) {
		i = (i + map.length) % map.length;
		j = (j + map[0].length) % map[0].length;
		return map[i][j];
	}
	
	public void tick(long elapsed) throws IOException {
		if (System.currentTimeMillis()-start > max_step*600 && !player_generated) {
			player_generated = true;
			world.player(); 
		}
	}
	
	
	public int getMaxStep() {
		return max_step;
	}
	
	public void remove(int x, int y) {
		world.removeEntity(map[x][y].getID());
		map[x][y] = null;
	}
	
	public int getX() {
		return map.length;
	}
	public int getY() {
		return map[0].length;
	}
}
