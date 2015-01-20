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
import serverinteraction.Send;
import sync.ClientStream;

public class JButtonSendServerListener implements ActionListener {

	Message m = null;
	private JanelaMain jam;
	private JanelaSelectServer jsv;
	private Send send = new Send();


	@Override
	public void actionPerformed(ActionEvent e) {
		send.sendAndhandleLog(jam);
	}
	
	public void sendAndhandleLog(JanelaMain jam) {
		try {
			if (sock == null || sock.isConnected() == false) {
				jam.getConnectionLog().setText("A connection is needed first");
				jam.getConnectionLog().setBackground(Color.RED);
			} else if (((JanelaMain) jam).getTextField().getText().length() == 0) {
				jam.getConnectionLog().setText("Empty messages not permitted");
				jam.getConnectionLog().setBackground(Color.RED);
			} else {
				this.send(sock,this.assembleMessage(),jam);
				jam.getConnectionLog().setText("Message succefully sent to server");
				jam.getConnectionLog().setBackground(Color.GREEN);
				(jam).getTextField().setText("");
			}
		} catch (SocketException e2) {
			jam.getConnectionLog().setText("Currently not conencted to any host");
			jam.getConnectionLog().setBackground(Color.RED);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public JButtonSendServerListener(JanelaMain jam) {
		this.jam = jam;
		this.jsv = jam.getJsv();
	}

}
