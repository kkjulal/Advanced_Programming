package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import domain.Customer;
import domain.Employee;
import domain.Payment;
import domain.Rental;
import view.CustomerDashboard;
import view.CustomerLoginScreen;

public class Client implements Serializable {
	private ObjectInputStream objIs;
	private static ObjectOutputStream objOs;
	private Socket connectionSocket;
	private String action = "";
	private static final Logger logger = LogManager.getLogger(Client.class); //logger used to track all actions and errors
	
	public Client() {
		this.createConnection();
		this.configureStreams();		
	}
	
	private void createConnection() {
		try {
			connectionSocket = new Socket("127.0.0.1", 8888);
			JOptionPane.showMessageDialog(null, "Client server connection created.", "Connection Status", JOptionPane.INFORMATION_MESSAGE);
			logger.info("Client server connection created.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Client server connection failed: " + e.getMessage(), "Connection Status", JOptionPane.ERROR_MESSAGE);
			logger.error("Client server connection failed: " + e.getMessage());
		}
	}
	
	private void configureStreams() {
		try {
			//Create an input stream to receive data from the server
			objIs = new ObjectInputStream(connectionSocket.getInputStream());
			//Create an output stream to send data to the server
			objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
			//JOptionPane.showMessageDialog(null, "Input and Output streams created.", "Connection Status", JOptionPane.INFORMATION_MESSAGE);
			logger.info("Input and Output streams created.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "An error occured: " + e.getMessage(), "Connection Status", JOptionPane.ERROR_MESSAGE);
			logger.error("An error occured: " + e.getMessage());
		}
	}
	
	public void closeConnection() {
		try {
			objOs.close();
			objIs.close();
			connectionSocket.close();
			JOptionPane.showMessageDialog(null, "Connections closed.", "Connection Status", JOptionPane.INFORMATION_MESSAGE);
			logger.info("Connections closed.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "An error occured while closing connections: " + e.getMessage(), "Connection Status", JOptionPane.ERROR_MESSAGE);
			logger.error("An error occured while closing connections: " + e.getMessage());
		}
	}
	
	public void sendAction(String action) {
		logger.trace("sendAction");
		this.action = action;
		try {
			objOs.writeObject(action);
			JOptionPane.showMessageDialog(null, "Action sent.", "Connection Status", JOptionPane.INFORMATION_MESSAGE);
			logger.info("Action sent.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "An error occured while sending an action: " + e.getMessage(), "Connection Status", JOptionPane.ERROR_MESSAGE);
			logger.error("An error occured while sending an action: " + e.getMessage());
		}
	}
	
	public void sendCustomer(Customer custObj) {
		try {
			objOs.writeObject(custObj);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void sendEmployee(Employee empObj) {
		try {
			objOs.writeObject(empObj);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void sendRental(Rental rentObj) {
		try {
			objOs.writeObject(rentObj);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void sendPayment(Payment payObj) {
		try {
			objOs.writeObject(payObj);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void receiveResponse() {
		logger.trace("receiveResponse");
		try {
			logger.trace("try block");
			if (action.equalsIgnoreCase("Authenticate Customer")) {
				
				Customer customer = new Customer();
				customer = (Customer) objIs.readObject();
				
				if (customer != null) {
					JOptionPane.showMessageDialog(null, "Login successful.", "Login Status", JOptionPane.INFORMATION_MESSAGE);
					//logger.trace("Customer Id: " + customer.getId() + " Name: " + customer.getFirstName() + " " + customer.getLastName() + " Logged in.");
					
					new CustomerDashboard();
				}
				else {
					logger.trace("Customer login attempt failed.");
					JOptionPane.showMessageDialog(null, "Login attempt failed.", "Login Status", JOptionPane.ERROR_MESSAGE);
					new CustomerLoginScreen();
				}
			}

			if (action.equalsIgnoreCase("Add Customer")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Customer record added successfully", "Record Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if (action.equalsIgnoreCase("Add Employee")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Employee record added successfully", "Record Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if (action.equalsIgnoreCase("Add Rental")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Employee record added successfully", "Record Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if (action.equalsIgnoreCase("Add Payment")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Employee record added successfully", "Record Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if (action.equalsIgnoreCase("Show Available Equipments")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Employee record added successfully", "Record Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}

			
		} catch (ClassCastException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
