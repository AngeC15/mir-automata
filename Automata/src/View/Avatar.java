package View;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.entities.Entity;

public class Avatar {
	Entity entity;
	AffineTransform transform;
	BufferedImage[] m_images;
	
	public Avatar(Entity e) throws IOException{
		m_images = loadSprite("Resources/winchester-4x6.png", 4, 6);
		entity = e;
		transform = e.getTransform();
		e.setAvatar(this);
	}
	void paint(Graphics2D g) {;
		BufferedImage img = m_images[0];
		g.drawRenderedImage(img, transform);
	}
	
	public static BufferedImage[] loadSprite(String filename, int nrows, int ncols) throws IOException {
	    File imageFile = new File(filename);
	    if (imageFile.exists()) {
	      BufferedImage image = ImageIO.read(imageFile);
	      int width = image.getWidth(null) / ncols;
	      int height = image.getHeight(null) / nrows;

	      BufferedImage[] images = new BufferedImage[nrows * ncols];
	      for (int i = 0; i < nrows; i++) {
	        for (int j = 0; j < ncols; j++) {
	          int x = j * width;
	          int y = i * height;
	          images[(i * ncols) + j] = image.getSubimage(x, y, width, height);
	        }
	      }
	      return images;
	    }
	    return null;
	  }
}
