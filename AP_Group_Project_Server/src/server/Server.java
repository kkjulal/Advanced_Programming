package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import domain.Customer;
import domain.Employee;
import domain.Equipment;
import domain.Payment;
import domain.Rental;

public class Server {
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private static Connection dbConn = null;
	private Statement stmt;
	
	private static final Logger logger = LogManager.getLogger(Server.class);
	
	public Server() {
		logger.trace("Entered Server class.");
		this.createConnection();
		this.waitForRequests();		
	}
	
	private void createConnection() {
		try {
			serverSocket = new ServerSocket(8888); //create new instance of the serverSocket listening on port 8888
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "An error occured: " + e.getMessage(), "Connection Status", JOptionPane.ERROR);
			logger.error("An error occured: " + e.getMessage());
		}
	}
	
	private void configureStreams() {
		try {
			objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
			
			objIs = new ObjectInputStream(connectionSocket.getInputStream());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "An error occured: " + e.getMessage(), "Connection Status", JOptionPane.ERROR);
			logger.error("An error occured: " + e.getMessage());
		}
	}
	
	private static Connection getDatabaseConnection() {
		if (dbConn == null) {
			try {
				String url = "jdbc:mysql://localhost:3306/geersdb";
				dbConn = DriverManager.getConnection(url, "root", "");
				
				JOptionPane.showMessageDialog(null, "Database Connection Established.", "Connection Status", JOptionPane.INFORMATION_MESSAGE);
				logger.info("Database Connection Established.");
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Could not connect to database: " + ex, "Connection Status", JOptionPane.ERROR_MESSAGE);
				logger.error("Could not connect to database: " + ex);
			}
		}
		return dbConn;		
	}
	
	private void closeConnection() {
		try {
			objOs.close();
			objIs.close();
			connectionSocket.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Could not connect to database: " + e, "Connection Status", JOptionPane.ERROR_MESSAGE);
			logger.error("Could not connect to database: " + e);
		}
	}
	
	private void addCustomer(Customer customer) {
		String sql = "INSERT INTO geersdb.customer (customer_id, first_name, last_name, telephone, balance, password) VALUES ('" + customer.getId() + "', '" + customer.getFirstName() + "', '" + customer.getLastName() + "', '" + customer.getTelephone() + "', '" + customer.getBalance() + "', '" + customer.getPassword() +"');";
		try {
			stmt = dbConn.createStatement();
			
			if (stmt.executeUpdate(sql) == 1) {
				objOs.writeObject(true);
			} else {
				objOs.writeObject(false);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "There was an error while creating customer record: " + e, "Record Creation Status", JOptionPane.ERROR_MESSAGE);
			logger.error("There was an error while creating customer record: " + e);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "There was an error while creating customer record: " + e, "Record Creation Status", JOptionPane.ERROR_MESSAGE);
			logger.error("There was an error while creating customer record: " + e);
		}
	}
	
	private void addEmployee(Employee employee) {
		String sql = "INSERT INTO geersdb.customer (employee_id, first_name, last_name, email) VALUES ('" + employee.getId() + "', '" + employee.getFirstName() + "', '" + employee.getLastName() + "', '" + employee.getEmail() + "');";
		try {
			stmt = dbConn.createStatement();
			
			if (stmt.executeUpdate(sql) == 1) {
				objOs.writeObject(true);
			} else {
				objOs.writeObject(false);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "There was an error while creating employee record: " + e, "Record Creation Status", JOptionPane.ERROR_MESSAGE);
			logger.error("There was an error while creating customer record: " + e);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "There was an error while creating employee record: " + e, "Record Creation Status", JOptionPane.ERROR_MESSAGE);
			logger.error("There was an error while creating customer record: " + e);
		}
	}
	
	private void addEquipment(Equipment equipment) {
		String sql = "INSERT INTO geersdb.equipment (equipment_id, name, category, status) VALUES ('" + equipment.getEquipmentId() + "', '" + equipment.getName() + "', '" + equipment.getCategory() + "', '" + equipment.getStatus() + "');";
		try {
			stmt = dbConn.createStatement();
			
			if (stmt.executeUpdate(sql) == 1) {
				objOs.writeObject(true);
			} else {
				objOs.writeObject(false);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "There was an error while creating equipment record: " + e, "Record Creation Status", JOptionPane.ERROR_MESSAGE);
			logger.error("There was an error while creating customer record: " + e);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "There was an error while creating equipment record: " + e, "Record Creation Status", JOptionPane.ERROR_MESSAGE);
			logger.error("There was an error while creating customer record: " + e);
		}
	}
	
	private void addRental(Rental rental) {
		String sql = "INSERT INTO geersdb.rental (customer, date, start_date, duration, cost, employee) VALUES ('" + rental.getCustomer() + "', '" + rental.getDate() + "', '" + rental.getStartDate() + "', '" + rental.getDuration() + rental.getCost() + rental.getEmployee() + "');";
		try {
			stmt = dbConn.createStatement();
			
			if (stmt.executeUpdate(sql) == 1) {
				objOs.writeObject(true);
			} else {
				objOs.writeObject(false);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "There was an error while creating rental record: " + e, "Record Creation Status", JOptionPane.ERROR_MESSAGE);
			logger.error("There was an error while creating customer record: " + e);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "There was an error while creating rental record: " + e, "Record Creation Status", JOptionPane.ERROR_MESSAGE);
			logger.error("There was an error while creating customer record: " + e);
		}
	}
	
	private void addPayment(Payment payment) {
		String sql = "INSERT INTO geersdb.payment (payment_id, date, customer, amount) VALUES ('" + payment.getPaymentId() + "', '" + payment.getDate() + "', '" + payment.getCustomer() + "', '" + payment.getAmount() + "');";
		try {
			stmt = dbConn.createStatement();
			
			if (stmt.executeUpdate(sql) == 1) {
				objOs.writeObject(true);
			} else {
				objOs.writeObject(false);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "There was an error while creating payment record: " + e, "Record Creation Status", JOptionPane.ERROR_MESSAGE);
			logger.error("There was an error while creating customer record: " + e);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "There was an error while creating payment record: " + e, "Record Creation Status", JOptionPane.ERROR_MESSAGE);
			logger.error("There was an error while creating customer record: " + e);
		}
	}
	
	private void waitForRequests() {
		String action = "";
		getDatabaseConnection();
		Customer cusObj = null;
		Employee empObj = null;
		Equipment equipObj = null;
		Rental rentObj = null;
		Payment payObj = null;
		try {
			while (true) {
				connectionSocket = serverSocket.accept();
				this.configureStreams();
				try {
					action = (String) objIs.readObject();
					
					if (action.equals("Add Customer")) {
						cusObj = (Customer) objIs.readObject();
						
						addCustomer(cusObj);
						objOs.writeObject(true);
					}
					else if (action.equals("Add Employee")) {
						empObj = (Employee) objIs.readObject();
						
						addEmployee(empObj);
						objOs.writeObject(true);
					}
					else if (action.equals("Add Equipment")) {
						equipObj = (Equipment) objIs.readObject();
						
						addEquipment(equipObj);
						objOs.writeObject(true);
					}
					else if (action.equals("Add Rental")) {
						rentObj = (Rental) objIs.readObject();
						
						addRental(rentObj);
						objOs.writeObject(true);
					}
					else if (action.equals("Add Payment")) {
						payObj = (Payment) objIs.readObject();
						
						addPayment(payObj);
						objOs.writeObject(true);
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (ClassCastException e) {
					e.printStackTrace();
				}
				this.closeConnection();
			}
		} catch (EOFException e) {
			JOptionPane.showMessageDialog(null, "There was an error: " + e, "Record Creation Status", JOptionPane.ERROR_MESSAGE);
			logger.error("There was an error: " + e);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "There was an error: " + e, "Record Creation Status", JOptionPane.ERROR_MESSAGE);
			logger.error("There was an error: " + e);
		}
	}
	
	public static void main(String[] args) {
		new Server();
	}
}
