package app.control.serverinteraction;

import java.io.IOException;
import java.net.UnknownHostException;

import app.control.Status;
import app.control.sync.ClientStream;
import app.model.exceptions.LocalException;
import app.model.messages.Message;
import app.model.messages.NormalMessage;

public class Send {

//	JanelaMain jam 			= null;
	ClientStream stream 	= ClientStream.getInstance();
//	JanelaSelectServer jsv 	= WindowDataFacade.getJsv();

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
