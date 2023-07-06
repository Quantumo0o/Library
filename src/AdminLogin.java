import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class AdminLogin extends JFrame {
	static AdminLogin frame;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
				try {
					frame = new AdminLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
	}

	/**
	 * Create the frame.
	 */
	public AdminLogin() {

		setTitle(" ADMIN LOGIN ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 410);
		contentPane = new JPanel();
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("smiley.png")));
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblAdminLoginForm = new JLabel("Admin Login Form");
		lblAdminLoginForm.setBounds(164, 13, 330, 38);
		lblAdminLoginForm.setForeground(new Color(153, 102, 255));
		lblAdminLoginForm.setFont(new Font("Algerian", Font.BOLD, 28));

		JLabel lblEnterName = new JLabel("Enter Name:");
		lblEnterName.setBounds(75, 83, 83, 27);
		lblEnterName.setForeground(new Color(153, 51, 255));
		lblEnterName.setFont(new Font("Agency FB", Font.BOLD, 18));

		JLabel lblEnterPassword = new JLabel("Enter Password:");
		lblEnterPassword.setBounds(62, 158, 118, 27);
		lblEnterPassword.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblEnterPassword.setForeground(new Color(153, 51, 255));

		textField = new JTextField();
		textField.setBounds(170, 85, 215, 25);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setColumns(10);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(170, 252, 86, 37);
		btnLogin.setFont(new Font("Agency FB", Font.BOLD, 20));
		btnLogin.setBackground(new Color(240, 240, 240));
		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				btnLogin.setEnabled(false);
				String name = textField.getText();
				String password = String.valueOf(passwordField.getPassword());
				/**
				 * Verification the Admin User Name and Password
				 */
				if (LibrarianDao.validateadmin(name, password)) {
					Adminlogindone a = new Adminlogindone();
					a.setSize(450, 650);
					a.setLocationRelativeTo(null);
					Connection con = DB.getConnection();
					try {
						con.close();
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
					a.setVisible(true);
					AdminLogin.this.setVisible(false);

				} else {
					btnLogin.setEnabled(true);
					JOptionPane.showMessageDialog(null, "Sorry, Username or Password Error", "Login Error!",
							JOptionPane.ERROR_MESSAGE);
					textField.setText("");
					passwordField.setText("");
				}

			}
		});

		passwordField = new JPasswordField();
		passwordField.setBounds(170, 160, 215, 25);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(307, 252, 86, 37);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminLogin.this.setVisible(false);
				Library.main(new String[] {});

			}
		});
		btnBack.setFont(new Font("Agency FB", Font.BOLD, 20));
		btnBack.setBackground(new Color(240, 240, 240));
		contentPane.setLayout(null);
		contentPane.add(lblEnterPassword);
		contentPane.add(lblEnterName);
		contentPane.add(textField);
		contentPane.add(passwordField);
		contentPane.add(lblAdminLoginForm);
		contentPane.add(btnLogin);
		contentPane.add(btnBack);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 642, 363);
		contentPane.add(lblNewLabel);
	}
}
