package Controller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import Model.World;
import Model.entities.Cowboy;
import Model.entities.EnemyPlayer;
import Model.entities.Player;
import Model.entities.enemies.Duck;
import Model.entities.enemies.Plane;
import Model.entities.enemies.Snake;
import Model.entities.enemies.Tank;
import Model.loader.AutomataLoader;
import Model.loader.TemplatesLoader;
import Model.map.Map;
import View.Avatar;
import View.GameView;
import View.Sound;
import View.Template;




public class Game {

	static Game game;
	
	CanvasListener m_listener;
	Cowboy m_cowboy;
	Sound m_music;
	World world;
	GameView view ;
	
	
	
	public static void main(String args[]) throws Exception {
		try {
			System.out.println("Game starting...");
			game = new Game();
			game.view.post(new Init());
			System.out.println("Game started.");
		} catch (Throwable th) {
			th.printStackTrace(System.err);
		}
	}

	Game() throws Exception {
		// creating a listener for all the events
		m_listener = new CanvasListener(this);
		view = new GameView(m_listener);
	}
	
	public void init_game() throws Exception {
		System.out.println("init game");
		m_listener.getVirtualInput().setView(view);
		view.setupFrame();
		AutomataLoader.load_all("Bots/loader.txt");
		TemplatesLoader.load_all("Resources/loader.txt");
		world = new World(m_listener.getVirtualInput());
		view.setWorld(world);

		Map map = new Map(50, 50, 10f, world);
		world.setMap(map);
		
		Player player = new Player(world);
		Template tmp = TemplatesLoader.get("Hero");
		new Avatar(player, tmp);
		world.addEntity(player);
		world.setPlayer(player);
		
		// uncomment if you want enemies
		/*
		Tank tank = new Tank("Tank");
		Template tmpTank = TemplatesLoader.get("Tank");
		new Avatar(tank, tmpTank);
		world.addEntity(tank);
		
		Snake snake = new Snake("Snake");
		Template tmpSnake = TemplatesLoader.get("Snake");
		new Avatar(snake, tmpSnake);
		world.addEntity(snake);
		
		Duck duck = new Duck("Duck");
		Template tmpDuck = TemplatesLoader.get("Duck");
		new Avatar(duck, tmpDuck);
		world.addEntity(duck);
		
		Plane plane = new Plane("Plane");
		Template tmpPlane = TemplatesLoader.get("Plane");
		new Avatar(plane, tmpPlane);
		world.addEntity(plane);
		*/
		
		/*
		 * Wall wall = new Wall(world); Avatar av2 = new Avatar(wall, tmp);
		 * wall.getTransform().concatenate(AffineTransform.getTranslateInstance(0, 10));
		 * world.addEntity(wall);
		 */
		
		Template tmp2 = TemplatesLoader.get("Dead");
		EnemyPlayer enemy = new EnemyPlayer(world);
		Avatar av3 = new Avatar(enemy, tmp2);
		enemy.getTransform().concatenate(AffineTransform.getTranslateInstance(0, -20));
		world.addEntity(enemy);
	}
	
	private static class Init implements Runnable{

		@Override
		public void run() {
			try {
				game.init_game();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	

	/*
	 * ================================================================ 
	 * All the
	 * methods below are invoked from the GameCanvas listener, once the window is
	 * visible on the screen.
	 * ==============================================================
	 */

	/*
	 * Called from the GameCanvas listener when the frame
	 */
	
	/*
	String m_musicName;

	void loadMusic() {
		m_musicName = m_musicNames[m_musicIndex];
		String filename = "resources/" + m_musicName + ".ogg";
		m_musicIndex = (m_musicIndex + 1) % m_musicNames.length;
		try { 
			RandomAccessFile file = new RandomAccessFile(filename,"r");
			RandomFileInputStream fis = new RandomFileInputStream(file);
			m_canvas.playMusic(fis, 0, 1.0F);
		} catch (Throwable th) {
			th.printStackTrace(System.err);
			System.exit(-1);
		}
	}

	private int m_musicIndex = 0;
	private String[] m_musicNames = new String[] { "Runaway-Food-Truck" }; 
	*/
	

	/*
	 * This method is invoked almost periodically, given the number of milli-seconds
	 * that elapsed since the last time this method was invoked.
	 */
	public void tick(long elapsed) {
		
		world.tick(elapsed);
		view.tick(elapsed);
	}

	/*
	 * This request is to paint the Game Canvas, using the given graphics. This is
	 * called from the GameCanvasListener, called from the GameCanvas.
	 */
	public void paint(Graphics g) {
		view.paint((Graphics2D)g);
	}

}

