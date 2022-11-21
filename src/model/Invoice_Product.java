package model;

public class Invoice_Product {
	private int idProduct;
	private float purchasePrice;
	private int quantity;
	private float gain;
	
	public Invoice_Product(int idProduct, float purchasePrice, int quantity, float gain) {
		this.idProduct = idProduct;
		this.purchasePrice = purchasePrice;
		this.quantity = quantity;
		this.gain = gain;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public float getPurchasePrice() {
		return purchasePrice;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public float getGain() {
		return gain;
	}
	
}
