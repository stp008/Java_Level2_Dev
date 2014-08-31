/**
 * @author clack008@gmail.com
 */

package week3.financial_manager.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import week3.financial_manager.utils.IDGenerator;

public class Account {

	private final int id;
	private final String description;
	private final String owner;
	private double balance;
	private Map<Integer, Record> records;

	public Account(String description, String owner) {
		this.id = IDGenerator.getAccountId();
		balance = 0;
		this.description = description;
		records = new HashMap<>();
		this.owner = owner;
	}
	
	public Account(int id, double balance, String description, String owner) {
		if (id >= IDGenerator.getAccountId()) throw new RuntimeException("Invalid ID");
		this.id = id;
		balance = 0;
		this.description = description;
		records = new HashMap<>();
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public void addRecord(Record record) {
		records.put(record.getId(), record);
	}

	public Record removeRecord(Record record) {
		return records.remove(record.getId());
	}

	public Record getRecord(int id) {
		return records.get(id);
	}

	public Set<Record> getRecords() {
		return new HashSet<Record>(records.values());
	}

	public double getBalance() {
		return balance;
	}

	public void changeBalance(double balance) {
		this.balance += balance;
	}

	public String getOwner() {
		return owner;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("Account " + id + ": DESCRIPTION - " + description
				+ ", BALANCE - " + balance + ";");
		return string.toString();
	}

	@Override
	public boolean equals(Object object) {
		if (object == null)
			return false;
		if (!(object instanceof Account))
			return false;
		Account account = (Account) object;
		return this.id == account.getId();
	}

	@Override
	public int hashCode() {
		return id + description.hashCode() + owner.hashCode();
	}

}
