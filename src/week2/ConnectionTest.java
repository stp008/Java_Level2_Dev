/**
 * @author clack008@gmail.com
 */

package week2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionTest {

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager
					.getConnection("jdbc:mysql://localhost/twitter?"
							+ "user=root&password=12121212");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM users");
			while (rs.next()) {
				System.out.println(rs.getString(1) + " :: " + rs.getString(2));
			}
		} catch (SQLException ex) {
			// handle any errors
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
		} finally {
			try {
				conn.close();
			} catch (SQLException ex) {
				System.err.println("Unable to close the connection.");
				System.err.println("SQLException: " + ex.getMessage());
				System.err.println("SQLState: " + ex.getSQLState());
				System.err.println("VendorError: " + ex.getErrorCode());
			}
		}

	}

}