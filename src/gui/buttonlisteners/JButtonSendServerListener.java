package gui.buttonlisteners;
import exceptions.ServerException;
import gui.janelas.JanelaMain;
import gui.janelas.JanelaSelectServer;
import gui.updatelogs.ConnectionLogUpdater;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import clientmain.ClientMain;

import sendable.Message;
import sendable.NormalMessage;
import serverinteraction.Send;

public class JButtonSendServerListener implements ActionListener {

	Message m = null;
	private JanelaMain jam;
	private JanelaSelectServer jsv;
	private Send send = new Send();
	private ConnectionLogUpdater log;


	@Override
	public void actionPerformed(ActionEvent e) {
		sendAndhandleLog(jam);
	}
	
	public void sendAndhandleLog(JanelaMain jam) {
		log = jam.getConnectionLog();
		try {
			send.send(assembleNormalMessage());
		} catch (UnknownHostException e) {
			e.printStackTrace();
			log.setErrorMessage("LOCAL> Unknown host, or host disconencted.");
		} catch (IOException e) {
			e.printStackTrace();
			log.setErrorMessage("LOCAL> I/O Exception");
		} catch (ServerException e) {
			log.setErrorMessage("LOCAL> " + e.getMessage());
		}
		
		/*try {
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
		}*/
	}

	private Message assembleNormalMessage() throws ServerException {
		if (ClientMain.CONNECTED) {
			if(jsv.getName() == null) {
				throw new ServerException("Name cannot be blank.");
			} else if (jam.getTextField().getText().equalsIgnoreCase("")) {
				throw new ServerException("Blank messages not permitted.");
			} else {
				Message m = new NormalMessage();
				m.addSeen(jsv.getName());
				m.setOwner(jsv.getName());
				m.setText(jam.getTextField().getText());
				m.setTimestamp();
				return m;
			}
		} else {
			throw new ServerException("Not connected.");
		}
	}
	
	public JButtonSendServerListener(JanelaMain jam) {
		this.jam = jam;
		this.jsv = jam.getJsv();
	}

}
