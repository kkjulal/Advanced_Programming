package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import server.SessionFactoryBuilder;

@SuppressWarnings("serial")
@Entity
@Table(name="Equipment")
public class Equipment implements Serializable {
	//Declaration and Initialization
	@Id
	private String equipmentId;
	@Column(name="name")
	private String name;
	@Column(name="category")
	private String category;
	@Column(name="price")
	private double price;
	@Column(name="status")
	private String status;
	private static final Logger logger = LogManager.getLogger(Equipment.class); //logger used to track all actions and errors
	
	//Constructors
	public Equipment() {
		this.equipmentId = "";
		this.name = "";
		this.category = "";
		this.price = 0;
		this.status = "available";
	}
	
	public Equipment(String equipmentId, String name, String category, double price, String status) {
		this.equipmentId = equipmentId;
		this.name = name;
		this.category = category;
		this.price = price;
		this.status = status;
	}
	
	public Equipment(String equipmentId, String name, String category, String status) {
		this.equipmentId = equipmentId;
		this.name = name;
		this.category = category;
		this.status = status;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Equipment(String category, double price) {
		this.category = category;
		this.price = price;
	}
	
	//Getters and Setters
	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getType() {
		return category;
	}

	public void setType(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	//Display
	@Override
	public String toString() {
		return "Equipment ID: " + equipmentId + "\nName: " + name + "\nCategory: " + category + "\nPrice: " + price + "\nStatus: " + status + "";
	}
	
	//The addEquipment method uses Hibernate to communicate with the database via the server
	public void addEquipment() {
		Transaction transaction = null;
		try {
			Session session = SessionFactoryBuilder.getSessionFactory(3).getCurrentSession();
			transaction = (Transaction) session.beginTransaction();
			session.save(this);
			logger.error("Equipment " + this.equipmentId + " record was created.");
			JOptionPane.showMessageDialog(null, "Equipment " + this.equipmentId + " record was created.", "Equipment Query Status", JOptionPane.INFORMATION_MESSAGE);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			transaction.rollback();
			logger.error("There was an error while create the record: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "There was an error while create the record: " + e, "Equipment Query Status", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			transaction.rollback();
			logger.error("There was an error while create the record: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "There was an error while create the record." + e, "Equipment Query Status", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Equipment> readAll(){
		List<Equipment> equipmentList = new ArrayList<>();
		
		Transaction transaction = null;
		try {
			Session session = SessionFactoryBuilder.getSessionFactory(3).getCurrentSession();
			transaction = session.beginTransaction();
			equipmentList = (List<Equipment>) session.createQuery("FROM Equipment").getResultList();
			JOptionPane.showMessageDialog(null, equipmentList.size() + " Customer records found in database.\nSee console for results.", "Customer Query Status", JOptionPane.INFORMATION_MESSAGE);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		
		return equipmentList;
	}
	
	public void delete() {
		Session session;
		Transaction transaction = null;
		try {
			session = SessionFactoryBuilder.getSessionFactory(3).getCurrentSession();
			transaction = (Transaction) session.beginTransaction();
			Equipment equipment = (Equipment) session.get(Equipment.class, this.equipmentId);
			session.delete(equipment);
			JOptionPane.showMessageDialog(null, "Student " + this.equipmentId + " record was deleted.", "Student Query Status", JOptionPane.INFORMATION_MESSAGE);
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
	
	public static void main(String[] args) {
		
		Equipment equi = new Equipment("LGT02", "Lghting Package", "light", "booked");
		
		equi.addEquipment();
		
		SessionFactoryBuilder.closeSessionFactory();
	}
}
