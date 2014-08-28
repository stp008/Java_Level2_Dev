/**
 * @author clack008@gmail.com
 */

package week3.financial_manager.commands;

public enum Commands {
	CREATE_USER, DELETE_USER, LOGIN, CREATE_ACCOUNT, PUT, WITHDRAW, GET_HISTORY, GET_ACCOUNTS, HELP, EXIT, BLANK; // for
																													// illegal
																													// commands

	public static String about() {
		StringBuilder string = new StringBuilder();

		string.append(Commands.CREATE_USER + " Login Password \n");
		string.append("Creates a new user with specified login and password. \n\n");
		string.append(Commands.DELETE_USER + " Login Password \n");
		string.append("Deletes user with specified login and password. \n\n");
		string.append(Commands.LOGIN + " Login Password\n");
		string.append("Logging with specified login and password. \n\n");
		string.append(Commands.CREATE_ACCOUNT + " Desciprion\n");
		string.append("Creates an account with specified description. \n\n");
		string.append(Commands.PUT + " Account.id Sum \n");
		string.append("Adds sum on a specified account. \n\n");
		string.append(Commands.WITHDRAW + " Account.id Sum\n");
		string.append("Withdraws sum on a specified account. \n\n");
		string.append(Commands.GET_HISTORY + " Account.id\n");
		string.append("Returns all records on a specified account. \n\n");
		string.append(Commands.GET_ACCOUNTS + "\n");
		string.append("Returns the list of all accounts of the current logged in user.\n\n");
		string.append(Commands.EXIT + "\n");
		string.append("Turns off the application.\n\n");

		return string.toString();
	}

}
