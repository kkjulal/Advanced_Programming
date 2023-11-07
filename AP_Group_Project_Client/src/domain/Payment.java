package domain;

public class Payment {
	//Declaration and Initialization
	private String paymentId;
	private double amount;
	private double amountPaid;
	private Date date;

	//Constructors
	public Payment() {
		this.paymentId = "";
		this.amount = 0;
		this.amountPaid = 0;
		this.date = new Date(1, 1, 1979);
	}
	
	public Payment(String paymentId, double amount, double amountPaid, Date date) {
		this.paymentId = paymentId;
		this.amount = amount;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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
	
	//Display
	@Override
	public String toString() {
		return "Payment ID: " + paymentId + "\nAmount: " + amount + "\nAmount Paid: " + amountPaid + "\nDate: "
				+ date + "";
	}
	
}
