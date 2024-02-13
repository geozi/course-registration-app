package user;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import applauncher.Main;
import model.Course;
import util.DBUtil;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The {@link CourseRegistrationForm} class creates 
 * a form for registering a student to a course.
 * It is accessed from the {@link UserMenu} class.
 */
public class CourseRegistrationForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String sql;
	private Statement statement;
	private PreparedStatement ps;
	private Connection connection;
	private ResultSet rs;
	private DefaultListModel<Course> dlm;
	private final JList<Course> courseList = new JList<>();
	private int id;
	private String title;
	private String classroom;
	private String period;
	private Course course;
	private String chosenCourseId;

	/**
	 * Create the frame.
	 */
	public CourseRegistrationForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				sql = "SELECT * FROM Course";
				
				try {
					dlm = new DefaultListModel<>();
					connection = DBUtil.getConnection();
					statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					rs = statement.executeQuery(sql);
					
					while(rs.next()) {
						id = rs.getInt("ID");
						title = rs.getString("Title");
						classroom = rs.getString("Classroom");
						period = rs.getString("Period");
						course = new Course(id, title, classroom, period);
						dlm.addElement(course);
					}
					
					courseList.setModel(dlm);
					
				} catch(SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
				try {
					connection.close();
				} catch(SQLException e3) {
					e3.printStackTrace();
				}
			}
		});
		setBackground(new Color(255, 255, 240));
		setTitle("Course Registration");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 581, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 557, 363);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sql = "INSERT INTO Students_Courses(Student_ID, Course_ID) VALUES(?,?)";
				
				try {
					ps = connection.prepareStatement(sql);
					ps.setString(1, Main.getLoginRegisterForm().getUserId());
					ps.setString(2,  chosenCourseId);
					int n = ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Registration Successful: " + n + " row(s) affected", "Register", JOptionPane.INFORMATION_MESSAGE);
					
				} catch(SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		registerBtn.setForeground(new Color(0, 0, 255));
		registerBtn.setBounds(139, 326, 117, 25);
		panel.add(registerBtn);
		
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection.close();
				} catch(SQLException e4) {
					e4.printStackTrace();
				}
				Main.getCourseRegistrationForm().setVisible(false);
				Main.getUserMenu().setEnabled(true);
			}
		});
		closeBtn.setForeground(new Color(0, 0, 255));
		closeBtn.setBounds(291, 326, 117, 25);
		panel.add(closeBtn);
		courseList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chosenCourseId = String.valueOf(courseList.getSelectedValue().getId());
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(courseList);
		scrollPane.setBounds(36, 22, 487, 292);
		panel.add(scrollPane);
	}
}
