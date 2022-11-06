package Admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.Color;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField txtemp_id;
	private JTextField txtdays;
	private JTextField txthours;
	private JTable table_1;
	private JComboBox<String> txteid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtid;
	private JTextField txt_empid;
	private JTextField txtFirstname;
	private JTextField txtLastname;
	private double hours;
	private JTextField txtSalary;
	private JTable table_2;
	public void Connect() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/employeeandpayroll","root","");
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(EmployeeCRUD.class.getName()).log(Level.SEVERE, null ,ex);
		}
	
		
	}
	
	public void fetch() {
		try {
			pst = con.prepareStatement("SELECT * FROM attendance");
			rs = pst.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

	/**
	 * Create the frame.
	 */
	public Home() throws SQLException {
		Connect();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 664, 484);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Employee", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new CompoundBorder());
		tabbedPane.addTab("Payroll", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Employee ID");
		lblNewLabel_4.setBounds(10, 24, 60, 14);
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Firstname");
		lblNewLabel_5.setBounds(10, 49, 60, 14);
		panel_2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Lastname");
		lblNewLabel_6.setBounds(10, 74, 60, 14);
		panel_2.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Hours Worked");
		lblNewLabel_7.setBounds(10, 99, 74, 20);
		panel_2.add(lblNewLabel_7);
		
		txt_empid = new JTextField();
		txt_empid.setBounds(104, 21, 86, 20);
		panel_2.add(txt_empid);
		txt_empid.setColumns(10);
		
		txtFirstname = new JTextField();
		txtFirstname.setBounds(104, 46, 86, 20);
		panel_2.add(txtFirstname);
		txtFirstname.setColumns(10);
		
		txtLastname = new JTextField();
		txtLastname.setBounds(104, 71, 86, 20);
		panel_2.add(txtLastname);
		txtLastname.setColumns(10);
		
		JTextField hours = new JTextField();
		hours.setBounds(104, 102, 86, 20);
		panel_2.add(hours);
		hours.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Salary");
		lblNewLabel_8.setBounds(10, 130, 46, 14);
		panel_2.add(lblNewLabel_8);
		
		txtSalary = new JTextField();
		txtSalary.setBounds(104, 133, 86, 20);
		panel_2.add(txtSalary);
		txtSalary.setColumns(10);
		
		JButton btnNewButton = new JButton("Calculate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
//					String emp_id = txt_empid.getText();
//					String firstname = txtFirstname.getText();
//					String lastname = txtLastname.getText();
//					String hours = txthours.getText();
//					String salary = txtSalary.getText();
					
					String firstname = txtFirstname.getText();
					String Lastname = txtLastname.getText();
					String emp_id = txt_empid.getText();
					Double hours = Double.parseDouble(txthours.getText());
					Double salary = Double.parseDouble(txtSalary.getText());
					
					
					Double calculatedSalary = (salary * hours);
					
					txttax(calculatedSalary);
					
					
					
					
					
					
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}

			private void txttax(Double calculatedSalary) {
				// TODO Auto-generated method stub
				
			}

			private Double setText(double calculatedSalary) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		btnNewButton.setBounds(223, 146, 89, 23);
		panel_2.add(btnNewButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(392, 11, 257, 205);
		panel_2.add(scrollPane_1);
		
		table_2 = new JTable();
		scrollPane_1.setViewportView(table_2);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Time", null, panel_1, null);
		panel_1.setLayout(null);
		
		txtemp_id = new JTextField();
		txtemp_id.setBounds(112, 56, 86, 20);
		panel_1.add(txtemp_id);
		txtemp_id.setColumns(10);
		
		txtdays = new JTextField();
		txtdays.setBounds(112, 87, 86, 20);
		panel_1.add(txtdays);
		txtdays.setColumns(10);
		
		txthours = new JTextField();
		txthours.setBounds(112, 118, 86, 20);
		panel_1.add(txthours);
		txthours.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Employee ID");
		lblNewLabel.setBounds(10, 59, 68, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Days Worked");
		lblNewLabel_1.setBounds(10, 90, 68, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Hours worked");
		lblNewLabel_2.setBounds(10, 121, 68, 14);
		panel_1.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(337, 22, 274, 222);
		panel_1.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		 txteid = new JComboBox<String>();
		 txteid.setBounds(241, 19, 86, 22);
		 panel_1.add(txteid);
		 
		 JButton CaluclateTime = new JButton("Calculate");
		 CaluclateTime.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	}
		 });
		 CaluclateTime.setBounds(10, 146, 89, 23);
		 panel_1.add(CaluclateTime);
		 
		 JButton SearchEmployee = new JButton("Search ID");
		 SearchEmployee.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		try {
		 			String eid = txteid.getSelectedItem().toString();
		 			pst = con.prepareStatement("SELECT * FROM attendance id=?");
		 			pst.setString(1,eid);
		 			rs=pst.executeQuery();
		 			
		 			if(rs.next() == true) {
		 				txtid.setText(rs.getString(2));
		 				txtdays.setText(rs.getString(3));
		 				txthours.setText(rs.getString(4));
		 				
		 			}else {
		 				JOptionPane.showMessageDialog(null , "No record found");
		 			}
		 			
		 		} catch (SQLException e1) {
		 			// TODO Auto-generated catch block
		 			e1.printStackTrace();
		 		}
		 		
		 		
		 	}
		 });
		 SearchEmployee.setBounds(238, 52, 89, 23);
		 panel_1.add(SearchEmployee);
		 
		 DefaultTableModel model = new DefaultTableModel();
		 Object[] column = {"ID" ,"Employee ID" , "date", "Time_in", "Time_out"};
		 Object[] row = new Object[0];
		 model.setColumnIdentifiers(column);
		 table_1.setModel(model);
		 
		 DefaultTableModel model2 = new DefaultTableModel();
		 Object[] column2 = {"ID" ,"Employee ID" , "date", "Time_in", "Time_out"};
		 Object[] row2 = new Object[0];
		 model.setColumnIdentifiers(column);
		 table_2.setModel(model2);
		 
		 final JComboBox txts_id = new JComboBox();
		 txts_id.setBounds(266, 20, 66, 22);
		 panel_2.add(txts_id);
		 
		 JButton btnNewButton_1 = new JButton("Search ID");
		 btnNewButton_1.setBounds(256, 45, 89, 23);
		 btnNewButton_1.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		try {
		 			String s_id = txts_id.getSelectedItem().toString();
		 			pst = con.prepareStatement("SELECT * FROM employee_tbl WHERE id=? ");
					pst.setString(1,s_id);
					rs=pst.executeQuery();
					
					if(rs.next() == true) {
//						txt_empid
						
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
		 		
		 		
		 	}
		 });
		 panel_2.add(btnNewButton_1);
		 
		 JSeparator separator = new JSeparator();
		 separator.setBounds(10, 167, 187, 2);
		 panel_2.add(separator);
		 
		 JPanel panel_3 = new JPanel();
		 panel_3.setBounds(10, 170, 208, 180);
		 panel_3.setBorder(new CompoundBorder());
		 panel_2.add(panel_3);
		 panel_3.setLayout(null);
		 
		 JLabel Deductions = new JLabel("Deductions");
		 Deductions.setBounds(10, 11, 60, 14);
		 panel_3.add(Deductions);
		 
		 JLabel lblNewLabel_13 = new JLabel("0.00");
		 lblNewLabel_13.setBounds(131, 62, 46, 14);
		 panel_3.add(lblNewLabel_13);
		 
		 JLabel lblNewLabel_14 = new JLabel("0.00");
		 lblNewLabel_14.setBounds(131, 87, 46, 14);
		 panel_3.add(lblNewLabel_14);
		 
		 JLabel lblNewLabel_15 = new JLabel("0.00");
		 lblNewLabel_15.setBounds(131, 112, 46, 14);
		 panel_3.add(lblNewLabel_15);
		 
		 JLabel lblNewLabel_10 = new JLabel("SSS");
		 lblNewLabel_10.setBounds(10, 62, 60, 14);
		 panel_3.add(lblNewLabel_10);
		 
		 JLabel lblNewLabel_11 = new JLabel("Philhealth");
		 lblNewLabel_11.setBounds(10, 87, 60, 14);
		 panel_3.add(lblNewLabel_11);
		 
		 JLabel lblNewLabel_12 = new JLabel("PAG-IBIG");
		 lblNewLabel_12.setBounds(10, 112, 60, 14);
		 panel_3.add(lblNewLabel_12);
		 
		 JLabel lblNewLabel_9 = new JLabel("Withholding Tax");
		 lblNewLabel_9.setBounds(10, 37, 96, 14);
		 panel_3.add(lblNewLabel_9);
		 
		 JSeparator separator_1 = new JSeparator();
		 separator_1.setBounds(10, 137, 163, 2);
		 panel_3.add(separator_1);
		 
		 JSeparator separator_1_1 = new JSeparator();
		 separator_1_1.setBounds(10, 167, 163, 2);
		 panel_3.add(separator_1_1);
		 
		 JLabel lblNewLabel_16 = new JLabel("Total Deductions");
		 lblNewLabel_16.setBounds(10, 142, 80, 14);
		 panel_3.add(lblNewLabel_16);
		 
		 JLabel lblNewLabel_17 = new JLabel("0.00");
		 lblNewLabel_17.setBounds(131, 142, 46, 14);
		 panel_3.add(lblNewLabel_17);
		 
		 JLabel lblNewLabel_13_1 = new JLabel("0.00");
		 lblNewLabel_13_1.setBounds(127, 37, 46, 14);
		 panel_3.add(lblNewLabel_13_1);
		 
		 JLabel lblNewLabel_18 = new JLabel("Net Pay");
		 lblNewLabel_18.setBounds(24, 347, 46, 14);
		 panel_2.add(lblNewLabel_18);
		 
		 JLabel lblNewLabel_19 = new JLabel("0.00");
		 lblNewLabel_19.setBounds(144, 347, 46, 14);
		 panel_2.add(lblNewLabel_19);
		 
		 JButton btnNewButton_2 = new JButton("Generate PDF");
		 btnNewButton_2.setBounds(24, 390, 116, 23);
		 panel_2.add(btnNewButton_2);
		 
		 
		 JLabel lblNewLabel_3 = new JLabel("ID");
		 lblNewLabel_3.setBounds(10, 34, 46, 14);
		 panel_1.add(lblNewLabel_3);
		 
		 txtid = new JTextField();
		 txtid.setBounds(112, 25, 86, 20);
		 panel_1.add(txtid);
		 txtid.setColumns(10);
	}
}
