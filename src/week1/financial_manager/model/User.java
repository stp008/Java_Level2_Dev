/**
 * @author clack008@gmail.com
 */

package week1.financial_manager.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class User {
	
	private static int count = 0;
	
	private final int id;
	private final String name;
	private final String password;
	private Map<Integer, Account> accounts;
	
	public User (String name, String password) {
		this.id = count++;
		this.name = name;
		this.password = password;
		this.accounts = new HashMap<>();
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
	
	public void addAccount(Account account) {
		accounts.put(account.getId(), account);
	}
	
	public Account getAccount(int id) {
		return accounts.get(id);
	}
	
	public Set<Account> getAccounts() {
		return new HashSet<Account>(accounts.values());
	}
	
	public Account removeAccount (Account account) {
		return accounts.remove(account.getId());
	}
	
	public int getId() {
		return this.id;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null) return false;
		if(!(object instanceof User)) return false;
		User user = (User)object;
		return this.id == user.getId();
	}
	
	 @Override
    public int hashCode() {
        return name.hashCode() + password.hashCode() + accounts.hashCode();
    }
	
}
