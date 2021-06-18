package View;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.swing.JPanel;

import Model.World;
import Model.entities.Entity;

public class MiniMap {

	JPanel conteneur;
	Canvas canvas;

	public MiniMap(Dimension dimensionFrame) {

		conteneur = new JPanel(new BorderLayout());
		conteneur.setSize(500, 1000);
		canvas = new Canvas();
		canvas.setBackground(Color.white);
		conteneur.add(canvas, BorderLayout.CENTER);
		conteneur.setVisible(true);
		conteneur.setBounds(5, 5, dimensionFrame.width / 5, dimensionFrame.height / 5);
	}

	public JPanel getPanel() {
		return conteneur;
	}

	public void paint(World world, Dimension dimensionFrame) {
		conteneur.setBounds(5, 5, dimensionFrame.width / 5, dimensionFrame.height / 5);
		Graphics g2 = canvas.getGraphics();
		Graphics2D g = (Graphics2D) g2;
		g2.drawOval(20, 20, 10, 10);

		TreeMap<Long, Entity> entities = world.getEntities();
		for (Entry<Long, Entity> entries : entities.entrySet()) {
			Entity et = entries.getValue();
			Avatar av = et.getAvatar();

			av.paint(g);

		}

	}
}
