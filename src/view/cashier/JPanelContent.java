package view.cashier;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;

import controller.Controller;
import view.JFrameMain;
import view.JPanelTableBill;
import view.admin.IListener;
import view.utils.Constants;

@SuppressWarnings("deprecation")
public class JPanelContent extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel jPanelTopBill;
	private JPanel jPanelInfoBill;
	private GridBagConstraints gbc;
	// JPANEL TOP
	private JLabel jLabelPencilIcon;
	private JLabel jLabelNewBill;
	private JLabel jLabelNumberBill;
	private JLabel jLabelDate;
	// JPANEL INFO
	private JLabel jLabelClient;
	private JComboBox<String> jComboBoxClient_ID; //
	private JLabel jLabelCashier_CC;
	private JLabel jLabelCashier;
	private JLabel jLabelTypeUser; //
	private JLabel jLabelPay;
	private JTextField jTextFieldClient_Name;
	private JTextField jTextFieldCashier_Name;
	private JTextField jTextFieldClient_ID;
	private JTextField jTextFieldCashier_CC;
	private JComboBox<String> jComboBoxTypeUser; //
	private JComboBox<String> jComboBoxClient_Pay;
	private JButton buttonAddProduct;
	private JButton buttonPrintBill;
	private JPanelTableBill jPanelTableBill;
	private String[] typeUsers;
	private IListener iListener;

	public JPanelContent(String name, int id, int numberBill, String[] typeUsers) {
		super(new BorderLayout());
		this.iListener = Controller.getInstance();
		this.typeUsers = typeUsers;
		this.jPanelTopBill = new JPanel(new GridBagLayout());
		this.jPanelInfoBill = new JPanel(new GridBagLayout());
		this.gbc = new GridBagConstraints();
		this.jLabelPencilIcon = new JLabel(
				new ImageIcon(new ImageIcon("res/pencilIcon.png").getImage().getScaledInstance(
						60 * JFrameMain.WIDTH_SIZE / 1920, 65 * JFrameMain.HEIGTH_SIZE / 1080, Image.SCALE_SMOOTH)));
		this.jLabelNewBill = new JLabel(Constants.NUEVA_FACTURA);
		this.jLabelNumberBill = new JLabel("N. " + numberBill, JLabel.CENTER); // TODO
		this.jLabelDate = new JLabel(
				"Fecha: " + String.format("%02d", LocalDate.now().getDayOfMonth()) + "/"
						+ String.format("%02d", LocalDate.now().getMonthValue()) + "/" + LocalDate.now().getYear(),
				JLabel.CENTER);
		this.jLabelClient = new JLabel(Constants.CLIENTE);
		this.jComboBoxClient_ID = new JComboBox<String>();
		this.jLabelCashier_CC = new JLabel(Constants.CC);
		this.jLabelCashier = new JLabel(Constants.CAJERO, JLabel.RIGHT);
		this.jLabelTypeUser = new JLabel(Constants.TYPE_USER);
		this.jLabelPay = new JLabel(Constants.PAGO, JLabel.RIGHT);
		this.jTextFieldClient_Name = new JTextField();
		this.jTextFieldCashier_Name = new JTextField(name);
		this.jTextFieldClient_ID = new JTextField();
		this.jTextFieldCashier_CC = new JTextField(id);
		this.jComboBoxTypeUser = new JComboBox<String>();
		this.jComboBoxClient_Pay = new JComboBox<String>();
		this.buttonAddProduct = new JButton();
		this.buttonPrintBill = new JButton();
		this.jPanelTableBill = new JPanelTableBill((byte) 19);
		init();
	}

	/**
	 * Metodo que inicializa los componentes de este panel
	 */
	private void init() {
		this.setOpaque(false);
		initInfoTop();
		this.add(jPanelTopBill, BorderLayout.NORTH);
		initInfoCentral();
		initButtons();
		this.add(jPanelInfoBill, BorderLayout.CENTER);
	}

	/**
	 * Metodo que inicializa las propiedades de los botones
	 */
	private void initButtons() {
		this.buttonAddProduct.setForeground(Color.BLACK);
		this.buttonAddProduct.setFont(new Font("Arial", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		this.buttonAddProduct.setBackground(new Color(157, 195, 230));
		this.buttonAddProduct.setRolloverEnabled(false);
		this.buttonAddProduct.setFocusable(false);
		this.buttonAddProduct.setBorder(new LineBorder(new Color(48, 116, 180), 3));
		this.buttonAddProduct.setIcon(new ImageIcon(
				new ImageIcon("res/searchIcon.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		this.buttonAddProduct.setText(Constants.AGREGAR_PRODUCTO);
		this.buttonAddProduct.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.buttonAddProduct.addActionListener(JFrameMain.getInstance());
		this.buttonAddProduct.setActionCommand(Constants.ADD_PRODUCT_BILL);
		this.buttonPrintBill.setForeground(Color.BLACK);
		this.buttonPrintBill.setFont(new Font("Arial", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		this.buttonPrintBill.setBorder(new LineBorder(new Color(48, 116, 180), 3));
		this.buttonPrintBill.setBackground(new Color(157, 195, 230));
		this.buttonPrintBill.setRolloverEnabled(false);
		this.buttonPrintBill.setFocusable(false);
		this.buttonPrintBill.setIcon(new ImageIcon(
				new ImageIcon("res/printIcon.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		this.buttonPrintBill.setText(Constants.IMPRIMIR);
		this.buttonPrintBill.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.buttonPrintBill.addActionListener(Controller.getInstance());
		this.buttonPrintBill.setActionCommand(Constants.COMMAND_PRINT_BILL);
	}

	/**
	 * Metodo que inicializa la informacion principal de la factura
	 */
	private void initInfoCentral() {
		initPropJTextFields();
		this.jPanelInfoBill.setOpaque(false);
		gbc.gridx = 0;
		gbc.weightx = 0;
		gbc.insets.left = 20;
		this.jPanelInfoBill.add(jLabelClient, gbc);
		gbc.insets.left = 0;
		gbc.gridx = 1;
		gbc.weightx = 1;
		this.jPanelInfoBill.add(jTextFieldClient_Name, gbc);
		gbc.weightx = 0;
		gbc.gridx = 2;
		gbc.insets.left = 20;
		gbc.insets.right = 10;
		this.jPanelInfoBill.add(jComboBoxClient_ID, gbc);
		gbc.insets.right = 0;
		gbc.insets.left = 0;
		gbc.weightx = 1;
		gbc.gridx = 3;
		this.jPanelInfoBill.add(jTextFieldClient_ID, gbc);
		gbc.gridx = 4;
		gbc.weightx = 0;
		gbc.insets.left = 20;
		this.jPanelInfoBill.add(jLabelTypeUser, gbc);
		gbc.insets.left = 0;
		gbc.gridx = 5;
		gbc.weightx = 1;
		gbc.insets.right = 20;
		this.jPanelInfoBill.add(jComboBoxTypeUser, gbc);
		// next line
		gbc.insets.right = 0;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
		gbc.insets.left = 20;
		gbc.insets.top = 20;
		this.jPanelInfoBill.add(jLabelCashier, gbc);
		gbc.insets.left = 0;
		gbc.gridx = 1;
		gbc.weightx = 1;
		this.jPanelInfoBill.add(jTextFieldCashier_Name, gbc);
		gbc.weightx = 0;
		gbc.gridx = 2;
		gbc.insets.left = 20;
		this.jPanelInfoBill.add(jLabelCashier_CC, gbc);
		gbc.insets.left = 0;
		gbc.weightx = 1;
		gbc.gridx = 3;
		this.jPanelInfoBill.add(jTextFieldCashier_CC, gbc);
		gbc.gridx = 4;
		gbc.weightx = 0;
		gbc.insets.left = 20;
		this.jPanelInfoBill.add(jLabelPay, gbc);
		gbc.insets.left = 0;
		gbc.gridx = 5;
		gbc.weightx = 1;
		gbc.insets.right = 20;
		this.jPanelInfoBill.add(jComboBoxClient_Pay, gbc);
		// next line
		gbc.insets.right = 0;
		gbc.gridwidth = 4;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = 0;
		gbc.weightx = 0;
		this.jPanelInfoBill.add(buttonAddProduct, gbc);
		gbc.insets.right = 20;
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.EAST;
		this.jPanelInfoBill.add(buttonPrintBill, gbc);
		// next line
		gbc.insets.left = 20;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridy = 3;
		gbc.gridwidth = 6;
		gbc.fill = 1;
		this.jPanelInfoBill.add(jPanelTableBill, gbc);

	}

	/**
	 * Metodo que inicializa las propiedades de los jtextfield
	 */
	private void initPropJTextFields() {
		this.jTextFieldClient_Name.setForeground(Color.BLACK);
		this.jTextFieldClient_Name.setFont(new Font("Arial", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		this.jTextFieldClient_Name.setPreferredSize(new Dimension(0, 25));
		this.jTextFieldClient_Name.setBorder(new LineBorder(new Color(157, 195, 230), 3));
		this.jTextFieldCashier_Name.setForeground(Color.DARK_GRAY);
		this.jTextFieldCashier_Name.setFont(new Font("Arial", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		this.jTextFieldCashier_Name.setPreferredSize(new Dimension(0, 25));
		this.jTextFieldCashier_Name.setBorder(new LineBorder(new Color(157, 195, 230), 3));
		this.jTextFieldCashier_Name.setEditable(false);
		this.jTextFieldClient_ID.setForeground(Color.BLACK);
		this.jTextFieldClient_ID.setFont(new Font("Arial", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		this.jTextFieldClient_ID.setPreferredSize(new Dimension(0, 25));
		this.jTextFieldClient_ID.setBorder(new LineBorder(new Color(157, 195, 230), 3));
		this.jTextFieldClient_ID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if ((int)e.getKeyChar() == 10) {
					iListener.iEvent(Constants.COMMAND_SEARCH_CLIENT);
				}
				if (!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		@SuppressWarnings("static-access")
		InputMap map = jTextFieldClient_ID.getInputMap(jTextFieldClient_ID.WHEN_FOCUSED);
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");

		this.jTextFieldCashier_CC.setForeground(Color.DARK_GRAY);
		this.jTextFieldCashier_CC.setFont(new Font("Arial", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		this.jTextFieldCashier_CC.setPreferredSize(new Dimension(0, 25));
		this.jTextFieldCashier_CC.setBorder(new LineBorder(new Color(157, 195, 230), 3));
		this.jTextFieldCashier_CC.setEditable(false);
		this.jComboBoxTypeUser.setForeground(Color.BLACK);
		this.jComboBoxTypeUser.setFont(new Font("Arial", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		this.jComboBoxTypeUser.setPreferredSize(new Dimension(0, 25));
		this.jComboBoxTypeUser.setBorder(new LineBorder(new Color(157, 195, 230), 3));
		for (String item : typeUsers) {
			this.jComboBoxTypeUser.addItem(item);
		}
		this.jComboBoxTypeUser.addActionListener(Controller.getInstance());
		this.jComboBoxTypeUser.setActionCommand(Constants.COMMAND_COMBOBOX_TYPECLIENT);

		this.jComboBoxClient_Pay.setForeground(Color.BLACK);
		this.jComboBoxClient_Pay.setFont(new Font("Arial", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		this.jComboBoxClient_Pay.setPreferredSize(new Dimension(0, 25));
		this.jComboBoxClient_Pay.setBorder(new LineBorder(new Color(157, 195, 230), 3));
		this.jComboBoxClient_Pay.addItem(Constants.EFECTIVO);
		this.jComboBoxClient_Pay.addItem(Constants.TARJETA_CREDITO);
		this.jComboBoxClient_Pay.addItem(Constants.TARJETA_DEBITO);
	}

	/**
	 * Metodo que agrega los componentes de la cabecera
	 */
	private void initInfoTop() {
		initPropLabels();
		this.jPanelTopBill.setOpaque(false);
		gbc.fill = 1;
		gbc.weightx = 0;
		gbc.insets.left = 10;
		this.jPanelTopBill.add(jLabelPencilIcon, gbc);
		gbc.insets.left = 0;
		gbc.weightx = 1;
		gbc.gridx = 1;
		this.jPanelTopBill.add(jLabelNewBill, gbc);
		gbc.gridx = 2;
		this.jPanelTopBill.add(jLabelNumberBill, gbc);
		gbc.gridx = 3;
		this.jPanelTopBill.add(jLabelDate, gbc);
	}

	private void initPropLabels() {
		this.jLabelNewBill.setForeground(Color.WHITE);
		this.jLabelNewBill.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 35 * JFrameMain.WIDTH_SIZE / 1920));
		this.jLabelNumberBill.setForeground(Color.WHITE);
		this.jLabelNumberBill.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 35 * JFrameMain.WIDTH_SIZE / 1920));
		this.jLabelDate.setForeground(Color.WHITE);
		this.jLabelDate.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 35 * JFrameMain.WIDTH_SIZE / 1920));
		this.jLabelClient.setForeground(new Color(47, 85, 151));
		this.jLabelClient.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 25 * JFrameMain.WIDTH_SIZE / 1920));
		this.jComboBoxClient_ID.setPreferredSize(new Dimension(80, 25));
		this.jComboBoxClient_ID.setForeground(Color.BLACK);
		this.jComboBoxClient_ID.setFont(new Font("Arial", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		this.jComboBoxClient_ID.addItem("CC");
		this.jComboBoxClient_ID.addItem("NIT");
		this.jComboBoxClient_ID.setBorder(new LineBorder(new Color(157, 195, 230), 3));

		this.jLabelCashier_CC.setForeground(new Color(47, 85, 151));
		this.jLabelCashier_CC.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 25 * JFrameMain.WIDTH_SIZE / 1920));
		this.jLabelCashier.setForeground(new Color(47, 85, 151));
		this.jLabelCashier.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 25 * JFrameMain.WIDTH_SIZE / 1920));
		this.jLabelTypeUser.setForeground(new Color(47, 85, 151));
		this.jLabelTypeUser.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 25 * JFrameMain.WIDTH_SIZE / 1920));
		this.jLabelPay.setForeground(new Color(47, 85, 151));
		this.jLabelPay.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 25 * JFrameMain.WIDTH_SIZE / 1920));
	}

	/**
	 * Metodo que cambia la informacion del usuario
	 * @param name nombre 
	 * @param id identificacion
	 */
	public void setInfoUser(String name, int id) {
		this.jTextFieldCashier_Name.setText(name);
		this.jTextFieldCashier_CC.setText(id + "");
	}

	/**
	 * Metodo que agrega un producto a la factura
	 * @param productInfo informacion del producto
	 */
	public void addProductToBill(Object[] productInfo) {
		jPanelTableBill.addProductToBill(productInfo);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(48, 116, 180));
		g2d.fillRect(0, 0, getWidth(), JFrameMain.HEIGTH_SIZE * 75 / 1080);
		super.paint(g);
	}

	/**
	 * Metodo que obtiene la informacion de todos los productos de la factura
	 * @return lista de informacion de los productos
	 */
	public ArrayList<Object[]> getProductsInBill() {
		return jPanelTableBill.getProductsInBill();
	}

	/**
	 * Metodo que actualiza la informacion de la factura
	 * @param productsInBill productos a actualizar en la factura
	 */
	public void actualizeBill(ArrayList<Object[]> productsInBill) {
		jPanelTableBill.actualizeBill(productsInBill);
	}

	/**
	 * Metodo que obtiene la infrmacion de la factura
	 * @return informacion de la factura
	 */
	public Object[] getinfoTotalBill() {
		return new Object[] { 
				jLabelDate.getText(),
				Integer.parseInt(jLabelNumberBill.getText().substring(3)),
				jTextFieldClient_Name.getText(),
				jComboBoxClient_ID.getSelectedItem(),
				jTextFieldClient_ID.getText(),
				jTextFieldCashier_Name.getText(),
				jTextFieldCashier_CC.getText(),
				jComboBoxTypeUser.getSelectedItem(),
				jComboBoxClient_Pay.getSelectedItem() };
	}

	/**
	 * Metodo que subraya el jtextfield del nombre del cliente
	 */
	public void underLineClientName() {
		this.jTextFieldClient_Name.setBorder(new LineBorder(Color.RED, 3));
	}

	/**
	 * Metodo que quita el subrayado del jtextfield del nombre del cliente
	 */
	public void resetUnderLineClientName() {
		this.jTextFieldClient_Name.setBorder(new LineBorder(new Color(157, 195, 230), 3));
	}

	/**
	 * Metodo que subraya el jtextfield del id del cliente
	 */
	public void underLineClientID() {
		this.jTextFieldClient_ID.setBorder(new LineBorder(Color.RED, 3));
	}

	/**
	 * Metodo que quita el subrayado del jtextfield del id del cliente
	 */
	public void resetUnderLineClientID() {
		this.jTextFieldClient_ID.setBorder(new LineBorder(new Color(157, 195, 230), 3));
	}

	/**
	 * Metodo que obtiene la informacion de pago de la factura
	 * @return informacion de pago
	 */
	public Object[] getInfoPayBill() {
		return jPanelTableBill.getInfoPayBill();
	}

	/**
	 * Metodo que reinicia la factura y pone el nuevo numero de factura
	 * @param numberBill numero de factura
	 */
	public void newBill(int numberBill) {
		this.jLabelNumberBill.setText("N. " + numberBill);
		this.jTextFieldClient_ID.setText("");
		this.jTextFieldClient_Name.setText("");
		this.jComboBoxClient_ID.setSelectedIndex(0);
		this.jComboBoxClient_Pay.setSelectedIndex(0);
		this.jComboBoxTypeUser.setSelectedIndex(0);
	}

	/**
	 * Metodo que remueve los productos de la factura
	 */
	public void removeProductsBill() {
		this.jPanelTableBill.removeProductsBill();
	}

	/**
	 * Metodo que actualiza los totales de la factura
	 * @param totalValues valores totales de la factura
	 */
	public void actualizeTotalValues(Object[] totalValues) {
		jPanelTableBill.actualizeTotalValues(totalValues);
	}

	/**
	 * Metodo que retorna 1 si es minoritario y 2 si es mayoritario
	 * @return 1 o 2
	 */
	public int getClietTypeOrdinal() {
		return jComboBoxTypeUser.getSelectedItem().equals("Minoritario")?1:2;
	}

	/**
	 * Metodo que obtiene el id del cliente
	 * @return id del cliente
	 */
	public String getIdClient() {
		return jTextFieldClient_ID.getText();
	}

	/**
	 * Metodo que cambia el nombre del cliente
	 * @param name nombre del clienteAE
	 */
	public void setNameClient(String name) {
		this.jTextFieldClient_Name.setText(name);
	}

	public void changeNumBill(int numberBill) {
		this.jLabelNumberBill.setText(numberBill+"");
	}


}
