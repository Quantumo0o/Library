import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LibrarianDao {

	static String mamname;

	public static boolean validateadmin(String name, String password) {
		mamname = name;
		boolean status = false;
		try {
			Connection con = DB.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from admin where Admin=? and password=?");
			ps.setString(1, name);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			status = rs.next();

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static boolean validate(String name, String password) {
		mamname = name;
		boolean status = false;
		try {
			Connection con = DB.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from librarian where name=? and password=?");
			ps.setString(1, name);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			status = rs.next();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

}
