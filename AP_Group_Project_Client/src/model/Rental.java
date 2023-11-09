package domain;

public class Rental {
	//Declaration and Initialization
	private String customer;
	private String equipment;
	private Date date;
	private int duration;
	private double cost;
	private String employee;
	
	//Constructors
	public Rental(String customer, String equipment, Date date, int duration, double cost, String employee) {
		this.customer = customer;
		this.equipment = equipment;
		this.date = date;
		this.duration = duration;
		this.cost = cost;
		this.employee = employee;
	}
	
	//Getters and Setters
	public String getCustomer() {
		return customer;
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
		return "Customer: " + customer + "\nEquipment: " + equipment + "\nDate: " + date + "\nDuration: " + duration
				+ "\nCost: " + cost + "\nEmployee: " + employee + "";
	}

}
