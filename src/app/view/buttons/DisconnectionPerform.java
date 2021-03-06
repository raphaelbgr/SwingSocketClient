package app.view.buttons;

import java.io.IOException;
import java.net.UnknownHostException;

import app.ClientMain;
import app.control.serverinteraction.Disconnect;
import app.view.WindowDataFacade;
import app.view.events.EventInterface;
import javafx.fxml.FXML;
import net.sytes.surfael.api.control.sync.ClientStream;
import net.sytes.surfael.api.control.sync.Status;
import net.sytes.surfael.api.model.clients.Client;

public class DisconnectionPerform implements EventInterface {
	
	private WindowDataFacade wdf = WindowDataFacade.getInstance();

	@FXML
	public boolean performAction() {
		try {
			wdf.setDisconnectedLockFields();
			wdf.createCanceledWorker();
			new Disconnect(buildClient());
//			Status.getInstance().setConnected(false);
			wdf.setBigStatusMsg(ClientMain.getTimestamp() + " LOCAL > Disconencted");
			wdf.addDisconnectionChatMessage();
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
