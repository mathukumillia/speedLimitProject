import java.awt.*;
import javax.swing.*;
import java.awt.geom.Line2D;

public class SpeedometerLabel extends JLabel {
	
	private int centerX;
	private int centerY;
	private int endX;
	private int endY;

	public SpeedometerLabel(ImageIcon a,int cX, int cY, int initX, int initY){
		super(a);
		this.centerX = cX;
		this.centerY = cY;
		this.endX = initX;
		this.endY = initY;
	}

	public void refresh(int x, int y){
		this.endX = x;
		this.endY = y;
		repaint();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(6));
		g2.setColor(Color.red);
		g2.draw(new Line2D.Float(centerX, centerY, endX, endY));
	}

}	