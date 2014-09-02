/**
 * @author clack008@gmail.com
 */

package week5.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ClientHandler extends Thread {

	private Server server;
	private Socket client;

	protected static Logger log = LoggerFactory.getLogger(Client.class);

	private BufferedReader in;
	private PrintWriter out;

	private final ConcurrentMap<String, User> users;
	private final ConcurrentMap<Integer, Boolean> authorized;

	User user;

	private boolean isPublic = false;

	private PrivateMessage privateResponse;
	private PublicMessage publicResponse;

	// номер, чтобы различать потоки
	private int id;

	public ClientHandler(Server server, Socket socket, int counter,
			ConcurrentMap<String, User> users,
			ConcurrentMap<Integer, Boolean> authorized) throws Exception {
		this.authorized = authorized;
		this.users = users;
		this.server = server;
		this.client = socket;
		user = new User("Undefined", "Undefine", counter);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream());
		id = counter;
	}

	// Отправка сообщения в сокет, связанный с клиентом
	public void send(String message) {
		out.println(message);
		out.flush();
	}

	@Override
	public void run() {

		// В отдельном потоке ждем данных от клиента
		try {
			String request = null;
			while ((request = in.readLine()) != null) {
				log.info("Handler[" + id + "]<< " + request);
				process(request);
				if (isPublic) {
					server.broadcastPublic(publicResponse);
				} else {
					server.broadcastPrivate(privateResponse);
				}
			}
		} catch (IOException e) {
			log.error("Failed to read from socket");
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.close();
		}
	}

	private void process(String request) {
		String response = "";
		int privateId = id;
		String command = request;
		String[] commands = command.split(" ");
		Commands enum_command;

		try {
			enum_command = Commands.valueOf(commands[0]);
		} catch (Exception e) {
			enum_command = Commands.BLANK;
		}

		try {
			switch (enum_command) {

			case HELP:
				isPublic = false;
				response = Commands.about();
				break;

			case CREATE:
				isPublic = false;
				if (createUser(commands[1], commands[2])) {
					response = "Пользователь успешно создан.";
				} else {
					response = "Пользователь не был создан.";
				}
				break;

			case LOGIN:
				isPublic = false;
				if (login(commands[1], commands[2])) {
					authorized.put(id, Boolean.TRUE);
					response = "Вы успешно вошли в чат.";
				} else {
					response = "Неправильный пользователь или пароль.";
				}
				break;

			case PRIVATE:
				isPublic = false;
				if (isAuthorized()) {
					for (int i = 2; i < commands.length; i++) {
						response += commands[i] + " ";
					}
					privateId = users.get(commands[1]).getId();
				} else {
					response = "Вы не авторизованы.";
				}

				break;

			case SEND:
				if (isAuthorized()) {
					isPublic = true;
					for (int i = 1; i < commands.length; i++) {
						response += commands[i] + " ";
					}
				} else {
					isPublic = false;
					response = "Вы не авторизованы.";
				}

				break;

			case EXIT:
				isPublic = false;
				log.info("Client disconnected: "
						+ client.getInetAddress().toString() + ":"
						+ client.getPort());
				client.close();
				break;

			case BLANK:
				isPublic = false;
				response = "Комманда не существует.";
				break;
			}

		} catch (Exception e) {
			isPublic = false;
			response = "Неправильная комманда. Повторите ввод.";
			sendPrivate(user, id, response);
		}

		if (isPublic) {
			sendPublic(user, response);
		} else {
			sendPrivate(user, privateId, response);
		}

	}

	private boolean createUser(String name, String password) {
		if (users.containsKey(name))
			return false;

		User newUser = new User(name, password, id);

		newUser.setLogin(name);
		newUser.setPassword(password);
		users.put(newUser.getLogin(), newUser);
		return true;
	}

	private boolean login(String name, String password) {
		if (!users.containsKey(name))
			return false;
		user = users.get(name);
		if (!user.getPassword().equals(password))
			return false;
		user.setAuthorized(true);
		authorized.put(user.getId(), true);
		return true;
	}

	private boolean isAuthorized() {
		return authorized.get(id) && user.isAvailable();
	}

	private void sendPrivate(User from, int to, String msg) {
		this.privateResponse = new PrivateMessage(from, to, msg);
	}

	private void sendPublic(User from, String msg) {
		this.publicResponse = new PublicMessage(from, msg);
	}

	public int getNumber() {
		return id;
	}

}