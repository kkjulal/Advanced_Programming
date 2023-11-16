package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import client.DbConn;

public class CustomerDashboard implements ActionListener {
	//Connection
	private Connection dbConn = null;
	private Statement stmt = null;
	
	//Buttons
	private JFrame frame;
	private JButton btnSearch, btnMessage, btnRefresh;
	
	//Tables
	private JLabel lblStage, lblLight, lblPower, lblSound, lblHistory, lblSearch, lblId, lblDate, lblInbox;
	private JTextField txtId, txtSender, txtDate, txtSubject;
	private JTextArea txtMessage;
	private JTable tblStage, tblLight, tblPower, tblSound, tblHistory, tblSearch, tblInbox;
	private JScrollPane paneStage, paneLight, panePower, paneSound, paneHistory, paneSearch, paneInbox;
	
	public CustomerDashboard() {
		this.dbConn = DbConn.getConnection();
		
		frame = new JFrame("GEERS - Customer Dashboard");
		
		btnRefresh = new JButton("Refresh Dashboard");
		btnSearch = new JButton("Search");
		txtId = new JTextField();
		txtDate = new JTextField();
		btnMessage = new JButton("Send Message");
		txtMessage = new JTextArea();
		
		frame.setSize(1500, 950);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buttons();
		tables();
		
		getTableData();
		transactionHistory();
		
		frame.addWindowListener((WindowListener) new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
				try {
					stmt.close();
					dbConn.close();
					//JOptionPane.showMessageDialog(null, "Connection closed.", "GEERS Connection Status", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "There was an error while closing the connection: " + e1.getSQLState(), "GEERS Connection Status", JOptionPane.ERROR_MESSAGE);
				}
		    }
		});

	}
	
	public void buttons() {
		
		btnRefresh.setBounds(650, 50, 150, 25);
		frame.add(btnRefresh);
		
		btnSearch.setBounds(910, 520, 150, 25);
		frame.add(btnSearch);
		
		txtId.setBounds(910, 450, 150, 25);
		frame.add(txtId);
		
		txtDate.setBounds(910, 480, 150, 25);
		frame.add(txtDate);
		
		btnMessage.setBounds(1230, 600, 150, 25);
		frame.add(btnMessage);
		
		txtMessage.setBounds(1080, 450, 300, 150);
		frame.add(txtMessage);
		
		btnRefresh.addActionListener(this);
		btnSearch.addActionListener(this);
		btnMessage.addActionListener(this);
	}
	
	public void tables() {
		lblStage = new JLabel("STAGE EQUIPMENTS AVAILABILITY");
		lblLight = new JLabel("LIGHT EQUIPMENTS AVAILABILITY");
		lblPower = new JLabel("POWER EQUIPMENTS AVAILABILITY");
		lblSound = new JLabel("SOUND EQUIPMENTS AVAILABILITY");		
		lblHistory = new JLabel("TRANSACTION HISTORY");
		lblInbox = new JLabel("MESSAGE INBOX");
		lblSearch = new JLabel("LOOK UP TRANSACTION");
		lblId = new JLabel("Enter ID: ");
		lblDate = new JLabel("Enter Date 'YYYY-MM-DD': ");
		
		lblStage.setBounds(150, 120, 200, 20);
		lblLight.setBounds(480, 120, 200, 20);
		lblPower.setBounds(810, 120, 210, 20);
		lblSound.setBounds(1140, 120, 200, 20);
		lblHistory.setBounds(330, 370, 200, 20);
		lblInbox.setBounds(360, 630, 200, 20);
		lblSearch.setBounds(1000, 370, 200, 20);
		lblId.setBounds(760, 450, 200, 20);
		lblDate.setBounds(760, 480, 200, 20);
		
		//Add labels to frame
		frame.add(lblStage);
		frame.add(lblLight);
		frame.add(lblPower);
		frame.add(lblSound);
		frame.add(lblHistory);
		frame.add(lblInbox);
		frame.add(lblSearch);
		frame.add(lblId);
		frame.add(lblDate);		
		
		//Stage Equipments Table	
		tblStage = new JTable();		
		paneStage = new JScrollPane(tblStage);
		paneStage.setBounds(100, 150, 300, 200);
		
		//Lighting Equipments Table		
		tblLight = new JTable();		
		paneLight = new JScrollPane(tblLight);
		paneLight.setBounds(430, 150, 300, 200);
		
		//Power Equipments Table		
		tblPower = new JTable();		
		panePower = new JScrollPane(tblPower);
		panePower.setBounds(760, 150, 300, 200);
		
		//Sound Equipments Table		
		tblSound = new JTable();		
		paneSound = new JScrollPane(tblSound);
		paneSound.setBounds(1090, 150, 300, 200);
		
		//Transaction History Table
		tblHistory = new JTable();		
		paneHistory = new JScrollPane(tblHistory);
		paneHistory.setBounds(100, 400, 630, 210);
		
		//Search History Table
		tblSearch = new JTable();		
		paneSearch = new JScrollPane(tblSearch);
		paneSearch.setBounds(760, 400, 630, 40);
		
		//Message Inbox Table
		tblInbox = new JTable();		
		paneInbox = new JScrollPane(tblInbox);
		paneInbox.setBounds(100, 660, 630, 210);
		
		//Add panes to frame
		frame.add(paneStage);
		frame.add(paneLight);
		frame.add(panePower);
		frame.add(paneSound);
		frame.add(paneHistory);
		frame.add(paneSearch);
		frame.add(paneInbox);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			
			String id = txtId.getText().trim();
			String date = txtDate.getText().trim();

			searchTransaction(id, date);
			txtId.setText("");
			txtDate.setText("");
		}
		if (e.getSource() == btnMessage) {
			String sender = txtMessage.getText().trim();
			String date = txtMessage.getText().trim();
			String subject = txtMessage.getText().trim();
			String message = txtMessage.getText().trim();
			
			sendMessage(sender, date, subject, message);		
		}
		if (e.getSource() == btnRefresh) {
			getTableData();
			transactionHistory();
		}
	}	
	
	public static void main(String[] args) {
		new CustomerDashboard();
	}
	
	public void sendMessage(String sender, String date, String subject, String message) {
		String insertSql = "INSERT into geersdb.company_inbox (sender, date, subject, body, status) values ('" + sender + "','" + date + "','" + subject + "','" + message + "','unread');";
		
		try {
			stmt = dbConn.createStatement();
			int inserted = stmt.executeUpdate(insertSql);
			
			if (inserted == 1) {
				JOptionPane.showMessageDialog(null, "Message Sent.", "Geers Messenger Status", JOptionPane.INFORMATION_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(null, "There was an error.", "Geers Messenger Status", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "SQL Exception: " + e.getSQLState(), "Geers Messenger Status", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "There was an error.", "Geers Messenger Status", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void searchTransaction(String id, String date2) {
		String query = "SELECT CONCAT(customer.first_name, ' ', customer.last_name) as 'name', rental.date, rental.duration, rental.cost, GROUP_CONCAT(rented_equipments.equipment) AS 'equipments rented' FROM rental INNER JOIN rented_equipments ON rental.customer = rented_equipments.customer INNER JOIN customer ON customer.customer_id = rental.customer WHERE rental.customer = '" + id + "' AND rental.date = '" + date2 +"'";
		
		try {
			stmt = dbConn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			ResultSetMetaData rsmd = (ResultSetMetaData) result.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tblSearch.getModel();
			
			model.setRowCount(0);
			int cols = rsmd.getColumnCount();
			String[] colName = new String[cols];
			for(int i=0; i<cols; i++)
				colName[i]=rsmd.getColumnName(i+1);
			model.setColumnIdentifiers(colName);

			String customer, date, duration, cost, equipments;
			while(result.next()) {
				customer = result.getString(1);
				date = result.getString(2);
				duration = result.getString(3);
				cost = result.getString(4);
				equipments = result.getString(5);
				
				String[] row = {customer, date, duration, cost, equipments};
				model.addRow(row);
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage(), "Record Search Status", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage(), "Record Search Status", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void transactionHistory() {
		String query = "SELECT CONCAT(customer.first_name, ' ', customer.last_name) as 'name', rental.date, rental.duration, rental.cost, GROUP_CONCAT(rented_equipments.equipment) AS 'equipments rented' FROM rental INNER JOIN rented_equipments ON rental.customer = rented_equipments.customer INNER JOIN customer ON customer.customer_id = rental.customer WHERE rental.customer = customer.customer_id AND rental.date = rented_equipments.date;";
		
		try {
			stmt = dbConn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			ResultSetMetaData rsmd = (ResultSetMetaData) result.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tblHistory.getModel();
			
			model.setRowCount(0);
			int cols = rsmd.getColumnCount();
			String[] colName = new String[cols];
			for(int i=0; i<cols; i++)
				colName[i]=rsmd.getColumnName(i+1);
			model.setColumnIdentifiers(colName);
			
			String customer, date, duration, cost, equipments;
			while(result.next()) {
				customer = result.getString(1);
				date = result.getString(2);
				duration = result.getString(3);
				cost = result.getString(4);
				equipments = result.getString(5);
				
				String[] row = {customer, date, duration, cost, equipments};
				model.addRow(row);				
			}								
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage(), "Record Display Status", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage(), "Record Display Status", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void getTableData() {
		
		//STAGING TABLE
		String sql = "SELECT equipment.name, category.price, equipment.status FROM equipment INNER JOIN category ON equipment.category = category.category_id WHERE category.name = 'STAGING' AND equipment.status = 'available';";
		
		try {
			stmt = dbConn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = (ResultSetMetaData) result.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tblStage.getModel();
			
			model.setRowCount(0);
			int cols = rsmd.getColumnCount();
			String[] colName = new String[cols];
			for(int i=0; i<cols; i++)
				colName[i]=rsmd.getColumnName(i+1);
			model.setColumnIdentifiers(colName);
			
			String name, price, status;
			while(result.next()) {
				name = result.getString(1);
				price = result.getString(2);
				status = result.getString(3);
				String[] row = {name, price, status};
				model.addRow(row);				
			}								
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage(), "Record Display Status", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage(), "Record Display Status", JOptionPane.ERROR_MESSAGE);
		}

		//LIGHTING TABLE
		String sql2 = "SELECT equipment.name, category.price, equipment.status FROM equipment INNER JOIN category ON equipment.category = category.category_id WHERE category.name = 'LIGHTING' AND equipment.status = 'available';";
		
		try {
			stmt = dbConn.createStatement();
			ResultSet result = stmt.executeQuery(sql2);
			ResultSetMetaData rsmd = (ResultSetMetaData) result.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tblLight.getModel();
			
			model.setRowCount(0);
			int cols = rsmd.getColumnCount();
			String[] colName = new String[cols];
			for(int i=0; i<cols; i++)
				colName[i] = rsmd.getColumnName(i+1);
			model.setColumnIdentifiers(colName);
			
			String name, price, status;
			while(result.next()) {
				name = result.getString(1);
				price = result.getString(2);
				status = result.getString(3);
				String[] row = {name, price, status};
				model.addRow(row);				
			}					
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage(), "Record Display Status", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage(), "Record Display Status", JOptionPane.ERROR_MESSAGE);
		}
		
	
		//POWER TABLE
		String sql3 = "SELECT equipment.name, category.price, equipment.status FROM equipment INNER JOIN category ON equipment.category = category.category_id WHERE category.name = 'POWER' AND equipment.status = 'available';";
		
		try {
			stmt = dbConn.createStatement();
			ResultSet result = stmt.executeQuery(sql3);
			ResultSetMetaData rsmd = (ResultSetMetaData) result.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tblPower.getModel();
			
			model.setRowCount(0);
			int cols = rsmd.getColumnCount();
			String[] colName = new String[cols];
			for(int i=0; i<cols; i++)
				colName[i] = rsmd.getColumnName(i+1);
			model.setColumnIdentifiers(colName);
			
			String name, price, status;
			while(result.next()) {
				name = result.getString(1);
				price = result.getString(2);
				status = result.getString(3);
				String[] row = {name, price, status};
				model.addRow(row);				
			}					
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage(), "Record Display Status", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage(), "Record Display Status", JOptionPane.ERROR_MESSAGE);
		}

		//SOUND TABLE
		String sql4 = "SELECT equipment.name, category.price, equipment.status FROM equipment INNER JOIN category ON equipment.category = category.category_id WHERE category.name = 'SOUND' AND equipment.status = 'available';";
		
		try {
			stmt = dbConn.createStatement();
			ResultSet result = stmt.executeQuery(sql4);
			ResultSetMetaData rsmd = (ResultSetMetaData) result.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tblSound.getModel();
			
			model.setRowCount(0);
			int cols = rsmd.getColumnCount();
			String[] colName = new String[cols];
			for(int i=0; i<cols; i++)
				colName[i] = rsmd.getColumnName(i+1);
			model.setColumnIdentifiers(colName);
			
			String name, price, status;
			while(result.next()) {
				name = result.getString(1);
				price = result.getString(2);
				status = result.getString(3);
				String[] row = {name, price, status};
				model.addRow(row);				
			}					
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage(), "Record Display Status", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage(), "Record Display Status", JOptionPane.ERROR_MESSAGE);
		}
		
		//INBOX TABLE
		String sql5 = "SELECT date, CONCAT(employee.first_name, ' ', employee.last_name) AS 'employee', subject, body, status FROM customer_inbox INNER JOIN employee ON customer_inbox.sender = employee.employee_id WHERE customer = '1000';";
		
		try {
			stmt = dbConn.createStatement();
			ResultSet result = stmt.executeQuery(sql5);
			ResultSetMetaData rsmd = (ResultSetMetaData) result.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tblInbox.getModel();
			
			model.setRowCount(0);
			int cols = rsmd.getColumnCount();
			String[] colName = new String[cols];
			for(int i=0; i<cols; i++)
				colName[i] = rsmd.getColumnName(i+1);
			model.setColumnIdentifiers(colName);
			
			String date, sender, subject, body, status;
			while(result.next()) {
				date = result.getString(1);
				sender = result.getString(2);
				subject = result.getString(3);
				body = result.getString(4);
				status = result.getString(5);
				
				String[] row = {date, sender, subject, body, status};
				model.addRow(row);
			}					
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage(), "Inbox Status", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage(), "Inbox Status", JOptionPane.ERROR_MESSAGE);
		}
	}
}
