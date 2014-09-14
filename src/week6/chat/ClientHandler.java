/**
ccccccccccccccccccc * @author clack008@gmail.com
 */

package week6.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ClientHandler extends Thread {

	private Server server;
	private Socket client;

	private final List<ClientHandler> handlers;

	protected static Logger log = LoggerFactory.getLogger(Client.class);

	private BufferedReader in;
	private PrintWriter out;

	private final ConcurrentMap<String, User> users;
	private final ConcurrentMap<Integer, Boolean> authorized;

	User user;

	private boolean isPublic = false;

	private PrivateMessage privateResponse;
	private PublicMessage publicResponse;

	// �����, ����� ��������� ������
	private int id;

	public ClientHandler(Server server, Socket socket, int counter,
			ConcurrentMap<String, User> users,
			ConcurrentMap<Integer, Boolean> authorized,
			List<ClientHandler> handlers) throws Exception {
		this.handlers = handlers;
		this.authorized = authorized;
		this.users = users;
		this.server = server;
		this.client = socket;
		user = new User("Undefined", "Undefine", counter);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream());
		id = counter;
	}

	// �������� ��������� � �����, ��������� � ��������
	public void send(String message) {
		out.println(message);
		out.flush();
	}

	@Override
	public void run() {

		// � ��������� ������ ���� ������ �� �������
		try {
			handlers.add(this);
			sendPrivate(user, user.getId(),
					"���������� � �������� ������� �����������.");
			server.broadcastPrivate(privateResponse);
			String request = null;
			while ((request = in.readLine()) != null) {
				process(request);
				if (ClientHandler.interrupted()) {
					System.out.println("����� �������");
					server.broadcastPrivate(privateResponse);
					client.close();
					return;
				}

				log.info("Handler[" + id + "]<< " + request);
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
					response = "������������ ������� ������.";
				} else {
					response = "������������ �� ��� ������.";
				}
				break;

			case LOGIN:
				isPublic = false;
				if (login(commands[1], commands[2])) {
					authorized.put(id, Boolean.TRUE);
					response = "�� ������� ����� � ���.";
				} else {
					response = "������������ ������������ ��� ������.";
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
					response = "�� �� ������������.";
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
					response = "�� �� ������������.";
				}

				break;

			case EXIT:
				isPublic = false;
				handlers.remove(this);
				Thread.currentThread().interrupt();
				log.info("Client disconnected: "
						+ client.getInetAddress().toString() + ":"
						+ client.getPort());
				response = "�� ��������!";
				break;

			case BLANK:
				isPublic = false;
				response = "�������� �� ����������.";
				break;
			}

		} catch (Exception e) {
			isPublic = false;
			response = "������������ ��������. ��������� ����.";
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