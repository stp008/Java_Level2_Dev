/**
 * @author clack008@gmail.com
 */

package week3.financial_manager.controller;

import java.util.Set;

public interface Controller {

	public boolean createUser(String login, String password, String fname, String sname, String mname);

	public boolean login(String login, String password);

	public boolean authorized();

	public boolean logout();

	public boolean put(int id, double sum);

	public boolean withdraw(int id, double sum);

	public boolean createAccount(String description);

	public boolean deleteAccount(int id);

	public Set<Integer> getUserAccounts();

	public Set<Integer> getAccountRecords(int id);

	public boolean updateAccountBalance (int id, double sum);
	
	public boolean createCategory ();
	
	public boolean deleteCategory ();
	
	public void removeMe();

}
