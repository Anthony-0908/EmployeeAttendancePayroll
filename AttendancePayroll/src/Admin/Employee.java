package Admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Employee {

	private JFrame frame;
	private JTable table;
	private JTextField txtID;
	private JTextField txtPassword;
	private JTextField txtFirstname;
	private JTextField txtLastname;
	private JComboBox<String> txtEID;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee window = new Employee();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	public void Connect() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/employeeandpayroll","root","");
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(EmployeeCRUD.class.getName()).log(Level.SEVERE, null ,ex);
		}
	
		
	}
	
	
	public void LoadEmployee() { 
		
		try {
			
			
			pst = con.prepareStatement("SELECT id FROM employee_tbl");
			rs = pst.executeQuery();
			txtEID.removeAllItems();
			while(rs.next()) {
				txtEID.addItem(rs.getString(1));
				
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
		
		
		
//		try {
//			pst = con.prepareStatement("SELECT * FROM employee_tbl");
//			rs = pst.executeQuery();
//			txtempID.removeAllItems();
//			while(rs.next()) {
//				txtempID.addItem(rs.getString(1));
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
	

	/**
	 * Create the application.
	 */
	public Employee() throws SQLException {
		initialize();
		Connect();
		fetch();
		LoadEmployee();
	}
	
	
	public void fetch()
	{
		try {
			
			pst = con.prepareStatement("SELECT * FROM employee_tbl");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
//			ResultSetMetaData rss = rs.getMetaData();
//			q = rss.getColumnCount();
//			
//			DefaultTableModel df =(DefaultTableModel)table.getModel();
//			df.setRowCount(0);
//			while(rs.next()) {
//				Vector<String> v2 = new Vector<String>();
//				for(int a = 1; a<=q; a++) {
//					v2.add(rs.getString("id"));
//					v2.add(rs.getString("Emp_id"));
//					v2.add(rs.getString("Password"));
//					v2.add(rs.getString("Firstname"));
//					
//					
//				}
//				df.addRow(v2);
//			}
//			
////			table.setModel(DbUtils.resultSetToTableModel(rs));
//			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 608, 447);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 214, 359, 183);
		frame.getContentPane().add(scrollPane);
		
		
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Employee ID");
		lblNewLabel.setBounds(21, 33, 66, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(21, 60, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Firstname");
		lblNewLabel_2.setBounds(21, 85, 59, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Lastname");
		lblNewLabel_3.setBounds(21, 110, 53, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		txtID = new JTextField();
		txtID.setBounds(97, 30, 141, 20);
		frame.getContentPane().add(txtID);
		txtID.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(97, 57, 141, 20);
		frame.getContentPane().add(txtPassword);
		
		txtFirstname = new JTextField();
		txtFirstname.setColumns(10);
		txtFirstname.setBounds(97, 85, 141, 20);
		frame.getContentPane().add(txtFirstname);
		
		txtLastname = new JTextField();
		txtLastname.setColumns(10);
		txtLastname.setBounds(97, 110, 141, 20);
		frame.getContentPane().add(txtLastname);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EmpID , Password, Firstname, Lastname;
				EmpID = txtID.getText();
				Password = txtPassword.getText();
				Firstname = txtFirstname.getText();
				Lastname = txtLastname.getText();
				
				try {
					pst = con.prepareStatement("INSERT INTO employee_tbl(Emp_id , Password, Firstname, Lastname) VALUES(?,? ,?,? )");
					pst.setString(1,EmpID);
					pst.setString(2,Password);
					pst.setString(3,Firstname);
					pst.setString(4,Lastname);
					
					int k = pst.executeUpdate();
					
					if(k==1) {
						JOptionPane.showMessageDialog(null, "REcord successfully added");
						txtID.setText("");
						txtPassword.setText("");
						txtFirstname.setText("");
						txtLastname.setText("");
						fetch();
						
					}else {
						JOptionPane.showMessageDialog(null,"Record is not added");
					
				}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
						
//				try {
//					pst = con.prepareStatement("INSERT INTO employee_tbl(Emp_id, Password ,Firstname, Lastname) VALUES (?, ?, ?, ? ");
//					
//					
//					int k = pst.executeUpdate();
//					
//					if(k==1) {
//						JOptionPane.showMessageDialog(null, "Record successfully added");

//
//					
//				} catch (SQLException e2) {
//					// TODO: handle exception
//				}
			}
		});
		btnAdd.setBounds(21, 170, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Emp_ID,EID , Password , Firstname, Lastname;
				EID = txtEID.getSelectedItem().toString();
				Emp_ID = txtID.getText();
				Password = txtPassword.getText();
				Firstname = txtFirstname.getText();
				Lastname = txtLastname.getText();
				
				
				try {
					pst = con.prepareStatement("UPDATE employee_tbl SET Emp_id =? , Password = ? , Firstname = ? , Lastname = ? WHERE id = ? ");
					pst.setString(1,Emp_ID);
					pst.setString(2,Password);
					pst.setString(3,Firstname);
					pst.setString(4, Lastname);
					pst.setString(5,EID);
					
					int k = pst.executeUpdate();
					if(k==1) {
						JOptionPane.showMessageDialog(null, "Record have been updated");
						txtID.setText("");
						txtPassword.setText("");
						txtFirstname.setText("");
						txtLastname.setText("");
						LoadEmployee();
						
					}else {
						JOptionPane.showMessageDialog(null,"No Record have been updated");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnUpdate.setBounds(120, 170, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(219, 170, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		JLabel lblNewLabel_4 = new JLabel("Employee ID");
		lblNewLabel_4.setBounds(308, 33, 82, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JButton btnSearch = new JButton("Search ID");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String eid = txtEID.getSelectedItem().toString();
					pst = con.prepareStatement("SELECT * FROM employee_tbl WHERE id=? ");
					pst.setString(1,eid);
					rs=pst.executeQuery();
					
					if(rs.next() == true) {
						txtID.setText(rs.getString(2));
						txtPassword.setText(rs.getString(3));
						txtFirstname.setText(rs.getString(4));
						txtLastname.setText(rs.getString(5));
						
					}else {
						JOptionPane.showMessageDialog(null, "No record found");
					}
					
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnSearch.setBounds(331, 60, 89, 23);
		frame.getContentPane().add(btnSearch);
		
		txtEID = new JComboBox();
		txtEID.setBounds(390, 29, 46, 22);
		frame.getContentPane().add(txtEID);
		DefaultTableModel model = new DefaultTableModel();
		Object[] column = {"ID", "Employee ID" , "Password" , "Firstname" , "Lastname"};
		Object[] row = new Object[0];
		model.setColumnIdentifiers(column);
	}
}
