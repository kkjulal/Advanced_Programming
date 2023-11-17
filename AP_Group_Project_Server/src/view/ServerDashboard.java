package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import server.Server;

public class ServerDashboard implements ActionListener {
	private JFrame frame;
	private JLabel lblWelcome, lblOption;
	private JButton btnGenerateData, btnStartServer, btnExit;
	
	private static final Logger logger = LogManager.getLogger(ServerDashboard.class);
	
	public ServerDashboard() {
		logger.trace("Entered ServerDashboard class.");
		
		frame = new JFrame("GEERS - ServerDashboard");
		lblWelcome = new JLabel("Welcome back Admin!");
		lblOption = new JLabel("Please select an option to continue:");
		btnGenerateData = new JButton("Generate Data");
		btnStartServer = new JButton("Start Server");
		btnExit = new JButton("Exit");
		
		lblWelcome.setBounds(130, 25, 450, 20);
		lblOption.setBounds(80, 75, 250, 20);
		btnGenerateData.setBounds(50, 125, 130, 30);
		btnStartServer.setBounds(200, 125, 130, 30);
		btnExit.setBounds(150, 175, 75, 30);
		
		frame.add(lblWelcome);
		frame.add(lblOption);
		frame.add(btnGenerateData);
		frame.add(btnStartServer);
		frame.add(btnExit);		
		
		frame.setSize(400, 280);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnGenerateData.addActionListener(this);
		btnStartServer.addActionListener(this);
		btnExit.addActionListener(this);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnStartServer) {
			new Server();
		}
		if (e.getSource() == btnGenerateData) {
			
		}
		if (e.getSource() == btnExit) {
			System.exit(0);
		}		
	}
	
	public static void main(String[] args) {
		new ServerDashboard();
	}

}
