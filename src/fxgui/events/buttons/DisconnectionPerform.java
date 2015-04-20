package fxgui.events.buttons;

import java.io.IOException;
import java.net.UnknownHostException;

import javafx.fxml.FXML;
import javafx.scene.Node;
import sendable.Client;
import serverinteraction.Disconnect;
import sync.ClientStream;
import clientmain.ClientMain;
import fxgui.events.WindowData;

public class DisconnectionPerform {

ClientStream stream = ClientStream.getInstance();
	
	@FXML
	public void performAction(WindowData wd) {
		try {
			unlockConnectionFields(wd);
			new Disconnect(buildClient(wd));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			ClientStream.getInstance().setSock(null);
//			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ClientStream.getInstance().setSock(null);
//			e.printStackTrace();
		}
	}
	
	private Client buildClient(WindowData wd) {
		Client c = new Client();
		c.setName(wd.getUserName());
		c.setPort(wd.getPort());
		c.setVersion(ClientMain.version);
		c.setPassword(wd.getPassword());
		c.setLogin(wd.getUserName());
		return c;
	}
	
	private void unlockConnectionFields(WindowData wd) {
		for (Node node : wd.getNodes()) {
			if (!node.getId().equalsIgnoreCase("btn_disconnect")) {
				node.setDisable(false);
			} else if (node.getId().equalsIgnoreCase("btn_disconnect")){
				node.setDisable(true);
			}
		}
	}
	
}
