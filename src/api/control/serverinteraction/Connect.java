package api.control.serverinteraction;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import api.control.sync.ClientStream;
import api.model.clients.Client;

public class Connect {

	public Connect(String ip, int port) throws UnknownHostException, IOException {
		ClientStream stream 	= ClientStream.getInstance();
		stream.setSock(new Socket(ip, port));
	}
	
	public Connect(Client c) throws UnknownHostException, IOException {
		ClientStream stream 	= ClientStream.getInstance();
		stream.setSock(new Socket(c.getTargetIp(), c.getTargetPort()));

		//CONNECTION MESSAGE
		stream.sendObject(c);
	}
}