package domain;

import java.io.Serializable;

import javax.swing.JOptionPane;

public class Customer implements Serializable {
	//Declaration and Initialization
	private String id;
	private String firstName;
	private String lastName;
	private String telephone;
	private String email;
	private String address;
	private double balance;
	private String password;
	
	//Constructors
	public Customer() {
		this.id = "";
		this.firstName = "";
		this.lastName = "";
		this.telephone = "";
		this.email = "";
		this.balance = 0;
		this.address = "";
	}
	
	public Customer(String id, String firstName, String lastName, String telephone, String email, String address, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.telephone = telephone;
		this.email = email;
		this.address = address;
		this.password = password;
	}
	
	public Customer(String id, String firstName, String lastName, String telephone, String email, String address, double balance) {
		this.id = id;
		this.firstName = firstName;
		this.telephone = telephone;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.balance = balance;
	}
	
	public Customer(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	//Getters and Setters	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
