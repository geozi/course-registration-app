package user;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import applauncher.Main;
import security.SecUtil;
import util.DBUtil;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

/**
 * The {@link UpdatePasswordForm} class creates
 * a form for updating the password of a student
 * user.
 * It is accessed from the {@link UserMenu} class.
 */
public class UpdatePasswordForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField updatePasswordField;
	private String newPassword;
	private String sql;
	private PreparedStatement ps;
	private Connection connection;

	/**
	 * Create the frame.
	 */
	public UpdatePasswordForm() {
		setBackground(new Color(255, 255, 240));
		setTitle("Update Password");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 416, 246);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel updatePasswordLbl = new JLabel("New Password");
		updatePasswordLbl.setForeground(new Color(128, 0, 0));
		updatePasswordLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		updatePasswordLbl.setBounds(160, 45, 115, 15);
		panel.add(updatePasswordLbl);
		
		updatePasswordField = new JPasswordField();
		updatePasswordField.setBounds(127, 75, 171, 31);
		panel.add(updatePasswordField);
		
		JButton updatePasswordBtn = new JButton("Update");
		updatePasswordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sql = "INSERT INTO User (Password) VALUES (?) WHERE ID = ?";
				newPassword = updatePasswordField.getPassword().toString();
				
				if(newPassword.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter a password", "Input Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(newPassword.length() < 8) {
					JOptionPane.showMessageDialog(null, "Password at least 8 characters", "Basic info", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					connection = DBUtil.getConnection();
					ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ps.setString(1, SecUtil.hashPassword(newPassword));
					ps.setString(2, Main.getLoginRegisterForm().getUserId());
					int n = ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Update Successful: " + n + " row(s) affected", "Update", JOptionPane.INFORMATION_MESSAGE);
					
				} catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		updatePasswordBtn.setForeground(new Color(0, 0, 255));
		updatePasswordBtn.setBounds(85, 134, 117, 25);
		panel.add(updatePasswordBtn);
		
		JButton updatePasswordCloseBtn = new JButton("Close");
		updatePasswordCloseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBUtil.closeConnection();
				Main.getUpdatePasswordForm().setVisible(false);
				Main.getUserMenu().setEnabled(true);
			}
		});
		updatePasswordCloseBtn.setForeground(new Color(0, 0, 255));
		updatePasswordCloseBtn.setBounds(214, 134, 117, 25);
		panel.add(updatePasswordCloseBtn);
	}
}
