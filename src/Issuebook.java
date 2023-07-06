import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class Issuebook extends JFrame {
	private JTable table1;
	ResultSet rs;
	PreparedStatement pst;
	private JTextField txtid;
	private JButton add;
	private JDateChooser txtissue;
	private JDateChooser txtreturn;
	private JTextField txtbook3;
	private JLabel txtbook2;
	private JLabel txtname;
	private JButton update;
	private JButton delete;
	private JButton back;

	String bookqty;

	public Issuebook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(0, 0, 51));
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("smiley.png")));

		getContentPane().setMinimumSize(new Dimension(660, 410));
		getContentPane().setSize(new Dimension(10, 10));
		getContentPane().setPreferredSize(new Dimension(10, 10));
		getContentPane().setName("");
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Issue Book");
		lblNewLabel.setBounds(122, 9, 243, 60);
		lblNewLabel.setForeground(new Color(153, 102, 255));
		lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 28));
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Student Name");
		lblNewLabel_1.setBounds(47, 143, 182, 50);
		lblNewLabel_1.setForeground(new Color(153, 102, 255));
		lblNewLabel_1.setFont(new Font("Agency FB", Font.BOLD, 18));
		getContentPane().add(lblNewLabel_1);

		add = new JButton("Add");
		add.setBounds(67, 455, 97, 37);
		add.setFont(new Font("Agency FB", Font.BOLD, 18));
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mid = txtid.getText();
				String bookid = txtbook3.getText();
				SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
				String issuedate = datef.format(txtissue.getDate());

				SimpleDateFormat datef1 = new SimpleDateFormat("yyyy-MM-dd");
				String returndate = datef1.format(txtreturn.getDate());

				try {

					Connection con = DB.getConnection();

					PreparedStatement pst = con
							.prepareStatement("insert into issue (`mid`,`book`,`idate`,`rdate`) Value(?,?,?,?)");
					pst.setString(1, mid);
					pst.setString(2, bookid);
					pst.setString(3, issuedate);
					pst.setString(4, returndate);

					int k = pst.executeUpdate();

					if (k == 1) {
						JOptionPane.showMessageDialog(null, "Book issued");
						txtid.setText("");
						txtbook2.setText("First Enter Book ID");
						txtname.setText("First Enter Member ID");
						txtbook3.setText("");

						txtid.requestFocus();
						issueload();
						PreparedStatement pst1 = con.prepareStatement("update book2 set qty=qty-1 where ID=?");
						pst1.setString(1, bookid);
						pst1.executeUpdate();
					} else {
						JOptionPane.showMessageDialog(null, "ERRORR..");

					}

					con.close();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}

		});

		getContentPane().add(add);

		delete = new JButton("Delete");
		delete.setBounds(285, 455, 97, 37);
		delete.setFont(new Font("Agency FB", Font.BOLD, 18));
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel d2 = (DefaultTableModel) table1.getModel();
				int selectIndex = table1.getSelectedRow();
				int ID = Integer.parseInt(d2.getValueAt(selectIndex, 0).toString());

				try {
					Connection con = DB.getConnection();
					PreparedStatement pst = con.prepareStatement("delete from issue where ID=?");

					pst.setInt(1, ID);

					int k = pst.executeUpdate();

					if (k == 1) {

						PreparedStatement pst1 = con.prepareStatement("update book2 set qty=qty+1 where ID=?");
						pst1.setString(1, bookqty);
						pst1.executeUpdate();

						JOptionPane.showMessageDialog(null, "Issuance Deleted");
						txtid.setText("");
						txtbook2.setText("First Enter Book ID");
						txtname.setText("First Enter Member ID");
						txtbook3.setText("");
						issueload();
						add.setEnabled(true);
						update.setEnabled(false);
						delete.setEnabled(false);

					} else {
						JOptionPane.showMessageDialog(null, "ERRORR..");

					}

					con.close();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}
		});
		getContentPane().add(delete);

		back = new JButton("Back");
		back.setBounds(176, 505, 97, 37);
		back.setFont(new Font("Agency FB", Font.BOLD, 18));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back.setEnabled(false);
				Adminlogindone a = new Adminlogindone();
				a.setSize(450, 650);
				a.setLocationRelativeTo(null);
				Connection con = DB.getConnection();
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				a.setVisible(true);
				Issuebook.this.setVisible(false);
			}
		});
		getContentPane().add(back);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(458, 13, 689, 517);
		scrollPane.setViewportBorder(UIManager.getBorder("TableHeader.cellBorder"));
		scrollPane.setToolTipText("");
		getContentPane().add(scrollPane);

		table1 = new JTable();
		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel d2 = (DefaultTableModel) table1.getModel();
				int selectIndex = table1.getSelectedRow();
				int ID = Integer.parseInt(d2.getValueAt(selectIndex, 0).toString());
//				txtid.setText(d2.getValueAt(selectIndex,0).toString());
				String mamid2 = String.valueOf(ID);

				Connection con = DB.getConnection();
				try {
					pst = con.prepareStatement("select*from issue where `id`=?");
					pst.setString(1, mamid2);
					rs = pst.executeQuery();
					if (rs.next() == false) {

						JOptionPane.showMessageDialog(null, "Member ID not Found");
					} else {
						String membername = rs.getString("mid");
						txtid.setText(membername);

						bookqty = rs.getString("book");

						txtbook3.setText(bookqty);

					}

					con.close();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

				txtname.setText(d2.getValueAt(selectIndex, 1).toString());
				txtbook2.setText(d2.getValueAt(selectIndex, 2).toString());
				add.setEnabled(false);

				update.setEnabled(true);
				delete.setEnabled(true);

			}
		});
		table1.setColumnSelectionAllowed(true);

		scrollPane.setViewportView(table1);
		table1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Student Name", "Book", "Issue Date", "Return Date" }));
		table1.getColumnModel().getColumn(0).setPreferredWidth(36);
		table1.getColumnModel().getColumn(1).setPreferredWidth(140);
		table1.getColumnModel().getColumn(2).setPreferredWidth(188);
		table1.getColumnModel().getColumn(3).setPreferredWidth(114);
		table1.getColumnModel().getColumn(4).setPreferredWidth(124);

		JLabel lblNewLabel_3 = new JLabel("Member ID");
		lblNewLabel_3.setBounds(47, 99, 105, 31);
		lblNewLabel_3.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblNewLabel_3.setForeground(new Color(153, 102, 255));
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Book ID");
		lblNewLabel_4.setBounds(47, 196, 171, 49);
		lblNewLabel_4.setForeground(new Color(153, 102, 255));
		lblNewLabel_4.setFont(new Font("Agency FB", Font.BOLD, 18));
		getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_7 = new JLabel("Issue Date");
		lblNewLabel_7.setBounds(47, 304, 141, 37);
		lblNewLabel_7.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblNewLabel_7.setForeground(new Color(153, 102, 255));
		getContentPane().add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Return Date");
		lblNewLabel_8.setBounds(47, 370, 141, 37);
		lblNewLabel_8.setForeground(new Color(153, 102, 255));
		lblNewLabel_8.setFont(new Font("Agency FB", Font.BOLD, 18));
		getContentPane().add(lblNewLabel_8);

		txtid = new JTextField();
		txtid.setBounds(205, 104, 170, 25);
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String mid = txtid.getText();
					Connection con = DB.getConnection();
					try {
						pst = con.prepareStatement("select*from librarian where `id`=?");
						pst.setString(1, mid);
						rs = pst.executeQuery();
						if (rs.next() == false) {

							JOptionPane.showMessageDialog(null, "Member ID not Found");
						} else {

							String membername = rs.getString("name");
							txtname.setText(membername);
						}

						con.close();
					} catch (SQLException e1) {

						e1.printStackTrace();
					}

				}

			}
		});
		getContentPane().add(txtid);
		txtid.setColumns(10);

		txtissue = new JDateChooser();
		txtissue.setBounds(205, 304, 167, 25);
		getContentPane().add(txtissue);

		txtreturn = new JDateChooser();
		txtreturn.setBounds(205, 370, 171, 25);
		getContentPane().add(txtreturn);

		update = new JButton("Update");
		update.setBounds(176, 455, 97, 37);
		update.setFont(new Font("Agency FB", Font.BOLD, 18));
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel d2 = (DefaultTableModel) table1.getModel();
				int selectIndex = table1.getSelectedRow();
				int ID = Integer.parseInt(d2.getValueAt(selectIndex, 0).toString());

				String mid = txtid.getText();

				String bookid = txtbook3.getText();

				SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
				String issuedate = datef.format(txtissue.getDate());

				SimpleDateFormat datef1 = new SimpleDateFormat("yyyy-MM-dd");
				String returndate = datef1.format(txtreturn.getDate());

				try {

					Connection con = DB.getConnection();
					PreparedStatement pst1 = con.prepareStatement("update book2 set qty=qty+1 where ID=?");
					pst1.setString(1, bookqty);
					pst1.executeUpdate();
					PreparedStatement pst = con
							.prepareStatement("update issue set `mid`=?,`book`=?,`idate`=?,`rdate`=? where id=?");
					pst.setString(1, mid);
					pst.setString(2, bookid);
					pst.setString(3, issuedate);
					pst.setString(4, returndate);
					pst.setInt(5, ID);

					int k = pst.executeUpdate();

					if (k == 1) {
						PreparedStatement pst2 = con.prepareStatement("update book2 set qty=qty-1 where ID=?");
						pst2.setString(1, bookid);
						pst2.executeUpdate();
						JOptionPane.showMessageDialog(null, "Book issued Update");
						txtid.setText("");
						txtbook2.setText("First Enter Book ID");
						txtname.setText("First Enter Member ID");
						txtbook3.setText("");

						txtid.requestFocus();
						issueload();
						add.setEnabled(true);
						update.setEnabled(false);
						delete.setEnabled(false);

					} else {
						JOptionPane.showMessageDialog(null, "ERRORR..");

						JOptionPane.showMessageDialog(null, "Book issued Update");
						txtid.setText("");
						txtbook2.setText("First Enter Book ID");
						txtname.setText("First Enter Member ID");
						txtbook3.setText("");

						txtid.requestFocus();
						issueload();
						add.setEnabled(true);
						update.setEnabled(false);
						delete.setEnabled(false);

					}

					con.close();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}
		});
		getContentPane().add(update);

		txtbook3 = new JTextField();
		txtbook3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String mid = txtbook3.getText();
					Connection con = DB.getConnection();
					try {

						pst = con.prepareStatement("select*from book2 where `id`=?");
						pst.setString(1, mid);
						rs = pst.executeQuery();
						if (rs.next() == false) {

							JOptionPane.showMessageDialog(null, "Book ID not Found");
						} else {
							String bookid = txtbook3.getText();
							PreparedStatement pst1 = con.prepareStatement("Select*from book2 where ID=? and qty=0");
							pst1.setString(1, bookid);
							ResultSet rs2 = pst1.executeQuery();
							if (rs2.next() == false) {
								String membername = rs.getString("bname");
								txtbook2.setText(membername);
							} else {

								JOptionPane.showMessageDialog(null, "Book not Availables");

							}
						}

						con.close();
					} catch (SQLException e1) {

						e1.printStackTrace();
					}

				}

			}
		});
		txtbook3.setBounds(205, 209, 141, 25);
		getContentPane().add(txtbook3);
		txtbook3.setColumns(10);

		txtbook2 = new JLabel("First Enter Book ID");
		txtbook2.setBounds(211, 258, 184, 33);
		txtbook2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		txtbook2.setForeground(new Color(0, 255, 255));
		getContentPane().add(txtbook2);

		JLabel lblNewLabel_4_1 = new JLabel("Book Nme");
		lblNewLabel_4_1.setForeground(new Color(153, 102, 255));
		lblNewLabel_4_1.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblNewLabel_4_1.setBounds(47, 242, 171, 49);
		getContentPane().add(lblNewLabel_4_1);

		txtname = new JLabel("First Enter Student ID");
		txtname.setForeground(Color.CYAN);
		txtname.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		txtname.setBounds(205, 150, 241, 33);
		getContentPane().add(txtname);
		issueload();
		update.setEnabled(false);
		delete.setEnabled(false);
	}

	public class Bookid {

		int id;
		String name;

		public Bookid(int id, String name) {
			this.id = id;
			this.name = name;

		}

		public String toString() {
			return name;
		}
	}

	@SuppressWarnings("unchecked")
	public void issueload() {
		int c;
		try {
			Connection con = DB.getConnection();
			pst = con.prepareStatement(
					"select i.id,m.name,b.bname,i.idate,i.rdate from issue i join librarian m on i.mid=m.id join book2 b on i.book=b.id");
			rs = pst.executeQuery();
			ResultSetMetaData rsd = rs.getMetaData();
			c = rsd.getColumnCount();
			DefaultTableModel d = (DefaultTableModel) table1.getModel();
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

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Issuebook frame = new Issuebook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
