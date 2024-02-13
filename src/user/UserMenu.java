package user;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import applauncher.Main;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The {@link UserMenu class} serves as the main
 * access point to the {@link CourseRegistrationForm} and
 * {@link UpdatePasswordForm} classes.
 * It follows the successful login of the student user
 * in the {@link LoginRegisterForm} class.
 */
public class UserMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public UserMenu() {
		setBackground(new Color(0, 128, 128));
		setTitle("User Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 426, 244);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton regCourseBtn = new JButton("");
		regCourseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getUserMenu().setEnabled(false);
				Main.getCourseRegistrationForm().setVisible(true);
			}
		});
		regCourseBtn.setBounds(107, 63, 34, 25);
		panel.add(regCourseBtn);
		
		JButton updatePasswordBtn = new JButton("");
		updatePasswordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getUserMenu().setEnabled(false);
				Main.getUpdatePasswordForm().setVisible(true);
			}
		});
		updatePasswordBtn.setBounds(107, 122, 34, 25);
		panel.add(updatePasswordBtn);
		
		JLabel regCourseLbl = new JLabel("Register for Course");
		regCourseLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		regCourseLbl.setForeground(new Color(128, 0, 0));
		regCourseLbl.setBounds(177, 63, 154, 15);
		panel.add(regCourseLbl);
		
		JLabel updatePasswordLbl = new JLabel("Update Password");
		updatePasswordLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		updatePasswordLbl.setForeground(new Color(128, 0, 0));
		updatePasswordLbl.setBounds(177, 122, 154, 15);
		panel.add(updatePasswordLbl);
	}
}
