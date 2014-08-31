/**
 * @author clack008@gmail.com
 */

package week3.financial_manager.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import week3.financial_manager.utils.IDGenerator;

public class User {

	private final int id;
	private final String name;
	private final String fname;
	private final String sname;
	private final String mname;
	private final String password;
	private Map<Integer, Account> accounts;

	public User(String name, String password, String fname, String sname, String mname) {
		this.id = IDGenerator.getUserId();
		this.name = name;
		this.fname = fname;
		this.sname = sname;
		this.mname = mname;
		this.password = password;
		this.accounts = new HashMap<>();
	}
	
	public User(int id, String name, String password, String fname, String sname, String mname) {
		if (id >= IDGenerator.getUserId()) throw new RuntimeException("Invalid ID");
		this.id = id;
		this.name = name;
		this.fname = fname;
		this.sname = sname;
		this.mname = mname;
		this.password = password;
		this.accounts = new HashMap<>();
	}

	public String getLogin() {
		return name;
	}
	
	public String getFullName() {
		return fname + " " + sname + " " + mname;
	}
	
	public String getFName() {
		return this.fname;
	}
	
	public String getSName() {
		return this.sname;
	}
	
	public String getMName() {
		return this.mname;
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

	public Account removeAccount(Account account) {
		return accounts.remove(account.getId());
	}

	public int getId() {
		return this.id;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null)
			return false;
		if (!(object instanceof User))
			return false;
		User user = (User) object;
		return this.id == user.getId();
	}

	@Override
	public int hashCode() {
		return name.hashCode() + password.hashCode() + accounts.hashCode();
	}

}
