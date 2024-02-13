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
 * The {@link UserSearchForm} class creates a form
 * for searching a specific user record in the database.
 * It is accessed from the {@link AdminMenu} class.
 */
public class UserSearchForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameTxt;
	private String username;


	/**
	 * Create the frame.
	 */
	public UserSearchForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				usernameTxt.setText("");
			}
		});
		setTitle("User Search");
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

		JLabel usernameLbl = new JLabel("Username");
		usernameLbl.setForeground(new Color(165, 42, 42));
		usernameLbl.setFont(new Font("Dialog", Font.BOLD, 14));
		usernameLbl.setBounds(168, 52, 85, 15);
		panel.add(usernameLbl);

		usernameTxt = new JTextField();
		usernameTxt.setBounds(136, 79, 148, 36);
		panel.add(usernameTxt);
		usernameTxt.setColumns(10);

		JButton searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = usernameTxt.getText();
				Main.getUserSearchForm().setEnabled(false);
				Main.getUserDeleteForm().setVisible(true);
			}
		});
		searchBtn.setForeground(new Color(0, 0, 255));
		searchBtn.setBounds(64, 149, 117, 25);
		panel.add(searchBtn);

		JButton closeBtn = new JButton("Close");
		closeBtn.setForeground(new Color(0, 0, 255));
		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.getUserSearchForm().setVisible(false);
				Main.getAdminMenu().setEnabled(true);

			}
		});
		closeBtn.setBounds(245, 149, 117, 25);
		panel.add(closeBtn);
	}


	public String getUsername() {
		return username;
	}
	
	

}
