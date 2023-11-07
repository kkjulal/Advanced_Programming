package domain;

public class Employee extends User {
	//Declaration and Initialization
	private String extension;	
	
	//Constructors
	public Employee(String id, String name, String extension, String email, String password) {
		super(id, name, email, password);
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
		return "Employee ID: " + id + "\nName: " + name + "\nExtension: " + extension + "\nEmail: " + email + "";
	}
	
}
