package View;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Map.Entry;

import javax.swing.JPanel;

import Model.World;
import Model.entities.Entity;
import Utils.SafeMap;
import Utils.SafeMapElement;

public class MiniMap extends JPanel {
	
	World world ;

	
	private AffineTransform canvasTransform;
	private AffineTransform cameraTransform;
	private AffineTransform localTransform;
	private double cameraDistance = 3;
	
	public MiniMap(Dimension dimensionFrame) {

		super(new BorderLayout());
		//canvas = new Canvas();
		//conteneur.add(canvas, BorderLayout.CENTER);
		this.setVisible(true);
		this.setBounds(5, 5, 200, 200);

		setupFrame();
	}
	
	public void setupFrame() {

		float units_per_width = 225.0f;
		float sprite_pixels_per_unit = 1.5f;

		
		float canvasScaling = this.getWidth() / units_per_width; // 100.0 wide
		localTransform = AffineTransform.getScaleInstance(1 / sprite_pixels_per_unit, -1 / sprite_pixels_per_unit);
		canvasTransform = AffineTransform.getScaleInstance(canvasScaling, -canvasScaling);
		canvasTransform.concatenate(AffineTransform.getTranslateInstance(units_per_width / 2.0f,
				-(units_per_width / 2.0f) * (((float) this.getHeight()) / ((float) this.getWidth()))));
		cameraTransform = AffineTransform.getScaleInstance(1 / cameraDistance, 1 / cameraDistance);
		
	}


	public void setWorld(World world) {
		this.world = world;
	}
	@Override
	public void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		this.setBounds(5, 5, 200,200);
		//Graphics g2 = this.getGraphics();
		Graphics2D g = (Graphics2D) g2;
		
		g.fillRect(0, 0, 200, 200);

		AffineTransform baseTransform = g.getTransform();


		AffineTransform cam_save = new AffineTransform(cameraTransform);
		AffineTransform playerTransform;
		if (world.getPlayer() != null) {
			playerTransform = world.getPlayer().getTransform();
		} else {
			playerTransform = new AffineTransform();
		}
		cameraTransform.concatenate(AffineTransform.getTranslateInstance(-playerTransform.getTranslateX(),
				-playerTransform.getTranslateY()));
		g.transform(canvasTransform); // pixel au coordonées du monde

		cameraTransform = cam_save;

		AffineTransform gameTransform = g.getTransform();
		
		SafeMap entities = world.getEntities();
		g.setColor(Color.lightGray);
		g.fillRect(-100, -100, 200, 200);
		
		g.setColor(Color.red);
		g.fillOval(-1, 1, 5, 5);
		
		for (Entry<Long, SafeMapElement> entries : entities) {
			Entity et = (Entity) entries.getValue();
			Avatar av = et.getAvatar();
			g.transform(et.getTransform());
			// et.getBody().debug(g);
			g.transform(localTransform);
			g.transform(AffineTransform.getTranslateInstance(-av.getSpriteW() / 2.0f, -av.getSpriteH() / 2.0f)); // center
																													// the
																													// object
			g.drawRenderedImage(av.getDefaultSprite(), new AffineTransform());	
			g.setTransform(gameTransform);

		}

	}
}
