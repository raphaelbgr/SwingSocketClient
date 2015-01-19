package gui.buttonlisteners;
import gui.janelas.JanelaMain;
import gui.janelas.JanelaSelectServer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import sendable.Message;
import serverinteraction.Send;
import sync.ClientStream;

public class JButtonSendServerListener implements ActionListener {

	Message m = null;
	private JanelaMain jam;
	private JanelaSelectServer jsv;
	private Socket sock = ClientStream.getInstance().getSock();
	private ClientStream stream = ClientStream.getInstance();
	private Send send = new Send(sock);


	@Override
	public void actionPerformed(ActionEvent e) {
		send.sendAndhandleLog(jam);
	}

	public JButtonSendServerListener(JanelaMain jam) {
		this.jam = jam;
		this.jsv = jam.getJsv();
	}

}
