import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class viewbook extends JFrame {
	private JTable table;

	public viewbook() {
		setTitle("BooK Viewer");
		setBackground(Color.BLACK);
		getContentPane().setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("smiley.png")));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 1171, 437);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Book", "Category", "Author",
				"Publisher", "Contents", "No. of Pages", "Edition", "Qty." }) {
			Class[] columnTypes = new Class[] { Integer.class, Object.class, Object.class, Object.class, Object.class,
					Object.class, Object.class, Object.class, Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(141);
		table.getColumnModel().getColumn(5).setPreferredWidth(248);
		table.getColumnModel().getColumn(6).setPreferredWidth(93);
		table.getColumnModel().getColumn(7).setPreferredWidth(49);
		scrollPane.setViewportView(table);
		bookload();
	}

	public void bookload() {
		int c;
		try {
			Connection con = DB.getConnection();
			PreparedStatement pst = con.prepareStatement(
					"select b1.id,b1.bname,c.CategoryName,a.name,p.name,b1.con,b1.pag,b1.edition,b1.qty from book2 b1 join category c on b1.category=c.id join author a on b1.author=a.id join publisher p on b1.publisher=p.id");
			ResultSet rs = pst.executeQuery();
			ResultSetMetaData rsd = rs.getMetaData();
			c = rsd.getColumnCount();
			DefaultTableModel d = (DefaultTableModel) table.getModel();
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

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
