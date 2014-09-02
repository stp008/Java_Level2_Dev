/**
 * @author clack008@gmail.com
 */

package week5.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	protected static Logger log = LoggerFactory.getLogger(Client.class);

	public static final int PORT = 19000;
	public String HOST = "localhost";
	private static final String EXIT = "EXIT";

	public static void main(String[] args) throws Exception {
		Client client;
		if (args.length != 0) {
			client = new Client(args[0]);
		} else {
			client = new Client();
		}
		client.startClient();

	}

	public Client() {
		super();
	}

	public Client(String host) {
		super();
		this.HOST = host;
	}

	public void startClient() {
		Socket socket = null;
		BufferedReader in = null;
		try {
			socket = new Socket(HOST, PORT);
			ConsoleThread console = new ConsoleThread(socket);
			console.start();

			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			String line = null;
			while ((line = in.readLine()) != null) {
				System.out.println(">> " + line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void send(String message) {

	}

	class ConsoleThread extends Thread {
		BufferedReader console = new BufferedReader(new InputStreamReader(
				System.in));
		PrintWriter out;

		public ConsoleThread(Socket socket) throws Exception {
			out = new PrintWriter(socket.getOutputStream());
		}

		@Override
		public void run() {
			try {
				String line;
				while ((line = console.readLine()) != null) {
					if (EXIT.equalsIgnoreCase(line)) {
						log.info("Closing chat");
						break;
					}
					out.println(line);
					out.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				out.close();
			}
		}

	}

}
