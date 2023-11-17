package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DbConn {
	
	private static Connection dbConn = null;
	
	private static final Logger logger = LogManager.getLogger(DbConn.class);
	
	public static Connection getConnection() {
		
		String url = "jdbc:mysql://localhost:3306/geersdb";
		
		if (dbConn == null) {
			try {
				dbConn = DriverManager.getConnection(url, "root", "");
				JOptionPane.showMessageDialog(null, "Successfully connected to Database.", "Connection Status", JOptionPane.INFORMATION_MESSAGE);
				logger.info("Successfully connected to Database.");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "There was an error while making a connection: " + e.getMessage(), "Connection Status", JOptionPane.ERROR);
				logger.error("There was an error while making a connection: " + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Already Connected to database.", "Connection Status", JOptionPane.INFORMATION_MESSAGE);
			logger.info("Already Connected to database.");
		}
		
		return dbConn;
	}
	
	public static void closeConnetion() {
		
		if (dbConn != null) {
			try {
				dbConn.close();
				logger.error("Connection closed.");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "There was an error while trying to close the connection: " + e.getMessage(), "Connection Status", JOptionPane.ERROR);
				logger.error("There was an error while trying to close the connection: " + e.getMessage());
			}
		}
	}

}
