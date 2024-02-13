package admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import applauncher.Main;

import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import model.PeriodType;
import model.Teacher;
import util.DBUtil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JScrollPane;

/**
 * The {@link CourseInsertForm} class creates
 * a form for inserting a new course record to the database.
 * It is accessed from the {@link AdminMenu} class.
 */
public class CourseInsertForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField newCourseTitleTxt;
	private JTextField newCourseClassroomTxt;
	private JTextField newCourseTeacherIdTxt;
	private String sql;
	private Connection connection;
	private Statement statement;
	private PreparedStatement ps;
	private ResultSet rs;
	private int id;
	private String firstname;
	private String lastname;
	private DefaultListModel<Teacher> dlm;
	private Teacher teacher;
	private final JList<Teacher> teacherList = new JList<>();
	private String title;
	private String classroom;
	private String period;
	private String teacherId;


	/**
	 * Create the frame.
	 */
	public CourseInsertForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				sql = "SELECT * FROM Teacher";
				
				try {
					connection = DBUtil.getConnection();
					statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					rs = statement.executeQuery(sql);
					dlm = new DefaultListModel<>();
					
					while(rs.next()) {
						id = rs.getInt("ID");
						firstname = rs.getString("Firstname");
						lastname = rs.getString("Lastname");
						teacher = new Teacher(id, firstname, lastname);
						dlm.addElement(teacher);
					}
					
					teacherList.setModel(dlm);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		setBackground(new Color(255, 255, 240));
		setTitle("Insert New Course");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 685, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 32, 268, 234);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel newCourseTitleLbl = new JLabel("Title");
		newCourseTitleLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		newCourseTitleLbl.setForeground(new Color(128, 0, 0));
		newCourseTitleLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		newCourseTitleLbl.setBounds(28, 28, 81, 15);
		panel_1.add(newCourseTitleLbl);
		
		JLabel newCourseClassroomLbl = new JLabel("Classroom");
		newCourseClassroomLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		newCourseClassroomLbl.setForeground(new Color(128, 0, 0));
		newCourseClassroomLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		newCourseClassroomLbl.setBounds(28, 68, 81, 15);
		panel_1.add(newCourseClassroomLbl);
		
		JLabel newCoursePeriodLbl = new JLabel("Period");
		newCoursePeriodLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		newCoursePeriodLbl.setForeground(new Color(128, 0, 0));
		newCoursePeriodLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		newCoursePeriodLbl.setBounds(28, 111, 81, 15);
		panel_1.add(newCoursePeriodLbl);
		
		JLabel newCourseTeacherIdLbl = new JLabel("TeacherID");
		newCourseTeacherIdLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		newCourseTeacherIdLbl.setForeground(new Color(128, 0, 0));
		newCourseTeacherIdLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		newCourseTeacherIdLbl.setBounds(28, 158, 81, 15);
		panel_1.add(newCourseTeacherIdLbl);
		
		newCourseTitleTxt = new JTextField();
		newCourseTitleTxt.setBounds(127, 28, 114, 26);
		panel_1.add(newCourseTitleTxt);
		newCourseTitleTxt.setColumns(10);
		
		newCourseClassroomTxt = new JTextField();
		newCourseClassroomTxt.setBounds(127, 68, 114, 26);
		panel_1.add(newCourseClassroomTxt);
		newCourseClassroomTxt.setColumns(10);
		
		newCourseTeacherIdTxt = new JTextField();
		newCourseTeacherIdTxt.setBounds(127, 153, 59, 26);
		panel_1.add(newCourseTeacherIdTxt);
		newCourseTeacherIdTxt.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(PeriodType.values()));
		comboBox.setBounds(127, 108, 119, 26);
		panel_1.add(comboBox);
		
		JButton newCourseAddBtn = new JButton("Add");
		newCourseAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					title = newCourseTitleTxt.getText().trim();
					classroom = newCourseClassroomTxt.getText().trim();
					period = comboBox.getSelectedItem().toString();
					teacherId = newCourseTeacherIdTxt.getText().trim();
					
					if(title.equals("") || classroom.equals("") || period.equals("") || teacherId.equals("") ) {
						JOptionPane.showMessageDialog(null, "Empty Title / Classroom / Period / TeacherID", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					sql = "INSERT INTO Course (Title, Teacher_ID, Classroom, Period) VALUES (?, ?, ?, ?)";
					ps = connection.prepareStatement(sql);
					ps.setString(1, title);
					ps.setString(2, teacherId);
					ps.setString(3, classroom);
					ps.setString(4, period);
					
					int n = ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Addition Successful: " + n + " row(s) affected", "Insert", JOptionPane.INFORMATION_MESSAGE);
					
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		newCourseAddBtn.setForeground(new Color(0, 0, 255));
		newCourseAddBtn.setBounds(89, 198, 117, 25);
		panel_1.add(newCourseAddBtn);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(382, 32, 259, 234);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		teacherList.setLocation(39, 0);
		
		JScrollPane scrollPane = new JScrollPane(teacherList);
		scrollPane.setBounds(12, 12, 235, 211);
		panel_2.add(scrollPane);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(330, 40, 2, 226);
		contentPane.add(separator);
		
		JButton newCourseCloseBtn = new JButton("Close");
		newCourseCloseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBUtil.closeConnection();
				Main.getCourseInsertForm().setVisible(false);
				Main.getAdminMenu().setEnabled(true);
			}
		});
		newCourseCloseBtn.setForeground(new Color(0, 0, 255));
		newCourseCloseBtn.setBounds(274, 302, 117, 25);
		contentPane.add(newCourseCloseBtn);
		
		JLabel currentTeachersLbl = new JLabel("Current Teachers");
		currentTeachersLbl.setForeground(new Color(128, 0, 0));
		currentTeachersLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		currentTeachersLbl.setBounds(447, 12, 129, 15);
		contentPane.add(currentTeachersLbl);
	}
}
