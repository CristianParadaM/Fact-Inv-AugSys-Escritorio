package view.admin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

/**
 * @author CRISTIAN DAVID PARADA MARTINEZ
 */
public class JButtonRound extends JButton{
	
	private boolean isPreesed;
	
	/**
	 * Constructor de JButtonRound
	 */
	public JButtonRound() {
		super();
		init();
	}
	
	/**
	 * Metodo que inicializa las propiedades de este boton
	 */
	private void init() {
		this.setSize(new Dimension(10,10));
		this.setPreferredSize(new Dimension(10,10));
		this.setContentAreaFilled(false);
		this.setRolloverEnabled(false);
		this.setFocusable(false);
		this.setBorderPainted(false);
	}
	
	/**
	 * Metodo que cambia si esta presionado
	 * @param isPreesed true o false
	 */
	public void setPreesed(boolean isPreesed) {
		this.isPreesed = isPreesed;
		repaint();
	}
	
	/**
	 * Metodo que retorna la variable isPresed
	 * @return true o false
	 */
	public boolean isPreseed() {
		return isPreesed;
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		Graphics2D g = (Graphics2D)graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setStroke(new BasicStroke(2));
		if (isPreesed) {
			g.setColor(new Color(48, 116, 180));
			g.fillOval(15, 0, 10, getHeight()-1);
			g.setColor(Color.BLACK);
			g.drawOval(15, 0, 10, getHeight()-1);
		}else {
			g.setColor(Color.BLACK);
			g.drawOval(15, 0, 10, getHeight()-1);
		}
		super.paintComponent(graphics);
	}
}
