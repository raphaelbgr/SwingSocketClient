package api.control.services;

import java.io.IOException;

import api.ApiReceiveInterface;
import api.control.sync.ClientStream;
import api.control.sync.Status;
import api.model.clients.Client;
import api.model.messages.DisconnectionMessage;
import api.model.messages.Message;
import api.model.messages.NormalMessage;
import api.model.messages.ServerMessage;

public class ApiReceiveFromServerThread implements Runnable {

	private ClientStream stream = ClientStream.getInstance();
	private ApiReceiveInterface api;

	@Override
	public void run() {
		boolean suicide = false;
		while (!suicide) {
			try {
				final Object o = stream.receiveMessage();
				api.onReceive(o);
				if (o instanceof Message) {
					if (o instanceof NormalMessage) {
						api.onReceiveNormalMessage((NormalMessage) o);
					} else if (o instanceof DisconnectionMessage) {
						Status.getInstance().setConnected(false);
						api.onReceiveDisconnectionMessage((DisconnectionMessage) o);
						break;
					} else if (o instanceof ServerMessage) {
						api.onReceiveServerMessage((ServerMessage) o);
					}
				} else if (o instanceof Client) {
					Status.getInstance().setConnected(true);
					api.onReceiveClient((Client) o);
				}
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				suicide = true;
			}
		}
	}
	
	public ApiReceiveFromServerThread(ApiReceiveInterface apiBridge) {
		this.api = apiBridge;
	}
	
}
