package serverinteraction;

import gui.swing.WindowDataFacade;
import gui.swing.janelas.JanelaSelectServer;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import sendable.Client;
import sendable.DisconnectionMessage;
import sync.ClientStream;

public class Disconnect {

	private String owner 	= null;
	private String pcname	= null;

	public Disconnect() throws UnknownHostException, IOException {
		ClientStream stream 	= ClientStream.getInstance();
		JanelaSelectServer jsv 	= WindowDataFacade.getJsv();

		this.owner 		= jsv.getNameFieldText();

		DisconnectionMessage dm = new DisconnectionMessage();
		dm = (DisconnectionMessage) dm.buildDisconnectMessage();
		dm.setOwner(this.owner);
		dm.setPcname(this.pcname);

		try {
			//EXPECTS THE SERVER CONFIRMATION
			stream.sendObject(dm);
			//			Object o = stream.receiveMessage();
			//			if (o instanceof DisconnectionMessage) {
			//				WindowDataFacade.getJam().getConnectionLog().setGreyMessage(getTimestamp() + "LOCAL> Disconnected succefully");
			//			}
			//		} catch (ClassNotFoundException e) {
			//			// TODO Auto-generated catch block
			//			// e.printStackTrace();
		} finally {
			//CLOSES SOCKET
			//			stream.getSock().close();
			//			stream.setSock(null);
			//			stream.checkOnlineStatus();
		}
	}

	public Disconnect(Client c) throws UnknownHostException, IOException {
		ClientStream stream 	= ClientStream.getInstance();

		DisconnectionMessage dm = new DisconnectionMessage();
		dm = (DisconnectionMessage) dm.buildDisconnectMessage();
		dm.setOwner(c.getName());

		stream.sendObject(dm);
//		stream.getSock().close();
//		stream.setSock(null);
	}

	@SuppressWarnings("unused")
	private String getTimestamp() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateFormatted = formatter.format(new Date());
		return "["+dateFormatted+"]" + " ";
	}

}
