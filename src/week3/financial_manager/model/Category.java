package week3.financial_manager.model;

import java.util.Map;

import week3.financial_manager.utils.IDGenerator;

public class Category {

	private static int count = IDGenerator.getCategoryId();

	private final int id;
	
	public Category() {
		this.id = count++;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
