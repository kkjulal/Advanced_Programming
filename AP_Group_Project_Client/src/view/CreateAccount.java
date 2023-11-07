package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class CreateAccount {

	private JFrame frmGrizzlysEntertainmentEquipment;
	private JTextField textTRN;
	private JTextField textName;
	private JTextField textAddress;
	private JTextField textTelephone;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccount window = new CreateAccount();
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
	public CreateAccount() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGrizzlysEntertainmentEquipment = new JFrame();
		frmGrizzlysEntertainmentEquipment.setTitle("Grizzly’s Entertainment Equipment Rental System");
		frmGrizzlysEntertainmentEquipment.setBounds(100, 100, 450, 430);
		frmGrizzlysEntertainmentEquipment.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGrizzlysEntertainmentEquipment.getContentPane().setLayout(null);
		
		JLabel lblTRN = new JLabel("TRN");
		lblTRN.setBounds(110, 148, 50, 14);
		frmGrizzlysEntertainmentEquipment.getContentPane().add(lblTRN);
		
		textTRN = new JTextField();
		textTRN.setBounds(170, 145, 150, 20);
		frmGrizzlysEntertainmentEquipment.getContentPane().add(textTRN);
		textTRN.setColumns(10);
		
		textName = new JTextField();
		textName.setColumns(10);
		textName.setBounds(170, 181, 150, 20);
		frmGrizzlysEntertainmentEquipment.getContentPane().add(textName);
		
		textAddress = new JTextField();
		textAddress.setColumns(10);
		textAddress.setBounds(170, 212, 150, 20);
		frmGrizzlysEntertainmentEquipment.getContentPane().add(textAddress);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(110, 184, 50, 14);
		frmGrizzlysEntertainmentEquipment.getContentPane().add(lblName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(110, 215, 50, 14);
		frmGrizzlysEntertainmentEquipment.getContentPane().add(lblAddress);
		
		JLabel lblCreateAccount = new JLabel("Create an account...");
		lblCreateAccount.setForeground(Color.RED);
		lblCreateAccount.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
		lblCreateAccount.setBackground(Color.WHITE);
		lblCreateAccount.setBounds(110, 74, 210, 14);
		frmGrizzlysEntertainmentEquipment.getContentPane().add(lblCreateAccount);
		
		JLabel lblTelephone = new JLabel("Telephone");
		lblTelephone.setBounds(110, 246, 50, 14);
		frmGrizzlysEntertainmentEquipment.getContentPane().add(lblTelephone);
		
		textTelephone = new JTextField();
		textTelephone.setColumns(10);
		textTelephone.setBounds(170, 243, 150, 20);
		frmGrizzlysEntertainmentEquipment.getContentPane().add(textTelephone);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(170, 274, 150, 20);
		frmGrizzlysEntertainmentEquipment.getContentPane().add(textField);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(110, 277, 50, 14);
		frmGrizzlysEntertainmentEquipment.getContentPane().add(lblEmail);
		
		JLabel lblMessage = new JLabel("Please complete the form below:");
		lblMessage.setForeground(Color.BLUE);
		lblMessage.setBounds(110, 99, 210, 14);
		frmGrizzlysEntertainmentEquipment.getContentPane().add(lblMessage);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(110, 324, 89, 23);
		frmGrizzlysEntertainmentEquipment.getContentPane().add(btnCancel);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(231, 324, 89, 23);
		frmGrizzlysEntertainmentEquipment.getContentPane().add(btnSubmit);
	}

}
