package model;

import java.util.ArrayList;
import java.util.Calendar;

public class Invoice {
	public static final byte IVA_PERCENTAGE = 19;
	private int id;
	private Calendar date;
	private float subtotal;
	private float totalValue;
	private EnumPaymentType wayToPay;
	private ArrayList<Invoice_Product> purchasedProducts;
	private int idClient;
	private int idCashier;
	private int idStore;
	private int idTypeClient;
	
	public Invoice(int id, Calendar date, float subtotal, EnumPaymentType wayToPay, int idClient,
			int idCashier, int idStore, int idTypeClient, ArrayList<Invoice_Product> purchasedProducts) {
		this.id = id;
		this.date = date;
		this.subtotal = subtotal;
		this.totalValue = subtotal +(subtotal*(IVA_PERCENTAGE/100));
		this.wayToPay = wayToPay;
		this.purchasedProducts = purchasedProducts;
		this.idClient = idClient;
		this.idCashier = idCashier;
		this.idStore = idStore;
		this.idTypeClient = idTypeClient;
	}

	public static byte getIvaPercentage() {
		return IVA_PERCENTAGE;
	}

	public int getId() {
		return id;
	}

	public Calendar getDate() {
		return date;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public float getTotalValue() {
		return totalValue;
	}

	public EnumPaymentType getWayToPay() {
		return wayToPay;
	}
	
	public ArrayList<Invoice_Product> getPurchasedProducts() {
		return purchasedProducts;
	}
	
	public void setPurchasedProducts(ArrayList<Invoice_Product> purchasedProducts) {
		this.purchasedProducts = purchasedProducts;
	}

	public int getIdClient() {
		return idClient;
	}

	public int getIdCashier() {
		return idCashier;
	}	
	
	public int getIdStore() {
		return idStore;
	}
	
	public int getIdTypeClient() {
		return idTypeClient;
	}
}
