package Model;

import java.io.IOException;

import Model.monster_generator.Generator;
import View.Season;

public class Level {
	private int time;
	private int level;
	public World world;
	private Generator generator;
	private double startTime;
	int numEnemy;
	Season season;
	
	public Level(int time, int level, World world, Generator generator) throws IOException {
		System.out.println("New Level 0");
		this.time = time; //on ajoute 50s au dix secondes de départ
		this.level = level;
		this.world = world;
		this.generator = generator;
		this.startTime = System.currentTimeMillis();
		season = new Season(this.world);
		//generate();
	}
	
	public void generate() throws IOException {
		double now = System.currentTimeMillis();
		//double diff = now - startTime;
		//System.out.println("Difference " + diff);
		if(now - startTime > time) {
			if(time == 100) {
				time = 60000;
			}
			//changement de saison:
			try {
				season.nextSeason();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//autre
			level++;
			startTime = now;
			System.out.println("Lancement niveau :" + level);
			numEnemy = generator.new_wave(level);//(this.world.getPlayer().getTransform().getTranslateX(), this.world.getPlayer().getTransform().getTranslateX(), level);
		}
	}
	
	
	
	
	
	
}
