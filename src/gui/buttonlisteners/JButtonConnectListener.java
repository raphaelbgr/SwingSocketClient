package gui.buttonlisteners;

import gui.WindowDataFacade;
import gui.janelas.JanelaMain;
import gui.janelas.JanelaSelectServer;
import gui.updatelogs.ConnectionLogUpdater;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import serverinteraction.Connect;
import sync.ClientStream;
import clientmain.Status;

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
						log.setGreyMessage(getTimestamp() + "LOCAL> Connected");
						WindowDataFacade.getJam().getJbt_Connect().setEnabled(false);
						WindowDataFacade.getJam().getJbt_Disconn().setEnabled(true);
						WindowDataFacade.getJam().getJbt_send().setEnabled(true);
						Status.getInstance().setConnected(true);
					} else {
						new Connect(ip, port);
						log.setGreyMessage(getTimestamp() + "LOCAL> Connected");
						jsv.lockFields();
						WindowDataFacade.getJam().getJbt_Connect().setEnabled(false);
						WindowDataFacade.getJam().getJbt_Disconn().setEnabled(true);
						WindowDataFacade.getJam().getJbt_send().setEnabled(true);
						Status.getInstance().setConnected(true);
					}
				} catch (ConnectException e5) {
					log.setErrorMessage(getTimestamp() + "LOCAL> Connection refused on " + jsv.getIpText() + ":" + jsv.getPortNumber());
					jsv.unlockFields();
					WindowDataFacade.getJam().getJbt_Connect().setEnabled(true);
					WindowDataFacade.getJam().getJbt_Disconn().setEnabled(false);
					WindowDataFacade.getJam().getJbt_send().setEnabled(false);
					Status.getInstance().setConnected(false);
				} catch (SocketException e6) {
					log.setErrorMessage(getTimestamp() + "LOCAL> Lost conenction to server. Socket dropped...Try again");
					jsv.unlockFields();
					WindowDataFacade.getJam().getJbt_Connect().setEnabled(true);
					WindowDataFacade.getJam().getJbt_Disconn().setEnabled(false);
					WindowDataFacade.getJam().getJbt_send().setEnabled(false);
					Status.getInstance().setConnected(false);
				} catch (NumberFormatException e2) {
					log.setErrorMessage(getTimestamp() + "LOCAL> Wrong or no IP/port informed");
					jsv.unlockFields();
					WindowDataFacade.getJam().getJbt_Connect().setEnabled(true);
					WindowDataFacade.getJam().getJbt_Disconn().setEnabled(false);
					WindowDataFacade.getJam().getJbt_send().setEnabled(false);
					Status.getInstance().setConnected(false);
				} catch (UnknownHostException e3) {
					log.setErrorMessage(getTimestamp() + "LOCAL> Host could not be resolved");
					jsv.unlockFields();
					WindowDataFacade.getJam().getJbt_Connect().setEnabled(true);
					WindowDataFacade.getJam().getJbt_Disconn().setEnabled(false);
					WindowDataFacade.getJam().getJbt_send().setEnabled(false);
					Status.getInstance().setConnected(false);
				} catch (IllegalArgumentException e4) {
					log.setErrorMessage(getTimestamp() + "LOCAL> Invalid Port value");
					jsv.unlockFields();
					WindowDataFacade.getJam().getJbt_Connect().setEnabled(true);
					WindowDataFacade.getJam().getJbt_Disconn().setEnabled(false);
					WindowDataFacade.getJam().getJbt_send().setEnabled(false);
					Status.getInstance().setConnected(false);
				} catch (IOException e1) {
					log.setErrorMessage(getTimestamp() + "LOCAL> I/O Error");
					jsv.unlockFields();
					WindowDataFacade.getJam().getJbt_Connect().setEnabled(true);
					WindowDataFacade.getJam().getJbt_Disconn().setEnabled(false);
					WindowDataFacade.getJam().getJbt_send().setEnabled(false);
					Status.getInstance().setConnected(false);
				}
			}
		}
	}
	
	private String getTimestamp() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateFormatted = formatter.format(new Date());
		return "["+dateFormatted+"]" + " ";
	}

	public JButtonConnectListener(JanelaMain jam, ConnectionLogUpdater log) {
//		this.jam = jam;
		this.jsv = jam.getJsv();
		this.log = log;
	}

}
