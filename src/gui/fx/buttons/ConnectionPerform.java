package gui.fx.buttons;

import gui.fx.WindowDataFacade;
import gui.fx.events.EventInterface;

import java.io.IOException;
import java.net.UnknownHostException;

import javafx.fxml.FXML;
import sendable.Client;
import serverinteraction.Connect;
import sync.ClientStream;
import clientmain.ClientMain;

public class ConnectionPerform implements EventInterface {

	ClientStream stream = ClientStream.getInstance();
	WindowDataFacade wdf = null;
	
	public ConnectionPerform(WindowDataFacade wdf) {
		this.wdf = wdf;
	}

	@FXML
	public void performAction() {
		try {
			Client c = buildClient(wdf);
			new Connect(c);
			wdf.setConnectedLockFields();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Client buildClient(WindowDataFacade wdf) {
		Client c = new Client();
		c.setName(wdf.getUserName());
		c.setPort(wdf.getPort());
		c.setVersion(ClientMain.version);
		c.setPassword(wdf.getPassword());
		return c;
	}
}
