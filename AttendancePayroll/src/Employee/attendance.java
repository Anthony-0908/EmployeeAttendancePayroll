package Employee;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Admin.EmployeeCRUD;

import java.awt.BorderLayout;
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

public class attendance {

	private JFrame frame;
	private JTextField emp_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					attendance window = new attendance();
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

	/**
	 * Create the application.
	 */
	public attendance() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton add = new JButton("New button");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add.setBounds(83, 119, 89, 23);
		frame.getContentPane().add(add);
		
		emp_id = new JTextField();
		emp_id.setBounds(86, 58, 86, 20);
		frame.getContentPane().add(emp_id);
		emp_id.setColumns(10);
	}
}
