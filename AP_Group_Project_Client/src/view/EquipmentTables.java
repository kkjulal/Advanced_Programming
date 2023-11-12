package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class EquipmentTables implements ActionListener {
	
	private JFrame frame;
	private JLabel lblStage, lblLight, lblPower, lblSound;
	private JTable tblStage, tblLight, tblPower, tblSound;
	private JButton btnBack, btnLogout;
	private JScrollPane paneStage, paneLight, panePower, paneSound;
	
	public EquipmentTables() {
		
		frame = new JFrame("GEERS - Equipments Available For Rent");

		lblStage = new JLabel("STAGE EQUIPMENTS AVAILABILITY");
		lblLight = new JLabel("LIGHT EQUIPMENTS AVAILABILITY");
		lblPower = new JLabel("POWER EQUIPMENTS AVAILABILITY");
		lblSound = new JLabel("SOUND EQUIPMENTS AVAILABILITY");
		
		btnBack = new JButton("Return");
		btnLogout = new JButton("Logout");
		
		lblStage.setBounds(150, 60, 200, 20);
		lblLight.setBounds(480, 60, 200, 20);
		lblPower.setBounds(810, 60, 210, 20);
		lblSound.setBounds(1140, 60, 200, 20);
		
		frame.add(lblStage);
		frame.add(lblLight);
		frame.add(lblPower);
		frame.add(lblSound);
		
		//Staging Equipments Table
		String[][] stageData = {
				{"Medium", "Staging", "100,000", "booked"},
				{"Small", "Staging", "50,000", "available"},
				{"Large", "Staging", "150,000", "available"}
				};
		
		String stageColumn[] = { "Size", "Category", "Price", "Status" };		
		tblStage = new JTable(stageData, stageColumn);		
		paneStage = new JScrollPane(tblStage);
		paneStage.setBounds(100, 100, 300, 300);
		
		//Lighting Equipments Table
		String[][] lightData = {
				{"Medium", "Lighting", "100,000", "booked"},
				{"Small", "Lighting", "50,000", "available"},
				{"Large", "Lighting", "150,000", "available"}
				};
		
		String lightColumn[] = { "Size", "Category", "Price", "Status" };		
		tblLight = new JTable(lightData, lightColumn);		
		paneLight = new JScrollPane(tblLight);
		paneLight.setBounds(430, 100, 300, 300);
		
		//Power Equipments Table
		String[][] powerData = {
				{"Medium", "Power", "100,000", "booked"},
				{"Small", "Power", "50,000", "available"},
				{"Large", "Power", "150,000", "available"}
				};
		
		String powerColumn[] = { "Size", "Category", "Price", "Status" };		
		tblPower = new JTable(powerData, powerColumn);		
		panePower = new JScrollPane(tblPower);
		panePower.setBounds(760, 100, 300, 300);
		
		//Sound Equipments Table
		String[][] soundData = {
				{"Medium", "Sound", "100,000", "booked"},
				{"Small", "Sound", "50,000", "available"},
				{"Large", "Sound", "150,000", "available"}
				};
		
		String soundColumn[] = { "Size", "Category", "Price", "Status" };		
		tblSound = new JTable(soundData, soundColumn);		
		paneSound = new JScrollPane(tblSound);
		paneSound.setBounds(1090, 100, 300, 300);
		
		frame.add(paneStage);
		frame.add(paneLight);
		frame.add(panePower);
		frame.add(paneSound);
		
		frame.add(btnBack);
		frame.add(btnLogout);
		
		frame.setSize(1500, 500);
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
		new EquipmentTables();
	}
	
}
