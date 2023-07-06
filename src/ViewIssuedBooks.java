import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ViewIssuedBooks extends JFrame {

	private JPanel contentPane;
	private JTextField txtold;
	private JTextField txtre;
	private JPasswordField txtnew;
	private JButton txtback;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewIssuedBooks frame = new ViewIssuedBooks();
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
	public ViewIssuedBooks() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("smiley.png")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Old Password");
		lblNewLabel.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(153, 102, 255));
		lblNewLabel.setBounds(47, 62, 91, 30);
		contentPane.add(lblNewLabel);

		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setForeground(new Color(153, 102, 255));
		lblNewPassword.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblNewPassword.setBounds(47, 105, 91, 30);
		contentPane.add(lblNewPassword);

		JLabel lblNewLabel_1 = new JLabel("Old Password");
		lblNewLabel_1.setForeground(new Color(153, 102, 255));
		lblNewLabel_1.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblNewLabel_1.setBounds(47, 148, 91, 30);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Change password");
		lblNewLabel_1_1.setForeground(new Color(153, 102, 255));
		lblNewLabel_1_1.setFont(new Font("Algerian", Font.BOLD, 28));
		lblNewLabel_1_1.setBounds(47, 23, 312, 30);
		contentPane.add(lblNewLabel_1_1);

		txtold = new JTextField();
		txtold.setBounds(180, 68, 162, 22);
		contentPane.add(txtold);
		txtold.setColumns(10);

		txtre = new JTextField();
		txtre.setColumns(10);
		txtre.setBounds(180, 154, 162, 22);
		contentPane.add(txtre);

		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String oldpass = txtold.getText();

				String newpass = String.valueOf(txtnew.getPassword());

				String newre = txtre.getText();
				LibrarianDao mname = new LibrarianDao();
				if (newpass.equals(newre)) {
					Connection con = DB.getConnection();
					try {
						PreparedStatement pst = con
								.prepareStatement("update librarian set `password`=? where `name`=? and `password`=?");
						pst.setString(1, newpass);
						pst.setString(2, mname.mamname);
						pst.setString(3, oldpass);
						int k = pst.executeUpdate();
						con.close();
						if (k == 1) {

							JOptionPane.showMessageDialog(null, "Password  Changed");
							txtold.setText("");
							txtnew.setText("");
							txtre.setText("");
						} else {

							JOptionPane.showMessageDialog(null, "Old Password  Wrong");
							txtold.setText("");
						}

					}

					catch (SQLException e1) {
						e1.printStackTrace();
					} finally {
						try {
							DB.getConnection().close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}

				} else {
					JOptionPane.showMessageDialog(null, "New Password Not Match");
					txtnew.setText("");
					txtre.setText("");

				}
			}
		});
		btnNewButton.setFont(new Font("Agency FB", Font.BOLD, 18));
		btnNewButton.setBounds(114, 201, 97, 25);
		contentPane.add(btnNewButton);

		txtnew = new JPasswordField();
		txtnew.setBounds(180, 111, 162, 22);
		contentPane.add(txtnew);

		txtback = new JButton("Back");
		txtback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtback.setEnabled(false);

				ViewIssuedBooks.this.setVisible(false);

				LibrarianSuccess.frame.enable();
			}
		});
		txtback.setFont(new Font("Agency FB", Font.BOLD, 18));
		txtback.setBounds(245, 203, 97, 25);
		contentPane.add(txtback);
	}
}
