package domain;

import java.io.Serializable;

public class Employee implements Serializable {
	//Declaration and Initialization
	private String id;
	private String firstName;
	private String lastName;
	private String extension;
	private String email;
	private String password;	
	
	//Constructors
	public Employee() {
		this.id = "";
		this.firstName = "";
		this.lastName = "";
		this.extension = "";
		this.email = "";
		this.password = "";		
	}
	
	public Employee(String id, String firstName, String lastName, String extension, String email, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.extension = extension;
		this.email = email;
		this.password = password;
	}
	
	public Employee(String id, String password) {
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

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//Display Method
	@Override
	public String toString() {
		return "Employee ID: " + id + "\nFirst Name: " + firstName + "\nLast Name: " + lastName + "\nExtension: " + extension + "\nEmail: " + email + "";
	}
	
}
