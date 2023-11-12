package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Transactions implements ActionListener {
	private JFrame frame;
	private JLabel lblTransaction;
	private JTable tblTransaction;
	private JButton btnBack, btnLogout;
	private JScrollPane pane;
	
	public Transactions() {
		
		frame = new JFrame("GEERS - Transaction History");

		lblTransaction = new JLabel("Your Past Transactions");
		
		btnBack = new JButton("Return");
		btnLogout = new JButton("Logout");
		
		lblTransaction.setBounds(50, 20, 200, 20);		
		frame.add(lblTransaction);
		
		//Staging Equipments Table
		String[][] transactionData = {
				{"10/10/2023", "PMT01", "REN01", "100,000", "100,000", "Equipment"},
				{"10/10/2023", "PMT01", "REN01", "100,000", "100,000", "Equipment"},
				{"10/10/2023", "PMT01", "REN01", "100,000", "100,000", "Equipment"},
				{"10/10/2023", "PMT01", "REN01", "100,000", "100,000", "Equipment"},
				{"10/10/2023", "PMT01", "REN01", "100,000", "100,000", "Equipment"},
				{"10/10/2023", "PMT01", "REN01", "100,000", "100,000", "Equipment"},
				{"10/10/2023", "PMT01", "REN01", "100,000", "100,000", "Equipment"},
				{"10/10/2023", "PMT01", "REN01", "100,000", "100,000", "Equipment"},
				{"10/10/2023", "PMT01", "REN01", "100,000", "100,000", "Equipment"},
				{"10/10/2023", "PMT01", "REN01", "100,000", "100,000", "Equipment"}
		};
		
		String Column[] = { "Date", "Payment ID", "Rental ID", "Total Cost", "Total Paid", "Equipments" };		
		tblTransaction = new JTable(transactionData, Column);		
		pane = new JScrollPane(tblTransaction);
		pane.setBounds(50, 50, 500, 500);
		
		frame.add(pane);
		
		frame.add(btnBack);
		frame.add(btnLogout);
		
		frame.setSize(700, 700);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnBack.addActionListener(this);
		btnLogout.addActionListener(this);		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new Transactions();
	}

}
