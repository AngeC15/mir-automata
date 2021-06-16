package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Model.World;
import Model.entities.Entity;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
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
	
	public GameView(GameCanvasListener listener) {
		m_canvas = new GameCanvas(listener);
		
		System.out.println("  - creating frame...");
		Dimension d = new Dimension(1024, 768);
		m_frame = m_canvas.createFrame(d);

		System.out.println("  - setting up the frame...");
		setupFrame();
	}
	
	/*
	 * Then it lays out the frame, with a border layout, adding a label to the north
	 * and the game canvas to the center.
	 */
	private void setupFrame() {
		
		//canvasTransform = AffineTransform.getScaleInstance(m_frame.getWidth(), -m_frame.getHeight());
		float canvasScaling = m_frame.getWidth()/100.0f; // 100.0 wide
		localTransform = AffineTransform.getScaleInstance(1/6.0f, -1/6.0f);
		canvasTransform = AffineTransform.getScaleInstance(canvasScaling, -canvasScaling);
		cameraTransform = AffineTransform.getScaleInstance(1/cameraDistance, 1/cameraDistance);
		m_frame.setTitle("Game");
		m_frame.setLayout(new BorderLayout());

		m_frame.add(m_canvas, BorderLayout.CENTER);

		m_text = new JLabel();
		m_text.setText("Tick: 0ms FPS=0");
		m_frame.add(m_text, BorderLayout.NORTH);

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
		int width = m_canvas.getWidth();
		int height = m_canvas.getHeight();
		
		// erase background
		g.setColor(Color.gray);
		g.fillRect(0, 0, width, height);
				
		AffineTransform baseTransform = g.getTransform();
		
		g.transform(cameraTransform);
		g.transform(canvasTransform);
		
		AffineTransform gameTransform = g.getTransform();
		//draw
		
		Ellipse2D.Float e = new Ellipse2D.Float();
		e.height = 1.0f;
		e.width = 1.0f;
		g.draw(e);
		TreeMap<Long, Entity> entities = world.getEntities();
		
		for(Entry<Long, Entity> entries : entities.entrySet()) {
			g.transform(entries.getValue().getTransform());
			g.transform(localTransform);
			entries.getValue().getAvatar().paint(g);
			g.setTransform(gameTransform);
		}
		
		g.setTransform(gameTransform);
		
		g.setTransform(baseTransform);
		
	}
	
}
