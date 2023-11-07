package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class Signin implements ActionListener {

	private JFrame frmGrizzlysEntertainmentEquipment;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signin window = new Signin();
					window.frmGrizzlysEntertainmentEquipment.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Signin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGrizzlysEntertainmentEquipment = new JFrame();
		frmGrizzlysEntertainmentEquipment.setTitle("Grizzly’s Entertainment Equipment Rental System");
		frmGrizzlysEntertainmentEquipment.setBounds(100, 100, 450, 300);
		frmGrizzlysEntertainmentEquipment.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGrizzlysEntertainmentEquipment.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsername.setBounds(103, 97, 64, 14);
		frmGrizzlysEntertainmentEquipment.getContentPane().add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setToolTipText("");
		usernameField.setBounds(204, 95, 120, 20);
		frmGrizzlysEntertainmentEquipment.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setBounds(103, 129, 64, 14);
		frmGrizzlysEntertainmentEquipment.getContentPane().add(lblPassword);
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.setBounds(204, 166, 89, 23);
		frmGrizzlysEntertainmentEquipment.getContentPane().add(btnSignIn);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(204, 127, 120, 20);
		frmGrizzlysEntertainmentEquipment.getContentPane().add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("Don't have an account?");
		lblNewLabel_2.setBounds(103, 200, 140, 14);
		frmGrizzlysEntertainmentEquipment.getContentPane().add(lblNewLabel_2);
		
		JLabel lblGreeting = new JLabel("Hello!");
		lblGreeting.setForeground(Color.RED);
		lblGreeting.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
		lblGreeting.setBounds(103, 40, 64, 14);
		frmGrizzlysEntertainmentEquipment.getContentPane().add(lblGreeting);
		
		JLabel lblSignInMessage = DefaultComponentFactory.getInstance().createTitle("Sign in to your account.");
		lblSignInMessage.setForeground(Color.BLUE);
		lblSignInMessage.setBounds(103, 65, 151, 14);
		frmGrizzlysEntertainmentEquipment.getContentPane().add(lblSignInMessage);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.setForeground(Color.BLUE);
		btnCreateAccount.setBounds(253, 200, 130, 14);
		frmGrizzlysEntertainmentEquipment.getContentPane().add(btnCreateAccount);
		btnCreateAccount.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		CreateAccount.main(null);
	}
}
