package Model.monster_generator;

import java.awt.geom.AffineTransform;
import java.io.IOException;

import Model.World;
import Model.entities.EnemyPlayer;
import Model.loader.TemplatesLoader;
import View.Avatar;
import View.Template;

public class Generator {
	int difficulty;
	World w;
	float dim;
	public Generator(World w,float dim, int dif) {
		this.w = w;
		this.dim=dim;
		this.difficulty = dif;
	}
	
	public void new_wave(int level) throws IOException {
		int nb_monster=this.difficulty*level*level+level+5;

		while(nb_monster>0) {
			//get a random coordinate 
			double x =Math.random()*2*dim-dim;
			double y =Math.random()*2*dim-dim;
			//spawn a ennemy her
			spawn(x,y);
			nb_monster--;

		}
		

	}
	public void spawn(double x, double y) throws IOException {
		//spawn probability of each enemy 
		double p_tank=0.25;
		double p_plane=0.25;
		double p_snake=0.25;
		double p_oie=0.25;
		double b1=p_tank;
		double b2=b1+p_plane;
		double b3=b2+p_snake;
		double random_enemy=Math.random();

		if(random_enemy<b1) {
			System.out.print("tank pop"); 
		}
		else if(random_enemy>b1 && random_enemy<b2) {
			System.out.print("plane pop") ;
		}
		else if(random_enemy>b2 && random_enemy<b3) {
			System.out.print("snake pop") ;
		}
		else if(random_enemy>b3) {
			System.out.print("oie pop") ;
		}
		
		
		Template tmp2 = TemplatesLoader.get("Dead");
		EnemyPlayer enemy = new EnemyPlayer(this.w);
		Avatar av3 = new Avatar(enemy, tmp2);
		enemy.getTransform().concatenate(AffineTransform.getTranslateInstance(x,y));
		this.w.addEntity(enemy);
	}
	
	
	
}
