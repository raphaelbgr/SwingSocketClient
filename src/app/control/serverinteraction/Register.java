package app.control.serverinteraction;

import java.io.IOException;
import java.net.UnknownHostException;

import app.control.sync.ClientStream;
import app.control.sync.Status;
import app.model.clients.Client;
import app.model.clients.NewClient;
import app.model.exceptions.LocalException;

public class Register {
	public Register(Object o) throws IOException, LocalException {
		if (Status.getInstance().isConnected()) {
			if (o instanceof Client) {
				if(Status.getInstance().isConnected()) {
					ClientStream.getInstance().sendObject((NewClient)o);	//SENDS THE MESSAGE
				}
			} else throw new LocalException("Must be an instance of Client class, to send this object.");
		} else {
			throw new LocalException("Not connected.");
		}
	}
	
	public boolean register(Object o) throws UnknownHostException, IOException, LocalException {	
		if (Status.getInstance().isConnected()) {
			if (o instanceof Client) {
				if(Status.getInstance().isConnected()) {
					ClientStream.getInstance().sendObject((NewClient)o);	//SENDS THE MESSAGE
					return true;
				} else {
					return false;
				}
			} else throw new LocalException("Must be an instance of Client class, to send this object.");
		} else throw new LocalException("Not connected.");
	}
}
