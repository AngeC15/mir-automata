package View;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private BufferedImage[] m_images;
	private int nbSprite;

	/**
	 * Construct a sprite-sheet from a given file path
	 * @param filePath
	 * @param nrows
	 * @param ncols
	 * @param nbSprite 
	 * @throws IOException
	 */
	SpriteSheet(String filePath, int nrows, int ncols, int nbSprite) throws IOException {
		this.nbSprite = nbSprite;
		m_images = loadSprite(filePath, nrows, ncols);
	}

	/**
	 * Load a sprite-sheet and decompose it into an array of single sprite
	 * @param filename
	 * @param nrows
	 * @param ncols
	 * @return an array of single sprite
	 * @throws IOException
	 */
	private BufferedImage[] loadSprite(String filename, int nrows, int ncols) throws IOException {
		File imageFile = new File(filename);
		if (imageFile.exists()) {
			BufferedImage image = ImageIO.read(imageFile);
			int width = image.getWidth(null) / ncols;
			int height = image.getHeight(null) / nrows;

			BufferedImage[] images = new BufferedImage[nbSprite];
			for (int i = 0; i < nrows; i++) {
				for (int j = 0; j < ncols; j++) {
					if (((i * ncols) + j) < nbSprite) {
						int x = j * width;
						int y = i * height;
						images[(i * ncols) + j] = image.getSubimage(x, y, width, height);
					}
				}
			}
			return images;
		}else {
			throw new FileNotFoundException("SpriteSheet not found");
		}
	}

	/**
	 * Get the sprite a the given index
	 * @param index
	 * @return the selected sprite
	 */
	public BufferedImage getSprite(int index) {
		if (index < nbSprite && index >= 0) {
			return m_images[index];
		} else {
			throw new ArrayIndexOutOfBoundsException("invalid sprite index");
		}
	}

}
