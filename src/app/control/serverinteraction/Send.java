package app.control.serverinteraction;

import java.io.IOException;
import java.net.UnknownHostException;

import app.model.exceptions.LocalException;
import app.model.messages.Message;
import app.model.messages.NormalMessage;
import net.sytes.surfael.api.control.sync.ClientStream;
import net.sytes.surfael.api.control.sync.Status;

public class Send {

	ClientStream stream 	= ClientStream.getInstance();

	public Send(Object o) throws IOException, LocalException {
		if (Status.getInstance().isConnected()) {
			if (o instanceof Message) {
				if(Status.getInstance().isConnected()) {
					stream.sendObject(o);	//SENDS THE MESSAGE
				}
			}
		} else {
			throw new LocalException("Not connected.");
		}
	}
	
	public boolean send(Object o) throws UnknownHostException, IOException, LocalException {	
		if (Status.getInstance().isConnected()) {
			if (o instanceof NormalMessage) {
				if(Status.getInstance().isConnected()) {
					stream.sendObject(o);	//SENDS THE MESSAGE
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
