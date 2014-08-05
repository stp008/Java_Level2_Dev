/**
 * @author clack008@gmail.com
 */

package week2.financial_manager.view;

import java.util.Set;

import week1.financial_manager.commands.Commands;
import week1.financial_manager.model.Account;
import week1.financial_manager.model.Record;

public class ViewImpl implements View {
	
	public void welcome() {
		System.out.println("\nWelcome to your personal financial manager. To use it you should sign in or sign up if you haven't used this app before. Type HELP to see all possible commands.\n\n");
	}
	
	public void login (String name, String password) {
		System.out.println("\nWelcome back, " + name + ". You have successfully logged in. \n");
	}
	
	public void createUser (String name, String password) {
		System.out.println("\nUser " + name + " was succesfully created. Now you can use all possibilities of the app. \n");
	}
	
	public void put (int id, double sum, double totalSum) {
		System.out.println("\nYou have put " + sum + " on account with id " + id + ". Your balance is " + totalSum + ". \n");
	}
	
	public void withdraw (int id, double sum, double totalSum) {
		System.out.println("\nYou have removed " + sum + " on account with id " + id + ". Your balance is " + totalSum + ". \n");
	}
	
	public void createAccount (String description) {
		System.out.println("\nYou have created new account with description: " +  description + ". Your balance is 0. \n");
	}
	
	public void deleteAccount (int id) {
		System.out.println("\nYou have deleted account account with id " + id + ".\n");
	}
	
	public void removeMe () {
		System.out.println("\nYou have deleted your current user. Log in with another username or create new user. \n");
	}
	
	public void incorrectLogin () {
		System.out.println("\nLogin or password is incorrect. \n");
	}
	
	public void wrongCommand () {
		System.out.println("\nWrong command. Type the correct one. \n");
	}
	
	public void enterCommand () {
		System.out.println("Please, enter your command: ");
	}
	
	public void notAuthorized () {
		System.out.println("Sorry, but you are not authorized.");
	}
	
	public void help () {
		System.out.println(Commands.about());
	}
	
	public void printAccounts (Set<Account> accounts) {
		for (Account account: accounts) {
			System.out.println(account);
		}
	}
	
	public void printRecords (Set<Record> records) {
		for (Record record: records) {
			System.out.println(record);
		}
	}
	
	public void wrongAccount () {
		System.out.println("Wrong account id.");
	}
	
	public void wrongArguments () {
		System.out.println("Wrong arguments.\n");
	}
	
}
