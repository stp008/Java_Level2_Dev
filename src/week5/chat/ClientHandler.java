package week5.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ClientHandler extends Thread {

	private Server server;
	private Socket client;

	protected static Logger log = LoggerFactory.getLogger(Client.class);

	private BufferedReader in;
	private PrintWriter out;

	private final ConcurrentMap<Integer, User> users;
	private final ConcurrentMap<Integer, Boolean> authorized;

	User user;

	private boolean publicUse = false;

	private PrivateMessage privateResponse;
	private PublicMessage publicResponse;

	// номер, чтобы различать потоки
	private int id;

	public ClientHandler(Server server, Socket socket, int counter,
			ConcurrentMap<Integer, User> users,
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
				if (publicUse) {
					server.broadcastPublic(publicResponse);
				} else {
					server.broadcastPrivate(privateResponse);
				}
			}
		} catch (IOException e) {
			log.error("Failed to read from socket");
		}
	}

	private void process(String request) {
		String response = "";

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
				publicUse = false;
				response = Commands.about();
				sendPrivate(user, id, response);
				break;
				
			case CREATE:
				publicUse = false;
				if (createUser(commands[1], commands[2])) {
					response = "Пользователь успешно создан.";
				} else {
					response = "Пользователь не был создан.";
				}
				sendPrivate(user, id, response);
				break;

			case LOGIN:
				publicUse = false;
				if (login(commands[1], commands[2])) {
					authorized.put(id, Boolean.TRUE);
					response = "Вы успешно вошли в чат.";
				} else {
					response = "Неправильный пользователь или пароль.";
				}
				sendPrivate(user, id, response);
				break;

			case PRIVATE:
				publicUse = false;
				if (isAuthorized()) {
					for (int i = 2; i < commands.length; i++) {
						response += commands[i] + " ";
					}
					sendPrivate(user, Integer.valueOf(commands[1]), response);
				} else {
					response = "Вы не авторизованы.";
				}
				sendPrivate(user, id, response);
				break;

			case SEND:
				publicUse = true;
				if (isAuthorized()) {
					for (int i = 1; i < commands.length; i++) {
						response += commands[i] + " ";
					}
				} else {
					response = "Вы не авторизованы.";

				}
				sendPublic(user, response);
				break;

			case EXIT:
				publicUse = false;
				log.info("Client disconnected: "
						+ client.getInetAddress().toString() + ":"
						+ client.getPort());
				client.close();
				break;

			case BLANK:
				publicUse = false;
				response = "Комманда не существует.";
				sendPrivate(user, id, response);
				break;
			}

		} catch (Exception e) {
			publicUse = false;
			response = "Неправильная комманда. Повторите ввод.";
			sendPrivate(user, id, response);
		}

	}

	private boolean createUser(String name, String password) {
		if (users.containsKey(name))
			return false;
		user.setLogin(name);
		user.setPassword(password);
		users.put(id, user);
		return true;
	}

	private boolean login(String name, String password) {
		Set<Integer> userId = users.keySet();
		int foundId = -1;
		if (!user.getLogin().equals(name)) {
			for (Integer elem : userId) {
				if (users.get(elem).getLogin().equals(name)
						&& users.get(elem).getPassword().equals(password)) {
					foundId = elem;
					break;
				}
			}
		} else {
			if (!user.getPassword().equals(password)) {
				return false;
			}
		}

		if (foundId != -1)
			user = users.get(foundId);

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