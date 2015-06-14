package gui.fx.buttons;

import gui.fx.WindowDataFacade;
import gui.fx.events.EventInterface;

import java.io.IOException;
import java.net.UnknownHostException;

import javafx.fxml.FXML;
import sendable.clients.Client;
import serverinteraction.Disconnect;
import sync.ClientStream;
import clientmain.ClientMain;
import clientmain.Status;

public class ExitPerform implements EventInterface {

	private WindowDataFacade wdf = WindowDataFacade.getInstance();

	@FXML
	public boolean performAction() {
		if (Status.getInstance().isConnected()) {
			try {
				wdf.setDisconnectedLockFields();
				wdf.createCanceledWorker();
				Status.getInstance().setConnected(false);
				new Disconnect(buildClient());
				return true;
			} catch (UnknownHostException e) {
				ClientStream.getInstance().setSock(null);
				Status.getInstance().setConnected(false);
				return false;
			} catch (IOException e) {
				ClientStream.getInstance().setSock(null);
				Status.getInstance().setConnected(false);
				return false;
			} finally {
				System.exit(0);
			}
		} else {
			System.exit(0);
			return true;
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
