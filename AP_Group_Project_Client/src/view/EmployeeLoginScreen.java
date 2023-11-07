package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EmployeeLoginScreen implements ActionListener {

	private JFrame frame;
	private JLabel lblHello, lblUsername, lblPassword;
	private JTextField txtUsername, txtPassword;
	private JButton btnCancel, btnLogin;
	
	public EmployeeLoginScreen() {		
		frame = new JFrame("GEERS - Employee Login");
		
		lblHello = new JLabel("Hello Staff! Please enter you credentials.");
		lblUsername = new JLabel("Username:");
		lblPassword = new JLabel("Password:");	
		txtUsername = new JTextField();
		txtPassword = new JTextField();	
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
			//new EmployeeDashboard();
		}
		if (e.getSource() == btnCancel) {
			new WelcomeScreen();
		}		
	}
	
	public static void main(String[] args) {
		new CustomerLoginScreen();
	}

}
