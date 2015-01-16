package gui.buttonlisteners;

import gui.janelas.JanelaMain;
import gui.janelas.JanelaSelectServer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;

import serverinteraction.Connect;
import sync.ClientStream;

public class JButtonConnectListener implements ActionListener {

	private JanelaMain jam;
	private JanelaSelectServer jsv;
	private String ip;
	private int port;
	private ClientStream stream = ClientStream.getInstance();

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (ClientStream.getInstance().getSock() == null) {
				new Connect(ip, port);
				jsv.lockFields();
				

			} else {
				jam.getCn_log().setText("Disconnection required first...");
				jam.getCn_log().setBackground(Color.RED);
			}
		} catch (ConnectException e5) {
			jam.getCn_log().setText("Connection refused by host " + ((JanelaSelectServer) jsv).getIpText() + " on port " + ((JanelaSelectServer) jsv).getPortNumber());
			jam.getCn_log().setBackground(Color.RED);
		} catch (SocketException e6) {
			jam.getCn_log().setText("Lost conenction to server. Socket dropped...Try again.");
			jam.getCn_log().setBackground(Color.RED);
			stream.setSock(null);
		} catch (NumberFormatException e2) {
			jam.getCn_log().setText("No IP or Port informed");
			jam.getCn_log().setBackground(Color.RED);
		} catch (UnknownHostException e3) {
			jam.getCn_log().setText("Host could not be resolved");
			jam.getCn_log().setBackground(Color.RED);
		} catch (IllegalArgumentException e4) {
			jam.getCn_log().setText("Invalid Port value");
			jam.getCn_log().setBackground(Color.RED);
		} catch (IOException e1) {
			jam.getCn_log().setText("I/O Error");
			jam.getCn_log().setBackground(Color.RED);
			e1.printStackTrace();
		}
	}

	public JButtonConnectListener(JanelaMain jam, JanelaSelectServer jsv) {
		this.jam = jam;
		this.jsv = jsv;
		this.ip = ((JanelaSelectServer) jsv).getIpText();
		this.port =((JanelaSelectServer) jsv).getPortNumber();
	}

}
