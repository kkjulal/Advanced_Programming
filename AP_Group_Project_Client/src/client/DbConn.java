package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DbConn {
	
	private static Connection dbConn = null;
	
	public static Connection getConnection() {
		
		String url = "jdbc:mysql://localhost:3306/geersdb";
		
		if (dbConn == null) {
			try {
				dbConn = DriverManager.getConnection(url, "root", ""); //
				//JOptionPane.showMessageDialog(null, "Sucessfully connected to Database.", "Connection Status", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
				System.err.println("There was an error while making a connection: " + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Already Connected to database.", "Connection Status", JOptionPane.INFORMATION_MESSAGE);
		}
		
		return dbConn;
	}
	
	public static void closeConnetion() {
		
		if (dbConn != null) {
			try {
				dbConn.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "There was an error while trying to close the connection.", "Connection Status", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

}
