package Admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class AdminLogin {

	private JFrame frame;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin window = new AdminLogin();
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
	public AdminLogin() {
		initialize();
		
		
	}
	
	Connection con;
	
	public void Connect() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/employeeandpayroll","root","");
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null ,ex);
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
		
		JLabel lblNewLabel = new JLabel("Admin Login");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblNewLabel.setBounds(172, 11, 101, 31);
		frame.getContentPane().add(lblNewLabel);
		
		username = new JTextField();
		username.setBounds(144, 71, 168, 20);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(88, 74, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setBounds(88, 126, 46, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		password = new JPasswordField();
		password.setBounds(144, 123, 168, 20);
		frame.getContentPane().add(password);
		
		final JButton btnLoginButton = new JButton("Login");
		btnLoginButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				
				//login code
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost/employeeandpayroll","root","");
					//Database name
					//mysql user
					//mysql password
					String Username = username.getText();
					String Password = password.getText();
					
					
					Statement stm = con.createStatement();
					String sql = "Select * from admin where username='"+Username+"' and password='"+Password+"'";
					ResultSet rs = stm.executeQuery(sql);
					if(rs.next() == true) {
						dispose();
						Home home = new Home();
//						JOptionPane.showMessageDialog(null, "Username Exist");
						home.show();
					
					}else if(((CharSequence) rs).isEmpty()) {
						JOptionPane.showMessageDialog(null, "Username and password is empty");
					}
					else {
						JOptionPane.showMessageDialog(btnLoginButton, this, "username and password is wrong", 0);
						username.setText("");
						password.setText("");
					}
					
					
				}catch(Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		btnLoginButton.setBounds(113, 193, 89, 23);
		frame.getContentPane().add(btnLoginButton);
		
		JButton btnResetButton = new JButton("Reset");
		btnResetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username.setText("");
				password.setText("");
				
			}
		});
		btnResetButton.setBounds(227, 193, 89, 23);
		frame.getContentPane().add(btnResetButton);
	}

	protected void dispose() {
		// TODO Auto-generated method stub
		try {
			pst = con.prepareStatement("INSERT INTO attendance(emp_id,Date,Time_elapsed) VALUES(?,?,?)");
			pst.setString(1,User);
			pst.setDate(2,sqlDate);
			pst.setTime(3,sqlTime);
			JOptionPane.showMessageDialog(null, "You have now clocked in");
			
		} catch (Exception e2) {
			// TODO: handle exception
			int k = pst.executeUpdate();
			
			if(k==1) {
				JOptionPane.showMessageDialog(null, "Attendance Recorded");
				txt_empID.setText("");
				
		}
	}
}
