package gui.buttonlisteners;

import gui.janelas.JanelaMain;
import gui.janelas.JanelaSelectServer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import sendable.Message;
import sync.ClientStream;

public class JButtonExitListener implements ActionListener {

	@SuppressWarnings("unused")
	private JanelaMain jam;
	private JanelaSelectServer jsv;
	private Socket sock = ClientStream.getInstance().getSock();
	private ClientStream stream = ClientStream.getInstance();

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Message m = new Message((jsv).getField_name().getText(), (jsv).getField_name().getText(),sock.getLocalAddress().toString());
			m.setPcname(sock.getInetAddress().getCanonicalHostName());
			stream.sendMessage(m.buildDisconnectMessage());
			sock.close();
		} catch (SocketException e2) {
			e2.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	public JButtonExitListener(JanelaMain jam) {
		this.jam = jam;
		this.jsv = jam.getJsv();
	}

}
