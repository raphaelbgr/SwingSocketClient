package api;

import java.io.IOException;

import api.control.serverinteraction.Connect;
import api.control.serverinteraction.Send;
import api.control.services.ApiReceiveFromServerThread;
import api.model.clients.Client;
import api.model.exceptions.LocalException;
import api.control.sync.Status;

public class ApiSendFacade {
	
	public static boolean send(Object o) {
		try {
			new Send(o);
		} catch (IOException | LocalException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean connect(String ip, int port, ApiReceiveInterface apiBridge) throws LocalException {
		try {
			new Connect(ip, port);
			Thread t1 = new Thread(new ApiReceiveFromServerThread(apiBridge));
			t1.start();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Client login(String login, String password) {
		Client client = new Client(login, password);
		client.setVersion(Status.VERSION);
		client.setConnect(true);
		ApiSendFacade.send(client);
		return null;
	}
	
}
