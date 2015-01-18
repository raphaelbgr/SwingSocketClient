package gui.buttonlisteners;

import gui.janelas.JanelaMain;
import gui.janelas.JanelaSelectServer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import sendable.Message;
import sync.ClientStream;

public class JButtonDisconnectListener implements ActionListener {

	private JanelaSelectServer jsv = null;
	private JanelaMain jam;
	private Socket sock = ClientStream.getInstance().getSock();
	private ClientStream stream = ClientStream.getInstance();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			if (sock != null && sock.isConnected()) {
				Message m = new Message(((JanelaSelectServer) jsv).getField_name().getText(), ((JanelaSelectServer) jsv).getField_name().getText(),sock.getLocalAddress().toString());
				m = m.buildDisconnectMessage();
				m.setPcname(sock.getInetAddress().getCanonicalHostName());
				
				//SENDS THE DISCONNECTION MESSAGE
				stream.sendMessage(m);

				jsv.unlockFields();
				jam.getCn_log().setText("Disconencted from host");
				jam.getCn_log().setBackground(Color.LIGHT_GRAY);
			} else if (sock == null) {
				jam.getCn_log().setText("No connection has been made yet.");
				jam.getCn_log().setBackground(Color.red);
			} else if (!sock.isConnected()) {
				sock.close();
				jam.getCn_log().setText("Already disconnected.");
				jam.getCn_log().setBackground(Color.red);
			}
		} catch (SocketException e2){ 
			jam.getCn_log().setText("Already disconnected.");
			jam.getCn_log().setBackground(Color.LIGHT_GRAY);
		} catch (IOException e1) {
			e1.printStackTrace();
			jam.getCn_log().setText("Could not disconnect from host");
			jam.getCn_log().setBackground(Color.RED);
		}
	}

	public JButtonDisconnectListener(JanelaMain jam) {
		this.jsv = jam.getJsv();
		this.jam = jam;
	}

}
