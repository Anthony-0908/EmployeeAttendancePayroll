package Employee;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.Timer;

import Admin.Employee;
import Admin.EmployeeCRUD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTabbedPane;

public class AttendanceRecord {

	private JFrame frame;
	private JTextField txt_empID;
	private JTextField txt_Password;
	private JLabel lbLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AttendanceRecord window = new AttendanceRecord();
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
			Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null ,ex);
		}
	
		
	}
	
	
	public void clock() {
		Thread clock = new Thread() {
			public void run() {
				try {
					while(true) {
						Date date = new Date();
						SimpleDateFormat formattedTime = new SimpleDateFormat("hh.mm.ss aa");
						String strTime = formattedTime.format(date);
						LocalDateTime obj = LocalDateTime.now();
						
//						DateTimeFormatter TimetObj = DateTimeFormatter.ofPattern("hh.mm aa");
						DateTimeFormatter DateObj = DateTimeFormatter.ofPattern("E, MM dd yyyy");
//						String formattedTime = obj.format(TimetObj);
						String formattedDate = obj.format(DateObj);
						
						
						txtClock.setText("Time"+ ":" + strTime);
						txtDate.setText("Date" + ":" + formattedDate);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		};
		
		clock.start();
		
//		Thread clock = new Thread() {
//			public void run() {
//				try {
//					while(true) {
//						Calendar cal = new GregorianCalendar();
//						int day = cal.get(Calendar.DAY_OF_MONTH);
//						int month = cal.get(Calendar.MONTH);
//						int year = cal.get(Calendar.YEAR);
//						
//						
//						int seconds = cal.get(Calendar.SECOND);
//						int minute = cal.get(Calendar.MINUTE);
//						int hour = cal.get(Calendar.HOUR);
//						
//					
//						txtClock.setText("Time" + hour + ":" + minute +":"+ seconds);
//					}
//					
//					
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			}
//		};
//		
//		clock.start();
		
	}
	

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public AttendanceRecord() throws SQLException {
		initialize();
		showDate();
//		showTime();
		clock();
		showDay();
		Connect();
	}
	
	public void CalculateTime(String User , String Password) {
		
		try {
			Statement stm = con.createStatement();
			String sql = "Select * id , emp_id, Date, Time_in, Time_out FROM attendance WHERE User = ?";
			
			ResultSet rs = stm.executeQuery(sql);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

		
	
	
//	
	
	SimpleDateFormat timeFormat;
	SimpleDateFormat dateFormat;
	SimpleDateFormat dayFormat;
	String time;
	String day;
	String date;
	private JTextField txtClock;
	private JTextField txtDate;
	private JLabel txttry;

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(400, 200, 570, 318);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		timeFormat = new SimpleDateFormat("hh:mm:ss a");
		dateFormat = new SimpleDateFormat("dd-MM-yyy");
		dayFormat = new SimpleDateFormat("EEEE");
		frame.getContentPane().setLayout(null);
		
		
		
		txt_empID = new JTextField();
		txt_empID.setBounds(240, 122, 86, 20);
		frame.getContentPane().add(txt_empID);
		txt_empID.setVisible(true);
		
		txt_Password = new JTextField();
		txt_Password.setBounds(240, 153, 86, 20);
		frame.getContentPane().add(txt_Password);
		txt_Password.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("EmployeeID");
		lblNewLabel.setBounds(138, 125, 86, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(138, 156, 78, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Time in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost/employeeandpayroll","root","");
					Date date = new Date();
					DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					Date fd = formatter(date);
					
					
					java.sql.Date sqlDate = new java.sql.Date(date.getTime());
					java.sql.Time sqlTime = new java.sql.Time(date.getTime());
					
					
					String User = txt_empID.getText();
					String Password = txt_Password.getText();
//					Time Clock = txtClock.getText();
//					String Date = txtDate.getText();
					
					
					
					
	
					if(User.equals("") && Password.equals("")) {
						JOptionPane.showMessageDialog(null, "there is no username and password");
					}
					else {
						Statement stm = con.createStatement();
						String sql = "Select * from employee_tbl where Emp_id='"+User+"' and password='"+Password+"'";
						ResultSet rs = stm.executeQuery(sql);
						
						
						if(rs.next() == true) {
							
							pst = con.prepareStatement("INSERT INTO attendance(emp_id,Date,Time_in,Time_out) VALUES(?,?,?,?)");
							
							pst.setString(1,User);
							pst.setDate(2,sqlDate);
							pst.setTime(3,sqlTime);
							pst.setInt(4,0);

							
							int k = pst.executeUpdate();
							
							JOptionPane.showMessageDialog(null, "You are clocked in");
						}
						else {
						JOptionPane.showMessageDialog(null, "Attendance not record");
					}
						
					}
						
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
				
				
				try {
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
					
			
					
						
						
				}

			private Date formatter(Date date) {
				// TODO Auto-generated method stub
				return null;
			}
			
			
		});
				
					
					
					
					
						
				
						
						
					
		
				
				
		
			
	
		btnNewButton.setBounds(155, 184, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Time out");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost/employeeandpayroll","root","");
					Date date = new Date();
					java.sql.Date sqlDate = new java.sql.Date(date.getTime());
					java.sql.Time sqlTime = new java.sql.Time(date.getTime());
					String User = txt_empID.getText();
					String Password = txt_Password.getText();
					
					
					if(User.equals("") && Password.equals("")) {
						JOptionPane.showMessageDialog(null, "there is no username and password");
					}
					else {
						Statement stm = con.createStatement();
						String sql = "Select * from employee_tbl where Emp_id='"+User+"' and password='"+Password+"'";
						ResultSet rs = stm.executeQuery(sql);
						
						if(rs.next() == true) {
							pst = con.prepareStatement("INSERT INTO attendance(emp_id,Date,Time_in, Time_out) VALUES(?,?,?,?)");
							
							pst.setString(1,User);
							pst.setDate(2,sqlDate);
							pst.setInt(3, 0);
							pst.setTime(4,sqlTime);
							

							
							int k = pst.executeUpdate();
							
							JOptionPane.showMessageDialog(null, "You are clocked out");
						}
						else {
						JOptionPane.showMessageDialog(null, "Attendance not record");
					}
						
					}
						
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
			
			}
		});
		btnNewButton_2.setBounds(298, 184, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		txtClock = new JTextField();
		txtClock.setBounds(117, 248, 127, 20);
		txtClock.setEditable(false);
		frame.getContentPane().add(txtClock);
		txtClock.setColumns(10);
		
		txtDate = new JTextField();
		txtDate.setBounds(259, 248, 127, 20);
		txtDate.setEditable(false);
		frame.getContentPane().add(txtDate);
		txtDate.setColumns(10);
		
		txttry = new JLabel();
		txttry.setBounds(10, 125, 86, 14);
		frame.getContentPane().add(txttry);
		frame.setVisible(true);
		

	}
	
	

	
	
	public void showDate() {
		Date d = new Date();
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		
	}
	
	public void showDay() {
		Date d = new Date();
		SimpleDateFormat s  = new SimpleDateFormat("EEEE");
	}
}
