package client;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.Customer;
import domain.Employee;

public class Actions {
	
	private static final Logger logger = LogManager.getLogger(Customer.class);
	
	public Actions() {
		
	}
	
	//Using Hibernate to find Customer in GEERS database
	public Customer loginCustomer(String id) {
		Customer cusObj = new Customer();
		
		Transaction transaction = null;
		try {
			Session session = SessionFactoryBuilder.getSessionFactory(1).getCurrentSession();
			transaction = (Transaction) session.beginTransaction();			
			cusObj = (Customer) session.get(Customer.class, id);			
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
	
	//Using Hibernate to find Customer in GEERS database
	public Employee loginEmployee(String id) {
		Employee empObj = new Employee();
		
		Transaction transaction = null;
		try {
			Session session = SessionFactoryBuilder.getSessionFactory(2).getCurrentSession();
			transaction = (Transaction) session.beginTransaction();			
			empObj = (Employee) session.get(Employee.class, id);			
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