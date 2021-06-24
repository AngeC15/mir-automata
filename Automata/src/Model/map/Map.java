package Model.map;

import java.awt.geom.AffineTransform;
import java.io.IOException;

import Model.World;
import Model.entities.Entity;
import Model.entities.Decor;
import Model.loader.TemplatesLoader;
import View.Avatar;

public class Map {
	
	private Entity[][] map;
	private float dimension;
	private World world;
	private double time;
	private int max_step = 15;
	private boolean canStep;
	private boolean player_generated;
	private int cmpt_step;
	
	public Map(int n, int p, float dimension, World world) throws IOException {
		this.world = world;
		map = new Entity[n][p];
		
		AffineTransform xt = AffineTransform.getTranslateInstance(dimension, 0);
		AffineTransform yt = AffineTransform.getTranslateInstance(0, dimension);
		AffineTransform lineCurrent = new AffineTransform();
		lineCurrent.translate(-n/2*dimension, -p/2*dimension);
		
		for (int i = 0 ; i < n ; i++) { 
			AffineTransform cellCurrent = new AffineTransform(lineCurrent);
			for (int j = 0 ; j < p ; j ++) {
				Decor w = new Decor(this, i, j);
				map[i][j] = w; 
				new Avatar(w, TemplatesLoader.get("Wall"));
				w.getBody().getTransform().concatenate(cellCurrent);
				world.addEntity(w);
				cellCurrent.concatenate(xt);
			}
			lineCurrent.concatenate(yt);
		}
		this.dimension = dimension;
		this.time = System.currentTimeMillis();
		this.player_generated = false;
		this.cmpt_step = 0;
	}
	
	public Entity get(int i, int j) {
		i = (i + map.length) % map.length;
		j = (j + map[0].length) % map[0].length;
		return map[i][j];
	}
	
	public void tick(long elapsed) throws IOException {
		canStep = false;
		if (cmpt_step > max_step+1 && !player_generated) {
			player_generated = true;
			world.generationDone(); 
		}
		if (System.currentTimeMillis()-time > 500) {
			canStep = true;
			time = System.currentTimeMillis();
			cmpt_step ++;
		}
	}
	
	public boolean step() {
		return canStep;
	}
	
	
	public int getMaxStep() {
		return max_step;
	}
	
	public int stepCounter() {
		return cmpt_step;
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
