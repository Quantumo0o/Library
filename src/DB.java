import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
	public static Connection getConnection() {
		Connection con = null;

		String xuri = "amRiYzpteXNxbDovL3VjZnI3cWh2NG56ZmM0dWE6NmZhMUxZSnE1ZXFLYkhPODRVcmtAYnhmYm11a2JicmJzcDUwNGZicXQtbXlzcWwuc2VydmljZXMuY2xldmVyLWNsb3VkLmNvbTozMzA2L2J4ZmJtdWtiYnJic3A1MDRmYnF0";
		String xuri1 = "dWNmcjdxaHY0bnpmYzR1YQ==";
		String xuri2 = "NmZhMUxZSnE1ZXFLYkhPODRVcms=";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(password.Youo(xuri), password.Youo(xuri1), password.Youo(xuri2));

		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
}