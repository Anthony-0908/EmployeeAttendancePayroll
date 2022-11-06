package Employee;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import Admin.EmployeeCRUD;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeeLogin {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeLogin window = new EmployeeLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EmployeeLogin() {
		initialize();
	}
	
	
	Connection con;
	private JTextField txtEmpID;
	private JPasswordField txtPassword;
	
	public void Connect() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/employeeandpayroll","root","");
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(EmployeeCRUD.class.getName()).log(Level.SEVERE, null ,ex);
			
			
		}
		
		
		
		
		
	
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtEmpID = new JTextField();
		txtEmpID.setBounds(162, 44, 86, 20);
		frame.getContentPane().add(txtEmpID);
		txtEmpID.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("EmployeeID");
		lblNewLabel.setBounds(56, 47, 86, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(69, 99, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Login Code
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost/employeeandpayroll","root","");
					String ID = txtEmpID.getText();
					String Password = txtPassword.getText();
					
					Statement stm = con.createStatement();
					String sql = "Select * from employee_tbl where Emp_id='"+ID+"' and Password='"+Password+"'";
					ResultSet rs = stm.executeQuery(sql);
					
					if(rs.next() == true) {
						dispose();
						
						
					}
					
					
					
					
					
					
				} catch (Exception e1) {
					// TODO: handle exception
				}
			}
		});
		btnLogin.setBounds(159, 173, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(162, 96, 86, 20);
		frame.getContentPane().add(txtPassword);
		
		
	}

	private void dispose() {
		// TODO Auto-generated method stub
		
	}
}
