package api.control.serverinteraction;

import java.io.IOException;
import java.net.UnknownHostException;

import api.control.sync.ClientStream;
import api.control.sync.Status;
import api.model.exceptions.LocalException;
import api.model.messages.Message;
import api.model.messages.NormalMessage;

public class Send {

	ClientStream stream 	= ClientStream.getInstance();

	public Send(Object o) throws IOException, LocalException {
		if (Status.getInstance().isConnected()) {
			if (o instanceof Message) {
				if(Status.getInstance().isConnected()) {
//					Gson gson = new Gson();
//					o = gson.toJson((NormalMessage)o);
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
//					Gson gson = new Gson();
//					o = gson.toJson((NormalMessage)o);
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
