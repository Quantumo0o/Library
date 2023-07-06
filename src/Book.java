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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class Book extends JFrame {
	private JTable table1;
	ResultSet rs;
	PreparedStatement pst;
	private JTextField textname;
	private JTextField txtcontent;
	private JTextField txtpage;
	private JTextField txtedition;
	private JComboBox txtcat;
	private JComboBox txtauthor;
	private JComboBox txtpublisher;
	private JButton add;
	private JButton update;
	private JTextField txtqty;
	private JButton delete;
	private JButton back;

	public Book() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(0, 0, 51));
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("smiley.png")));

		getContentPane().setMinimumSize(new Dimension(660, 410));
		getContentPane().setSize(new Dimension(10, 10));
		getContentPane().setPreferredSize(new Dimension(10, 10));
		getContentPane().setName("");
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Book Manager");
		lblNewLabel.setBounds(112, 7, 261, 37);
		lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 28));
		lblNewLabel.setForeground(new Color(153, 102, 255));
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Cetagory Name");
		lblNewLabel_1.setBounds(48, 134, 141, 30);
		lblNewLabel_1.setForeground(new Color(153, 102, 255));
		lblNewLabel_1.setFont(new Font("Agency FB", Font.BOLD, 18));
		getContentPane().add(lblNewLabel_1);

		add = new JButton("Add");
		add.setBounds(71, 453, 97, 37);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textname.getText();
				CetItem category = (CetItem) txtcat.getSelectedItem();
				AutItem authoe = (AutItem) txtauthor.getSelectedItem();
				PubItem publisher = (PubItem) txtpublisher.getSelectedItem();
				String content = txtcontent.getText();
				String page = txtpage.getText();
				String editon = txtedition.getText();
				String qty = txtqty.getText();

				try {
					Connection con = DB.getConnection();
					PreparedStatement pst1 = con.prepareStatement(
							"select*from book2 where bname=? and category=? and author=? and publisher=? and con=? and pag=? and edition=?");
					pst1.setString(1, name);
					pst1.setInt(2, category.id);
					pst1.setInt(3, authoe.id);
					pst1.setInt(4, publisher.id);
					pst1.setString(5, content);
					pst1.setString(6, page);
					pst1.setString(7, editon);

					rs = pst1.executeQuery();
					con.close();
					if (rs.next() == false) {
						PreparedStatement pst = con.prepareStatement(
								"insert into book2(`bname`,`category`,`author`,`publisher`,`con`,`pag`,`edition`,`qty`) Value(?,?,?,?,?,?,?,?)");
						pst.setString(1, name);
						pst.setInt(2, category.id);
						pst.setInt(3, authoe.id);
						pst.setInt(4, publisher.id);
						pst.setString(5, content);
						pst.setString(6, page);
						pst.setString(7, editon);
						pst.setString(8, qty);

						int k = pst.executeUpdate();
						con.close();
						if (k == 1) {
							JOptionPane.showMessageDialog(null, "Book Created");
							textname.setText("");
							txtcat.setSelectedIndex(-1);
							txtauthor.setSelectedIndex(-1);
							txtpublisher.setSelectedIndex(-1);
							txtcontent.setText("");
							txtpage.setText("");
							txtedition.setText("");
							txtqty.setText("");
							textname.requestFocus();
							bookload();
						} else {
							JOptionPane.showMessageDialog(null, "ERRORR..");

						}
					} else {
						JOptionPane.showMessageDialog(null, "Duplicat..");
						textname.setText("");
						txtcat.setSelectedIndex(-1);
						txtauthor.setSelectedIndex(-1);
						txtpublisher.setSelectedIndex(-1);
						txtcontent.setText("");
						txtpage.setText("");
						txtedition.setText("");
						txtqty.setText("");
						textname.requestFocus();
					}

					con.close();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}

		});

		getContentPane().add(add);

		update = new JButton("Update");
		update.setBounds(180, 453, 97, 37);
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel d2 = (DefaultTableModel) table1.getModel();
				int selectIndex = table1.getSelectedRow();
				int ID = Integer.parseInt(d2.getValueAt(selectIndex, 0).toString());

				String name = textname.getText();
				CetItem category = (CetItem) txtcat.getSelectedItem();
				AutItem authoe = (AutItem) txtauthor.getSelectedItem();
				PubItem publisher = (PubItem) txtpublisher.getSelectedItem();
				String content = txtcontent.getText();
				String page = txtpage.getText();
				String editon = txtedition.getText();
				String qty = txtqty.getText();

				try {
					Connection con = DB.getConnection();
					PreparedStatement pst1 = con.prepareStatement(
							"select*from book2 where bname=? and category=? and author=? and publisher=? and con=? and pag=? and edition=? and qty=?");
					pst1.setString(1, name);
					pst1.setInt(2, category.id);
					pst1.setInt(3, authoe.id);
					pst1.setInt(4, publisher.id);
					pst1.setString(5, content);
					pst1.setString(6, page);
					pst1.setString(7, editon);
					pst1.setString(8, qty);

					rs = pst1.executeQuery();
					if (rs.next() == false) {

						PreparedStatement pst = con.prepareStatement(
								"update book2 set `bname`=?,`category`=?,`author`=?,`publisher`=?,`con`=?,`pag`=?,`edition`=?, `qty`=? where ID=?");
						pst.setString(1, name);
						pst.setInt(2, category.id);
						pst.setInt(3, authoe.id);
						pst.setInt(4, publisher.id);
						pst.setString(5, content);
						pst.setString(6, page);
						pst.setString(7, editon);
						pst.setString(8, qty);
						pst.setInt(9, ID);

						int k = pst.executeUpdate();

						if (k == 1) {
							JOptionPane.showMessageDialog(null, "Book Updated");
							textname.setText("");
							txtcat.setSelectedIndex(-1);
							txtauthor.setSelectedIndex(-1);
							txtpublisher.setSelectedIndex(-1);
							txtcontent.setText("");
							txtpage.setText("");
							txtedition.setText("");
							txtqty.setText("");
							textname.requestFocus();
							bookload();
							add.setEnabled(true);
							update.setEnabled(false);
							delete.setEnabled(false);
						} else {
							JOptionPane.showMessageDialog(null, "ERRORR..");

						}
					} else {

						JOptionPane.showMessageDialog(null, "Duplicat..");
						textname.setText("");
						txtcat.setSelectedIndex(-1);
						txtauthor.setSelectedIndex(-1);
						txtpublisher.setSelectedIndex(-1);
						txtcontent.setText("");
						txtpage.setText("");
						txtedition.setText("");
						txtqty.setText("");
						textname.requestFocus();
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

		delete = new JButton("Delete");
		delete.setBounds(289, 453, 97, 37);
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel d2 = (DefaultTableModel) table1.getModel();
				int selectIndex = table1.getSelectedRow();
				int ID = Integer.parseInt(d2.getValueAt(selectIndex, 0).toString());

				try {
					Connection con = DB.getConnection();
					PreparedStatement pst = con.prepareStatement("delete from book2 where ID=?");

					pst.setInt(1, ID);

					int k = pst.executeUpdate();

					if (k == 1) {
						JOptionPane.showMessageDialog(null, "Book Deleted");
						textname.setText("");
						txtcat.setSelectedIndex(-1);
						txtauthor.setSelectedIndex(-1);
						txtpublisher.setSelectedIndex(-1);
						txtcontent.setText("");
						txtpage.setText("");
						txtedition.setText("");
						textname.requestFocus();
						bookload();
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
		back.setBounds(180, 503, 97, 37);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back.setEnabled(false);
				Book.this.setVisible(false);
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

			}
		});
		getContentPane().add(back);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(404, 13, 848, 504);
		scrollPane.setViewportBorder(UIManager.getBorder("TableHeader.cellBorder"));
		scrollPane.setToolTipText("");
		getContentPane().add(scrollPane);

		table1 = new JTable();
		table1.setColumnSelectionAllowed(true);
		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				DefaultTableModel d2 = (DefaultTableModel) table1.getModel();
				int selectIndex = table1.getSelectedRow();
				int id = Integer.parseInt(d2.getValueAt(selectIndex, 0).toString());
				textname.setText(d2.getValueAt(selectIndex, 1).toString());
				txtcat.setSelectedItem(d2.getValueAt(selectIndex, 2).toString());
				txtauthor.setSelectedItem(d2.getValueAt(selectIndex, 3).toString());
				txtpublisher.setSelectedItem(d2.getValueAt(selectIndex, 4).toString());
				txtcontent.setText(d2.getValueAt(selectIndex, 5).toString());
				txtpage.setText(d2.getValueAt(selectIndex, 6).toString());
				txtedition.setText(d2.getValueAt(selectIndex, 7).toString());
				add.setEnabled(false);
				update.setEnabled(true);
				delete.setEnabled(true);

			}
		});
		scrollPane.setViewportView(table1);
		table1.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Book Name", "Category", "Author",
				"Publisher", "Contents", "No of Page", "Edition", "Qty." }) {
			Class[] columnTypes = new Class[] { Integer.class, Object.class, Object.class, Object.class, Object.class,
					Object.class, Integer.class, Object.class, Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table1.getColumnModel().getColumn(0).setPreferredWidth(35);
		table1.getColumnModel().getColumn(1).setPreferredWidth(149);
		table1.getColumnModel().getColumn(5).setPreferredWidth(149);
		table1.getColumnModel().getColumn(6).setPreferredWidth(89);
		table1.getColumnModel().getColumn(7).setPreferredWidth(92);
		table1.getColumnModel().getColumn(8).setPreferredWidth(46);

		JLabel lblNewLabel_3 = new JLabel("Name");
		lblNewLabel_3.setBounds(48, 85, 71, 21);
		lblNewLabel_3.setForeground(new Color(153, 102, 255));
		lblNewLabel_3.setFont(new Font("Agency FB", Font.BOLD, 18));
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Author");
		lblNewLabel_4.setBounds(48, 177, 56, 38);
		lblNewLabel_4.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblNewLabel_4.setForeground(new Color(153, 102, 255));
		getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Publisher");
		lblNewLabel_5.setBounds(48, 228, 68, 34);
		lblNewLabel_5.setForeground(new Color(153, 102, 255));
		lblNewLabel_5.setFont(new Font("Agency FB", Font.BOLD, 18));
		getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Contents");
		lblNewLabel_6.setBounds(48, 275, 71, 32);
		lblNewLabel_6.setForeground(new Color(153, 102, 255));
		lblNewLabel_6.setFont(new Font("Agency FB", Font.BOLD, 18));
		getContentPane().add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("No. of Pages");
		lblNewLabel_7.setBounds(48, 320, 91, 28);
		lblNewLabel_7.setForeground(new Color(153, 102, 255));
		lblNewLabel_7.setFont(new Font("Agency FB", Font.BOLD, 18));
		getContentPane().add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Edition");
		lblNewLabel_8.setBounds(48, 361, 71, 30);
		lblNewLabel_8.setForeground(new Color(153, 102, 255));
		lblNewLabel_8.setFont(new Font("Agency FB", Font.BOLD, 18));
		getContentPane().add(lblNewLabel_8);

		textname = new JTextField();
		textname.setBounds(180, 86, 116, 22);
		getContentPane().add(textname);
		textname.setColumns(10);

		txtcat = new JComboBox();
		txtcat.setBounds(180, 137, 116, 22);
		getContentPane().add(txtcat);

		txtauthor = new JComboBox();
		txtauthor.setBounds(180, 187, 97, 22);
		getContentPane().add(txtauthor);

		txtpublisher = new JComboBox();
		txtpublisher.setBounds(180, 236, 101, 22);
		getContentPane().add(txtpublisher);

		txtcontent = new JTextField();
		txtcontent.setBounds(180, 283, 116, 22);
		getContentPane().add(txtcontent);
		txtcontent.setColumns(10);

		txtpage = new JTextField();
		txtpage.setBounds(180, 328, 116, 22);
		getContentPane().add(txtpage);
		txtpage.setColumns(10);

		txtedition = new JTextField();
		txtedition.setBounds(180, 369, 116, 22);
		getContentPane().add(txtedition);
		txtedition.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(48, 415, 56, 16);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_8_1 = new JLabel("Qyt.");
		lblNewLabel_8_1.setForeground(new Color(153, 102, 255));
		lblNewLabel_8_1.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblNewLabel_8_1.setBounds(48, 401, 71, 30);
		getContentPane().add(lblNewLabel_8_1);

		txtqty = new JTextField();
		txtqty.setBounds(180, 404, 56, 22);
		getContentPane().add(txtqty);
		txtqty.setColumns(10);

		category();
		Auther();
		publisher();
		bookload();
		update.setEnabled(false);
		delete.setEnabled(false);
	}

	public class CetItem {

		int id;
		String name;

		public CetItem(int id, String name) {
			this.id = id;
			this.name = name;

		}

		public String toString() {
			return name;
		}
	}

	public class AutItem {

		int id;
		String name;

		public AutItem(int id, String name) {
			this.id = id;
			this.name = name;

		}

		public String toString() {
			return name;
		}
	}

	public class PubItem {

		int id;
		String name;

		public PubItem(int id, String name) {
			this.id = id;
			this.name = name;

		}

		public String toString() {
			return name;
		}
	}

	public void bookload() {
		int c;
		try {
			Connection con = DB.getConnection();
			pst = con.prepareStatement(
					"select b1.id,b1.bname,c.CategoryName,a.name,p.name,b1.con,b1.pag,b1.edition,b1.qty from book2 b1 join category c on b1.category=c.id join author a on b1.author=a.id join publisher p on b1.publisher=p.id");
			rs = pst.executeQuery();
			ResultSetMetaData rsd = rs.getMetaData();
			c = rsd.getColumnCount();
			DefaultTableModel d = (DefaultTableModel) table1.getModel();
			d.setRowCount(0);

			while (rs.next()) {

				Vector v2 = new Vector();
				for (int i = 1; i <= c; i++) {

					v2.add(rs.getString("b1.id"));
					v2.add(rs.getString("b1.bname"));
					v2.add(rs.getString("c.CategoryName"));
					v2.add(rs.getString("a.name"));
					v2.add(rs.getString("p.name"));
					v2.add(rs.getString("b1.con"));
					v2.add(rs.getString("b1.pag"));
					v2.add(rs.getString("b1.edition"));

					v2.add(rs.getString("b1.qty"));

				}

				d.addRow(v2);

			}

			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void category() {
		Connection con = DB.getConnection();
		try {
			pst = con.prepareStatement("select*from category");
			rs = pst.executeQuery();
			txtcat.removeAllItems();

			while (rs.next()) {
				txtcat.addItem(new CetItem(rs.getInt(1), rs.getString(2)));
			}

			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void Auther() {
		Connection con = DB.getConnection();
		try {
			pst = con.prepareStatement("select*from author");
			rs = pst.executeQuery();
			txtauthor.removeAllItems();

			while (rs.next()) {
				txtauthor.addItem(new AutItem(rs.getInt(1), rs.getString(2)));
			}

			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void publisher() {
		Connection con = DB.getConnection();
		try {
			pst = con.prepareStatement("select*from publisher");
			rs = pst.executeQuery();
			txtpublisher.removeAllItems();

			while (rs.next()) {
				txtpublisher.addItem(new PubItem(rs.getInt(1), rs.getString(2)));
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
					Book frame = new Book();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
