package net.sytes.surfael.api.control.services;

import java.io.IOException;

import net.sytes.surfael.api.ApiReceiveInterface;
import net.sytes.surfael.api.control.sync.ClientStream;
import net.sytes.surfael.api.control.sync.Status;
import net.sytes.surfael.api.model.clients.Client;
import net.sytes.surfael.api.model.messages.DisconnectionMessage;
import net.sytes.surfael.api.model.messages.Message;
import net.sytes.surfael.api.model.messages.NormalMessage;
import net.sytes.surfael.api.model.messages.ServerMessage;

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
