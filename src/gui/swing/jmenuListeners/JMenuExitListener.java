package gui.swing.jmenuListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import serverinteraction.Disconnect;
import sync.ClientStream;

public class JMenuExitListener implements ActionListener {

	private Socket sock = ClientStream.getInstance().getSock();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			new Disconnect();
			sock.close();
		} catch (IOException e1) {
			//e1.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

}
