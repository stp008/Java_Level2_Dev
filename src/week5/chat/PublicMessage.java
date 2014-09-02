/**
 * @author clack008@gmail.com
 */

package week5.chat;

public class PublicMessage {

	private final String message;
	private final User user;

	public PublicMessage(User from, String msg) {
		this.user = from;
		this.message = msg;
	}

	public String getMessage() {
		return message;
	}

	public User getFrom() {
		return user;
	}

}
