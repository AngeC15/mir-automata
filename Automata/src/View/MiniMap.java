package View;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.TreeMap;
import java.util.Map.Entry;


import javax.swing.JPanel;

import Model.World;
import Model.entities.Entity;

public class MiniMap {

	JPanel conteneur;
	Canvas canvas;

	
	private AffineTransform canvasTransform;
	private AffineTransform cameraTransform;
	private AffineTransform localTransform;
	private double cameraDistance = 3;
	
	public MiniMap(Dimension dimensionFrame) {

		conteneur = new JPanel(new BorderLayout());
		canvas = new Canvas();
		conteneur.add(canvas, BorderLayout.CENTER);
		conteneur.setVisible(true);
		conteneur.setBounds(5, 5, 200, 200);
		setupFrame();
	}
	
	public void setupFrame() {
		float units_per_width = 300.0f;
		float sprite_pixels_per_unit = 1.5f;
		
		float canvasScaling = conteneur.getWidth() / units_per_width; // 100.0 wide
		localTransform = AffineTransform.getScaleInstance(1 / sprite_pixels_per_unit, -1 / sprite_pixels_per_unit);
		canvasTransform = AffineTransform.getScaleInstance(canvasScaling, -canvasScaling);
		canvasTransform.concatenate(AffineTransform.getTranslateInstance(units_per_width / 2.0f,
				-(units_per_width / 2.0f) * (((float) conteneur.getHeight()) / ((float) conteneur.getWidth()))));
		cameraTransform = AffineTransform.getScaleInstance(1 / cameraDistance, 1 / cameraDistance);
		
	}

	public JPanel getPanel() {
		return conteneur;
	}

	public void paint(World world) {
		conteneur.setBounds(5, 5, 200,200);
		Graphics g2 = canvas.getGraphics();
		Graphics2D g = (Graphics2D) g2;
		
		g.fillRect(0, 0, 200, 200);

		AffineTransform baseTransform = g.getTransform();

		AffineTransform cam_save = new AffineTransform(cameraTransform);
		AffineTransform playerTransform = world.getPlayer().getTransform();
		cameraTransform.concatenate(AffineTransform.getTranslateInstance(-playerTransform.getTranslateX(),
				-playerTransform.getTranslateY()));
		g.transform(canvasTransform); // pixel au coordonées du monde
		//g.transform(cameraTransform); // vu de la caméra par rapport au monde
		cameraTransform = cam_save;

		AffineTransform gameTransform = g.getTransform();
		
		TreeMap<Long, Entity> entities = world.getEntities();
		g.setColor(Color.lightGray);
		g.fillRect(-100, -100, 200, 200);
		
		g.setColor(Color.red);
		g.fillOval(-1, 1, 5, 5);
		

		
		for (Entry<Long, Entity> entries : entities.entrySet()) {
			Entity et = entries.getValue();
			Avatar av = et.getAvatar();
			
			g.transform(et.getTransform());
			g.transform(localTransform); // Pour centrer le 0.0 au milieu
			g.transform(AffineTransform.getTranslateInstance(-av.getSpriteW() / 2.0f, -av.getSpriteH() / 2.0f)); // set
																													// 0,0																									// the
																													// object
			
			g.drawRenderedImage(av.getDefaultSprite(), new AffineTransform());
	//av.paint(g);
			/*g.setColor(Color.green);
			g.fillOval(2, 2, 15, 15);
			g.setColor(Color.black);
			g.drawOval(2, 2, 15, 15);*/
			
			g.setTransform(gameTransform);

		}

	}
}
