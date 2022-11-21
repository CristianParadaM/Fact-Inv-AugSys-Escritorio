package view.admin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
/**
 * @author CRISTIAN DAVID PARADA MARTINEZ
 */
public class JPanelPieChart extends JPanel {

	private int[] angles;
	private String[] legend;
	private int totalBuys;

	/**
	 * Constructor de JPanelPieChart
	 * @param angles angulos
	 * @param legend legenda
	 * @param totalBuys total de ventas
	 */
	public JPanelPieChart(int[] angles, String[] legend, int totalBuys) {
		super();
		this.angles = angles;
		this.legend = legend;
		this.totalBuys = totalBuys;
		this.setOpaque(false);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int startAngle = 0;
		g2d.setColor(new Color(132, 47, 149));
		int height = getHeight() - 150;
		int width = getWidth();
		int dimension = height < width ? height : width - 50;
		int x = (getWidth() / 2) - (dimension / 2);
		int y = 10;
		Color actualColor = g2d.getColor();
		for (int i = 0; i < angles.length; i++) {
			if (i == angles.length - 1) {
				angles[i] = 360 - startAngle;
			}
			g2d.fillArc(x, y, dimension, dimension, startAngle, angles[i]);
			g2d.setColor(Color.BLACK);
			g2d.setStroke(new BasicStroke(3));
			g2d.drawArc(x, y, dimension, dimension, startAngle, angles[i]);
			startAngle += angles[i];
			actualColor = generateColor(actualColor);
			g2d.setColor(actualColor);
		}
		x = (getWidth()/2 - ((getWidth()/2)/2))-50;
		y += dimension+30;
		g2d.setFont(new Font("Arial", Font.BOLD, 20));
		g2d.setColor(new Color(91, 155, 213));
		actualColor = g2d.getColor();
		for (int i = 0, c = 0; i < legend.length; i++ , c++) {
			actualColor = generateColor(actualColor);
			g2d.setColor(actualColor);
			if (c==3) {
				c=0;
				x = getWidth()/2 + 50;
				y = dimension+40;
				g2d.drawString(legend[i], x, y);
			}else {
				g2d.drawString(legend[i], x, y);
			}
			y+=30;
		}
		g2d.setColor(Color.BLACK);
		g2d.drawString("Ventas realizadas: "+totalBuys+" productos", getWidth()/2 - ((getWidth()/2)/2), getHeight()-20);
		super.paint(g);
	}

	/**
	 * Metodo que genera el siguiente color
	 * @param actualColor color actual
	 * @return siguiente color
	 */
	private Color generateColor(Color actualColor) {
		if (actualColor.getRed() == 132 && actualColor.getGreen() == 47 && actualColor.getBlue() == 149) {
			return new Color(0, 176, 80);
		} else if (actualColor.getRed() == 0 && actualColor.getGreen() == 176 && actualColor.getBlue() == 80) {
			return new Color(230, 30, 130);
		} else if (actualColor.getRed() == 230 && actualColor.getGreen() == 30 && actualColor.getBlue() == 130) {
			return new Color(255, 192, 0);
		} else if (actualColor.getRed() == 255 && actualColor.getGreen() == 192 && actualColor.getBlue() == 0) {
			return new Color(91, 155, 213);
		} else {
			return new Color(132, 47, 149);
		}
	}
}
