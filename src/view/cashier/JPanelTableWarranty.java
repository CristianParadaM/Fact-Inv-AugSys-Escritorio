package view.cashier;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.JFrameMain;
import view.utils.JTableWarranty;

/**
 * @author CRISTIAN DAVID PARADA MARTINEZ
 */
public class JPanelTableWarranty extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel jlabelClientText;
	private JLabel jlabelClientName;
	private JLabel jlabelClientTypeID;
	private JLabel jlabelClientID;
	private JLabel jlabelTypeClientText;
	private JLabel jlabelTypeClient;
	private JLabel jlabelCashierText;
	private JLabel jlabelCashierName;
	private JLabel jlabelCashierTypeID;
	private JLabel jlabelCashierID;
	private JLabel jlabelClientTypePayText;
	private JLabel jlabelClientTypePay;
	private JLabel jlabelSubtotalText;
	private JLabel jlabelIVAText;
	private JLabel jlabelTotalText;
	private JLabel jlabelSubtotal;
	private JLabel jlabelIVA;
	private JLabel jlabelTotal;
	private JTableWarranty jTableWarranty;
	private GridBagConstraints gbc;

	/**
	 * Constructor de JPanelTableWarranty
	 * @param infoSale informacion de la venta
	 */
	@SuppressWarnings("unchecked")
	public JPanelTableWarranty(Object[] infoSale) {
		super(new GridBagLayout());
		this.gbc = new GridBagConstraints();
		this.jlabelClientText = new JLabel("Cliente:", JLabel.CENTER);
		this.jlabelClientName = new JLabel((String) infoSale[2], JLabel.LEFT);
		this.jlabelClientTypeID = new JLabel((String) infoSale[3] + ":", JLabel.CENTER);
		this.jlabelClientID = new JLabel((int) infoSale[4] + "", JLabel.LEFT);
		this.jlabelTypeClientText = new JLabel("Tipo:", JLabel.CENTER);
		this.jlabelTypeClient = new JLabel((String) infoSale[7], JLabel.LEFT);
		this.jlabelCashierText = new JLabel("Cajero:", JLabel.CENTER);
		this.jlabelCashierName = new JLabel((String) infoSale[5], JLabel.LEFT);
		this.jlabelCashierTypeID = new JLabel("CC:", JLabel.CENTER);
		this.jlabelCashierID = new JLabel((int) infoSale[6] + "", JLabel.LEFT);
		this.jlabelClientTypePayText = new JLabel("Pago:", JLabel.CENTER);
		this.jlabelClientTypePay = new JLabel((String) infoSale[8], JLabel.LEFT);
		this.jlabelSubtotalText = new JLabel("Subtotal($)", JLabel.RIGHT);
		this.jlabelIVAText = new JLabel("IVA(" + (int) infoSale[10] + "%)", JLabel.RIGHT);
		this.jlabelTotalText = new JLabel("Total($)", JLabel.RIGHT);
		this.jlabelSubtotal = new JLabel((String) infoSale[11], JLabel.LEFT);
		this.jlabelIVA = new JLabel((String) infoSale[12], JLabel.LEFT);
		this.jlabelTotal = new JLabel((String) infoSale[13], JLabel.LEFT);
		this.jTableWarranty = new JTableWarranty((ArrayList<Object[]>) infoSale[9]);
		init((ArrayList<Object[]>) infoSale[9]);
	}

	/**
	 * Metodo que inicia este panel
	 * @param productsInBill productos en la factura
	 */
	private void init(ArrayList<Object[]> productsInBill) {
		this.setOpaque(false);
		this.jTableWarranty.actualizeBill(productsInBill);
		addInfoPanel();
	}

	/**
	 * Metodo que agrega la informacion a este panel
	 */
	private void addInfoPanel() {
		jlabelSubtotalText.setForeground(new Color(48, 116, 180));
		jlabelIVAText.setForeground(new Color(48, 116, 180));
		jlabelTotalText.setForeground(new Color(48, 116, 180));
		jlabelClientText.setForeground(new Color(48, 116, 180));
		jlabelClientTypeID.setForeground(new Color(48, 116, 180));
		jlabelTypeClientText.setForeground(new Color(48, 116, 180));
		jlabelCashierText.setForeground(new Color(48, 116, 180));
		jlabelCashierTypeID.setForeground(new Color(48, 116, 180));
		jlabelClientTypePayText.setForeground(new Color(48, 116, 180));

		jlabelSubtotal.setForeground(Color.BLACK);
		jlabelIVA.setForeground(Color.BLACK);
		jlabelTotal.setForeground(Color.BLACK);
		jlabelClientName.setForeground(Color.BLACK);
		jlabelClientID.setForeground(Color.BLACK);
		jlabelTypeClient.setForeground(Color.BLACK);
		jlabelCashierName.setForeground(Color.BLACK);
		jlabelCashierID.setForeground(Color.BLACK);
		jlabelClientTypePay.setForeground(Color.BLACK);

		jlabelSubtotalText.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 25 * JFrameMain.WIDTH_SIZE / 1920));
		jlabelIVAText.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 25 * JFrameMain.WIDTH_SIZE / 1920));
		jlabelTotalText.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 25 * JFrameMain.WIDTH_SIZE / 1920));
		jlabelClientText.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 25 * JFrameMain.WIDTH_SIZE / 1920));
		jlabelClientTypeID.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 25 * JFrameMain.WIDTH_SIZE / 1920));
		jlabelTypeClientText.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 25 * JFrameMain.WIDTH_SIZE / 1920));
		jlabelCashierText.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 25 * JFrameMain.WIDTH_SIZE / 1920));
		jlabelCashierTypeID.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 25 * JFrameMain.WIDTH_SIZE / 1920));
		jlabelClientTypePayText
				.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 25 * JFrameMain.WIDTH_SIZE / 1920));

		jlabelClientName.setFont(new Font("Arial", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		jlabelClientID.setFont(new Font("Arial", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		jlabelTypeClient.setFont(new Font("Arial", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		jlabelCashierName.setFont(new Font("Arial", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		jlabelCashierID.setFont(new Font("Arial", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		jlabelClientTypePay.setFont(new Font("Arial", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		jlabelSubtotal.setFont(new Font("Arial", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		jlabelIVA.setFont(new Font("Arial", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		jlabelTotal.setFont(new Font("Arial", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));

		gbc.fill = 1;
		gbc.insets.top = 5 * JFrameMain.HEIGTH_SIZE / 1080;
		gbc.insets.left = 50 * JFrameMain.WIDTH_SIZE / 1920;
		gbc.gridx = 0;
		gbc.weightx = 1;
		this.add(jlabelClientText, gbc);
		gbc.insets.left = 0;
		gbc.gridx = 1;
		this.add(jlabelClientName, gbc);
		gbc.gridx = 2;
		this.add(jlabelClientTypeID, gbc);
		gbc.gridx = 3;
		this.add(jlabelClientID, gbc);
		gbc.gridx = 4;
		this.add(jlabelTypeClientText, gbc);
		gbc.gridx = 5;
		this.add(jlabelTypeClient, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets.top = 10 * JFrameMain.HEIGTH_SIZE / 1080;
		gbc.insets.left = 50 * JFrameMain.WIDTH_SIZE / 1920;
		this.add(jlabelCashierText, gbc);
		gbc.insets.left = 0;
		gbc.gridx = 1;
		this.add(jlabelCashierName, gbc);
		gbc.gridx = 2;
		this.add(jlabelCashierTypeID, gbc);
		gbc.gridx = 3;
		this.add(jlabelCashierID, gbc);
		gbc.gridx = 4;
		this.add(jlabelClientTypePayText, gbc);
		gbc.gridx = 5;
		this.add(jlabelClientTypePay, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 6;
		gbc.weighty = 1;
		gbc.insets = new Insets(20 * JFrameMain.HEIGTH_SIZE / 1080, 50 * JFrameMain.WIDTH_SIZE / 1920,
				20 * JFrameMain.HEIGTH_SIZE / 1080, 50 * JFrameMain.WIDTH_SIZE / 1920);
		this.add(jTableWarranty, gbc);
		gbc.insets = new Insets(0, 50 * JFrameMain.WIDTH_SIZE / 1920, 5 * JFrameMain.HEIGTH_SIZE / 1080, 0);
		gbc.gridwidth = 1;
		gbc.gridy = 3;
		gbc.weighty = 0;
		gbc.gridx = 0;
		gbc.weightx = 1;
		this.add(jlabelSubtotalText, gbc);
		gbc.gridx = 1;
		this.add(jlabelSubtotal, gbc);
		gbc.gridy = 4;
		gbc.gridx = 0;
		this.add(jlabelIVAText, gbc);
		gbc.gridx = 1;
		this.add(jlabelIVA, gbc);
		gbc.gridy = 5;
		gbc.gridx = 0;
		gbc.insets.bottom = 100 * JFrameMain.HEIGTH_SIZE / 1080;
		this.add(jlabelTotalText, gbc);
		gbc.gridx = 1;
		this.add(jlabelTotal, gbc);
	}

	@Override
	public void paint(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		g.setColor(new Color(48, 116, 180));
		g.setStroke(new BasicStroke(3));
		g.drawLine(54 * JFrameMain.WIDTH_SIZE / 1920, 100 * JFrameMain.HEIGTH_SIZE / 1080,
				54 * JFrameMain.WIDTH_SIZE / 1920, getHeight() - (100 * JFrameMain.HEIGTH_SIZE / 1080));
		g.drawLine(getWidth() - (52 * JFrameMain.WIDTH_SIZE / 1920), 100 * JFrameMain.HEIGTH_SIZE / 1080,
				getWidth() - (52 * JFrameMain.WIDTH_SIZE / 1920), getHeight() - (100 * JFrameMain.HEIGTH_SIZE / 1080));
		g.drawLine(52 * JFrameMain.WIDTH_SIZE / 1920, 430 * JFrameMain.HEIGTH_SIZE / 1080,
				getWidth() - (52 * JFrameMain.WIDTH_SIZE / 1920), 430 * JFrameMain.HEIGTH_SIZE / 1080);
		g.drawLine(52 * JFrameMain.WIDTH_SIZE / 1920, 552 * JFrameMain.HEIGTH_SIZE / 1080,
				getWidth() - (52 * JFrameMain.WIDTH_SIZE / 1920), 552 * JFrameMain.HEIGTH_SIZE / 1080);
		g.drawImage(
				new ImageIcon(new ImageIcon("res/logoOscuro.png").getImage().getScaledInstance(
						400 * JFrameMain.WIDTH_SIZE / 1920, 100 * JFrameMain.HEIGTH_SIZE / 1080, Image.SCALE_SMOOTH))
								.getImage(),
				getWidth() / 2 + (350 * JFrameMain.WIDTH_SIZE / 1920),
				getHeight() - (210 * JFrameMain.HEIGTH_SIZE / 1080), this);
		g.setFont(new Font("Arial", Font.ITALIC, 25 * JFrameMain.WIDTH_SIZE / 1920));
		g.setColor(Color.BLACK);
		g.drawString("© Augusto Systems. Todos los derechos reservados",
				getWidth() / 2 - (300 * JFrameMain.WIDTH_SIZE / 1920),
				getHeight() - (60 * JFrameMain.HEIGTH_SIZE / 1080));
		super.paint(g);
	}
}
