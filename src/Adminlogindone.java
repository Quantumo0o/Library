import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Adminlogindone extends JFrame {
	static Adminlogindone frame;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;
	private JButton btnAuthor;
	private JButton btnBooks;
	private JButton btnBooks_1;
	private JButton btnIssue;
	private JButton btnReturnBook;
	private JButton btnLogOut;
	private JButton btnPublisher;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Adminlogindone();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Adminlogindone() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("smiley.png")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setBackground(new Color(0, 0, 51));
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Admin Section");
		lblNewLabel.setForeground(new Color(153, 102, 255));
		lblNewLabel.setBackground(new Color(153, 102, 255));
		lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 28));
		lblNewLabel.setBounds(91, 13, 293, 33);
		getContentPane().add(lblNewLabel);

		btnNewButton = new JButton("Student Management");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setEnabled(false);

				Adminlogindone.this.setVisible(false);
				member2 h = new member2();
				h.setSize(1200, 600);
				h.setLocationRelativeTo(null);
				h.setVisible(true);

			}
		});
		btnNewButton.setFont(new Font("Agency FB", Font.BOLD, 18));
		btnNewButton.setBounds(128, 73, 167, 37);
		getContentPane().add(btnNewButton);

		btnPublisher = new JButton("Publisher");
		btnPublisher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPublisher.setEnabled(false);
				Adminlogindone.this.setVisible(false);
				Publisher i = new Publisher();
				i.setSize(1200, 600);
				i.setLocationRelativeTo(null);
				i.setVisible(true);

			}
		});
		btnPublisher.setFont(new Font("Agency FB", Font.BOLD, 18));
		btnPublisher.setBounds(128, 128, 167, 37);
		getContentPane().add(btnPublisher);

		btnAuthor = new JButton("Author");
		btnAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAuthor.setEnabled(false);
				Adminlogindone.this.setVisible(false);
				Author j = new Author();
				j.setSize(1200, 600);
				j.setLocationRelativeTo(null);
				j.setVisible(true);
				Adminlogindone.this.setVisible(false);

			}
		});
		btnAuthor.setFont(new Font("Agency FB", Font.BOLD, 18));
		btnAuthor.setBounds(128, 190, 167, 37);
		getContentPane().add(btnAuthor);

		btnBooks = new JButton("Cetagory");
		btnBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBooks.setEnabled(false);
				Adminlogindone.this.setVisible(false);
				ctagory k = new ctagory();
				k.setSize(1004, 421);
				k.setLocationRelativeTo(null);
				k.setVisible(true);

			}
		});
		btnBooks.setFont(new Font("Agency FB", Font.BOLD, 18));
		btnBooks.setBounds(128, 240, 167, 37);
		getContentPane().add(btnBooks);

		btnBooks_1 = new JButton("Books");
		btnBooks_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBooks_1.setEnabled(false);
				Adminlogindone.this.setVisible(false);
				Book l = new Book();
				l.setSize(1300, 600);
				l.setLocationRelativeTo(null);
				l.setVisible(true);

			}
		});
		btnBooks_1.setFont(new Font("Agency FB", Font.BOLD, 18));
		btnBooks_1.setBounds(128, 300, 167, 37);
		getContentPane().add(btnBooks_1);

		btnIssue = new JButton("Issue Book");
		btnIssue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnIssue.setEnabled(false);
				Adminlogindone.this.setVisible(false);
				Issuebook l = new Issuebook();
				l.setSize(1200, 600);
				l.setLocationRelativeTo(null);
				l.setVisible(true);

			}
		});
		btnIssue.setFont(new Font("Agency FB", Font.BOLD, 18));
		btnIssue.setBounds(128, 357, 167, 37);
		getContentPane().add(btnIssue);

		btnReturnBook = new JButton("Return Book");
		btnReturnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnReturnBook.setEnabled(false);
				Adminlogindone.this.setVisible(false);
				Return2 m = new Return2();
				m.setSize(1200, 600);
				m.setLocationRelativeTo(null);
				m.setVisible(true);

			}
		});
		btnReturnBook.setFont(new Font("Agency FB", Font.BOLD, 18));
		btnReturnBook.setBounds(128, 429, 167, 37);
		getContentPane().add(btnReturnBook);

		btnLogOut = new JButton("Log Out");

		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLogOut.setEnabled(false);

				Adminlogindone.this.setVisible(false);
				AdminLogin.main(new String[] {});

			}
		});
		btnLogOut.setFont(new Font("Agency FB", Font.BOLD, 18));
		btnLogOut.setBounds(128, 491, 167, 37);
		getContentPane().add(btnLogOut);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 0, 432, 603);
		getContentPane().add(lblNewLabel_1);
	}
}
