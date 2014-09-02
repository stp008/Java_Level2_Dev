/**
 * @author clack008@gmail.com
 */

package week3.financial_manager.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IDGenerator {

	/*
	 * private static int cachedUserID = 1; private static int cachedAccountID =
	 * 1; private static int cachedRecordID = 1; private static int
	 * cachedCategoryID = 1;
	 */

	private static Connection conn;
	private static PreparedStatement prepared = null;
	private static ResultSet result = null;
	private static final int DEFAULT_ID = 1;
	private static final String PATH = "C:\\Users\\Just a man\\workspace\\java_level2\\src\\week3\\financial_manager\\utils\\Queries\\";

	static {
		try {
			conn = DBHelper.getConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println(IDGenerator.getUserId());
	}

	public static int getUserId() {
		int id;

		try {
			prepared = conn.prepareStatement(ReadFile.getQuery(PATH
					+ "GetLatestUserId.sql"));
			result = prepared.executeQuery();
			result.next();
			id = Integer.valueOf(result.getString(1));
			id++;

			prepared.close();
		} catch (SQLException e) {
			id = DEFAULT_ID;
		}

		return id;
	}

	public static int getAccountId() {
		int id;

		try {
			prepared = conn.prepareStatement(ReadFile.getQuery(PATH
					+ "GetLatestAccountId.sql"));
			result = prepared.executeQuery();
			result.next();
			id = Integer.valueOf(result.getString(1));
			id++;

			prepared.close();
		} catch (SQLException e) {
			id = DEFAULT_ID;
		}

		return id;
	}

	public static int getRecordId() {
		int id;

		try {
			prepared = conn.prepareStatement(ReadFile.getQuery(PATH
					+ "GetLatestRecordId.sql"));

			result = prepared.executeQuery();

			result.next();
			id = Integer.valueOf(result.getString(1));
			id++;

			prepared.close();
		} catch (SQLException e) {
			id = DEFAULT_ID;
		}

		return id;
	}

	public static int getCategoryId() {
		int id;
		try {
			prepared = conn.prepareStatement(ReadFile.getQuery(PATH
					+ "GetLatestCategoryId.sql"));

			result = prepared.executeQuery();

			result.next();
			id = Integer.valueOf(result.getString(1));
			id++;

			prepared.close();
		} catch (SQLException e) {
			id = DEFAULT_ID;
		}

		return id;
	}

}
