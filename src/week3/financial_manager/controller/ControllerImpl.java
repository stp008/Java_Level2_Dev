/**
 * @author clack008@gmail.com
 */

package week3.financial_manager.controller;

import java.util.HashSet;
import java.util.Set;

import week3.financial_manager.model.Account;
import week3.financial_manager.model.Model;
import week3.financial_manager.model.Record;
import week3.financial_manager.view.View;

public class ControllerImpl implements Controller {

	private Model model;
	private View view;
	
	public ControllerImpl(Model model, View view) {
		this.model = model;
		this.view = view;
	}
	
	@Override
	public boolean createUser(String login, String password, String fname,
			String sname, String mname) {
		model.createUser(login, password, fname, sname, mname);
		return true;
	}

	@Override
	public boolean login(String login, String password) {
		return model.login(login, password);
	}

	@Override
	public boolean authorized() {
		return model.authorized();
	}

	@Override
	public boolean logout() {
		return model.logout();
	}

	@Override
	public boolean put(int id, double sum) {
		return model.put(model.getAccount(id), sum);
	}

	@Override
	public boolean withdraw(int id, double sum) {
		return model.withdraw(model.getAccount(id), sum);
	}

	@Override
	public boolean createAccount(String description) {
		return model.createAccount(description);
	}

	@Override
	public boolean deleteAccount(int id) {
		return model.deleteAccount(model.getAccount(id));
	}

	@Override
	public Set<Integer> getUserAccounts() {
		Set<Integer> accountID = new HashSet<>();
		Set<Account> accounts = model.getUserAccounts();		
		for (Account acc : accounts) {
			accountID.add(acc.getId());
		}	
		
		return accountID;
	}

	@Override
	public Set<Integer> getAccountRecords(int id) {
		Set<Integer> recordID = new HashSet<>();
		Set<Record> records = model.getAccountRecords(model.getAccount(id));		
		for (Record rec : records) {
			recordID.add(rec.getId());
		}	
		
		return recordID;
	}

	@Override
	public boolean updateAccountBalance(int id, double sum) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createCategory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCategory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeMe() {
		// TODO Auto-generated method stub
		
	}

}
