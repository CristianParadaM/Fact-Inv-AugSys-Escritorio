package model;

public class ClientType {
	private int id;
	private byte percentageOfProfit;
	private String typeClient;
	
	public ClientType(int id, byte percentageOfProfit, String typeClient) {
		this.id = id;
		this.percentageOfProfit = percentageOfProfit;
		this.typeClient = typeClient;
	}

	public int getId() {
		return id;
	}

	public byte getPercentageOfProfit() {
		return percentageOfProfit;
	}

	public String getTypeClient() {
		return typeClient;
	}
}
