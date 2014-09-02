/**
 * @author clack008@gmail.com
 */

package week5.fileTransfer;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	private Socket socket;
	private FileOutputStream fos;
	private BufferedOutputStream bos;

	public static void main(String[] args) throws Exception {

		String fileName;
		String host;

		if (args.length < 2) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Введите название хоста и файла:");
			host = sc.next();
			fileName = sc.next();
			sc.close();

		} else {
			fileName = args[0];
			host = args[1];
		}

		Client client = new Client();
		client.start(host, fileName);
	}

	public void start(String host, String fileName) {
		init(host);
		process(fileName);
	}

	private void init(String host) {
		try {
			socket = new Socket(host, 20000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void process(String fileName) {
		byte[] mybytearray = new byte[1024];
		InputStream is;
		try {
			is = socket.getInputStream();
			fos = new FileOutputStream(fileName);
			bos = new BufferedOutputStream(fos);
			int bytesRead = is.read(mybytearray, 0, mybytearray.length);
			bos.write(mybytearray, 0, bytesRead);
			bos.flush();
			System.out.println("Файл " + fileName + " был успешно принят.");
		} catch (IOException e) {
			System.out.println("Файл " + fileName + " не был принят.");
		} finally {
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Socket getSocket() {
		return socket;
	}
}
