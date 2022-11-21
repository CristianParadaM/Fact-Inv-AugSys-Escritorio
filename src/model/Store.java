package model;

public class Store {
	private String name;
	private String address;
	private long phoneNumber;
	private String email;
	private int id_city;
	
	public Store(String name, String address, long phoneNumber, String email, int id_city) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.id_city = id_city;
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
	
	public String getEmail() {
		return email;
	}

	public int getId_city() {
		return id_city;
	}
}
