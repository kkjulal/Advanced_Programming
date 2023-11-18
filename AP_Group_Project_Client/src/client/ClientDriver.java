package client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import domain.Customer;
import view.WelcomeScreen;

public class ClientDriver {
	
	private static final Logger logger = LogManager.getLogger(ClientDriver.class);

	public static void main(String[] args) {
		
		logger.trace("Client application started");
		
//		WelcomeScreen ws = new WelcomeScreen();
//		
//		ws.start();
		
		new WelcomeScreen();
		
//		Client client = new Client();
//		Customer cusObj = new Customer("1002", "Jaiden", "Julal", "8761234567", 100000, "jj123");
//		
//		client.sendAction("Add Customer");
//		client.sendCustomer(cusObj);		
//		client.receiveResponse();
//		client.closeConnection();
	}

}