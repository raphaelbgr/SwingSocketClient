package app.control.serverinteraction;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.control.sync.ClientStream;
import app.model.clients.Client;
import app.model.messages.DisconnectionMessage;

public class Disconnect {

	public Disconnect() throws UnknownHostException, IOException {
		ClientStream stream = ClientStream.getInstance();
		DisconnectionMessage dm = new DisconnectionMessage();
		dm = (DisconnectionMessage) dm.buildDisconnectMessage();
		stream.sendObject(dm);
	}

	public Disconnect(Client c) throws UnknownHostException, IOException {
		ClientStream stream 	= ClientStream.getInstance();
		DisconnectionMessage dm = new DisconnectionMessage();
		dm = (DisconnectionMessage) dm.buildDisconnectMessage();
		dm.setOwnerName(c.getName());
		stream.sendObject(dm);
	}

	@SuppressWarnings("unused")
	private String getTimestamp() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateFormatted = formatter.format(new Date());
		return "["+dateFormatted+"]" + " ";
	}

}
