package serverinteraction;

import exceptions.LocalException;
import gui.swing.WindowDataFacade;
import gui.swing.janelas.JanelaMain;
import gui.swing.janelas.JanelaSelectServer;

import java.io.IOException;
import java.net.UnknownHostException;

import sendable.Message;
import sendable.NormalMessage;
import sync.ClientStream;
import clientmain.Status;

public class Send {

	JanelaMain jam 			= null;
	ClientStream stream 	= ClientStream.getInstance();
	JanelaSelectServer jsv 	= WindowDataFacade.getJsv();

	public Send(Object o) throws IOException, LocalException {
		if (Status.getInstance().isConnected()) {
			if (o instanceof Message) {
				if(Status.getInstance().isConnected()) {
					stream.sendObject((NormalMessage)o);	//SENDS THE MESSAGE
				}
			}
		} else {
			throw new LocalException("Not connected.");
		}
	}
	
	public Send() {
		
	}

	public boolean send(Object o) throws UnknownHostException, IOException, LocalException {	
		if (Status.getInstance().isConnected()) {
			if (o instanceof NormalMessage) {
				if(Status.getInstance().isConnected()) {
					stream.sendObject((NormalMessage)o);	//SENDS THE MESSAGE
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			throw new LocalException("Not connected.");
		}
	}

}
