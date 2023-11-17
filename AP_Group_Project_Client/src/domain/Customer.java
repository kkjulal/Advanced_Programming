package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import client.SessionFactoryBuilder;
import view.EmployeeLoginScreen;


@SuppressWarnings("serial")
@Entity
@Table(name="Customer")
public class Customer implements Serializable {
	//Declaration and Initialization
	@Id
	@Column(name="customer_id")
	private String id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="telephone")
	private String telephone;
	@Column(name="balance")
	private double balance;
	@Column(name="password")
	private String password;
	
	private static final Logger logger = LogManager.getLogger(Customer.class);
	
	//Constructors
	public Customer() {
		this.id = "";
		this.firstName = "";
		this.lastName = "";
		this.telephone = "";
		this.balance = 0;
		this.password = "";
	}
	
	public Customer(String id, String firstName, String lastName, String telephone, double balance, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.telephone = telephone;
		this.balance = balance;
		this.password = password;
	}
	
	public Customer(String id, String firstName, String lastName, String telephone, double balance) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.telephone = telephone;
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
		return "Customer ID: " + id + "\nFirst Name: " + firstName + "\nLast Name: " + lastName + "\nTelephone: " + telephone + "\nBalance: " + balance + "\nPassword: " + password + "";
	}
	
	//Using Hibernate to find Customer in GEERS database
	public Customer loginSearch() {
		Customer cusObj = new Customer();
		
		Transaction transaction = null;
		try {
			Session session = SessionFactoryBuilder.getSessionFactory(1).getCurrentSession();
			transaction = (Transaction) session.beginTransaction();			
			cusObj = (Customer) session.get(Customer.class, this.id);			
			transaction.commit();
			session.close();			
		} catch (HibernateException e) {
			transaction.rollback();
			JOptionPane.showMessageDialog(null, "An error occured: " + e.getMessage(), "Customer Class", JOptionPane.ERROR_MESSAGE);
			logger.error("An error occured: " + e.getMessage());
		} catch (Exception e) {
			transaction.rollback();
			JOptionPane.showMessageDialog(null, "An error occured: " + e.getMessage(), "Customer Class", JOptionPane.ERROR_MESSAGE);
			logger.error("An error occured: " + e.getMessage());
		}
		return cusObj;
	}
	

}
