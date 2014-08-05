/**
 * @author clack008@gmail.com
 */

package week2.financial_manager.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Account {
	
	private static int count = 0;
	
	private final int id;
	private final String description;
	private final String owner;
	private double balance;
	private Map<Integer, Record> records;
	
	public Account(String description, String owner) {
		balance = 0;
		this.description = description;
		this.id = count++;
		records = new HashMap<>();
		this.owner = owner;
	}
	
	public int getId() {
		return id;
	}
	
	public void addRecord(Record record) {
		records.put(record.getId(), record);
	}
	
	public Record removeRecord (Record record) {
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
    	string.append("Account " + id + ": DESCRIPTION - " + description + ", BALANCE - " + balance + ";");   	
    	return string.toString();
    }
    
    @Override
	public boolean equals(Object object) {
		if (object == null) return false;
		if(!(object instanceof Account)) return false;
		Account account = (Account)object;
		return this.id == account.getId();
	}
    
    @Override
    public int hashCode() {
        return id + description.hashCode() + owner.hashCode();
    }
    
}
