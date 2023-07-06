import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Library extends JFrame {
	static Library frame;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Library();
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
	public Library() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("smiley.png")));

		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblLibraryManagement = new JLabel("Library Management ");
		lblLibraryManagement.setBounds(36, 15, 333, 38);
		lblLibraryManagement.setFont(new Font("Algerian", Font.BOLD, 28));
		lblLibraryManagement.setForeground(new Color(153, 102, 255));

		/**
		 * Create Button.
		 */
		JButton btnAdminLogin = new JButton("Admin Login");
		btnAdminLogin.setBounds(145, 85, 135, 52);
		btnAdminLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminLogin.main(new String[] {});
				frame.dispose();
			}
		});
		btnAdminLogin.setFont(new Font("Agency FB", Font.BOLD, 22));
		/**
		 * Create Button.
		 */
		JButton btnLibrarianLogin = new JButton("Student Login");
		btnLibrarianLogin.setBounds(145, 147, 135, 53);
		btnLibrarianLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LibrarianLogin a = new LibrarianLogin();
				a.setSize(650, 400);

				LibrarianLogin.main(new String[] {});
				frame.dispose();
			}
		});
		contentPane.setLayout(null);
		btnLibrarianLogin.setFont(new Font("Agency FB", Font.BOLD, 22));
		contentPane.add(btnLibrarianLogin);
		contentPane.add(btnAdminLogin);
		contentPane.add(lblLibraryManagement);
	}
}
