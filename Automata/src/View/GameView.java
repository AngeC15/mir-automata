package View;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import Model.World;
import Model.entities.Entity;

import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Float;
import java.awt.geom.Line2D;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Map.Entry;

public class GameView {

	GameCanvas m_canvas;
	JFrame m_frame;
	JLabel m_text;
	private long m_textElapsed;
	private AffineTransform canvasTransform;
	private AffineTransform cameraTransform;
	private AffineTransform localTransform;
	private double cameraDistance = 1;
	private MiniMap miniMap;
	private Dimension FrameSize;

	public GameView(GameCanvasListener listener) {
		m_canvas = new GameCanvas(listener);

		System.out.println("  - creating frame...");
		FrameSize = new Dimension(1024, 768);
		m_frame = m_canvas.createFrame(FrameSize);
		// miniMap = new MiniMap(listener, m_frame);

		System.out.println("  - setting up the frame...");
		setupFrame();
	}

	/*
	 * Then it lays out the frame, with a border layout, adding a label to the north
	 * and the game canvas to the center.
	 */
	private void setupFrame() {
		float units_per_width = 100.0f;
		float sprite_pixels_per_unit = 6.0f;

		float canvasScaling = m_frame.getWidth() / units_per_width; // 100.0 wide
		localTransform = AffineTransform.getScaleInstance(1 / sprite_pixels_per_unit, -1 / sprite_pixels_per_unit);
		canvasTransform = AffineTransform.getScaleInstance(canvasScaling, -canvasScaling);
		canvasTransform.concatenate(AffineTransform.getTranslateInstance(units_per_width / 2.0f,
				-(units_per_width / 2.0f) * (((float) m_frame.getHeight()) / ((float) m_frame.getWidth()))));
		cameraTransform = AffineTransform.getScaleInstance(1 / cameraDistance, 1 / cameraDistance);
		m_frame.setTitle("Game");
		m_frame.setLayout(new BorderLayout());

		m_text = new JLabel();
		m_text.setText("Tick: 0ms FPS=0");
		m_frame.add(m_text, BorderLayout.NORTH);

		// Création du carré correspondant à la miniMap
		JPanel miniMap = new JPanel();
		miniMap.setSize(500, 1000);
		miniMap.setBackground(Color.RED);
		miniMap.setVisible(true);
		miniMap.setBounds(5, 5, this.FrameSize.width / 5, this.FrameSize.height / 5);

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

	public void paint(Graphics2D g, World world) {
		// get the size of the canvas
		
		this.FrameSize.width = m_frame.getWidth();
		this.FrameSize.height = m_frame.getHeight();
		
		m_canvas.setSize(FrameSize.width, FrameSize.height);

		// erase background
		g.setColor(Color.gray);
		g.fillRect(0, 0, FrameSize.width, FrameSize.height);

		AffineTransform baseTransform = g.getTransform();

		AffineTransform cam_save = new AffineTransform(cameraTransform);
		AffineTransform playerTransform = world.getPlayer().getTransform();
		cameraTransform.concatenate(AffineTransform.getTranslateInstance(-playerTransform.getTranslateX(),
				-playerTransform.getTranslateY()));
		g.transform(canvasTransform);
		g.transform(cameraTransform);
		cameraTransform = cam_save;

		AffineTransform gameTransform = g.getTransform();
		// draw

		TreeMap<Long, Entity> entities = world.getEntities();

		g.setColor(Color.darkGray);
		g.setStroke(new BasicStroke(0.2f));

		for (int i = -100; i < 100; i += 5) {
			for (int j = -100; j < 100; j += 5) {
				g.draw(new Line2D.Float((float) i, -100.0f, (float) i, 100.0f));
				g.draw(new Line2D.Float(-100.0f, (float) i, 100.0f, (float) i));
			}
		}
		g.setColor(Color.red);
		g.draw(new Ellipse2D.Float(-0.5f, -0.5f, 1, 1));
		for (Entry<Long, Entity> entries : entities.entrySet()) {
			Entity et = entries.getValue();
			Avatar av = et.getAvatar();
			g.transform(et.getTransform());
			g.transform(localTransform);
			g.transform(AffineTransform.getTranslateInstance(-av.getSpriteW() / 2.0f, -av.getSpriteH() / 2.0f)); // set
																													// 0,0
																													// to
																													// the
																													// center
																													// of
																													// the
																													// object
			av.paint(g);

			g.setTransform(gameTransform);

		}

		// g.setTransform(baseTransform);

	}

}
