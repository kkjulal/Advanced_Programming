package domain;

import java.io.Serializable;

public class Payment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8776402468662556224L;
	//Declaration and Initialization
	private String paymentId;
	private String customer;
	private String equipment;
	private String rental;
	private double amount;
	private Date date;

	//Constructors
	public Payment() {
		this.paymentId = "";
		this.customer = "";
		this.equipment = "";
		this.rental = "";
		this.amount = 0;
		this.date = new Date(1, 1, 1979);
	}

	public Payment(String paymentId, String customer, String equipment, String rental, double amount, Date date) {
		super();
		this.paymentId = paymentId;
		this.customer = customer;
		this.equipment = equipment;
		this.rental = rental;
		this.amount = amount;
		this.date = date;
	}
	
	//Getters and Setters
	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

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

	public String getRental() {
		return rental;
	}

	public void setRental(String rental) {
		this.rental = rental;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Payment ID: " + paymentId + "\nCustomer ID: " + customer + "\nEquipment ID: " + equipment + "\nRental ID: "
				+ rental + "\nAmount: " + amount + "\nDate: " + date + "";
	}
}
