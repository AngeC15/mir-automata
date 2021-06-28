package Model;

import java.io.IOException;

import Model.entities.Player;
import Model.monster_generator.Generator;
import View.Season;

public class Level {
	private int time;
	public int level;
	public World world;
	private Generator generator;
	private double startTime;
	int numEnemy;
	private boolean transition;
	Season season;

	public Level(int time, int level, World world, Generator generator) throws IOException {
		System.out.println("New Level 0");
		this.time = time;
		this.level = level;
		this.world = world;
		this.generator = generator;
		this.startTime = System.currentTimeMillis();
		this.numEnemy = 0;
		season = new Season(this.world);
		transition = true;
		// generate();
	}

	public void generate() throws IOException {
		double now = System.currentTimeMillis();
		boolean allEnnemyKilled = (numEnemy != 0 && (numEnemy - world.deathEnnemies) == 0);
		if (now - startTime > time || allEnnemyKilled) {
			if (time == 2000) {
				time = 60000;
			}

			level++;
			startTime = now;
			// System.out.println("Lancement niveau :" + level);
			numEnemy = generator.new_wave(level);// (this.world.getPlayer().getTransform().getTranslateX(),
													// this.world.getPlayer().getTransform().getTranslateX(), level);
			world.deathEnnemies = 0;
			((Player) world.getPlayer()).setLife(1000); // remet la vie du joueur � son maximum

		}
	}

}
