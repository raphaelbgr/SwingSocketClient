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
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;

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

	public void sendMessage(Object o) throws IOException {
		oos = new ObjectOutputStream(this.sock.getOutputStream());
		oos.writeObject(o);
		oos.flush();
	}

	public Object receiveMessage() throws IOException, ClassNotFoundException {
		ois = new ObjectInputStream(this.sock.getInputStream());
		Object o = ois.readObject();
		return o;
	}
	
	public boolean checkOnlineStatus() {
		if(this.sock != null && this.sock.isConnected()) {
			ClientMain.CONNECTED = true;
			return true;
		} else {
			ClientMain.CONNECTED = false;
			return false;
		}
	}

}
