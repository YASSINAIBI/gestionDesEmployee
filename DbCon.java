package employes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCon {
	private static String username = "root";
	private static String password = "";
	private static String host = "jdbc:mysql://localhost/gestionpersonneltwo";

	public static Connection connDB() throws SQLException {
		return DriverManager.getConnection(host, username, password);
	}
}
