package serverinteraction;

import gui.WindowDataFacade;
import gui.janelas.JanelaSelectServer;

import java.io.IOException;
import java.net.UnknownHostException;

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

		//DISCONNECTION MESSAGE
		stream.sendMessage(dm);
		stream.getSock().close();
		stream.checkOnlineStatus();
		
	}
	
}
