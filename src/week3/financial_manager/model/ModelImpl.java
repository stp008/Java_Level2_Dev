/**
 * @author clack008@gmail.com
 */

package week3.financial_manager.model;

import java.util.Set;


public class ModelImpl implements Model {
	private final DataStore data;
	private User currentUser;

	public ModelImpl() {
		data = new DataStoreImpl();
	}

	public User createUser(String name, String password, String fname, String sname, String mname) {
		User user = new User(name, password, fname, sname, mname);
		data.addUser(user);
		return user;
	}

	public boolean login(String name, String password) {
		User user = data.getUser(name);
		if (user == null || !user.getPassword().equals(password))
			return false;
		currentUser = user;
		return true;
	}

	public boolean authorized() {
		return currentUser != null;
	}

	public boolean logout() {
		if (!this.authorized())
			return false;
		currentUser = null;
		return true;
	}

	public boolean put(Account account, double sum) {
		if (!this.authorized())
			return false;
		if (!(account.getOwner().equals(currentUser.getFullName())))
			return false;
		if (sum <= 0)
			return false;
		account.changeBalance(sum);
		//account.addRecord(new Record(account, "adding money", sum, new Date(),
				//Tags.PUT));
		return true;
	}

	public boolean withdraw(Account account, double sum) {
		if (!this.authorized())
			return false;
		if (!account.getOwner().equals(currentUser.getFullName()))
			return false;
		if (sum <= 0 || account.getBalance() < sum)
			return false;
		account.changeBalance(-sum);
		//account.addRecord(new Record(account, "withdrawing money", sum,
				//new Date(), Tags.WITHDRAW));
		return true;
	}

	public Set<Account> getUserAccounts() {
		if (!this.authorized())
			return null;
		return currentUser.getAccounts();
	}

	public Set<Record> getAccountRecords(Account account) {
		if (!this.authorized())
			return null;
		return currentUser.getAccount(account.getId()).getRecords();
	}

	public void removeMe() {
		if (!this.authorized())
			return;
		data.removeUser(currentUser.getFullName());
	}

	@Override
	public boolean createAccount(String description) {
		if (!this.authorized())
			return false;
		data.addAccount(currentUser,
				new Account(description, currentUser.getFullName()));
		return true;
	}

	@Override
	public boolean deleteAccount(Account account) {
		if (!this.authorized())
			return false;
		if (!account.getOwner().equals(currentUser.getFullName()))
			return false;
		data.removeAccount(currentUser, account);
		return true;
	}

	@Override
	public Account getAccount(int id) {
		if (!this.authorized())
			return null;
		return currentUser.getAccount(id);
	}

	@Override
	public boolean createCategory(Category category) {
		data.addCategory(category);
		if (category == null) return false;
		return true;
	}

	@Override
	public boolean deleteCategory(String name) {
		Category category = data.removeCategory(name);
		if (category == null) return false;
		return true;
	}

	@Override
	public Set<String> getCategoryNames() {
		return data.getCategoryNames();
	}

	@Override
	public Category getCategory(String name) {
		return data.getCategory(name);
	}

}
