package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

import domain.Customer;

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
	
	public void sendCustomerCredentials(Customer cusObj) {
		try {
			objOs.writeObject(cusObj);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	public void receiveLoginResponse() {
		try {
			if (action.equalsIgnoreCase("Find Customer")) {
				Customer customer = null;
				
				
				customer = (Customer) objIs.readObject();
				System.out.println(customer);
				
				if (customer.getId() != null) {
					JOptionPane.showMessageDialog(null, "Record found", "GEERS Query Status", JOptionPane.INFORMATION_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(null, "Record not found", "GEERS Query Status", JOptionPane.ERROR_MESSAGE);
				
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
		Customer cusObj = new Customer("1001", "kj123");
		
		client.sendAction("Find Customer");
		client.sendCustomerCredentials(cusObj);
		
		client.receiveLoginResponse();
		client.closeConnection();
		
		//If customer is found they will be logged into the GEER system.

	}

}
