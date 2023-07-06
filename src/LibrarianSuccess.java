import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class LibrarianSuccess extends JFrame {
	static LibrarianSuccess frame;
	private JPanel contentPane;
	private JTable table2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LibrarianSuccess();
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
	ResultSet rs;
	PreparedStatement pst;
	private JButton btnLogout;

	public LibrarianSuccess() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1031, 462);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("smiley.png")));

		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setForeground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblLibrarianSection = new JLabel("Student Section");
		lblLibrarianSection.setForeground(new Color(153, 102, 255));
		lblLibrarianSection.setFont(new Font("Algerian", Font.BOLD, 28));

		JButton btnViewBooks = new JButton("View Books");
		btnViewBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viewbook v = new viewbook();
				v.setSize(1200, 500);
				v.setLocationRelativeTo(null);
				v.setVisible(true);
			}
		});
		btnViewBooks.setFont(new Font("Agency FB", Font.BOLD, 24));

		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLogout.setEnabled(false);
				LibrarianSuccess.this.setVisible(false);
				Library.main(new String[] {});
				frame.dispose();
			}
		});
		btnLogout.setFont(new Font("Agency FB", Font.BOLD, 24));

		JScrollPane scrollPane = new JScrollPane();

		JButton btnNewButton = new JButton("Change Password");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.disable();
				ViewIssuedBooks.main(new String[] {});

			}
		});
		btnNewButton.setFont(new Font("Agency FB", Font.BOLD, 24));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup().addGap(132)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnLogout, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
										.addComponent(btnViewBooks, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)))
						.addGap(102)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 571, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(379).addComponent(lblLibrarianSection)))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap().addComponent(lblLibrarianSection)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(73)
								.addComponent(btnViewBooks, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addGap(67)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addGap(75)
								.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(18).addComponent(scrollPane,
								GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)))
				.addContainerGap()));

		table2 = new JTable();
		table2.setForeground(new Color(0, 0, 0));
		table2.setBackground(new Color(255, 255, 255));
		table2.setFont(new Font("Agency FB", Font.PLAIN, 12));
		table2.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Student Name", "Book", "Issue Date", "Return date" }) {
			Class[] columnTypes = new Class[] { Integer.class, Object.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table2.getColumnModel().getColumn(1).setPreferredWidth(104);
		table2.getColumnModel().getColumn(2).setPreferredWidth(147);
		table2.getColumnModel().getColumn(4).setPreferredWidth(110);
		scrollPane.setViewportView(table2);
		contentPane.setLayout(gl_contentPane);
		issuevload();
	}

	public void issuevload() {
		int c;
		LibrarianDao mname = new LibrarianDao();

		try {
			Connection con = DB.getConnection();
			pst = con.prepareStatement(
					"select i.id,m.name,b.bname,i.idate,i.rdate from issue i join librarian m on i.mid=m.id join book2 b on i.book=b.id where m.name=?");
			pst.setString(1, mname.mamname);
			rs = pst.executeQuery();
			ResultSetMetaData rsd = rs.getMetaData();
			c = rsd.getColumnCount();
			DefaultTableModel d = (DefaultTableModel) table2.getModel();
			d.setRowCount(0);

			while (rs.next()) {

				Vector v2 = new Vector();
				for (int i = 1; i <= c; i++) {

					v2.add(rs.getString("i.id"));
					v2.add(rs.getString("m.name"));
					v2.add(rs.getString("b.bname"));
					v2.add(rs.getString("i.idate"));
					v2.add(rs.getString("i.rdate"));

				}

				d.addRow(v2);

			}

			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
}
