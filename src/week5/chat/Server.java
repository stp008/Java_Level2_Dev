/**
 * @author clack008@gmail.com
 */

package week5.chat;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {

	protected static Logger log = LoggerFactory.getLogger("ChatServer");
	private static final int PORT = 19000;
	private static int counter = 0;

	private final ConcurrentMap<String, User> users = new ConcurrentHashMap<>();
	private final ConcurrentMap<Integer, Boolean> authorized = new ConcurrentHashMap<>();

	// список обработчиков для клиентов
	private List<ClientHandler> handlers = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		Server server = new Server();
		server.startServer();
	}

	public void startServer() throws Exception {
		log.info("Starting server...");
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(PORT);
		while (true) {

			// блокируемся и ждем клиента
			Socket socket = serverSocket.accept();
			log.info("Client connected: " + socket.getInetAddress().toString()
					+ ":" + socket.getPort());

			int clientId = counter;

			// создаем обработчик
			ClientHandler handler = new ClientHandler(this, socket, clientId,
					users, authorized);
			authorized.put(clientId, Boolean.FALSE);
			counter++;
			handlers.add(handler);
			handler.start();
		}
	}

	/*
	 * Для каждого присоединившегося пользователя создается поток обработки
	 * независимый от остальных
	 */

	// рассылаем всем подписчикам
	public void broadcastPublic(PublicMessage publicMessage) {
		String message = publicMessage.getMessage();

		log.info("Public message to all : " + message);
		String from = publicMessage.getFrom().getLogin();
		for (ClientHandler handler : handlers) {
			handler.send("From " + from + ": " + message);
		}
	}

	public void broadcastPrivate(PrivateMessage privateMessage) {
		int destination = privateMessage.getTo();
		String message = privateMessage.getMessage();
		String from = privateMessage.getFrom().getLogin();

		log.info("Private message sent to Handler[" + destination + "]");

		for (ClientHandler handler : handlers) {
			if (handler.getNumber() == destination) {
				handler.send("Private message from " + from + ": " + message);
			}
		}
	}

}
