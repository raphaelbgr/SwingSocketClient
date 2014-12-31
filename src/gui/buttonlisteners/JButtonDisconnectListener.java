package gui.buttonlisteners;

import gui.janelas.JanelaMain;
import gui.janelas.JanelaSelectServer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketException;

import sendable.Message;
import clientmain.ClientMain;

public class JButtonDisconnectListener implements ActionListener {

	private JanelaSelectServer jsv = null;
	private JanelaMain jam;

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			if (ClientMain.sock != null && ClientMain.sock.isConnected()) {
				Message m = new Message(((JanelaSelectServer) jsv).getField_name().getText(), ((JanelaSelectServer) jsv).getField_name().getText(),ClientMain.sock.getLocalAddress().toString());
				m.setPcname(ClientMain.sock.getInetAddress().getCanonicalHostName());
				
				//SENDS THE DISCONNECTION MESSAGE
				ClientMain.oos.writeObject(m.buildDisconnectMessage());
				ClientMain.sock.close();
				ClientMain.sock = null;
				jsv.unlockFields();
				jam.getJtxt_cnlog().setText("Disconencted from host");
				jam.getJtxt_cnlog().setBackground(Color.LIGHT_GRAY);
			} else if (ClientMain.sock == null) {
				jam.getJtxt_cnlog().setText("No connection has been made yet.");
				jam.getJtxt_cnlog().setBackground(Color.red);
			} else if (!ClientMain.sock.isConnected()) {
				ClientMain.sock.close();
				jam.getJtxt_cnlog().setText("Already disconnected.");
				jam.getJtxt_cnlog().setBackground(Color.red);
			}
		} catch (SocketException e2){ 
			jam.getJtxt_cnlog().setText("Already disconnected.");
			jam.getJtxt_cnlog().setBackground(Color.LIGHT_GRAY);
		} catch (IOException e1) {
			e1.printStackTrace();
			jam.getJtxt_cnlog().setText("Could not disconnect from host");
			jam.getJtxt_cnlog().setBackground(Color.RED);
		}
	}

	public JButtonDisconnectListener(JanelaMain jam, JanelaSelectServer jsv) {
		this.jsv = jsv;
		this.jam = jam;
	}

}
