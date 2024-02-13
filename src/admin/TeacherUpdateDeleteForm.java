package admin;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import applauncher.Main;
import util.DBUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The {@link TeacherUpdateDeleteForm} class creates
 * a form for updating/deleting a teacher record in the database.
 * It is accessed from the {@link TeacherSearchForm} class.
 */
public class TeacherUpdateDeleteForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField teacherIdTxt;
	private JTextField teacherFirstnameTxt;
	private JTextField teacherLastnameTxt;
	private String sql;
	private PreparedStatement ps;
	private ResultSet rs;
	private Connection connection;

	/**
	 * Create the frame.
	 */
	public TeacherUpdateDeleteForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				sql = "SELECT * FROM Teacher WHERE Lastname LIKE ?";
				try {
					connection = DBUtil.getConnection();
					ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ps.setString(1, Main.getTeacherSearchForm().getTeacherLastname() + "%");
					rs = ps.executeQuery();
					
					if(rs.next()) {
						teacherIdTxt.setText(Integer.toString(rs.getInt("ID")));
						teacherFirstnameTxt.setText(rs.getString("Firstname"));
						teacherLastnameTxt.setText(rs.getString("Lastname"));
					} else {
						JOptionPane.showMessageDialog(null, "Teacher(s) not found", "Teacher(s)", JOptionPane.WARNING_MESSAGE);
						Main.getTeacherUpdateDeleteForm().setVisible(false);
						Main.getTeacherSearchForm().setEnabled(true);
					}
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
				try {
					connection.close();
				} catch(SQLException e8) {
					e8.printStackTrace();
				}
			}
		});
		setBackground(new Color(255, 255, 240));
		setTitle("Update/Delete Teacher");
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 416, 246);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel teacherIdLbl = new JLabel("ID");
		teacherIdLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		teacherIdLbl.setForeground(new Color(128, 0, 0));
		teacherIdLbl.setBounds(71, 31, 70, 15);
		panel.add(teacherIdLbl);

		JLabel teacherFirstnameLbl = new JLabel("Firstname");
		teacherFirstnameLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		teacherFirstnameLbl.setForeground(new Color(128, 0, 0));
		teacherFirstnameLbl.setBounds(59, 58, 82, 15);
		panel.add(teacherFirstnameLbl);

		JLabel teacherLastnameLbl = new JLabel("Lastname");
		teacherLastnameLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		teacherLastnameLbl.setForeground(new Color(128, 0, 0));
		teacherLastnameLbl.setBounds(71, 85, 70, 15);
		panel.add(teacherLastnameLbl);

		teacherIdTxt = new JTextField();
		teacherIdTxt.setBackground(new Color(255, 255, 224));
		teacherIdTxt.setEditable(false);
		teacherIdTxt.setBounds(159, 29, 57, 19);
		panel.add(teacherIdTxt);
		teacherIdTxt.setColumns(10);

		teacherFirstnameTxt = new JTextField();
		teacherFirstnameTxt.setBounds(159, 56, 114, 19);
		panel.add(teacherFirstnameTxt);
		teacherFirstnameTxt.setColumns(10);

		teacherLastnameTxt = new JTextField();
		teacherLastnameTxt.setBounds(159, 83, 114, 19);
		panel.add(teacherLastnameTxt);
		teacherLastnameTxt.setColumns(10);

		JButton firstTeacherRecordBtn = new JButton("");
		firstTeacherRecordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(rs.first()) {
						teacherIdTxt.setText(Integer.toString(rs.getInt("ID")));
						teacherFirstnameTxt.setText(rs.getString("Firstname"));
						teacherLastnameTxt.setText(rs.getString("Lastname"));
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		firstTeacherRecordBtn.setIcon(new ImageIcon(TeacherUpdateDeleteForm.class.getResource("/resources/First record.png")));
		firstTeacherRecordBtn.setBounds(106, 125, 39, 25);
		panel.add(firstTeacherRecordBtn);

		JButton prevTeacherRecordBtn = new JButton("");
		prevTeacherRecordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(rs.previous()) {
						teacherIdTxt.setText(Integer.toString(rs.getInt("ID")));
						teacherFirstnameTxt.setText(rs.getString("Firstname"));
						teacherLastnameTxt.setText(rs.getString("Lastname"));
					} else {
						rs.first();
					}
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
			}
		});
		prevTeacherRecordBtn.setIcon(new ImageIcon(TeacherUpdateDeleteForm.class.getResource("/resources/Previous_record.png")));
		prevTeacherRecordBtn.setBounds(158, 125, 39, 25);
		panel.add(prevTeacherRecordBtn);

		JButton nextTeacherRecordBtn = new JButton("");
		nextTeacherRecordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(rs.next()) {
						teacherIdTxt.setText(Integer.toString(rs.getInt("ID")));
						teacherFirstnameTxt.setText(rs.getString("Firstname"));
						teacherLastnameTxt.setText(rs.getString("Lastname"));
					} else {
						rs.last();
					}
				} catch (SQLException e4) {
					e4.printStackTrace();
				}
			}
		});
		nextTeacherRecordBtn.setIcon(new ImageIcon(TeacherUpdateDeleteForm.class.getResource("/resources/Next_track.png")));
		nextTeacherRecordBtn.setBounds(209, 125, 39, 25);
		panel.add(nextTeacherRecordBtn);

		JButton lastTeacherRecordBtn = new JButton("");
		lastTeacherRecordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(rs.last()) {
						teacherIdTxt.setText(Integer.toString(rs.getInt("ID")));
						teacherFirstnameTxt.setText(rs.getString("Firstname"));
						teacherLastnameTxt.setText(rs.getString("Lastname"));
					}
				} catch(SQLException e5) {
					e5.printStackTrace();
				}
			}
		});
		lastTeacherRecordBtn.setIcon(new ImageIcon(TeacherUpdateDeleteForm.class.getResource("/resources/Last_Record.png")));
		lastTeacherRecordBtn.setBounds(260, 125, 39, 25);
		panel.add(lastTeacherRecordBtn);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sql = "UPDATE Teacher SET Firstname = ?, Lastname = ? WHERE ID = ?";
				try (Connection connection = DBUtil.getConnection();
						PreparedStatement ps = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)){
					
					String firstname = teacherFirstnameTxt.getText().trim();
					String lastname = teacherLastnameTxt.getText().trim();
					String id = teacherIdTxt.getText().trim();
					
					if(firstname.equals("") || lastname.equals("") ) {
						JOptionPane.showMessageDialog(null, "Empty Firstname / Lastname", "Input Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					ps.setString(1, firstname);
					ps.setString(2, lastname);
					ps.setString(3, id);
					
					int n = ps.executeUpdate();
					
					if(n > 0) {
						JOptionPane.showMessageDialog(null, "Update Successful: " + n + " row(s) affected", "Update", JOptionPane.INFORMATION_MESSAGE);
					} 
					
				} catch(SQLException e6) {
					e6.printStackTrace();
				}
			}
		});
		btnUpdate.setForeground(new Color(0, 0, 255));
		btnUpdate.setBounds(26, 183, 117, 25);
		panel.add(btnUpdate);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sql = "DELETE FROM Teacher WHERE ID = ?";
				
				try (Connection connection = DBUtil.getConnection();
						PreparedStatement ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
					int id = Integer.parseInt(teacherIdTxt.getText());
					ps.setInt(1, id);
					
					int response = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning", JOptionPane.YES_NO_OPTION);
					if(response == JOptionPane.YES_OPTION) {
						int n = ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "Deletion Successful: " + n + " row(s) affected", "Delete", JOptionPane.INFORMATION_MESSAGE);
					} else {
						return;
					}
				} catch(SQLException e7) {
					e7.printStackTrace();
				}
			}
		});
		btnDelete.setForeground(new Color(0, 0, 255));
		btnDelete.setBounds(150, 183, 117, 25);
		panel.add(btnDelete);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection.close();
				} catch(SQLException e9) {
					e9.printStackTrace();
					}
				Main.getTeacherUpdateDeleteForm().setVisible(false);
				Main.getTeacherSearchForm().setEnabled(true);
			}
		});
		btnClose.setForeground(new Color(0, 0, 255));
		btnClose.setBounds(275, 183, 117, 25);
		panel.add(btnClose);
	}

}
