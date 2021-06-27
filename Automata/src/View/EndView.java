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

public class EndView extends JPanel implements ActionListener {
	private GameView gameView;
	private JButton end, settings;
	private JLabel titre;
	// private JPanel center;
	// private Settings pageSettings;

	public EndView(Dimension frameSize, GameView gameView) {
		super(new GridLayout(3, 1));

		this.setBounds(0, 0, frameSize.width, frameSize.height);

		this.gameView = gameView;

		ImageIcon img = new ImageIcon("Resources/Menu/Title.png");
		titre = new JLabel(img, SwingConstants.CENTER);

		this.add(titre);

		initButtonPlay();

		
		this.setVisible(false);
		this.repaint();

	}

	private void initButtonPlay() {
		JPanel panel = new JPanel(new FlowLayout());

		panel.setOpaque(false);
		end = new JButton();

		end.setOpaque(false);
		end.setContentAreaFilled(false);
		end.setBorderPainted(false);

		ImageIcon img = new ImageIcon("Resources/Menu/end.png");
		end.setIcon(img);
		end.setName("buttonEnd");

		// this.add(play);
		panel.add(end);
		end.addActionListener(this);
		end.setCursor(new Cursor(Cursor.HAND_CURSOR));
		end.setBounds(100, 100, 100, 100);
		this.add(panel);
		panel.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("_______________");
		//System.out.println(e.getActionCommand());
		/*
		if(e.getActionCommand()) {
			System.out.println("Bad finishing");
			System.exit(0);
		}
		*/
		if (e.getSource() == end) {
			//System.out.println("Click Finish");
			System.exit(0);
		} else if (e.getSource() == settings) {
			System.out.println("Click settings");
		}
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		
		File imageFile = new File("Resources/Menu/fond.jpg");

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
