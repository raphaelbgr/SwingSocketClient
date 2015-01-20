package serverinteraction;

import gui.WindowDataFacade;
import gui.janelas.JanelaSelectServer;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import sendable.ConnectionMessage;
import sync.ClientStream;

public class Connect {
	
	private Socket sock 	= null;
	private String owner 	= null;
	private String pcname	= null;

	public Connect(String ip, int port) throws UnknownHostException, IOException {
		ClientStream stream 	= ClientStream.getInstance();
		JanelaSelectServer jsv 	= WindowDataFacade.getJsv();
		
		ip 				= sock.getInetAddress().getHostAddress();
		port			= (jsv).getPortNumber();
		
		this.sock 		= stream.getSock();
		this.owner 		= jsv.getNameField();
		this.pcname 	= sock.getInetAddress().getCanonicalHostName();
		
		stream.setSock(new Socket(ip, port));
		
		ConnectionMessage cm = new ConnectionMessage();
		cm = (ConnectionMessage) cm.buildConnectMessage();
		cm.setOwner(this.owner);
		cm.setIp(ip);
		cm.setPcname(this.pcname);

		//CONNECTION MESSAGE
		stream.sendMessage(cm);
		stream.checkOnlineStatus();
	}

}
