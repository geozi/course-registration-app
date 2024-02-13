package applauncher;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import security.SecUtil;
import util.DBUtil;

import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.awt.event.ActionEvent;

/**
 * The {@link LoginRegisterForm} class creates 
 * the login form for entering into the system.
 * It, also, provides the possibility for registering
 * a new username and password for a student already 
 * in the database.
 */
public class LoginRegisterForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField loginUsernameTxt;
	private JPasswordField loginPasswordField;
	private JTextField newUsernameTxt;
	private JPasswordField newPasswordField;
	private JPasswordField confirmPasswordField;
	private JTextField userRegNumberTxt;
	private PreparedStatement ps;
	private Statement statement;
	private Connection connection;
	private ResultSet rs;
	private String sql;
	private String regNumber;
	private String username;
	private String password;
	private String confirmPassword;
	private int userRowCount;
	private int studentRowCount;
	private String userId;


	/**
	 * Create the frame.
	 */
	public LoginRegisterForm() {
		setBackground(new Color(0, 128, 128));
		setTitle("Login/Register");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 12, 416, 246);
		contentPane.add(tabbedPane);
		
		JPanel loginPanel = new JPanel();
		tabbedPane.addTab("Login", null, loginPanel, null);
		loginPanel.setLayout(null);
		
		JLabel loginUsernameLbl = new JLabel("Username");
		loginUsernameLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		loginUsernameLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		loginUsernameLbl.setForeground(new Color(128, 0, 0));
		loginUsernameLbl.setBounds(81, 51, 79, 15);
		loginPanel.add(loginUsernameLbl);
		
		JLabel loginPasswordLbl = new JLabel("Password");
		loginPasswordLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		loginPasswordLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		loginPasswordLbl.setForeground(new Color(128, 0, 0));
		loginPasswordLbl.setBounds(81, 94, 79, 15);
		loginPanel.add(loginPasswordLbl);
		
		loginUsernameTxt = new JTextField();
		loginUsernameTxt.setBounds(193, 46, 119, 26);
		loginPanel.add(loginUsernameTxt);
		loginUsernameTxt.setColumns(10);
		
		loginPasswordField = new JPasswordField();
		loginPasswordField.setBounds(193, 89, 119, 26);
		loginPanel.add(loginPasswordField);
		
		JButton loginSubmitBtn = new JButton("Submit");
		loginSubmitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HashMap<String, String[]> userMap = new HashMap<>();
				username = loginUsernameTxt.getText().trim();
				password = String.valueOf(loginPasswordField.getPassword()).trim();
				
				if(username.equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(null, "Username / Password empty", "Input Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					sql = "SELECT * FROM User";
					connection = DBUtil.getConnection();
					statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					rs = statement.executeQuery(sql);
					
					while(rs.next()) {
						userMap.put(rs.getString("Username"), new String[]{Integer.toString(rs.getInt("ID")), rs.getString("Password")});
					}
					
					if(userMap.containsKey(username) && SecUtil.checkPassword(password, userMap.get(username)[1])) {
						JOptionPane.showMessageDialog(null, "Login Successful!", "Login", JOptionPane.INFORMATION_MESSAGE);
						userId = userMap.get(username)[0];
						Main.getLoginRegisterForm().setVisible(false);
						Main.getUserMenu().setVisible(true);
					} else if (username.matches("[aA]dmin") && password.equals("randomPassword")) {
						JOptionPane.showMessageDialog(null, "Login Successful!", "Login", JOptionPane.INFORMATION_MESSAGE);
						Main.getLoginRegisterForm().setVisible(false);
						Main.getAdminMenu().setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Username / Password not found", "Input Error", JOptionPane.ERROR_MESSAGE);
					}
					
					DBUtil.closeConnection();
					
				} catch (SQLException e2) {
					e2.printStackTrace();
					DBUtil.closeConnection();
				}
			}
		});
		loginSubmitBtn.setForeground(new Color(0, 0, 255));
		loginSubmitBtn.setBounds(140, 182, 117, 25);
		loginPanel.add(loginSubmitBtn);
		
		JPanel registerPanel = new JPanel();
		tabbedPane.addTab("Register", null, registerPanel, null);
		registerPanel.setLayout(null);
		
		JLabel newUsernameLbl = new JLabel("Username");
		newUsernameLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		newUsernameLbl.setForeground(new Color(165, 42, 42));
		newUsernameLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		newUsernameLbl.setBounds(70, 59, 107, 15);
		registerPanel.add(newUsernameLbl);
		
		JLabel newPasswordLbl = new JLabel("Password");
		newPasswordLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		newPasswordLbl.setForeground(new Color(165, 42, 42));
		newPasswordLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		newPasswordLbl.setBounds(77, 97, 100, 15);
		registerPanel.add(newPasswordLbl);
		
		JLabel confirmPasswordLbl = new JLabel("Confirm Password");
		confirmPasswordLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		confirmPasswordLbl.setForeground(new Color(165, 42, 42));
		confirmPasswordLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		confirmPasswordLbl.setBounds(19, 139, 158, 15);
		registerPanel.add(confirmPasswordLbl);
		
		newUsernameTxt = new JTextField();
		newUsernameTxt.setBounds(195, 54, 119, 26);
		registerPanel.add(newUsernameTxt);
		newUsernameTxt.setColumns(10);
		
		newPasswordField = new JPasswordField();
		newPasswordField.setBounds(195, 92, 119, 26);
		registerPanel.add(newPasswordField);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(195, 134, 119, 26);
		registerPanel.add(confirmPasswordField);
		
		JButton regBtn = new JButton("Submit");
		regBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				regNumber = userRegNumberTxt.getText().trim();
				username = newUsernameTxt.getText().trim();
				password = String.valueOf(newPasswordField.getPassword()).trim();
				confirmPassword = String.valueOf(confirmPasswordField.getPassword()).trim();
				userRowCount = 0;
				studentRowCount = 0;
				
				if(regNumber.equals("") || username.equals("") || password.equals("") || confirmPassword.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill Registration Number / Username / Password", "Basic info", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(username.length() <= 2 || confirmPassword.length() < 8) {
					JOptionPane.showMessageDialog(null, "Username at least 3 characters / Password at least 8 characters", "Basic info", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(!password.equals(confirmPassword)) {
					JOptionPane.showMessageDialog(null, "Confirmation password not the same", "Basic info", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					HashMap<String, Integer> studentMap = new HashMap<>();
					HashMap<String, Integer> userMap = new HashMap<>();
					connection = DBUtil.getConnection();
					
					sql = "SELECT ID, Username FROM User";
					statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					rs = statement.executeQuery(sql);
					
					while(rs.next()) {
						userMap.put(rs.getString("Username"), rs.getInt("ID"));
					}
					
					if(userMap.containsKey(username)) {
						JOptionPane.showMessageDialog(null, "Username is not available", "Register", JOptionPane.ERROR_MESSAGE);
						DBUtil.closeConnection();
						return;
					}
					
					sql = "SELECT * FROM Student";
					statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					rs = statement.executeQuery(sql);
					
					while(rs.next()) {
						studentMap.put(rs.getString("RegNumber"), rs.getInt("ID"));
					}
					
					if(studentMap.containsKey(regNumber)) {
						sql = "INSERT INTO User (Username, Password) VALUES (?,?)";
						ps = connection.prepareStatement(sql);
						ps.setString(1, username);
						ps.setString(2,  SecUtil.hashPassword(password));
						userRowCount = ps.executeUpdate();
						
						sql = "UPDATE Student SET User_ID = LAST_INSERT_ID() WHERE ID = ?";
						ps = connection.prepareStatement(sql);
						ps.setString(1, studentMap.get(regNumber).toString());
						studentRowCount = ps.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "Addition Successful: " + userRowCount + " row(s) affected in the User table and " + studentRowCount + " row(s) in the Student table", "Register", JOptionPane.INFORMATION_MESSAGE);
						DBUtil.closeConnection();
					} else {
						JOptionPane.showMessageDialog(null, "Registration Number not found", "Input Error", JOptionPane.ERROR_MESSAGE);
						DBUtil.closeConnection();
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
					DBUtil.closeConnection();
				}
			}
		});
		regBtn.setForeground(new Color(0, 0, 255));
		regBtn.setBounds(135, 182, 117, 25);
		registerPanel.add(regBtn);
		
		JLabel userRegNumberLbl = new JLabel("Registration Number");
		userRegNumberLbl.setForeground(new Color(165, 42, 42));
		userRegNumberLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		userRegNumberLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		userRegNumberLbl.setBounds(19, 21, 158, 15);
		registerPanel.add(userRegNumberLbl);
		
		userRegNumberTxt = new JTextField();
		userRegNumberTxt.setColumns(10);
		userRegNumberTxt.setBounds(195, 16, 119, 26);
		registerPanel.add(userRegNumberTxt);
	}

	public String getUserId() {
		return userId;
	}
	
	
}
