package admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
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
 * The {@link CourseUpdateDeleteForm} class creates
 * a form for updating and/or deleting a course record in the database.
 * It is accessed from the {@link CourseSearchForm} class.
 */
public class CourseUpdateDeleteForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField viewIdTxt;
	private JTextField viewTitleTxt;
	private JTextField viewClassroomTxt;
	private JTextField viewPeriodTxt;
	private JTextField viewInstructorFirstnameTxt;
	private JTextField viewInstructorLastnameTxt;
	private String sql;
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	private StringBuilder sqlBuilder;
	

	/**
	 * Create the frame.
	 */
	public CourseUpdateDeleteForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				sqlBuilder = new StringBuilder();
				sqlBuilder.append("SELECT c.ID, c.Title, c.Classroom , c.Period, t.Firstname , t.Lastname");
				sqlBuilder.append(" FROM Course c");
				sqlBuilder.append(" INNER JOIN Teacher t");
				sqlBuilder.append(" ON c.Teacher_ID = t.ID ");
				sqlBuilder.append(" WHERE Title LIKE ?");
				sql = sqlBuilder.toString();
				try {
					connection = DBUtil.getConnection();
					ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ps.setString(1, Main.getCourseSearchForm().getTitle()+"%");
					rs = ps.executeQuery();
					
					if(rs.next()) {
						viewIdTxt.setText(Integer.toString(rs.getInt("ID")));
						viewTitleTxt.setText(rs.getString("Title"));
						viewClassroomTxt.setText(rs.getString("Classroom"));
						viewPeriodTxt.setText(rs.getString("Period"));
						viewInstructorFirstnameTxt.setText(rs.getString("Firstname"));
						viewInstructorLastnameTxt.setText(rs.getString("Lastname"));
					} else {
						JOptionPane.showMessageDialog(null, "No Course(s) found", "Course(s)", JOptionPane.WARNING_MESSAGE);
						Main.getCourseUpdateDeleteForm().setVisible(false);
						Main.getCourseSearchForm().setEnabled(true);
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
				try {
					connection.close();
				} catch(SQLException e9) {
					e9.printStackTrace();
				}
			}
		});
		setBackground(new Color(255, 255, 240));
		setTitle("Update/Delete Course");
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 683, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 649, 272);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel viewCourseIdLbl = new JLabel("ID");
		viewCourseIdLbl.setForeground(new Color(165, 42, 42));
		viewCourseIdLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		viewCourseIdLbl.setBounds(16, 46, 70, 15);
		panel.add(viewCourseIdLbl);

		JLabel viewCourseTitleLbl = new JLabel("Title");
		viewCourseTitleLbl.setForeground(new Color(165, 42, 42));
		viewCourseTitleLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		viewCourseTitleLbl.setBounds(16, 73, 70, 15);
		panel.add(viewCourseTitleLbl);

		JLabel viewCourseClassroomLbl = new JLabel("Classroom");
		viewCourseClassroomLbl.setForeground(new Color(165, 42, 42));
		viewCourseClassroomLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		viewCourseClassroomLbl.setBounds(0, 102, 86, 15);
		panel.add(viewCourseClassroomLbl);

		JLabel viewCoursePeriodLbl = new JLabel("Period");
		viewCoursePeriodLbl.setForeground(new Color(165, 42, 42));
		viewCoursePeriodLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		viewCoursePeriodLbl.setBounds(16, 131, 70, 15);
		panel.add(viewCoursePeriodLbl);

		viewIdTxt = new JTextField();
		viewIdTxt.setBackground(new Color(255, 255, 224));
		viewIdTxt.setEditable(false);
		viewIdTxt.setBounds(105, 44, 58, 19);
		panel.add(viewIdTxt);
		viewIdTxt.setColumns(10);

		viewTitleTxt = new JTextField();
		viewTitleTxt.setBounds(105, 71, 196, 19);
		panel.add(viewTitleTxt);
		viewTitleTxt.setColumns(10);

		viewClassroomTxt = new JTextField();
		viewClassroomTxt.setBounds(105, 100, 150, 19);
		panel.add(viewClassroomTxt);
		viewClassroomTxt.setColumns(10);

		viewPeriodTxt = new JTextField();
		viewPeriodTxt.setBounds(105, 129, 150, 19);
		panel.add(viewPeriodTxt);
		viewPeriodTxt.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(313, 14, 2, 132);
		panel.add(separator);

		JLabel courseDetailsLbl = new JLabel("Course Details");
		courseDetailsLbl.setForeground(new Color(165, 42, 42));
		courseDetailsLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		courseDetailsLbl.setBounds(105, 14, 136, 15);
		panel.add(courseDetailsLbl);

		JLabel instructorDetailsLbl = new JLabel("Instructor Details");
		instructorDetailsLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		instructorDetailsLbl.setForeground(new Color(165, 42, 42));
		instructorDetailsLbl.setBounds(418, 14, 136, 15);
		panel.add(instructorDetailsLbl);

		JLabel instructorFirstnameLbl = new JLabel("Firstname");
		instructorFirstnameLbl.setForeground(new Color(165, 42, 42));
		instructorFirstnameLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		instructorFirstnameLbl.setBounds(313, 46, 86, 15);
		panel.add(instructorFirstnameLbl);

		JLabel instructorLastnameLbl = new JLabel("Lastname");
		instructorLastnameLbl.setForeground(new Color(165, 42, 42));
		instructorLastnameLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		instructorLastnameLbl.setBounds(329, 73, 70, 15);
		panel.add(instructorLastnameLbl);

		viewInstructorFirstnameTxt = new JTextField();
		viewInstructorFirstnameTxt.setBackground(new Color(255, 255, 224));
		viewInstructorFirstnameTxt.setEditable(false);
		viewInstructorFirstnameTxt.setBounds(418, 44, 114, 19);
		panel.add(viewInstructorFirstnameTxt);
		viewInstructorFirstnameTxt.setColumns(10);

		viewInstructorLastnameTxt = new JTextField();
		viewInstructorLastnameTxt.setBackground(new Color(255, 255, 224));
		viewInstructorLastnameTxt.setEditable(false);
		viewInstructorLastnameTxt.setBounds(418, 71, 114, 19);
		panel.add(viewInstructorLastnameTxt);
		viewInstructorLastnameTxt.setColumns(10);

		JButton firstCourseRecordBtn = new JButton("");
		firstCourseRecordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(rs.first()) {
						viewIdTxt.setText(Integer.toString(rs.getInt("ID")));
						viewTitleTxt.setText(rs.getString("Title"));
						viewClassroomTxt.setText(rs.getString("Classroom"));
						viewPeriodTxt.setText(rs.getString("Period"));
						viewInstructorFirstnameTxt.setText(rs.getString("Firstname"));
						viewInstructorLastnameTxt.setText(rs.getString("Lastname"));
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		firstCourseRecordBtn.setIcon(new ImageIcon(CourseUpdateDeleteForm.class.getResource("/resources/First record.png")));
		firstCourseRecordBtn.setBounds(69, 186, 117, 25);
		panel.add(firstCourseRecordBtn);

		JButton previousCourseRecordBtn = new JButton("");
		previousCourseRecordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(rs.previous()) {
						viewIdTxt.setText(Integer.toString(rs.getInt("ID")));
						viewTitleTxt.setText(rs.getString("Title"));
						viewClassroomTxt.setText(rs.getString("Classroom"));
						viewPeriodTxt.setText(rs.getString("Period"));
						viewInstructorFirstnameTxt.setText(rs.getString("Firstname"));
						viewInstructorLastnameTxt.setText(rs.getString("Lastname"));
					} else {
						rs.first();
					}
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
			}
		});
		previousCourseRecordBtn.setIcon(new ImageIcon(CourseUpdateDeleteForm.class.getResource("/resources/Previous_record.png")));
		previousCourseRecordBtn.setBounds(198, 186, 117, 25);
		panel.add(previousCourseRecordBtn);

		JButton nextCourseRecordBtn = new JButton("");
		nextCourseRecordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(rs.next()) {
						viewIdTxt.setText(Integer.toString(rs.getInt("ID")));
						viewTitleTxt.setText(rs.getString("Title"));
						viewClassroomTxt.setText(rs.getString("Classroom"));
						viewPeriodTxt.setText(rs.getString("Period"));
						viewInstructorFirstnameTxt.setText(rs.getString("Firstname"));
						viewInstructorLastnameTxt.setText((rs.getString("Lastname")));;
					} else {
						rs.last();
					}
				} catch(SQLException e4) {
					e4.printStackTrace();
				}
			}
		});
		nextCourseRecordBtn.setIcon(new ImageIcon(CourseUpdateDeleteForm.class.getResource("/resources/Next_track.png")));
		nextCourseRecordBtn.setBounds(322, 186, 117, 25);
		panel.add(nextCourseRecordBtn);

		JButton lastCourseRecordBtn = new JButton("");
		lastCourseRecordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(rs.last()) {
						viewIdTxt.setText(Integer.toString(rs.getInt("ID")));
						viewTitleTxt.setText(rs.getString("Title"));
						viewClassroomTxt.setText(rs.getString("Classroom"));
						viewPeriodTxt.setText(rs.getString("Period"));
						viewInstructorFirstnameTxt.setText(rs.getString("Firstname"));
						viewInstructorLastnameTxt.setText(rs.getString("Lastname"));
					}
				} catch (SQLException e5) {
					e5.printStackTrace();
				}
			}
		});
		lastCourseRecordBtn.setIcon(new ImageIcon(CourseUpdateDeleteForm.class.getResource("/resources/Last_Record.png")));
		lastCourseRecordBtn.setBounds(451, 186, 117, 25);
		panel.add(lastCourseRecordBtn);

		JButton courseUpdateBtn = new JButton("Update");
		courseUpdateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sql = "UPDATE Course SET Title = ?, Classroom = ?, Period = ? WHERE ID = ?";
				try (Connection connection = DBUtil.getConnection();
						PreparedStatement ps =connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
					String title = viewTitleTxt.getText().trim();
					String classroom = viewClassroomTxt.getText().trim();
					String period = viewPeriodTxt.getText().trim();
					String id = viewIdTxt.getText().trim();
					
					if(title.equals("") || classroom.equals("") || period.equals("")) {
						JOptionPane.showMessageDialog(null, "Empty title / classroom / period", "Input Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					ps.setString(1,title);
					ps.setString(2,  classroom);
					ps.setString(3, period);
					ps.setString(4, id);
					
					int n = ps.executeUpdate();
					
					if(n > 0) {
						JOptionPane.showMessageDialog(null, "Successful Update: " + n + " row(s) affected.", "Update", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Update Error", "Update", JOptionPane.ERROR_MESSAGE);
					}

					
				} catch(SQLException e6) {
					e6.printStackTrace();
				}
			}
		});
		courseUpdateBtn.setForeground(new Color(0, 0, 255));
		courseUpdateBtn.setBounds(138, 235, 117, 25);
		panel.add(courseUpdateBtn);

		JButton courseDeleteBtn = new JButton("Delete");
		courseDeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sql = "DELETE FROM Course WHERE ID = ?";
				
				
				try (Connection connection = DBUtil.getConnection();
						PreparedStatement ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
					int id = Integer.parseInt(viewIdTxt.getText());
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
		courseDeleteBtn.setForeground(new Color(0, 0, 255));
		courseDeleteBtn.setBounds(268, 235, 117, 25);
		panel.add(courseDeleteBtn);

		JButton viewCourseCloseBtn = new JButton("Close");
		viewCourseCloseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					connection.close();
				} catch(SQLException e8) {
					e8.printStackTrace();
					}
				Main.getCourseUpdateDeleteForm().setVisible(false);
				Main.getCourseSearchForm().setEnabled(true);
				}
		});
		viewCourseCloseBtn.setForeground(new Color(0, 0, 255));
		viewCourseCloseBtn.setBounds(397, 235, 117, 25);
		panel.add(viewCourseCloseBtn);
	}
}
