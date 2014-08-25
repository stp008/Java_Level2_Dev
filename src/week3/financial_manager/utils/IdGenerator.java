package week3.financial_manager.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class IdGenerator {

	private static Connection conn;
	private static PreparedStatement prepared = null;
	private static ResultSet result = null;
	private static final String PATH = "C:\\Users\\Just a man\\workspace\\java_level2\\src\\week3\\financial_manager\\utils\\Queries\\";

	static {
		try {
			conn = DbHelper.getConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		System.out.println(IdGenerator.getUserId());
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
		} catch (SQLException e) {
			id = 0;
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
		} catch (SQLException e) {
			id = 0;
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
		} catch (SQLException e) {
			id = 0;
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
		} catch (SQLException e) {
			id = 0;
		}
		
		return id;
	}

}
