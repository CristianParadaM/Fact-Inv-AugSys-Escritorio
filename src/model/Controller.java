package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.ImageIcon;
/**
 * Clase que se encarga de conectar y controlar todo el modelo
 * @author Daniel Felipe Suarez Bohorquez
 * 15/03/2022
 */
public class Controller {
	private Store store;
	private Inventory inventory;
	private ListPerson persons;
	private ListUsers users;
	private ArrayList<Invoice> invoices;
	private ArrayList<City> cities;
	private ArrayList<Country> countries;
	private ArrayList<Warranty> warranties;
	private ArrayList<ClientType> clientTypes;
	private ArrayList<DocumentType> documentTypes;
	private ListSupplier suppliers;
	
	public Controller(Store store, Inventory inventory, ListPerson persons, ListUsers cashiers,
			ArrayList<Invoice> invoices, ArrayList<City> cities, ArrayList<Country> countries,
			ArrayList<Warranty> warranties, ArrayList<ClientType> clientTypes, ArrayList<DocumentType> documentTypes, ListSupplier suppliers) {
		this.store = store;
		this.inventory = inventory;
		this.persons = persons;
		this.users = cashiers;
		this.invoices = invoices;
		this.cities = cities;
		this.countries = countries;
		this.warranties = warranties;
		this.clientTypes = clientTypes;
		this.documentTypes = documentTypes;
		this.suppliers = suppliers;
	}

	/**
	 * Obtiene todas las facturas dada una fecha inicial y una final
	 * @param initDate fecha inicial
	 * @param finalDate fecha final
	 * @return lista de facturas
	 */
	public ArrayList<Invoice> getInvoiceAt(Calendar initDate, Calendar finalDate) {
		ArrayList<Invoice> aux = new ArrayList<>();
		for (Invoice invoice : invoices) {
			if (invoice.getDate().after(finalDate)) {
				break;
			} else if (invoice.getDate().equals(initDate) || invoice.getDate().equals(finalDate)
					|| (invoice.getDate().after(initDate) && invoice.getDate().before(finalDate))) {
				aux.add(invoice);
			}
		}
		return aux;
	}
	
	/**
	 * Metodo que valida los datos de un usuario
	 * @param userName nombre de usuario
	 * @param password contraseña
	 * @return true si son validos o false si no
	 */
	public boolean validataUser(String userName, String password) {
		return users.isValid(userName, password);
	}
	
	/**
	 * Metodo que busca un usario en el sistema
	 * @param userName nombre de usuario con el que se buscara al usuario
	 * @return El usuario si fue encontrado o null si no
	 */
	public User searchUser(String userName) {
		return users.searchUser(userName);
	}
	
	/**
	 * Metodo para buscar una persona
	 * @param id identificacion de la persona que desea buscar
	 * @return persona si se encontro o null si no
	 */
	public Person searchPerson(int id) {
		return persons.searchPerson(id);
	}
	
	/**
	 * Retorna el codigo para una nueva factura
	 * @return codigo de la factura
	 */
	public int getCodeInvoice() {
		if (invoices.size() == 0) {
			return 20221001;
		}else {
			return invoices.get(invoices.size()-1).getId()+1;
		}
	}
	
	/**
	 * Metodo que obtiene la cantidad de cajeros activos en el sistema
	 * @return cantidad de cajeros
	 */
	public int getQuantityCashiers() {
		return users.getQuantityCashiers();
	}
	
	/**
	 * Metodo que retorna la cantidad de productos activos en el sistema
	 * @return cantidad de productos
	 */
	public int getQuantityProducts() {
		return inventory.getQuantityProducts();
	}
	
	/**
	 * Metodo para enviar un correo de recuperacion de contraseña con un codigo
	 * @param receptorEmail correo del usuario
	 * @param code codigo que se va a enviar
	 */
	public void sendEmail(String receptorEmail, int code) {
		try {
			String remitente = "ausy.soporte@gmail.com";
			String receptor = receptorEmail;
			String password = "AuSyDC2022";
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.user", "remitente");
			props.setProperty("mail.smtp.auth", "true");
			Session session = Session.getDefaultInstance(props, null);

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(remitente));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
			message.setSubject("RECUPERACION DE CONTRASEÑA");
			BodyPart text = new MimeBodyPart();
			text.setText(
					"se ha registrado una peticion de recuperacion de contraseña, su codigo para continuar con el proceso es \n\n"
							+ code);
			MimeMultipart multiParte = new MimeMultipart();
			multiParte.addBodyPart(text);
			message.setContent(multiParte);
			Transport t = session.getTransport("smtp");
			t.connect(remitente, password);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que genera un codigo al azar
	 * @return codigo generado
	 */
	public int getRandomCode() {
		Random random = new Random();
		String code = "";
		for (int i = 0; i < 9; i++) {
			code += "" + random.nextInt(9);
		}
		return Integer.parseInt(code);
	}
	
	/**
	 * Metodo que verifica si un usuario existe
	 * @param userName nombre de usuario del usuario
	 * @return verdadero si existe o falso si no
	 */
	public boolean existUser(String userName) {
		return users.searchUser(userName) != null;
	}
	
	/**
	 * Metodo para obtener los datos necesarios de los productos para enviarselos a la vista
	 * @return matriz con los datos
	 */
	public ArrayList<Object[]> getDataProducts(){
		ArrayList<Object[]> objects = new ArrayList<>();
		Warranty warranty = null;
		for (Product product : inventory.getProducts()) {
			if (product.getState() == 1) {
				warranty = getWarranty(product.getIdWarranty());
				objects.add(new Object[] {
						product.getName(),
						product.getQuantity(),
						product.getPrice()+(product.getPrice()*(searchClientType(1).getPercentageOfProfit())),
						product.getTypeProduct().name(),
						product.getId(),
						warranty.getWarrantyTime(),
						warranty.getTypeTime().ordinal(),
						new ImageIcon(product.getImage()),
						product.getDescription()	
				});
			}
		}
		return objects;
	}
	
	/**
	 * Metodo que obtiene una garantia pasando el id de la misma
	 * @param id identificacion de la garantia
	 * @return garantia si se hallo o null si no
	 */
	public Warranty getWarranty(int id) {
		for (Warranty warranty : warranties) {
			if (warranty.getId() == id) {
				return warranty;
			}
		}
		return null;
	}
	
	/**
	 * Metodo que obtiene los datos de la tienda
	 * @return datos de la tienda en un arreglo
	 */
	public Object[] getDataStore() {
		Object[] objects = new Object[5];
		objects[0] = store.getName();
		objects[1] = store.getAddress();
		objects[2] = store.getEmail();
		objects[3] = store.getPhoneNumber();
		objects[4] = Invoice.IVA_PERCENTAGE;
		return objects;
	}
	
	/**
	 * Metodo para añadir una nueva factura
	 */
	public void addInvoice(Invoice invoice) {
		invoices.add(invoice);
	}
	
	/**
	 * Metodo para verificar si existe una persona en el sistema
	 * @param idPerson id de la persona
	 * @return true si existe o false si no
	 */
	public boolean existPerson(int idPerson) {
		return persons.searchPerson(idPerson) != null;
	}
	
	/**
	 * Metodo para añadir una persona a la lista de personas
	 * @param person persona a añadir
	 */
	public void addPerson(Person person) {
		persons.addPerson(person);
	}
	
	/**
	 * Metodo que obtiene los valores de subtotal, ivaValor y totalValue de una factura
	 * @param infoProductToInvoice informacion de los productos de la factura
	 * @param percentageOfProfit porcentaje de ganancia que se les aplica a dichos productos
	 * @return arreglo de float con los valores obtenidos
	 */
	public float[] getValuesToInvoice(ArrayList<Object[]> infoProductToInvoice, byte percentageOfProfit) {
		return inventory.getValuesToInvoice(infoProductToInvoice, percentageOfProfit);
	}
	
	/**
	 * Metodo que busca un tipo de cliente pasando su id
	 * @param idClientType id del tipo de cliente que se desea buscar
	 * @return tipo de cliente se lo encontro o null si no
	 */
	public ClientType searchClientType(int idClientType) {
		for (ClientType clientType : clientTypes) {
			if (clientType.getId() == idClientType) {
				return clientType;
			}
		}
		return null;
	}

	/**
	 * Metodo que obtien los tipos de clientes que hay en el sistema como Strings
	 * @return tipos de cliente en un arreglo de strings
	 */
	public ArrayList<String> getDataClientTypes() {
		ArrayList<String> data = new ArrayList<>();
		for (int i = 0; i < clientTypes.size(); i++) {
			data.add(clientTypes.get(i).getTypeClient());
		}
		return data;
	}
	
	/**¨
	 * Metodo para obtener el valor unitario y el valor total de una lista de productos dependiendo el tipo de cliente
	 * @param infoProductToInvoice lista con la informacion de los productos id y cantidad
	 * @param percentageOfProfit porcentaje de ganancia que se le aplicara al producto
	 * @return lista con la informacion de los precios: precio unitario y precio total
	 */
	public ArrayList<Object[]> getValuesProducts(ArrayList<Object[]> infoProductToInvoice, byte percentageOfProfit) {
		ArrayList<Object[]> prices = new ArrayList<>(infoProductToInvoice.size());
		for (int i = 0; i < prices.size(); i++) {
			float value = inventory.searchProduct((int)infoProductToInvoice.get(i)[0]).getPrice();
			value += value*percentageOfProfit;
			prices.add(new Object[] {value, value*(int)infoProductToInvoice.get(i)[1]});
		}
		return prices;
	}
	
	/**
	 * Metodo que obtiene el historial de facturas
	 * @return lista con el historial de facturas
	 */
	public ArrayList<Object[]> getInvoicesHistory() {
		ArrayList<Object[]> invoicesHistory = new ArrayList<>(invoices.size());
		for (Invoice invoice : invoices) {
			ArrayList<Object[]> dataProductsToInvoice = getDataProductsToInvoice(invoice.getPurchasedProducts(), invoice.getDate());
			float[] values = getValuesToHistory(dataProductsToInvoice);
			invoicesHistory.add(new Object[] {
					LocalDate.of(invoice.getDate().get(Calendar.YEAR), invoice.getDate().get(Calendar.MONTH), invoice.getDate().get(Calendar.DAY_OF_MONTH)),
					invoice.getId(),
					getNamePerson(invoice.getIdClient()),
					getAcronym(getIdDocumentType(invoice.getIdClient())),
					invoice.getIdClient(),
					getNamePerson(invoice.getIdCashier()),
					invoice.getIdCashier(),
					getTypeClient(invoice.getIdTypeClient()),
					invoice.getWayToPay().ordinal() == 1?"Efectivo":invoice.getWayToPay().ordinal() == 2?"Tarjeta credito":"Tarjeta debito",
					dataProductsToInvoice,
					invoice.IVA_PERCENTAGE,
					values[0],
					values[1],
					values[2]
			});
		}
		return invoicesHistory;
	}
	
	/**
	 * Obtiene los valores de subtotal, valor del iva y valor total de los productos de una factura
	 * @param dataProductsToInvoice datos de los productos de la factura
	 * @return arreglo de float con los tres valores mencionados
	 */
	private float[] getValuesToHistory(ArrayList<Object[]> dataProductsToInvoice) {
		float subtotal = 0, ivaValue = 0, totalValue = 0;
		for (Object[] objects : dataProductsToInvoice) {
			subtotal += (float)objects[objects.length-1];
		}
		ivaValue = totalValue*Invoice.IVA_PERCENTAGE;
		totalValue = subtotal + ivaValue;
		return new float[] {subtotal, ivaValue, totalValue};
	}

	/**
	 * Metodo para obtener los datos pertenecientes a una factura
	 * @param purchasedProducts infoormacion de los productos de las facturas
	 * @return lista con la informacion de los productos
	 */
	private ArrayList<Object[]> getDataProductsToInvoice(ArrayList<Invoice_Product> purchasedProducts, Calendar date) {
		ArrayList<Object[]> data = new ArrayList<>(purchasedProducts.size());
		for (Invoice_Product invoice_Product : purchasedProducts) {
			data.add(new Object[] {invoice_Product.getIdProduct(), invoice_Product.getQuantity(), inventory.getNameProduct(invoice_Product.getIdProduct()), isActiveWarranty(date, getIdWarrantyToProduct(invoice_Product.getIdProduct()))==true?"Activa":"Inactiva", invoice_Product.getPurchasePrice(), invoice_Product.getPurchasePrice()*invoice_Product.getQuantity()});
		}
		return data;
	}
	
	/**
	 * Metodo que verifiva si una garantia sigue activa
	 * @param date fecha en la que se genero la factura
	 * @param idWarrantyToProduct id de la garnatia que se desea verificar
	 * @return true si la grantia sigue activa o false si no
	 */
	private boolean isActiveWarranty(Calendar date, int idWarrantyToProduct) {
		for (Warranty warranty : warranties) {
			if (warranty.getId() == idWarrantyToProduct) {
				return warranty.warrantyIsActive(date);
			}
		}
		return false;
	}
	
	/**
	 * Metodo para obtener el id de la garantia de un producto
	 * @param idProduct id del producto
	 * @return id de la garantia del producto
	 */
	private int getIdWarrantyToProduct(int idProduct) {
		return inventory.getIdWarrantyToProduct(idProduct);
	}

	/**
	 * Metodo para obtener la descripcion del tipo de cliente pasando el id del mismo
	 * @param idTypeClient id tipo de cliente
	 * @return tipo de cliente como string
	 */
	private String getTypeClient(int idTypeClient) {
		for (ClientType clientType : clientTypes) {
			if (clientType.getId() == idTypeClient) {
				return clientType.getTypeClient();
			}
		}
		return "";
	}

	/**
	 * Metodo para obtener el acronimo de un tipo de documento
	 * @param idDocumentType id del tipo de documento
	 * @return acronimo como String
	 */
	private String getAcronym(int idDocumentType) {
		for (DocumentType documentType : documentTypes) {
			if (idDocumentType == documentType.getId()) {
				return documentType.getAcronym();
			}
		}
		return "";
	}
	
	/**
	 * Metodo para obtener el id del tipo de documento de un cliente
	 * @param idClient id del cliente
	 * @return id del tipo de docuemnto
	 */
	private int getIdDocumentType(int idClient) {
		return persons.getIdDocumentType(idClient);
	}
	
	/**
	 * Metodo para obtener el nombre de una persona con su id
	 * @param idPerson id de la persona
	 * @return Nombre de la persona si existe o string vacio si no
	 */
	private String getNamePerson(int idPerson) {
		return persons.getNamePerson(idPerson);
	}
	
	/**
	 * Metodo que remueve los productos de una compra del inventario
	 * @param salesHistory datos de los productos que se compraron
	 */
	public void removePurchaseToInventary(ArrayList<Invoice_Product> salesHistory) {
		inventory.removePurchaseToInventary(salesHistory);
	}
	
	/**
	 * Metodo para añadir nuevos usuarios
	 * @param user usuario que se va a añadir
	 */
	public void addUser(User user) {
		users.addUser(user);
	}
	
	/**
	 * Metodo que busca un producto con su id
	 * @param idProduct identificador del poducto
	 * @return producto
	 */
	public Product searchProduct(int idProduct) {
		return inventory.searchProduct(idProduct);
	}
	
	/**
	 * Metodo para buscar un pais
	 * @param string nombre del pais
	 * @return pais si se encontro o null si no
	 */
	public Country searchCountry(String string) {
		for (Country country : countries) {
			if (country.getNameCountry().equalsIgnoreCase(string)) {
				return country;
			}
		}
		return null;
	}
	
	/**
	 * Metodo para obtener el id para un nuevo pais
	 * @return id del nuevo pais
	 */
	public int getIdCountry() {
		int id = 0;
		for (Country country : countries) {
			if (country.getId() > id) {
				id = country.getId();
			}
		}
		return id+1;
	}
	
	/**
	 * Metodo para añadir un nuevo pais
	 * @param country pais a añadir
	 */
	public void addCountry(Country country) {
		countries.add(country);
	}
	
	/**
	 * Metodo para buscar una ciudad
	 * @param string nombre de la ciudad
	 * @return ciudad si se encontro o null si no
	 */
	public City searchCity(String string, int idCountry) {
		for (City city : cities) {
			if (city.getNameCity().equalsIgnoreCase(string) && city.getIdCountry() == idCountry) {
				return city;
			}
		}
		return null;
	}
	
	/**
	 * Metodo para obtener un nuevo codigo para una ciudad
	 * @return nuevo codigo
	 */
	public int getIdCity() {
		return cities.get(cities.size()-1).getId()+1;
	}
	
	/**
	 * Metodo para añadir una nueva ciudad
	 * @param city ciudad a añadir
	 */
	public void addCity(City city) {
		cities.add(city);
	}
	
	/**
	 * Metodo para buscar un proovedor
	 * @param id identificador del proovedor
	 * @return proovedor si se encontro o null si no
	 */
	public Supplier searchSupplier(int id) {
		return suppliers.searchSupplier(id);
	}
	
	/**
	 * Metodo para añadir un nuevo proovedor
	 * @param supplier proovedor a añadir
	 */
	public void addSupplier(Supplier supplier) {
		suppliers.addSupplier(supplier);
	}
	
	/**
	 * Metodo para buscar una garantia
	 * @param time tiempo de garantia
	 * @param enumTypeTime tipo de tiempo
	 * @return garantia si existe o null si no
	 */
	public Warranty searchWarranty(int time, EnumTypeTime enumTypeTime) {
		for (Warranty warranty : warranties) {
			if (warranty.getWarrantyTime() == time && warranty.getTypeTime() == enumTypeTime) {
				return warranty;
			}
		}
		return null;
	}
	
	/**
	 * Metodo que obtiene el id para una nueva garantia
	 * @return nuevo id
	 */
	public int getIdWarranty() {
		return warranties.get(warranties.size()-1).getId()+1;
	}
	
	/**
	 * Metodo que añade una nueva garantia
	 * @param warranty garantia que se añadira
	 */
	public void addWarranty(Warranty warranty) {
		warranties.add(warranty);
	}
	
	/**
	 * Metodo que añade un nuevo producto al inventario
	 * @param product producto a añadir
	 */
	public void addProduct(Product product) {
		inventory.add(product);
	}


}
