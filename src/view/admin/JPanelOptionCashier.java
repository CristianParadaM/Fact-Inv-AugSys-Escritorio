package view.admin;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import view.JFrameMain;

/**
 * @author CRISTIAN DAVID PARADA MARTINEZ
 */
public class JPanelOptionCashier extends JPanel {

	private GridBagConstraints gbc;
	private JLabel jlabelIcon;
	private JLabel jlabelPoint;
	private JLabel jlabelTittle;
	private JLabel jlabelquanty;

	/**
	 * Constructor de JPanelOptionCashier
	 * @param quanty cantidad de cajeros activos
	 */
	public JPanelOptionCashier(int quanty) {
		super(new GridBagLayout());
		this.gbc = new GridBagConstraints();
		this.jlabelIcon = new JLabel(new ImageIcon(
				new ImageIcon("res/cashiersConfig.png").getImage().getScaledInstance(250*JFrameMain.WIDTH_SIZE/1920, 275*JFrameMain.HEIGTH_SIZE/1080, Image.SCALE_SMOOTH)));
		this.jlabelPoint = new JLabel(new ImageIcon(
				new ImageIcon("res/online.png").getImage().getScaledInstance(30*JFrameMain.WIDTH_SIZE/1920, 30*JFrameMain.HEIGTH_SIZE/1080, Image.SCALE_SMOOTH)));
		this.jlabelTittle = new JLabel("<html><center>Gestionar Cajeros<center><html>");
		this.jlabelquanty = new JLabel("<html>" + quanty + " cajeros activos" + "<html>");
		init();
	}

	/**
	 * Metodo que inicializa las propiedades de este panel
	 */
	private void init() {
		this.setOpaque(false);
		this.setBorder(new LineBorder(new Color(132, 47, 149), 5*JFrameMain.WIDTH_SIZE/1920));
		this.setPreferredSize(new Dimension(700 * JFrameMain.WIDTH_SIZE / 1920, 0));
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		initLabels();
		initGbc();
	}

	/**
	 * Metodo que inicializa las propiedades de los labels
	 */
	private void initLabels() {
		this.jlabelTittle.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 45*JFrameMain.WIDTH_SIZE/1920));
		this.jlabelTittle.setForeground(Color.BLACK);
		this.jlabelquanty.setFont(new Font("Arial", Font.ITALIC, 30*JFrameMain.WIDTH_SIZE/1920));
		this.jlabelquanty.setForeground(Color.BLACK);

	}

	/**
	 * Metodo que inicializa las restricciones del layout y agrega los componentes
	 */
	private void initGbc() {
		gbc.fill = 1;
		gbc.gridheight = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets.left = 5*JFrameMain.WIDTH_SIZE/1920;
		gbc.insets.right = 75*JFrameMain.WIDTH_SIZE/1920;
		this.add(jlabelIcon, gbc);
		gbc.insets.left = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(jlabelTittle, gbc);
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(jlabelPoint, gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		this.add(jlabelquanty, gbc);
	}

	@Override
	public void paint(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		GradientPaint gradientPaint = new GradientPaint(0f, 0f, new Color(161, 58, 182), getWidth() / 3 * 2, 0f,
				new Color(182, 155, 221));
		g.setPaint(gradientPaint);
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paint(graphics);
	}
}
