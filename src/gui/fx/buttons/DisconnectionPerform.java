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
	public boolean performAction() {
		try {
			wdf.setDisconnectedLockFields();
			wdf.createCanceledWorker();
			new Disconnect(buildClient());
//			Status.getInstance().setConnected(false);
			return true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			ClientStream.getInstance().setSock(null);
			Status.getInstance().setConnected(false);
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ClientStream.getInstance().setSock(null);
			Status.getInstance().setConnected(false);
			return false;
		}
	}
	
	private Client buildClient() {
		Client c = new Client();
		c.setName(wdf.getLogin());
		c.setTargetPort(wdf.getPort());
		c.setVersion(ClientMain.VERSION);
		c.setPassword(wdf.getPassword());
		return c;
	}
	
}
