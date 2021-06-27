package View;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JPanel;

import Model.entities.LivingEntity;

public class LifeBar extends JComponent {

	private LivingEntity livingEntity;
	private float lifeMax;
	int x;
	int y;
	int width;
	int height;

	public LifeBar(LivingEntity entity, int x, int y, int width, int height) {
		this.livingEntity = entity;
		lifeMax = entity.getLife();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.repaint();
		this.setVisible(true);
	}

	private int getSizeBar(float life) {
		return (int) ((life / 100.0f) * width);
	}

	@Override
	public void paint(Graphics g) {
		int life = (int) ((livingEntity.getLife() / lifeMax) * 100);

		g.setColor(Color.black);
		g.drawRect(x, y + width * 2, getSizeBar(100), height);
		g.setColor(Color.white);
		g.fillRect(x, y + width * 2, getSizeBar(100), height);
		if (life < (100 * 1 / 3)) {
			g.setColor(Color.red);
		} else if (life >= (100 * 1 / 3) && life <= (100 * 2 / 3)) {
			g.setColor(Color.orange);
		} else if (life > (100 * 2 / 3)) {
			g.setColor(Color.green);
		}

		g.fillRect(x, y + width * 2, getSizeBar(life), height);

	}
}
