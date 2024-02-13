package admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import applauncher.Main;
import util.DBUtil;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

/**
 * The {@link UserDeleteForm} class creates a form
 * for deleting a user record from the database.
 * It is accessed from the {@link UserSearchForm} class.
 */
public class UserDeleteForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userIdTxt;
	private JTextField usernameTxt;
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public UserDeleteForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				sql = "SELECT * FROM User WHERE Username LIKE ?";
				try {
					connection = DBUtil.getConnection();
					ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ps.setString(1, Main.getUserSearchForm().getUsername() + "%");
					rs = ps.executeQuery();
					
					if(rs.next()) {
						userIdTxt.setText(Integer.toString(rs.getInt("ID")));
						usernameTxt.setText(rs.getString("Username"));
						passwordField.setText(rs.getString("Password"));
					} else {
						JOptionPane.showMessageDialog(null, "User(s) not found", "User(s)", JOptionPane.WARNING_MESSAGE);
						Main.getUserDeleteForm().setVisible(false);
						Main.getUserSearchForm().setEnabled(true);
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
				try {
					connection.close();
				} catch (SQLException e6) {
					e6.printStackTrace();
				}
			}
		});
		setBackground(new Color(255, 255, 240));
		setTitle("Update/Delete User");
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
		
		JLabel userIdLbl = new JLabel("ID");
		userIdLbl.setForeground(new Color(128, 0, 0));
		userIdLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		userIdLbl.setBounds(115, 41, 70, 15);
		panel.add(userIdLbl);
		
		JLabel usernameLbl = new JLabel("Username");
		usernameLbl.setForeground(new Color(128, 0, 0));
		usernameLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		usernameLbl.setBounds(101, 68, 84, 15);
		panel.add(usernameLbl);
		
		JLabel passwordLbl = new JLabel("Password");
		passwordLbl.setForeground(new Color(128, 0, 0));
		passwordLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		passwordLbl.setBounds(115, 95, 70, 15);
		panel.add(passwordLbl);
		
		userIdTxt = new JTextField();
		userIdTxt.setBackground(new Color(255, 255, 224));
		userIdTxt.setEditable(false);
		userIdTxt.setBounds(203, 39, 57, 19);
		panel.add(userIdTxt);
		userIdTxt.setColumns(10);
		
		usernameTxt = new JTextField();
		usernameTxt.setBackground(new Color(255, 255, 224));
		usernameTxt.setEditable(false);
		usernameTxt.setBounds(203, 66, 114, 19);
		panel.add(usernameTxt);
		usernameTxt.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sql = "DELETE FROM User WHERE ID = ?";
				
				try (Connection connection = DBUtil.getConnection();
						PreparedStatement ps = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
					
					int id = Integer.parseInt(userIdTxt.getText());
					ps.setInt(1, id);
					
					int response= JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning", JOptionPane.YES_NO_OPTION);
					if(response == JOptionPane.YES_OPTION) {
						int n = ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "Deletion Successful: " + n + " row(s) affected", "Delete", JOptionPane.INFORMATION_MESSAGE);
					} 
					
				} catch (SQLException e7) {
					e7.printStackTrace();
				}
			}
		});
		btnDelete.setForeground(new Color(0, 0, 255));
		btnDelete.setBounds(88, 184, 117, 25);
		panel.add(btnDelete);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection.close();
				} catch(SQLException e8) {
					e8.printStackTrace();
				}
				Main.getUserDeleteForm().setVisible(false);
				Main.getUserSearchForm().setEnabled(true);
			}
		});
		btnClose.setForeground(new Color(0, 0, 255));
		btnClose.setBounds(209, 184, 117, 25);
		panel.add(btnClose);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(255, 255, 224));
		passwordField.setEditable(false);
		passwordField.setBounds(203, 93, 114, 19);
		panel.add(passwordField);
		
		JButton firstUserRecordBtn = new JButton("");
		firstUserRecordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(rs.first()) {
						userIdTxt.setText(Integer.toString(rs.getInt("ID")));
						usernameTxt.setText(rs.getString("Username"));
						passwordField.setText(rs.getString("Password"));
					}
				} catch(SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		firstUserRecordBtn.setIcon(new ImageIcon(UserDeleteForm.class.getResource("/resources/First record.png")));
		firstUserRecordBtn.setBounds(120, 136, 39, 25);
		panel.add(firstUserRecordBtn);
		
		JButton prevUserRecordBtn = new JButton("");
		prevUserRecordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(rs.previous()) {
						userIdTxt.setText(Integer.toString(rs.getInt("ID")));
						usernameTxt.setText(rs.getString("Username"));
						passwordField.setText(rs.getString("Password"));
					} else {
						rs.first();
					}
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
			}
		});
		prevUserRecordBtn.setIcon(new ImageIcon(UserDeleteForm.class.getResource("/resources/Previous_record.png")));
		prevUserRecordBtn.setBounds(174, 136, 39, 25);
		panel.add(prevUserRecordBtn);
		
		JButton nextUserRecordBtn = new JButton("");
		nextUserRecordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(rs.next()) {
						userIdTxt.setText(Integer.toString(rs.getInt("ID")));
						usernameTxt.setText(rs.getString("Username"));
						passwordField.setText(rs.getString("Password"));
					} else {
						rs.last();
					}
				} catch (SQLException e4) {
					e4.printStackTrace();
				}
			}
		});
		nextUserRecordBtn.setIcon(new ImageIcon(UserDeleteForm.class.getResource("/resources/Next_track.png")));
		nextUserRecordBtn.setBounds(225, 136, 39, 25);
		panel.add(nextUserRecordBtn);
		
		JButton lastUserRecordBtn = new JButton("");
		lastUserRecordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(rs.last()) {
						userIdTxt.setText(Integer.toString(rs.getInt("ID")));
						usernameTxt.setText(rs.getString("Username"));
						passwordField.setText(rs.getString("Password"));
					}
				} catch (SQLException e5) {
					e5.printStackTrace();
				}
			} 
		});
		lastUserRecordBtn.setIcon(new ImageIcon(UserDeleteForm.class.getResource("/resources/Last_Record.png")));
		lastUserRecordBtn.setBounds(278, 136, 39, 25);
		panel.add(lastUserRecordBtn);
	}
}
