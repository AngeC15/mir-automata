package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Menu extends JPanel implements ActionListener {

	private GameView gameView;
	private JButton play, settings;
	private JLabel titre;
	// private JPanel center;
	// private Settings pageSettings;

	public Menu(Dimension frameSize, GameView gameView) {
		super(new GridLayout(3, 1));

		this.setBounds(0, 0, frameSize.width, frameSize.height);

		this.gameView = gameView;

		ImageIcon img = new ImageIcon("Resources/Menu/Title.png");
		titre = new JLabel(img, SwingConstants.CENTER);

		this.add(titre);

		initButtonPlay();

		this.setVisible(true);
		// lf = new LifeBar();
		// this.add(lf);
		// lf.repaint();
		this.repaint();

	}

	private void initButtonPlay() {
		JPanel panel = new JPanel(new FlowLayout());

		panel.setOpaque(false);
		play = new JButton();

		play.setOpaque(false);
		play.setContentAreaFilled(false);
		play.setBorderPainted(false);

		ImageIcon img = new ImageIcon("Resources/Menu/Play.png");
		play.setIcon(img);
		play.setName("buttonPlay");

		// this.add(play);
		panel.add(play);

		play.addActionListener(this);
		play.setCursor(new Cursor(Cursor.HAND_CURSOR));
		play.setBounds(100, 100, 100, 100);
		this.add(panel);
		panel.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == play) {
			System.out.println("Click play");
			gameView.setupGame();
		} else if (e.getSource() == settings) {
			System.out.println("Click settings");
			// pageSettings.initSettings();
		}
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		File imageFile = new File("Resources/Menu/menuDepart.png");

		if (imageFile.exists()) {
			BufferedImage image;
			try {
				image = ImageIO.read(imageFile);
				g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
