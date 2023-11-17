package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

import domain.Customer;
import domain.Employee;
import domain.Equipment;
import domain.Payment;
import domain.Rental;

public class Client {
	private ObjectInputStream objIs;
	private static ObjectOutputStream objOs;
	private Socket connectionSocket;
	private String action = "";
	
	public Client() {
		this.createConnection();
		this.configureStreams();		
	}
	
	private void createConnection() {
		try {
			connectionSocket = new Socket("127.0.0.1", 8888);
			JOptionPane.showMessageDialog(null, "Client server connection created.", "Connection Status", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void configureStreams() {
		try {
			//Create an input stream to receive data from the server
			objIs = new ObjectInputStream(connectionSocket.getInputStream());
			//Create an output stream to send data to the server
			objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			objOs.close();
			objIs.close();
			connectionSocket.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void sendAction(String action) {
		this.action = action;
		try {
			objOs.writeObject(action);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void sendCustomer(Customer cusObj) {
		try {
			objOs.writeObject(cusObj);
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
	
	public void sendEquipment(Equipment equipObj) {
		try {
			objOs.writeObject(equipObj);
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
		try {
			if (action.equalsIgnoreCase("Add Customer")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Record added successfully", "Customer Record Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if (action.equalsIgnoreCase("Add Employee")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Record added successfully", "Customer Record Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if (action.equalsIgnoreCase("Add Equipment")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Record added successfully", "Customer Record Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if (action.equalsIgnoreCase("Add Rental")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Record added successfully", "Customer Record Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if (action.equalsIgnoreCase("Add Payment")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "Record added successfully", "Customer Record Status", JOptionPane.INFORMATION_MESSAGE);
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
	
	public static void main(String[] args) {
		
		Client client = new Client();
		Customer cusObj = new Customer("1002", "Jaiden", "Julal", "8761234567", 100000, "jj123");
		
		client.sendAction("Add Customer");
		client.sendCustomer(cusObj);		
		client.receiveResponse();
		client.closeConnection();

	}

}
