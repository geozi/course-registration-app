package admin;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The {@link StudentUpdateDeleteForm} class creates a
 * form for updating and/or deleting a student record in
 * the database.
 * It is accessed from the {@link StudentSearchForm} class.
 */
public class StudentUpdateDeleteForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField studentIdTxt;
	private JTextField studentFirstnameTxt;
	private JTextField studentLastnameTxt;
	private JTextField regNumberTxt;
	private JTextField studentUserIdTxt;
	private String sql;
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	

	/**
	 * Create the frame.
	 */
	public StudentUpdateDeleteForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				sql = "SELECT * FROM Student WHERE Lastname LIKE ?";
				try{
					connection = DBUtil.getConnection();
					ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ps.setString(1, Main.getStudentSearchForm().getStudentLastname() +"%");
					rs = ps.executeQuery();
					
					if(rs.next()) {
						studentIdTxt.setText(rs.getString("ID"));
						studentFirstnameTxt.setText(rs.getString("Firstname"));
						studentLastnameTxt.setText(rs.getString("Lastname"));
						regNumberTxt.setText(rs.getString("RegNumber"));
						studentUserIdTxt.setText(rs.getString("User_ID"));
					} else {
						JOptionPane.showMessageDialog(null,  "No Student(s) found", "Student(s)", JOptionPane.WARNING_MESSAGE);
						Main.getStudentUpdateDeleteForm().setVisible(false);
						Main.getStudentSearchForm().setEnabled(true);
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
		setTitle("Update/Delete Student");
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 426, 244);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel studentIDLbl = new JLabel("ID");
		studentIDLbl.setForeground(new Color(165, 42, 42));
		studentIDLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		studentIDLbl.setBounds(125, 24, 22, 15);
		panel.add(studentIDLbl);

		JLabel studentFirstnameLbl = new JLabel("Firstname");
		studentFirstnameLbl.setForeground(new Color(165, 42, 42));
		studentFirstnameLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		studentFirstnameLbl.setBounds(66, 51, 81, 15);
		panel.add(studentFirstnameLbl);

		JLabel studentLastnameLbl = new JLabel("Lastname");
		studentLastnameLbl.setForeground(new Color(165, 42, 42));
		studentLastnameLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		studentLastnameLbl.setBounds(77, 78, 70, 15);
		panel.add(studentLastnameLbl);

		JLabel regNumberLbl = new JLabel("Registration Number");
		regNumberLbl.setForeground(new Color(165, 42, 42));
		regNumberLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		regNumberLbl.setBounds(0, 105, 147, 15);
		panel.add(regNumberLbl);

		JLabel studentUserIdLbl = new JLabel("UserID");
		studentUserIdLbl.setForeground(new Color(165, 42, 42));
		studentUserIdLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		studentUserIdLbl.setBounds(91, 132, 56, 15);
		panel.add(studentUserIdLbl);

		studentIdTxt = new JTextField();
		studentIdTxt.setBackground(new Color(255, 255, 224));
		studentIdTxt.setEditable(false);
		studentIdTxt.setBounds(165, 22, 56, 19);
		panel.add(studentIdTxt);
		studentIdTxt.setColumns(10);

		studentFirstnameTxt = new JTextField();
		studentFirstnameTxt.setBounds(165, 49, 114, 19);
		panel.add(studentFirstnameTxt);
		studentFirstnameTxt.setColumns(10);

		studentLastnameTxt = new JTextField();
		studentLastnameTxt.setBounds(165, 76, 114, 19);
		panel.add(studentLastnameTxt);
		studentLastnameTxt.setColumns(10);

		regNumberTxt = new JTextField();
		regNumberTxt.setBounds(165, 103, 114, 19);
		panel.add(regNumberTxt);
		regNumberTxt.setColumns(10);

		studentUserIdTxt = new JTextField();
		studentUserIdTxt.setBackground(new Color(255, 255, 224));
		studentUserIdTxt.setEditable(false);
		studentUserIdTxt.setBounds(165, 130, 56, 19);
		panel.add(studentUserIdTxt);
		studentUserIdTxt.setColumns(10);

		JButton firstStudentRecordBtn = new JButton("");
		firstStudentRecordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(rs.first()) {
						studentIdTxt.setText(Integer.toString(rs.getInt("ID")));
						studentFirstnameTxt.setText(rs.getString("Firstname"));
						studentLastnameTxt.setText(rs.getString("Lastname"));
						regNumberTxt.setText(rs.getString("RegNumber"));
						studentUserIdTxt.setText(Integer.toString(rs.getInt("User_ID")));
					}
				} catch(SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		firstStudentRecordBtn.setIcon(new ImageIcon(StudentUpdateDeleteForm.class.getResource("/resources/First record.png")));
		firstStudentRecordBtn.setBounds(80, 172, 49, 27);
		panel.add(firstStudentRecordBtn);

		JButton prevStudentRecordBtn = new JButton("");
		prevStudentRecordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(rs.previous()) {
						studentIdTxt.setText(Integer.toString(rs.getInt("ID")));
						studentFirstnameTxt.setText(rs.getString("Firstname"));
						studentLastnameTxt.setText(rs.getString("Lastname"));
						regNumberTxt.setText(rs.getString("RegNumber"));
						studentUserIdTxt.setText(Integer.toString(rs.getInt("User_ID")));
					} else {
						rs.first();
					}
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
			}
		});
		prevStudentRecordBtn.setIcon(new ImageIcon(StudentUpdateDeleteForm.class.getResource("/resources/Previous_record.png")));
		prevStudentRecordBtn.setBounds(141, 172, 49, 27);
		panel.add(prevStudentRecordBtn);

		JButton nextStudentRecordBtn = new JButton("");
		nextStudentRecordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(rs.next()) {
						studentIdTxt.setText(Integer.toString(rs.getInt("ID")));
						studentFirstnameTxt.setText(rs.getString("Firstname"));
						studentLastnameTxt.setText(rs.getString("Lastname"));
						regNumberTxt.setText(rs.getString("RegNumber"));
						studentUserIdTxt.setText(Integer.toString(rs.getInt("User_ID")));
					} else {
						rs.last();
					}
				} catch(SQLException e4) {
					e4.printStackTrace();
				}
			}
		});
		nextStudentRecordBtn.setIcon(new ImageIcon(StudentUpdateDeleteForm.class.getResource("/resources/Next_track.png")));
		nextStudentRecordBtn.setBounds(202, 172, 49, 27);
		panel.add(nextStudentRecordBtn);

		JButton lastStudentRecordBtn = new JButton("");
		lastStudentRecordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(rs.last()) {
						studentIdTxt.setText(Integer.toString(rs.getInt("ID")));
						studentFirstnameTxt.setText(rs.getString("Firstname"));
						studentLastnameTxt.setText(rs.getString("Lastname"));
						regNumberTxt.setText(rs.getString("RegNumber"));
						studentUserIdTxt.setText(Integer.toString(rs.getInt("User_ID")));
					}
				} catch(SQLException e5) {
					e5.printStackTrace();
				}
			}
		});
		lastStudentRecordBtn.setIcon(new ImageIcon(StudentUpdateDeleteForm.class.getResource("/resources/Last_Record.png")));
		lastStudentRecordBtn.setBounds(263, 172, 49, 27);
		panel.add(lastStudentRecordBtn);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sql = "UPDATE Student SET Firstname = ?, Lastname = ?, RegNumber = ? WHERE ID = ?";
				
				try (Connection connection = DBUtil.getConnection();
						PreparedStatement ps = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

					String firstname = studentFirstnameTxt.getText().trim();
					String lastname = studentLastnameTxt.getText().trim();
					String regNumber = regNumberTxt.getText().trim();
					String id = studentIdTxt.getText().trim();
					
					if(firstname.equals("") || lastname.equals("") || regNumber.equals("")) {
						JOptionPane.showMessageDialog(null, "Empty Firstname / Lastname / RegNumber", "Input Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					ps.setString(1, firstname);
					ps.setString(2, lastname);
					ps.setString(3, regNumber);
					ps.setString(4, id);
					
					int n = ps.executeUpdate();
					
					if(n > 0) {
						JOptionPane.showMessageDialog(null, "Successful Update: " + n + " row(s) affected.", "Update", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Update Error", "Update", JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (SQLException e6) {
					e6.printStackTrace();
				}
			}
		});
		btnUpdate.setForeground(new Color(0, 0, 255));
		btnUpdate.setBounds(30, 219, 117, 25);
		panel.add(btnUpdate);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sql = "DELETE FROM Student WHERE ID = ?";
				
				try (Connection connection = DBUtil.getConnection();
						PreparedStatement ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
					
					int id = Integer.parseInt(studentIdTxt.getText());
					ps.setInt(1, id);
					
					int response = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning", JOptionPane.YES_NO_OPTION);
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
		btnDelete.setBounds(151, 219, 117, 25);
		panel.add(btnDelete);

		JButton btnClose = new JButton("Close");
		btnClose.setForeground(new Color(0, 0, 255));
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					connection.close();
				} catch(SQLException e9) {
					e9.printStackTrace();
				}
				Main.getStudentUpdateDeleteForm().setVisible(false);
				Main.getStudentSearchForm().setEnabled(true);
			}
		});
		btnClose.setBounds(273, 219, 117, 25);
		panel.add(btnClose);
	}
}
