package server;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import domain.Customer;
import domain.Employee;
import domain.Equipment;
import domain.Payment;
import domain.Rental;

public class SessionFactoryBuilder {
	private static SessionFactory sessionFactory = null;
	
	public static SessionFactory getSessionFactory(int opt) {
		
		try {
			if (opt == 1) {
				if(sessionFactory == null) {
					sessionFactory = new org.hibernate.cfg.Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class).buildSessionFactory();
				}
			}
			if (opt == 2) {
				if(sessionFactory == null) {
					sessionFactory = new org.hibernate.cfg.Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
				}
			}
			if (opt == 3) {
				if(sessionFactory == null) {
					sessionFactory = new org.hibernate.cfg.Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Equipment.class).buildSessionFactory();
				}
			}
			if (opt == 4) {
				if(sessionFactory == null) {
					sessionFactory = new org.hibernate.cfg.Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Rental.class).buildSessionFactory();
				}
			}
			if (opt == 5) {
				if(sessionFactory == null) {
					sessionFactory = new org.hibernate.cfg.Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Payment.class).buildSessionFactory();
				}
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		}		
		return sessionFactory;		
	}
	
	public static void closeSessionFactory() {
		
		if(sessionFactory != null) {
			sessionFactory.close();
		}		
		
	}
}
