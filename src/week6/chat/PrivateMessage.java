/**
 * @author clack008@gmail.com
 */

package week6.chat;

public class PrivateMessage {

	private final User from;
	private final int to;
	private final String message;

	public PrivateMessage(User from, int to, String msg) {
		this.from = from;
		this.to = to;
		this.message = msg;
	}

	public int getTo() {
		return to;
	}

	public String getMessage() {
		return message;
	}

	public User getFrom() {
		return from;
	}

}
