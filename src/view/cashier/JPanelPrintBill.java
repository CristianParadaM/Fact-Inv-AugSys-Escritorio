package view.cashier;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPanelPrintBill extends JPanel {

	private static final Color COLOR_BLUE = new Color(48, 116, 180);
	private static final Color COLOR_BLACK = Color.BLACK;
	private static final int WIDTH_PAPER = 2200;
	private static int HEIGTH_PAPER = 1330;
	private static final Font FONT_ARIAL_BLACK = new Font("Arial Rounded MT Bold", Font.BOLD, 40);
	private static final Font FONT_ARIAL = new Font("Arial", Font.ITALIC, 38);
	private static final long serialVersionUID = 1L;
	private Object[] infoTableBill;
	private Object[] infoStore;
	private Object[] infoTotalPay;
	private ArrayList<Object[]> infoProducts;

	/**
	 * Constructor de JPanelPrintBill
	 * @param infoBill informacion de la factura
	 * @param nameFile nombre del archivo
	 */
	@SuppressWarnings("unchecked")
	public JPanelPrintBill(Object[] infoBill, String nameFile) {
		super();
		this.infoTableBill = (Object[]) infoBill[0];
		this.infoProducts = (ArrayList<Object[]>) infoBill[1];
		this.infoStore = (Object[]) infoBill[2];
		this.infoTotalPay = (Object[]) infoBill[3];
		init(nameFile);
	}

	/**
	 * Metodo que inicializa este panel
	 * @param nameFile nombre del archivo
	 */
	private void init(String nameFile) {
		this.setSize(new Dimension(WIDTH_PAPER, HEIGTH_PAPER));
		this.setPreferredSize(new Dimension(WIDTH_PAPER, HEIGTH_PAPER));
		if (this.infoProducts.size() > 5) {
			HEIGTH_PAPER += (infoProducts.size() - 5) * 60;
		}
		this.setPreferredSize(new Dimension(WIDTH_PAPER, HEIGTH_PAPER));
		this.setSize(new Dimension(WIDTH_PAPER, HEIGTH_PAPER));
		BufferedImage bi = new BufferedImage(WIDTH_PAPER, HEIGTH_PAPER, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		this.paint(g);
		g.dispose();
		try {
			ImageIO.write(bi, "png", new File("data/" + nameFile + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que pinta la factura
	 */
	@Override
	public void paint(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		// DEFINICION DE LA HOJA
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(COLOR_BLUE);
		g.setStroke(new BasicStroke(20));
		g.drawRect(0, 0, getWidth(), getHeight());
		g.setStroke(new BasicStroke(5));
		// 1ER BLOQUE
		g.setFont(FONT_ARIAL_BLACK);
		g.drawString("Fecha:", 50, 70);
		g.setColor(COLOR_BLACK);
		g.drawString((String) infoTableBill[0], 200, 70);
		g.drawString("Factura N." + (int) infoTableBill[1], 1740, 70);
		g.setColor(COLOR_BLUE);
		g.drawLine(50, 100, 2150, 100);
		// 2o BLOQUE
		g.drawString("Empresa:", 50, 170);
		g.setColor(COLOR_BLACK);
		g.setFont(FONT_ARIAL);
		g.drawString((String) infoStore[0], 270, 170);
		g.setColor(COLOR_BLUE);
		g.setFont(FONT_ARIAL_BLACK);
		g.drawString("Dirección:", 1100, 170);
		g.setColor(COLOR_BLACK);
		g.setFont(FONT_ARIAL);
		g.drawString((String) infoStore[1], 1330, 170);
		g.setColor(COLOR_BLUE);
		g.setFont(FONT_ARIAL_BLACK);
		g.drawString("Correo:", 88, 230);
		g.setColor(COLOR_BLACK);
		g.setFont(FONT_ARIAL);
		g.drawString((String) infoStore[2], 270, 230);
		g.setColor(COLOR_BLUE);
		g.setFont(FONT_ARIAL_BLACK);
		g.drawString("Teléfono:", 1122, 230);
		g.setColor(COLOR_BLACK);
		g.setFont(FONT_ARIAL);
		g.drawString((long) infoStore[3] + "", 1330, 230);
		g.setColor(COLOR_BLUE);
		g.setFont(FONT_ARIAL_BLACK);
		g.drawLine(50, 260, 2150, 260);
		// 3er BLOQUE
		g.drawString("Cliente:", 88, 340);
		g.setColor(COLOR_BLACK);
		g.setFont(FONT_ARIAL);
		g.drawString((String) infoTableBill[2], 270, 340);
		g.setColor(COLOR_BLUE);
		g.setFont(FONT_ARIAL_BLACK);
		g.drawString("Cajero:", 1158, 340);
		g.setColor(COLOR_BLACK);
		g.setFont(FONT_ARIAL);
		g.drawString((String) infoTableBill[5], 1330, 340);
		g.setColor(COLOR_BLUE);
		g.setFont(FONT_ARIAL_BLACK);
		if (((String) infoTableBill[3]).equals("CC")) {
			g.drawString((String) infoTableBill[3] + ":", 170, 390);
		} else {
			g.drawString((String) infoTableBill[3] + ":", 163, 390);
		}
		g.setColor(COLOR_BLACK);
		g.setFont(FONT_ARIAL);
		g.drawString((String) infoTableBill[4], 270, 390);
		g.setColor(COLOR_BLUE);
		g.setFont(FONT_ARIAL_BLACK);
		g.drawString("CC:", 1234, 390);
		g.setColor(COLOR_BLACK);
		g.setFont(FONT_ARIAL);
		g.drawString((String) infoTableBill[6], 1330, 390);
		g.setColor(COLOR_BLUE);
		g.setFont(FONT_ARIAL_BLACK);
		g.drawLine(50, 420, 2150, 420);
		// 4o BLOQUE
		g.drawString("Tipo:", 145, 490);
		g.setColor(COLOR_BLACK);
		g.setFont(FONT_ARIAL);
		g.drawString((String) infoTableBill[7], 270, 490);
		g.setColor(COLOR_BLUE);
		g.setFont(FONT_ARIAL_BLACK);
		g.drawString("Pago:", 1195, 490);
		g.setColor(COLOR_BLACK);
		g.setFont(FONT_ARIAL);
		g.drawString((String) infoTableBill[8], 1330, 490);
		g.setColor(COLOR_BLUE);
		// 5o BLOQUE FACTURA
		g.drawLine(50, 520, 2150, 520);
		g.drawLine(50, 580, 2150, 580);
		g.drawLine(50, 520, 50, 580);
		g.drawLine(2150, 520, 2150, 580);
		g.drawString("Código", 60, 560);
		g.drawLine(250, 520, 250, 580);
		g.drawString("Cantidad", 260, 560);
		g.drawLine(450, 520, 450, 580);
		g.drawString("Descripción", 460, 560);
		g.drawLine(1500, 520, 1500, 580);
		g.drawString("Precio Unitario($)", 1510, 560);
		g.drawLine(1850, 520, 1850, 580);
		g.drawString("Precio Total($)", 1860, 560);
		g.setColor(COLOR_BLACK);
		DecimalFormat formato = new DecimalFormat("##,###.00");
		int xCoord = 60, yCoord = 620;
		for (int i = 0; i < infoProducts.size(); i++) {
			g.drawString((int) infoProducts.get(i)[0] + "", xCoord, yCoord);
			xCoord = 260;
			g.drawString((int) infoProducts.get(i)[1] + "", xCoord, yCoord);
			xCoord = 460;
			g.drawString((String) infoProducts.get(i)[2], xCoord, yCoord);
			xCoord = 1510;
			g.drawString(formato.format((double) infoProducts.get(i)[3]), xCoord, yCoord);
			xCoord = 1860;
			g.drawString(formato.format((double) infoProducts.get(i)[4]), xCoord, yCoord);
			xCoord = 60;
			yCoord += 60;
		}
		yCoord -= 50;
		g.setColor(COLOR_BLUE);
		g.drawLine(50, yCoord, 2150, yCoord);

		g.drawLine(50, 580, 50, yCoord);
		g.drawLine(2150, 580, 2150, yCoord);
		g.drawLine(250, 580, 250, yCoord);
		g.drawLine(450, 580, 450, yCoord);
		g.drawLine(1500, 580, 1500, yCoord);
		g.drawLine(1850, 580, 1850, yCoord);

		g.drawLine(50, yCoord, 50, yCoord + 190);
		g.drawLine(1500, yCoord, 1500, yCoord + 190);
		g.drawLine(1850, yCoord, 1850, yCoord + 190);
		g.drawLine(2150, yCoord, 2150, yCoord + 190);

		g.drawString("SubTotal $", 1647, yCoord + 60);
		g.drawString("IVA (" + (int) infoStore[4] + ") $", 1656, yCoord + 120);
		g.drawString("Total $", 1710, yCoord + 180);
		g.drawLine(50, yCoord + 190, 2150, yCoord + 190);

		g.setColor(COLOR_BLACK);
		g.setFont(new Font("Arial", Font.ITALIC, 35));
		g.drawString(infoTotalPay[0] + "", 1860, yCoord + 60);
		g.drawString(infoTotalPay[1] + "", 1860, yCoord + 120);
		g.drawString(infoTotalPay[2] + "", 1860, yCoord + 180);

		ImageIcon logo = new ImageIcon(new ImageIcon("res/logoOscuro.png").getImage().getScaledInstance(getWidth() / 3,
				200, Image.SCALE_SMOOTH));
		g.drawImage(logo.getImage(), getWidth() / 2 - logo.getIconWidth() / 2, getHeight() - 210, this);
	}
}
