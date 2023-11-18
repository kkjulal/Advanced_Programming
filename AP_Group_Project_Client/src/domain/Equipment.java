package domain;

import java.io.Serializable;

import javax.persistence.Id;

public class Equipment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6775809575997525191L;
	//Declaration and Initialization
	@Id
	private String equipmentId;
	private String name;
	private String category;
	private double price;
	private String status;
	
	//Constructors
	public Equipment() {
		this.equipmentId = "";
		this.name = "";
		this.category = "";
		this.price = 0;
		this.status = "available";
	}
	
	public Equipment(String equipmentId, String name, String category, double price, String status) {
		this.equipmentId = equipmentId;
		this.name = name;
		this.category = category;
		this.price = price;
		this.status = status;
	}
	
	public Equipment(String equipmentId, String name, String category, String status) {
		this.equipmentId = equipmentId;
		this.name = name;
		this.category = category;
		this.status = status;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Equipment(String category, double price) {
		this.category = category;
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

	public void setType(String category) {
		this.category = category;
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
		return "Equipment ID: " + equipmentId + "\nName: " + name + "\nCategory: " + category + "\nPrice: " + price + "\nStatus: " + status + "";
	}
	
}
