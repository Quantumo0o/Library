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

public class member2 extends JFrame {
	private JTextField txtname;
	private JTable table1;
	ResultSet rs;
	PreparedStatement pst;
	private JTextField txtcity;
	private JTextField txtpass;
	private JTextField txtmail;
	private JTextField txtadd;
	private JTextField txtphone;
	private JButton delete;
	private JButton update;
	private JButton back;
	private JButton add;

	public member2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(0, 0, 51));
		getContentPane().setForeground(new Color(0, 0, 51));
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("smiley.png")));

		getContentPane().setMinimumSize(new Dimension(660, 410));
		getContentPane().setSize(new Dimension(10, 10));
		getContentPane().setPreferredSize(new Dimension(10, 10));
		getContentPane().setName("");

		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Student management");
		lblNewLabel.setForeground(new Color(153, 102, 255));
		lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 28));
		lblNewLabel.setBounds(37, 13, 380, 37);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Strudent Name");
		lblNewLabel_1.setForeground(new Color(153, 102, 255));
		lblNewLabel_1.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblNewLabel_1.setBounds(68, 78, 172, 16);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setForeground(new Color(153, 102, 255));
		lblNewLabel_2.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblNewLabel_2.setBounds(68, 122, 91, 16);
		getContentPane().add(lblNewLabel_2);

		txtname = new JTextField();
		txtname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtname.setBounds(185, 77, 216, 22);
		getContentPane().add(txtname);
		txtname.setColumns(10);

		add = new JButton("Add");
		add.setFont(new Font("Agency FB", Font.BOLD, 18));
		add.setBounds(56, 415, 97, 37);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtname.getText();
				String address = txtname.getText();
				String mail = txtmail.getText();
				String adds = txtadd.getText();
				String city = txtcity.getText();
				String phone = txtphone.getText();

				try {
					Connection con = DB.getConnection();
					PreparedStatement pst1 = con.prepareStatement(
							"select*from librarian where name=? and email=? and address=? and city=? and contect=?");
					pst1.setString(1, name);

					pst1.setString(2, mail);
					pst1.setString(3, adds);
					pst1.setString(4, city);
					pst1.setString(5, phone);
					rs = pst1.executeQuery();
					if (rs.next() == false) {
						PreparedStatement pst = con.prepareStatement(
								"insert into librarian(`name`,`password`,`email`,`address`,`city`,`contect`)"
										+ "values(?,?,?,?,?,?)");
						pst.setString(1, name);
						pst.setString(2, address + "123");
						pst.setString(3, mail);
						pst.setString(4, adds);
						pst.setString(5, city);
						pst.setString(6, phone);

						int k = pst.executeUpdate();

						if (k == 1) {
							JOptionPane.showMessageDialog(null, "Member Created");
							txtname.setText("");
							txtpass.setText("");
							txtmail.setText("");
							txtadd.setText("");
							txtcity.setText("");
							txtphone.setText("");
							txtname.requestFocus();
							Memberload();
						} else {
							JOptionPane.showMessageDialog(null, "ERRORR..");

						}
					} else {
						JOptionPane.showMessageDialog(null, "Duplicat..");
						txtname.setText("");
						txtpass.setText("");
						txtmail.setText("");
						txtadd.setText("");
						txtcity.setText("");
						txtphone.setText("");
						txtname.requestFocus();
						Memberload();
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

		getContentPane().add(add);

		update = new JButton("Update");
		update.setFont(new Font("Agency FB", Font.BOLD, 18));
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel d2 = (DefaultTableModel) table1.getModel();
				int selectIndex = table1.getSelectedRow();
				int ID = Integer.parseInt(d2.getValueAt(selectIndex, 0).toString());

				String name = txtname.getText();
				String address = txtpass.getText();
				String mail = txtmail.getText();
				String adds = txtadd.getText();
				String city = txtcity.getText();
				String phone = txtphone.getText();

				try {
					Connection con = DB.getConnection();
					PreparedStatement pst1 = con.prepareStatement(
							"select*from librarian where name=? and password=? and email=? and address=? and city=? and contect=?");
					pst1.setString(1, name);
					pst1.setString(2, address);
					pst1.setString(3, mail);
					pst1.setString(4, adds);
					pst1.setString(5, city);
					pst1.setString(6, phone);
					rs = pst1.executeQuery();
					if (rs.next() == false) {

						PreparedStatement pst = con.prepareStatement(
								"update librarian set `name`=?,`password`=?,`email`=?,`address`=?,`city`=?,`contect`=? where `ID`=?");
						pst.setString(1, name);
						pst.setString(2, address);
						pst.setString(3, mail);
						pst.setString(4, adds);
						pst.setString(5, city);
						pst.setString(6, phone);
						pst.setInt(7, ID);

						int k = pst.executeUpdate();

						if (k == 1) {
							JOptionPane.showMessageDialog(null, "Member Updated");
							txtname.setText("");
							txtpass.setText("");
							txtmail.setText("");
							txtadd.setText("");
							txtcity.setText("");
							txtphone.setText("");
							txtname.requestFocus();
							Memberload();
							add.setEnabled(true);
							update.setEnabled(false);
							delete.setEnabled(false);
						} else {
							JOptionPane.showMessageDialog(null, "ERRORR..");

						}
					} else {
						JOptionPane.showMessageDialog(null, "Duplicat..");
						txtname.setText("");
						txtpass.setText("");
						txtmail.setText("");
						txtadd.setText("");
						txtcity.setText("");
						txtphone.setText("");
						txtname.requestFocus();
						Memberload();
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
		update.setBounds(170, 415, 97, 37);
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
					PreparedStatement pst = con.prepareStatement("delete from librarian where ID=?");

					pst.setInt(1, ID);

					int k = pst.executeUpdate();

					if (k == 1) {
						JOptionPane.showMessageDialog(null, "Member Deleted");
						txtname.setText("");
						txtpass.setText("");
						txtmail.setText("");
						txtadd.setText("");
						txtcity.setText("");
						txtphone.setText("");
						txtname.requestFocus();
						Memberload();
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
		delete.setBounds(279, 415, 97, 37);
		getContentPane().add(delete);

		back = new JButton("Back");
		back.setFont(new Font("Agency FB", Font.BOLD, 18));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back.setEnabled(false);
				member2.this.setVisible(false);
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
		back.setBounds(170, 475, 97, 37);
		getContentPane().add(back);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(457, 37, 674, 475);
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
				txtpass.setText(d2.getValueAt(selectIndex, 2).toString());
				txtmail.setText(d2.getValueAt(selectIndex, 3).toString());
				txtadd.setText(d2.getValueAt(selectIndex, 4).toString());
				txtcity.setText(d2.getValueAt(selectIndex, 5).toString());
				txtphone.setText(d2.getValueAt(selectIndex, 6).toString());
				add.setEnabled(false);
				update.setEnabled(true);
				delete.setEnabled(true);

			}
		});
		scrollPane.setViewportView(table1);
		table1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Student Name", "Password", "Email", "Address", "City", "Phone No." }) {
			Class[] columnTypes = new Class[] { Integer.class, Object.class, Object.class, Object.class, Object.class,
					Object.class, Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table1.getColumnModel().getColumn(0).setPreferredWidth(39);
		table1.getColumnModel().getColumn(1).setPreferredWidth(154);
		table1.getColumnModel().getColumn(3).setPreferredWidth(126);
		table1.getColumnModel().getColumn(4).setPreferredWidth(130);

		JLabel lblNewLabel_3 = new JLabel("Phone No.");
		lblNewLabel_3.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblNewLabel_3.setForeground(new Color(153, 102, 255));
		lblNewLabel_3.setBounds(68, 336, 91, 16);
		getContentPane().add(lblNewLabel_3);

		txtcity = new JTextField();
		txtcity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtcity.setBounds(185, 277, 216, 22);
		getContentPane().add(txtcity);
		txtcity.setColumns(10);

		txtpass = new JTextField();
		txtpass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpass.setBounds(185, 121, 216, 22);
		getContentPane().add(txtpass);
		txtpass.setColumns(10);

		txtmail = new JTextField();
		txtmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtmail.setBounds(185, 175, 216, 22);
		getContentPane().add(txtmail);
		txtmail.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setForeground(new Color(153, 102, 255));
		lblNewLabel_4.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblNewLabel_4.setBounds(68, 169, 56, 31);
		getContentPane().add(lblNewLabel_4);

		txtadd = new JTextField();
		txtadd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtadd.setBounds(185, 225, 216, 22);
		getContentPane().add(txtadd);
		txtadd.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Address");
		lblNewLabel_5.setForeground(new Color(153, 102, 255));
		lblNewLabel_5.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblNewLabel_5.setBounds(68, 226, 56, 16);
		getContentPane().add(lblNewLabel_5);

		txtphone = new JTextField();
		txtphone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtphone.setBounds(185, 335, 216, 22);
		getContentPane().add(txtphone);
		txtphone.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("City");
		lblNewLabel_6.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblNewLabel_6.setForeground(new Color(153, 102, 255));
		lblNewLabel_6.setBounds(68, 278, 56, 16);
		getContentPane().add(lblNewLabel_6);
		Memberload();
		update.setEnabled(false);
		delete.setEnabled(false);
	}

	public void Memberload() {
		int c;
		try {
			Connection con = DB.getConnection();
			pst = con.prepareStatement("select*from librarian");
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
					v2.add(rs.getString("password"));
					v2.add(rs.getString("email"));
					v2.add(rs.getString("address"));
					v2.add(rs.getString("city"));
					v2.add(rs.getString("contect"));

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
					member2 frame = new member2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
