package view.cashier;

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import controller.Controller;
import view.JFrameMain;
import view.utils.Constants;

public class JPanelCard extends JPanel {

	private static final long serialVersionUID = 1L;
	private Object[] productInfo;
	private JPanel jPanelContentProduct;
	private JPanel jPanelEspecificationsProduct;
	private JLabel imageProduct;
	private JLabel jLabelinfoProduct;
	private JTextPane jTextPaneEspecification;
	private JScrollPane jScrollPane;
	private JButton jButtonaddProduct;
	private JButton jButtonaddUP;
	private JButton jButtonaddDown;
	private JTextField jTextFieldQuanty;
	private GridBagConstraints gbc;
	private Color colorBorder;
	private int quanty;

	/**
	 * 0 nombre
	 * 1 unidades disp
	 * 2 precio
	 * 3 componente
	 * 4 id
	 * 5 numero de garantia
	 * 6 tipo garantia
	 * 7 imagen
	 * 8 descripcion
	 * Constructor de JPanelCard
	 * @param product informacion del producto 
	 * @param widthCard ancho de la carta de presentacion
	 * @param heightCard alto de la carta de presentacion
	 */
	public JPanelCard(Object[] product, int widthCard, int heightCard) {
		super(new GridBagLayout());
		product[2] = ((float)product[2])+((float)product[2]* 0.2);
		this.quanty = 0;
		this.productInfo = product;
		this.imageProduct = new JLabel(new ImageIcon(((ImageIcon) productInfo[7]).getImage()
				.getScaledInstance(widthCard - (20*JFrameMain.WIDTH_SIZE/1920), heightCard / 2 - (10* JFrameMain.HEIGTH_SIZE/1080), Image.SCALE_SMOOTH)));
		this.jLabelinfoProduct = new JLabel();
		this.jTextPaneEspecification = new JTextPane();
		this.gbc = new GridBagConstraints();
		this.jScrollPane = new JScrollPane();
		this.jPanelContentProduct = new JPanel(new GridBagLayout());
		this.jPanelEspecificationsProduct = new JPanel(new GridBagLayout());
		this.setOpaque(false);
		this.setBorder(new LineBorder(Color.BLACK, 5* JFrameMain.WIDTH_SIZE/1920));
		this.setName(productInfo[0] + "");
		this.jButtonaddProduct = new JButton("+");
		this.jButtonaddUP = new JButton();
		this.jButtonaddDown = new JButton();
		this.jTextFieldQuanty = new JTextField(quanty + "");
		init();
	}

	/**
	 * Metodo que cambia la vista a las especificaciones del producto
	 * @param isEspecifications true o false
	 */
	private void switchViewCard(boolean isEspecifications) {
		if (isEspecifications) {
			setInfoEspecifications();
		} else {
			setInfo();
		}
	}

	/**
	 * Metodo que agrega a la vista la presentacion del producto
	 */
	private void setInfo() {
		this.jPanelEspecificationsProduct.setVisible(false);
		this.add(jPanelContentProduct);
		this.jPanelContentProduct.setVisible(true);
	}

	/**
	 * Metodo que agrega a la vista las especifiaciones del producto
	 */
	private void setInfoEspecifications() {
		this.jPanelContentProduct.setVisible(false);
		gbc.gridy = 0;
		gbc.fill = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		this.add(jPanelEspecificationsProduct, gbc);
		this.jPanelEspecificationsProduct.setVisible(true);

	}

	/**
	 * Metodo que inicializa los componentes de este panel
	 */
	private void init() {
		initLabels();
		addInfoPanels();
		setInfo();
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBorder(new LineBorder(colorBorder, 5));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				setBorder(new LineBorder(Color.BLACK, 5));
			}
		});
	}

	/**
	 * Metodo que añade la informacion a este panel
	 */
	private void addInfoPanels() {
		this.jPanelContentProduct.setOpaque(false);
		this.jPanelEspecificationsProduct.setOpaque(false);
		gbc.gridwidth = 3;
		gbc.fill = 1;
		this.jPanelContentProduct.add(imageProduct, gbc);
		gbc.gridy = 1;
		gbc.insets.bottom = 30;
		this.jPanelContentProduct.add(jLabelinfoProduct, gbc);
		gbc.insets.bottom = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		this.jPanelContentProduct.add(jButtonaddProduct, gbc);
		gbc.gridx = 1;
		gbc.weightx = 1;
		gbc.fill = 1;
		gbc.insets.left = 10;
		this.jPanelContentProduct.add(jTextFieldQuanty, gbc);
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.fill = 0;
		gbc.gridx = 2;
		gbc.gridheight = 1;
		gbc.gridy = 2;
		this.jPanelContentProduct.add(jButtonaddUP, gbc);
		gbc.gridy = 3;
		this.jPanelContentProduct.add(jButtonaddDown, gbc);
		gbc.insets.left = 0;
		gbc.gridwidth = 3;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.fill = 1;
		this.jPanelEspecificationsProduct.add(jScrollPane, gbc);
	}

	/**
	 * Metodo que inicializa los labels
	 */
	private void initLabels() {
		DecimalFormat formato = new DecimalFormat("$#,###.00");
		this.jLabelinfoProduct.setText("<html><center><b>" + (String) productInfo[0] + "<br>" + (int) productInfo[1]
				+ " u. disponibles<br>" + formato.format((double) productInfo[2]) + "<br>" + (String) productInfo[3]
				+ "<br>" + ((int) productInfo[5]) + (((int)productInfo[6])==0?" mes(es)":" año(s)")+ "<b><center><html>");
		this.jLabelinfoProduct.setForeground(Color.WHITE);
		this.jLabelinfoProduct.setFont(new Font("Arial", Font.ITALIC, 19*JFrameMain.WIDTH_SIZE/1920));
		this.jLabelinfoProduct.setHorizontalAlignment(JLabel.CENTER);

		this.jTextPaneEspecification.setOpaque(false);
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_JUSTIFIED);
		StyleConstants.setForeground(attribs, Color.WHITE);
		StyleConstants.setFontFamily(attribs, "Arial");
		StyleConstants.setFontSize(attribs, 18*JFrameMain.WIDTH_SIZE/1920);
		StyleConstants.setItalic(attribs, true);
		jTextPaneEspecification.setParagraphAttributes(attribs, true);
		this.jTextPaneEspecification.setText((String) productInfo[8]);
		this.jTextPaneEspecification.setEditable(false);
		this.jTextPaneEspecification.setFocusable(false);
		this.jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.jScrollPane.setViewportView(jTextPaneEspecification);
		this.jScrollPane.setOpaque(false);
		this.jScrollPane.getViewport().setOpaque(false);
		this.jScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10* JFrameMain.HEIGTH_SIZE/1080, 0));
		this.jScrollPane.getHorizontalScrollBar().setOpaque(false);
		this.jScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				switch ((String) productInfo[3]) {
				case "Componente":
					this.thumbColor = new Color(209, 140, 254);
					colorBorder = new Color(182, 0, 255);
					break;
				case "Celulares":
					this.thumbColor = new Color(140, 179, 254);
					colorBorder = new Color(0, 97, 255 );
					break;
				case "Laptop":
					this.thumbColor = new Color(250, 254, 140);
					colorBorder = new Color(247, 255, 0);
					break;
				case "PC":
					this.thumbColor = new Color(140, 254, 142);
					colorBorder = new Color(81, 255, 0);
					break;
				case "Accesorio":
					this.thumbColor = new Color(236, 0, 255 );
					colorBorder = new Color(243, 0, 255 );
					break;
				}

			}
		});
		this.jTextPaneEspecification.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.jTextPaneEspecification.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchViewCard(false);
			}
		});
		this.imageProduct.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.imageProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchViewCard(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				setBorder(new LineBorder(colorBorder,5));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setBorder(new LineBorder(Color.BLACK, 5));
			}
		});
		this.jButtonaddProduct.setPreferredSize(new Dimension(50* JFrameMain.WIDTH_SIZE/1920, 50* JFrameMain.HEIGTH_SIZE/1080));
		this.jButtonaddProduct.setBackground(new Color(46, 165, 29));
		this.jButtonaddProduct.setForeground(Color.WHITE);
		this.jButtonaddProduct.setFont(new Font("Arial", Font.ITALIC, 20* JFrameMain.WIDTH_SIZE/1920));
		this.jButtonaddProduct.setBorderPainted(false);
		this.jButtonaddProduct.setFocusable(false);
		this.jButtonaddProduct.setRolloverEnabled(false);
		this.jButtonaddProduct.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.jButtonaddProduct.addActionListener(Controller.getInstance());
		this.jButtonaddProduct.setActionCommand(Constants.COMMAND_BUTTON_ADD_PRODUCT_TO_BILL);
		this.jButtonaddProduct.setName(productInfo[4] + "");
		this.jButtonaddProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBorder(new LineBorder(colorBorder,5));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setBorder(new LineBorder(Color.BLACK, 5));
			}
		});

		this.jButtonaddUP.setPreferredSize(new Dimension(50* JFrameMain.WIDTH_SIZE/1920, 25* JFrameMain.HEIGTH_SIZE/1080));
		this.jButtonaddUP.setBackground(new Color(46, 165, 29));
		this.jButtonaddUP.setForeground(Color.WHITE);
		this.jButtonaddUP.setFont(new Font("Arial", Font.ITALIC, 10* JFrameMain.WIDTH_SIZE/1920));
		this.jButtonaddUP.setBorderPainted(false);
		this.jButtonaddUP.setFocusable(false);
		this.jButtonaddUP.setRolloverEnabled(false);
		this.jButtonaddUP.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.jButtonaddUP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (quanty < (int) productInfo[1]) {
					jTextFieldQuanty.setText(++quanty + "");
				}

			}
		});
		this.jButtonaddUP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBorder(new LineBorder(colorBorder,5));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setBorder(new LineBorder(Color.BLACK, 5));
			}
		});

		this.jButtonaddDown.setPreferredSize(new Dimension(50* JFrameMain.WIDTH_SIZE/1920, 25* JFrameMain.HEIGTH_SIZE/1080));
		this.jButtonaddDown.setBackground(new Color(201, 72, 72));
		this.jButtonaddDown.setForeground(Color.WHITE);
		this.jButtonaddDown.setFont(new Font("Arial", Font.ITALIC, 10* JFrameMain.WIDTH_SIZE/1920));
		this.jButtonaddDown.setBorderPainted(false);
		this.jButtonaddDown.setFocusable(false);
		this.jButtonaddDown.setRolloverEnabled(false);
		this.jButtonaddDown.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.jButtonaddDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (quanty != 0) {
					jTextFieldQuanty.setText(--quanty + "");
				}
			}
		});
		this.jButtonaddDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBorder(new LineBorder(colorBorder,5));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setBorder(new LineBorder(Color.BLACK, 5));
			}
		});

		this.jTextFieldQuanty.setPreferredSize(new Dimension(100* JFrameMain.WIDTH_SIZE/1920, 50* JFrameMain.HEIGTH_SIZE/1080));
		this.jTextFieldQuanty.setOpaque(false);
		this.jTextFieldQuanty.setHorizontalAlignment(JTextField.CENTER);
		this.jTextFieldQuanty.setForeground(Color.WHITE);
		this.jTextFieldQuanty.setFont(new Font("Arial", Font.ITALIC, 20* JFrameMain.WIDTH_SIZE/1920));
		this.jTextFieldQuanty.setBorder(new LineBorder(Color.WHITE, 2));
		this.jTextFieldQuanty.setEditable(false);
		this.jTextFieldQuanty.setFocusable(false);
		this.jTextFieldQuanty.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setBorder(new LineBorder(colorBorder,5));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setBorder(new LineBorder(Color.BLACK, 5));
			}
		});
		

	}

	/**
	 * Metodo que obtiene el codigo del producto
	 * @return codigo del producto
	 */
	public int getCode() {
		return (int) productInfo[4];
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		// claro - oscuro gradient
		switch ((String) productInfo[3]) {
		case "Componente" -> g2d
				.setPaint(new GradientPaint(0, 0, new Color(135, 13, 214), 0, getHeight(), new Color(55, 7, 86)));
		case "Celulares" -> g2d
				.setPaint(new GradientPaint(0, 0, new Color(26, 115, 210), 0, getHeight(), new Color(12, 55, 102)));
		case "Laptop" -> g2d
				.setPaint(new GradientPaint(0, 0, new Color(225, 226, 35), 0, getHeight(), new Color(92, 92, 15)));
		case "PC" -> g2d
				.setPaint(new GradientPaint(0, 0, new Color(62, 216, 24), 0, getHeight(), new Color(28, 92, 12)));
		case "Accesorio" -> g2d
				.setPaint(new GradientPaint(0, 0, new Color(223, 29, 181), 0, getHeight(), new Color(94, 12, 76)));
		}
		g2d.fillRect(0, 0, getWidth(), getHeight());
		super.paint(g);
	}

	/**
	 * Metodo que obtiene la informacion de este producto
	 * @return informacion del producto
	 */
	public Object[] getProductInfoToBill() {
		return new Object[] { productInfo[4], quanty, productInfo[0], productInfo[2],
				quanty * (double) productInfo[2] };
	}

	/**
	 * Metodo que pone en cero el jtexfield de cantidad
	 */
	public void setCeroQuanty() {
		this.quanty = 0;
		this.jTextFieldQuanty.setText("" + quanty);
	}

	/**
	 * Metodo que obtiene el tipo de producto
	 * @return tipo de producto
	 */
	public String getType() {
		return (String) productInfo[3];
	}

	/**
	 * Metodo que obtiene el precio de este producto
	 * @return precio del producto
	 */
	public double getPrice() {
		return (double) productInfo[2];
	}
}
