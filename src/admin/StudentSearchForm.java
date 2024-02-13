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
 * The {@link StudentSearchForm} class creates a form
 * for searching a specific student record in the database.
 * It is accessed from the {@link AdminMenu} class.
 */
public class StudentSearchForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField studentLastnameTxt;
	private String studentLastname;

	/**
	 * Create the frame.
	 */
	public StudentSearchForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				studentLastnameTxt.setText("");
			}
		});
		setBackground(new Color(255, 255, 240));
		setTitle("Student Search");
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

		JLabel studentLastnamelbl = new JLabel("Lastname");
		studentLastnamelbl.setFont(new Font("Dialog", Font.BOLD, 14));
		studentLastnamelbl.setForeground(new Color(165, 42, 42));
		studentLastnamelbl.setBounds(177, 43, 76, 15);
		panel.add(studentLastnamelbl);

		studentLastnameTxt = new JTextField();
		studentLastnameTxt.setBounds(141, 70, 148, 36);
		panel.add(studentLastnameTxt);
		studentLastnameTxt.setColumns(10);

		JButton studentSearchbtn = new JButton("Search");
		studentSearchbtn.setForeground(new Color(0, 0, 255));
		studentSearchbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				studentLastname = studentLastnameTxt.getText();
				Main.getStudentSearchForm().setEnabled(false);
				Main.getStudentUpdateDeleteForm().setVisible(true);
			}
		});
		studentSearchbtn.setBounds(69, 149, 117, 25);
		panel.add(studentSearchbtn);

		JButton studentCloseBtn = new JButton("Close");
		studentCloseBtn.setForeground(new Color(0, 0, 255));
		studentCloseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.getStudentSearchForm().setVisible(false);
				Main.getAdminMenu().setEnabled(true);
			}
		});
		studentCloseBtn.setBounds(245, 149, 117, 25);
		panel.add(studentCloseBtn);
	}

	public String getStudentLastname() {
		return studentLastname;
	}

}
