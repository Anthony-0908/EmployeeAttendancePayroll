import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JButton;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JRadioButtonMenuItem;
import java.awt.Panel;
import java.awt.ScrollPane;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class EmployeeCRUD {

	private JFrame frame;
	private JTextField txtempID;
	private JTextField txtFirstname;
	private JTextField txtLastname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeCRUD window = new EmployeeCRUD();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	
	
	public EmployeeCRUD() throws SQLException {
		initialize();
		
		Connect();
		fetch();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table_1;
	private JTextField txtidSearch;
	
	public void Connect() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/employeeandpayroll","root","");
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(EmployeeCRUD.class.getName()).log(Level.SEVERE, null ,ex);
		}
	
		
	}
	
	public void fetch()
	{
		try {
			
			pst = con.prepareStatement("select * from employee_tbl");
			rs = pst.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
			
			
			
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
		frame.setBounds(100, 100, 653, 410);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String emp_id = txtempID.getText();
					String firstname = txtFirstname.getText();
					String lastname = txtLastname.getText();
					
					pst = con.prepareStatement("INSERT INTO employee_tbl (Emp_id, Firstname,Lastname) VALUES (?, ? ,?)");
					pst.setString(1,emp_id);
					pst.setString(2,firstname);
					pst.setString(3,lastname);
					fetch();
					int k = pst.executeUpdate();
					
					if(k == 1)
					{
						JOptionPane.showMessageDialog(null, "record added");
						txtempID.setText("");
						txtFirstname.setText("");
						txtLastname.setText("");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "record not added");
						
					}
					
				} catch (SQLException ex) {
					Logger.getLogger(EmployeeCRUD.class.getName()).log(Level.SEVERE,null,ex);
				}
			}
		});
		btnAdd.setBounds(20, 97, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					
					String emp_id , firstname, lastname, update_id;
					
					emp_id = txtempID.getText();
					firstname = txtFirstname.getText();
					lastname = txtLastname.getText();
					update_id = txtidSearch.getText();
					
					pst = con.prepareStatement("UPDATE employee_tbl SET Emp_id = ? , Firstname = ? , Lastname = ? WHERE id = ?");
					pst.setString(1,emp_id);
					pst.setString(2,firstname);
					pst.setString(3,lastname);
					pst.setString(4, update_id);
				
					int k = pst.executeUpdate();
					
					if(k == 1)
					{
						JOptionPane.showMessageDialog(null, "record updated");
						txtempID.setText("");
						txtFirstname.setText("");
						txtLastname.setText("");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "record not updated");
						
					}
					
				} catch (SQLException ex) {
					Logger.getLogger(EmployeeCRUD.class.getName()).log(Level.SEVERE,null,ex);
				}
			}
			
		});
		btnUpdate.setBounds(119, 97, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(218, 97, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		JLabel lblNewLabel = new JLabel("EmployeeID");
		lblNewLabel.setBounds(10, 11, 64, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblFirstname = new JLabel("Firstname");
		lblFirstname.setBounds(10, 36, 74, 14);
		frame.getContentPane().add(lblFirstname);
		
		JLabel lblLastname = new JLabel("Lastname");
		lblLastname.setBounds(10, 59, 64, 14);
		frame.getContentPane().add(lblLastname);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(10, 87, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		txtempID = new JTextField();
		txtempID.setBounds(84, 8, 86, 20);
		frame.getContentPane().add(txtempID);
		txtempID.setColumns(10);
		
		txtFirstname = new JTextField();
		txtFirstname.setColumns(10);
		txtFirstname.setBounds(84, 33, 86, 20);
		frame.getContentPane().add(txtFirstname);
		
		txtLastname = new JTextField();
		txtLastname.setColumns(10);
		txtLastname.setBounds(84, 56, 86, 20);
		frame.getContentPane().add(txtLastname);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(73, 152, 368, 160);
		frame.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JLabel lblNewLabel_1 = new JLabel("Search ID");
		lblNewLabel_1.setBounds(384, 11, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtidSearch = new JTextField();
		txtidSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String id = txtidSearch.getText();
				try {
					pst = con.prepareStatement("select Emp_id , Firstname, Lastname FROM  employee_tbl WHERE id = ?");
					pst.setString(1 , id);
					rs = pst.executeQuery();
					
					
					if(rs.next()==true)
					{
						String emp_id = rs.getString(1);
						String firstname = rs.getString(2);
						String lastname = rs.getString(3);
						
						
						txtempID.setText(emp_id);
						txtFirstname.setText(firstname);
						txtLastname.setText(lastname);
						
					}
					else
					{
						txtempID.setText("");
						txtFirstname.setText("");
						txtLastname.setText("");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		txtidSearch.setBounds(440, 8, 86, 20);
		frame.getContentPane().add(txtidSearch);
		txtidSearch.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBounds(437, 32, 89, 23);
		frame.getContentPane().add(btnNewButton);
		DefaultTableModel model = new DefaultTableModel();
		Object[] column ={"ID", "Employee ID " , "Firstname" , "Lastname"};
		Object[] row = new Object[0];
		model.setColumnIdentifiers(column);
		
	}
}
