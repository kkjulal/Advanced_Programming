package domain;

import java.io.Serializable;

import javax.swing.JOptionPane;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import server.SessionFactoryBuilder;

@Entity
@Table(name="Customer")
public class Customer implements Serializable {
	//Declaration and Initialization
	@Id
	private String id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="telephone")
	private String telephone;
	@Column(name="email")
	private String email;
	@Column(name="address")
	private String address;
	@Column(name="balance")
	private double balance;
	@Column(name="password")
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

	//Display
	@Override
	public String toString() {
		return "Customer ID: " + "\nFirst Name: " + firstName + "\nLast Name: " + lastName + "\nTelephone: " + telephone + "\nEmail: " + email + "\nAddress: " + address + "\nBalance: " + balance + "";
	}
	
	public void login() {
		Session session;
		Transaction transaction = null;
		try {
			session = SessionFactoryBuilder.getSessionFactory(1).getCurrentSession();
			transaction = (Transaction) session.beginTransaction();
			Customer cus = (Customer) session.get(Customer.class, this.id);
			session.delete(cus);
			JOptionPane.showMessageDialog(null, "Customer " + cus.getId() + " record was deleted.", "Student Query Status", JOptionPane.INFORMATION_MESSAGE);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}		
	}

}
