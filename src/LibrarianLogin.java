import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LibrarianLogin extends JFrame {
	static LibrarianLogin frame;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	String name;
	private JButton btnBack;
	private JButton btnLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LibrarianLogin();
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
	public LibrarianLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 398);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("smiley.png")));

		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblAdminLoginForm = new JLabel("Student Login Panel");
		lblAdminLoginForm.setBounds(125, 13, 347, 38);
		lblAdminLoginForm.setForeground(new Color(153, 102, 255));
		lblAdminLoginForm.setFont(new Font("Algerian", Font.BOLD, 28));

		JLabel lblEnterName = new JLabel("Enter Name:");
		lblEnterName.setBounds(75, 83, 83, 27);
		lblEnterName.setForeground(new Color(153, 102, 255));
		lblEnterName.setFont(new Font("Agency FB", Font.BOLD, 18));

		JLabel lblEnterPassword = new JLabel("Enter Password:");
		lblEnterPassword.setBounds(61, 166, 118, 27);
		lblEnterPassword.setForeground(new Color(153, 102, 255));
		lblEnterPassword.setFont(new Font("Agency FB", Font.BOLD, 18));

		textField = new JTextField();
		textField.setBounds(260, 83, 215, 25);
		textField.setColumns(10);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(170, 280, 86, 37);
		btnLogin.setFont(new Font("Agency FB", Font.BOLD, 22));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLogin.setEnabled(false);
				name = textField.getText();
				String password = String.valueOf(passwordField.getPassword());
				// System.out.println(name+" "+password);
				if (LibrarianDao.validate(name, password)) {
					LibrarianSuccess.main(new String[] {});
					frame.dispose();
				} else {btnLogin.setEnabled(true);
					JOptionPane.showMessageDialog(LibrarianLogin.this, "Sorry, Username or Password Error",
							"Login Error!", JOptionPane.ERROR_MESSAGE);
					textField.setText("");
					passwordField.setText("");
				}
			}
		});

		passwordField = new JPasswordField();
		passwordField.setBounds(260, 171, 215, 25);
		contentPane.setLayout(null);
		contentPane.add(lblEnterPassword);
		contentPane.add(lblEnterName);
		contentPane.add(textField);
		contentPane.add(passwordField);
		contentPane.add(lblAdminLoginForm);
		contentPane.add(btnLogin);

		btnBack = new JButton("Back");
		btnBack.setBounds(380, 280, 86, 37);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBack.setEnabled(false);
				LibrarianLogin.this.setVisible(false);
				Library.main(new String[] {});
				frame.dispose();
			}
		});
		btnBack.setFont(new Font("Agency FB", Font.BOLD, 22));
		contentPane.add(btnBack);
	}
}
