package model;

public class DocumentType {
	private int id;
	private String acronym;
	private String description;
	
	public DocumentType(int id, String acronym, String description) {
		this.id = id;
		this.acronym = acronym;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getAcronym() {
		return acronym;
	}

	public String getDescription() {
		return description;
	}
}
