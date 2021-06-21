package View;


import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map.Entry;

import javax.swing.JPanel;

import Model.World;
import Model.entities.Entity;
import Utils.SafeMap;
import Utils.SafeMapElement;

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

		float units_per_width = 225.0f;
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
		AffineTransform playerTransform;
		if (world.getPlayer() != null) {
			playerTransform = world.getPlayer().getTransform();
		} else {
			playerTransform = new AffineTransform();
		}
		cameraTransform.concatenate(AffineTransform.getTranslateInstance(-playerTransform.getTranslateX(),
				-playerTransform.getTranslateY()));
		g.transform(canvasTransform); // pixel au coordon�es du monde

		cameraTransform = cam_save;

		AffineTransform gameTransform = g.getTransform();
		
		SafeMap entities = world.getEntities();
		//g.setColor(Color.lightGray);
		//g.fillRect(-100, -100, 200, 200);
		
		g.setColor(Color.red);
		g.fillOval(-1, 1, 5, 5);
		
		//drawing background
		SpriteSheet sp = null;
		BufferedImage image = null;
		try {
			sp = new SpriteSheet("Resources/sprite_sheet_decor.png", 3, 5, 15);
				image = sp.getSprite(0);
				
				//pour retourner l'image
				AffineTransform aT = AffineTransform.getScaleInstance(1, -1);
				aT.translate(0, -image.getHeight());
				AffineTransformOp op = new AffineTransformOp(aT, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
				image = op.filter(image, null);
				
				int width  = this.conteneur.getWidth();
				int heigth = this.conteneur.getHeight();
				//System.out.println("width " + width + " height" + heigth);
				int imwidth = image.getWidth();
				int imHeigth = image.getHeight();
				for(int x = -width; x < width; x+=imwidth) {
						for(int z = - heigth; z < heigth ; z+=imHeigth) {
							g.drawImage(image, x,z, this.conteneur);
						}
				}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
