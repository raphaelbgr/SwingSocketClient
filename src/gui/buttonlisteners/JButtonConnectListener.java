package gui.buttonlisteners;

import gui.janelas.JanelaMain;
import gui.janelas.JanelaSelectServer;
import gui.updatelogs.ConnectionLogUpdater;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;

import serverinteraction.Connect;
import sync.ClientStream;

public class JButtonConnectListener implements ActionListener {

//	private JanelaMain jam = WindowDataFacade.getJam();
	private JanelaSelectServer jsv = null;
	private String ip;
	private int port;
	private ClientStream stream = ClientStream.getInstance();
	private ConnectionLogUpdater log = null;

	@Override
	public void actionPerformed(ActionEvent e) {
		if ((jsv.getIpText()) != null) {
			if ((jsv.getPortNumber() >= 1) && (jsv.getPortNumber() <= 65535)) {
				try {
					if (stream.getSock() == null) {
						new Connect(ip, port);
						jsv.lockFields();
					} else {
						new Connect(ip, port);
						jsv.lockFields();
					}
				} catch (ConnectException e5) {
					log.setErrorMessage("LOCAL> Connection refused on " + jsv.getIpText() + ":" + jsv.getPortNumber());
					jsv.unlockFields();
				} catch (SocketException e6) {
					log.setErrorMessage("LOCAL> Lost conenction to server. Socket dropped...Try again");
					jsv.unlockFields();
				} catch (NumberFormatException e2) {
					log.setErrorMessage("LOCAL> Wrong or no IP/port informed");
					jsv.unlockFields();
				} catch (UnknownHostException e3) {
					log.setErrorMessage("LOCAL> Host could not be resolved");
					jsv.unlockFields();
				} catch (IllegalArgumentException e4) {
					log.setErrorMessage("LOCAL> Invalid Port value");
					jsv.unlockFields();
				} catch (IOException e1) {
					log.setErrorMessage("LOCAL> I/O Error");
					jsv.unlockFields();
				}
			}
		}
	}

	public JButtonConnectListener(JanelaMain jam, ConnectionLogUpdater log) {
//		this.jam = jam;
		this.jsv = jam.getJsv();
		this.log = log;
	}

}
