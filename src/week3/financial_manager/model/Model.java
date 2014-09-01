/**
 * @author clack008@gmail.com
 */

package week3.financial_manager.model;

import java.util.Set;

public interface Model {

	public User createUser(String login, String password, String fname, String sname, String mname);

	public boolean login(String login, String password);

	public boolean authorized();

	public boolean logout();

	public boolean put(Account account, double sum);

	public boolean withdraw(Account account, double sum);

	public boolean createAccount(String description);

	public boolean deleteAccount(Account account);
	
	public boolean createCategory(Category category);
	
	public boolean deleteCategory(String name);
	
	public Set<String> getCategoryNames();

	public Account getAccount(int id);
	
	public Category getCategory (String name);

	public Set<Account> getUserAccounts();

	public Set<Record> getAccountRecords(Account account);

	public void removeMe();

}
