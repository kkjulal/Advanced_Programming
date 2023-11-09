package domain;

public class User {
	//Declaration and Initialization
	protected String id;
	protected String firstName;
	protected String lastName;
	protected String telephone;
	protected String email;
	protected Crypto password;
	
	//Constructors	
	public User() {
		this.id = "";
		this.firstName = "";
		this.lastName = "";
		this.telephone = "";
		this.email = "";
		this.password = new Crypto("");
	}
	
	public User(String id, String firstName, String lastName, String telephone, String email, Crypto password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.telephone = telephone;
		this.email = email;
		this.password = password;
	}
	
	public User(String id, String firstName, String lastName, String telephone, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.telephone = telephone;
		this.email = email;
	}
	
	public User(String id, Crypto password) {
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
	public Crypto getPassword() {
		return password;
	}
	public void setPassword(Crypto password) {
		this.password = password;
	}
	
	//Display
	@Override
	public String toString() {
		return "User ID: " + id + "\nFirst Name: " + firstName + "\nLast Name: " + lastName + "\nTelephone: " + telephone + "\nEmail" + email + "";
	}
	
}
