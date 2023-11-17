package view;

import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import domain.Customer;

public class CustomerLoginScreen extends Thread  implements ActionListener {
	
	@Override
	public void run() { //Multi-threading Implementation
		System.out.println("Customer Thread ID: "+ Thread.currentThread().getId());
		new CustomerLoginScreen();
	}
	
	private JFrame frame;
	private JLabel lblHello, lblUsername, lblPassword;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnCancel, btnLogin;
	private ImageIcon frameIcon;
	private TrayIcon trayIcon;
	private Image image, logoImage;
	private Toolkit toolkit;
	private PopupMenu popup;
	
	private static final Logger logger = LogManager.getLogger(CustomerLoginScreen.class);
	
	public CustomerLoginScreen() {
		logger.info("Now in CustomerLoginScreen.");
		
		initialize();
	}
	
	public void initialize() {
		frame = new JFrame("GEERS - Customer Login");
		
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
		
		toolkit = Toolkit.getDefaultToolkit();
		image = toolkit.getImage("./images/geer.png");
		trayIcon = new TrayIcon(image, "Tray Icon");
		trayIcon.setPopupMenu(popup);
		popup = new PopupMenu();
		
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
			
			String id = txtUsername.getText().trim();
			String password = String.valueOf(txtPassword.getPassword());
			Customer customer = new Customer(id, password);
			Customer cusReturn = new Customer();
			
			cusReturn = customer.loginSearch(); //Search for customer
			
			//If customer is found they will gain access to the dashboard.
			if(cusReturn.getId().equals(id) && cusReturn.getPassword().equals(password)) {
				JOptionPane.showMessageDialog(null, "Login successful.", "Customer Login Status", JOptionPane.INFORMATION_MESSAGE);
				logger.info("Customer " + id + "logged in.");
				new CustomerDashboard(cusReturn); //passing the customer's id and name to use in various functions
				
			}
			else
				JOptionPane.showMessageDialog(null, "User not found. Please check your credentials and try again.", "Customer Login Status", JOptionPane.ERROR_MESSAGE);
				logger.error("Customer login failed ID used: " + id);
		}
		if (e.getSource() == btnCancel) {
			new WelcomeScreen();
		}		
	}
	
	public static void main(String[] args) {
		new CustomerLoginScreen();
	}
	
}
