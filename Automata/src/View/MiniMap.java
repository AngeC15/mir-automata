package View;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Model.World;

public class MiniMap  {

	JPanel conteneur;
	Canvas canvas;
	
	public MiniMap(Dimension dimensionFrame) {
		
		conteneur = new JPanel(new BorderLayout());
		conteneur.setSize(500, 1000);
		canvas = new Canvas();
		canvas.setBackground(Color.blue);
		conteneur.add(canvas, BorderLayout.CENTER);
		conteneur.setVisible(true);
		conteneur.setBounds(5, 5, dimensionFrame.width / 5, dimensionFrame.height / 5);
	}
	
	public JPanel getPanel() {
		return conteneur;
	}
	
	public void paint(Graphics2D g, World world, Dimension dimensionFrame) {
		conteneur.setBounds(5, 5, dimensionFrame.width / 5, dimensionFrame.height / 5);

		// erase background
		g.setColor(Color.gray);
		g.fillRect(0, 0, dimensionFrame.width, dimensionFrame.height);

		
	}
}
