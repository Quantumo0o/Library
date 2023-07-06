import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

@SuppressWarnings("serial")
public class Author extends JFrame {
	static Author frame;
	private JTextField txtname;
	private JTextField txtphone;
	private JTextField txtadd;
	private JButton delete;
	private JTable table1;
	ResultSet rs;
	PreparedStatement pst;
	private JButton add;
	private JButton update;
	private JButton back;

	public Author() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("smiley.png")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(0, 0, 51));

		getContentPane().setMinimumSize(new Dimension(660, 410));
		getContentPane().setSize(new Dimension(10, 10));
		getContentPane().setPreferredSize(new Dimension(10, 10));
		getContentPane().setName("");

		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Author");
		lblNewLabel.setForeground(new Color(102, 102, 255));
		lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 28));
		lblNewLabel.setBounds(178, 13, 182, 52);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Author Name");
		lblNewLabel_1.setForeground(new Color(153, 102, 255));
		lblNewLabel_1.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblNewLabel_1.setBounds(47, 78, 141, 16);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Address");
		lblNewLabel_2.setForeground(new Color(153, 102, 255));
		lblNewLabel_2.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblNewLabel_2.setBounds(47, 143, 56, 16);
		getContentPane().add(lblNewLabel_2);

		txtname = new JTextField();
		txtname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtname.setBounds(170, 77, 216, 22);
		getContentPane().add(txtname);
		txtname.setColumns(10);

		add = new JButton("Add");
		add.setFont(new Font("Agency FB", Font.BOLD, 18));
		add.setBounds(61, 429, 97, 37);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtname.getText();
				String address = txtadd.getText();
				String phone = txtphone.getText();

				Connection con = null;
				try {

					con = DB.getConnection();
					PreparedStatement pst1 = con.prepareStatement("Select*from author where name=?");
					pst1.setString(1, name);
					rs = pst1.executeQuery();
					if (rs.next() == false) {
						PreparedStatement pst = con
								.prepareStatement("insert into author(`name`,`add`,`Phone`)" + "values(?,?,?)");
						pst.setString(1, name);
						pst.setString(2, address);
						pst.setString(3, phone);

						int k = pst.executeUpdate();

						if (k == 1) {
							JOptionPane.showMessageDialog(null, "Auther Created");
							txtname.setText("");
							txtadd.setText("");
							txtphone.setText("");
							txtname.requestFocus();
							Authorload();
						} else {
							JOptionPane.showMessageDialog(null, "ERRORR..");

						}
					} else {
						JOptionPane.showMessageDialog(null, "Dulicate..");
					}

					con.close();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}

		});

		getContentPane().add(add);

		update = new JButton("Update");
		update.setFont(new Font("Agency FB", Font.BOLD, 18));
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel d2 = (DefaultTableModel) table1.getModel();
				int selectIndex = table1.getSelectedRow();
				int ID = Integer.parseInt(d2.getValueAt(selectIndex, 0).toString());

				String name = txtname.getText();
				String address = txtadd.getText();
				String phone = txtphone.getText();

				try {
					Connection con = DB.getConnection();
					PreparedStatement pst1 = con
							.prepareStatement("Select*from author where name=? and `add`=? and Phone=? ");
					pst1.setString(1, name);
					pst1.setString(2, address);
					pst1.setString(3, phone);
					rs = pst1.executeQuery();
					if (rs.next() == false) {
						PreparedStatement pst = con
								.prepareStatement("update author set `name`=?,`add`=?,`Phone`=? where `ID`=?");
						pst.setString(1, name);
						pst.setString(2, address);
						pst.setString(3, phone);
						pst.setInt(4, ID);

						int k = pst.executeUpdate();

						if (k == 1) {
							JOptionPane.showMessageDialog(null, "Author Updated");
							txtname.setText("");
							txtadd.setText("");
							txtphone.setText("");
							txtname.requestFocus();
							Authorload();
							add.setEnabled(true);
							update.setEnabled(false);
							delete.setEnabled(false);
						} else {
							JOptionPane.showMessageDialog(null, "ERRORR..");

						}
					} else {
						JOptionPane.showMessageDialog(null, "Duplicate..");
						txtname.setText("");
						txtadd.setText("");
						txtphone.setText("");
						txtname.requestFocus();

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
		update.setBounds(170, 429, 97, 37);
		getContentPane().add(update);

		delete = new JButton("Delete");
		delete.setFont(new Font("Agency FB", Font.BOLD, 18));
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel d2 = (DefaultTableModel) table1.getModel();
				int selectIndex = table1.getSelectedRow();
				int ID = Integer.parseInt(d2.getValueAt(selectIndex, 0).toString());

				try {
					Connection con = DB.getConnection();
					PreparedStatement pst = con.prepareStatement("delete from author where ID=?");

					pst.setInt(1, ID);

					int k = pst.executeUpdate();

					if (k == 1) {
						JOptionPane.showMessageDialog(null, "Author Deleted");
						txtname.setText("");
						txtadd.setText("");
						txtphone.setText("");
						txtname.requestFocus();
						Authorload();
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
		delete.setBounds(284, 429, 97, 37);
		getContentPane().add(delete);

		back = new JButton("Back");
		back.setFont(new Font("Agency FB", Font.BOLD, 18));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back.setEnabled(false);
				Author.this.setVisible(false);
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
				Author.this.setVisible(false);
			}
		});
		back.setBounds(170, 475, 97, 37);
		getContentPane().add(back);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(455, 37, 670, 475);
		scrollPane.setViewportBorder(UIManager.getBorder("TableHeader.cellBorder"));
		scrollPane.setToolTipText("");
		getContentPane().add(scrollPane);

		table1 = new JTable();
		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				DefaultTableModel d2 = (DefaultTableModel) table1.getModel();
				int selectIndex = table1.getSelectedRow();
				int id = Integer.parseInt(d2.getValueAt(selectIndex, 0).toString());
				txtname.setText(d2.getValueAt(selectIndex, 1).toString());
				txtadd.setText(d2.getValueAt(selectIndex, 2).toString());
				txtphone.setText(d2.getValueAt(selectIndex, 3).toString());
				add.setEnabled(false);
				update.setEnabled(true);
				delete.setEnabled(true);
			}
		});
		scrollPane.setViewportView(table1);
		table1.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Author Name", "Address", "Phone No." }) {
					Class[] columnTypes = new Class[] { Integer.class, Object.class, Object.class, Integer.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});

		JLabel lblNewLabel_3 = new JLabel("Phone No.");
		lblNewLabel_3.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblNewLabel_3.setForeground(new Color(153, 102, 255));
		lblNewLabel_3.setBounds(47, 245, 72, 16);
		getContentPane().add(lblNewLabel_3);

		txtphone = new JTextField();
		txtphone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtphone.setBounds(170, 242, 216, 22);
		getContentPane().add(txtphone);
		txtphone.setColumns(10);

		txtadd = new JTextField();
		txtadd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtadd.setBounds(170, 143, 216, 60);
		getContentPane().add(txtadd);
		txtadd.setColumns(10);
		Authorload();
		update.setEnabled(false);
		delete.setEnabled(false);
	}

	public void Authorload() {
		int c;
		try {
			Connection con = DB.getConnection();
			pst = con.prepareStatement("select*from author");
			rs = pst.executeQuery();
			ResultSetMetaData rsd = rs.getMetaData();
			c = rsd.getColumnCount();
			DefaultTableModel d = (DefaultTableModel) table1.getModel();
			d.setRowCount(0);

			while (rs.next()) {

				Vector v2 = new Vector();
				for (int i = 1; i <= c; i++) {

					v2.add(rs.getString("id"));
					v2.add(rs.getString("name"));
					v2.add(rs.getString("add"));
					v2.add(rs.getString("Phone"));

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
					frame = new Author();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
