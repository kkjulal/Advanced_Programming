package domain;

public class Equipment {
	//Declaration and Initialization
	private String equipmentId;
	private String category;
	private double price;
	private String status;
	
	//Constructors
	public Equipment() {
		this.equipmentId = "";
		this.category = "";
		this.price = 0;
		this.status = "available";
	}
	
	public Equipment(String equipmentId, String type, double price, String status) {
		this.equipmentId = equipmentId;
		this.category = type;
		this.price = price;
		this.status = status;
	}
	
	public Equipment(String type, double price) {
		this.category = type;
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
		return category;
	}

	public void setType(String type) {
		this.category = type;
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
		return "Equipment ID: " + equipmentId + "\nCategory: " + category + "\nPrice: " + price + "\nStatus: " + status
				+ "";
	}
	
}
