package sync;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import sendable.Message;

/**
 * This class is supposed to store the socket of the connection generated with the server.
 * It is intended to be a Singleton.
 * @author raphael.bernardo
 */
public class ClientStream {

	private Socket sock = null;

	public Socket getSock() {
		return sock;
	}
	public void setSock(Socket sock) {
		this.sock = sock;
	}


	//SINGLETON PATTERN BLOCK
	private static ClientStream instance;
	public static ClientStream getInstance() {
		if (instance == null){
			instance = new ClientStream();
		}
		return instance;
	}
	private ClientStream() {
	}

	public synchronized void sendMessage(Message m) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(this.sock.getOutputStream());
		oos.writeObject(m);
		oos.flush();
//		oos.close();
	}

	public synchronized Object receiveMessage() throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(this.sock.getInputStream());
		Object o = ois.readObject();
//		ois.close();
		return o;
	}

}
