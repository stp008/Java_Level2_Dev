package week3.financial_manager.model;

import java.util.Map;

import week3.financial_manager.utils.IdGenerator;

public class Category {

	private static int count = IdGenerator.getCategoryId();

	private final int id;
	
	public Category() {
		this.id = count++;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
