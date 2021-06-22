package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.Map.Entry;

import javax.swing.JPanel;

import Model.World;
import Model.entities.Entity;
import Model.physics.primitives.Circle;
import Utils.SafeMap;
import Utils.SafeMapElement;

public class MiniMap extends JPanel {

	private World world;

	private AffineTransform canvasTransform;
	private AffineTransform cameraTransform;
	private AffineTransform localTransform;
	private double cameraDistance = 3;
	private float size;

	public MiniMap() {

		super(new BorderLayout());
		this.setVisible(true);
		this.setBounds(5, 5, 200, 200);
		size = 12;
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
		this.setBounds(5, 5, 200, 200);
		// Graphics g2 = this.getGraphics();
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
		g.setColor(Color.white);
		g.fillRect(-100, -100, 200, 200);

		for (Entry<Long, SafeMapElement> entries : entities) {
			Entity et = (Entity) entries.getValue();
			Avatar av = et.getAvatar();

			g.transform(et.getTransform());
			g.transform(localTransform);
			g.transform(AffineTransform.getTranslateInstance(-size / 2f, -size / 2f)); // center
																						// the

			
			Color c = et.getColor();
			if (c != null) {
				g.setColor(c);
				g.fillOval(-1, 1, (int) size, (int) size);
			}
			g.setTransform(gameTransform);

		}

	}

}
