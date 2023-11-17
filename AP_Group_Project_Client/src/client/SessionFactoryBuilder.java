package client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import domain.Customer;
import domain.Employee;

public class SessionFactoryBuilder {
	private static SessionFactory sessionFactory = null;
	
	private static final Logger logger = LogManager.getLogger(SessionFactoryBuilder.class);
	
	public static SessionFactory getSessionFactory(int opt) {
		
		try {
			if (opt == 1) {
				if(sessionFactory == null) {
					sessionFactory = new org.hibernate.cfg.Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class).buildSessionFactory();
				}
			}
			else if (opt == 2) {
				if(sessionFactory == null) {
					sessionFactory = new org.hibernate.cfg.Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
				}
			}

		} catch (HibernateException e) {
			logger.error("An error occured: " + e.getMessage());
		}		
		return sessionFactory;		
	}
	
	public static void closeSessionFactory() {
		
		if(sessionFactory != null) {
			sessionFactory.close();
			logger.info("Connection closed.");
		}		
		
	}
}