package model;

public class User {
	private int id;
	private String userName;
	private String password;
	private EnumTypeUser typeUser;
	private String email;
	private byte state;
	private int idPerson;
	
	public User(int id, String userName, String password, EnumTypeUser typeUser, String email, byte state, int idPerson) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.typeUser = typeUser;
		this.email = email;
		this.state = state;
		this.idPerson = idPerson;
	}
	
	public int getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public EnumTypeUser getTypeUser() {
		return typeUser;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public byte getState() {
		return state;
	}
	
	public void setState(byte state) {
		this.state = state;
	}
	
	public int getIdPerson() {
		return idPerson;
	}
}
