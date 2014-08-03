/**
 * @author clack008@gmail.com
 */

package week1.financial_manager.model;

import java.util.Comparator;
import java.util.Date;

import week1.financial_manager.commands.Tags;


public class Record implements Comparable<Record> {
	
	private static int count = 0;
	
	 public final Comparator<Record> DATE_ORDER = new Comparator<Record>(){
	    	public int compare(Record r1, Record r2) {
				return r1.getDate().compareTo(r2.getDate());
			}	
	    }; 
	
	private final Account account;
	private final Date date;
	private final int id;
	private final double amount;
	private final String description;
	private final Tags tag;
	
	public Record (Account account, String description, double amount, Date date, Tags tag) {
		this.account = account;
		this.description = description;
		this.amount = amount;
		this.date = date;
		this.tag = tag;
		this.id = count++;
	}

	public Account getAccount() {
		return account;
	}


	public Date getDate() {
		return date;
	}


	public int getId() {
		return id;
	}


	public double getAmount() {
		return amount;
	}


	public String getDescription() {
		return description;
	}
	
	public Tags getTag() {
		return tag;
	}
	
	@Override
    public String toString() {
    	StringBuilder string = new StringBuilder();
    	string.append("Record " + id + ": TAG - " + tag + ", AMOUNT - " + amount + ", DESCRIPTION - " + description + ", DATE - " + date + ", OWNER - Account " + account.getId());   	
    	return string.toString();
    }

	@Override
	public int compareTo(Record record) {
		return this.getId() - record.getId();
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null) return false;
		if(!(object instanceof Record)) return false;
		Record record = (Record)object;
		return this.id == record.getId();
	}
	
	@Override
    public int hashCode() {
        return account.hashCode() + description.hashCode() + date.hashCode() + tag.hashCode();
    }
	
}
