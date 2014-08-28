package week3.financial_manager.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

import week1.financial_manager.commands.Tags;
import week3.financial_manager.utils.DBHelper;
import week3.financial_manager.utils.ReadFile;

public class DataStoreSQLImpl implements DataStore {

	private Connection conn;
	private PreparedStatement prepared = null;
	private ResultSet result = null;
	private final String PATH = "C:\\Users\\Just a man\\workspace\\java_level2\\src\\week3\\financial_manager\\model\\Queries\\";
	
	public DataStoreSQLImpl() {
		try {
			conn = DBHelper.getConn();
		} catch (SQLException e) {
			System.err.println("Не удалось установить соединение с базой данных");
		}
	}
	
	
	public static void main(String[] args) {
		DataStore st = new DataStoreSQLImpl();
		User user = new User("6598", "6589", "6589", "6589", "6895");
		Account account = new Account("fjdkjdh", user.getLogin());
		Category category = new Category("Личное", "Банк");
		st.addUser(user);
		st.addCategory(category);
		st.addAccount(user, account);
		Record record = new Record(account, "djkjdkjk", 3500, new Date(2014, 8, 25), Tags.WITHDRAW, category);
		st.addRecord(account, record);
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
			
			prepared.setInt(1, user.getId());
			prepared.setString(2, user.getPassword());
			prepared.setString(3, user.getLogin());
			prepared.setString(4, user.getFName());
			prepared.setString(5, user.getSName());
			prepared.setString(6, user.getMName());

			prepared.execute();
			
			prepared.close();		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	
	@Override
	public void addAccount(User user, Account account) {
		try {
			
			prepared = conn.prepareStatement(ReadFile
					.getQuery(PATH + "AddAccount.sql"));
			
			prepared.setInt(1, account.getId());
			prepared.setInt(2, user.getId());
			prepared.setDouble(3, account.getBalance());
			prepared.setString(4, account.getDescription());

			prepared.execute();
			
			prepared.close();		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}

	
	@Override
	public void addRecord(Account account, Record record) {
		double amount = record.getAmount();		
		
		if(record.getTag().toString().equals("WITHDRAW")) {
			amount = -amount;
		}
		
		try {
			prepared = conn.prepareStatement(ReadFile
					.getQuery(PATH + "AddRecord.sql"));
		
			prepared.setInt(1, record.getId());
			prepared.setInt(2, account.getId());
			prepared.setString(3, record.getTag().toString());
			prepared.setDouble(4, record.getAmount());
			prepared.setDate(5, record.getDate());
			prepared.setString(6, record.getDescription());
			prepared.setInt(7, record.getCategory().getId());
			
			prepared.execute();
			
			
			prepared = conn.prepareStatement(ReadFile
					.getQuery(PATH + "UpdateAccount.sql"));
		
			prepared.setInt(1, account.getId());
			prepared.setDouble(2, amount);
			prepared.setInt(3, account.getId());


			prepared.execute();
			
			prepared.close();		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void addCategory(Category category) {	
		try {
			prepared = conn.prepareStatement(ReadFile
					.getQuery(PATH + "AddCategory.sql"));
		
			prepared.setInt(1, category.getId());
			prepared.setString(2, category.getName());
			prepared.setString(3, category.getDescription());

			prepared.execute();
			
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

	
	@Override
	public Set<Category> getCategories() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Category removeCategory(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
