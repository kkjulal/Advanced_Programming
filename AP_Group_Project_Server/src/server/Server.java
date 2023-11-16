package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

import domain.Customer;

public class Server {
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private static Connection dbConn = null;
	private Statement stmt;
	private ResultSet result = null;
	
	public Server() {
		this.createConnection();
		this.waitForRequests();		
	}
	
	private void createConnection() {
		try {
			serverSocket = new ServerSocket(8888); //create new instance of the serverSocket listening on port 8888
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void configureStreams() {
		try {
			objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
			
			objIs = new ObjectInputStream(connectionSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Connection getDatabaseConnection() {
		if (dbConn == null) {
			try {
				String url = "jdbc:mysql://localhost:3306/geersdb";
				dbConn = DriverManager.getConnection(url, "root", "");
				
				JOptionPane.showMessageDialog(null, "DB Connection Established", "Connection Status", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Could not connect to database\n" + ex, "Connection Status", JOptionPane.ERROR_MESSAGE);
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
			e.printStackTrace();
		}
	}
	
	private void addCustomer(Customer customer) {
		String sql = "INSERT INTO dblab.students (id, firstName, lastName, email) VALUES ('" + customer.getId() + "', '" + customer.getFirstName() + "', '" + customer.getLastName() + "', '" + customer.getTelephone() + "', '" + customer.getBalance() + "', '" + customer.getPassword() +"');";
		try {
			stmt = dbConn.createStatement();
			
			if (stmt.executeUpdate(sql) == 1) {
				objOs.writeObject(true);
			} else {
				objOs.writeObject(false);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Customer findCustomer(Customer customer) {
		Customer cusObj = new Customer();
		String query = "SELECT * FROM geersdb.customer WHERE password = '" + customer.getPassword() + "';";
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(query);
			while (result.next()) {
				cusObj.setId(result.getString(1));
				cusObj.setFirstName(result.getString(2));
				cusObj.setLastName(result.getString(3));
				cusObj.setTelephone(result.getString(4));
				cusObj.setBalance(result.getDouble(5));
				cusObj.setPassword(result.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cusObj;		
	}
	
	private void waitForRequests() {
		String action = "";
		getDatabaseConnection();
		Customer cusObj = null;
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
					else if (action.equals("Find Customer")) {
						Customer customer = (Customer) objIs.readObject();
						
						cusObj = findCustomer(customer);
						objOs.writeObject(cusObj);
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (ClassCastException e) {
					e.printStackTrace();
				}
				this.closeConnection();
			}
		} catch (EOFException e) {
			System.out.println("Client has terminated connection with the server");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Server();
	}
}
