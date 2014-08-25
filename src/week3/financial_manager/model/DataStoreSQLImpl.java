package week3.financial_manager.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

import week3.financial_manager.utils.DbHelper;
import week3.financial_manager.utils.ReadFile;

public class DataStoreSQLImpl implements DataStore {

	private Connection conn;
	private PreparedStatement prepared = null;
	private ResultSet result = null;
	private final String PATH = "C:\\Users\\Just a man\\workspace\\java_level2\\src\\week3\\financial_manager\\model\\Queries\\";
	
	public DataStoreSQLImpl() {
		try {
			conn = DbHelper.getConn();
		} catch (SQLException e) {
			System.err.println("Ќе удалось установить соединение с базой данных");
		}
	}
	
	public static void main(String[] args) {
		DataStore st = new DataStoreSQLImpl();
		st.addUser(new User("ff", "ff", "ff", "ff", "ff"));
		st.addAccount(new User("ff", "ff", "ff", "ff", "ff"), new Account("fdjkjk", new User("ff", "ff", "ff", "ff", "ff").getLogin()));
	}

	@Override
	public User getUser(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getUserNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Account> getAccounts(User owner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Record> getRecords(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser(User user) {
		try {
			prepared = conn.prepareStatement(ReadFile
					.getQuery(PATH + "AddUser.sql"));
			
			prepared.setString(1, user.getPassword());
			prepared.setString(2, user.getLogin());
			prepared.setString(3, user.getFName());
			prepared.setString(4, user.getSName());
			prepared.setString(5, user.getMName());

			prepared.executeUpdate();
			
			prepared.close();		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void addAccount(User user, Account account) {
		try {
			prepared = conn.prepareStatement(ReadFile
					.getQuery(PATH + "GetUser.sql"));
			
			prepared.setString(1, user.getLogin());
			
			result = prepared.executeQuery();
			
			result.next();
			int id = Integer.valueOf(result.getString(1));

			
			prepared = conn.prepareStatement(ReadFile
					.getQuery(PATH + "AddAccount.sql"));
			
			prepared.setInt(1, id);
			prepared.setDouble(2, account.getBalance());
			prepared.setString(3, account.getDescription());

			prepared.executeUpdate();
			
			prepared.close();		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}

	@Override
	public void addRecord(Account account, Record record) {
		try {
			prepared = conn.prepareStatement(ReadFile
					.getQuery(PATH + "AddRecord.sql"));
	 // TODO Complete the method 		
			/*prepared.setString(1, user.getPassword());
			prepared.setString(2, user.getLogin());
			prepared.setString(3, user.getFName());
			prepared.setString(4, user.getSName());
			prepared.setString(5, user.getMName());*/

			prepared.executeUpdate();
			
			prepared.close();		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User removeUser(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account removeAccount(User owner, Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Record removeRecord(Account from, Record record) {
		// TODO Auto-generated method stub
		return null;
	}

}
