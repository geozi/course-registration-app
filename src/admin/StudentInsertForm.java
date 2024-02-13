package admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import applauncher.Main;
import util.DBUtil;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The {@link StudentInsertForm} class creates
 * a form for inserting a new student record to the database.
 * It is accessed from the {@link AdminMenu} class.
 */
public class StudentInsertForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField newStudentFirstnameTxt;
	private JTextField newStudentLastnameTxt;
	private JTextField newStudentRegNumberTxt;
	private String firstname;
	private String lastname;
	private String regNumber;
	private String sql;
	private Connection connection;
	private PreparedStatement ps;
	


	/**
	 * Create the frame.
	 */
	public StudentInsertForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				newStudentFirstnameTxt.setText("");
				newStudentLastnameTxt.setText("");
				newStudentRegNumberTxt.setText("");
			}
		});
		setBackground(new Color(255, 255, 240));
		setTitle("Insert New Student");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 426, 244);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel newStudentFirstnameLbl = new JLabel("Firstname");
		newStudentFirstnameLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		newStudentFirstnameLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		newStudentFirstnameLbl.setForeground(new Color(128, 0, 0));
		newStudentFirstnameLbl.setBounds(83, 30, 96, 15);
		panel.add(newStudentFirstnameLbl);
		
		JLabel newStudentLastnameLbl = new JLabel("Lastname");
		newStudentLastnameLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		newStudentLastnameLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		newStudentLastnameLbl.setForeground(new Color(128, 0, 0));
		newStudentLastnameLbl.setBounds(83, 73, 96, 15);
		panel.add(newStudentLastnameLbl);
		
		JLabel newStudentRegNumberLbl = new JLabel("RegNumber");
		newStudentRegNumberLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		newStudentRegNumberLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		newStudentRegNumberLbl.setForeground(new Color(128, 0, 0));
		newStudentRegNumberLbl.setBounds(83, 122, 96, 15);
		panel.add(newStudentRegNumberLbl);
		
		newStudentFirstnameTxt = new JTextField();
		newStudentFirstnameTxt.setBounds(199, 23, 114, 26);
		panel.add(newStudentFirstnameTxt);
		newStudentFirstnameTxt.setColumns(10);
		
		newStudentLastnameTxt = new JTextField();
		newStudentLastnameTxt.setColumns(10);
		newStudentLastnameTxt.setBounds(199, 69, 114, 26);
		panel.add(newStudentLastnameTxt);
		
		newStudentRegNumberTxt = new JTextField();
		newStudentRegNumberTxt.setColumns(10);
		newStudentRegNumberTxt.setBounds(199, 117, 114, 26);
		panel.add(newStudentRegNumberTxt);
		
		JButton addNewStudentBtn = new JButton("Add");
		addNewStudentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					firstname = newStudentFirstnameTxt.getText().trim();
					lastname = newStudentLastnameTxt.getText().trim();
					regNumber = newStudentRegNumberTxt.getText().trim();
					
					if(firstname.equals("") || lastname.equals("") || regNumber.equals("")) {
						JOptionPane.showMessageDialog(null, "Empty Firstname / Lastname / RegNumber", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					sql = "INSERT INTO Student (Firstname, Lastname, RegNumber) VALUES (?, ?, ?)";
					connection = DBUtil.getConnection();
					ps = connection.prepareStatement(sql);
					ps.setString(1, firstname);
					ps.setString(2, lastname);
					ps.setString(3, regNumber);
					
					int  n = ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Addition Successful: " + n + " row(s) affected", "Insert", JOptionPane.INFORMATION_MESSAGE);
					
				} catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		addNewStudentBtn.setForeground(new Color(0, 0, 255));
		addNewStudentBtn.setBounds(97, 177, 117, 25);
		panel.add(addNewStudentBtn);
		
		JButton closeNewStudentBtn = new JButton("Close");
		closeNewStudentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBUtil.closeConnection();
				Main.getStudentInsertForm().setVisible(false);
				Main.getAdminMenu().setEnabled(true);
			}
		});
		closeNewStudentBtn.setForeground(new Color(0, 0, 255));
		closeNewStudentBtn.setBounds(221, 177, 117, 25);
		panel.add(closeNewStudentBtn);
	}

}
