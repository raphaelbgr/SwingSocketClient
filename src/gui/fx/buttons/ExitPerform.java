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

public class ExitPerform implements EventInterface {

	private WindowDataFacade wdf = WindowDataFacade.getInstance();

	@FXML
	public void performAction() {
		if (Status.getInstance().isConnected()) {
			try {
				wdf.setDisconnectedLockFields();
				wdf.createCanceledWorker();
				Status.getInstance().setConnected(false);
				new Disconnect(buildClient());
			} catch (UnknownHostException e) {
				ClientStream.getInstance().setSock(null);
				Status.getInstance().setConnected(false);
			} catch (IOException e) {
				ClientStream.getInstance().setSock(null);
				Status.getInstance().setConnected(false);
			} finally {
				System.exit(0);
			}
		} else System.exit(0);
		
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
