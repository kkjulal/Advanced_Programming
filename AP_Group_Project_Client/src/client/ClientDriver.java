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
		
//		System.out.println("Thread Name: " + Thread.currentThread().getName());
//		System.out.println("Thread ID: "+ Thread.currentThread().getId());
//		System.out.println("Priority Level: "+ Thread.currentThread().getPriority());
//		System.out.println("Thread State: " + Thread.currentThread().getState());
	}

	public static void main(String[] args) {		
		
		ClientDriver clientThread0 = new ClientDriver();
		ClientDriver clientThread1 = new ClientDriver();
		ClientDriver clientThread2 = new ClientDriver();
		
		clientThread0.start();
		clientThread1.start();
		clientThread2.start();
		
		//new WelcomeScreen();
		
//		Client client = new Client();
//		Customer cust = new Customer("1000", "Kimarley", "Julal", "8765840091", "kj@geer.com", "Bath, St. Thomas", "kj123");
//		Employee emp = new Employee("EMP101", "John", "Mark", "101", "jmark@geer.com", "jm321");
//		
//		client.sendAction("Add Customer");
//		client.sendCustomer(cust);
//		client.receiveResponse();		
//		client.closeConnection();
//		
//		client.sendAction("Add Employee");
//		client.sendEmployee(emp);
//		client.receiveResponse();		
//		client.closeConnection();
		
		
		
	}

}