package week3.financial_manager.model;

import java.util.Map;

import week3.financial_manager.utils.IDGenerator;

public class Category {

	private static int count = IDGenerator.getCategoryId();

	private final int id;
	private final String name;
	private final String description;
	
	public Category(String name, String description) {
		this.id = count++;
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
