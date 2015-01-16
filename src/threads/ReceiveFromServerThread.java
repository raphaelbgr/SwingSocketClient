package threads;

import exceptions.ServerException;
import gui.janelas.JanelaMain;
import gui.updatelogs.ConnectionLog;

import java.awt.Color;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JTextField;

import sendable.Message;
import sync.ClientStream;
import clientmain.ClientMain;

public class ReceiveFromServerThread implements Runnable {

	private JanelaMain jam;
	private ClientStream stream = ClientStream.getInstance();
	private ConnectionLog clog = ConnectionLog.getInstance();

	@Override
	public void run() {

		while (true) {

			try {
				Object o = stream.receiveMessage();
				if (o != null) {
					if (o instanceof Message) {
						Message m = (Message) o;
						if (m.getType().equals("normal") && (m.getType() != null)) {
							clog.setGreenMessage("SERVER> " + m.getServresponse());
						} else if (m.getType().equals("broadcast") && (m.getType() != null)) {
							//TERMINAR ISSO
							jam.getMsg_list().add(m.getText(), new JTextField());
						} else if (m.getType().equals("connectok") && (m.getType() != null)) {
							clog.setGreenMessage(m.getServresponse() + " on " + ClientMain.ip + " on port " + ClientMain.port);
						}
					} else if (o instanceof ServerException) {
						ServerException se = (ServerException) o;
						clog.setErrorMessage(se.getMessage());
					}
				} else {
					clog.setErrorMessage("Connected but not responding.");
				}
			} catch (ClassNotFoundException e) {
			} catch (IOException e) {
				clog.setErrorMessage("I/O Error, operation aborted.");
			} finally {
			}
		}
	}

	public ReceiveFromServerThread(JanelaMain jam) {
		this.jam = jam;
	}

}
