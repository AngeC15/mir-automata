package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import Model.World;
import Model.entities.Entity;
import Model.entities.enemies.Tank;
import Model.loader.TemplatesLoader;
import Utils.SafeMap;
import Utils.SafeMapElement;
import Utils.Vector2;

public class GameView {

	GameCanvas m_canvas;
	JFrame m_frame;
	JLabel m_text;
	World world;
	int intensity;
	
	private float game_w;
	private float game_h;
	private long m_textElapsed;
	private AffineTransform canvasTransform;
	private AffineTransform cameraTransform;
	private AffineTransform localTransform;

	private double cameraDistance = 1.5f;
	private MiniMap miniMap;
	private Menu menu;
	private Dimension frameSize;

	private float units_per_width = 100.0f;
	private float sprite_pixels_per_unit = 6.0f;
	private Season season;
	
	

	// utiliser spritesheet pour charger le fond
	// private File imageFond;


	private static final AffineTransform identity = new AffineTransform();

	public GameView(GameCanvasListener listener) {

		m_canvas = new GameCanvas(listener);

		System.out.println("  - creating frame...");

		frameSize = new Dimension(1024, 768);
		miniMap = new MiniMap();

		menu = new Menu(frameSize, this);

		m_frame = m_canvas.createFrame(frameSize);

		m_frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent ev) {
				System.out.println("resized");
				updateCanvasTransform();
			}
		});

		m_frame.add(menu, BorderLayout.CENTER);

	}

	public void post(Runnable r) {
		m_canvas.post(r);
	}
	/*
	 * Then it lays out the frame, with a border layout, adding a label to the north
	 * and the game canvas to the center.
	 */

	public void updateCanvasTransform() {
		float canvasScaling = m_frame.getWidth() / units_per_width; // 100.0 wide
		canvasTransform = AffineTransform.getScaleInstance(canvasScaling, -canvasScaling);
		float ratio = (((float) m_frame.getHeight()) / ((float) m_frame.getWidth()));
		canvasTransform.translate(units_per_width / 2.0f, -(units_per_width / 2.0f) * ratio);

	}

	public void setupFrame(float game_w, float game_h) {
		m_frame.setTitle("MIR: Automata");
		m_frame.setLayout(new BorderLayout());
		m_frame.add(menu, BorderLayout.CENTER);
		// center the window on the screen
		m_frame.setLocationRelativeTo(null);

		// make the window visible
		m_frame.setVisible(true);
		this.game_w = game_w;
		this.game_h = game_h;
	}

	public void setupGame() {
		System.out.println("  - setting up the frame...");

		localTransform = AffineTransform.getScaleInstance(1 / sprite_pixels_per_unit, -1 / sprite_pixels_per_unit);
		updateCanvasTransform();
		cameraTransform = AffineTransform.getScaleInstance(1 / cameraDistance, 1 / cameraDistance);

		
		m_frame.setTitle("Game");
		m_frame.setLayout(new BorderLayout());

		m_text = new JLabel();
		m_text.setText("Tick: 0ms FPS=0");
		m_frame.add(m_text, BorderLayout.NORTH);

		// LayeredPane permet la superposition du canvas et du panel
		JLayeredPane pane = new JLayeredPane();

		m_canvas.setBounds(0, 0, m_frame.getWidth(), m_frame.getHeight());

		// adding buttons on pane
		pane.add(miniMap, 1);
		pane.add(m_canvas, 2);

		m_frame.add(pane, BorderLayout.CENTER);

		// center the window on the screen
		m_frame.setLocationRelativeTo(null);
		// make the window visible
		m_frame.setVisible(true);

		// ______
		m_frame.remove(menu);

		season = new Season(world);
		intensity = 0;
		
		/*Tank tank = new Tank("Tank");
		Template tmpTank = TemplatesLoader.get("Tank");
		try {
			new Avatar(tank, tmpTank);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tank.getTransform().concatenate(AffineTransform.getTranslateInstance(0, 100));
		world.addEntity(tank);*/
		

	}

	public void tick(long elapsed) {
		// Update every second
		// the text on top of the frame: tick and fps
		m_textElapsed += elapsed;
		if (m_textElapsed > 1000) {
			m_textElapsed = 0;
			float period = m_canvas.getTickPeriod();
			int fps = m_canvas.getFPS();

			String txt = "Tick=" + period + "ms";
			while (txt.length() < 15)
				txt += " ";
			txt = txt + fps + " fps   ";
			m_text.setText(txt);
		}
	}

	public void setWorld(World w) {
		world = w;
		miniMap.setWorld(world);
	}

	public Vector2 getMouseWorld(int x, int y) throws NoninvertibleTransformException {
		Vector2 m = getMousePlayer(x, y);
		Vector2 r = new Vector2(0, 0);
		AffineTransform playerTransform;
		if (world.getPlayer() == null) {
			playerTransform = identity;
		} else {
			playerTransform = world.getPlayer().getTransform();
		}

		AffineTransform playerTranslate = AffineTransform.getTranslateInstance(playerTransform.getTranslateX(),
				playerTransform.getTranslateY());
		playerTranslate.transform(m, r);
		return r;
	}

	public Vector2 getMousePlayer(int x, int y) throws NoninvertibleTransformException {
		Vector2 r = new Vector2(0, 0);
		canvasTransform.inverseTransform(new Vector2(x, y), r);
		return r;
	}

	public void paint(Graphics2D g) {

		// get the size of the canvas

		this.frameSize.width = m_frame.getWidth();
		this.frameSize.height = m_frame.getHeight();

		m_canvas.setSize(frameSize.width, frameSize.height);
		this.miniMap.setWorld(world); // Met a jour le monde dans la miniMap
		miniMap.repaint(); // Refait l'affichage

		// erase background
		g.setColor(Color.gray);

		g.fillRect(0, 0, frameSize.width, frameSize.height);

		if (world == null)
			return;

		AffineTransform baseTransform = g.getTransform();

		AffineTransform cam_save = new AffineTransform(cameraTransform);

		AffineTransform playerTransform;
		if (world.getPlayer() != null) {
			playerTransform = world.getPlayer().getTransform();
		} else {
			playerTransform = new AffineTransform();
		}

		// float angle = (float) Math.cos((System.currentTimeMillis() % 6282) / 1000.0f)
		// * 0.2f;
		// System.out.println("angle " + System.currentTimeMillis());
		cameraTransform.translate(-playerTransform.getTranslateX(), -playerTransform.getTranslateY());
		// .concatenate(AffineTransform.getRotateInstance(angle));

		g.transform(canvasTransform);

		AffineTransform screeSpace = new AffineTransform(g.getTransform());
		
		
		g.transform(cameraTransform);
		cameraTransform = cam_save;

		AffineTransform gameTransform = new AffineTransform(g.getTransform());

		drawGround(g, 20, 20, 0.5f, playerTransform);
		
		g.setTransform(gameTransform);
		
		SafeMap entities = world.getEntities();

		for (Entry<Long, SafeMapElement> entries : entities) {

			Entity et = (Entity) entries.getValue();
			Avatar av = et.getAvatar();
			g.transform(et.getTransform());

			et.getBody().debug(g);
			g.transform(localTransform);
			g.translate(-av.getSpriteW() / 2.0f, -av.getSpriteH() / 2.0f); // center
																			// the
																			// object

			av.paint(g);
			g.setTransform(gameTransform);

		}
		/*try {
			season.nextSeason();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		/**
		 * TO DECOMMENT : if you want to change season
		 */
		 //intensity = season.transitionSummerWinter(g, 500, intensity);

	}

	private void drawGround(Graphics2D g, int width, int height, float scale, AffineTransform playerTransform) {
		/*
		for (float w = -width / 2.0f; w < width / 2.0f; w += size) {
			for (float h = -height / 2.0f; h < height / 2.0f; h += size) {
				g.drawImage(season.getGround(), (int) w, (int) h, size, size, m_canvas);
			}
		}*/
		BufferedImage bg = season.getGround();
		
		
		
		int imWidth = bg.getWidth();
		int imHeight = bg.getHeight();
		float bgw = imWidth/sprite_pixels_per_unit * scale;
		float bgh = imHeight/sprite_pixels_per_unit * scale;
	
		float tX = (float) playerTransform.getTranslateX();
		float tY = (float) playerTransform.getTranslateY();
		
		float ptX = (float) (playerTransform.getTranslateX() % bgw);
		float ptY = (float) (playerTransform.getTranslateY() % bgh);
		
		g.translate(-bgw*width/2.0f + tX - ptX, -bgh*height/2.0f + tY - ptY);
		AffineTransform backgroundTransform = new AffineTransform(g.getTransform());
		//System.out.println("width " + width + " height" + heigth);
		AffineTransform lineTransform = new AffineTransform();
		AffineTransform tileTransform = null;
		for(int y = 0; y < height; y++) {
			tileTransform = new AffineTransform(lineTransform);
			for(int x = 0; x < width ; x++) {
				tileTransform.translate(bgw, 0);
				g.transform(tileTransform);
				g.transform(localTransform);
				g.translate(-imWidth / 2.0f, -imHeight / 2.0f);
				g.scale(scale, scale);
				g.drawRenderedImage(bg, identity);
				g.setTransform(backgroundTransform);
			}
			lineTransform.translate(0, bgh);
		}

	}

}
