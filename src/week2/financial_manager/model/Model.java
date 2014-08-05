/**
 * @author clack008@gmail.com
 */

package week2.financial_manager.model;

import java.util.Set;

public interface Model {
	
	public User createUser (String name, String password);
	
	public boolean login (String name, String password);
	
	public boolean authorized ();
	
	public boolean logout ();
	
	public boolean put (Account account, double sum);
	
	public boolean withdraw (Account account, double sum);
	
	public boolean createAccount (String description);
	
	public boolean deleteAccount (Account account);
	
	public Account getAccount (int id);
	
	public Set<Account> getUserAccounts ();
	
	public Set<Record> getAccountRecords(Account account);
	
	public void removeMe ();		
	
}
