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
 * The {@link CourseSearchForm} class creates a 
 * form for searching a specific course record in the database.
 * It is accessed from the {@link AdminMenu} class.
 */
public class CourseSearchForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField titleTxt;
	private String title;

	/**
	 * Create the frame.
	 */
	public CourseSearchForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				titleTxt.setText("");
			}
		});
		setBackground(new Color(255, 255, 240));
		setTitle("Course Search");
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

		JLabel titleLbl = new JLabel("Title");
		titleLbl.setFont(new Font("Dialog", Font.BOLD, 14));
		titleLbl.setForeground(new Color(165, 42, 42));
		titleLbl.setBounds(188, 42, 39, 15);
		panel.add(titleLbl);

		titleTxt = new JTextField();
		titleTxt.setBounds(134, 69, 148, 36);
		panel.add(titleTxt);
		titleTxt.setColumns(10);

		JButton searchBtn = new JButton("Search");
		searchBtn.setForeground(new Color(0, 0, 255));
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				title = titleTxt.getText();
				Main.getCourseSearchForm().setEnabled(false);
				Main.getCourseUpdateDeleteForm().setVisible(true);
			}
		});
		searchBtn.setBounds(69, 149, 117, 25);
		panel.add(searchBtn);

		JButton closeBtn = new JButton("Close");
		closeBtn.setForeground(new Color(0, 0, 255));
		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.getCourseSearchForm().setVisible(false);
				Main.getAdminMenu().setEnabled(true);
			}
		});
		closeBtn.setBounds(245, 149, 117, 25);
		panel.add(closeBtn);
	}

	@Override
	public String getTitle() {
		return title;
	}



}
