package view.cashier;

import java.awt.BasicStroke;
import java.awt.Color;
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
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.Controller;
import view.JFrameMain;
import view.admin.JPanelContentAdmin;
import view.utils.Constants;
import view.utils.JButtonAS;

/**
 * @author CRISTIAN DAVID PARADA MARTINEZ
 */
public class JPanelUser extends JPanel {

	private static final long serialVersionUID = 1L;
	private GridBagConstraints gbc;
	private ImageIcon backGround;
	private JPanel jPanelTop;
	private JPanelContentAdmin jPanelContentAdmin;
	private JPanelContent jPanelContent;
	private JPanelInventory jPanelInventory;
	private JPanelSalesHistoryTable historyTable;
	private JLabel jLabellogo;
	private JLabel jLabelInfoUser;
	private JLabel jlabelTypeUser;
	private JButtonAS jbuttonLogOut;
	private JButtonAS jbuttonMinimize;
	private JButton buttonBillPanel;
	private JButton buttonSalesPanel;
	private boolean isAdmin;

	/**
	 * Constructor de JPanelUser
	 * @param numberBill numero de factura
	 * @param listProducts lista de productos del inventario
	 * @param typeUsers lista de tipos de usuarios 
	 * @param salesHistory lista de historial de ventas
	 */
	public JPanelUser(int numberBill, ArrayList<Object[]> listProducts, String[] typeUsers, ArrayList<Object[]> salesHistory) {
		super(new GridBagLayout());
		this.isAdmin = false;
		this.historyTable = new JPanelSalesHistoryTable(salesHistory);
		this.historyTable.setVisible(false);
		this.buttonBillPanel = new JButton("Facturas");
		this.buttonSalesPanel = new JButton("Historial de Ventas");
		this.gbc = new GridBagConstraints();
		this.backGround = new ImageIcon("res/backgroundUser.png");
		this.jPanelTop = new JPanel(new GridBagLayout());
		this.jPanelContent = new JPanelContent("", 0, numberBill, typeUsers);
		this.jLabellogo = new JLabel(
				new ImageIcon(new ImageIcon("res/logo.png").getImage().getScaledInstance(
						400 * JFrameMain.WIDTH_SIZE / 1920, 100 * JFrameMain.HEIGTH_SIZE / 1080, Image.SCALE_SMOOTH)),
				JLabel.LEFT);
		this.jLabelInfoUser = new JLabel("Cristian David Parada Martinez", JLabel.CENTER); // TODO
		this.jlabelTypeUser = new JLabel("Cajero:", JLabel.CENTER); // TODO
		this.jbuttonLogOut = new JButtonAS("Cerrar Sesion", Color.BLACK, 1,
				new Dimension(231 * JFrameMain.WIDTH_SIZE / 1920, 68 * JFrameMain.HEIGTH_SIZE / 1080));
		this.jbuttonMinimize = new JButtonAS("-", Color.BLACK, 1,
				new Dimension(77 * JFrameMain.WIDTH_SIZE / 1920, 68 * JFrameMain.HEIGTH_SIZE / 1080));
		this.jPanelInventory = new JPanelInventory(listProducts);
		initCashier();
	}
	
	/**
	 * Constructor de JPanelUser
	 * @param quantyCashiers cantidad de cajeros activos
	 * @param quantyProducts cantidad de productos activos
	 * @param listProducts lista de productos
	 */
	public JPanelUser(int quantyCashiers, int quantyProducts, ArrayList<Object[]> listProducts) {
		super(new GridBagLayout());
		this.isAdmin = true;
		this.backGround = new ImageIcon("res/backgroundUser.png");
		this.jPanelTop = new JPanel(new GridBagLayout());
		this.jLabellogo = new JLabel(
				new ImageIcon(new ImageIcon("res/logo.png").getImage().getScaledInstance(
						400 * JFrameMain.WIDTH_SIZE / 1920, 100 * JFrameMain.HEIGTH_SIZE / 1080, Image.SCALE_SMOOTH)),
				JLabel.LEFT);
		this.jLabelInfoUser = new JLabel("Cristian David Parada Martinez", JLabel.CENTER); // TODO
		this.jlabelTypeUser = new JLabel("Administrador:", JLabel.CENTER); // TODO
		this.jbuttonLogOut = new JButtonAS("Cerrar Sesion", Color.BLACK, 1,
				new Dimension(231 * JFrameMain.WIDTH_SIZE / 1920, 68 * JFrameMain.HEIGTH_SIZE / 1080));
		this.jbuttonMinimize = new JButtonAS("-", Color.BLACK, 1,
				new Dimension(77 * JFrameMain.WIDTH_SIZE / 1920, 68 * JFrameMain.HEIGTH_SIZE / 1080));
		this.jPanelInventory = new JPanelInventory(listProducts);
		this.jPanelContentAdmin = new JPanelContentAdmin(quantyCashiers, quantyProducts);
		this.gbc = new GridBagConstraints();
		initAdmin();
	}

	/**
	 * Metodo que inicia la vista de administrador
	 */
	private void initAdmin() {
		this.setOpaque(false);
		initTopProperties();
		gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.fill = 1;
		gbc.gridwidth = 2;
		this.add(jPanelTop, gbc);
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.gridy = 1;
		gbc.insets.top = 52 * JFrameMain.HEIGTH_SIZE / 1080;
		gbc.insets.left = 52;
		gbc.insets.right = 52;
		gbc.insets.bottom = 56* JFrameMain.HEIGTH_SIZE / 1080;
		jPanelContentAdmin.setPreferredSize(
				new Dimension(JFrameMain.WIDTH_SIZE, JFrameMain.HEIGTH_SIZE - JFrameMain.HEIGTH_SIZE / 6));
		this.add(jPanelContentAdmin, gbc);
	}

	/**
	 * Metodo que inicia la vista de cajero
	 */
	private void initCashier() {
		this.setOpaque(false);
		initTopProperties();
		gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.fill = 1;
		gbc.gridwidth = 2;
		this.add(jPanelTop, gbc);
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.gridy = 1;
		gbc.insets.top = 52 * JFrameMain.HEIGTH_SIZE / 1080;
		gbc.insets.left = 52;
		gbc.insets.right = 52;
		historyTable.setPreferredSize(
				new Dimension(JFrameMain.WIDTH_SIZE, JFrameMain.HEIGTH_SIZE - JFrameMain.HEIGTH_SIZE / 6));
		jPanelInventory.setPreferredSize(
				new Dimension(JFrameMain.WIDTH_SIZE, JFrameMain.HEIGTH_SIZE - JFrameMain.HEIGTH_SIZE / 6));
		jPanelContent.setPreferredSize(
				new Dimension(JFrameMain.WIDTH_SIZE, JFrameMain.HEIGTH_SIZE - JFrameMain.HEIGTH_SIZE / 6));
		this.add(jPanelContent, gbc);
		initPropButtonsBottom();
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.insets.top = 30;
		gbc.insets.left = 0;
		gbc.insets.right = 0;
		gbc.insets.bottom = 0;
		gbc.gridy = 2;
		this.add(buttonBillPanel, gbc);
		gbc.gridx = 1;
		this.add(buttonSalesPanel, gbc);
	}

	/**
	 * Metodo que inicia las propiedades del panel cabecera
	 */
	private void initTopProperties() {
		initPropLabels();
		initPropButtons();
		this.jPanelTop.setOpaque(false);
		gbc.fill = 1;
		gbc.gridheight = 2;
		gbc.insets.left = 20;
		this.jPanelTop.add(jLabellogo, gbc);
		gbc.insets.left = 0;
		gbc.gridx = 1;
		gbc.weightx = 1;
		gbc.gridheight = 1;
		gbc.weighty = 1;
		jlabelTypeUser.setVerticalAlignment(JLabel.BOTTOM);
		gbc.insets.right = (JFrameMain.WIDTH_SIZE / 20) * JFrameMain.WIDTH_SIZE / 1920;
		this.jPanelTop.add(jlabelTypeUser, gbc);
		gbc.weightx = 0;
		gbc.gridy = 1;
		jLabelInfoUser.setVerticalAlignment(JLabel.TOP);
		this.jPanelTop.add(jLabelInfoUser, gbc);
		gbc.weighty = 0;
		gbc.fill = 0;
		gbc.gridheight = 2;
		gbc.gridy = 0;
		gbc.gridx = 2;
		gbc.insets.right = 5;
		this.jPanelTop.add(jbuttonMinimize, gbc);
		gbc.insets.left = 5;
		gbc.gridx = 3;
		gbc.insets.right = 20;
		this.jPanelTop.add(jbuttonLogOut, gbc);

	}

	/**
	 * Metodo que inicia las propiedades de los botones
	 */
	private void initPropButtons() {
		this.jbuttonLogOut.setForeground(Color.WHITE);
		this.jbuttonLogOut.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20 * JFrameMain.WIDTH_SIZE / 1920));
		this.jbuttonLogOut.setBackground(new Color(80, 80, 80));
		this.jbuttonLogOut.addActionListener(Controller.getInstance());
		this.jbuttonLogOut.setActionCommand(Constants.COMMAND_LOG_OUT);
		this.jbuttonMinimize.setForeground(Color.WHITE);
		this.jbuttonMinimize.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20 * JFrameMain.WIDTH_SIZE / 1920));
		this.jbuttonMinimize.setBackground(new Color(80, 80, 80));
		this.jbuttonMinimize.addActionListener(JFrameMain.getInstance());
		this.jbuttonMinimize.setActionCommand(Constants.COMMAND_MINIMIZE);

	}

	/**
	 * Metodo que inicia las propiedades de los botones de abajo
	 */
	private void initPropButtonsBottom() {
		this.buttonBillPanel.setForeground(Color.WHITE);
		this.buttonBillPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.WHITE));
		this.buttonBillPanel.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		this.buttonBillPanel.setContentAreaFilled(false);
		this.buttonBillPanel.setFocusable(false);
		this.buttonBillPanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchToBillTable();
			}

		});
		this.buttonBillPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonBillPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 2, new Color(0, 156, 255)));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				buttonBillPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 2, Color.WHITE));
			}
		});

		this.buttonSalesPanel.setForeground(Color.WHITE);
		this.buttonSalesPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.WHITE));
		this.buttonSalesPanel
				.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		this.buttonSalesPanel.setContentAreaFilled(false);
		this.buttonSalesPanel.setFocusable(false);
		this.buttonSalesPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonSalesPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 0, Color.MAGENTA));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				buttonSalesPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.WHITE));
			}
		});
		this.buttonSalesPanel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switchToSalesHistoryTable();
			}

		});
	}

	/**
	 * Metodo que cambia a la vista de historial de ventas
	 */
	private void switchToSalesHistoryTable() {
		if (!historyTable.isVisible()) {
			this.historyTable.setVisible(true);
			this.jPanelInventory.setVisible(false);
			this.jPanelContent.setVisible(false);
			gbc.gridx = 0;
			gbc.weightx = 0;
			gbc.weighty = 1;
			gbc.gridwidth = 2;
			gbc.gridy = 1;
			gbc.insets.top = 52 * JFrameMain.HEIGTH_SIZE / 1080;
			gbc.insets.left = 52;
			gbc.insets.right = 52;
			gbc.insets.bottom = 20;
			this.add(historyTable, gbc);
			gbc.gridwidth = 1;
			gbc.weightx = 1;
			gbc.weighty = 0;
			gbc.insets.top = 30;
			gbc.insets.left = 0;
			gbc.insets.right = 0;
			gbc.insets.bottom = 0;
			gbc.gridy = 2;
			this.add(buttonBillPanel, gbc);
			gbc.gridx = 1;
			this.add(buttonSalesPanel, gbc);
		}
	}

	/**
	 * Metodo que cambia a la vista de la factura
	 */
	private void switchToBillTable() {
		if (!jPanelContent.isVisible()) {
			this.historyTable.setVisible(false);
			this.jPanelInventory.setVisible(false);
			this.jPanelContent.setVisible(true);
			gbc.gridx = 0;
			gbc.weightx = 0;
			gbc.weighty = 1;
			gbc.gridwidth = 2;
			gbc.gridy = 1;
			gbc.insets.top = 46 * JFrameMain.HEIGTH_SIZE / 1080;
			gbc.insets.left = 52;
			gbc.insets.right = 52;
			this.add(jPanelContent, gbc);
			gbc.gridwidth = 1;
			gbc.weightx = 1;
			gbc.weighty = 0;
			gbc.insets.top = 30;
			gbc.insets.left = 0;
			gbc.insets.right = 0;
			gbc.insets.bottom = 0;
			gbc.gridy = 2;
			this.add(buttonBillPanel, gbc);
			gbc.gridx = 1;
			this.add(buttonSalesPanel, gbc);
		}
	}

	/**
	 * Metodo que inicializa los labels 
	 */
	private void initPropLabels() {
		this.jLabelInfoUser.setForeground(Color.WHITE);
		this.jLabelInfoUser.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20 * JFrameMain.WIDTH_SIZE / 1920));
		if (isAdmin) {
			this.jlabelTypeUser.setForeground(Color.YELLOW);
		}else {
			this.jlabelTypeUser.setForeground(Color.CYAN);
		}
		this.jlabelTypeUser.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20 * JFrameMain.WIDTH_SIZE / 1920));
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(backGround.getImage(), 0, 0, JFrameMain.WIDTH_SIZE, JFrameMain.HEIGTH_SIZE, this);
		g2d.setColor(new Color(40, 40, 40, 220));
		g2d.fillRect(0, 0, JFrameMain.WIDTH_SIZE, JFrameMain.HEIGTH_SIZE / 10);
		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(Color.BLACK);
		g2d.drawLine(0, JFrameMain.HEIGTH_SIZE / 10, JFrameMain.WIDTH_SIZE, JFrameMain.HEIGTH_SIZE / 10);
		g2d.setColor(Color.WHITE);
		g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 2.0f, new float[] { 20 }, 0.0f));
		g2d.drawLine(JFrameMain.WIDTH_SIZE / 3, 0, JFrameMain.WIDTH_SIZE / 3, JFrameMain.HEIGTH_SIZE / 10);
		g2d.drawLine(JFrameMain.WIDTH_SIZE / 3 * 2, 0, JFrameMain.WIDTH_SIZE / 3 * 2, JFrameMain.HEIGTH_SIZE / 10);
		g2d.setColor(new Color(255, 255, 255, 220));
		g2d.fillRect(50, JFrameMain.HEIGTH_SIZE / 7, JFrameMain.WIDTH_SIZE - 100,
				JFrameMain.HEIGTH_SIZE - JFrameMain.HEIGTH_SIZE / 5);
		g2d.setColor(new Color(48, 116, 180));
		g2d.setStroke(new BasicStroke(6));
		g2d.drawRect(50, JFrameMain.HEIGTH_SIZE / 7, JFrameMain.WIDTH_SIZE - 100,
				JFrameMain.HEIGTH_SIZE - JFrameMain.HEIGTH_SIZE / 5);
		if (!isAdmin) {
			GradientPaint gradientPaint = new GradientPaint(500f, 0f, new Color(16, 75, 91), 0f, 0f,
					new Color(39, 146, 207));
			g2d.setPaint(gradientPaint);
			g2d.fillRect(0, JFrameMain.HEIGTH_SIZE - buttonBillPanel.getHeight(), buttonBillPanel.getWidth(),
					buttonBillPanel.getHeight());
			GradientPaint gradientPaint2 = new GradientPaint(1920f, 0f, new Color(92, 19, 85), 0f, 0f,
					new Color(238, 55, 221));
			g2d.setPaint(gradientPaint2);
			g2d.fillRect(buttonBillPanel.getWidth(), JFrameMain.HEIGTH_SIZE - buttonSalesPanel.getHeight(),
					buttonSalesPanel.getWidth(), buttonSalesPanel.getHeight());
		}
		super.paint(g);
	}

	/**
	 * Metodo que cambia a la vista de historial de ventas
	 */
	public void switchToSalesHistory() {
		this.jPanelContent.setVisible(false);
		this.jPanelInventory.setVisible(true);
		gbc.gridx = 0;
		gbc.weightx = 0;
		gbc.gridwidth = 2;
		gbc.weighty = 1;
		gbc.gridy = 1;
		gbc.insets.top = 52 * JFrameMain.HEIGTH_SIZE / 1080;
		gbc.insets.left = 52;
		gbc.insets.right = 52;
		this.add(jPanelInventory, gbc);
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.insets.top = 30;
		gbc.insets.left = 0;
		gbc.insets.right = 0;
		gbc.insets.bottom = 0;
		gbc.gridy = 2;
		this.add(buttonBillPanel, gbc);
		gbc.gridx = 1;
		this.add(buttonSalesPanel, gbc);
	}

	/**
	 * Metodo que cambia la informacion del usuario
	 * @param name nombre 
	 * @param id identificacion
	 */
	public void setInfoUser(String name, int id) {
		this.jLabelInfoUser.setText(name);
		if (!isAdmin) {
			this.jPanelContent.setInfoUser(name, id);
		}
	}

	/**
	 * Metodo que cambia a la vista de la factura
	 */
	public void switchToBill() {
		this.jPanelInventory.setVisible(false);
		this.jPanelContent.setVisible(true);
		gbc.gridx = 0;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.gridwidth = 2;
		gbc.gridy = 1;
		gbc.insets.top = 52 * JFrameMain.HEIGTH_SIZE / 1080;
		gbc.insets.left = 52;
		gbc.insets.right = 52;
		this.add(jPanelContent, gbc);
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.insets.top = 30;
		gbc.insets.left = 0;
		gbc.insets.right = 0;
		gbc.insets.bottom = 0;
		gbc.gridy = 2;
		this.add(buttonBillPanel, gbc);
		gbc.gridx = 1;
		this.add(buttonSalesPanel, gbc);
	}

	/**
	 * Metodo que reinicia la busqueda del historial de ventas
	 */
	public void restoreSearch() {
		jPanelInventory.restoreSearch();
	}

	/**
	 * Metodo que desactiva o activa los filtros
	 * @param active true o false
	 */
	public void desactiveFilters(boolean active) {
		jPanelInventory.showFilters(active);
	}

	/**
	 * Metodo que activa o desactiva los filtros
	 */
	public void activeOrDesactiveFilters() {
		jPanelInventory.showFilters();
	}

	/**
	 * Metodo que aplica los filtros
	 */
	public void applyFilters() {
		jPanelInventory.applyFilters();
	}

	/**
	 * Metodo que pone en cero la cantidad de los productos
	 */
	public void setCeroQuanty() {
		jPanelInventory.setCeroQuanty();
	}

	/**
	 * Metodo que obtiene la informacion de un producto
	 * @param informacion del producto
	 */
	public Object[] getProductInfo(int code) {
		return jPanelInventory.getProductInfo(code);
	}

	/**
	 * Metodo que agrega un producto a la factura
	 * @param productInfo informacion del producto
	 */
	public void addProductToBill(Object[] productInfo) {
		jPanelContent.addProductToBill(productInfo);
	}

	/**
	 * Metodo que obtiene los productos de la factura
	 * @return productos de la factura
	 */
	public ArrayList<Object[]> getProductsInBill() {
		return jPanelContent.getProductsInBill();
	}

	/**
	 * Metodo que obtiene la cantidad maxima del producto por codigo
	 * @param code codigo del producto
	 * @return cantidad maxima del producto
	 */
	public int getMaxQuantyOf(int code) {
		return jPanelInventory.getMaxQuantyOf(code);
	}

	/**
	 * Metodo que actualiza la factura
	 * @param productsInBill productos de la factura
	 */
	public void actualizeBill(ArrayList<Object[]> productsInBill) {
		jPanelContent.actualizeBill(productsInBill);
	}

	/**
	 * Metodo que obtiene la informacion de la factura
	 * @return informacion de la factura
	 */
	public Object[] getinfoTotalBill() {
		return jPanelContent.getinfoTotalBill();
	}

	/**
	 * Metodo que subraya el campo de texto del nombre del cliente
	 */
	public void underLineClientName() {
		this.jPanelContent.underLineClientName();
	}

	/**
	 * Metodo que quita el subrayado del campo de texto del nombre del cliente
	 */
	public void resetUnderLineClientName() {
		jPanelContent.resetUnderLineClientName();
	}

	/**
	 * Metodo que subraya el campo de texto del id del cliente
	 */
	public void underLineClientID() {
		this.jPanelContent.underLineClientID();
	}

	/**
	 * Metodo que quita el subrayado del campo de texto del id del cliente
	 */
	public void resetUnderLineClientID() {
		this.jPanelContent.resetUnderLineClientID();
	}

	/**
	 * Metodo que obtiene la informacion de pago de la factura
	 * @return informacion de pago
	 */
	public Object[] getInfoPayBill() {
		return jPanelContent.getInfoPayBill();
	}

	/**
	 * Metodo que crea una nueva factura
	 * @param numberBill numero de factura
	 */
	public void newBill(int numberBill) {
		this.jPanelContent.newBill(numberBill);
	}

	/**
	 * Metodo que remueve los productos de la factura
	 */
	public void removeProductsBill() {
		this.jPanelContent.removeProductsBill();
	}

	/**
	 * Metodo que activa o desactiva los calendarios
	 */
	public void desactiveCalendars() {
		jPanelContentAdmin.desactiveCalendars();
	}

	/**
	 * Metodo que cambia a la vista de agregar cajero
	 */
	public void switchAddCashier() {
		jPanelContentAdmin.switchAddCashier();
	}

	/**
	 * Metodo que cambia a la vista principal del admin
	 */
	public void switchInitMenu() {
		jPanelContentAdmin.switchInitMenu();
	}

	/**
	 * Metodo que cambia a la vista de agregar producto
	 */
	public void switchAddProduct() {
		jPanelContentAdmin.switchAddProduct();
	}

	/**
	 * Metodo que actualiza los totales de la factura 
	 * @param totalValues valores totales de la factura
	 */
	public void actualizeTotalValues(Object[] totalValues) {
		jPanelContent.actualizeTotalValues(totalValues);
	}

	/**
	 * Metodo que obtiene el ordinal del tipo de cliente
	 * @return
	 */
	public int getClietTypeOrdinal() {
		return jPanelContent.getClietTypeOrdinal();
	}

	/**
	 * Metodo que obtiene el id del cliente 
	 * @return id del cliente
	 */
	public String getIdClient() {
		return jPanelContent.getIdClient();
	}

	/**
	 * Metodo que cambia el nombre del cliente
	 * @param name nombre del cliente
	 */
	public void setNameClient(String name) {
		jPanelContent.setNameClient(name);
	}

	/**
	 * Metodo que actualiza el historial de ventas
	 * @param salesHistory historial de ventas
	 */
	public void actualizeSalesHistory(ArrayList<Object[]> salesHistory) {
		historyTable.actualizeSalesHistory(salesHistory);
	}

	/**
	 * Metodo que actualiza el inventario
	 * @param inventoryActualized inventario actualizado
	 */
	public void actualizeInventory(ArrayList<Object[]> inventoryActualized) {
		jPanelInventory.actualizeInventory(inventoryActualized);
	}

	/**
	 * Metodo que cambia el numero de factura
	 * @param numberBill numero de factura
	 */
	public void changeNumBill(int numberBill) {
		jPanelContent.changeNumBill(numberBill);
	}

	/**
	 * Metodo que obtiene la informacion del cajero a agregar
	 * @return informacion del cajero
	 */
	public String[] getNewCashierInfo() {
		return jPanelContentAdmin.getNewCashierInfo();
	}
	
	/**
	 * Metodo que resalta los campos de texto de agregar cajero
	 * @param index posicion
	 * @param color color 
	 */
	public void underlineAddCashierTo(int index, int color) {
		jPanelContentAdmin.underlineAddCashierTo(index, color);
	}

	/**
	 * Metodo que cambia la vista al menu principal del admin
	 */
	public void switchPrincipalMenuAdmin() {
		jPanelContentAdmin.switchPrincipalMenuAdmin();
	}

	
	/**
	 * Metodo que pone en blanco las cajas de texto del formulario addCashier
	 */
	public void putBlankAddCashierText() {
		jPanelContentAdmin.putBlankAddCashierText();
	}

	/**
	 * Metodo que obtiene la informacion
	 * @return informacion del nuevo producto
	 */
	public String[] getNewProductInfo() {
		return jPanelContentAdmin.getNewProductInfo();
	}

	/**
	 * Metodo que resalta el campo de texto que se pida
	 * @param index posicion del campo de texto
	 * @param color color de resaltado
	 */
	public void underLineAddProductTo(int index, int color) {
		jPanelContentAdmin.underLineAddProductTo(index, color);
	}

	/**
	 * Metodo que pone en blanco las cajas de texto de agregar producto
	 */
	public void putBlankAddProductText() {
		jPanelContentAdmin.putBlankAddProductText();
	}

	/**
	 * Metodo que genera el reporte de ventas
	 * @param dateI fecha inicial
	 * @param dateF fecha final
	 * @param infoReport informacion del reporte
	 */
	public void generateReport(String dateI, String dateF, Object[] infoReport) {
		jPanelContentAdmin.switchSalesReport(dateI, dateF, infoReport);
	}

	public boolean isAdmin() {
		return isAdmin;
	}

}
