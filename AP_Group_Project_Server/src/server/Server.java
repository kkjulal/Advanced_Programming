package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.cj.xdevapi.SessionFactory;

import domain.Customer;
import domain.Employee;
import domain.Equipment;
import domain.Payment;
import domain.Rental;

public class Server {
	//Declaration
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private static Connection dbConn = null;
	private Statement stmt;
	private ResultSet result = null;
	private static final Logger logger = LogManager.getLogger(Server.class); //logger used to track all actions and errors
	private static SessionFactory sessionFactory = null; //Hibernate
	
	//Constructors
	public Server() {
		this.createConnection();
		this.waitForRequests();
	}
	
	//Methods
	//Create server connection
	private void createConnection() {
		try {
			serverSocket = new ServerSocket(8888); //create new instance of the serverSocket listening on port 8888
			JOptionPane.showMessageDialog(null, "Server Connection Established.", "Connection Status", JOptionPane.INFORMATION_MESSAGE);
			logger.info("Server Connection Established.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Could not connect to server." + e, "Connection Status", JOptionPane.ERROR_MESSAGE);
			logger.error("There was an error while creating a connection to the server:  " + e.getMessage());
		}
	}
	
	//Setup Input and output streams
	private void configureStreams() {
		try {
			objOs = new ObjectOutputStream(connectionSocket.getOutputStream());			
			objIs = new ObjectInputStream(connectionSocket.getInputStream());
			
			//JOptionPane.showMessageDialog(null, "Streams initialised successfully.", "Connection Status", JOptionPane.INFORMATION_MESSAGE);
			logger.info("Streams initialised successfully.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "There was an error while setting up the data streams:  " + e, "Connection Status", JOptionPane.ERROR_MESSAGE);
			logger.error("There was an error while setting up the data streams:  " + e.getMessage());
		}
	}
	
	private static Connection getDatabaseConnection() {
		if (dbConn == null) {
			try {
				String url = "jdbc:mysql://localhost:3306/geersdb";
				dbConn = DriverManager.getConnection(url, "root", "");
				
				JOptionPane.showMessageDialog(null, "Database Connection Successful.", "Connection Status", JOptionPane.INFORMATION_MESSAGE);
				logger.info("Database Connection Established.");
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Could not connect to database" + ex, "Connection Status", JOptionPane.ERROR_MESSAGE);
				logger.error("There was an error while connection to the database:  " + ex.getMessage());
			}
		}
		return dbConn;		
	}
	
	//Method to closer server connection
	private void closeConnection() {
		try {
			objOs.close();
			objIs.close();
			connectionSocket.close();
			JOptionPane.showMessageDialog(null, "Connections closed.", "Connection Status", JOptionPane.INFORMATION_MESSAGE);
			logger.info("Connections closed.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "There was an error while closing the connections:" + e, "Connection Status", JOptionPane.ERROR_MESSAGE);
			logger.error("Error while closing the connections:  " + e.getMessage());
		}
	}
	
	//Method to create a Customer record
	private void addCustomer(Customer customer) {
		String sql = "INSERT INTO geersdb.customer (customer_id, first_name, last_name, address, telephone, email, password) "
				+ "VALUES ('" + customer.getId() + "', '" + customer.getFirstName() + "', '" + customer.getLastName() + "', '" + customer.getAddress() + "', '" + customer.getTelephone() + "', '" + customer.getEmail() + "', '" + customer.getPassword() + "');";
		try {
			stmt = dbConn.createStatement();
			
			if (stmt.executeUpdate(sql) == 1) {
				objOs.writeObject(true);
			} else {
				objOs.writeObject(false);
			}
			JOptionPane.showMessageDialog(null, "New customer added.", "Add Customer Status", JOptionPane.INFORMATION_MESSAGE);
			logger.info("Connections closed.");
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "An error occured while creating the record: " + ex, "Add Customer Status", JOptionPane.ERROR_MESSAGE);
			logger.error("An error occured while creating the record: " + ex.getMessage());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "SQL operation error: " + e, "Add Customer Status", JOptionPane.ERROR_MESSAGE);
			logger.error("SQL operation error: " + e.getMessage());
		}
	}
	
	//Method to create a Employee record
	private void addEmployee(Employee employee) {
		String sql = "INSERT INTO geersdb.employee (employee_id, first_name, last_name, extension, email, password) "
				+ "VALUES ('" + employee.getId() + "', '" + employee.getFirstName() + "', '" + employee.getLastName() + "', '" + employee.getExtension() + "',  '" + employee.getEmail() + "', '" + employee.getPassword() + "');";
		try {
			stmt = dbConn.createStatement();
			
			if (stmt.executeUpdate(sql) == 1) {
				objOs.writeObject(true);
			} else {
				objOs.writeObject(false);
			}
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "An error occured while creating the record: " + ex, "Add Employee Status", JOptionPane.ERROR_MESSAGE);
			logger.error("An error occured while creating the record: " + ex.getMessage());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "SQL operation error: " + e, "Add Employee Status", JOptionPane.ERROR_MESSAGE);
			logger.error("SQL operation error: " + e.getMessage());
		}
	}
	
	//Method to create a new Rental record
	private void createRental(Rental rental) {
		String sql = "INSERT INTO geersdb.rental (customer, equipment, date, duration, cost, employee) "
				+ "VALUES ('" + rental.getCustomer() + "', '" + rental.getEquipment() + "', '" + rental.getDate() + "', '" + rental.getDuration() + "', '" + rental.getCost() + "', '" + rental.getEmployee() + "');";
		try {
			stmt = dbConn.createStatement();
			
			if (stmt.executeUpdate(sql) == 1) {
				objOs.writeObject(true);
			} else {
				objOs.writeObject(false);
			}
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "An error occured while creating the record: " + ex, "Add Rental Status", JOptionPane.ERROR_MESSAGE);
			logger.error("An error occured while creating the record: " + ex.getMessage());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "SQL operation error: " + e, "Add Rental Status", JOptionPane.ERROR_MESSAGE);
			logger.error("SQL operation error: " + e.getMessage());
		}
	}
	
	//Method to create a Payment record
	private void createPayment(Payment payment) {
		String sql = "INSERT INTO geersdb.payment (payment_id, customer, equipment, rental, date, amount_paid) "
				+ "VALUES ('" + payment.getPaymentId() + "', '" + payment.getCustomer() + "', '" + payment.getEquipment() + "', '" + payment.getRental() + "', '" + payment.getDate() + "', '" + payment.getAmountPaid() + "');";
		try {
			stmt = dbConn.createStatement();
			
			if (stmt.executeUpdate(sql) == 1) {
				objOs.writeObject(true);
			} else {
				objOs.writeObject(false);
			}
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "An error occured while creating the record: " + ex, "Add Payment Status", JOptionPane.ERROR_MESSAGE);
			logger.error("An error occured while creating the record: " + ex.getMessage());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "SQL operation error: " + e, "Add Payment Status", JOptionPane.ERROR_MESSAGE);
			logger.error("SQL operation error: " + e.getMessage());
		}
	}
	
	//Method to authenticate Customer
	private Customer authenticateCustomer(Customer find) {
		logger.trace("authenticateCustomer");
		Customer custObj = new Customer();
		
		String query = "SELECT * FROM geersdb.customer WHERE customer_id = '" + find.getId() + "' AND password = '" + find.getPassword() + "'";
		
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(query);
			if (result.next()) {
				custObj.setId(result.getString(1));
//				custObj.setFirstName(result.getString(2));
//				custObj.setLastName(result.getString(3));
//				custObj.setAddress(result.getString(4));
//				custObj.setTelephone(result.getString(5));
//				custObj.setEmail(result.getString(6));
//				custObj.setBalance(result.getDouble(7));
//				custObj.setPassword(result.getString(8));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return custObj;		
	}
	
	public void customerLogin(String id, String pass) {
		
		String sql = "SELECT * FROM geersdb.customer WHERE customer_id = '" + id + "' AND password = '" + pass + "'";
		
		try {
			stmt = dbConn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			
			while(result.next()) {
				String id2 = result.getString("sustomer_id");
				String pass2 = result.getString("password");
				
				System.out.println("ID: " + id2 + "\nName: " + pass2 + "\n");
			}			
			
		} catch (SQLException e) {
			System.err.println("Error viewing query: " + e.getMessage());
			//JOptionPane.showMessageDialog(null, "Error viewing query: " + e.getMessage(), "Record Display Status", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//Method to authenticate Customer
	private Employee authenticateEmployee(Employee find) {
		logger.trace("authenticateEmployee");
		Employee empObj = new Employee();
		
		String query = "SELECT * FROM geersdb.employee WHERE employee_id = '" + find.getId() + "' AND password = '" + find.getPassword() + "'";
		
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(query);
			if (result.next()) {
				empObj.setId(result.getString(1));
//				empObj.setFirstName(result.getString(2));
//				empObj.setLastName(result.getString(3));
//				empObj.setExtension(query);
//				empObj.setEmail(query);
//				empObj.setPassword(result.getString(6));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empObj;		
	}
	
	
	//Method to process Requests
	private void waitForRequests() {
		logger.trace("waitForRequests");
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
					
					if (action.equals("Authenticate Customer")) {
						Customer cusLogin = (Customer) objIs.readObject();						
						cusObj = authenticateCustomer(cusLogin);
						objOs.writeObject(cusObj);
					}
					else if (action.equals("Authenticate Employee")) {
						empObj = (Employee) objIs.readObject();
						addEmployee(empObj);
						objOs.writeObject(true);
					}
					else if (action.equals("Add Customer")) { //Add a new customer record
						cusObj = (Customer) objIs.readObject();
						addCustomer(cusObj);
						objOs.writeObject(true);
					}
					else if (action.equals("Add Employee")) { //Add a new employee record
						empObj = (Employee) objIs.readObject();
						addEmployee(empObj);
						objOs.writeObject(true);
					}
					else if (action.equals("Add Rental")) { //Create a new rental record
						rentObj = (Rental) objIs.readObject();
						createRental(rentObj);
						objOs.writeObject(true);
					}
					else if (action.equals("Add Payment")) { //Create a new payment record
						payObj = (Payment) objIs.readObject();
						createPayment(payObj);
						objOs.writeObject(true);
					}
//					else if (action.equals("Find Student")) {
//						String stuId = (String) objIs.readObject();
//						
//						stuObj = findStudentById(stuId);
//						objOs.writeObject(stuObj);
//					}
				} catch (ClassNotFoundException e) {
					logger.error("An error occured while processing a request: " + e.getMessage());
				} catch (ClassCastException e) {
					logger.error("An error occured while processing a request: " + e.getMessage());
				}
				this.closeConnection();
			}
		} catch (EOFException e) {
			JOptionPane.showMessageDialog(null, "Client has terminated connection with the server.", "Add Payment Status", JOptionPane.INFORMATION_MESSAGE);
			logger.error("An error occured while accepting request: " + e.getMessage());
		} catch (IOException e) {
			logger.error("An error occured while accepting request: " + e.getMessage());
		}
	}

//=========================================================================================================================================================//
	

	


}
