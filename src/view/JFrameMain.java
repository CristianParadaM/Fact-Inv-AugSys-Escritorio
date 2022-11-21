package view;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import view.cashier.JPanelPrintBill;
import view.cashier.JPanelUser;
import view.login.JPanelLogin;
import view.utils.Constants;

/**
 * @author CRISTIAN DAVID PARADA MARTINEZ
 */
public class JFrameMain extends JFrame implements ActionListener {

	private static final int WIDTH = 1300 * Toolkit.getDefaultToolkit().getScreenSize().width / 1920;
	private static final int HEIGTH = 800 * Toolkit.getDefaultToolkit().getScreenSize().height / 1080;
	public static int WIDTH_SIZE = WIDTH;
	public static int HEIGTH_SIZE = HEIGTH;
	private static final long serialVersionUID = 1L;
	private JPanelLogin jPanelLogin;
	private JPanelUser jPanelUser;
	private static JFrameMain frameMain = null;
	private boolean isAdmin;

	/**
	 * Metodo donde se obtiene la instancia de la clase JFrameMain
	 * 
	 * @return instancia de JFrameMain
	 */
	public static JFrameMain getInstance() {
		if (frameMain == null) {
			frameMain = new JFrameMain();
		}
		return frameMain;
	}

	/**
	 * Constructor de JFrameMain
	 */
	public JFrameMain() {
		super();
		this.isAdmin = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case Constants.COMMAND_MINIMIZE -> minimize();
		case Constants.ADD_PRODUCT_BILL -> jPanelUser.switchToSalesHistory();
		case Constants.COMMAND_BUTTON_BACK_HISTORY -> switchToBill();
		case Constants.COMMAND_BUTTON_FILTER -> showFilters();
		case Constants.COMMAND_RADIOBUTTON_FILTER -> applyFilters();
		case Constants.COMMAND_BUTTON_BACK_ADDCASHIER -> jPanelUser.switchInitMenu();
		case Constants.COMMAND_BUTTON_BACK_ADD_PRODUCT -> jPanelUser.switchInitMenu();
		}
	}

	/**
	 * Metodo para aplicar los filtros del inventario
	 */
	private void applyFilters() {
		this.jPanelUser.applyFilters();
	}

	/**
	 * Metodo para minimizar la ventana
	 */
	private void minimize() {
		if (isAdmin) {
			jPanelUser.desactiveCalendars();
		}else {
			if (jPanelUser!=null) {
				jPanelUser.desactiveFilters(false);
			}
		}
		this.setExtendedState(ICONIFIED);
	}

	/**
	 * Metodo para activar o desactivar el JPopUpMenu de los filtros
	 */
	private void showFilters() {
		jPanelUser.activeOrDesactiveFilters();
	}

	/**
	 * Metodo para desactivar el JPopUpMenu de los filtros
	 */
	public void desactiveFilters() {
		jPanelUser.desactiveFilters(false);
	}

	/**
	 * Metodo que cambia la vista a la factura
	 */
	private void switchToBill() {
		jPanelUser.desactiveFilters(false);
		jPanelUser.setCeroQuanty();
		jPanelUser.switchToBill();
		jPanelUser.restoreSearch();
	}

	/**
	 * Metodo que inicializa las propiedades y componentes del frame
	 */
	public void init() {
		setProperties();
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					if (!isAdmin) {
						desactiveFilters();
					}else {
						jPanelUser.desactiveCalendars();
					}
				} catch (NullPointerException e1) {
				}
			}

			@Override
			public void windowClosed(WindowEvent e) {
				try {
					if (!isAdmin) {
						desactiveFilters();
					}else {
						jPanelUser.desactiveCalendars();
					}
				} catch (NullPointerException e1) {
				}
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				try {
					if (!isAdmin) {
						desactiveFilters();
					}
				} catch (NullPointerException e1) {
				}
			}
		});
		this.jPanelLogin = new JPanelLogin();
		this.setContentPane(jPanelLogin);
		this.setVisible(true);
		
		ArrayList<Object[]> products = new ArrayList<Object[]>();
		
		products.add(new Object[] {"Tarjeta Grafica GEFORCE RTX 1920", 200, 2000000f, "Componente", 1001, 6, 0, new ImageIcon("res/p1.png"), "Excelente tarjeta grafica para jugar videojuegos"});
		products.add(new Object[] {"Xiaomi Poco x3 PRO", 20, 1200000f, "Celulares", 1002, 1, 1, new ImageIcon("res/p2.png"), "Excelente Celular relacion calidad precio ideal para tu vida diaria, util para los trabajos cotidianos."});
		products.add(new Object[] {"Mouse Gaming Pro Max", 50, 1400000f, "Accesorio", 1003, 8, 0, new ImageIcon("res/p3.png"), "Excelente Celular relacion calidad precio ideal para tu vida diaria, util para los trabajos cotidianos."});
		products.add(new Object[] {"PC Gaming Ultra RTX", 10, 5400000f, "PC", 1004, 2, 1, new ImageIcon("res/p4.png"), "Excelente Celular relacion calidad precio ideal para tu vida diaria, util para los trabajos cotidianos."});
		products.add(new Object[] {"HP Omen Intel Core i9", 10, 3400000f, "Laptop", 1005, 2, 1, new ImageIcon("res/p5.png"), "Excelente Celular relacion calidad precio ideal para tu vida diaria, util para los trabajos cotidianos."});
		products.add(new Object[] {"Tarjeta Grafica GEFORCE RTX 1920", 200, 2000000f, "Componente", 1001, 6, 0, new ImageIcon("res/p1.png"), "Excelente tarjeta grafica para jugar videojuegos"});
		products.add(new Object[] {"Xiaomi Poco x3 PRO", 20, 1200000f, "Celulares", 1002, 1, 1, new ImageIcon("res/p2.png"), "Excelente Celular relacion calidad precio ideal para tu vida diaria, util para los trabajos cotidianos."});
		products.add(new Object[] {"Mouse Gaming Pro Max", 50, 1400000f, "Accesorio", 1003, 8, 0, new ImageIcon("res/p3.png"), "Excelente Celular relacion calidad precio ideal para tu vida diaria, util para los trabajos cotidianos."});
		products.add(new Object[] {"PC Gaming Ultra RTX", 10, 5400000f, "PC", 1004, 2, 1, new ImageIcon("res/p4.png"), "Excelente Celular relacion calidad precio ideal para tu vida diaria, util para los trabajos cotidianos."});
		products.add(new Object[] {"HP Omen Intel Core i9", 10, 3400000f, "Laptop", 1005, 2, 1, new ImageIcon("res/p5.png"), "Excelente Celular relacion calidad precio ideal para tu vida diaria, util para los trabajos cotidianos."});
		products.add(new Object[] {"Tarjeta Grafica GEFORCE RTX 1920", 200, 2000000f, "Componente", 1001, 6, 0, new ImageIcon("res/p1.png"), "Excelente tarjeta grafica para jugar videojuegos"});
		products.add(new Object[] {"Xiaomi Poco x3 PRO", 20, 1200000f, "Celulares", 1002, 1, 1, new ImageIcon("res/p2.png"), "Excelente Celular relacion calidad precio ideal para tu vida diaria, util para los trabajos cotidianos."});
		products.add(new Object[] {"Mouse Gaming Pro Max", 50, 1400000f, "Accesorio", 1003, 8, 0, new ImageIcon("res/p3.png"), "Excelente Celular relacion calidad precio ideal para tu vida diaria, util para los trabajos cotidianos."});
		products.add(new Object[] {"PC Gaming Ultra RTX", 10, 5400000f, "PC", 1004, 2, 1, new ImageIcon("res/p4.png"), "Excelente Celular relacion calidad precio ideal para tu vida diaria, util para los trabajos cotidianos."});
		products.add(new Object[] {"HP Omen Intel Core i9", 10, 3400000f, "Laptop", 1005, 2, 1, new ImageIcon("res/p5.png"), "Excelente Celular relacion calidad precio ideal para tu vida diaria, util para los trabajos cotidianos."});
		products.add(new Object[] {"Tarjeta Grafica GEFORCE RTX 1920", 200, 2000000f, "Componente", 1001, 6, 0, new ImageIcon("res/p1.png"), "Excelente tarjeta grafica para jugar videojuegos"});
		products.add(new Object[] {"Xiaomi Poco x3 PRO", 20, 1200000f, "Celulares", 1002, 1, 1, new ImageIcon("res/p2.png"), "Excelente Celular relacion calidad precio ideal para tu vida diaria, util para los trabajos cotidianos."});
		products.add(new Object[] {"Mouse Gaming Pro Max", 50, 1400000f, "Accesorio", 1003, 8, 0, new ImageIcon("res/p3.png"), "Excelente Celular relacion calidad precio ideal para tu vida diaria, util para los trabajos cotidianos."});
		products.add(new Object[] {"PC Gaming Ultra RTX", 10, 5400000f, "PC", 1004, 2, 1, new ImageIcon("res/p4.png"), "Excelente Celular relacion calidad precio ideal para tu vida diaria, util para los trabajos cotidianos."});
		products.add(new Object[] {"HP Omen Intel Core i9", 10, 3400000f, "Laptop", 1005, 2, 1, new ImageIcon("res/p5.png"), "Excelente Celular relacion calidad precio ideal para tu vida diaria, util para los trabajos cotidianos."});
		products.add(new Object[] {"Tarjeta Grafica GEFORCE RTX 1920", 200, 2000000f, "Componente", 1001, 6, 0, new ImageIcon("res/p1.png"), "Excelente tarjeta grafica para jugar videojuegos"});
		products.add(new Object[] {"Xiaomi Poco x3 PRO", 20, 1200000f, "Celulares", 1002, 1, 1, new ImageIcon("res/p2.png"), "Excelente Celular relacion calidad precio ideal para tu vida diaria, util para los trabajos cotidianos."});
		products.add(new Object[] {"Mouse Gaming Pro Max", 50, 1400000f, "Accesorio", 1003, 8, 0, new ImageIcon("res/p3.png"), "Excelente Celular relacion calidad precio ideal para tu vida diaria, util para los trabajos cotidianos."});
		products.add(new Object[] {"PC Gaming Ultra RTX", 10, 5400000f, "PC", 1004, 2, 1, new ImageIcon("res/p4.png"), "Excelente Celular relacion calidad precio ideal para tu vida diaria, util para los trabajos cotidianos."});
		products.add(new Object[] {"HP Omen Intel Core i9", 10, 3400000f, "Laptop", 1005, 2, 1, new ImageIcon("res/p5.png"), "Excelente Celular relacion calidad precio ideal para tu vida diaria, util para los trabajos cotidianos."});
		
		ArrayList<Object[]> sales = new ArrayList<Object[]>();
		
		
		switchCashier("Cristian David Parada Martinez", 1002365941, 20221001, products , new String[] {"Mayorista", "Minorista"}, sales);
	}

	/**
	 * Metodo que inicializa las propiedades iniciales del JFrameMain
	 */
	private void setProperties() {
		this.setTitle("Sistema De Facturacion e inventario Augusto Systems®");
		this.setIconImage(new ImageIcon("res/iconLogo.png").getImage());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setUndecorated(true);
		this.setSize(WIDTH_SIZE, HEIGTH_SIZE);
		this.setLocationRelativeTo(null);
	}

	/**
	 * Metodo para obtener el nombre de usuario digitado en el panel de login
	 * 
	 * @return nombre de usuario digitado
	 */
	public String getUsernameLogin() {
		return jPanelLogin.getUsernameLogin();
	}

	/**
	 * Metodo para obtener la contraseña digitada en el panel de login
	 * 
	 * @return contraseña digitada
	 */
	public String getPasswordLogin() {
		return jPanelLogin.getPasswordLogin();
	}

	/**
	 * Metodo que cambia la vista principal dependiendo del tipo de inicio de sesion
	 * 
	 * @param option (1 cajero, 2 admin)
	 * @param name   nombre del usuario
	 * @param id     identificacion del usuario
	 */
	public void switchViewTo(int option, String name, int id, int numberBill, int quantyCashiers, int quantyProducts, 
			ArrayList<Object[]> listProducts, String[] typeUsers, ArrayList<Object[]> salesHistory) {
		switch (option) {
		case 1 -> switchCashier(name, id, numberBill, listProducts, typeUsers, salesHistory);
		default -> switchAdmin(name, id, quantyCashiers, quantyProducts);
		}
	}

	/**
	 * Metodo que cambia la vista a modo cajero
	 * 
	 * @param name nombre del cajero
	 * @param id   identificacion del cajero
	 */
	private void switchCashier(String name, int id, int numberBill, ArrayList<Object[]> listProducts, String[] typeUsers, ArrayList<Object[]> salesHistory) {
		this.isAdmin = false;
		JFrameMain.WIDTH_SIZE = Toolkit.getDefaultToolkit().getScreenSize().width;
		JFrameMain.HEIGTH_SIZE = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.jPanelUser = new JPanelUser(numberBill, listProducts, typeUsers, salesHistory);
		this.jPanelLogin.setVisible(false);
		this.jPanelUser.setInfoUser(name, id);
		this.setContentPane(jPanelUser);
		this.setSize(WIDTH_SIZE, HEIGTH_SIZE);
		this.setLocationRelativeTo(null);
	}

	/**
	 * Metodo que cambia la vista a modo administrador
	 * 
	 * @param name nombre del administrador
	 * @param id   identificacion del administrador
	 */
	private void switchAdmin(String name, int id, int quantyCashiers, int quantyProducts) {
		this.isAdmin = true;
		JFrameMain.WIDTH_SIZE = Toolkit.getDefaultToolkit().getScreenSize().width;
		JFrameMain.HEIGTH_SIZE = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.jPanelUser = new JPanelUser(quantyCashiers, quantyProducts, null);
		this.jPanelLogin.setVisible(false);
		this.jPanelUser.setInfoUser(name, id);
		this.setContentPane(jPanelUser);
		this.setSize(WIDTH_SIZE, HEIGTH_SIZE);
		this.setLocationRelativeTo(null);
	}

	/**
	 * Metodo que cambia la vista al login
	 */
	public void switchLogin() {
		JFrameMain.WIDTH_SIZE = WIDTH;
		JFrameMain.HEIGTH_SIZE = HEIGTH;
		jPanelUser.setVisible(false);
		jPanelLogin.setVisible(true);
		this.setContentPane(jPanelLogin);
		this.setSize(WIDTH_SIZE, HEIGTH_SIZE);
		this.setLocationRelativeTo(null);
	}

	/**
	 * Metodo que pone en blanco las cajas de texto del login
	 */
	public void putBlankText() {
		jPanelLogin.putBlankText();
	}

	/**
	 * Metodo que obtiene el username de la caja de texto de olvido su contraseña
	 * @return nombre de usuario
	 */
	public String getIdJtextfield() {
		return jPanelLogin.getIdJtextField();
	}

	/**
	 * Metodo que añade componentes como la caja de texto del codigo enviado al correo a la vista
	 */
	public void addInfoCode() {
		jPanelLogin.addInfoCode();
	}

	/**
	 * Metodo que obtiene la informacion de un producto cargado en la vista
	 * @param code codigo
	 * @return informacion del producto
	 */
	public Object[] getProductInfo(int code) {
		return jPanelUser.getProductInfo(code);
	}

	/**
	 * Metodo que añade un producto a la vista de la factura
	 * @param productInfo
	 */
	public void addProductToBill(Object[] productInfo) {
		jPanelUser.addProductToBill(productInfo);
	}

	/**
	 * Metodo que obtiene los productos seleccionados en la factura
	 * @return informacion de los productos de la factura
	 */ 
	public ArrayList<Object[]> getProductsInBill() {
		return jPanelUser.getProductsInBill();
	}

	/**
	 * Metodo que obtiene la cantidad de un producto
	 * @param code codigo del producto
	 * @return cantidad total en stock
	 */
	public int getMaxQuantyOf(int code) {
		return jPanelUser.getMaxQuantyOf(code);
	}

	/**
	 * Metodo que actualiza la factura
	 * @param productsInBill productos a setear a la factura
	 */
	public void actualizeBill(ArrayList<Object[]> productsInBill) {
		jPanelUser.actualizeBill(productsInBill);
	}

	/**
	 * Metodo que obtiene el subtotal,iva y total de la factura 
	 * @return informacion total de la factura
	 */
	public Object[] getinfoTotalBill() {
		return jPanelUser.getinfoTotalBill();
	}

	/**
	 * Metodo que resalta el cuadro de texto del nombre del cliente
	 */
	public void underLineClientName() {
		this.jPanelUser.underLineClientName();
	}

	/**
	 * Metodo que quita el resalto del cuadro de texto del nombre del cliente
	 */
	public void resetUnderLineClientName() {
		this.jPanelUser.resetUnderLineClientName();
	}

	/**
	 * Metodo que resalta el cuadro de texto del id del cliente
	 */
	public void underLineClientID() {
		this.jPanelUser.underLineClientID();
	}

	/**
	 * Metodo que quita el resalto del cuadro de texto del id del cliente
	 */
	public void resetUnderLineClientID() {
		this.jPanelUser.resetUnderLineClientID();
	}

	/**
	 * Metodo que imprime la factura
	 * @param infoBill informacion de la factura
	 * @param nameFile nombre del archivo para guardar
	 */
	public void printBill(Object[] infoBill, String nameFile) {
		new JPanelPrintBill(infoBill, nameFile);
	}

	/**
	 * Metodo que obtiene la informacion de pago de la factura
	 * @return informacion de pago
	 */
	public Object[] getInfoPayBill() {
		return jPanelUser.getInfoPayBill();
	}

	/**
	 * metodo que crea una nueva factura en la vista
	 * @param numberBill numero de factura
	 */
	public void newBill(int numberBill) {
		this.jPanelUser.newBill(numberBill);
	}

	/**
	 * Metodo que remueve los productos de la factura
	 */
	public void removeProductsBill() {
		this.jPanelUser.removeProductsBill();
	}

	/**
	 * metodo que desactiva los calendarios en la vista del admin
	 */
	public void desactiveCalendars() {
		jPanelUser.desactiveCalendars();
	}
	
	/**
	 * Metodo que cambia la vista a vista de cajero
	 */
	public void switchAddCashier() {
		jPanelUser.switchAddCashier();
	}

	/**
	 * Metodo que cambia la vista al inventario
	 */
	public void switchAddProduct() {
		jPanelUser.switchAddProduct();
	}
	
	/**
	 * Metodo que obtiene el codigo de recuperacion 
	 * @return codigo de recuperacion
	 */
	public String getCodeRecovery() {
		return jPanelLogin.getCodeRecovery();
	}

	/**
	 * Metodo que obtiene la contraseña de confirmacion
	 * @return contraseña de recuperacion
	 */
	public String getPasswordConfirmRecovery() {
		return jPanelLogin.getPasswordConfirmRecovery();
	}

	/**
	 * Metodo que obtiene la contraseña
	 * @return contraseña de recuperacion
	 */
	public String getPasswordRecovery() {
		return jPanelLogin.getPasswordRecovery();
	}

	/**
	 * Metodo que cambia la vista a cambiar contraseña
	 */
	public void switchChangePassword() {
		jPanelLogin.switchChangePassword();
	}

	/**
	 * Metodo que cambia el nombre de la caja de texto de recuperacion
	 * @param username nombre a cambiar
	 */
	public void setUserNameRecovery(String username) {
		jPanelLogin.setUserNameRecovery(username);
	}

	/**
	 * Metodo que actualiza los valores totales de la factura
	 * @param totalValues valores totales
	 */
	public void actualizeTotalValues(Object[] totalValues) {
		jPanelUser.actualizeTotalValues(totalValues);
	}

	/**
	 * Metodo que obtiene el ordinal de tipo de cliente
	 * @return ordinal de tipo de cliente
	 */
	public int getClietTypeOrdinal() {
		return jPanelUser.getClietTypeOrdinal();
	}

	/**
	 * Metodo que obtiene el id del cliente
	 * @return id del cliente
	 */
	public String getIdClient() {
		return jPanelUser.getIdClient();
	}

	/**
	 * Metodo que cambia el nombre del cliente
	 * @param name nombre del cliente
	 */
	public void setNameClient(String name) {
		jPanelUser.setNameClient(name);
	}

	/**
	 * Metodo que actualiza la lista del ventas
	 * @param salesHistory lista de ventas
	 */
	public void actualizeSalesHistory(ArrayList<Object[]> salesHistory) {
		jPanelUser.actualizeSalesHistory(salesHistory);
	}

	/**
	 * Metodo que actualiza el inventario
	 * @param inventoryActualized inventario actualizado
	 */
	public void actualizeInventory(ArrayList<Object[]> inventoryActualized) {
		jPanelUser.actualizeInventory(inventoryActualized);
	}

	/**
	 * Metodo que cambia el numero de factura
	 * @param numberBill numero de factura
	 */
	public void changeNumBill(int numberBill) {
		jPanelUser.changeNumBill(numberBill);
	}

	/**
	 * Metodo que obtiene la informacion del cajero a agregar
	 * @return informacion del cajero
	 */
	public String[] getNewCashierInfo() {
		return jPanelUser.getNewCashierInfo();
	}
	
	/**
	 * Metodo que resalta los campos de texto de agregar cajero
	 * @param index posicion
	 * @param color color 
	 */
	public void underlineAddCashierTo(int index, int color) {
		jPanelUser.underlineAddCashierTo(index, color);
	}

	/**
	 * Metodo que cambia a menu principal del admin
	 */
	public void switchPrincipalMenuAdmin() {
		jPanelUser.switchPrincipalMenuAdmin();
	}
	
	/**
	 * Metodo que pone en blanco las cajas de texto del formulario addCashier
	 */
	public void putBlankAddCashierText() {
		jPanelUser.putBlankAddCashierText();
	}

	/**
	 * Metodo que obtiene la informacion
	 * @return informacion del nuevo producto
	 */
	public String[] getNewProductInfo() {
		return jPanelUser.getNewProductInfo();
	}

	/**
	 * Metodo que resalta el campo de texto que se pida
	 * @param index posicion del campo de texto
	 * @param color color de resaltado
	 */
	public void underLineAddProductTo(int index, int color) {
		jPanelUser.underLineAddProductTo(index, color);
	}

	/**
	 * Metodo que pone en blanco las cajas de texto de agregar producto
	 */
	public void putBlankAddProductText() {
		jPanelUser.putBlankAddProductText();
	}

	/**
	 * Metodo que genera el reporte de ventas
	 * @param dateI fecha inicial
	 * @param dateF fecha final
	 * @param infoReport informacion del reporte
	 */
	public void generateReport(String dateI, String dateF, Object[] infoReport) {
		jPanelUser.generateReport(dateI, dateF, infoReport);
	}

	public boolean isAdmin() {
		return jPanelUser.isAdmin();
	}

}