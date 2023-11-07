package domain;

public class Equipment {
	//Declaration and Initialization
	private String equipmentId;
	private String type;
	private double price;
	private String status;
	
	//Constructors
	public Equipment() {
		this.equipmentId = "";
		this.type = "";
		this.price = 0;
		this.status = "available";
	}
	
	public Equipment(String equipmentId, String type, double price, String status) {
		this.equipmentId = equipmentId;
		this.type = type;
		this.price = price;
		this.status = status;
	}
	
	public Equipment(String type, double price) {
		this.type = type;
		this.price = price;
	}
	
	//Getters and Setters
	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	//Display
	@Override
	public String toString() {
		return "Equipment ID: " + equipmentId + "\nType: " + type + "\nPrice: " + price + "\nStatus: " + status
				+ "";
	}
	
}
