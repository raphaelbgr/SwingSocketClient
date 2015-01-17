package serverinteraction;

import gui.janelas.JanelaMain;
import gui.janelas.JanelaSelectServer;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import sendable.Message;
import sync.ClientStream;
import clientmain.ClientMain;

public class Connect {
	
	private Socket sock = ClientStream.getInstance().getSock();
	private ClientStream stream = ClientStream.getInstance();
	private JanelaSelectServer jsv = JanelaMain.getInstance().getJsv();

	public Connect(String ip, int port) throws UnknownHostException, IOException {
		ClientMain.ip 		= (jsv).getIpText();
		ClientMain.port		= (jsv).getPortNumber();
		ClientStream.getInstance().setSock(new Socket(ip, port));

		Message m = new Message();
		m = m.buildConnectMessage();
		m.setOwner(jsv.getNameField());
		m.setIp(sock.getInetAddress().getHostAddress());
		m.setPcname(sock.getInetAddress().getCanonicalHostName());

		//CONNECTION MESSAGE
		stream.sendMessage(m);
	}

}
