package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import client.SessionFactoryBuilder;

@Entity
@Table(name="Employee")
public class Employee implements Serializable {
	//Declaration and Initialization
	@Id
	@Column(name="employee_id")
	private String id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;	
	
	//Constructors
	public Employee() {
		this.id = "";
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.password = "";		
	}
	
	public Employee(String id, String firstName, String lastName, String email, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
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
		return "Employee ID: " + id + "\nFirst Name: " + firstName + "\nLast Name: " + lastName + "\nEmail: " + email + "";
	}
	
	//Using Hibernate to find Customer in GEERS database
	public Employee loginSearch() {
		Employee empObj = new Employee();
		
		Transaction transaction = null;
		try {
			Session session = SessionFactoryBuilder.getSessionFactory(2).getCurrentSession();
			transaction = (Transaction) session.beginTransaction();			
			empObj = (Employee) session.get(Employee.class, this.id);			
			transaction.commit();
			session.close();			
			
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return empObj;
	}
	
}
