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

public class Return2 extends JFrame {
	private JTable table1;
	ResultSet rs;
	PreparedStatement pst;
	private JTextField txtid;
	private JButton add;
	private JTextField txtlate;
	private JTextField txtfine;
	private JLabel txtmam;
	private JLabel txtbook;
	private JLabel rshubham;
	private JTable table3;
	private JButton back;

	int bookqty;

	public Return2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(0, 0, 51));
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("smiley.png")));

		getContentPane().setMinimumSize(new Dimension(660, 410));
		getContentPane().setSize(new Dimension(10, 10));
		getContentPane().setPreferredSize(new Dimension(10, 10));
		getContentPane().setName("");

		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Return Book");
		lblNewLabel.setForeground(new Color(153, 102, 255));
		lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 28));
		lblNewLabel.setBounds(119, 6, 251, 39);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Member Name");
		lblNewLabel_1.setForeground(new Color(153, 102, 255));
		lblNewLabel_1.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblNewLabel_1.setBounds(47, 114, 149, 26);
		getContentPane().add(lblNewLabel_1);

		add = new JButton("Add");
		add.setFont(new Font("Agency FB", Font.BOLD, 18));
		add.setBounds(180, 419, 97, 35);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mid = txtid.getText();
				String mam = txtmam.getText();
				String bookn = txtbook.getText();
				String returndate = rshubham.getText();
				String elpdat = txtlate.getText();
				String fine = txtfine.getText();

				try {

					Connection con = DB.getConnection();
					PreparedStatement pst = con.prepareStatement(
							"insert into returnbook (`mid`,`mname`,`bname`,`rdate`,`elp`,`fine`) Value(?,?,?,?,?,?)");
					pst.setString(1, mid);
					pst.setString(2, mam);
					pst.setString(3, bookn);
					pst.setString(4, returndate);
					pst.setString(5, elpdat);
					pst.setString(6, fine);
					int k = pst.executeUpdate();

					pst = con.prepareStatement("delete from issue where id=? ");
					pst.setInt(1, bookqty);
					pst.executeUpdate();
					PreparedStatement pst1 = con.prepareStatement("update book2 set qty=qty+1 where bname=?");
					pst1.setString(1, bookn);
					pst1.executeUpdate();

					if (k == 1) {
						JOptionPane.showMessageDialog(null, "Book Returned");
						txtid.setText("");
						txtmam.setText("");
						txtbook.setText("");
						rshubham.setText("");
						txtlate.setText("");
						txtfine.setText("");

						txtid.requestFocus();
						retureload();
						issuevload();

					} else {
						JOptionPane.showMessageDialog(null, "ERRORR..");

					}

					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		getContentPane().add(add);

		back = new JButton("Back");
		back.setFont(new Font("Agency FB", Font.BOLD, 18));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back.setEnabled(false);
				Return2.this.setVisible(false);
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

			}
		});
		back.setBounds(180, 498, 97, 35);
		getContentPane().add(back);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(458, 13, 703, 267);
		scrollPane.setViewportBorder(UIManager.getBorder("TableHeader.cellBorder"));
		scrollPane.setToolTipText("");
		getContentPane().add(scrollPane);

		table1 = new JTable();

		scrollPane.setViewportView(table1);
		table1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Member ID", "Member Name", "Book", "Return Book", "Day Elap", "Fine" }) {
			Class[] columnTypes = new Class[] { Integer.class, Integer.class, Object.class, Object.class, Object.class,
					Object.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table1.getColumnModel().getColumn(0).setPreferredWidth(32);
		table1.getColumnModel().getColumn(2).setPreferredWidth(140);
		table1.getColumnModel().getColumn(3).setPreferredWidth(168);
		table1.getColumnModel().getColumn(4).setPreferredWidth(99);
		table1.getColumnModel().getColumn(5).setPreferredWidth(89);

		JLabel lblNewLabel_3 = new JLabel("Member ID");
		lblNewLabel_3.setForeground(new Color(153, 102, 255));
		lblNewLabel_3.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblNewLabel_3.setBounds(47, 65, 121, 16);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Book");
		lblNewLabel_4.setForeground(new Color(153, 102, 255));
		lblNewLabel_4.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblNewLabel_4.setBounds(47, 178, 69, 24);
		getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_8 = new JLabel("Return Date");
		lblNewLabel_8.setForeground(new Color(153, 102, 255));
		lblNewLabel_8.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblNewLabel_8.setBounds(47, 221, 121, 35);
		getContentPane().add(lblNewLabel_8);

		txtid = new JTextField();
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					String id = txtid.getText();

					Connection con = DB.getConnection();
					try {

						pst = con.prepareStatement(
								"select m.name,b.bname,i.rdate,Datediff(now(),i.rdate) as elap from issue i join librarian m on i.mid=m.id join book2 b on i.book=b.id where i.mid=?");
						pst.setString(1, id);
						pst.executeQuery();
						rs = pst.executeQuery();
						if (rs.next() == false) {

							JOptionPane.showMessageDialog(null, "this ID not found");
							con.close();
						} else {

							String mname = rs.getString("m.name");
							txtmam.setText(mname.trim());
							issuevload();

						}

						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		txtid.setBounds(176, 61, 183, 22);
		getContentPane().add(txtid);
		txtid.setColumns(10);

		txtmam = new JLabel("First Enter Member ID");
		txtmam.setFont(new Font("Agency FB", Font.BOLD, 18));
		txtmam.setForeground(new Color(153, 102, 255));
		txtmam.setBounds(180, 114, 179, 23);
		getContentPane().add(txtmam);

		txtbook = new JLabel("Select Book From Below Table");
		txtbook.setForeground(new Color(153, 102, 255));
		txtbook.setFont(new Font("Agency FB", Font.BOLD, 18));
		txtbook.setBounds(180, 178, 183, 22);
		getContentPane().add(txtbook);

		JLabel lblNewLabel_6 = new JLabel("Day Elap");
		lblNewLabel_6.setForeground(new Color(153, 102, 255));
		lblNewLabel_6.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblNewLabel_6.setBounds(47, 266, 73, 47);
		getContentPane().add(lblNewLabel_6);

		txtlate = new JTextField();
		txtlate.setBounds(180, 280, 116, 22);
		getContentPane().add(txtlate);
		txtlate.setColumns(10);

		txtfine = new JTextField();
		txtfine.setBounds(180, 340, 116, 22);
		getContentPane().add(txtfine);
		txtfine.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Fine");
		lblNewLabel_7.setForeground(new Color(153, 102, 255));
		lblNewLabel_7.setFont(new Font("Agency FB", Font.BOLD, 19));
		lblNewLabel_7.setBounds(47, 332, 73, 35);
		getContentPane().add(lblNewLabel_7);

		rshubham = new JLabel("Select Book From Below Table");
		rshubham.setForeground(new Color(153, 102, 255));
		rshubham.setFont(new Font("Agency FB", Font.BOLD, 18));
		rshubham.setBounds(180, 225, 196, 26);
		getContentPane().add(rshubham);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(458, 293, 703, 240);
		getContentPane().add(scrollPane_1);

		table3 = new JTable();
		table3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				DefaultTableModel d2 = (DefaultTableModel) table3.getModel();
				int selectIndex = table3.getSelectedRow();
				int id = Integer.parseInt(d2.getValueAt(selectIndex, 0).toString());
				bookqty = id;
				rshubham.setText(d2.getValueAt(selectIndex, 4).toString());
				txtbook.setText(d2.getValueAt(selectIndex, 2).toString());
				String mamname = txtid.getText();
				String rnbook = txtbook.getText();

				Connection con = DB.getConnection();
				try {

					pst = con.prepareStatement(
							"select m.name,b.bname,i.rdate,Datediff(now(),i.rdate) as elap from issue i join librarian m on i.mid=m.id join book2 b on i.book=b.id where i.mid=? and b.bname=?");
					pst.setString(1, mamname);
					pst.setString(2, rnbook);

					ResultSet rs2 = pst.executeQuery();
					if (rs2.next() == false) {

						JOptionPane.showMessageDialog(null, "this ID not found");
						con.close();
					} else {

						String elps = rs2.getString("elap");
						int elaped = Integer.parseInt(elps);
						if (elaped > 0) {
							txtlate.setText(elps);
							int fine = elaped * 10;
							txtfine.setText(String.valueOf(fine));
						} else {

							txtlate.setText("0");
							txtfine.setText("0");
						}
						con.close();
					}

					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		table3.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Member Name", "Book", "Issue Date", "Return Date" }) {
			Class[] columnTypes = new Class[] { Integer.class, Object.class, Object.class, Object.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { true, true, false, true, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table3.getColumnModel().getColumn(0).setPreferredWidth(48);
		table3.getColumnModel().getColumn(1).setPreferredWidth(120);
		table3.getColumnModel().getColumn(2).setPreferredWidth(143);
		table3.getColumnModel().getColumn(3).setPreferredWidth(94);
		table3.getColumnModel().getColumn(4).setPreferredWidth(104);
		scrollPane_1.setViewportView(table3);

		retureload();

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

	public void retureload() {
		int c;
		try {
			Connection con = DB.getConnection();
			pst = con.prepareStatement("select*from returnbook ");
			rs = pst.executeQuery();
			ResultSetMetaData rsd = rs.getMetaData();
			c = rsd.getColumnCount();
			DefaultTableModel d = (DefaultTableModel) table1.getModel();
			d.setRowCount(0);

			while (rs.next()) {

				Vector v2 = new Vector();
				for (int i = 1; i <= c; i++) {

					v2.add(rs.getString("id"));
					v2.add(rs.getString("mid"));
					v2.add(rs.getString("mname"));
					v2.add(rs.getString("bname"));
					v2.add(rs.getString("rdate"));
					v2.add(rs.getString("elp"));
					v2.add(rs.getString("fine"));

				}

				d.addRow(v2);

			}

			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void issuevload() {
		int c;
		String mamid = txtid.getText();

		try {
			Connection con = DB.getConnection();
			pst = con.prepareStatement(
					"select i.id,m.name,b.bname,i.idate,i.rdate from issue i join librarian m on i.mid=m.id join book2 b on i.book=b.id where i.mid=?");
			pst.setString(1, mamid);
			rs = pst.executeQuery();
			ResultSetMetaData rsd = rs.getMetaData();
			c = rsd.getColumnCount();
			DefaultTableModel d = (DefaultTableModel) table3.getModel();
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
					Return2 frame = new Return2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
