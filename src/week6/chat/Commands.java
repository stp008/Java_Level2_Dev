/**
 * @author clack008@gmail.com
 */

package week6.chat;

public enum Commands {
	LOGIN, CREATE, PRIVATE, SEND, HELP, EXIT, BLANK; // for
														// illegal
														// commands

	public static String about() {
		StringBuilder string = new StringBuilder();

		string.append(Commands.LOGIN + " Login Password\n");
		string.append("Logging with specified login and password. \n\n");
		string.append(Commands.CREATE + " Login Password \n");
		string.append("Creates a new user with specified login and password. \n\n");
		string.append(Commands.PRIVATE + " Login \n");
		string.append("Send message to user. \n\n");
		string.append(Commands.SEND + "\n");
		string.append("Send message to all users. \n\n");
		string.append(Commands.HELP + "\n");
		string.append("List of all commands. \n\n");
		string.append(Commands.EXIT + "\n");
		string.append("Turns off the application.\n\n");

		return string.toString();
	}

}
