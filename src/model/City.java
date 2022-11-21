package model;

public class City {
	private int id;
	private String nameCity;
	private int idCountry;
	
	public City(int id, String nameCity, int idCountry) {
		this.id = id;
		this.nameCity = nameCity;
		this.idCountry = idCountry;
	}

	public int getId() {
		return id;
	}

	public String getNameCity() {
		return nameCity;
	}

	public int getIdCountry() {
		return idCountry;
	}
	
}
