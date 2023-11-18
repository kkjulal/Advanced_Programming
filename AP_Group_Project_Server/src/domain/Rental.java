package domain;

import java.io.Serializable;

public class Rental implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4412211134326205179L;
	//Declaration and Initialization
	private String customer;
	private String equipment;
	private Date date;
	private Date startDate;
	private int duration;
	private double cost;
	private String employee;
	
	//Constructors
	public Rental(String customer, String equipment, Date date, Date startDate, int duration, double cost, String employee) {
		this.customer = customer;
		this.equipment = equipment;
		this.date = date;
		this.startDate = startDate;
		this.duration = duration;
		this.cost = cost;
		this.employee = employee;
	}
	
	//Getters and Setters
	public String getCustomer() {
		return customer;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	//Display
	@Override
	public String toString() {
		return "Customer: " + customer + "\nEquipment: " + equipment + "\nDate: " + date + "\nStart Date: " + startDate + "\nDuration: " + duration
				+ "\nCost: " + cost + "\nEmployee: " + employee + "";
	}

}
