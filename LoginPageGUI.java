package HW9_simplePaint;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class LoginPageGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField_user;
	private JPasswordField passwordField_pass;
	private PersonDBManager personDBM;
	public Person person;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {// main method of the whole program
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPageGUI frame = new LoginPageGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginPageGUI() {
		setTitle("صفحه ورود");
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		dispose();
		setBounds(100, 100, 667, 567);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 153, 102));
		panel.setBounds(198, 192, 272, 181);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lbl_username = new JLabel("\u0646\u0627\u0645 \u06A9\u0627\u0631\u0628\u0631\u06CC :");
		lbl_username.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_username.setBounds(165, 19, 97, 31);
		panel.add(lbl_username);

		JLabel lbl_password = new JLabel("\u06AF\u0630\u0631\u0648\u0627\u0698\u0647 :");
		lbl_password.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_password.setBounds(165, 68, 86, 28);
		panel.add(lbl_password);

		textField_user = new JTextField();
		textField_user.setBounds(20, 21, 135, 31);
		panel.add(textField_user);
		textField_user.setColumns(10);

		passwordField_pass = new JPasswordField();
		passwordField_pass.setBounds(20, 69, 135, 31);
		passwordField_pass.setToolTipText("Password must contain at least 4 characters");
		panel.add(passwordField_pass);

		JButton btn_login = new JButton("\u0648\u0631\u0648\u062F");
		btn_login.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!textField_user.getText().trim().isEmpty() & passwordField_pass.getPassword().length != 0) {
					String passText = new String(passwordField_pass.getPassword());
					String userText = new String(textField_user.getText());
					person = new Person(userText, passText);
					try {
						personDBM = new PersonDBManager();
						if (personDBM.authentication(person)) {
							setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
							dispose();
							new DrawPage().setVisible(true);
						} else
							JOptionPane.showMessageDialog(null,
									"The username or password you entered doesn't/dont match!");
					} catch (HeadlessException | SQLException e) {
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"You must fill both fields of username and password to continue!");
				}
			}
		});
		btn_login.setBounds(85, 125, 99, 34);
		panel.add(btn_login);

		JLabel lbl_welcome = new JLabel(
				"\u0628\u0647 \u0628\u0631\u0646\u0627\u0645\u0647 \u0646\u0642\u0627\u0634\u06CC \u062E\u0648\u0634 \u0627\u0648\u0645\u062F\u06CC");
		lbl_welcome.setForeground(new Color(153, 0, 0));
		lbl_welcome.setFont(new Font("2  Kaj", Font.BOLD, 42));
		lbl_welcome.setBounds(84, 11, 510, 116);
		contentPane.add(lbl_welcome);

		JLabel lbl_direct = new JLabel(
				"\u0644\u0637\u0641\u0627 \u0628\u0631\u0627\u06CC \u0647\u062F\u0627\u06CC\u062A \u0634\u062F\u0646 \u0628\u0647 \u0635\u0641\u062D\u0647 \u062E\u0648\u062F \u0627\u0628\u062A\u062F\u0627 \u0648\u0627\u0631\u062F \u0634\u0648\u06CC\u062F :");
		lbl_direct.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_direct.setBounds(172, 147, 328, 34);
		contentPane.add(lbl_direct);
	}
}
