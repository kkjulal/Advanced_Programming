package client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import domain.Customer;

public class Actions {
	
	private static Statement stmt;
	private static Connection conn;
	private static ResultSet result;
	
	public Actions() {
		conn = DbConn.getConnection();
	}
	
	public int authenticate(Customer cusObj) {
		String searchSql = "SELECT customer_id, password FROM geersdb.customer WHERE customer_id = '" + cusObj.getId() + "';";
		
		
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(searchSql);
			
			
			while(result.next()) {
				String id = result.getString("customer_id");
				String password = result.getString("password");
				System.out.println(password);
				
				if (cusObj.getPassword().equals(id) && cusObj.getPassword().equals(password)) { //if the record is found user will login successfully.
					JOptionPane.showMessageDialog(null, "Login Successful.", "Login Status", JOptionPane.INFORMATION_MESSAGE);					
					
					DbConn.closeConnetion();
					return 1;
				}
				else
					JOptionPane.showMessageDialog(null, "Login failed.", "Login Status", JOptionPane.INFORMATION_MESSAGE);					

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DbConn.closeConnetion();
		return 0;		
	}
	
	public void searchCustomer(String key) {
		
		String searchSql = "SELECT customer_id, password FROM geersdb.customer WHERE customer_id = '" + key + "';";
		
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(searchSql);
			
			
			while(result.next()) {
				String id = result.getString("customer_id");
				String psw = result.getString("password");
				
				if (key.equals(id)) { //if the record is found an object will be passed to the updateForm function
					JOptionPane.showMessageDialog(null, "Record Found.", "Record Update Status", JOptionPane.INFORMATION_MESSAGE);
					
					DbConn.closeConnetion();
					return;
				}
			}
			System.out.println("Record not found.");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DbConn.closeConnetion();
		return;		
	}
	
}