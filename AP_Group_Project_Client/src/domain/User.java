package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Entity
@Table(name="User")
public abstract class User implements Serializable {
	//Declaration and Initialization
	@Id
	@Column(name="id")
	private String id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="password")
	private String password;
	
	private static final Logger logger = LogManager.getLogger(Customer.class);
	
	//Constructors
	public User() {
		this.id = "";
		this.firstName = "";
		this.lastName = "";
		this.password = "";
	}
	
	public User(String id, String firstName, String lastName, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}
	
	public User(String id, String password) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//Display
	@Override
	public String toString() {
		return "User ID: " + id + "\nFirst Name: " + firstName + "\nLast Name: " + lastName + "\nPassword: " + password + "";
	}
	
	//Abstract Methods
	public abstract User login();
}
