package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.Client;
import domain.Customer;

public class CustomerDashboard implements ActionListener {
	private JFrame frame;
	private JLabel lblHello, lblUsername, lblPassword;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnCancel, btnLogin;
	
	public CustomerDashboard() {		
		initialize();		
	}
	
	public void initialize() {
		frame = new JFrame("GEERS - Customer Dashboard");
		
		lblHello = new JLabel("Hello Customer! Please enter you credentials.");
		lblUsername = new JLabel("Username:");
		lblPassword = new JLabel("Password:");	
		txtUsername = new JTextField();
		txtPassword = new JPasswordField();	
		btnCancel = new JButton("Cancel");
		btnLogin = new JButton("Login");
		
		lblHello.setBounds(100, 25, 300, 50);
		frame.add(lblHello);		
		lblUsername.setBounds(100, 100, 100, 20);
		frame.add(lblUsername);
		lblPassword.setBounds(100, 150, 100, 20);
		frame.add(lblPassword);
		
		txtUsername.setBounds(200, 100, 150, 20);
		frame.add(txtUsername);
		txtPassword.setBounds(200, 150, 150, 20);
		frame.add(txtPassword);
		
		btnCancel.setBounds(100, 200, 100, 25);
		frame.add(btnCancel);
		btnLogin.setBounds(250, 200, 100, 25);
		frame.add(btnLogin);
		
		frame.setSize(500, 325);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnCancel.addActionListener(this);
		btnLogin.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogin) {
		
		}
		if (e.getSource() == btnCancel) {
			
		}		
	}
}
