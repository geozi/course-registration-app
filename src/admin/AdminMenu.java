package admin;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import applauncher.Main;

/**
 * The {@link AdminMenu} class serves as the main
 * access point for the search and insert forms used
 * by the administrator of the database.
 * It follows the successful login of the administrator
 * in the {@link LoginRegisterForm} class.
 */
public class AdminMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AdminMenu() {
		setTitle("Admin Menu");
		setBackground(new Color(95, 158, 160));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 416, 246);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton teacherBtn = new JButton("");
		teacherBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.getAdminMenu().setEnabled(false);
				Main.getTeacherSearchForm().setVisible(true);
			}
		});
		teacherBtn.setBounds(27, 29, 27, 25);
		panel.add(teacherBtn);

		JButton studentBtn = new JButton("");
		studentBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.getAdminMenu().setEnabled(false);
				Main.getStudentSearchForm().setVisible(true);
			}
		});
		studentBtn.setBounds(27, 83, 27, 25);
		panel.add(studentBtn);

		JButton courseBtn = new JButton("");
		courseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.getAdminMenu().setEnabled(false);
				Main.getCourseSearchForm().setVisible(true);
			}
		});
		courseBtn.setBounds(27, 137, 27, 25);
		panel.add(courseBtn);

		JButton userBtn = new JButton("");
		userBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.getAdminMenu().setEnabled(false);
				Main.getUserSearchForm().setVisible(true);
			}
		});
		userBtn.setBounds(27, 191, 27, 25);
		panel.add(userBtn);

		JLabel teachersLbl = new JLabel("Teachers");
		teachersLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		teachersLbl.setForeground(new Color(165, 42, 42));
		teachersLbl.setBounds(72, 29, 79, 15);
		panel.add(teachersLbl);

		JLabel studentsLbl = new JLabel("Students");
		studentsLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		studentsLbl.setForeground(new Color(165, 42, 42));
		studentsLbl.setBounds(72, 83, 79, 15);
		panel.add(studentsLbl);

		JLabel courseslbl = new JLabel("Courses");
		courseslbl.setFont(new Font("Dialog", Font.BOLD, 13));
		courseslbl.setForeground(new Color(165, 42, 42));
		courseslbl.setBounds(72, 138, 79, 15);
		panel.add(courseslbl);

		JLabel userslbl = new JLabel("Users");
		userslbl.setFont(new Font("Dialog", Font.BOLD, 13));
		userslbl.setForeground(new Color(165, 42, 42));
		userslbl.setBounds(72, 191, 79, 15);
		panel.add(userslbl);

		JLabel lblSearchupdatedelete = new JLabel("Search/Update/Delete");
		lblSearchupdatedelete.setFont(new Font("Dialog", Font.BOLD, 14));
		lblSearchupdatedelete.setForeground(new Color(165, 42, 42));
		lblSearchupdatedelete.setBounds(12, 0, 184, 15);
		panel.add(lblSearchupdatedelete);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(209, 52, 2, 182);
		panel.add(separator);

		JButton newTeacherBtn = new JButton("");
		newTeacherBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.getAdminMenu().setEnabled(false);
				Main.getTeacherInsertForm().setVisible(true);
			}
		});
		newTeacherBtn.setBounds(233, 29, 27, 25);
		panel.add(newTeacherBtn);

		JLabel newTeacherLbl = new JLabel("New Teacher");
		newTeacherLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		newTeacherLbl.setForeground(new Color(165, 42, 42));
		newTeacherLbl.setBounds(278, 29, 95, 15);
		panel.add(newTeacherLbl);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(58, 70, 87, 2);
		panel.add(separator_1);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(58, 123, 87, 2);
		panel.add(separator_1_1);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(64, 175, 87, 2);
		panel.add(separator_1_2);

		JLabel lblRegister = new JLabel("Insert");
		lblRegister.setForeground(new Color(165, 42, 42));
		lblRegister.setFont(new Font("Dialog", Font.BOLD, 14));
		lblRegister.setBounds(278, 0, 70, 15);
		panel.add(lblRegister);
		
		JButton newStudentBtn = new JButton("");
		newStudentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getAdminMenu().setEnabled(false);
				Main.getStudentInsertForm().setVisible(true);
			}
		});
		newStudentBtn.setBounds(233, 83, 27, 25);
		panel.add(newStudentBtn);
		
		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setBounds(286, 70, 87, 2);
		panel.add(separator_1_3);
		
		JLabel newStudentLbl = new JLabel("New Student");
		newStudentLbl.setForeground(new Color(165, 42, 42));
		newStudentLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		newStudentLbl.setBounds(278, 83, 95, 15);
		panel.add(newStudentLbl);
		
		JSeparator separator_1_3_1 = new JSeparator();
		separator_1_3_1.setBounds(286, 123, 87, 2);
		panel.add(separator_1_3_1);
		
		JButton newCourseBtn = new JButton("");
		newCourseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getAdminMenu().setEnabled(false);
				Main.getCourseInsertForm().setVisible(true);
			}
		});
		newCourseBtn.setBounds(233, 137, 27, 25);
		panel.add(newCourseBtn);
		
		JLabel newCourseLbl = new JLabel("New Course");
		newCourseLbl.setForeground(new Color(165, 42, 42));
		newCourseLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		newCourseLbl.setBounds(278, 137, 95, 15);
		panel.add(newCourseLbl);
	}
}
