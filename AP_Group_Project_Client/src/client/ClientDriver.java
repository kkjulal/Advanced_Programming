package client;

import domain.Customer;
import domain.Employee;
import domain.Equipment;
import view.CustomerLoginScreen;
import view.WelcomeScreen;

public class ClientDriver extends Thread {
	
	@Override
	public void run() {
		new WelcomeScreen();
	}

	public static void main(String[] args) {
		ClientDriver client = new ClientDriver(); //Multi-threading		
		client.start();
		
//		Client client = new Client();
//		Customer cust = new Customer("1000", "Kimarley", "Julal", "8765840091", "kj@geer.com", "Bath, St. Thomas", "kj123");
//		Employee emp = new Employee("EMP101", "John", "Mark", "101", "jmark@geer.com", "jm321");
//		
//		client.sendAction("Add Customer");
//		client.sendCustomer(cust);
//		client.receiveResponse();		
//		client.closeConnection();
		
		
		
	}

}