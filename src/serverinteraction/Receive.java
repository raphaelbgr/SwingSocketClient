package serverinteraction;

import exceptions.ServerException;
import gui.WindowDataFacade;
import gui.updatelogs.LocalLogUpdater;

import java.io.IOException;

import sendable.Client;
import sendable.ConnectionMessage;
import sendable.DisconnectionMessage;
import sendable.Message;
import sendable.NormalMessage;
import sync.ClientStream;

public class Receive {

	public Receive() throws ClassNotFoundException, IOException {
		ClientStream stream 	= ClientStream.getInstance();
		LocalLogUpdater log = WindowDataFacade.getJam().getLocalConnectionLog();
		
		Object o = stream.receiveMessage();
		if (o instanceof Message) {
			if (o instanceof NormalMessage) {
				log.setGreenMessage("Connected on host.");
			} else if (o instanceof ConnectionMessage) {
				log.setGreenMessage("Connected on host.");
			} else if (o instanceof DisconnectionMessage) {
				log.setErrorMessage("Error.");
			} else if (o instanceof Client) {
				log.setGreenMessage("Client connected.");
			}
		} else if (o instanceof ServerException) {
			System.err.println(((ServerException)o).getMessage());
		}
		
	}
	
}
