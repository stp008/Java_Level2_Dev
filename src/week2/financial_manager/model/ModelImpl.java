/**
 * @author clack008@gmail.com
 */

package week2.financial_manager.model;

import java.util.Date;
import java.util.Set;

import week1.financial_manager.commands.Tags;

public class ModelImpl implements Model {
	private final DataStore data;
	private User currentUser;

	public ModelImpl() {
		data = new DataStoreImpl();
	}

	public User createUser(String name, String password) {
		User user = new User(name, password);
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
		if (!(account.getOwner().equals(currentUser.getName())))
			return false;
		if (sum <= 0)
			return false;
		account.changeBalance(sum);
		account.addRecord(new Record(account, "adding money", sum, new Date(),
				Tags.PUT));
		return true;
	}

	public boolean withdraw(Account account, double sum) {
		if (!this.authorized())
			return false;
		if (!account.getOwner().equals(currentUser.getName()))
			return false;
		if (sum <= 0 || account.getBalance() < sum)
			return false;
		account.changeBalance(-sum);
		account.addRecord(new Record(account, "withdrawing money", sum,
				new Date(), Tags.WITHDRAW));
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
		data.removeUser(currentUser.getName());
	}

	@Override
	public boolean createAccount(String description) {
		if (!this.authorized())
			return false;
		data.addAccount(currentUser,
				new Account(description, currentUser.getName()));
		return true;
	}

	@Override
	public boolean deleteAccount(Account account) {
		if (!this.authorized())
			return false;
		if (!account.getOwner().equals(currentUser.getName()))
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

}
