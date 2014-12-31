package gui.buttonlisteners;

import gui.janelas.JanelaMain;
import gui.janelas.JanelaSelectServer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketException;

import sendable.Message;
import clientmain.ClientMain;

public class JButtonExitListener implements ActionListener {

	@SuppressWarnings("unused")
	private JanelaMain jam;
	private JanelaSelectServer jsv;

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Message m = new Message(((JanelaSelectServer) jsv).getField_name().getText(), ((JanelaSelectServer) jsv).getField_name().getText(),ClientMain.sock.getLocalAddress().toString());
			m.setPcname(ClientMain.sock.getInetAddress().getCanonicalHostName());
			ClientMain.oos.writeObject(m.buildDisconnectMessage());
			clientmain.ClientMain.oos.close();
			clientmain.ClientMain.sock.close();
		} catch (SocketException e2) {
			try {
				clientmain.ClientMain.oos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
				clientmain.ClientMain.oos = null;
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			System.exit(0);
		}
	}
	
	public JButtonExitListener(JanelaMain jam, JanelaSelectServer jsv) {
		this.jam = jam;
		this.jsv = jsv;
	}

}
