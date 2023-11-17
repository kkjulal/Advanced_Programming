package client;

import domain.Customer;

public class ClientDriver {

	public static void main(String[] args) {
		Client client = new Client();
		Customer cusObj = new Customer("1002", "Jaiden", "Julal", "8761234567", 100000, "jj123");
		
		client.sendAction("Add Customer");
		client.sendCustomer(cusObj);		
		client.receiveResponse();
		client.closeConnection();
	}

}