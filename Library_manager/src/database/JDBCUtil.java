package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
	public static String userName = "root";
	public static String passsWord = "5zm7aa8o";
	public static String databaseName = "LIBRARY_MANAGEMENT";
	public static String url = "jdbc:mysql://localhost:3307/" + databaseName;

	public static Connection getConnection() throws SQLException {

		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		return DriverManager.getConnection(url, userName, passsWord);
	}

	public static void closeConnection(Connection c) throws SQLException {
		if (c!=null)
		{
			c.close();
		}
	}
}
