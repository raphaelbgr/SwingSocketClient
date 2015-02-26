package serverinteraction;

import gui.WindowDataFacade;
import gui.janelas.JanelaMain;
import gui.janelas.JanelaSelectServer;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import clientmain.ClientMain;
import clientmain.Status;
import sendable.Client;
import sync.ClientStream;

public class Connect {

	private Socket sock 	= null;
	private String owner 	= null;
	private String pcname	= null;

	public Connect(String ip, int port) throws UnknownHostException, IOException {
		ClientStream stream 	= ClientStream.getInstance();
		JanelaSelectServer jsv 	= WindowDataFacade.getJsv();
		JanelaMain jam 			= WindowDataFacade.getJam();

		ip 				= jsv.getIpText();
		port			= (jsv).getPortNumber();

		this.sock 		= stream.getSock();
		this.owner 		= jsv.getNameFieldText();

		stream.setSock(new Socket(ip, port));

		Client c = new Client();
		c.setVersion(ClientMain.version);
		c.setName(owner);

		//CONNECTION MESSAGE
		stream.sendObject(c);
//		stream.checkOnlineStatus();
	}
}
