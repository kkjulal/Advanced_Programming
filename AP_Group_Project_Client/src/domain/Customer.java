package domain;

public class Customer extends User{
	//Declaration and Initialization
	private String address;
	private double balance;
	
	//Constructors
	public Customer(String id, String firstName, String lastName, String telephone, String email, Crypto password, String address, double balance) {
		super(id, firstName, lastName, telephone, email, password);
		this.address = address;
		this.balance = balance;
	}
	
	public Customer(String id, String firstName, String lastName, String telephone, String email, String address, double balance) {
		super(id, firstName, lastName, telephone, email);
		this.address = address;
		this.balance = balance;
	}
	
	//Getters and Setters	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	//Display
	@Override
	public String toString() {
		return "Customer ID: " + "\nFirst Name: " + firstName + "\nLast Name: " + lastName + "\nAddress: " + address	+ "\nTelephone: " + telephone + "\nEmail: " + email + "\nBalance: " + balance + "";
	}

}
