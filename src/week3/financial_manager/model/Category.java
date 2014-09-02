/**
 * @author clack008@gmail.com
 */

package week3.financial_manager.model;


import week3.financial_manager.utils.IDGenerator;

public class Category {

	private final int id;
	private final String name;
	private final String description;
	
	public Category(String name, String description) {
		this.id = IDGenerator.getCategoryId();
		this.name = name;
		this.description = description;
	}
	
	public Category(int id, String name, String description) {
		if (id >= IDGenerator.getCategoryId()) throw new RuntimeException("Invalid ID");
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}
