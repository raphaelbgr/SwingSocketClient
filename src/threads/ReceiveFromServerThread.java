package threads;

import exceptions.ServerException;
import gui.WindowDataFacade;
import gui.janelas.JanelaMain;
import gui.updatelogs.ConnectionLogUpdater;
import gui.updatelogs.TextLog;

import java.io.IOException;

import javax.swing.JTextField;

import sendable.DisconnectionMessage;
import sendable.Message;
import sendable.NormalMessage;
import sendable.ServerMessage;
import sync.ClientStream;
import clientmain.ClientMain;

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
								stream.checkOnlineStatus();
								//TODO
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
					if (!ClientStream.getInstance().checkOnlineStatus()) {
						clog.setErrorMessage("LOCAL> I/O Error, operation aborted, please reconnect.");
					}
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

		public ReceiveFromServerThread(JanelaMain jam) {
			this.jam = jam;
			this.clog = jam.getConnectionLog();
		}

}
