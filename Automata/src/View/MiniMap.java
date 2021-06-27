package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.Map.Entry;

import javax.swing.JPanel;

import Model.World;
import Model.entities.Entity;
import Model.entities.Player;
import Utils.Functions;
import Utils.SafeMap;
import Utils.SafeMapElement;

public class MiniMap extends JPanel {

	private World world;
	private float game_w;
	private float game_h;
	private AffineTransform canvasTransform;
	private AffineTransform localTransform;

	private static final float arrowScaling = 2;
	private static final float[] arrowX = new float[] { -arrowScaling, 0, arrowScaling, 0 };
	private static final float[] arrowY = new float[] { -arrowScaling, arrowScaling, -arrowScaling, -arrowScaling/2 };
	private static final Rectangle2D.Float square = new Rectangle2D.Float(-0.5f, -0.5f, 1, 1); 
	private static final Ellipse2D.Float circle = new Ellipse2D.Float(-0.5f, -0.5f, 1, 1);
	private static Path2D.Float arrow;
	

	public MiniMap() {
		super(new BorderLayout());
		this.setVisible(true);
		this.setBounds(5, 5, 200, 200);
		game_w = 100.0f;
		game_h = 100.0f;
		arrow = new Path2D.Float();
		arrow.moveTo(arrowX[0], arrowY[0]);
		for(int i=0; i < arrowX.length; i++) {
			arrow.lineTo(arrowX[i], arrowY[i]);
		}
		arrow.closePath();
	
		setupFrame();
	}

	public void setupFrame() {
		float local_scaling = 4f;
		float canvasScaling = this.getWidth() / game_w; // 100.0 wide
		localTransform = AffineTransform.getScaleInstance(local_scaling, local_scaling);
		canvasTransform = AffineTransform.getScaleInstance(canvasScaling, -canvasScaling);
		canvasTransform.concatenate(AffineTransform.getTranslateInstance(game_w / 2.0f,
				-(game_w / 2.0f) * (((float) this.getHeight()) / ((float) this.getWidth()))));

	}
	public void setDims(float w, float h) {
		game_w = w;
		game_h = h;
		setupFrame();
	}
	public void setWorld(World world) {
		this.world = world;
	}

	@Override
	public void paintComponent(Graphics g2) {
		this.setBounds(5, 5, 200, 200);
		super.paintComponent(g2);
		
		// Graphics g2 = this.getGraphics();
		Graphics2D g = (Graphics2D) g2;

		AffineTransform baseTransform = g.getTransform();

		AffineTransform playerTransform;
		if (world.getPlayer() != null) {
			playerTransform = world.getPlayer().getTransform();
		} else {
			playerTransform = new AffineTransform();
		}

		g.transform(canvasTransform); // pixel au coordon�es du monde

		AffineTransform gameTransform = g.getTransform();

		SafeMap entities = world.getEntities();

		g.setColor(new Color(220, 220, 220));
		
		g.scale(game_w, game_h);
		g.draw(square);
		g.setTransform(gameTransform);
		
		for (Entry<Long, SafeMapElement> entries : entities) {
			Entity et = (Entity) entries.getValue();
	
			float tx = (float) et.getTransform().getTranslateX();
			float ty = (float) et.getTransform().getTranslateY();
	
			g.translate(-tx, -ty);
			g.translate(Functions.pfmod((tx+game_w/2.0f), game_w) - game_w/2.0f, Functions.pfmod((ty+game_h/2.0f), game_h) - game_h/2.0f);
			g.transform(et.getTransform());
			g.transform(localTransform);

			Color c = et.getColor();

			if (c != null) {
				g.setColor(c);
				if (et.addLifeBar()&& et instanceof Player) {
					g.fill(arrow);
					//g.fillPolygon(triangleX, triangleY, 4);
				} else if (et.getEquipe() == 4) {
					g.draw(circle);
				}else {
					g.draw(square);
				}
			}
			g.setTransform(gameTransform);

		}

	}

}
