package model;

public class Country {
	private int id;
	private String nameCountry;
	
	public Country(int id, String nameCountry) {
		this.id = id;
		this.nameCountry = nameCountry;
	}

	public int getId() {
		return id;
	}

	public String getNameCountry() {
		return nameCountry;
	}
	
}
