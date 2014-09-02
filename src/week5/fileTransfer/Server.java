/**
 * @author clack008@gmail.com
 */

package week5.fileTransfer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	private ServerSocket serverSocket;
	File file;

	public static void main(String[] args) throws IOException {
		String fileName;

		if (args.length < 1) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Введите название передаваемого файла:");
			fileName = sc.next();
			sc.close();

		} else {
			fileName = args[0];
		}

		Server server = new Server();
		server.start(fileName);
	}

	public void start(String fileName) {
		init();
		process(fileName);
	}

	private void init() {
		try {
			serverSocket = new ServerSocket(20000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void process(String fileName) {
		file = new File(fileName);

		while (true) {
			Socket sock;
			try {
				sock = serverSocket.accept();
				byte[] mybytearray = new byte[(int) file.length()];
				BufferedInputStream bis = new BufferedInputStream(
						new FileInputStream(file));
				bis.read(mybytearray, 0, mybytearray.length);
				OutputStream os = sock.getOutputStream();
				os.write(mybytearray, 0, mybytearray.length);
				os.flush();
				bis.close();
				sock.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}