package serverinteraction;

import gui.janelas.JanelaMain;
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
		JanelaSelectServer jsv 	= JanelaMain.getInstance().getJsv();
		
		ip 			= (jsv).getIpText();
		port		= (jsv).getPortNumber();
		stream.setSock(new Socket(ip, port));
		this.sock 	= stream.getSock();
		
		this.owner 		= jsv.getNameField();
		ip 				= sock.getInetAddress().getHostAddress();
		this.pcname 	= sock.getInetAddress().getCanonicalHostName();
		
		
		ConnectionMessage cm = new ConnectionMessage();
		cm = (ConnectionMessage) cm.buildConnectMessage();
		cm.setOwner(this.owner);
		cm.setIp(ip);
		cm.setPcname(this.pcname);

		//CONNECTION MESSAGE
		stream.sendMessage(cm);
	}

}
