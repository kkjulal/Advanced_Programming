package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import domain.Customer;
import client.DbConn;

@SuppressWarnings("serial")
public class CustomerDashboard implements ActionListener, Serializable {
	
	public static void main(String[] args) {
		Customer cusObj = new Customer();
		new CustomerDashboard(cusObj);
	}
	
	//Declaration and Initialization
	private String id;
	private String firstName;
	private String lastName;
	private double balance;
	
	private static final Logger logger = LogManager.getLogger(EmployeeDashboard.class);
	
	//Connection
	private Connection dbConn = null;
	private Statement stmt = null;
	
	private JFrame frame;
	private JButton btnSearch, btnMessage, btnLogout, btnCalculate;
	
	private JLabel lblStage, lblLight, lblPower, lblSound, lblHistory, lblSearch, lblResult, lblDate, lblSubject, lblMessage, lblInbox, lblImage, lblWelcome, lblQuote;
	private JLabel lblSelectEquipment, lblQuoteDate, lblHeading, lblBalance, lblCost, lblDuration;
	private JTextField txtResult, txtSender, txtDate, txtSubject, txtDuration, txtQuoteDate, txtCost;
	private JTextArea txtMessage;
	private JTable tblStage, tblLight, tblPower, tblSound, tblHistory, tblSearch, tblInbox;
	private JTable availablStage, availableLight, availablePower, availableSound;
	private JScrollPane paneStage, paneLight, panePower, paneSound, paneHistory, paneSearch, paneInbox, paneMessage;	
	
	private JCheckBox cbStage, cbLighting, cbPower, cbSound;
	private JComboBox<String> comboBox;
	
	private TrayIcon trayIcon;
	private Image image, logoImage;
	private Toolkit toolkit;
	private PopupMenu popup;
	
	public CustomerDashboard() {
		
	}
	
	public CustomerDashboard(Customer cusObj) {
		logger.trace("Entered CustomerDashboard class.");
		
		this.dbConn = DbConn.getConnection();
		
		this.id = cusObj.getId();
		this.firstName = cusObj.getFirstName();
		this.lastName = cusObj.getLastName();
		this.balance = cusObj.getBalance();
		
		frame = new JFrame("GEERS - Customer Dashboard");
		
		btnLogout = new JButton("Logout");
		btnSearch = new JButton("Search");
		btnMessage = new JButton("Send Message");
		btnCalculate = new JButton("Calculate");
		
		txtResult = new JTextField();
		txtDate = new JTextField();
		txtSubject = new JTextField();
		txtMessage = new JTextArea();
		txtDuration = new JTextField();
		txtQuoteDate = new JTextField();
		txtCost = new JTextField();
		
		lblStage = new JLabel("STAGE EQUIPMENTS AVAILABILITY");
		lblLight = new JLabel("LIGHT EQUIPMENTS AVAILABILITY");
		lblPower = new JLabel("POWER EQUIPMENTS AVAILABILITY");
		lblSound = new JLabel("SOUND EQUIPMENTS AVAILABILITY");		
		lblHistory = new JLabel("TRANSACTION HISTORY");
		lblInbox = new JLabel("CUSTOMER INBOX");
		lblSubject = new JLabel("Subject:");
		lblMessage = new JLabel("SEND ENQUIRY");
		lblSearch = new JLabel("LOOK UP TRANSACTION");
		lblResult = new JLabel("Result: ");
		lblDate = new JLabel("Enter Date: ");
		lblWelcome = new JLabel();
		lblQuote = new JLabel("GET QUOTE");
		lblSelectEquipment = new JLabel("Select Equipment:");
		lblQuoteDate = new JLabel("Enter Date:");
		lblHeading = new JLabel("Grizzly’s Entertainment Equipment Rental System");
		lblBalance = new JLabel("Your account Balance is: " + this.getBalance());
		lblCost = new JLabel("Total Cost:");
		lblDuration = new JLabel("Duration (days):");

		cbStage = new JCheckBox("Staging");
		cbLighting = new JCheckBox("Lighting");
		cbPower = new JCheckBox("Power");
		cbSound = new JCheckBox("Sound");
		
		setProperties();		
		getTableData();
		transactionHistory();
		updateStatus();
		
		frame.addWindowListener((WindowListener) new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
				try {
					stmt.close();
					dbConn.close();
					new CustomerLoginScreen();
					//JOptionPane.showMessageDialog(null, "Connection closed.", "GEERS Connection Status", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "There was an error while exiting the program: " + e1.getSQLState(), "GEERS Connection Status", JOptionPane.ERROR_MESSAGE);
					logger.error("There was an error while closing the program: " + e1.getMessage());
				}
		    }
		});
	}
	
	public void setProperties() {
		
		btnLogout.setBounds(1200, 50, 150, 25);
		frame.add(btnLogout);
		
		btnSearch.setBounds(910, 420, 150, 25);
		frame.add(btnSearch);
		
		btnMessage.setBounds(900, 740, 150, 25);
		frame.add(btnMessage);
		
		btnCalculate.setBounds(1200, 660, 150, 25);
		frame.add(btnCalculate);
		
		txtResult.setBounds(910, 350, 150, 25);
		frame.add(txtResult);
		
		txtDate.setBounds(910, 380, 150, 25);
		txtDate.setText("yyyy-mm-dd");
		frame.add(txtDate);
		
		txtSubject.setBounds(840, 555, 210, 20);
		frame.add(txtSubject);		
		
		txtMessage.setLineWrap(true);
		paneMessage = new JScrollPane(txtMessage);
		paneMessage.setBounds(760, 580, 300, 150);
		frame.add(paneMessage);
		
		txtDuration.setBounds(1200, 530, 50, 25);
		frame.add(txtDuration);
		
		txtQuoteDate.setBounds(1200, 570, 150, 25);
		txtQuoteDate.setText("yyyy-mm-dd");
		frame.add(txtQuoteDate);
		
		txtCost.setBounds(1200, 620, 150, 25);
		frame.add(txtCost);
		
		//Set label properties
		lblStage.setBounds(150, 150, 200, 20);
		lblStage.setForeground(Color.RED);
		lblLight.setBounds(480, 150, 200, 20);
		lblLight.setForeground(Color.RED);
		lblPower.setBounds(810, 150, 210, 20);
		lblPower.setForeground(Color.RED);
		lblSound.setBounds(1140, 150, 200, 20);
		lblSound.setForeground(Color.RED);
		lblHistory.setBounds(330, 270, 200, 20);
		lblSubject.setBounds(785, 555, 200, 20);
		lblInbox.setBounds(360, 530, 200, 20);
		lblMessage.setBounds(870, 530, 200, 20);
		lblMessage.setForeground(Color.BLUE);
		lblSearch.setBounds(1000, 270, 200, 20);
		lblResult.setBounds(800, 350, 200, 20);
		lblDate.setBounds(800, 380, 200, 20);
		lblWelcome.setBounds(225, 50, 500, 100);
		lblWelcome.setFont(new Font("Arial", Font.BOLD, 30));
		lblWelcome.setText("Welcome back " + this.firstName + "!");
		lblWelcome.setForeground(Color.DARK_GRAY);
		lblQuote.setBounds(1200, 420, 200, 20);
		lblQuote.setForeground(Color.BLUE);
		lblSelectEquipment.setBounds(1110, 500, 200, 20);
		lblDuration.setBounds(1100, 530, 200, 20);
		lblQuoteDate.setBounds(1120, 570, 200, 20);
		lblCost.setBounds(1120, 620, 200, 20);
		lblHeading.setBounds(200, 5, 900, 100);
		lblHeading.setFont(new Font("Helvitica", Font.BOLD, 36));
		lblHeading.setForeground(Color.RED);
		lblBalance.setBounds(1160, 100, 300, 25);
		lblBalance.setForeground(Color.DARK_GRAY);
		
		//Add labels to frame
		frame.add(lblStage);
		frame.add(lblLight);
		frame.add(lblPower);
		frame.add(lblSound);
		frame.add(lblHistory);
		frame.add(lblInbox);
		frame.add(lblSubject);
		frame.add(lblMessage);
		frame.add(lblSearch);
		frame.add(lblResult);
		frame.add(lblDate);
		frame.add(lblWelcome);
		frame.add(lblQuote);
		frame.add(lblCost);
		frame.add(lblSelectEquipment);
		frame.add(lblDuration);
		frame.add(lblQuoteDate);
		frame.add(lblHeading);
		frame.add(lblBalance);		
		
		//Add Checkbox to frame
		cbStage.setBounds(1100, 450, 75, 20);
		cbLighting.setBounds(1175, 450, 80, 20);
		cbPower.setBounds(1255, 450, 65, 20);
		cbSound.setBounds(1320, 450, 75, 20);
		frame.add(cbStage);
		frame.add(cbLighting);
		frame.add(cbPower);
		frame.add(cbSound);
		
		//Add ComboBox to frame
		String equipments[] = {"Staging", "Lighting", "Power", "Sound"};
		comboBox = new JComboBox<String>(equipments);
		comboBox.setSelectedIndex(1);
		comboBox.setBounds(1225, 500, 100, 20);
		frame.add(comboBox);
		
		//Stage Equipments Table	
		tblStage = new JTable();		
		paneStage = new JScrollPane(tblStage);
		paneStage.setBounds(100, 175, 300, 75);
		
		//Lighting Equipments Table		
		tblLight = new JTable();		
		paneLight = new JScrollPane(tblLight);
		paneLight.setBounds(430, 175, 300, 75);
		
		//Power Equipments Table		
		tblPower = new JTable();		
		panePower = new JScrollPane(tblPower);
		panePower.setBounds(760, 175, 300, 75);
		
		//Sound Equipments Table		
		tblSound = new JTable();		
		paneSound = new JScrollPane(tblSound);
		paneSound.setBounds(1090, 175, 300, 75);
		
		//Transaction History Table
		tblHistory = new JTable();		
		paneHistory = new JScrollPane(tblHistory);
		paneHistory.setBounds(100, 300, 630, 210);
		
		//Search Transaction Table
		tblSearch = new JTable();		
		paneSearch = new JScrollPane(tblSearch);
		paneSearch.setBounds(760, 300, 630, 39);
		
		//Message Inbox Table
		tblInbox = new JTable();		
		paneInbox = new JScrollPane(tblInbox);
		paneInbox.setBounds(100, 560, 630, 210);
		
		//Add panes to frame
		frame.add(paneStage);
		frame.add(paneLight);
		frame.add(panePower);
		frame.add(paneSound);
		frame.add(paneHistory);
		frame.add(paneSearch);
		frame.add(paneInbox);
		
		//Add tray icon
		toolkit = Toolkit.getDefaultToolkit();
		image = toolkit.getImage("./images/geer.png");
		trayIcon = new TrayIcon(image, "Tray Icon");
		trayIcon.setPopupMenu(popup);
		popup = new PopupMenu();
		
		try {
			BufferedImage bufferedImage = ImageIO.read(new File("./images/geer.png"));
			logoImage = bufferedImage.getScaledInstance(90, 90, Image.SCALE_DEFAULT);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "An error occured: " + e.getMessage(), "An error occured: " + e.getMessage(), JOptionPane.ERROR);
			logger.error("An error occured: " + e.getMessage());
		}
		
		//Add image to frame
		lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(logoImage));
		lblImage.setBounds(100, 30, 90, 90);
		frame.add(lblImage);
		
		frame.setIconImage(image);
		frame.setSize(1500, 850);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnLogout.addActionListener(this);
		btnSearch.addActionListener(this);
		btnMessage.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			String id = txtResult.getText().trim();
			String date = txtDate.getText().trim();

			searchTransaction(date);
			txtDate.setText("yyyy-mm-dd");
		}
		if (e.getSource() == btnMessage) {
			//Gets current date in format yyyy-mm-dd
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
			LocalDateTime now = LocalDateTime.now();
	        
			String subject = txtSubject.getText().trim();
			String message = txtMessage.getText().trim();
			
			sendMessage(this.id, now, subject, message);		
		}
		if (e.getSource() == btnLogout) {
			
			
			try {
				stmt.close();
				dbConn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == btnCalculate) {
			
		}
	}
	
	//Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Customer Id: " + id + "\nFirst Name: " + firstName + "\nLast Name: " + lastName + "\nBalance: "
				+ balance + "]";
	}
	
	//Method used to calculate Quote
	public void calculateQuote() {
		
	}
	
	//Method used to send GEER an inquiry message
	public void sendMessage(String sender, LocalDateTime date, String subject, String message) {
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
	
	//Method used to update message status
	public void updateStatus() {
		String insertSql = "UPDATE geersdb.customer_inbox SET status = 'seen' WHERE status = 'unseen';";
		
		try {
			stmt = dbConn.createStatement();
			int inserted = stmt.executeUpdate(insertSql);
			
			if (inserted == 1) {
				//JOptionPane.showMessageDialog(null, "Message Sent.", "Geers Messenger Status", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "SQL Exception: " + e.getSQLState(), "Geers Messenger Status", JOptionPane.ERROR_MESSAGE);
			logger.error("SQL Exception: " + e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "There was an error.", "Geers Messenger Status", JOptionPane.ERROR_MESSAGE);
			logger.error("An error occured: " + e.getMessage());
		}
	}
	
	public void availableStages() {
		String query = "SELECT ";
		
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
			
			String date, duration, cost, equipments;
			while(result.next()) {
				date = result.getString(1);
				duration = result.getString(2);
				cost = result.getString(3);
				equipments = result.getString(4);
				
				String[] row = {date, duration, cost, equipments};
				model.addRow(row);				
			}								
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage(), "Record Display Status", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage(), "Record Display Status", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//Method used to search customer records for a specific transaction using their id and entered date
	public void searchTransaction(String date2) {
		String sql = "SELECT r.date AS 'date created', r.customer, r.start_date AS 'reserved date', r.duration, r.cost AS 'daily cost', r.duration*r.cost as 'total cost', GROUP_CONCAT(DISTINCT re.equipment) as 'equipments rented' FROM rental r INNER JOIN rented_equipments re ON r.date = re.date WHERE r.date = '"+date2+"' GROUP BY r.date;";
		
		try {
			stmt = dbConn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = (ResultSetMetaData) result.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tblSearch.getModel();
			
			model.setRowCount(0);
			int cols = rsmd.getColumnCount();
			String[] colName = new String[cols];
			for(int i=0; i<cols; i++)
				colName[i]=rsmd.getColumnName(i+1);
			model.setColumnIdentifiers(colName);
			
			String date, customer, duration, startDate, cost, totalCost, equipments;
			while(result.next()) {
				date = result.getString(1);
				customer = result.getString(2);
				startDate = result.getString(3);
				duration = result.getString(4);
				cost = result.getString(5);
				totalCost = result.getString(6);
				equipments = result.getString(7);
				
				String[] row = {date, customer, startDate, duration, cost, totalCost, equipments};
				model.addRow(row);				
			}								
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage(), "Record Display Status", JOptionPane.ERROR_MESSAGE);
			logger.error("SQL Exception: " + e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "There was an error: " + e.getMessage(), "Record Display Status", JOptionPane.ERROR_MESSAGE);
			logger.error("An error occured: " + e.getMessage());
		}
	}
	
	public void transactionHistory() {
		String sql = "SELECT r.date AS 'date created', r.customer, r.start_date AS 'reserved date', r.duration, r.cost AS 'daily cost', r.duration*r.cost as 'total cost', GROUP_CONCAT(DISTINCT re.equipment) as 'equipments rented' FROM rental r INNER JOIN rented_equipments re ON r.date = re.date GROUP BY r.date;";
		
		try {
			stmt = dbConn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = (ResultSetMetaData) result.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tblHistory.getModel();
			
			model.setRowCount(0);
			int cols = rsmd.getColumnCount();
			String[] colName = new String[cols];
			for(int i=0; i<cols; i++)
				colName[i]=rsmd.getColumnName(i+1);
			model.setColumnIdentifiers(colName);
			
			String date, customer, duration, startDate, cost, totalCost, equipments;
			while(result.next()) {
				date = result.getString(1);
				customer = result.getString(2);
				startDate = result.getString(3);
				duration = result.getString(4);
				cost = result.getString(5);
				totalCost = result.getString(6);
				equipments = result.getString(7);
				
				String[] row = {date, customer, startDate, duration, cost, totalCost, equipments};
				model.addRow(row);				
			}								
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage(), "Record Display Status", JOptionPane.ERROR_MESSAGE);
			logger.error("SQL Exception: " + e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "There was an error: " + e.getMessage(), "Record Display Status", JOptionPane.ERROR_MESSAGE);
			logger.error("An error occured: " + e.getMessage());
		}
	}
	
	//Methods to get the table data of all equipments from all category
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
		String sql5 = "SELECT date, CONCAT(employee.first_name, ' ', employee.last_name) AS 'sender', subject, body FROM customer_inbox INNER JOIN employee ON customer_inbox.sender = employee.employee_id WHERE customer = '" + id + "';";
		
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
			
			String date, sender, subject, body;
			while(result.next()) {
				date = result.getString(1);
				sender = result.getString(2);
				subject = result.getString(3);
				body = result.getString(4);
				
				String[] row = {date, sender, subject, body};
				model.addRow(row);
			}					
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage(), "Inbox Status", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage(), "Inbox Status", JOptionPane.ERROR_MESSAGE);
		}
	}
}
