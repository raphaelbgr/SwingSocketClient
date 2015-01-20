package sync;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import clientmain.ClientMain;

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

	public synchronized void sendMessage(Object o) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(this.sock.getOutputStream());
		oos.writeObject(o);
		oos.flush();
	}

	public synchronized Object receiveMessage() throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(this.sock.getInputStream());
		Object o = ois.readObject();
		return o;
	}
	
	public boolean checkOnlineStatus() {
		if(this.sock.isConnected()) {
			ClientMain.CONNECTED = true;
			return true;
		} else {
			ClientMain.CONNECTED = false;
			return false;
		}
	}

}
