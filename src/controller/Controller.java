package controller;

import network.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import view.JFrameMain;
import view.admin.IListener;
import view.admin.JPanelOptionCashier;
import view.admin.JPanelOptionSalesHistory;
import view.utils.Constants;
import com.google.gson.Gson;
import model.ClientType;
import model.DocumentType;
import model.EnumPaymentType;
import model.EnumPersonType;
import model.EnumTypeProduct;
import model.EnumTypeTime;
import model.EnumTypeUser;
import model.Inventory;
import model.Invoice;
import model.Invoice_Product;
import model.Person;
import model.Product;
import model.Store;
import model.Supplier;
import model.User;
import model.Warranty;

/**
 * @author CRISTIAN DAVID PARADA MARTINEZ
 */
public class Controller extends ThreadNotify implements ActionListener, IListener {

	private static Controller controller;
	private JFrameMain frameMain;
	private int numberBill;
	private Gson gson;
	private Client client;
	private User useractual;
	private Person personactual;
	private ArrayList<Invoice_Product> invoiceProductActual;
	private Invoice invoice;

	/**
	 * Patron Singleton que retorna una instancia de Controller, donde contiene los
	 * listener implementados en la clase
	 * 
	 * @return instancia de Controller
	 */
	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	/**
	 * Constructor de Controller
	 */
	private Controller() {
		super(50);
		this.gson = new Gson();
		this.numberBill = 20221001;
		this.invoiceProductActual = new ArrayList<Invoice_Product>();
		this.frameMain = JFrameMain.getInstance();
	}

	/**
	 * Metodo para iniciar el cliente
	 * 
	 * @param ip   direccion ip del servidor
	 * @param port puerto de conexion
	 */
	public void startApp(String ip, int port) {
		this.frameMain.init();
//		this.client = new Client(ip, port);
		this.start();
	}

	@Override
	protected void executeTask() {
		try {
			if (client != null) {
				if (client.getIn().available() > 0) {
					if (client.getIn().readByte() == -1) {
						changeNumBill();
					}
				}
			}
		} catch (IOException e) {
		}
	}

	@Override
	public void iEvent(String command) {
		this.pause();
		try {
			switch (command) {
			case Constants.COMMAND_SEARCH_CLIENT -> searchClient();
			case Constants.COMMAND_TIME_OUT -> timeOutCode();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.resume();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.pause();
		try {
			switch (e.getActionCommand()) {
			case Constants.COMMAND_LOGIN -> login(frameMain.getUsernameLogin(), frameMain.getPasswordLogin());
			case Constants.COMMAND_LOG_OUT -> logout();
			case Constants.COMMAND_BUTTON_VERIFY_ID -> verifyUsername();
			case Constants.COMMAND_BUTTON_ADD_PRODUCT_TO_BILL -> addProductToBill(((JButton) e.getSource()).getName());
			case Constants.COMMAND_PRINT_BILL -> proccessPrintBill();
			case Constants.COMMAND_BUTTON_EXIT -> closeConnection();
			case Constants.COMMAND_BUTTON_VERIFY_CODE -> verifyCode();
			case Constants.COMMAND_BUTTON_CHANGE_PASS -> changePass();
			case Constants.COMMAND_COMBOBOX_TYPECLIENT -> updateBill();
			case Constants.COMMAND_ADD_CASHIER -> addCashier();
			case Constants.COMMAND_ADD_NEW_PRODUCT -> addProduct();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.resume();
	}

	/**
	 * Metodo que procesa el reporte de ventas
	 * 
	 * @param source
	 * @throws IOException
	 */
	private void processReportSales(Object source) throws IOException {
		String dateI = ((JPanelOptionSalesHistory) source).getDateI();
		String dateF = ((JPanelOptionSalesHistory) source).getDateF();
		boolean pass = false;
		if (dateI.isBlank()) {
			((JPanelOptionSalesHistory) source).setBorderTo(0, 1);
			pass = false;
		} else {
			((JPanelOptionSalesHistory) source).setBorderTo(0, 0);
			pass = true;
		}
		if (dateF.isBlank()) {
			((JPanelOptionSalesHistory) source).setBorderTo(1, 1);
			pass = false;
		} else {
			((JPanelOptionSalesHistory) source).setBorderTo(1, 0);
			pass = true;
		}
		if (pass) {

			String[] dateIText = dateI.split("/");
			String[] dateFText = dateI.split("/");

			LocalDate[] dates = new LocalDate[] {

					LocalDate.of(Integer.parseInt(dateIText[2]), Integer.parseInt(dateIText[1]),
							Integer.parseInt(dateIText[0])),
					
					LocalDate.of(Integer.parseInt(dateFText[2]), Integer.parseInt(dateFText[1]),
							Integer.parseInt(dateFText[0])) 
					};

			client.getOut().writeByte(15);
			client.getOut().writeUTF(gson.toJson(dates));

			if (client.getIn().readBoolean()) {

				int[] angles = new int[5];
				for (int i = 0; i < 5; i++) {
					angles[i] = client.getIn().readInt();
				}
				
				String[] legends = new String[5];
				for (int i = 0; i < legends.length; i++) {
					legends[i] = client.getIn().readUTF();
				}
				
				int totalSales = client.getIn().readInt();
				
				// producto mas vendido
				String typeProductMas = client.getIn().readUTF();
				double percentSaleMas = client.getIn().readDouble();
				
				// producto mas vendido
				String typeProductMenos = client.getIn().readUTF();
				double percentSaleMenos = client.getIn().readDouble();
				
				//podium cajeros
				int sizeCashiersPodium = client.getIn().readInt();
				Object[][] podiumCashiers = new Object[sizeCashiersPodium][3];
				for (int i = 0; i < sizeCashiersPodium; i++) {
					podiumCashiers[sizeCashiersPodium][0] = client.getIn().readInt();
					podiumCashiers[sizeCashiersPodium][1] = client.getIn().readUTF();
					podiumCashiers[sizeCashiersPodium][2] = client.getIn().readInt();
				}
				
				//podium productos
				int sizeProductsPodium = client.getIn().readInt();
				Object[][] podiumProducts = new Object[sizeProductsPodium][3];
				for (int i = 0; i < sizeProductsPodium; i++) {
					podiumProducts[i][0] = client.getIn().readInt();
					podiumProducts[i][1] = client.getIn().readUTF();
					podiumProducts[i][2] = client.getIn().readInt();
				}
				
				double totalSalesMoney = client.getIn().readDouble();
				double totalGain = client.getIn().readDouble();
				
				Object[] infoReport = new Object[] {
						new Object[] { angles, legends, totalSales},
						new Object[] {typeProductMas, percentSaleMas},
						new Object[] {typeProductMenos, percentSaleMenos},
						podiumCashiers,
						podiumProducts,
						totalSalesMoney,
						totalGain
				};
				frameMain.generateReport(dateI, dateF, infoReport);

			} else {
				JOptionPane.showMessageDialog(frameMain, "Rango de fechas invalidas", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		} else {
			JOptionPane.showMessageDialog(frameMain, "Falta rellenar algun campo", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Metodo que añade un nuevo producto al modelo
	 * 
	 * @throws IOException Excepcion de entrada o salida
	 */
	private void addProduct() throws IOException {
		String[] infoProduct = frameMain.getNewProductInfo();
		boolean pass = false;

		pass = validateUnderLineAddProduct(infoProduct[0], 0);
		pass = validateUnderLineAddProduct(infoProduct[1], 1);
		pass = validateUnderLineAddProduct(infoProduct[2], 2);

		if (infoProduct[3].equals("0")) {
			pass = false;
			frameMain.underLineAddProductTo(3, 0);
		} else {
			frameMain.underLineAddProductTo(3, 1);
			pass = true;
		}

		pass = validateUnderLineAddProduct(infoProduct[5], 5);
		pass = validateUnderLineAddProduct(infoProduct[6], 6);
		pass = validateUnderLineAddProduct(infoProduct[8], 8);
		pass = validateUnderLineAddProduct(infoProduct[9], 9);
		pass = validateUnderLineAddProduct(infoProduct[10], 10);
		pass = validateUnderLineAddProduct(infoProduct[11], 11);
		pass = validateUnderLineAddProduct(infoProduct[12], 12);
		pass = validateUnderLineAddProduct(infoProduct[13], 13);
		pass = validateUnderLineAddProduct(infoProduct[14], 14);

		if (pass) {

			File file = new File(infoProduct[14]);
			byte[] imageBytes = Files.readAllBytes(file.toPath());

			client.getOut().writeByte(14);
			client.getOut().writeInt(Integer.parseInt(infoProduct[0]));

			if (!client.getIn().readBoolean()) {

				client.getOut().writeUTF(infoProduct[13]);
				client.getOut().writeUTF(infoProduct[12]);

				int idCity = client.getIn().readInt();
				Supplier supplier = new Supplier(Integer.parseInt(infoProduct[8]), infoProduct[9], infoProduct[11],
						Long.parseLong(infoProduct[10]), idCity);
				client.getOut().writeUTF(gson.toJson(supplier));
				
				client.getOut().writeInt(Integer.parseInt(infoProduct[6]));
				client.getOut().writeInt(infoProduct[7].equals("Mes(es)")?0:1);
				
				client.getOut().writeInt(imageBytes.length);
				for (int i = 0; i < imageBytes.length; i++) {
					client.getOut().writeByte(imageBytes[i]);
				}
				
				client.getOut().writeInt(Integer.parseInt(infoProduct[0]));
				client.getOut().writeUTF(infoProduct[1]);
				client.getOut().writeUTF(infoProduct[2]);
				client.getOut().writeInt(Integer.parseInt(infoProduct[3]));
				client.getOut().writeFloat(Float.parseFloat(infoProduct[5]));
				int typeProductOrdinal = infoProduct[4].equals("Componente")?0:infoProduct[4].equals("PC")?3:infoProduct[4].equals("Celulares")?1:infoProduct[4].equals("Laptop")?2:4;
				client.getOut().writeInt(typeProductOrdinal);
				
				frameMain.putBlankAddProductText();
				frameMain.switchPrincipalMenuAdmin();

			} else {
				JOptionPane.showMessageDialog(frameMain, "el producto ya existe", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(frameMain, "Falta rellenar algun campo", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Metodo que valida si la informacion esta completa
	 * 
	 * @param text  texto
	 * @param index posicion
	 * @return true o false
	 */
	private boolean validateUnderLineAddProduct(String text, int index) {
		if (text.isBlank()) {
			frameMain.underLineAddProductTo(index, 0);
			return false;
		} else {
			frameMain.underLineAddProductTo(index, 1);
			return true;
		}
	}

	/**
	 * Metodo que agrega un nuevo cajero
	 * 
	 * @throws IOException Excepcion de entrada o salida
	 */
	private void addCashier() throws IOException {
		String[] infoCashier = frameMain.getNewCashierInfo();
		boolean pass = false;

		pass = validateUnderLineAddCashier(infoCashier[0], 0);
		pass = validateUnderLineAddCashier(infoCashier[1], 1);
		pass = validateUnderLineAddCashier(infoCashier[2], 2);
		pass = validateUnderLineAddCashier(infoCashier[3], 3);
		pass = validateUnderLineAddCashier(infoCashier[4], 4);
		pass = validateUnderLineAddCashier(infoCashier[5], 5);
		pass = validateUnderLineAddCashier(infoCashier[6], 6);

		if (pass) {
			if (infoCashier[4].equals(infoCashier[5])) {

				client.getOut().writeByte(11);
				client.getOut().writeUTF(gson.toJson(infoCashier));

				if (client.getIn().readBoolean()) {
					frameMain.putBlankAddCashierText();
					frameMain.switchPrincipalMenuAdmin();
				} else {
					JOptionPane.showMessageDialog(frameMain, "el usuario ya existe", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(frameMain, "las contraseñas no coinciden", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(frameMain, "Falta rellenar algun campo", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Metodo que valida si la informacion esta completa
	 * 
	 * @param text  texto
	 * @param index posicion
	 * @return true o false
	 */
	private boolean validateUnderLineAddCashier(String text, int index) {
		if (text.isBlank()) {
			frameMain.underlineAddCashierTo(index, 0);
			return false;
		} else {
			frameMain.underlineAddCashierTo(index, 1);
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	private void changeNumBill() throws IOException {

//		boolean isAdmin = frameMain.isAdmin();
//		numberBill = client.getIn().readInt();
//		Object[][] inventoryActualized = gson.fromJson(client.getIn().readUTF(), Object[][].class);
//		ArrayList<Object[]> salesHistory = gson.fromJson(client.getIn().readUTF(), ArrayList.class);
//
//		if (!isAdmin) {
//			frameMain.changeNumBill(numberBill);
//			frameMain.actualizeSalesHistory(salesHistory);
//			frameMain.actualizeInventory(inventoryActualized);
//		}
	}

	/**
	 * Metodo que invalida el codigo generado porque se acabaron los 5 min de tiempo
	 * 
	 * @throws IOException
	 */
	private void timeOutCode() throws IOException {
		client.getOut().writeInt(-1234);
		client.getIn().readBoolean();
	}

	/**
	 * metodo que busca un cliente por su numero de documento
	 * 
	 * @throws IOException excepcion de entrada o salida
	 */
	private void searchClient() throws IOException {
		client.getOut().writeByte(7);
		int idClient = Integer.parseInt(frameMain.getIdClient());
		client.getOut().writeInt(idClient);
		frameMain.setNameClient(client.getIn().readUTF());
	}

	/**
	 * Metodo que actualiza la factura
	 * 
	 * @throws IOException Excepcion de entrada o salida
	 */
	@SuppressWarnings("unchecked")
	private void updateBill() throws IOException {
//		ArrayList<Object[]> productsInBill = frameMain.getProductsInBill();
//		if (!productsInBill.isEmpty()) {
//
//			client.getOut().writeByte(6);
//			client.getOut().writeUTF(gson.toJson(invoiceProductActual));
//			client.getOut().writeInt(frameMain.getClietTypeOrdinal());
//
//			float subtotal = client.getIn().readFloat();
//			float ivatotal = client.getIn().readFloat();
//			float total = client.getIn().readFloat();
//
//			Object[] totalValues = new Object[] { subtotal, ivatotal, total };
//			frameMain.actualizeTotalValues(totalValues);
//
//			int sizeInvoiceProducts = client.getIn().readInt();
//			ArrayList<Invoice_Product> invoiceProductsActualized = new ArrayList<Invoice_Product>();
//			for (int i = 0; i < totalValues.length; i++) {
////				invoiceProductsActualized.add(new Invoice_Product(client.getIn().readInt(), client.getIn().readDouble(),
////						client.getIn().readInt(), client.getIn().readDouble()));
//			}
//
//			invoiceProductActual = invoiceProductsActualized;
//
//			ArrayList<Object[]> producstActualized = new ArrayList<Object[]>();
//
//			for (int i = 0; i < productsInBill.size(); i++) {
//				producstActualized.add(new Object[] { productsInBill.get(i)[0], productsInBill.get(i)[1],
//						productsInBill.get(i)[2], invoiceProductActual.get(i).getPurchasePrice(),
//						invoiceProductActual.get(i).getPurchasePrice() * invoiceProductActual.get(i).getQuantity() });
//			}
//
//			frameMain.actualizeBill(producstActualized);
//		}
	}

	/**
	 * Metodo que cierra la conexion con el servidor y finaliza el programa
	 * 
	 * @throws IOException Excepcion de entrada o salida
	 */
	private void closeConnection() throws IOException {
//		client.getOut().writeByte(2);
		System.exit(1);
	}

	/**
	 * Metodo que procesa la impresion de la factura
	 * 
	 * @throws IOException Excepcion de entrada o salida
	 */
	@SuppressWarnings("unchecked")
	private void proccessPrintBill() throws IOException {
//		client.getOut().writeByte(4);
		Object[] infoTableBill = frameMain.getinfoTotalBill(); // trae informacion como cliente, n factura, fecha etc
//		Store store = gson.fromJson(client.getIn().readUTF(), Store.class);
		boolean pass = validateInfoPrintBill(infoTableBill);
		if (pass) {
			ArrayList<Object[]> productsInBill = frameMain.getProductsInBill();
			Object[] infoTotalPay = frameMain.getInfoPayBill();
			if (productsInBill.isEmpty()) {
				JOptionPane.showMessageDialog(frameMain, "No se ha añadido ningun producto a la factura", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				int option = JOptionPane.showOptionDialog(frameMain,
						"¿Está seguro que desea generar la factura? (Revise detalladamente los datos ingresados)",
						"Advertencia", 1, JOptionPane.YES_NO_CANCEL_OPTION, null, new String[] { "SI", "NO" }, "NO");
				if (option == 0) {
//					Object[] infoStore = new Object[] { store.getName(), store.getEmail(), store.getAddress(),
//							store.getPhoneNumber() };
					Object[] infoStore = new Object[] { "Augusto Systems", "AuSy.soporte@gmail.com", "calle 12 #11-23",
							3212321233l, 19 };
					Object[] infoBill = new Object[] { infoTableBill, productsInBill, infoStore, infoTotalPay }; // esto
					frameMain.printBill(infoBill, "F_" + (numberBill++));
					frameMain.newBill(numberBill);
					frameMain.removeProductsBill();

//					invoice = new Invoice((int) infoTableBill[1],
//
//							new GregorianCalendar(Integer.parseInt(((String) infoTableBill[0]).split("/")[2]),
//									Integer.parseInt(((String) infoTableBill[0]).split("/")[1]),
//									Integer.parseInt(((String) infoTableBill[0]).split("/")[0])),
//							(float) infoTotalPay[0],
//							EnumPaymentType.values()[((String) infoTableBill[8]).equals("Efectivo") ? 0
//									: ((String) infoTableBill[8]).equals("Tarjeta Debido") ? 1 : 2],
//							Integer.parseInt((String) infoTableBill[4]), useractual.getId(), 1,
//							((String) infoTableBill[3]).equals("Minorista") ? 1 : 2, invoiceProductActual);
//
//					client.getOut().writeUTF(gson.toJson(invoice));
//
//					if (!client.getIn().readBoolean()) {
//						String[] names = ((String) infoTableBill[2]).split(" ");
//						Person personClient = new Person(Integer.parseInt(((String) infoTableBill[4])),
//								names[0] + " " + names[1], names[2] + " " + names[3], EnumPersonType.CLIENT,
//								((String) infoTableBill[3]).equals("CC") ? 1 : 2);
//						client.getOut().writeUTF(gson.toJson(personClient));
//					}
					JOptionPane.showConfirmDialog(frameMain, "Factura generada Exitosamente", "Notificación",
							JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
							new ImageIcon(new ImageIcon("res/confirmation.png").getImage().getScaledInstance(50, 50,
									Image.SCALE_SMOOTH)));
//
//					client.getOut().writeInt(8);
//					ArrayList<Invoice> salesHistory = gson.fromJson(client.getIn().readUTF(), ArrayList.class);
//
//					ArrayList<Object[]> salesH = new ArrayList<Object[]>();
//					for (Invoice invoices : salesHistory) {
//
//						client.getOut().writeByte(16);
//						client.getOut().writeInt(invoices.getId());
//						Person personClient = gson.fromJson(client.getIn().readUTF(), Person.class);
//
//						client.getOut().writeByte(17);
//						client.getOut().writeInt(personClient.getIdDocumentType());
//						DocumentType documentType = gson.fromJson(client.getIn().readUTF(), DocumentType.class);
//
//						client.getOut().writeByte(18);
//						client.getOut().writeInt(invoices.getIdCashier());
//						User cashier = gson.fromJson(client.getIn().readUTF(), User.class);
//
//						client.getOut().writeByte(16);
//						client.getOut().writeInt(cashier.getIdPerson());
//						Person cashierPerson = gson.fromJson(client.getIn().readUTF(), Person.class);
//
//						ArrayList<Object[]> productsInvoice = new ArrayList<Object[]>();
//
//						for (Invoice_Product productInvoice : invoices.getPurchasedProducts()) {
//
//							client.getOut().writeByte(19);
//							client.getOut().writeInt(productInvoice.getIdProduct());
//							Product product = gson.fromJson(client.getIn().readUTF(), Product.class);
//							Warranty warranty = gson.fromJson(client.getIn().readUTF(), Warranty.class);
//
//							productsInvoice.add(new Object[] { productInvoice.getIdProduct(),
//									productInvoice.getQuantity(), product.getName(),
//									warranty.warrantyIsActive(invoices.getDate()) ? "Activa" : "Inactiva",
//									product.getPrice(), product.getPrice() * productInvoice.getQuantity() });
//						}
//
//						salesH.add(new Object[] { LocalDate.of(invoices.getDate().get(Calendar.YEAR),
//								invoices.getDate().get(Calendar.MONTH), invoices.getDate().get(Calendar.DAY_OF_MONTH)),
//								invoices.getId(), personClient.getName(), documentType.getAcronym(),
//								personClient.getId(), cashierPerson.getName(), cashier.getIdPerson(),
//								invoices.getIdTypeClient(), productsInvoice, invoices.IVA_PERCENTAGE,
//								invoices.getSubtotal(), (invoices.getSubtotal() * invoices.IVA_PERCENTAGE) / 100,
//								invoices.getTotalValue() });
//
//					}
//
//					frameMain.actualizeSalesHistory(salesH);
				}
			}
		} else {
			JOptionPane.showMessageDialog(frameMain, "Falta rellenar algun campo", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Metodo que valida si la informacion de la factura esta completa
	 * 
	 * @param infoTableBill informacion de la factura
	 * @return si es valido o no
	 */
	private boolean validateInfoPrintBill(Object[] infoTableBill) {
		boolean pass = true;
		if (((String) infoTableBill[2]).isBlank()) {
			frameMain.underLineClientName();
			pass = false;
		} else {
			frameMain.resetUnderLineClientName();
		}

		if (((String) infoTableBill[4]).isBlank()) {
			frameMain.underLineClientID();
			pass = false;
		} else {
			frameMain.resetUnderLineClientID();
		}
		return pass;
	}

	/**
	 * Metodo que agrega un producto a la factura
	 * 
	 * @param code codigo del producto
	 * @throws IOException
	 */
	private void addProductToBill(String code) throws IOException {
//		client.getOut().writeByte(5);
		Object[] infoTable = frameMain.getinfoTotalBill();
		Object[] productInfo = frameMain.getProductInfo(Integer.parseInt(code));
		ArrayList<Object[]> productsInBill = frameMain.getProductsInBill();

//		invoiceProductActual.add(new Invoice_Product((int) productInfo[4], (float) productInfo[3], (int) productInfo[1],
//				((String) infoTable[7]).equals("Minorista") ? (float) ((float) productInfo[3] * 0.2)
//						: (float) ((float) productInfo[3] * 0.14)));
//		client.getOut().writeInt(invoiceProductActual.size());

//		for (int i = 0; i < invoiceProductActual.size(); i++) {
//			client.getOut().writeInt(invoiceProductActual.get(i).getIdProduct());
//			client.getOut().writeDouble(invoiceProductActual.get(i).getPurchasePrice());
//			client.getOut().writeInt(invoiceProductActual.get(i).getQuantity());
//			client.getOut().writeDouble(invoiceProductActual.get(i).getGain());
//		}
//
//		client.getOut().writeInt(((String) infoTable[7]).equals("Minorista") ? 1 : 2);

//		float subtotal = client.getIn().readFloat();
//		float totalIva = client.getIn().readFloat();
//		float total = client.getIn().readFloat();

		frameMain.actualizeTotalValues(new Object[] { 10000f, 100000f, 1000000f });

		int option = verifyInBill(productsInBill, productInfo);

		if (option != -1) {
			if (option == 0) {
				frameMain.actualizeBill(productsInBill);
			}
		} else if ((int) productInfo[1] != 0) {
			frameMain.addProductToBill(productInfo);
		}
	}

	/**
	 * Metodo que verifica la cantidad en stock en el inventario cargado
	 * 
	 * @param productsInBill productos en la factura
	 * @param productInfo    informacion del producto
	 * @return 0 si hay productos, 1 si ha excedido el stock, -1 si no existe el
	 *         producto
	 */
	private int verifyInBill(ArrayList<Object[]> productsInBill, Object[] productInfo) {
		int quantyMax = frameMain.getMaxQuantyOf((int) productInfo[0]);
		for (Object[] products : productsInBill) {
			if ((int) products[0] == (int) productInfo[0]) {
				int quanty = (int) products[1];
				quanty += (int) productInfo[1];
				if (quanty <= quantyMax) {
					products[1] = quanty;
					return 0;
				} else {
					JOptionPane.showMessageDialog(frameMain,
							"Error al agregar producto (Se ha excedido la cantidad en stock)", "Error",
							JOptionPane.ERROR_MESSAGE);
					return 1;
				}
			}
		}
		return -1;
	}

	/**
	 * Metodo que verifica la existencia del nombre de usuario en el apartado de
	 * recuperacion de contraseña
	 * 
	 * @throws IOException Excepcion de entrada o salida
	 */
	private void verifyUsername() throws IOException {
		client.getOut().writeByte(3);
		String username = frameMain.getIdJtextfield();
		client.getOut().writeUTF(username);
		if (client.getIn().readBoolean()) {
			frameMain.setUserNameRecovery("Cristian");
			frameMain.addInfoCode();
		} else {
			JOptionPane.showMessageDialog(frameMain, "No se encuentra el usuario", "error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Metodo que verifica si el codigo enviado al correo coincide con el digitado
	 * por el usuario
	 * 
	 * @throws IOException Excepcion de entrada o salida
	 */
	private void verifyCode() throws IOException {
		int code = Integer.parseInt(frameMain.getCodeRecovery());
		client.getOut().writeInt(code);
		if (client.getIn().readBoolean()) {
			frameMain.switchChangePassword();
		}
	}

	/**
	 * Metodo que verifica si las contraseñas digitadas son las mismas y la envia a
	 * la base de datos para su cambio
	 * 
	 * @throws IOException Excepcion de entrada o salida
	 */
	private void changePass() throws IOException {
		String password = frameMain.getPasswordRecovery();
		String passwordConfirm = frameMain.getPasswordConfirmRecovery();
		if (password.equalsIgnoreCase(passwordConfirm)) {
			client.getOut().writeUTF(passwordConfirm);
		} else {
			JOptionPane.showMessageDialog(frameMain, "Contraseñas no validas", "error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Metodo para cerrar la sesion del usuario
	 * 
	 * @throws IOException
	 */
	private void logout() throws IOException {
		int option = JOptionPane.showOptionDialog(frameMain, "Esta seguro que desea cerrar sesión", "Cerrar sesión", 1,
				JOptionPane.YES_NO_CANCEL_OPTION, null, new String[] { "SI", "NO" }, "NO");
		if (option == 0) {
//			client.getOut().writeByte(1);
			if (frameMain.isAdmin()) {
				frameMain.desactiveCalendars();
			} else {
				frameMain.desactiveFilters();
			}
			frameMain.switchLogin();
		}
	}

	/**
	 * Metodo para iniciar sesion con el usuario y el servidor
	 * 
	 * @param username nombre de usuario
	 * @param password contraseña de usuario
	 * @throws IOException Excepcion de entrada o salida
	 */
	private void login(String username, String password) throws IOException {
		frameMain.putBlankText();

		client.getOut().writeByte(0);
		client.getOut().writeUTF(username);
		client.getOut().writeUTF(password);

		if (client.getIn().readBoolean()) {
			if (!client.getIn().readBoolean()) {
				processLogin();
			} else {
				JOptionPane.showMessageDialog(frameMain, "Usuario ya conectado", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(frameMain, "Datos invalidos", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Metodo que procesa el inicio de sesion
	 * 
	 * @throws IOException excepcion de entrada o salida
	 */
	@SuppressWarnings("unchecked")
	private void processLogin() throws IOException {
		useractual = gson.fromJson(client.getIn().readUTF(), User.class);
		personactual = gson.fromJson(client.getIn().readUTF(), Person.class);

		if (useractual.getTypeUser() == EnumTypeUser.ADMINISTRATOR) {
			int productsTotal = Integer.parseInt(client.getIn().readUTF());
			int cashiersTotal = Integer.parseInt(client.getIn().readUTF());
			frameMain.switchViewTo(1, personactual.getName() + " " + personactual.getLastName(), personactual.getId(),
					-1, productsTotal, cashiersTotal, null, null, null);
		} else {
			int size = client.getIn().readInt();
			ArrayList<Product> products2 = new ArrayList<Product>();
			for (int i = 0; i < size; i++) {
				int size2 = client.getIn().readInt();
				byte[] bytesImage = new byte[size2];
				for (int j = 0; j < size2; j++) {
					bytesImage[j] = client.getIn().readByte();
				}
				Product product = new Product(client.getIn().readInt(), client.getIn().readUTF(),
						client.getIn().readUTF(), client.getIn().readInt(), client.getIn().readFloat(),
						gson.fromJson(client.getIn().readUTF(), EnumTypeProduct.class), client.getIn().readByte(),
						bytesImage, client.getIn().readInt(), client.getIn().readInt(), client.getIn().readInt());
				products2.add(product);

			}
			Inventory inventory = new Inventory(products2, "2");
			this.numberBill = Integer.parseInt(client.getIn().readUTF());

			int sizeClientType = client.getIn().readInt();
			ArrayList<ClientType> clientTypes = new ArrayList<ClientType>();
			for (int i = 0; i < sizeClientType; i++) {
				clientTypes.add(
						new ClientType(client.getIn().readInt(), client.getIn().readByte(), client.getIn().readUTF()));
			}

			int sizeSalesHistory = client.getIn().readInt();
			ArrayList<Invoice> salesHistory = new ArrayList<Invoice>();
			for (int i = 0; i < sizeSalesHistory; i++) {
				int sizeProducts = client.getIn().readInt();
				ArrayList<Invoice_Product> productsInInvoice = new ArrayList<Invoice_Product>();
				for (int j = 0; j < sizeProducts; j++) {
					productsInInvoice.add(new Invoice_Product(client.getIn().readInt(), client.getIn().readFloat(),
							client.getIn().readInt(), client.getIn().readFloat()));
				}

				salesHistory.add(new Invoice(client.getIn().readInt(),
						new GregorianCalendar(client.getIn().readInt(), client.getIn().readInt(),
								client.getIn().readInt()),
						client.getIn().readFloat(), gson.fromJson(client.getIn().readUTF(), EnumPaymentType.class),
						client.getIn().readInt(), client.getIn().readInt(), client.getIn().readInt(),
						client.getIn().readInt(), productsInInvoice));
			}

			int sizeWarranties = client.getIn().readInt();
			ArrayList<Warranty> warranties = new ArrayList<Warranty>();
			for (int i = 0; i < sizeWarranties; i++) {
				warranties.add(new Warranty(client.getIn().readInt(), client.getIn().readInt(),
						gson.fromJson(client.getIn().readUTF(), EnumTypeTime.class)));

			}

			ArrayList<Object[]> products = new ArrayList<Object[]>();
			for (Product product : inventory.getProducts()) {

				Warranty warranty = null;
				for (Warranty warranty2 : warranties) {
					if (warranty2.getId() == product.getIdWarranty()) {
						warranty = warranty2;
					}
				}

				products.add(new Object[] { product.getName(), product.getQuantity(), product.getPrice(),
						product.getTypeProduct().toString(), product.getId(), warranty.getWarrantyTime(),
						warranty.getTypeTime(), product.getImage(), product.getDescription() });
			}

			String[] types = new String[clientTypes.size()];
			for (int i = 0; i < clientTypes.size(); i++) {
				types[i] = clientTypes.get(i).toString();
			}

			ArrayList<Object[]> salesH = new ArrayList<Object[]>();
			for (Invoice invoice : salesHistory) {

				client.getOut().writeByte(16);
				client.getOut().writeInt(invoice.getId());
				Person personClient = gson.fromJson(client.getIn().readUTF(), Person.class);

				client.getOut().writeByte(17);
				client.getOut().writeInt(personClient.getIdDocumentType());
				DocumentType documentType = gson.fromJson(client.getIn().readUTF(), DocumentType.class);

				client.getOut().writeByte(18);
				client.getOut().writeInt(invoice.getIdCashier());
				User cashier = gson.fromJson(client.getIn().readUTF(), User.class);

				client.getOut().writeByte(16);
				client.getOut().writeInt(cashier.getIdPerson());
				Person cashierPerson = gson.fromJson(client.getIn().readUTF(), Person.class);

				ArrayList<Object[]> productsInvoice = new ArrayList<Object[]>();

				for (Invoice_Product productInvoice : invoice.getPurchasedProducts()) {

					client.getOut().writeByte(19);
					client.getOut().writeInt(productInvoice.getIdProduct());
					Product product = gson.fromJson(client.getIn().readUTF(), Product.class);
					Warranty warranty = gson.fromJson(client.getIn().readUTF(), Warranty.class);

					productsInvoice.add(new Object[] { productInvoice.getIdProduct(), productInvoice.getQuantity(),
							product.getName(), warranty.warrantyIsActive(invoice.getDate()) ? "Activa" : "Inactiva",
							product.getPrice(), product.getPrice() * productInvoice.getQuantity() });
				}

				salesH.add(new Object[] {
						LocalDate.of(invoice.getDate().get(Calendar.YEAR), invoice.getDate().get(Calendar.MONTH),
								invoice.getDate().get(Calendar.DAY_OF_MONTH)),
						invoice.getId(), personClient.getName(), documentType.getAcronym(), personClient.getId(),
						cashierPerson.getName(), cashier.getIdPerson(), invoice.getIdTypeClient(), productsInvoice,
						invoice.IVA_PERCENTAGE, invoice.getSubtotal(),
						(invoice.getSubtotal() * invoice.IVA_PERCENTAGE) / 100, invoice.getTotalValue() });

			}

			frameMain.switchViewTo(0, personactual.getName() + " " + personactual.getLastName(), personactual.getId(),
					numberBill, -1, -1, products, types, salesH);
		}
	}

	@Override
	public void iEvent(Object source) {
		this.pause();
		try {
			if (source instanceof JPanelOptionSalesHistory) {
				processReportSales(source);
			} else if (source instanceof JPanelOptionCashier) {
				processModidyCashier();
			} else {
				processModifyProduct();
			}
		} catch (IOException e) {
		}
		this.resume();
	}

	private void processModifyProduct() {
		Object option = JOptionPane.showInputDialog(frameMain, "Digite una opcion", "Opciones",
				JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon(
						new ImageIcon("res/confirmation.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)),
				new Object[] { "1. Añadir", "2. Modificar", "3. Eliminar" }, "1. Añadir");
		if (option != null) {
			try {
				switch ((String) option) {
				case "1. Añadir" -> frameMain.switchAddProduct();
				case "2. Modificar" -> modifyProduct();
				case "3. Eliminar" -> removeProduct();
				}
			} catch (IOException e) {
			}
		}
	}

	private void processModidyCashier() {
		Object option = JOptionPane.showInputDialog(frameMain, "Digite una opcion: ", "Opciones",
				JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon(
						new ImageIcon("res/confirmation.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)),
				new Object[] { "1. Añadir", "2. Modificar", "3. Eliminar" }, "1. Añadir");
		if (option != null) {
			try {
				switch ((String) option) {
				case "1. Añadir" -> frameMain.switchAddCashier();
				case "2. Modificar" -> modifyCashier();
				case "3. Eliminar" -> removeCashier();
				}
			} catch (IOException e) {

			}
		}
	}

	/**
	 * Metodo para remover un producto
	 * 
	 * @throws IOException
	 */
	private void removeProduct() throws IOException {
		client.getOut().writeByte(13);
		try {
			int idProduct = Integer
					.parseInt(JOptionPane.showInputDialog("Digite el id del producto que desea modificar"));
			client.getOut().writeInt(idProduct);
		} catch (Exception e) {
			if (!(e instanceof NullPointerException)) {
				JOptionPane.showMessageDialog(frameMain, "Datos invalidos (solo numeros)", "Error",
						JOptionPane.ERROR_MESSAGE);
				client.getOut().writeInt(-1);
			}
		}

		if (client.getIn().readBoolean()) {
			int option = JOptionPane.showOptionDialog(frameMain, "Esta seguro que desea eliminar el producto",
					"Eliminar producto", 1, JOptionPane.YES_NO_OPTION, null, new String[] { "SI", "NO" }, "NO");
			client.getOut().writeBoolean(option == 0);
		} else {
			JOptionPane.showMessageDialog(frameMain, "El id del producto dado no existe en el sistema", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Metodo para modificar un atributo del producto
	 * 
	 * @throws IOException
	 */
	private void modifyProduct() throws IOException {
		client.getOut().writeByte(12);
		try {
			int idProduct = Integer
					.parseInt(JOptionPane.showInputDialog("Digite el id del producto que desea modificar"));
			client.getOut().writeInt(idProduct);
		} catch (Exception e) {
			if (!(e instanceof NullPointerException)) {
				JOptionPane.showMessageDialog(frameMain, "Datos invalidos (solo numeros)", "Error",
						JOptionPane.ERROR_MESSAGE);
				client.getOut().writeInt(-1);
			}
		}
		if (client.getIn().readBoolean()) {
			boolean confirmation = false;
			int option = JOptionPane.showOptionDialog(frameMain, "Seleccione el atributo que desea modificar",
					"Modificar producto", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
					new String[] { "Precio", "Cantidad" }, "Precio");
			if (option == 0) {
				String price = JOptionPane.showInputDialog("Digite el nuevo precio del producto");
				confirmation = price != null;
				if (confirmation) {
					client.getOut().writeByte(0);
					client.getOut().writeFloat(Float.parseFloat(price));
				}
			} else if (option == 1) {
				String quantity = JOptionPane.showInputDialog("Digite la nueva cantidad del producto");
				confirmation = quantity != null;
				if (confirmation) {
					client.getOut().writeByte(1);
					client.getOut().writeFloat(Integer.parseInt(quantity));
				}
			}
		} else {
			JOptionPane.showMessageDialog(frameMain, "El id del producto dado no existe en el sistema", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Metodo para remover un cajero
	 * 
	 * @throws IOException
	 */
	private void removeCashier() throws IOException {
		client.getOut().writeByte(10);
		String idCashier = JOptionPane
				.showInputDialog("Digite el nombre de usuario del cajero que desea modificar la informacion");
		if (idCashier != null) {
			client.getOut().writeUTF(idCashier);
			if (client.getIn().readBoolean()) {
				int option = JOptionPane.showOptionDialog(frameMain,
						"Esta seguro que desea eliminar el cajero (esta accion no se puede revertir)", "Remover Cajero",
						JOptionPane.DEFAULT_OPTION, JOptionPane.YES_NO_OPTION, null, new String[] { "SI", "NO" }, null);
				client.getOut().writeBoolean(option == 0);
			}
		}
	}

	/**
	 * Metodo para modificar un atributo de un cajero
	 * 
	 * @throws IOException
	 */
	private void modifyCashier() throws IOException {
		client.getOut().writeByte(9);
		String idCashier = JOptionPane
				.showInputDialog("Digite el nombre de usuario del cajero que desea modificar la informacion");
		if (idCashier != null) {
			client.getOut().writeUTF(idCashier);
			if (client.getIn().readBoolean()) {
				int option = JOptionPane.showOptionDialog(frameMain, "Seleccione el atributo que desea modificar",
						"Modificar Cajeros", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
						new String[] { "Nombre de usuario", "Contraseña", "Correo Electronico" }, null);
				boolean confirmation = false;
				switch (option) {
				case 0:
					String newName = JOptionPane.showInputDialog("Digite el nuevo nombre de usuario");
					confirmation = newName != null;
					client.getOut().writeBoolean(confirmation);
					if (confirmation) {
						client.getOut().writeByte(option);
						client.getOut().writeUTF(newName);
					}
					break;
				case 1:
					String newPass = JOptionPane.showInputDialog("Digite la nueva contraseña");
					String newPassConfirmation = JOptionPane.showInputDialog("Confirme nuevamente la nueva contraseña");
					confirmation = (newPass != null && newPassConfirmation != null)
							&& newPass.equals(newPassConfirmation);
					client.getOut().writeBoolean(confirmation);
					if (confirmation) {
						client.getOut().writeByte(option);
						client.getOut().writeUTF(newPass);
					}
					break;
				case 2:
					String newEmail = JOptionPane.showInputDialog("Digite el nuevo correo electronico");
					confirmation = newEmail != null;
					client.getOut().writeBoolean(confirmation);
					if (confirmation) {
						client.getOut().writeByte(option);
						client.getOut().writeUTF(newEmail);
					}
					break;
				}
			} else {
				JOptionPane.showMessageDialog(frameMain, "El id del cajero no esta registrado en el sistema", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
