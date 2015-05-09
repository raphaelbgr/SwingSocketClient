package gui.fx.buttons;

import gui.fx.WindowDataFacade;
import gui.fx.events.EventInterface;

import java.io.IOException;
import java.net.UnknownHostException;

import javafx.fxml.FXML;
import sendable.Client;
import serverinteraction.Disconnect;
import sync.ClientStream;
import clientmain.ClientMain;
import clientmain.Status;

public class DisconnectionPerform implements EventInterface {
	
	private WindowDataFacade wdf = WindowDataFacade.getInstance();

	@FXML
	public void performAction() {
		try {
			wdf.setDisconnectedLockFields();
			wdf.createCanceledWorker();
			new Disconnect(buildClient());
//			Status.getInstance().setConnected(false);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			ClientStream.getInstance().setSock(null);
			Status.getInstance().setConnected(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ClientStream.getInstance().setSock(null);
			Status.getInstance().setConnected(false);
		}
	}
	
	private Client buildClient() {
		Client c = new Client();
		c.setName(wdf.getComboLogin());
		c.setTargetPort(wdf.getPort());
		c.setVersion(ClientMain.version);
		c.setPassword(wdf.getPassword());
		return c;
	}
	
}
