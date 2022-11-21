package model;

public class Supplier {
	private int id;
	private String name;
	private String address;
	private long phoneNumber;
	private int idCity;
	
	public Supplier(int id, String name, String address, long phoneNumber, int idCity) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.idCity = idCity;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}
	
	public long getPhoneNumber() {
		return phoneNumber;
	}

	public int getIdCity() {
		return idCity;
	}
	
}
