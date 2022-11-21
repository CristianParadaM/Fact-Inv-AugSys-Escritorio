package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import view.utils.JTableBill;

/**
 * @author CRISTIAN DAVID PARADA MARTINEZ
 */
public class JPanelTableBill extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTableBill jTableBill;
	private JPanel jPanelTotal;
	private GridBagConstraints gbc;
	private JLabel jLabelSubTotal;
	private JLabel jLabelSubTotal_Value;
	private JLabel jLabelIVA;
	private JLabel jLabelIVA_Value;
	private JLabel jLabelTotal;
	private JLabel jLabelTotal_Value;
	private ImageIcon logo;
	private DecimalFormat formato;

	/**
	 * Constructor de JPanelTableBill
	 * @param iva valor del iva
	 */
	public JPanelTableBill(byte iva) {
		super(new BorderLayout());
		this.formato = new DecimalFormat("#,###.00");
		this.logo = new ImageIcon(
				new ImageIcon("res/logoOscuro.png").getImage().getScaledInstance(400* JFrameMain.WIDTH_SIZE/1920, 100* JFrameMain.HEIGTH_SIZE/1080, Image.SCALE_SMOOTH));
		this.jTableBill = new JTableBill();
		this.jPanelTotal = new JPanel(new GridBagLayout());
		this.gbc = new GridBagConstraints();
		this.jLabelSubTotal = new JLabel("Subtotal: ", JLabel.RIGHT);
		this.jLabelSubTotal_Value = new JLabel(formato.format(12231321), JLabel.CENTER);
		this.jLabelIVA = new JLabel("IVA(" + iva + "%): ", JLabel.RIGHT);
		this.jLabelIVA_Value = new JLabel(formato.format(10000000), JLabel.CENTER);
		this.jLabelTotal = new JLabel("Total: ", JLabel.RIGHT);
		this.jLabelTotal_Value = new JLabel(formato.format(25000000), JLabel.CENTER);
		init();
	}

	/**
	 * Metodo que inicializa las propiedades de este panel
	 */
	private void init() {
		initPropPanelTotal();
		this.setOpaque(false);
		this.setBorder(new LineBorder(new Color(48, 116, 180), 3));
		this.setPreferredSize(new Dimension(0, JFrameMain.HEIGTH_SIZE * 550 / 1080));
		this.add(jTableBill, BorderLayout.CENTER);
		this.add(jPanelTotal, BorderLayout.SOUTH);
	}

	/**
	 * Metodo que inicializa las propiedades del panel total
	 */
	private void initPropPanelTotal() {
		initPropJLabels();
		jPanelTotal.setPreferredSize(new Dimension(0, JFrameMain.HEIGTH_SIZE * 150 / 1080));
		jPanelTotal.setOpaque(false);
		jPanelTotal.setBorder(new MatteBorder(3, 0, 0, 0, new Color(48, 116, 180)));

		gbc.fill = 1;
		gbc.weightx = 1;
		this.jPanelTotal.add(Box.createRigidArea(new Dimension(JFrameMain.WIDTH_SIZE * 1106 / 1920, 0)), gbc);
		gbc.gridx = 1;
		this.jPanelTotal.add(jLabelSubTotal, gbc);
		gbc.gridx = 2;
		this.jPanelTotal.add(jLabelSubTotal_Value, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.jPanelTotal.add(Box.createRigidArea(new Dimension(0, 0)), gbc);
		gbc.gridx = 1;
		this.jPanelTotal.add(jLabelIVA, gbc);
		gbc.gridx = 2;
		this.jPanelTotal.add(jLabelIVA_Value, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.jPanelTotal.add(Box.createRigidArea(new Dimension(0, 0)), gbc);
		gbc.gridx = 1;
		this.jPanelTotal.add(jLabelTotal, gbc);
		gbc.gridx = 2;
		this.jPanelTotal.add(jLabelTotal_Value, gbc);
	}

	/**
	 * Metodo que inicializa las propiedades de los JLabels
	 */
	private void initPropJLabels() {
		this.jLabelSubTotal.setForeground(new Color(48, 116, 180));
		this.jLabelSubTotal.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 30* JFrameMain.WIDTH_SIZE/1920));
		this.jLabelSubTotal_Value.setForeground(Color.BLACK);
		this.jLabelSubTotal_Value.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 20* JFrameMain.WIDTH_SIZE/1920));
		this.jLabelIVA.setForeground(new Color(48, 116, 180));
		this.jLabelIVA.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 30* JFrameMain.WIDTH_SIZE/1920));
		this.jLabelIVA_Value.setForeground(Color.BLACK);
		this.jLabelIVA_Value.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 20* JFrameMain.WIDTH_SIZE/1920));
		this.jLabelTotal.setForeground(new Color(48, 116, 180));
		this.jLabelTotal.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 30* JFrameMain.WIDTH_SIZE/1920));
		this.jLabelTotal_Value.setForeground(Color.BLACK);
		this.jLabelTotal_Value.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 20* JFrameMain.WIDTH_SIZE/1920));
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(new Color(48, 116, 180));
		
		g2d.drawLine(JFrameMain.WIDTH_SIZE * 302 / 1920, 0, JFrameMain.WIDTH_SIZE * 302 / 1920, 395 * JFrameMain.HEIGTH_SIZE/1080);
		g2d.drawLine(JFrameMain.WIDTH_SIZE * 152 / 1920, 0, JFrameMain.WIDTH_SIZE * 152 / 1920, 395 * JFrameMain.HEIGTH_SIZE/1080);
		
		g2d.drawLine(JFrameMain.WIDTH_SIZE * 1262 / 1920, 0, JFrameMain.WIDTH_SIZE * 1262 / 1920, getHeight());
		g2d.drawLine(JFrameMain.WIDTH_SIZE * 1512 / 1920, 0, JFrameMain.WIDTH_SIZE * 1512 / 1920, getHeight());
		g2d.drawImage(logo.getImage(), 10* JFrameMain.HEIGTH_SIZE/1080 , getHeight() - logo.getIconHeight() - (10* JFrameMain.HEIGTH_SIZE/1080), this);
		super.paint(g);
	}

	/**
	 * Metodo que agrega un producto a la factura
	 * @param productInfo informacion del producto
	 */
	public void addProductToBill(Object[] productInfo) {
		this.jTableBill.addItemToBill(productInfo);
	}

	/**
	 * Metodo que obtiene los productos de la factura
	 * @return informacion de los productos de la factura
	 */
	public ArrayList<Object[]> getProductsInBill() {
		return jTableBill.getProductsInBill();
	}

	/**
	 * Metodo que actualiza la factura
	 * @param productsInBill productos a setear a la factura
	 */
	public void actualizeBill(ArrayList<Object[]> productsInBill) {
		jTableBill.actualizeBill(productsInBill);
	}

	/**
	 * Metodo que obtiene la informacion de pago de la factura
	 * @return informacion de pago
	 */
	public Object[] getInfoPayBill() {
		return new Object[] {
				jLabelSubTotal_Value.getText(),
				jLabelIVA_Value.getText(),
				jLabelTotal_Value.getText()
				};
	}

	/**
	 * Metodo que remueve los productos de la factura
	 */
	public void removeProductsBill() {
		this.jTableBill.removeAll();
	}

	public void actualizeTotalValues(Object[] totalValues) {
		jLabelSubTotal_Value.setText(formato.format((float)totalValues[0]));
		jLabelIVA_Value.setText(formato.format((float)totalValues[1]));
		jLabelTotal_Value.setText(formato.format((float)totalValues[2]));
	}

}
