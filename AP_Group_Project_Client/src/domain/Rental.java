package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Rental")
public class Rental {
	//Declaration and Initialization
	@Id
	@Column(name="customer_id")
	private String customer;
	@Column(name="equipment")
	private String equipment;
	@Column(name="date")
	private Date date;
	@Column(name="start_date")
	private Date start_date;
	@Column(name="duration")
	private int duration;
	@Column(name="cost")
	private double cost;
	@Column(name="employee")
	private String employee;
	
	//Constructors
	public Rental(String customer, String equipment, Date date, Date start_date, int duration, double cost, String employee) {
		this.customer = customer;
		this.equipment = equipment;
		this.date = date;
		this.start_date = start_date;
		this.duration = duration;
		this.cost = cost;
		this.employee = employee;
	}
	
	//Getters and Setters
	public String getCustomer() {
		return customer;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
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
		return "Customer: " + customer + "\nEquipment: " + equipment + "\nDate: " + date + "\nStart Date: " + start_date + "\nDuration: " + duration
				+ "\nCost: " + cost + "\nEmployee: " + employee + "";
	}

}
