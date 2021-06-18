package View;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Model.World;
import Model.entities.Entity;

public class MiniMap  {

	GameCanvas m_canvas;
	JFrame frame;
	JLabel m_text;
	private long m_textElapsed;
	private AffineTransform canvasTransform;
	private AffineTransform cameraTransform;
	private AffineTransform localTransform;
	private double cameraDistance = 1;
	
	public MiniMap(GameCanvasListener listener) {
		m_canvas = new GameCanvas( listener);
		
		System.out.println("  - creating frame...");
		Dimension d = new Dimension(400, 200);
		//m_frame = m_canvas.createFrame(d);


		System.out.println("  - setting up the frame...");
		setupFrame();
	}
	
	private void setupFrame() {

		float units_per_width = 100.0f;
		float sprite_pixels_per_unit = 6.0f;
		
		/*float canvasScaling = frame.getWidth()/units_per_width; // 100.0 wide
		localTransform = AffineTransform.getScaleInstance(1/sprite_pixels_per_unit, -1/sprite_pixels_per_unit);
		canvasTransform = AffineTransform.getScaleInstance(canvasScaling, -canvasScaling);
		canvasTransform.concatenate(AffineTransform.getTranslateInstance(units_per_width/2.0f, -(units_per_width/2.0f)*(((float)frame.getHeight())/((float)frame.getWidth()))));
		cameraTransform = AffineTransform.getScaleInstance(1/cameraDistance, 1/cameraDistance);*/
		frame.setTitle("MiniMap");
		frame.setLayout(new BorderLayout());

		frame.add(m_canvas, BorderLayout.CENTER);

		m_text = new JLabel();
		m_text.setText("Tick: 0ms FPS=0");
		frame.add(m_text, BorderLayout.NORTH);

		// center the window on the screen
		
	}
	
	/*public void paint(Graphics2D g, World world) {
		// get the size of the canvas
		int width = m_canvas.getWidth();
		int height = m_canvas.getHeight();
		
		// erase background
		g.setColor(Color.gray);
		g.fillRect(0, 0, width, height);
				
		AffineTransform baseTransform = g.getTransform();
		
		AffineTransform cam_save = new AffineTransform(cameraTransform);
		AffineTransform playerTransform = world.getPlayer().getTransform();
		cameraTransform.concatenate(AffineTransform.getTranslateInstance(-playerTransform.getTranslateX(), -playerTransform.getTranslateY()));
		g.transform(canvasTransform);
		g.transform(cameraTransform);
		cameraTransform = cam_save;
		
		
		AffineTransform gameTransform = g.getTransform();
		//draw
		
		TreeMap<Long, Entity> entities = world.getEntities();
		
		g.setColor(Color.darkGray);
		g.setStroke(new BasicStroke(0.2f));
		
		for(int i=-100; i < 100; i+=5) {
			for(int j=-100; j < 100; j+=5) {
				g.draw(new Line2D.Float((float)i, -100.0f, (float)i, 100.0f));
				g.draw(new Line2D.Float(-100.0f, (float)i, 100.0f, (float)i));
			}
		}
		g.setColor(Color.red);
		g.draw(new Ellipse2D.Float(-0.5f, -0.5f, 1, 1));
		for(Entry<Long, Entity> entries : entities.entrySet()) {
			Entity et = entries.getValue();
			Avatar av = et.getAvatar();
			g.transform(et.getTransform());
			g.transform(localTransform);
			g.transform(AffineTransform.getTranslateInstance(-av.getSpriteW()/2.0f, -av.getSpriteH()/2.0f)); //set 0,0 to the center of the object
			av.paint(g);
			
			g.setTransform(gameTransform);
			
			
		}
		
		
		//g.setTransform(baseTransform);
		
	}*/
}
