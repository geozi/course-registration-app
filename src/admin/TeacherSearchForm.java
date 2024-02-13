package admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import applauncher.Main;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The {@link TeacherSearchForm} class creates a form
 * for searching a specific teacher record in the database.
 * It is accessed from the {@link AdminMenu} class.
 */
public class TeacherSearchForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField teacherLastnameTxt;
	private String teacherLastname;


	/**
	 * Create the frame.
	 */
	public TeacherSearchForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				teacherLastnameTxt.setText("");
			}
		});
		setTitle("Teacher Search");
		setBackground(new Color(255, 255, 240));
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

		JLabel lblLastname = new JLabel("Lastname");
		lblLastname.setForeground(new Color(165, 42, 42));
		lblLastname.setFont(new Font("Dialog", Font.BOLD, 14));
		lblLastname.setBounds(174, 43, 76, 15);
		panel.add(lblLastname);

		teacherLastnameTxt = new JTextField();
		teacherLastnameTxt.setBounds(138, 70, 148, 36);
		panel.add(teacherLastnameTxt);
		teacherLastnameTxt.setColumns(10);

		JButton searchBtn = new JButton("Search");
		searchBtn.setForeground(new Color(0, 0, 255));
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				teacherLastname = teacherLastnameTxt.getText();
				Main.getTeacherSearchForm().setEnabled(false);
				Main.getTeacherUpdateDeleteForm().setVisible(true);
			}
		});
		searchBtn.setBounds(64, 149, 117, 25);
		panel.add(searchBtn);

		JButton closeBtn = new JButton("Close");
		closeBtn.setForeground(new Color(0, 0, 255));
		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.getTeacherSearchForm().setVisible(false);
				Main.getAdminMenu().setEnabled(true);
			}
		});
		closeBtn.setBounds(245, 149, 117, 25);
		panel.add(closeBtn);
	}

	public String getTeacherLastname() {
		return teacherLastname;
	}
	
	
}
