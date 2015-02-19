package serverinteraction;

import gui.WindowDataFacade;
import gui.janelas.JanelaSelectServer;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import sendable.DisconnectionMessage;
import sync.ClientStream;

public class Disconnect {
	
	private String owner 	= null;
	private String pcname	= null;

	public Disconnect() throws UnknownHostException, IOException {
		ClientStream stream 	= ClientStream.getInstance();
		JanelaSelectServer jsv 	= WindowDataFacade.getJsv();
		
		this.owner 		= jsv.getNameField();
		
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
			stream.getSock().close();
			stream.setSock(null);
			stream.checkOnlineStatus();
		}
		
	}
	
	private String getTimestamp() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateFormatted = formatter.format(new Date());
		return "["+dateFormatted+"]" + " ";
	}
	
}
