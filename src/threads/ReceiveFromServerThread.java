package threads;

import exceptions.ServerException;
import gui.WindowDataFacade;
import gui.janelas.JanelaMain;
import gui.updatelogs.ConnectionLogUpdater;
import gui.updatelogs.TextLog;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import clientmain.Status;
import sendable.BroadCastMessage;
import sendable.DisconnectionMessage;
import sendable.Message;
import sendable.NormalMessage;
import sendable.ServerMessage;
import serverinteraction.Disconnect;
import sync.ClientStream;

public class ReceiveFromServerThread implements Runnable {

	private JanelaMain jam = null;;
	private ClientStream stream = ClientStream.getInstance();
	private ConnectionLogUpdater clog = null;
	private TextLog tlog = WindowDataFacade.getInstance().getJam().getMsg_list();

	@Override
	public void run() {

		while (true) {
			if ((stream.getSock() != null) && (stream.getSock().isConnected() == true)) {
				try {
					Object o = stream.receiveMessage();
					if (o != null) {
						if (o instanceof Message) {
							if (o instanceof ServerMessage) {
								ServerMessage sm = (ServerMessage) o;
								clog.setGreenMessage(sm.toString());
							} else if (o instanceof NormalMessage) {
								NormalMessage nm = (NormalMessage) o;
								clog.setGreenMessage("[" + nm.getTimestamp() + "]" + " " + nm.getServresponse());
								tlog.addMessage(nm.toString());
							} else if (o instanceof DisconnectionMessage) {
								// Client receives order to disconnect.
								// TODO AFTER ONE CLIENT DISCONNECTS, THE NEXT MESSAGE WILL BE CONSIDERED AS DISCONNECTIONMESSAGE
								// THIS SHOULD NOT HAPPEN FIXME
								new Disconnect();
								WindowDataFacade.getJsv().unlockFields();
								WindowDataFacade.getJam().getJbt_send().setEnabled(false);
								WindowDataFacade.getJam().getJbt_Disconn().setEnabled(false);
								WindowDataFacade.getJam().getJbt_Connect().setEnabled(true);
								Status.getInstance().setConnected(false);
//								stream.checkOnlineStatus();
//								stream.getSock().close();
//								stream.setSock(null);
							} else if (o instanceof BroadCastMessage) {
								BroadCastMessage nm = (BroadCastMessage) o;
								clog.setGreenMessage("[" + nm.getTimestamp() + "]" + " " + nm.getServresponse());
								tlog.addMessage(nm.toString());
							}
						} else if (o instanceof ServerException) {
							ServerException se = (ServerException) o;
							clog.setErrorMessage(se.getMessage());
						}
					} else {
						clog.setGreyMessage("LOCAL> Connected but cannot confirm.");
					}
				} catch (ClassNotFoundException e) {
					clog.setErrorMessage("LOCAL> Report this to dev: \"ClassNotFoundException\".");
				} catch (IOException e) {
					Status.getInstance().setConnected(false);
				} finally {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private String getTimestamp() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateFormatted = formatter.format(new Date());
		return "["+dateFormatted+"]" + " ";
	}
	
	public ReceiveFromServerThread(JanelaMain jam) {
		this.jam = jam;
		this.clog = jam.getConnectionLog();
	}

}
