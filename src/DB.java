import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    public static Connection getConnection() {
        Connection con = null;

        // Database connection details (In base64 encoded for security)
        String xuri = "jdbc:mysql://localhost:3306/mydatabase";
	String xuri1 = "myUsername";
	String xuri2 = "myPassword";


        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Decode and use connection details
            String dbUrl = password.Youo(xuri);
            String username = password.Youo(xuri1);
            String userPassword = password.Youo(xuri2);

            if (dbUrl.isEmpty() || username.isEmpty() || userPassword.isEmpty()) {
                // Prompt the user to fill in their information
                System.out.println("Please provide your database connection details in 'xuri', 'xuri1', and 'xuri2'.");
            } else {
                // Attempt to establish a database connection
                con = DriverManager.getConnection(dbUrl, username, userPassword);
            }
        } catch (Exception e) {
            // Handle exceptions (e.g., log the error or show a user-friendly error message)
            System.out.println("An error occurred while connecting to the database: " + e.getMessage());
        }
        return con;
    }
}
