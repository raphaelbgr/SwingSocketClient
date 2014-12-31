package gui.buttonlisteners;

import gui.janelas.JanelaMain;
import gui.janelas.JanelaSelectServer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import sendable.Message;
import threads.ReceiveFromServerThread;
import clientmain.ClientMain;

public class JButtonConnectListener implements ActionListener {

	private JanelaMain jam;
	private JanelaSelectServer jsv;

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (ClientMain.sock == null) {
				ClientMain.sock = new Socket(((JanelaSelectServer) jsv).getIpText(), ((JanelaSelectServer) jsv).getPortNumber());
				jam.getJtxt_cnlog().setText("Connected succesfuly on server " + ClientMain.ip + " on port " + ClientMain.port);
				jam.getJtxt_cnlog().setBackground(Color.GREEN);
				jsv.lockFields();
				ClientMain.oos 		= new ObjectOutputStream(clientmain.ClientMain.sock.getOutputStream());
				ClientMain.ip 		= ((JanelaSelectServer) jsv).getIpText();
				ClientMain.port		= ((JanelaSelectServer) jsv).getPortNumber();
				Message m = new Message();
				m = m.buildConnectMessage();
				m.setOwner(jsv.getNameField());
				m.setIp(ClientMain.sock.getInetAddress().getHostAddress());
				m.setPcname(ClientMain.sock.getInetAddress().getCanonicalHostName());
				
				//CONNECTION MESSAGE
				clientmain.ClientMain.oos.writeObject(m);
				ClientMain.receiver = new Thread(new ReceiveFromServerThread(jam));
				ClientMain.receiver.start();
				
			} else {
				jam.getJtxt_cnlog().setText("Disconnection required first...");
				jam.getJtxt_cnlog().setBackground(Color.RED);
			}
		} catch (ConnectException e5) {
			jam.getJtxt_cnlog().setText("Connection refused by host " + ((JanelaSelectServer) jsv).getIpText() + " on port " + ((JanelaSelectServer) jsv).getPortNumber());
			jam.getJtxt_cnlog().setBackground(Color.RED);
		} catch (SocketException e6) {
			jam.getJtxt_cnlog().setText("Lost conenction to server. Socket dropped...Try again.");
			jam.getJtxt_cnlog().setBackground(Color.RED);
			ClientMain.sock = null;
		} catch (NumberFormatException e2) {
			jam.getJtxt_cnlog().setText("No IP or Port informed");
			jam.getJtxt_cnlog().setBackground(Color.RED);
		} catch (UnknownHostException e3) {
			jam.getJtxt_cnlog().setText("Host could not be resolved");
			jam.getJtxt_cnlog().setBackground(Color.RED);
		} catch (IllegalArgumentException e4) {
			jam.getJtxt_cnlog().setText("Invalid Port value");
			jam.getJtxt_cnlog().setBackground(Color.RED);
		} catch (IOException e1) {
			jam.getJtxt_cnlog().setText("I/O Error");
			jam.getJtxt_cnlog().setBackground(Color.RED);
			e1.printStackTrace();
		}
	}
	
	public JButtonConnectListener(JanelaMain jam, JanelaSelectServer jsv) {
		this.jam = jam;
		this.jsv = jsv;
	}
	
}
