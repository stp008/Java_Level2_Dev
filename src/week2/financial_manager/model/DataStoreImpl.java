/**
 * @author clack008@gmail.com
 */

package week2.financial_manager.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DataStoreImpl implements DataStore {

	private Map<String, User> users;

	public DataStoreImpl() {
		users = new HashMap<>();
	}

	@Override
	public User getUser(String name) {
		return users.get(name);
	}

	@Override
	public Set<String> getUserNames() {
		return users.keySet();
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
		users.put(user.getName(), user);
	}

	@Override
	public void addAccount(User user, Account account) {
		users.get(user.getName()).addAccount(account);
	}

	@Override
	public void addRecord(Account account, Record record) {
		users.get(account.getOwner()).getAccount(account.getId())
				.addRecord(record);
	}

	@Override
	public User removeUser(String name) {
		return users.remove(name);
	}

	@Override
	public Account removeAccount(User owner, Account account) {
		return owner.removeAccount(account);
	}

	@Override
	public Record removeRecord(Account from, Record record) {
		return users.get(from.getOwner()).getAccount(from.getId())
				.removeRecord(record);
	}

}
