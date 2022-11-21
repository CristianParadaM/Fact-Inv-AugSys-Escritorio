package test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class testGraphics extends JPanel {

	private int[] angles;
	private String[] legend;
	private int rows;
	private int totalBuys;

	public testGraphics(int[] angles, String[] legend, int totalBuys) {
		super();
		this.angles = angles;
		this.legend = legend;
		this.rows = (int)Math.round(legend.length/3.0);
		this.totalBuys = totalBuys;
		this.setOpaque(false);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.add(new testGraphics(new int[] { 90, 90, 20, 100, 60 },
				new String[] { "Componentes: 30%", "PC: 30%", "Accesorios: 30%", "Laptop: 30%", "Celulares: 30%" }, 200));
		frame.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int startAngle = 0;
		g2d.setColor(new Color(132, 47, 149));
		int height = getHeight() - 150;
		int width = getWidth();
		int dimension = height < width ? height - 50 : width - 50;
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
		x = 40;
		y += dimension+30;
		g2d.setFont(new Font("Arial", Font.BOLD, 20));
		g2d.setColor(new Color(91, 155, 213));
		actualColor = g2d.getColor();
		for (int i = 0, c = 0; i < legend.length; i++ , c++) {
			actualColor = generateColor(actualColor);
			g2d.setColor(actualColor);
			if (c==3) {
				c=0;
				x = (rows * ((getWidth()/2))+100)/2;
				y = dimension+40;
				g2d.drawString(legend[i], x, y);
			}else {
				g2d.drawString(legend[i], x, y);
			}
			y+=30;
		}
		g2d.setColor(Color.BLACK);
		g2d.drawString("Ventas realizadas: "+totalBuys, getWidth()/2 - ((getWidth()/2)/2), getHeight()-30);
		super.paint(g);
	}

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
