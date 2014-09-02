/**
 * @author clack008@gmail.com
 */

package week3.financial_manager.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import week3.financial_manager.commands.Tags;
import week3.financial_manager.utils.DBHelper;
import week3.financial_manager.utils.IDGenerator;
import week3.financial_manager.utils.ReadFile;

public class DataStoreSQLImpl implements DataStore {

	private Connection conn;
	private PreparedStatement prepared = null;
	private ResultSet result = null;
	private final String PATH = "C:\\Users\\Just a man\\workspace\\java_level2\\src\\week3\\financial_manager\\model\\Queries\\";

	private Map<String, User> users;
	private Map<String, Category> categories;

	public DataStoreSQLImpl() {
		users = new HashMap<>();
		categories = new HashMap<>();
		try {
			conn = DBHelper.getConn();
		} catch (SQLException e) {
			System.err
					.println("Не удалось установить соединение с базой данных");
		}
	}

	public static void main(String[] args) {
		System.out.println(IDGenerator.getAccountId());
		System.out.println(IDGenerator.getCategoryId());
		System.out.println(IDGenerator.getRecordId());
		System.out.println(IDGenerator.getUserId());
		DataStore st = new DataStoreSQLImpl();
		User user = new User("65689897898", "6589", "6589", "69589", "68985");
		Account account = new Account("fjdkj89dh", user.getLogin());
		Category category = new Category("Личное", "Банк");
		st.addUser(user);
		st.addCategory(category);
		st.addAccount(user, account);
		Record record = new Record(account, "djkj89dkjk", 3500, new Date(2014,
				8, 25), Tags.WITHDRAW, category);
		st.addRecord(account, record);
	}
	
	@Override
	public User getUser(String name) {
		User user = users.get(name);
		if (user == null) {
			try {
				prepared = conn.prepareStatement(ReadFile.getQuery(PATH
						+ "GetUser.sql"));

				prepared.setString(1, name);

				result = prepared.executeQuery();

				result.next();

				int id = Integer.valueOf(result.getString(1));
				String password = result.getString(2);
				String login = result.getString(3);
				String fname = result.getString(4);
				String sname = result.getString(5);
				String mname = result.getString(6);

				user = new User(id, login, password, fname, sname, mname);
				users.put(user.getLogin(), user);

				prepared = conn.prepareStatement(ReadFile.getQuery(PATH
						+ "GetAccounts.sql"));

				prepared.setInt(1, user.getId());

				result = prepared.executeQuery();

				while (result.next()) {
					user.addAccount(new Account(result.getInt(1), result
							.getDouble(2), result.getString(3), user.getLogin()));
				}

				prepared.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return user;
	}
	
	@Override
	public Category getCategory(String name) {
		Category category = categories.get(name);
		if (category == null) {
			try {
				prepared = conn.prepareStatement(ReadFile.getQuery(PATH
						+ "GetCategory.sql"));

				prepared.setString(1, name);

				result = prepared.executeQuery();

				result.next();

				int id = Integer.valueOf(result.getString(1));
				String categoryName = result.getString(2);
				String description = result.getString(3);
				
				category = new Category(id, categoryName, description);

				categories.put(category.getName(), category);


				prepared.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return category;
	}
	
	@Override
	public Set<String> getUserNames() {
		Set<String> userNames = new HashSet<>();

		try {
			prepared = conn.prepareStatement(ReadFile.getQuery(PATH
					+ "GetUsers.sql"));
			result = prepared.executeQuery();

			while (result.next()) {
				userNames.add(result.getString(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userNames;
	}
	
	@Override
	public Set<String> getCategoryNames() {
		Set<String> categoryNames = new HashSet<>();

		try {
			prepared = conn.prepareStatement(ReadFile.getQuery(PATH
					+ "GetCategories.sql"));
			result = prepared.executeQuery();

			while (result.next()) {
				categoryNames.add(result.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryNames;
	}
	
	@Override
	public Set<Account> getAccounts(User owner) {
		return owner.getAccounts();
	}
	
	@Override
	public Set<Record> getRecords(Account account) {
		return account.getRecords();
	}
	
	@Override
	public void addUser(User user) {
		if (users.containsKey(user.getLogin()))
			return;

		users.put(user.getLogin(), user);

		try {
			prepared = conn.prepareStatement(ReadFile.getQuery(PATH
					+ "AddUser.sql"));

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
		if (users.containsKey(user.getLogin())) {
			users.get(user.getLogin()).addAccount(account);
		}
		try {

			prepared = conn.prepareStatement(ReadFile.getQuery(PATH
					+ "AddAccount.sql"));

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

		if (record.getTag().toString().equals("WITHDRAW")) {
			amount = -amount;
		}

		updateAccountBalance(account, amount);
		
		try {
			prepared = conn.prepareStatement(ReadFile.getQuery(PATH
					+ "AddRecord.sql"));

			prepared.setInt(1, record.getId());
			prepared.setInt(2, account.getId());
			prepared.setString(3, record.getTag().toString());
			prepared.setDouble(4, record.getAmount());
			prepared.setDate(5, record.getDate());
			prepared.setString(6, record.getDescription());
			prepared.setInt(7, record.getCategory().getId());
			// prepared.setInt(7, 1);

			prepared.execute();

			prepared.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addCategory(Category category) {
		categories.put(category.getName(), category);
		try {
			try (PreparedStatement prepared = conn.prepareStatement(ReadFile
					.getQuery(PATH + "AddCategory.sql"))) {

				prepared.setInt(1, category.getId());
				prepared.setString(2, category.getName());
				prepared.setString(3, category.getDescription());

				prepared.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public User removeUser(String name) {
		User user = users.remove(name);
		try {
			try (PreparedStatement prepared = conn.prepareStatement(ReadFile
					.getQuery(PATH + "RemoveUser.sql"))) {

				prepared.setString(1, name);

				prepared.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public Account removeAccount(User owner, Account account) {
		owner.removeAccount(account);
		
		try {
			try (PreparedStatement prepared = conn.prepareStatement(ReadFile
					.getQuery(PATH + "RemoveAccount.sql"))) {

				prepared.setInt(1, account.getId());

				prepared.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public Record removeRecord(Account from, Record record) {
		
		double amount = record.getAmount();

		if (record.getTag().toString().equals("PUT")) {
			amount = -amount;
		}

		updateAccountBalance(from, amount);
		from.removeRecord(record);
		
		try {
			try (PreparedStatement prepared = conn.prepareStatement(ReadFile
					.getQuery(PATH + "RemoveRecord.sql"))) {

				prepared.setInt(1, record.getId());

				prepared.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return record;
	}

	@Override
	public Category removeCategory(String name) {
		Category category = categories.remove(name);
		
		try {
			try (PreparedStatement prepared = conn.prepareStatement(ReadFile
					.getQuery(PATH + "RemoveCategory.sql"))) {

				prepared.setString(1, name);

				prepared.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}
	
	private void updateAccountBalance(Account account, double amount) {
		try {
			prepared = conn.prepareStatement(ReadFile.getQuery(PATH
					+ "UpdateAccount.sql"));
			
			prepared.setInt(1, account.getId());
			prepared.setDouble(2, amount);
			prepared.setInt(3, account.getId());

			prepared.execute();

			prepared.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
