package admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import applauncher.Main;
import util.DBUtil;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The {@link TeacherInsertForm} class creates a form
 * for adding a new teacher record to the database.
 * It is accessed from the {@link AdminMenu} class.
 */
public class TeacherInsertForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField newTeacherFirstnameTxt;
	private JTextField newTeacherLastnameTxt;
	private String firstname;
	private String lastname;
	private String sql;
	private Connection connection;
	private PreparedStatement ps;
	
	/**
	 * Create the frame.
	 */
	public TeacherInsertForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				newTeacherFirstnameTxt.setText("");
				newTeacherLastnameTxt.setText("");
			}
		});
		setBackground(new Color(255, 255, 240));
		setTitle("Insert New Teacher");
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
		
		JLabel newTeacherFirstnameLbl = new JLabel("Firstname");
		newTeacherFirstnameLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		newTeacherFirstnameLbl.setForeground(new Color(128, 0, 0));
		newTeacherFirstnameLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		newTeacherFirstnameLbl.setBounds(79, 66, 83, 15);
		panel.add(newTeacherFirstnameLbl);
		
		JLabel newTeacherLastnameLbl = new JLabel("Lastname");
		newTeacherLastnameLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		newTeacherLastnameLbl.setForeground(new Color(128, 0, 0));
		newTeacherLastnameLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		newTeacherLastnameLbl.setBounds(79, 111, 83, 15);
		panel.add(newTeacherLastnameLbl);
		
		newTeacherFirstnameTxt = new JTextField();
		newTeacherFirstnameTxt.setBounds(180, 61, 114, 26);
		panel.add(newTeacherFirstnameTxt);
		newTeacherFirstnameTxt.setColumns(10);
		
		newTeacherLastnameTxt = new JTextField();
		newTeacherLastnameTxt.setBounds(180, 106, 114, 26);
		panel.add(newTeacherLastnameTxt);
		newTeacherLastnameTxt.setColumns(10);
		
		JButton addNewTeacherBtn = new JButton("Add");
		addNewTeacherBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					firstname = newTeacherFirstnameTxt.getText().trim();
					lastname = newTeacherLastnameTxt.getText().trim();
					
					if(firstname.equals("") || lastname.equals("")) {
						JOptionPane.showMessageDialog(null, "Empty Firstname / Lastname", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					sql = "INSERT INTO Teacher (Firstname, Lastname) VALUES (?, ?)";
					connection =  DBUtil.getConnection();
					ps = connection.prepareStatement(sql);
					ps.setString(1, firstname);
					ps.setString(2, lastname);
					
					int n = ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Addition Successful: " + n + " row(s) affected", "Insert", JOptionPane.INFORMATION_MESSAGE);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		addNewTeacherBtn.setForeground(new Color(0, 0, 255));
		addNewTeacherBtn.setBounds(92, 177, 117, 25);
		panel.add(addNewTeacherBtn);
		
		JButton closeNewTeacherBtn = new JButton("Close");
		closeNewTeacherBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBUtil.closeConnection();
				Main.getTeacherInsertForm().setVisible(false);
				Main.getAdminMenu().setEnabled(true);
			}
		});
		closeNewTeacherBtn.setForeground(new Color(0, 0, 255));
		closeNewTeacherBtn.setBounds(221, 177, 117, 25);
		panel.add(closeNewTeacherBtn);
	}
}
