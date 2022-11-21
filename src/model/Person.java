package model;

public class Person {
	private int id;
	private String name;
	private String lastName;
	private int idDocumentType;
	private EnumPersonType personType;

	public Person(int id, String name, String lastName, EnumPersonType personType, int idDocumentType) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.idDocumentType = idDocumentType;
		this.personType = personType;
	}

	public boolean isEqual(Person person) {
		return this.id == person.id && this.name.equals(person.name) && this.lastName.equals(person.lastName);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}
	
	public int getIdDocumentType() {
		return idDocumentType;
	}
	
	public EnumPersonType getPersonType() {
		return personType;
	}
}
