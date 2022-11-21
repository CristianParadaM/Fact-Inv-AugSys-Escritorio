package view.cashier;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

import model.TextPrompt;
import view.JFrameMain;

/**
 * @author CRISTIAN DAVID PARADA MARTINEZ
 */
public class JPanelSalesHistoryTable extends JPanel {

	private static final int WIDHT_PANEL = 1800 * JFrameMain.WIDTH_SIZE / 1920;
	private static final int HEIGHT_INFO_PANEL = (836 * JFrameMain.HEIGTH_SIZE / 1080) / 10;
	private static final long serialVersionUID = 1L;
	private JPanel jPanelTop;
	private JTextField jTextFieldSearch;
	private JLabel jLabelIconSearch;
	private JButton jbuttonDeleteAll;
	private JPanel jPanelContainer;
	private JScrollPane jScrollPane;
	private ArrayList<Object[]> listbills;
	private ArrayList<JPanel> listPanelBills;
	private ArrayList<Object[]> listTableBills;
	private Color backGround;
	private int coordY;
	private String filtre;

	/**
	 * Constructor de JPanelSalesHistoryTable
	 * @param salesHistory lista de historico de ventas
	 */
	public JPanelSalesHistoryTable(ArrayList<Object[]> salesHistory) {
		super(new BorderLayout());
		this.coordY = 0;
		this.jbuttonDeleteAll = new JButton("     X     ");
		this.jTextFieldSearch = new JTextField();
		this.jLabelIconSearch = new JLabel(
				new ImageIcon(new ImageIcon("res/searchIcon.png").getImage().getScaledInstance(
						30 * JFrameMain.WIDTH_SIZE / 1920, 30 * JFrameMain.HEIGTH_SIZE / 1080, Image.SCALE_SMOOTH)));
		this.jPanelTop = new JPanel(new GridBagLayout());
		this.listbills = salesHistory;
		this.listPanelBills = new ArrayList<JPanel>();
		this.listTableBills = new ArrayList<Object[]>();
		this.jPanelContainer = new JPanel();
		this.jScrollPane = new JScrollPane();
		this.backGround = new Color(161, 210, 230);
		testBills();
		init();
	}

	/**
	 * Metodo que inicia este panel
	 */
	private void init() {
		this.setOpaque(false);
		initPropJPanelTop();
		this.jPanelContainer.setLayout(null);
		this.jPanelContainer.setPreferredSize(
				new Dimension(JFrameMain.WIDTH_SIZE * WIDHT_PANEL / 1920, JFrameMain.HEIGTH_SIZE * 836 / 1080));
		this.jPanelContainer.setSize(
				new Dimension(JFrameMain.WIDTH_SIZE * WIDHT_PANEL / 1920, JFrameMain.HEIGTH_SIZE * 836 / 1080));
		addBills();
		initPropJScroll();
		this.add(jPanelTop, BorderLayout.NORTH);
		this.add(jScrollPane, BorderLayout.CENTER);
	}

	/**
	 * Metodo que inicia las propiedades del panel cabecera
	 */
	private void initPropJPanelTop() {
		GridBagConstraints gbc = new GridBagConstraints();
		this.jPanelTop.setOpaque(false);
		this.jbuttonDeleteAll.setForeground(Color.BLACK);
		this.jbuttonDeleteAll.setFont(new Font("Arial", Font.BOLD, 30* JFrameMain.WIDTH_SIZE/1920));
		this.jbuttonDeleteAll.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, new Color(48, 116, 180)));
		this.jbuttonDeleteAll.setContentAreaFilled(false);
		this.jbuttonDeleteAll.setFocusable(false);
		this.jbuttonDeleteAll.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.jbuttonDeleteAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jTextFieldSearch.setText("");
				for (Object[] jTables : listTableBills) {
					jTables[1] = false;
				}
				reorganize();
			}
		});

		this.jTextFieldSearch.setPreferredSize(new Dimension(0, 50* JFrameMain.HEIGTH_SIZE/1080));
		this.jTextFieldSearch.setForeground(Color.BLACK);
		this.jTextFieldSearch.setFont(new Font("Arial", Font.ITALIC, 25* JFrameMain.WIDTH_SIZE/1920));
		this.jTextFieldSearch.setOpaque(false);
		this.jTextFieldSearch.setBorder(new LineBorder(new Color(48, 116, 180), 3));
		this.jTextFieldSearch.setLayout(new BorderLayout());
		this.jTextFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (jTextFieldSearch.getText().isEmpty() && (int) e.getKeyChar() != 8) {
					jTextFieldSearch.setText("\t" + jTextFieldSearch.getText());
				}
				filtre(jTextFieldSearch.getText() + e.getKeyChar());
			}

		});
		TextPrompt textPrompt = new TextPrompt(
				"           Digite el numero o fecha de la factura (Ejemplo: 22/02/2022 o 20221001)", jTextFieldSearch);
		textPrompt.changeAlpha(0.35f);
		textPrompt.changeStyle(Font.ITALIC);
		this.jTextFieldSearch.add(jLabelIconSearch, BorderLayout.WEST);
		this.jTextFieldSearch.add(jbuttonDeleteAll, BorderLayout.EAST);

		gbc.fill = 1;
		gbc.weightx = 1;
		gbc.insets.top = 50;
		gbc.insets.left = 100;
		gbc.insets.right = 100;
		gbc.insets.bottom = 50;
		this.jPanelTop.add(jTextFieldSearch, gbc);
	}

	/**
	 * Metodo que inicia las propiedades del scrollpane
	 */
	private void initPropJScroll() {
		this.jScrollPane.setViewportView(jPanelContainer);
		this.jScrollPane.setOpaque(false);
		this.jScrollPane.getViewport().setOpaque(false);
		this.jScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10* JFrameMain.WIDTH_SIZE/1920, 0));
		this.jScrollPane.getHorizontalScrollBar().setOpaque(false);
		this.jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.jScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = new Color(64, 181, 195);
			}
		});
	}

	/**
	 * Metodo que agrega las ventas al panel
	 */
	private void addBills() {
		for (int i = 0; i < listbills.size(); i++) {
			JPanel jPanelInfoBill = new JPanel(new GridBagLayout());
			JPanelTableWarranty jPanelTableWarranty = new JPanelTableWarranty(listbills.get(i));
			jPanelTableWarranty.setName(i + "");

			if (backGround.getRed() == 161 && backGround.getGreen() == 210 && backGround.getBlue() == 230) {
				backGround = new Color(43, 112, 140);
			} else {
				backGround = new Color(161, 210, 230);
			}

			jPanelInfoBill.setBackground(backGround);
			jPanelInfoBill.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.WHITE));
			jPanelInfoBill.setCursor(new Cursor(Cursor.HAND_CURSOR));
			jPanelInfoBill.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					if (jPanelInfoBill.getBackground().getRed() == 161
							&& jPanelInfoBill.getBackground().getGreen() == 210
							&& jPanelInfoBill.getBackground().getBlue() == 230) {
						jPanelInfoBill.setBackground(new Color(177, 230, 251));
					} else {
						jPanelInfoBill.setBackground(new Color(54, 142, 177));
					}
				}

				@Override
				public void mouseExited(MouseEvent e) {
					if (jPanelInfoBill.getBackground().getRed() == 54
							&& jPanelInfoBill.getBackground().getGreen() == 142
							&& jPanelInfoBill.getBackground().getBlue() == 177) {
						jPanelInfoBill.setBackground(new Color(43, 112, 140));
					} else {
						jPanelInfoBill.setBackground(new Color(161, 210, 230));
					}
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					String numberTable = ((JPanel) e.getSource()).getName().split(":")[2];
					for (Object[] jTables : listTableBills) {
						if (((JPanelTableWarranty) jTables[0]).getName().equals(numberTable)) {
							if ((boolean) jTables[1] == true) {
								jTables[1] = false;
								((JPanelTableWarranty) jTables[0]).setVisible(false);
							} else {
								jTables[1] = true;
								((JPanelTableWarranty) jTables[0]).setVisible(true);
							}
							break;
						}
					}
					filtre(filtre);
				}
			});
			putInfoInPanel(i, jPanelInfoBill, new GridBagConstraints(), listbills.get(i));
			this.listPanelBills.add(jPanelInfoBill);
			this.listTableBills.add(new Object[] { jPanelTableWarranty, false });
			this.jPanelContainer.add(jPanelInfoBill).setBounds(0, coordY, WIDHT_PANEL, HEIGHT_INFO_PANEL);
			coordY += HEIGHT_INFO_PANEL;
			if (coordY >= jPanelContainer.getHeight()) {
				jPanelContainer.setPreferredSize(
						new Dimension(jPanelContainer.getWidth(), jPanelContainer.getHeight() + HEIGHT_INFO_PANEL));
				jPanelContainer.setSize(
						new Dimension(jPanelContainer.getWidth(), jPanelContainer.getHeight() + HEIGHT_INFO_PANEL));
			}
		}
	}

	/**
	 * Metodo que pone la informacion de la venta en el panel
	 * @param index posicion
	 * @param jPanel panel a agregarle la informacion 
	 * @param gbc restricciones
	 * @param infoTable informacion de la venta
	 */
	private void putInfoInPanel(int index, JPanel jPanel, GridBagConstraints gbc, Object[] infoTable) {
		JLabel jlabelNumberBill = new JLabel("  " + (index + 1) + ".  Factura N. " + (int) infoTable[1], JLabel.LEFT);
		JLabel jlabelDate = new JLabel("Fecha: " + (String.format("%02d", ((LocalDate) infoTable[0]).getDayOfMonth()))
				+ "/" + (String.format("%02d", ((LocalDate) infoTable[0]).getMonthValue())) + "/"
				+ ((LocalDate) infoTable[0]).getYear(), JLabel.LEFT);
		jPanel.setName((String.format("%02d", ((LocalDate) infoTable[0]).getDayOfMonth())) + "/"
				+ (String.format("%02d", ((LocalDate) infoTable[0]).getMonthValue())) + "/"
				+ ((LocalDate) infoTable[0]).getYear() + ":" + (int) infoTable[1] + ":" + index);
		@SuppressWarnings("unchecked")
		JLabel jlabelNumberProducts = new JLabel(((ArrayList<Object[]>) infoTable[9]).size() + " Producto(s)",
				JLabel.LEFT);
		if (backGround.getRed() == 161 && backGround.getGreen() == 210 && backGround.getBlue() == 230) {
			jlabelNumberBill.setForeground(Color.BLACK);
			jlabelDate.setForeground(Color.BLACK);
			jlabelNumberProducts.setForeground(Color.BLACK);
		} else {
			jlabelNumberBill.setForeground(Color.WHITE);
			jlabelDate.setForeground(Color.WHITE);
			jlabelNumberProducts.setForeground(Color.WHITE);
		}

		jlabelNumberBill.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 30 * JFrameMain.WIDTH_SIZE / 1920));
		jlabelDate.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 30 * JFrameMain.WIDTH_SIZE / 1920));
		jlabelNumberProducts.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 30 * JFrameMain.WIDTH_SIZE / 1920));

		gbc.weightx = 1;
		gbc.fill = 1;
		jPanel.add(jlabelNumberBill, gbc);
		gbc.gridx = 1;
		jPanel.add(jlabelDate, gbc);
		gbc.weightx = 0;
		gbc.gridx = 2;
		jPanel.add(jlabelNumberProducts, gbc);
		gbc.weightx = 1;
		gbc.gridx = 3;
		jPanel.add(
				new JLabel(new ImageIcon(new ImageIcon("res/confirmation.png").getImage().getScaledInstance(
						60 * JFrameMain.WIDTH_SIZE / 1920, 60 * JFrameMain.HEIGTH_SIZE / 1080, Image.SCALE_SMOOTH))),
				gbc);
	}

	/**
	 * Metodo que filtra las ventas 
	 * @param text texto a filtrar
	 */
	public void filtre(String text) {
		text = text == null ? "" : text;
		filtre = text;
		ArrayList<Object[]> listFiltred = new ArrayList<Object[]>();
		text = text.replace("	", "").replace("", "").toLowerCase();
		if (text.isBlank()) {
			reorganize();
			return;
		} else {
			for (int i = 0; i < this.jPanelContainer.getComponents().length; i++) {
				if (this.jPanelContainer.getComponent(i) instanceof JPanelTableWarranty) {
				} else {
					String[] dateAndNumberBill = this.jPanelContainer.getComponent(i).getName().split(":");

					if (dateAndNumberBill[0].contains(text) || dateAndNumberBill[1].contains(text)) {
						listFiltred.add(new Object[] { (JPanel) this.jPanelContainer.getComponent(i), true });
					} else {
						this.jPanelContainer.getComponent(i).setVisible(false);
					}
				}
			}
		}
		if (!listFiltred.isEmpty()) {
			organize(listFiltred);
			this.jScrollPane.getVerticalScrollBar().setValue(0);
		}

	}
	
	/**
	 * Metodo que reorganiza el historial de ventas
	 */
	private void reorganize() {
		setvisibleAll(false);
		coordY = 0;
		jPanelContainer.setSize(new Dimension(WIDHT_PANEL, 0));
		jPanelContainer.setPreferredSize(new Dimension(WIDHT_PANEL, 0));
		for (int i = 0; i < listPanelBills.size(); i++) {
			this.jPanelContainer.add(listPanelBills.get(i)).setBounds(0, coordY, WIDHT_PANEL, HEIGHT_INFO_PANEL);
			listPanelBills.get(i).setVisible(true);
			int index = Integer.parseInt(listPanelBills.get(i).getName().split(":")[2]);
			if ((boolean) listTableBills.get(index)[1]) {
				coordY += HEIGHT_INFO_PANEL;
				this.jPanelContainer.add(((JPanelTableWarranty) listTableBills.get(index)[0])).setBounds(0, coordY,
						WIDHT_PANEL, 650 * JFrameMain.HEIGTH_SIZE / 1080);
				((JPanelTableWarranty) listTableBills.get(index)[0]).setVisible(true);
				jPanelContainer.setPreferredSize(new Dimension(jPanelContainer.getWidth(),
						jPanelContainer.getHeight() + (650 * JFrameMain.HEIGTH_SIZE / 1080) + HEIGHT_INFO_PANEL));
				jPanelContainer.setSize(new Dimension(jPanelContainer.getWidth(),
						jPanelContainer.getHeight() + (650 * JFrameMain.HEIGTH_SIZE / 1080) + HEIGHT_INFO_PANEL));
				coordY += 650 * JFrameMain.HEIGTH_SIZE / 1080;
			} else {
				coordY += HEIGHT_INFO_PANEL;
				if (coordY >= jPanelContainer.getHeight()) {
					jPanelContainer.setPreferredSize(
							new Dimension(jPanelContainer.getWidth(), jPanelContainer.getHeight() + HEIGHT_INFO_PANEL));
					jPanelContainer.setSize(
							new Dimension(jPanelContainer.getWidth(), jPanelContainer.getHeight() + HEIGHT_INFO_PANEL));
				}
			}
		}
	}

	/**
	 * Metodo que organiza el historial de ventas
	 * @param billsFiltred ventas filtradas
	 */
	private void organize(ArrayList<Object[]> billsFiltred) {
		setvisibleAll(false);
		coordY = 0;
		jPanelContainer.setSize(new Dimension(WIDHT_PANEL, 0));
		jPanelContainer.setPreferredSize(new Dimension(WIDHT_PANEL, 0));
		for (int i = 0; i < billsFiltred.size(); i++) {
			if ((boolean) billsFiltred.get(i)[1]) {
				this.jPanelContainer.add((JPanel) billsFiltred.get(i)[0]).setBounds(0, coordY, WIDHT_PANEL,
						HEIGHT_INFO_PANEL);
				((JPanel) billsFiltred.get(i)[0]).setVisible(true);
				int index = Integer.parseInt(((JPanel) billsFiltred.get(i)[0]).getName().split(":")[2]);
				if ((boolean) listTableBills.get(index)[1]) {
					coordY += HEIGHT_INFO_PANEL;
					this.jPanelContainer.add(((JPanelTableWarranty) listTableBills.get(index)[0])).setBounds(0, coordY,
							WIDHT_PANEL, 650 * JFrameMain.HEIGTH_SIZE / 1080);
					((JPanelTableWarranty) listTableBills.get(index)[0]).setVisible(true);
					jPanelContainer.setPreferredSize(new Dimension(jPanelContainer.getWidth(),
							jPanelContainer.getHeight() + (650 * JFrameMain.HEIGTH_SIZE / 1080) + HEIGHT_INFO_PANEL));
					jPanelContainer.setSize(new Dimension(jPanelContainer.getWidth(),
							jPanelContainer.getHeight() + (650 * JFrameMain.HEIGTH_SIZE / 1080) + HEIGHT_INFO_PANEL));
					coordY += 650 * JFrameMain.HEIGTH_SIZE / 1080;
				} else {
					coordY += HEIGHT_INFO_PANEL;
					if (coordY >= jPanelContainer.getHeight()) {
						jPanelContainer.setPreferredSize(new Dimension(jPanelContainer.getWidth(),
								jPanelContainer.getHeight() + HEIGHT_INFO_PANEL));
						jPanelContainer.setSize(new Dimension(jPanelContainer.getWidth(),
								jPanelContainer.getHeight() + HEIGHT_INFO_PANEL));
					}
				}
			}
		}

	}

	/**
	 * Metodo que pone o no visibles los componentes de jpanelContainer
	 * @param b true o false
	 */
	private void setvisibleAll(boolean b) {
		for (int i = 0; i < this.jPanelContainer.getComponents().length; i++) {
			this.jPanelContainer.getComponent(i).setVisible(b);
		}
	}

	private void testBills() {
		ArrayList<Object[]> productsInBill = new ArrayList<Object[]>();
		productsInBill.add(new Object[] { 1001, 2, "Mouse x", "Activa", 123123.32, 1233212.32 });
		productsInBill.add(new Object[] { 1001, 3, "Mouse x2", "Inactiva", 123123.32, 1233212.32 });
		productsInBill.add(new Object[] { 1001, 1, "Mouse x3", "Activa", 123123.32, 1233212.32 });
		productsInBill.add(new Object[] { 1001, 1, "Mouse x3", "Activa", 123123.32, 1233212.32 });
		productsInBill.add(new Object[] { 1001, 1, "Mouse x3", "Activa", 123123.32, 1233212.32 });

		ArrayList<Object[]> productsInBill2 = new ArrayList<Object[]>();
		productsInBill2.add(new Object[] { 1001, 2, "Mouse x", "Activa", 123123.32, 1233212.32 });
		productsInBill2.add(new Object[] { 1001, 3, "Mouse x2", "Inactiva", 123123.32, 1233212.32 });

		this.listbills.add(new Object[] { 
				LocalDate.of(2022, 2, 21), // fecha factura
				20221001, // numero factura
				"Daniel Felipe Suarez Bohorquez", // cliente
				"CC", 
				1002587073, // id cliente
				"Crisitan David Parada Martinez", // cajero
				1002365941, // id cajero
				"Minoritario", 
				"Efectivo", 
				productsInBill2, 
				19, // iva porcentaje
				"167657839.03", // subtotal
				"1237123.21", // iva
				"123141231.43" // total
		});
		this.listbills.add(new Object[] { LocalDate.of(2022, 2, 21), // fecha factura
				20221001, // numero factura
				"Daniel Felipe Suarez Bohorquez", // cliente
				"CC", 1002587073, // id cliente
				"Crisitan David Parada Martinez", // cajero
				1002365941, // id cajero
				"Minoritario", "Efectivo", productsInBill, // subtotal
				19, // iva porcentaje
				"167657839.03", // subtotal
				"1237123.21", // iva
				"123141231.43" // total
		});
		this.listbills.add(new Object[] { LocalDate.of(2022, 2, 21), // fecha factura
				20221001, // numero factura
				"Daniel Felipe Suarez Bohorquez", // cliente
				"CC", 1002587073, // id cliente
				"Crisitan David Parada Martinez", // cajero
				1002365941, // id cajero
				"Minoritario", "Efectivo", productsInBill, // subtotal
				19, // iva porcentaje
				"167657839.03", // subtotal
				"1237123.21", // iva
				"123141231.43" // total
		});
		this.listbills.add(new Object[] { LocalDate.of(2022, 2, 21), // fecha factura
				20221001, // numero factura
				"Daniel Felipe Suarez Bohorquez", // cliente
				"CC", 1002587073, // id cliente
				"Crisitan David Parada Martinez", // cajero
				1002365941, // id cajero
				"Minoritario", "Efectivo", productsInBill, // subtotal
				19, // iva porcentaje
				"167657839.03", // subtotal
				"1237123.21", // iva
				"123141231.43" // total
		});
		this.listbills.add(new Object[] { LocalDate.of(2021, 2, 21), // fecha factura
				20221001, // numero factura
				"Daniel Felipe Suarez Bohorquez", // cliente
				"CC", 1002587073, // id cliente
				"Crisitan David Parada Martinez", // cajero
				1002365941, // id cajero
				"Minoritario", "Efectivo", productsInBill, // subtotal
				19, // iva porcentaje
				"167657839.03", // subtotal
				"1237123.21", // iva
				"123141231.43" // total
		});
		this.listbills.add(new Object[] { LocalDate.of(2020, 2, 21), // fecha factura
				20221001, // numero factura
				"Daniel Felipe Suarez Bohorquez", // cliente
				"CC", 1002587073, // id cliente
				"Crisitan David Parada Martinez", // cajero
				1002365941, // id cajero
				"Minoritario", "Efectivo", productsInBill, // subtotal
				19, // iva porcentaje
				"167657839.03", // subtotal
				"1237123.21", // iva
				"123141231.43" // total
		});
		this.listbills.add(new Object[] { LocalDate.of(2022, 2, 21), // fecha factura
				20221001, // numero factura
				"Daniel Felipe Suarez Bohorquez", // cliente
				"CC", 1002587073, // id cliente
				"Crisitan David Parada Martinez", // cajero
				1002365941, // id cajero
				"Minoritario", "Efectivo", productsInBill, // subtotal
				19, // iva porcentaje
				"167657839.03", // subtotal
				"1237123.21", // iva
				"123141231.43" // total
		});
		this.listbills.add(new Object[] { LocalDate.of(2022, 2, 21), // fecha factura
				20221001, // numero factura
				"Daniel Felipe Suarez Bohorquez", // cliente
				"CC", 1002587073, // id cliente
				"Crisitan David Parada Martinez", // cajero
				1002365941, // id cajero
				"Minoritario", "Efectivo", productsInBill, // subtotal
				19, // iva porcentaje
				"167657839.03", // subtotal
				"1237123.21", // iva
				"123141231.43" // total
		});
		this.listbills.add(new Object[] { LocalDate.of(2022, 2, 21), // fecha factura
				20221001, // numero factura
				"Daniel Felipe Suarez Bohorquez", // cliente
				"CC", 1002587073, // id cliente
				"Crisitan David Parada Martinez", // cajero
				1002365941, // id cajero
				"Minoritario", "Efectivo", productsInBill, // subtotal
				19, // iva porcentaje
				"167657839.03", // subtotal
				"1237123.21", // iva
				"123141231.43" // total
		});
		this.listbills.add(new Object[] { LocalDate.of(2022, 2, 21), // fecha factura
				20221001, // numero factura
				"Daniel Felipe Suarez Bohorquez", // cliente
				"CC", 1002587073, // id cliente
				"Crisitan David Parada Martinez", // cajero
				1002365941, // id cajero
				"Minoritario", "Efectivo", productsInBill, // subtotal
				19, // iva porcentaje
				"167657839.03", // subtotal
				"1237123.21", // iva
				"123141231.43" // total
		});
		this.listbills.add(new Object[] { LocalDate.of(2022, 2, 21), // fecha factura
				20221001, // numero factura
				"Daniel Felipe Suarez Bohorquez", // cliente
				"CC", 1002587073, // id cliente
				"Crisitan David Parada Martinez", // cajero
				1002365941, // id cajero
				"Minoritario", "Efectivo", productsInBill, // subtotal
				19, // iva porcentaje
				"167657839.03", // subtotal
				"1237123.21", // iva
				"123141231.43" // total
		});
	}

	/**
	 * Metodo que actualiza el historial de ventas
	 * @param salesHistory
	 */
	public void actualizeSalesHistory(ArrayList<Object[]> salesHistory) {
		this.listbills = salesHistory;
		this.listPanelBills = new ArrayList<JPanel>();
		this.listTableBills = new ArrayList<Object[]>();
		setvisibleAll(false);
		addBills();
	}
}
