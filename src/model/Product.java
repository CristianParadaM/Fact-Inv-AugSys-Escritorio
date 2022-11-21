package model;

import javax.swing.ImageIcon;

public class Product {
	private int id;
	private String name;
	private String description;
	private int quantity;
	private float price;
	private EnumTypeProduct typeProduct;
	private byte state;
	private byte[] image;
	private int idWarranty;
	private int idSupplier;
	private int idInventory;
	
	public Product(int id, String name, String description, int quantity, float price,
			EnumTypeProduct typeProduct, byte state, byte[] image, int id_warranty,
			int idSupplier, int id_inventory) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.typeProduct = typeProduct;
		this.state = state;
		this.image = image;
		this.idWarranty = id_warranty;
		this.idSupplier = idSupplier;
		this.idInventory = id_inventory;
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void substracQuantity(int quantity) {
		this.quantity -= quantity;
	}

	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}

	public EnumTypeProduct getTypeProduct() {
		return typeProduct;
	}
	
	public byte[] getImage() {
		return image;
	}

	public int getIdWarranty() {
		return idWarranty;
	}
	
	public int getIdSupplier() {
		return idSupplier;
	}
	
	public int getIdInventory() {
		return idInventory;
	}
	
	public byte getState() {
		return this.state;
	}
	
	public void setState(byte state) {
		this.state = state;
	}
}
