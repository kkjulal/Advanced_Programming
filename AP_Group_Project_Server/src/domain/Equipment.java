package domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Equipment implements Serializable {
	//Declaration and Initialization
	private String equipmentId;
	private String name;
	private String category;
	private String status;
	
	//Constructors
	public Equipment(String equipmentId, String name, String category, String status) {
		this.equipmentId = equipmentId;
		this.name = name;
		this.category = category;
		this.status = status;
	}
	
	//Getters and Setters
	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
		return "Equipment ID: " + equipmentId + "\nName: " + name + "\nCategory: " + category + "\nStatus: " + status + "";
	}
	
}
