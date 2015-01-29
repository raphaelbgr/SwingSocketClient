package gui.buttonlisteners;

import gui.janelas.JanelaMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import serverinteraction.Disconnect;
import sync.ClientStream;

public class JButtonExitListener implements ActionListener {

	@SuppressWarnings("unused")
	private JanelaMain jam;
	private Socket sock = ClientStream.getInstance().getSock();

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			try {
				new Disconnect();
				sock.close();
			} catch (IOException e1) {
				//e1.printStackTrace();
			} finally {
				System.exit(0);
			}
		} finally {
			System.exit(0);
		}
	}

	public JButtonExitListener(JanelaMain jam) {
		this.jam = jam;
	}

}
