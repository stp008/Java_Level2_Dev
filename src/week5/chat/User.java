package week5.chat;

public class User {
	private String login;
	private String password;
	private boolean available = false;
	private final int id;
	private boolean authorized;

	public User(String login, String password, int id) {
		super();
		this.login = login;
		this.password = password;
		this.id = id;
		this.authorized = false;
	}

	public boolean isAuthorized() {
		return authorized;
	}

	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}

	public String setLogin(String login) {
		available = true;
		this.login = login;
		return login;
	}
	
	public String getLogin() {
		return login;
	}

	public String setPassword(String password) {
		this.password = password;
		return password;
	}
	
	public String getPassword() {
		return password;
	}

	public int getId() {
		return id;
	}

	public boolean isAvailable() {
		return available;
	}

}
