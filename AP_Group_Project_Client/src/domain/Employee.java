package domain;

public class Employee extends User {
	//Declaration and Initialization
	private String extension;	
	
	//Constructors
	public Employee(String id, String firstName, String lastName, String extension, String email, String password) {
		super(id, firstName, lastName, email, password);
		this.extension = extension;
	}
	
	//Getters and Setters	
	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	//Display Method
	@Override
	public String toString() {
		return "Employee ID: " + id + "\nFirst Name: " + firstName + "\nLast Name: " + lastName + "\nExtension: " + extension + "\nEmail: " + email + "";
	}
	
}
