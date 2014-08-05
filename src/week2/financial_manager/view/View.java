/**
 * @author clack008@gmail.com
 */

package week2.financial_manager.view;

import java.util.Set;

import week1.financial_manager.model.Account;
import week1.financial_manager.model.Record;

public interface View {
	void welcome();
	
	void login(String name, String password);
	
	void createUser(String name, String password);
	
	void put(int id, double sum, double totalSum);
	
	void withdraw(int id, double sum, double totalSum);
	
	void createAccount(String description);
	
	void deleteAccount(int id);
	
	void removeMe();
	
	void incorrectLogin();
	
	void wrongCommand();
	
	void enterCommand();
	
	void notAuthorized();
	
	void help();
	
	void printAccounts(Set<Account> accounts);
	
	void printRecords(Set<Record> records);
	
	void wrongAccount();
	
	void wrongArguments();
}
