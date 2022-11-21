package model;

import java.util.Calendar;

public class Warranty {
	private int id;
	private int warrantyTime;
	private EnumTypeTime typeTime;
	
	public Warranty(int id, int warrantyTime, EnumTypeTime typeTime) {
		this.id = id;
		this.warrantyTime = warrantyTime;
		this.typeTime = typeTime;
	}
	
	public boolean isEqual(Warranty warranty) {
		return this.warrantyTime == warranty.warrantyTime && this.typeTime == warranty.typeTime;
	}
	
	public boolean warrantyIsActive(Calendar datePurchase) {
		if (typeTime == EnumTypeTime.MONTH) {
			datePurchase.add(Calendar.MONTH, warrantyTime);
		}else {
			datePurchase.add(Calendar.YEAR, warrantyTime);
		}
		return datePurchase.before(Calendar.getInstance());
	}
	
	public int getId() {
		return id;
	}

	public int getWarrantyTime() {
		return warrantyTime;
	}

	public EnumTypeTime getTypeTime() {
		return typeTime;
	}
	
}
