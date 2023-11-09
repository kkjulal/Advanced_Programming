package domain;

public class Payment {
	//Declaration and Initialization
	private String paymentId;
	private String customer;
	private String equipment;
	private String rental;
	private double amountPaid;
	private Date date;

	//Constructors
	public Payment() {
		this.paymentId = "";
		this.customer = "";
		this.equipment = "";
		this.rental = "";
		this.amountPaid = 0;
		this.date = new Date(1, 1, 1979);
	}

	public Payment(String paymentId, String customer, String equipment, String rental, double amountPaid, Date date) {
		super();
		this.paymentId = paymentId;
		this.customer = customer;
		this.equipment = equipment;
		this.rental = rental;
		this.amountPaid = amountPaid;
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

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Payment ID: " + paymentId + "Customer ID: " + customer + "Equipment ID: " + equipment + "Rental ID: "
				+ rental + "Amount Paid: " + amountPaid + "Date: " + date + "";
	}
	
}
