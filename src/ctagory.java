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

import javax.swing.DefaultComboBoxModel;
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

public class ctagory extends JFrame {
	private JTextField textcat;
	private JTable table1;
	private JComboBox txtstatus;
	ResultSet rs;
	PreparedStatement pst;
	private JButton delete;
	private JButton update;
	private JButton add;
	private JButton back;

	public ctagory() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(0, 0, 51));
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("smiley.png")));

		getContentPane().setMinimumSize(new Dimension(660, 410));
		getContentPane().setSize(new Dimension(10, 10));
		getContentPane().setPreferredSize(new Dimension(10, 10));
		getContentPane().setName("");

		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Category");
		lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 28));
		lblNewLabel.setForeground(new Color(153, 102, 255));
		lblNewLabel.setBounds(151, 13, 196, 37);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Cetagory Name");
		lblNewLabel_1.setForeground(new Color(153, 102, 255));
		lblNewLabel_1.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblNewLabel_1.setBounds(47, 96, 141, 22);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Status");
		lblNewLabel_2.setForeground(new Color(153, 102, 255));
		lblNewLabel_2.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblNewLabel_2.setBounds(47, 143, 56, 16);
		getContentPane().add(lblNewLabel_2);

		textcat = new JTextField();
		textcat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textcat.setBounds(231, 93, 116, 22);
		getContentPane().add(textcat);
		textcat.setColumns(10);

		add = new JButton("Add");
		add.setBounds(66, 248, 97, 37);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String category = textcat.getText();
				String statuss = txtstatus.getSelectedItem().toString();

				try {
					Connection con = DB.getConnection();
					PreparedStatement pst1 = con.prepareStatement("Select*from category where CategoryName=?");
					pst1.setString(1, category);

					rs = pst1.executeQuery();
					if (rs.next() == false) {
						PreparedStatement pst = con
								.prepareStatement("insert into category(CategoryName,Status) Value(?,?)");
						pst.setString(1, category);
						pst.setString(2, statuss);

						int k = pst.executeUpdate();

						if (k == 1) {
							JOptionPane.showMessageDialog(null, "Category Created");
							textcat.setText("");
							txtstatus.setSelectedIndex(-1);
							textcat.requestFocus();
							Dataload();
						} else {
							JOptionPane.showMessageDialog(null, "ERRORR..");

						}
					} else {
						JOptionPane.showMessageDialog(null, "Duplicat..");
						textcat.setText("");
						txtstatus.setSelectedIndex(-1);
						textcat.requestFocus();
					}

					con.close();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
				Dataload();
			}

		});

		getContentPane().add(add);

		update = new JButton("Update");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel d2 = (DefaultTableModel) table1.getModel();
				int selectIndex = table1.getSelectedRow();
				int ID = Integer.parseInt(d2.getValueAt(selectIndex, 0).toString());

				String category = textcat.getText();
				String statuss = txtstatus.getSelectedItem().toString();

				try {
					Connection con = DB.getConnection();
					PreparedStatement pst1 = con
							.prepareStatement("Select*from category where CategoryName=? && status=?");
					pst1.setString(1, category);
					pst1.setString(2, statuss);
					rs = pst1.executeQuery();
					if (rs.next() == false) {

						PreparedStatement pst = con
								.prepareStatement("update category set CategoryName=?,Status=? where ID=?");
						pst.setString(1, category);
						pst.setString(2, statuss);
						pst.setInt(3, ID);

						int k = pst.executeUpdate();

						if (k == 1) {
							JOptionPane.showMessageDialog(null, "Category Created");
							textcat.setText("");
							txtstatus.setSelectedIndex(-1);
							textcat.requestFocus();
							Dataload();
							add.setEnabled(true);
							update.setEnabled(false);
							delete.setEnabled(false);
						} else {
							JOptionPane.showMessageDialog(null, "ERRORR..");

						}
					} else {
						JOptionPane.showMessageDialog(null, "Duplicate......");
						textcat.setText("");
						txtstatus.setSelectedIndex(-1);
						textcat.requestFocus();
						Dataload();
						add.setEnabled(true);
						update.setEnabled(false);
						delete.setEnabled(false);
					}

					con.close();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

				Dataload();

			}
		});

		update.setBounds(175, 248, 97, 37);
		getContentPane().add(update);

		delete = new JButton("Delete");
		delete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				DefaultTableModel d2 = (DefaultTableModel) table1.getModel();
				int selectIndex = table1.getSelectedRow();
				int ID = Integer.parseInt(d2.getValueAt(selectIndex, 0).toString());

				try {
					Connection con = DB.getConnection();
					PreparedStatement pst = con.prepareStatement("delete from category where ID=?");

					pst.setInt(1, ID);

					int k = pst.executeUpdate();

					if (k == 1) {

						JOptionPane.showMessageDialog(null, "Category Deleted");
						textcat.setText("");
						txtstatus.setSelectedIndex(-1);
						textcat.requestFocus();
						Dataload();
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
		delete.setBounds(284, 248, 97, 37);
		getContentPane().add(delete);

		back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back.setEnabled(false);
				ctagory.this.setVisible(false);
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
		back.setBounds(170, 302, 97, 37);
		getContentPane().add(back);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(456, 30, 516, 320);
		scrollPane.setViewportBorder(UIManager.getBorder("TableHeader.cellBorder"));
		scrollPane.setToolTipText("");
		getContentPane().add(scrollPane);

		table1 = new JTable();
		table1.setBackground(new Color(224, 255, 255));
		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				DefaultTableModel d2 = (DefaultTableModel) table1.getModel();
				int selectIndex = table1.getSelectedRow();
				int id = Integer.parseInt(d2.getValueAt(selectIndex, 0).toString());
				textcat.setText(d2.getValueAt(selectIndex, 1).toString());
				txtstatus.setSelectedItem(d2.getValueAt(selectIndex, 2).toString());
				add.setEnabled(false);
				update.setEnabled(true);
				delete.setEnabled(true);

			}
		});
		scrollPane.setViewportView(table1);
		table1.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Category Name", "Status" }) {
			boolean[] columnEditables = new boolean[] { false, true, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		txtstatus = new JComboBox();
		txtstatus.setBounds(231, 140, 116, 22);
		txtstatus.setModel(new DefaultComboBoxModel(new String[] { "Active", "DeActive" }));
		getContentPane().add(txtstatus);
		Dataload();
		update.setEnabled(false);
		delete.setEnabled(false);
	}

	public void Dataload() {
		int c;
		try {
			Connection con = DB.getConnection();
			pst = con.prepareStatement("select*from category");
			rs = pst.executeQuery();
			ResultSetMetaData rsd = rs.getMetaData();
			c = rsd.getColumnCount();
			DefaultTableModel d = (DefaultTableModel) table1.getModel();
			d.setRowCount(0);

			while (rs.next()) {

				Vector v2 = new Vector();
				for (int i = 1; i <= c; i++) {

					v2.add(rs.getString("ID"));
					v2.add(rs.getString("CategoryName"));
					v2.add(rs.getString("Status"));

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
					ctagory frame = new ctagory();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
