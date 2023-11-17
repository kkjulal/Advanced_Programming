package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WelcomeScreen implements ActionListener {
	
	private JFrame frame;
	private JLabel lblWelcome, lblUser;
	private JButton btnCustomer, btnEmployee, btnExit;
	
	private static final Logger logger = LogManager.getLogger(WelcomeScreen.class);
	
	public WelcomeScreen() {
		logger.trace("Entered WelcomeScreen class.");
		
		frame = new JFrame("GEERS - Welcome");
		lblWelcome = new JLabel("Welcome to the Grizzly’s Entertainment Equipment Rental System");
		lblUser = new JLabel("Please select a user to continue:");
		btnCustomer = new JButton("Customer");
		btnEmployee = new JButton("Employee");
		btnExit = new JButton("Exit");
		
		lblWelcome.setBounds(50, 25, 450, 20);
		lblUser.setBounds(150, 75, 200, 20);
		btnCustomer.setBounds(120, 125, 100, 30);
		btnEmployee.setBounds(260, 125, 100, 30);
		btnExit.setBounds(200, 175, 75, 30);
		
		frame.add(lblWelcome);
		frame.add(lblUser);
		frame.add(btnCustomer);
		frame.add(btnEmployee);
		frame.add(btnExit);		
		
		frame.setSize(500, 280);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnCustomer.addActionListener(this);
		btnEmployee.addActionListener(this);
		btnExit.addActionListener(this);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCustomer) {
			CustomerLoginScreen t1 = new CustomerLoginScreen();			
			t1.start();
		}
		if (e.getSource() == btnEmployee) {
			EmployeeLoginScreen t2 = new EmployeeLoginScreen();
			t2.start();
		}
		if (e.getSource() == btnExit) {
			System.exit(0);
		}		
	}
	
	public static void main(String[] args) {
		new WelcomeScreen();
	}

}
