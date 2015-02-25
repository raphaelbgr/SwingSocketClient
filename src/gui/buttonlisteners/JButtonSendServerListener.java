package gui.buttonlisteners;
import exceptions.LocalException;
import exceptions.ServerException;
import gui.WindowDataFacade;
import gui.janelas.JanelaMain;
import gui.janelas.JanelaSelectServer;
import gui.updatelogs.LocalLogUpdater;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import sendable.Message;
import sendable.NormalMessage;
import serverinteraction.Send;
import clientmain.Status;

public class JButtonSendServerListener implements ActionListener {

	Message m = null;
	private JanelaMain jam;
	private JanelaSelectServer jsv;
	private Send send = new Send();
	private LocalLogUpdater log;


	@Override
	public void actionPerformed(ActionEvent e) {
		if (jam.getTextField().getText().length() < 100) {
			sendAndhandleLog(jam);
		} else {
			log = jam.getLocalConnectionLog();
			log.setErrorMessage(getTimestamp() + "LOCAL> Message cannot exeed 100 characters");
		}	
	}

	public void sendAndhandleLog(JanelaMain jam) {
		if (Status.getInstance().isConnected() == true) {
			log = jam.getLocalConnectionLog();
			try {
				send.send(assembleMessage());
				(jam).getTextField().setText("");
				log.setGreyMessage(getTimestamp() + "LOCAL> Sent to server");
//				Status.getInstance().setConnected(true);
			} catch (UnknownHostException e) {
				e.printStackTrace();
				Status.getInstance().setConnected(false);
				log.setErrorMessage(getTimestamp() + "LOCAL> Unknown host, or host disconencted.");
				WindowDataFacade.getJam().getJbt_Connect().setEnabled(true);
			} catch (IOException e) {
				//				e.printStackTrace();
				Status.getInstance().setConnected(false);
				log.setErrorMessage(getTimestamp() + "LOCAL> I/O Exception");
				WindowDataFacade.getJam().getJbt_Connect().setEnabled(true);
			} catch (ServerException e) {
				log.setErrorMessage(getTimestamp() + "LOCAL> " + e.getMessage());
				//				WindowDataFacade.getJam().getJbt_Connect().setEnabled(true);
			} catch (LocalException e) {
				log.setGreyMessage(getTimestamp() + "LOCAL> " + e.getMessage());
			}
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

	private Message assembleMessage() throws ServerException {
		if (Status.getInstance().isConnected()) {
			if(jsv.getName() == null) {
				throw new ServerException("Name cannot be blank.");
			} else if (jam.getTextField().getText().equalsIgnoreCase("")) {
				throw new ServerException("Blank messages not permitted.");
			} else {
				Message m = new NormalMessage();
				m.addSeen(jsv.getNameFieldText());
				m.setOwner(jsv.getName());
				m.setText(jam.getTextField().getText());
				m.setTimestamp();
				return m;
			}
		} else {
			throw new ServerException("Not connected.");
		}
	}

	private String getTimestamp() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateFormatted = formatter.format(new Date());
		return "["+dateFormatted+"]" + " ";
	}

	public JButtonSendServerListener(JanelaMain jam) {
		this.jam = jam;
		this.jsv = jam.getJsv();
	}

}
