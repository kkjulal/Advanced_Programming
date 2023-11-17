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
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import client.DbConn;
import domain.Employee;

@SuppressWarnings("serial")
public class EmployeeDashboard  implements ActionListener, Serializable {
	
	public static void main(String[] args) {
		Employee empObj = new Employee();
		new EmployeeDashboard(empObj);
	}
	
	//Declaration and Initialization
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	
	private static final Logger logger = LogManager.getLogger(EmployeeDashboard.class);

	//Connection
	private Connection dbConn = null;
	private Statement stmt = null;
	
	private JFrame frame;
	
	private JLabel lblHeading, lblImage, lblWelcome, lblEquipment, lblInbox, lblMessage;
	private JLabel lblSubject, lblRental, lblReceiver;
	
	private JTable tblEquipment, tblRental, tblInbox;
	
	private JScrollPane paneEquipment, paneRental, paneInbox, paneMessage;
	
	private JTextField txtReceiver, txtSubject;
	private JTextArea txtMessage;
	
	private JButton btnLogout, btnMessage;	
	
	private TrayIcon trayIcon;
	private Image image;
	private Image logoImage;
	private Toolkit toolkit;
	private PopupMenu popup;
	
	public EmployeeDashboard() {
		
	}
	
	public EmployeeDashboard(Employee empObj) {
		logger.trace("Entered EmployeeDashboard class.");
		
		this.dbConn = DbConn.getConnection();
		
		this.id = empObj.getId();
		this.firstName = empObj.getFirstName();
		this.lastName = empObj.getLastName();
		
		frame = new JFrame("GEERS - Employee Dashboard");
		
		lblHeading = new JLabel("Grizzly’s Entertainment Equipment Rental System");		
		lblWelcome = new JLabel();
		
		lblEquipment = new JLabel("EQUIPMENTS IN HOUSE");
		lblRental = new JLabel("RENTAL REQUESTS");
		
		lblInbox = new JLabel("COMPANY INBOX");
		lblReceiver = new JLabel("Receiver ID:");
		lblSubject = new JLabel("Subject:");
		lblMessage = new JLabel("SEND MESSAGE");
		
		txtReceiver = new JTextField("");
		txtSubject = new JTextField();
		txtMessage = new JTextArea();
		
		btnLogout = new JButton("Logout");
		btnMessage = new JButton("Send Message");
		
		
		
		setProperties();
		equipments();
		rental();
		inbox();
		
		frame.addWindowListener((WindowListener) new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
				try {
					stmt.close();
					dbConn.close();
					new EmployeeLoginScreen();
					//JOptionPane.showMessageDialog(null, "Connection closed.", "GEERS Connection Status", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "There was an error while closing the program: " + e1.getSQLState(), "GEERS Connection Status", JOptionPane.ERROR_MESSAGE);
					logger.error("There was an error while closing the program: " + e1.getMessage());
				}
		    }
		});
		
	}
	
	public void setProperties() {
		
		txtReceiver.setBounds(840, 530, 100, 20);
		frame.add(txtReceiver);
		
		txtSubject.setBounds(840, 555, 210, 20);
		frame.add(txtSubject);		
		
		txtMessage.setLineWrap(true);
		paneMessage = new JScrollPane(txtMessage);
		paneMessage.setBounds(760, 580, 300, 150);
		frame.add(paneMessage);
		
		btnLogout.setBounds(1200, 50, 150, 25);
		frame.add(btnLogout);
		
		btnMessage.setBounds(900, 740, 150, 25);
		frame.add(btnMessage);
		
		//Set label properties
		lblHeading.setBounds(200, 5, 900, 100);
		lblHeading.setFont(new Font("Helvitica", Font.BOLD, 36));
		lblHeading.setForeground(Color.BLUE);
		frame.add(lblHeading);
		
		lblWelcome.setBounds(225, 50, 500, 100);
		lblWelcome.setFont(new Font("Arial", Font.BOLD, 30));
		lblWelcome.setText("Welcome back " + this.firstName + "!");
		lblWelcome.setForeground(Color.DARK_GRAY);
		frame.add(lblWelcome);
		
		lblEquipment.setBounds(350, 150, 200, 20);
		lblEquipment.setForeground(Color.RED);
		frame.add(lblEquipment);
		
		lblRental.setBounds(1000, 150, 200, 20);
		lblRental.setForeground(Color.RED);
		frame.add(lblRental);
		
		lblInbox.setBounds(360, 530, 200, 20);
		frame.add(lblInbox);
		
		lblReceiver.setBounds(770, 530, 100, 20);
		frame.add(lblReceiver);
		
		lblSubject.setBounds(785, 555, 200, 20);
		frame.add(lblSubject);
		
		lblMessage.setBounds(870, 500, 200, 20);
		lblMessage.setForeground(Color.BLUE);
		frame.add(lblMessage);
		
		//Equipments Table	
		tblEquipment = new JTable();		
		paneEquipment = new JScrollPane(tblEquipment);
		paneEquipment.setBounds(100, 175, 600, 300);
		frame.add(paneEquipment);
		
		//Rental Table	
		tblRental = new JTable();		
		paneRental = new JScrollPane(tblRental);
		paneRental.setBounds(750, 175, 600, 150);
		frame.add(paneRental);
		
		//Message Inbox Table
		tblInbox = new JTable();		
		paneInbox = new JScrollPane(tblInbox);
		paneInbox.setBounds(100, 560, 630, 210);
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
		
		//Set frame properties
		frame.setIconImage(image);
		frame.setSize(1500, 850);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnLogout.addActionListener(this);
		btnMessage.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnMessage) {
			//Gets current date in format yyyy-mm-dd
			Date strDate = Calendar.getInstance().getTime();  
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
			String date = dateFormat.format(strDate);
	        
			String customer = txtReceiver.getText().trim();
			String subject = txtSubject.getText().trim();
			String message = txtMessage.getText().trim();
			
			sendMessage(customer, date, subject, message);		
		}
		
		if (e.getSource() == btnLogout) {			
			

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	//Method used to send customers messages about inquiries
	public void sendMessage(String customer, String date, String subject, String message) {
		String insertSql = "INSERT into geersdb.customer_inbox (date, sender, customer, subject, body, status) values ('" + date + "','" + this.getId() + "','" + customer + "','" + subject + "','" + message + "','unseen');";
		
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
			logger.error("SQL Exception: " + e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "There was an error.", "Geers Messenger Status", JOptionPane.ERROR_MESSAGE);
			logger.error("An error occured: " + e.getMessage());
		}
	}
	
	//Method to get all equipments available
	public void equipments() {
		String sql = "SELECT e.name AS 'package', e.category, c.price 'price per day', e.status FROM equipment e INNER JOIN category c ON e.category = c.category_id;";
		
		try {
			stmt = dbConn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = (ResultSetMetaData) result.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tblEquipment.getModel();
			
			model.setRowCount(0);
			int cols = rsmd.getColumnCount();
			String[] colName = new String[cols];
			for(int i=0; i<cols; i++)
				colName[i]=rsmd.getColumnName(i+1);
			model.setColumnIdentifiers(colName);
			
			String name, category, price, status;
			while(result.next()) {
				name = result.getString(1);
				category = result.getString(2);
				price = result.getString(3);
				status = result.getString(4);
				
				String[] row = {name, category, price, status};
				model.addRow(row);				
			}								
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage(), "Record Display Status", JOptionPane.ERROR_MESSAGE);
			logger.error("SQL Exception: " + e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "There was an error: " + e.getMessage(), "Record Display Status", JOptionPane.ERROR_MESSAGE);
			logger.error("An error occurred: " + e.getMessage());
		}
	}
	
	//Method to get rental requests
	public void rental() {
		//String sql = "SELECT r.date, r.customer, r.duration, r.start_date, r.duration*r.cost as 'total cost', GROUP_CONCAT(DISTINCT re.equipment) AS 'equipments rented' FROM rental r INNER JOIN rented_equipments re ON re.date = re.date ORDER BY r.date DESC;";
		
		String sql = "SELECT r.date AS 'date created', r.customer, r.start_date AS 'reserved date', r.duration, r.cost AS 'daily cost', r.duration*r.cost as 'total cost', GROUP_CONCAT(DISTINCT re.equipment) as 'equipments rented' FROM rental r INNER JOIN rented_equipments re ON r.date = re.date GROUP BY r.date;";
		
		try {
			stmt = dbConn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = (ResultSetMetaData) result.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tblRental.getModel();
			
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
	
	//Method to get company messages
	public void inbox() {
		String sql = "SELECT ci.date, ci.sender, CONCAT(c.first_name, ' ', c.last_name) AS 'customer name', ci.subject, ci.body, ci.status FROM company_inbox ci INNER JOIN customer c ON ci.sender = c.customer_id;";
		
		try {
			stmt = dbConn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = (ResultSetMetaData) result.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tblInbox.getModel();
			
			model.setRowCount(0);
			int cols = rsmd.getColumnCount();
			String[] colName = new String[cols];
			for(int i=0; i<cols; i++)
				colName[i] = rsmd.getColumnName(i+1);
			model.setColumnIdentifiers(colName);
			
			String date, sender, customer_name = null, subject, body, status = null;
			while(result.next()) {
				date = result.getString(1);
				sender = result.getString(2);
				customer_name = result.getString(3);
				subject = result.getString(4);
				body = result.getString(5);
				status = result.getString(6);
				
				String[] row = {date, sender, customer_name, subject, body, status};
				model.addRow(row);
			}					
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage(), "Inbox Status", JOptionPane.ERROR_MESSAGE);
			logger.error("SQL Exception: " + e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage(), "Inbox Status", JOptionPane.ERROR_MESSAGE);
			logger.error("An error occured: " + e.getMessage());
		}
	}

}


