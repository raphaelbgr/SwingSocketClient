package app.view.buttons;

import java.io.IOException;
import java.net.UnknownHostException;

import app.ClientMain;
import app.control.serverinteraction.Disconnect;
import app.control.sync.ClientStream;
import app.control.sync.Status;
import app.model.clients.Client;
import app.view.WindowDataFacade;
import app.view.events.EventInterface;
import javafx.fxml.FXML;

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
		c.setLogin(wdf.getLogin());
		c.setTargetPort(wdf.getPort());
		c.setVersion(ClientMain.VERSION);
		c.setPassword(wdf.getPassword());
		c.setDisconnect(true);
		return c;
	}
	
}
