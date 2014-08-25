package week3.financial_manager.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbHelper {

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static Connection conn = null;
	private static Statement stmt = null;
	private static Boolean closed = false;

	public static Connection getConn() throws SQLException {
		if (conn == null || closed) {
			conn = DriverManager.getConnection("jdbc:sqlite:manager.db");
			closed = false;
		}

		createTables();

		return conn;
	}

	public static void closeConn() throws SQLException {
		conn.close();
		closed = true;
	}

	private static void createTables() throws SQLException {
		if (conn != null) {
			stmt = conn.createStatement();
			stmt.executeUpdate(ReadFile
					.getQuery("C:\\Users\\Just a man\\workspace\\java_level2\\src\\week3\\financial_manager\\utils\\Queries\\CreateTables.sql"));
		}

	}

	public static void main(String[] args) {
		try {
			DbHelper.getConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
